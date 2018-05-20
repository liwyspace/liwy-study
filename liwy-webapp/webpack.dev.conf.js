const merge = require('webpack-merge');//合并webpack配置文件模块
const common = require('./webpack.base.conf.js');
const webpack = require('webpack');
const ManifestPlugin = require('webpack-manifest-plugin');

module.exports = merge(common, {
    devtool: 'inline-source-map', //source map，方便错误追踪
    // 交由gulp配置
    // devServer: { //配置web服务器
    //     contentBase:['./dist'],
    //     inline: true,
    //     open: true,
    //     openPage: 'app.html',
    //     hot: true,
    //     stats: { //准确地控制显示哪些包的信息
    //         colors: true, //打印信息颜色
    //         reasons: true // 增加模块被引入的原因
    //     },
    // },
    plugins: [
        new ManifestPlugin(), //生成manifest资源映射文件
        //热部署
        //当开启 HMR 的时候使用该插件会显示模块的相对路径,建议用于开发环境
        new webpack.NamedModulesPlugin(),
        new webpack.HotModuleReplacementPlugin()
    ]
});
