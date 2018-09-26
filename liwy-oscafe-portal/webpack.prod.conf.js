const merge = require('webpack-merge');
const common = require('./webpack.base.conf.js');
const optimizeCss = require('optimize-css-assets-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');

module.exports = merge(common,{
    mode: 'production',
    plugins:[
        new CleanWebpackPlugin(['dist']),
        new optimizeCss({
            assetNameRegExp: /\.css$/g,
            cssProcessor: require('cssnano'),
            cssProcessorOptions: { discardComments: { removeAll: true } },
            canPrint: true
        })
    ]
});
