const gulp = require('gulp');
const gulpUtil = require("gulp-util");
const sass = require('gulp-sass');
const sourcemaps = require('gulp-sourcemaps');
const autoprefixer = require('gulp-autoprefixer');
const plumber = require('gulp-plumber');
const babel = require('gulp-babel');
const eslint = require('gulp-eslint');
const uglify = require('gulp-uglify');
const useref = require('gulp-useref');
const gulpif = require('gulp-if');
const cssnano = require('gulp-cssnano');
const htmlmin = require('gulp-htmlmin');
const imagemin = require('gulp-imagemin');
const cache = require('gulp-cache');
const del = require('del');
const browserSync = require('browser-sync').create();
const reload = browserSync.reload;
const size = require('gulp-size');
const wiredep = require('wiredep').stream;
const karma = require('karma').Server;
const runSequence = require('run-sequence');
const webpack = require('webpack');
const WebpackDevServer = require('webpack-dev-server');
const webpackDevConf = require('./webpack.dev.conf.js');
const webpackProdConf = require('./webpack.prod.conf.js');
const mockjs = require('mockjs');
const logger = require('gulp-logger');
const open = require('open');

// js/css部分交由webpack处理
// styles 预编译样式表
// gulp.task('styles', function() {
//     return gulp.src('src/styles/**/*.scss')
//         .pipe(plumber())
//         .pipe(sourcemaps.init())
//         .pipe(sass({
//             outputStyle: 'expanded'
//         }).on('error', sass.logError))
//         .pipe(autoprefixer({
//             browsers: ['> 1%', 'last 2 versions', 'Firefox ESR']
//         }))
//         .pipe(sourcemaps.write('maps'))
//         .pipe(gulp.dest('.tmp/styles'));
// });

// scripts 预编译js.es6
// gulp.task('scripts', function() {
//     return gulp.src('src/scripts/**/*.js')
//         .pipe(plumber())
//         .pipe(sourcemaps.init())
//         .pipe(babel())
//         .pipe(sourcemaps.write())
//         .pipe(gulp.dest('.tmp/scripts'));
// });

// eslint 校验js
// gulp.task('eslint', function() {
//     return gulp.src('src/scripts/**/*.js')
//         .pipe(eslint())
//         .pipe(eslint.format())
//         .pipe(eslint.failAfterError());
// });

// eslint:test 校验测试js.spec
// gulp.task('eslint:test', function() {
//     return gulp.src('test/spec/**/*.js')
//         .pipe(eslint())
//         .pipe(eslint.format())
//         .pipe(eslint.failAfterError());
// });



/////////////////////////////////// mock 服务 //////////////////////////////////////////////

// 启动一个mock服务器
gulp.task('mock-server', function() {
    browserSync.init({
        notify: false,
        port: 9000,
        server: {
            baseDir: ["mock"],
            index: "data.json",
            middleware: mockMiddleware
        }
    });
});

// mockjs数据处理中间件
var mockMiddleware = function (req, res, next) {
    // console.log("Mock middleware");
    const data = require('./mock/data.json');
    // console.log(data);
    // console.log(req.url);
    // console.log(data[req.url])
    const url = require('url');
    const urlObj = url.parse(req.url, true);

    console.log(urlObj);

    const pathname = urlObj.pathname;
    const paramObj = urlObj.query;
    // console.log(paramObj);
    var subData = data[pathname];
    if(subData) {
        if(subData instanceof Array) {
            if(paramObj.id) {
                console.log(paramObj.id);
                subData = subData[paramObj.id]
                console.log(subData);
            }
        }

        var mockData = mockjs.mock(subData);
        if(mockData) {
            // console.log(mockData);
            res.setHeader('Access-Control-Allow-Origin', '*');
            res.setHeader('Content-Type', 'application/json; charset=utf-8');
            res.write(JSON.stringify(mockData));
            res.end();
        }
    }
    next();
};


////////////////////////////////////////// webpack 任务 //////////////////////////////////////////////

// webpack-dev-server 开发服务-热部署
gulp.task("webpack-dev-server", function(callback) {
    // Start a webpack-dev-server
    const devServerOptions = {
        contentBase: './dist',
        hot: true,
        host: 'localhost',
        open: true,
        inline: true,
        stats: {
            colors: true
        },
        historyApiFallback: false,
    };
    // 想要启用 HMR，还需要修改 webpack 配置对象，使其包含 HMR 入口起点。
    // webpack-dev-server package 中具有一个叫做 addDevServerEntrypoints 的方法，可以通过使用这个方法来实现。
    WebpackDevServer.addDevServerEntrypoints(webpackDevConf, devServerOptions);
    const server = new WebpackDevServer(webpack(webpackDevConf), devServerOptions)
    server.listen(8080, 'localhost', function(err) {
        console.log('dev server listening on port 8080');
        open('http://localhost:8080/app.html');
    });
});

