const merge = require('webpack-merge');
const common = require('./webpack.base.conf.js');
const UglifyJSPlugin = require('uglifyjs-webpack-plugin');
const webpack = require('webpack');

module.exports = merge(common,{
  plugins:[
  	new UglifyJSPlugin({ //压缩js
      sourceMap: true
    }),
    new webpack.HashedModuleIdsPlugin(),//该插件会根据模块的相对路径生成一个四位数的hash作为模块id, 建议用于生产环境
    new webpack.DefinePlugin({ //设置环境变量NODE_ENV为production即生成环境标志
      'process.env': {
        'NODE_ENV':JSON.stringify('production')
      }
    })
  ]
});