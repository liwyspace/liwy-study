const gulp = require('gulp');
const del = require('del');
const browserSync = require('browser-sync').create();
const reload = browserSync.reload;
/////////////////////////////////// 测试服务 //////////////////////////////////////////////

// Mock服务器
gulp.task('mock-server', function () {
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
    ]).on('change', function () {
        fileMap = initFileMap();
        reload();
    });
});

// Dist服务器
gulp.task('dist-server', function () {
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


/////////////////////////////////// 构建任务 //////////////////////////////////////////////

// 清理dist目录
gulp.task('clean', function () {
    return del(['dist']).then(function (paths) {
        console.log('删除目录与文件:\n', paths.join('\n'));
    });
});

/////////////////////////////////// 工具方法 //////////////////////////////////////////////

// 初始化Mock文件集合
function initFileMap() {
    let fileMap = {};
    const fs = require("fs");
    const path = './mock/data';
    const files = fs.readdirSync(path);
    files.forEach(function (file) {
        const data = fs.readFileSync(path + "/" + file);
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
        let mockData = returnDate;
        if(fileObj["returnType"] === "Object") {
            mockData = returnDate[0];
        }
        return mockData;
    }
}


