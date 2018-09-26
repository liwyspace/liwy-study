const merge = require('webpack-merge');//合并webpack配置文件模块
const common = require('./webpack.base.conf.js');
const CleanWebpackPlugin = require('clean-webpack-plugin');
// const webpack = require('webpack');

module.exports = merge(common, {
    mode: 'development',
    // 交由gulp配置
    devServer: { //配置web服务器
        contentBase:['./dist'],
        inline: true,
        // hot: true,
        open: true,
        openPage: 'index.html',
        stats: { //准确地控制显示哪些包的信息
            colors: true, //打印信息颜色
            reasons: true // 增加模块被引入的原因
        },
    },
    plugins: [
        new CleanWebpackPlugin(['dist']),
        // new webpack.NamedModulesPlugin(),
        // new webpack.HotModuleReplacementPlugin()
    ]
});
