const gulp = require('gulp');
const gulpUtil = require("gulp-util");
const cssnano = require('gulp-cssnano');
const htmlmin = require('gulp-htmlmin');
const del = require('del');
const browserSync = require('browser-sync').create();
const reload = browserSync.reload;
const wiredep = require('wiredep').stream;
const karma = require('karma').Server;
const runSequence = require('run-sequence');
const webpack = require('webpack');
const WebpackDevServer = require('webpack-dev-server');
const webpackConf = require('./webpack.base.conf.js');
const merge = require('webpack-merge');
const mockjs = require('mockjs');
const open = require('open');

/////////////////////////////////// 测试服务 //////////////////////////////////////////////

// Mock服务器
gulp.task('mock-server', function() {
    const url = require('url');
    let fileMap = initFileMap();

    browserSync.init({
        notify: false,
        port: 9000,
        server: {
            baseDir: ["mock"],
            index: "index.html",
            middleware: function (req, res, next) {
                const urlObj = url.parse(req.url, true);
                const pathname = urlObj.pathname;
                const paramObj = urlObj.query;

                //将url转换为mock文件名称
                const fileName = pathname.replace(/\/(\w)/g, function (all, letter) {
                    return letter.toUpperCase();
                })

                const fileObj = fileMap[fileName];
                if(fileObj) {
                    const mockData = getMockData(fileObj, paramObj);
                    if(mockData) {
                        res.setHeader('Access-Control-Allow-Origin', '*');
                        res.setHeader('Content-Type', 'application/json; charset=utf-8');
                        res.write(JSON.stringify(mockData));
                        res.end();
                    }
                }
                next();
            }
        }
    });

    gulp.watch([
        'mock/**/*'
    ]).on('change', function() {
        fileMap = initFileMap();
        reload();
    });
});

// Dist服务器
gulp.task('dist-server', function() {
    browserSync.init({
        notify: false,
        port: 9001,
        server: {
            baseDir: ["dist"],
            index: "index.html",
        }
    });

    gulp.watch([
        'dist/**/*'
    ]).on('change', reload);
});

// 单元测试
// gulp.task('karma-test', function(callback) {
//     new karma({
//         configFile: __dirname+'/karma.conf.js',
//         // detached: true,
//         autoWatch: true, //karma进行监听工作
//         singleRun: false //不只运行一次
//     }, callback).start();
// });

/////////////////////////////////// 构建任务 //////////////////////////////////////////////

// 清理dist目录
gulp.task('clean', function() {
    return del(['dist']).then(function(paths){
        console.log('删除目录与文件:\n', paths.join('\n'));
    });
});

// 压缩CSS
gulp.task('minCss', function () {
    return gulp.src('dist/styles/**/*.css').pipe(cssnano()).pipe(gulp.dest('dist/styles'));
});

//压缩HTML
gulp.task('minHtml', function () {
    var options = {
        removeComments: true,//清除HTML注释
        collapseWhitespace: true,//压缩HTML
        collapseBooleanAttributes: true,//省略布尔属性的值 <input checked="true"/> ==> <input />
        removeEmptyAttributes: true,//删除所有空格作属性值 <input id="" /> ==> <input />
        removeScriptTypeAttributes: true,//删除<script>的type="text/javascript"
        removeStyleLinkTypeAttributes: true,//删除<style>和<link>的type="text/css"
        minifyJS: true,//压缩页面JS
        minifyCSS: true//压缩页面CSS
    };
    return gulp.src('dist/*.html').pipe(htmlmin(options)).pipe(gulp.dest('dist'));
});

// webpack-dev 开发编译
gulp.task('webpack-dev', ['clean'], function(callback) {
    webpack(merge(webpackConf, {mode: 'development'}), function(err, stats) {
        // 在这里处理日志
        formatLog(err, stats);

        callback();
    });
});

// webpack-dev-server 开发服务-热部署
gulp.task("webpack-dev-server", function(callback) {
    const devServerOptions = {
        contentBase: './dist',
        // hot: true,
        host: 'localhost'
    };

    // node下不支持inline选择所以需要在入口点加入客户端脚本
    for(let key in webpackConf.entry) {
        webpackConf.entry[key].unshift("webpack-dev-server/client?http://localhost:8080/");
    }
    // 想要启用 HMR，还需要修改 webpack 配置对象，使其包含 HMR 入口起点。
    // WebpackDevServer.addDevServerEntrypoints(merge(webpackConf, {mode: 'development'}), devServerOptions);
    const server = new WebpackDevServer(webpack(merge(webpackConf, {mode: 'development'})), devServerOptions)
    server.listen(8080, 'localhost', function(msg) {
        console.log('dev server listening on port 8080');
        open('http://localhost:8080/index.html');
    });
});

// webpack-prod 生产编译
gulp.task('webpack-prod', ['clean'], function(callback) {
    webpack(merge(webpackConf, {mode: 'production'}), function(err, stats) {
        // 在这里处理日志
        formatLog(err, stats);

        callback();// 完成 task与return功能相同
    });
});

gulp.task("dev", function(callback) {
    runSequence('webpack-dev', callback);
});
gulp.task("dev-server", function(callback) {
    runSequence('webpack-dev','webpack-dev-server', callback);
});
gulp.task("prod", function(callback) {
    runSequence('webpack-prod',['minCss', 'minHtml'], 'dist-server', callback);
});


/////////////////////////////////// 工具方法 //////////////////////////////////////////////

// 处理webpack输出日志
function formatLog(err, stats) {
    if (err) {
        throw new gulpUtil.PluginError("webpack", err);
    }
    var statColor = stats.compilation.warnings.length < 1 ? 'green' : 'yellow';

    if (stats.compilation.errors.length > 0) {
        stats.compilation.errors.forEach(function(error) {
            console.log(error);
            statColor = 'red';
        });
    } else {
        gulpUtil.log(stats.toString({
            colors: gulpUtil.colors.supportsColor,
            hash: false,
            timings: true,
            chunks: false,
            chunkModules: false,
            modules: false,
            children: false,
            version: false,
            cached: false,
            cachedAssets: false,
            reasons: false,
            source: false,
            errorDetails: false
        }));
    }
};

// 初始化Mock文件集合
function initFileMap() {
    let fileMap = {};
    const fs = require("fs");
    const path = './mock/data';
    const files = fs.readdirSync(path);
    files.forEach(function (file) {
        const data = fs.readFileSync(path+"/"+file);
        fileMap[file.split('.')[0]] = JSON.parse(data);
    });
    console.log("加载mock文件列表...")
    return fileMap;
}

// 获取mockData
function getMockData(fileObj, paramObj) {
    let returnDate = [];
    fileObj["datas"].forEach(function (data) {
        let pp = true;
        for(let key in paramObj) {
            if(data[key] !== paramObj[key]) {
                pp = false;
            }
        }
        if(pp === true) {
            returnDate.push(data);
        }
    });

    if(returnDate) {
        let mockData = mockjs.mock(returnDate);
        if (fileObj["returnType"] === "Object") {
            mockData = mockjs.mock(returnDate[0]);
        }
        return mockData;
    }
}


