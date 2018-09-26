# liwy-oscafe-portal 开源小屋前端门户系统

[![GitHub license](https://img.shields.io/github/license/liwyspace/liwy-oscafe.svg)](https://github.com/liwyspace/liwy-oscafe/blob/master/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/liwyspace/liwy-oscafe.svg?style=social)](https://github.com/liwyspace/liwy-oscafe/issues)
[![GitHub stars](https://img.shields.io/github/stars/liwyspace/liwy-oscafe.svg?style=social)](https://github.com/liwyspace/liwy-oscafe/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/liwyspace/liwy-oscafe.svg?style=social)](https://github.com/liwyspace/liwy-oscafe/network)

* __作者：__ LIWY
* __QQ：__ 332301842
* __微信：__ liwy1024611
* __邮箱：__ liwy1024@163.com
* __github:__ [https://github.com/liwyspace/](https://github.com/liwyspace/)
* __gitee:__ [https://gitee.com/liwycode/](https://gitee.com/liwycode/)
* __team@osc:__ [https://team.oschina.net/liwy/](https://team.oschina.net/liwy/)
* __开源小屋官网：__ [http://www.oscafe.net](http://www.oscafe.net)
* __开源小屋公众号：__ oscafe_net

![开源小屋www.oscafe.org 公众平台二维码](https://github.com/liwyspace/liwy-oscafe/raw/master/docs/resources/oscafe_qrcode.jpg)


## 1. 前言
liwy-oscafe-portal 开源小屋前端门户系统。其为开源小屋项目前后端分离后的前端门户系统。

## 2. 技术栈

技术 | 名称 | 官网
----|------|----
gulp | Gulp | [https://www.gulpjs.com.cn/](https://www.gulpjs.com.cn/)
webpack | Webpack | [https://webpack.github.io/](https://webpack.github.io/)
karma | Karma | 
jasemine | Jasemine | 
sass | Sass | [https://www.sass.hk/](https://www.sass.hk/)
babel | Babel | 
eslint | Eslint | 
mockjs | MockJs | 
jQuery | 函式库  | [http://jquery.com/](http://jquery.com/)

## 3. 源码结构
- src
    - index 首页
        - index.html
        - styles
        - scripts
            - index.js
        - images
        - fonts
    - course 课程列表
        - course.html
        - styles
        - scripts
            - course.js
        - images
    - work 作品列表
        - work.html
        - styles
        - scripts
            - work.js
        - images
    - ......
    - components 功能组件
        - header 页头组件
        - footer 页脚组件
        - tabs 标签页组件
        - page 分页组件
        - picList 图标列表组件
        - slide3d 3D滚动图组件
        - upgradeBrowser 浏览器检查组件
        - category 分类组件
        - collectionList 收藏列表组件
        - summaryList 博文列表组件
        - slideCourse 侧边栏文章列表
        - slideUser 侧边栏用户列表
    - commons 公共内容
        - styles
            - base.scss
            - projectPicList.scss
        - scripts
            - vendors 第三方为打包库
            - utils.js 工具库
            - constant.js 常态变量库
        - images
        - fonts

## 4. 部署结构

- dist
    - index.html
    - course.html
    - works.html
    - styles
        - index.css
        - course.css
        - works.css
        - commons
            - commons.css
            - vendors.css
    - scritps
        - index.js
        - course.js
        - works.js
        - commons
            - commons.js
            - manifest.js
            - vendors.js
        - vendors
            - jquery.min.js
            - idangerous.swiper.min.js
    - images
    - fonts

## 5. 部署项目
1. 下载项目到本地：`git clone git@github.com:liwyspace/liwy-oscafe-portal.git`
2. 进入项目目录：`cd liwy-oscafe-portal`
3. 安装npm包：`npm install`
4. 安装bower包：`bower install`

## 6. 启动
```bash
#启动MOCK服务
npm run mock

# 开发模式-编译
npm run dev
# 开发模式-启动WEB服务器
npm run dev-server
# 生产模式-编译, 同时会启用一个WEB服务进行查看
npm run prod

# 使用webpack-开发模式
npm run webpack-dev
# 使用webpack服务-提供热部署
npm run webpack-dev-server
# 使用webpack-生产模式
npm run webpack-prod
```

## 7. 项目总结

#### 1) 使用wiredep向html中注入bower组件
html中通过bower/endbower注释定位
```html
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <!-- bower:css -->
    <!-- endbower -->
</head>
<body>
    <!-- bower:js -->
    <!-- endbower -->
</body>
</html>
```
gulp中将bower.json中dependencies的包注入到html中
```js
// wiredep 引入bower中的组件
gulp.task('wiredep', function() {
    return gulp.src('src/app.html').pipe(wiredep({
        ignorePath: /^(\.\.\/)*\.\./
    })).pipe(gulp.dest('src/'));
});
```

#### 2) gulp中使用webpack任务并实现热部署
```js
// webpack-dev-server 开发服务-热部署
gulp.task("webpack-dev-server", function(callback) {
    // Start a webpack-dev-server
    const devServerOptions = {
        contentBase: './dist',
        // hot: true,
        host: 'localhost'
    };
    // node下不支持inline选择所以需要在入口点加入客户端脚本
    webpackDevConf.entry[key].unshift("webpack-dev-server/client?http://localhost:8080/");
    // 想要启用 HMR，还需要修改 webpack 配置对象，使其包含 HMR 入口起点。
    // webpack-dev-server package 中具有一个叫做 addDevServerEntrypoints 的方法，可以通过使用这个方法来实现。
    // WebpackDevServer.addDevServerEntrypoints(webpackDevConf, devServerOptions);
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
```

#### 3) 创建能够解析mockjs的服务器
```js
// 启动一个mock服务器
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
```

例如/user/getUserInfo请求访问的就是UserGetUserInfo.json文件，匹配方式为“/”转为分隔转为驼峰格式。

json文件中datas为范围数据，为数组类型，该数组会根据请求入参进行匹配筛选出符合参数的列表，如果最终列表中只有一个元素则返回为对象。
```json
{
    "returnType": "Object",
    "datas": [{
        "id": "123456",
        "name": "李文姚",
        "headPic": "http://localhost:9000/uploads/40.jpg",
        "age": 26
    },{
        "id": "10001",
        "name": "李文姚0001",
        "headPic": "http://localhost:9000/uploads/40.jpg",
        "age": 99
    }]
}
```
#### 4) webpack 引用第三方模块的几种方式
通过设置别名，指定require引入模块的具体文件
```js
module.exports = {
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.esm.js',
            //当require或import jquery时，实际上引入的是浏览器的全局变量window.jQuery
            //此时jquery由html的<script>引入
            'jquery': 'window.jQuery'
        }
    }
}
```

通过设置externals来引用浏览器全局变量中变量
```js
// 允许指定库的依赖项，这些依赖项不会被webpack解析，而是成为输出的依赖关系。
// 这意味着它们在运行时从环境中导入。
// 此时jquery由html的<script>引入
module.exports = {
    externals: {
        'jquery': 'jQuery'
    }
}
```

通过expose-loader将node模块暴露到全局变量
```js
module.exports = {
    module: {
        rules: [
            {//通过expose-loader暴露全局变量，使得页面引入的插件也可以获取到jquery
                test: require.resolve('jquery'),
                use: [{
                  loader: 'expose-loader',
                  options: 'jQuery'
                },{
                  loader: 'expose-loader',
                  options: '$'
                }]
            }
        ]
    }
}
```

通过ProvidePlugin插件将某个模块自动加载到js模块中
```js
module.exports = {
    plugins: [
        // 当webpack加载到某个js模块里，出现了未定义且名称符合配置中key的变量时，
        // 会自动require配置中value所指定的js模块。
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery',
            'window.jQuery': 'jquery',
            'window.$': 'jquery'
        })
    ]
}
```

#### 5) Webpack4新内容
1.mini-css-extract-plugin插件替代了原extract-text-plugin
```js
new MiniCssExtractPlugin({
    filename: "styles/[name].[contenthash:8].css",
    chunkFilename: "styles/[id].[contenthash:8].css"
})
```
2.splitChunks与runtimeChunk替代了原CommonsChunkPlugin插件
```
optimization: {
    //代替webpack3的CommonsChunkPlugin插件
    splitChunks: {
        chunks: "initial",
        minChunks: 2,
        cacheGroups: {
            commons: {
                name: "commons/commons",
                priority: -20
            },
            vendors: {
                name: "commons/vendors",
                test: /[\\/]node_modules[\\/]/,
                priority: -10
            }
        }
    },
    runtimeChunk: {
        name:'commons/manifest'
    }
}
```

#### 6) flex布局
[http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html](http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html)

#### 7) better-scroll列表联动


## 8. 参考资源
> [http://www.jianshu.com/p/9724c47b406c](http://www.jianshu.com/p/9724c47b406c)
> <br/>
> [http://www.jianshu.com/p/2cc6a22c9ecc](http://www.jianshu.com/p/2cc6a22c9ecc)
> [webpack教程资源收集](https://github.com/kraaas/webpack-tutorial-collection)
> <br/>
>

## 9. Changelog

### 1.0.1

_新特性：_

* 使用VUE做模块化开发

_Bug修复：_

* 优化首页

### 1.0.0

_新特性：_

* 增加首页
* 增加课程列表页
* 增加作品列表页
* 增加博文列表页
* 增加收藏列表页
* 增加关于我们页
* 增加文章列表页
* 增加文章内容页
* 增加登录、注册功能

_Bug修复：_

* 无