// webpack-clean 清理webpack dist目录
gulp.task('webpack-clean', function() {
    return del(['dist']).then(function(paths){
        console.log('Deleted files and folders:\n', paths.join('\n'));
    });
});

// webpack-dev 开发编译
gulp.task('webpack-dev', ['webpack-clean'], function(callback) {
    webpack(webpackDevConf, function(err, stats) {
        // 在这里处理日志
        formatLog(err, stats);

        callback();// 完成 task与return功能相同
    });
});

// webpack-prod 生产编译
gulp.task('webpack-prod', ['webpack-clean'], function(callback) {
    webpack(webpackProdConf, function(err, stats) {
        // 在这里处理日志
        formatLog(err, stats);

        callback();// 完成 task与return功能相同
    });
});

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



// images 压缩图片
gulp.task('images', function() {
    return gulp.src('src/images/**/**')
        .pipe(cache(imagemin([
            imagemin.gifsicle({interlaced: true}),
            imagemin.jpegtran({progressive: true}),
            imagemin.optipng({optimizationLevel: 5}),
            imagemin.svgo({
                plugins: [
                    {removeViewBox: true},
                    {cleanupIDs: false}
                ]
            })
        ])).on('error',function(err){
            console.log(err);
            this.end();
        }))
        .pipe(gulp.dest('dist/images'));
});

// fonts 字体文件
gulp.task('fonts', function() {
    return gulp.src('src/fonts/**/*')
        .pipe(gulp.dest('.tmp/fonts'))
        .pipe(gulp.dest('dist/fonts'));
});

// extras 其他文件
gulp.task('extras', function() {
    return gulp.src(['src/*.*','!src/*.html'],{dot:true})
        .pipe(gulp.dest('dist'));
});

// html 处理HTML引用
gulp.task('html', ['styles','scripts'], function() {
    return gulp.src('src/*.html')
        .pipe(useref({searchPath: ['.tmp', 'app', '.']}))
        .pipe(gulpif('*.js', uglify()))
        .pipe(gulpif('*.css', cssnano()))
        .pipe(gulpif('*.html', htmlmin({collapseWhitespace: true})))
        .pipe(gulp.dest('dist'));
});

// wiredep 引入bower中的组件
gulp.task('wiredep', function() {
    return gulp.src('src/app.html').pipe(wiredep({
        ignorePath: /^(\.\.\/)*\.\./
    })).pipe(gulp.dest('src/'));
});



// karma测试
gulp.task('test', function(callback) {
    new karma({
        configFile: __dirname+'/karma.conf.js',
        // detached: true,
        autoWatch: true, //karma进行监听工作
        singleRun: false //不只运行一次
    }, callback).start();
});


//======================开发模式
gulp.task('dev-server', ['styles','scripts','fonts'], function() {
    browserSync.init({
        notify: false,
        port: 9000,
        server: {
            baseDir: [".tmp", "src"],
            index: "index.html",
            routes: {
                "/bower_components": "bower_components"
            },
            middleware: mockMiddleware
        }
    });

    gulp.watch([
        'src/*.html',
        'src/images/**/*',
        '.tmp/scripts/**/*.js',
        '.tmp/styles/**/*.css',
        '.tmp/fonts/**/*'
    ]).on('change', reload);
    gulp.watch('src/styles/**/*.scss', ['styles']);
    gulp.watch('src/scripts/**/*.js', ['scripts']);
    gulp.watch('src/fonts/**/*', ['fonts']);
    gulp.watch('bower.json', ['wiredep']);
});

gulp.task("dev", function(callback) {
    runSequence('clean',['dev-server'],callback);
});


//====================生产模式
gulp.task('build', ['eslint', 'html', 'images', 'fonts', 'extras'], function() {
    return gulp.src('dist/**/*').pipe(size({title: 'build', gzip: true}));
});
gulp.task('server:dist', function() {
  browserSync.init({
    notify: false,
    port: 9000,
    server: {
      baseDir: ['dist']
    }
  });
});

gulp.task("dist-server", function(callback) {
    runSequence('clean','build','server:dist', callback);
});








