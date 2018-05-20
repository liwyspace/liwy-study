# LIWY-WEBAPP 前端工具综合应用
> Author: LIWY
> <br/>
> WebSet: [www.oscafe.net](http://www.oscafe.net)
> <br/>
> GitHub: [https://github.com/liwyspace](https://github.com/liwyspace)

该项目用于练习测试前端自动化构建打包与组件开发测试的应用。
通过该项目熟悉前端工程化开发的流程与技术。
我们将一步一步的学习下面技术，以至于熟练掌握。

***

版本|更新时间|作者
---|:---:|---
v0.0.1|2017-11-24|LIWY
v0.0.2|2017-11-25|李文姚

## 技术栈
- yeoman
- bower
- gulp
- webpack
- karma
- jasemine
- vue
- vue router
- vue resource
- sass
- babel
- eslint
- mockjs

## 部署项目
1. 下载项目到本地：`git clone git@github.com:liwyspace/gulptest.git`
2. 进入项目目录：`cd gulptest`
3. 安装npm包：`npm install`
4. 安装bower包：`bower install`

## 启动
```bash
# 单独编译webpack-开发模式
npm run webpack-dev
# 单独启动webpack服务-提供热部署
npm run webpack-dev-server
# 单独编译webpack-生产模式
webpack-prod

# 开发模式-编译
dev
# 开发模式-启动服务器
dev-server
# 生产模式-编译
prod
# 生产模式-启动服务器
prod-server
```


## 备忘命令
**Git常用命令**
```bash
# 生成sshkey
ssh-keygen -t rsa -C "liwenyao_java@126.com"
#初始化git项目
git init
# 查看git项目状态
git status
# 添加指定文件
git add file_name
# 添加所有未添加文件
git add -A
# 本地提交
git commit -m "this is message"
# 上传至远程库
git push
# 向github提交子分支，gh-pages分支用于创建github站点
git subtree push --prefix=docs/dist origin gh-pages  
# 更新项目
git pull
```

## 项目总结

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
```

#### 3) 创建能够解析mockjs的服务器
```js
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
```

data.json
```json
{
    "/api/seller": {
        "name": "粥品香坊（回龙观）",
        "description": "蜂鸟专送",
        "deliveryTime": 38,
        "score": 4.2,
        "supports": [
            {
                "type": 0,
                "description": "在线支付满28减5"
            },
            {
                "type": 1,
                "description": "VC无限橙果汁全场8折"
            },
            {
                "type": 2,
                "description": "单人精彩套餐"
            },
            {
                "type": 3,
                "description": "该商家支持发票,请下单写好发票抬头"
            }
        ]
    }
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

#### 5) flex布局
[http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html](http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html)

#### 6) better-scroll列表联动


## 参考资源
> [http://www.jianshu.com/p/9724c47b406c](http://www.jianshu.com/p/9724c47b406c)
> <br/>
> [http://www.jianshu.com/p/2cc6a22c9ecc](http://www.jianshu.com/p/2cc6a22c9ecc)
> [webpack教程资源收集](https://github.com/kraaas/webpack-tutorial-collection)
> <br/>
>
