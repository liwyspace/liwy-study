/**
 *  webpack4与jquery3存在__WEBPACK_AMD_DEFINE_ARRAY__ is not defined问题
 */

const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin"); //webpack4用来代替ExtractTextPlugin
const CopyWebpackPlugin = require('copy-webpack-plugin');

const htmlModels = [
    "index",       // 首页
    "course",       // 课程列表页
    "works",        // 作品列表页
    "blog",         // 博文列表页
    "collection",   // 收藏列表页
    "about",        // 关于我们页
    // "catalogue",    // 目录页
    // "article",      // 文章页
    // "login",        // 登录页
    // "register"      // 注册页
];

let entrys = {};
let plugins = [
    new CopyWebpackPlugin([
        { from: 'src/commons/scripts/vendors', to: 'scripts/vendors' }
    ]),
    //当webpack加载到某个js模块里，出现了未定义且名称符合（字符串完全匹配）配置中key的变量时，会自动require配置中value所指定的js模块。
    new webpack.ProvidePlugin({
        $: 'jquery',
        jQuery: 'jquery',
        'window.jQuery': 'jquery',
        'window.$': 'jquery'
    }),
    // 替代webpack3中的ExtractTextPlugin插件
    new MiniCssExtractPlugin({
        filename: "styles/[name].[contenthash:8].css",
        chunkFilename: "styles/[id].[contenthash:8].css"
    })
];

htmlModels.forEach(model => {
    entrys[model] = [path.resolve(__dirname, `src/${model}/scripts/${model}.js`)];
    plugins.push(new HtmlWebpackPlugin({
        favicon: path.resolve(__dirname, `src/commons/images/favicon.ico`),
        template: path.resolve(__dirname, `src/${model}/${model}.html`),
        filename: path.resolve(__dirname, `dist/${model}.html`),
        chunks: ['commons/manifest','commons/vendors','commons/commons',model] //引入的chunks
    }));
});

module.exports = {
    entry: entrys,
    output: {
        path: path.resolve(__dirname, 'dist/'),
        publicPath: '/',
        filename: 'scripts/[name].[contenthash:8].js'
    },
    resolve: {
        alias: {//设置别名，可以用来替换模块所引用的文件
            // 'jquery':'window.jQuery' //当在页面<script>引入jquery时，可通过此方法引用全局的jQuery
            '@': path.resolve(__dirname, 'src/')
        }
    },
    externals: {
        jquery: "jQuery",
        swiper: 'Swiper'
    },
    module: {
        rules: [{
            test: /\.(htm|html)$/,
            use: ['html-withimg-loader?min=false', {
                loader: 'string-replace-loader',
                options: {
                    search: '/commons/scripts/vendors/',
                    replace: '/scripts/vendors/',
                    flags: 'g'
                }
            }]
        }, {
            test: /\.(css|scss)$/,
            use: [MiniCssExtractPlugin.loader, 'css-loader', {
                loader: 'postcss-loader',
                options: {
                    plugins: [require('autoprefixer')({
                        browsers: [
                            "> 1%",
                            "last 2 versions",
                            "Firefox 15"
                        ]
                    })]
                }
            },{
                loader:'sass-loader',
                options:{outputStyle:'expanded'}
            }]
        }, {
            test: /\.js$/,
            use: ['babel-loader', 'eslint-loader'],
            include: [path.resolve(__dirname, 'src'), path.resolve(__dirname, 'test')]
        }, {
            test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
            loader: 'url-loader',
            options: {
              limit: 1000,
              // publicPath: path.resolve(__dirname, 'dist/'),
              // outputPath: '/images/',
              name: 'images/[name].[hash:8].[ext]'
            }
        }, {
            test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
            loader: 'url-loader',
            options: {
              limit: 10000,
              name: 'media/[name].[hash:8].[ext]'
            }
        }, {
            test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
            loader: 'url-loader',
            options: {
              limit: 10000,
              name: 'fonts/[name].[hash:8].[ext]'
            }
        }, { //通过expose-loader暴露全局变量，使得页面引入的插件也可以获取到jquery
            test: require.resolve('jquery'),
            use: [{
              loader: 'expose-loader',
              options: 'jQuery'
            },{
              loader: 'expose-loader',
              options: '$'
            }]
        }]
    },
    plugins: plugins,
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
};
