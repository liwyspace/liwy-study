//Node 全局变量：
//__filename：指向当前运行的脚本文件名。
//__dirname：指向当前运行的脚本所在的目录。

//通过requier导入其他库
//Node Path模块常用函数：
//path.resolve 返回绝对路径
//path.join用于连接路径

const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const webpack = require('webpack');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: {
        index: path.resolve(__dirname, 'src/scripts/index.js'),
        about: path.resolve(__dirname, 'src/scripts/about.js'),
        app: path.resolve(__dirname, 'src/scripts/app.js'),
        vendor: ['lodash','jquery','vue']
    },
    output: {
        path: path.resolve(__dirname, 'dist/'),
        publicPath: '/',
        filename: 'scripts/[name].[chunkhash:8].js'
    },
    resolve: {
        alias: {//设置别名，可以用来替换模块所引用的文件
            'vue$': 'vue/dist/vue.esm.js',
            // 'jquery':'window.jQuery' //当在页面<script>引入jquery时，可通过此方法引用全局的jQuery
        },
        extensions: ['.js', '.vue', '.json']
    },
    // 允许指定库的依赖项，这些依赖项不会被webpack解析，而是成为输出的依赖关系。
    // 这意味着它们在运行时从环境中导入。
    // externals: {
    //  'jquery': 'jQuery'
    // },
    module: {
        rules: [
            {
                test: /\.css$/,
                // exclude: /node_modules/,
                use: ExtractTextPlugin.extract({//从一个已存在的 loader 中，创建一个提取(extract) loader。
                    fallback: 'style-loader',//loader（例如 'style-loader'）应用于当 CSS 没有被提取(也就是一个额外的 chunk，当 allChunks: false)
                    use: [
                    'css-loader',
                    {
                        loader: 'autoprefixer-loader',
                        options: {browsers:["last 2 version", "Firefox 15"]}
                    }]
                })
            },
            {
                test: /\.scss$/,
                use: ExtractTextPlugin.extract({
                    fallback: 'style-loader', //通过<style>标签嵌入到Dom文档中
                    use: [
                    'css-loader',
                    {
                        loader: 'autoprefixer-loader',
                        options: {browsers:["last 2 version", "Firefox 15"]}
                    },
                    {
                        loader:'sass-loader',
                        options:{outputStyle:'expanded'}
                    }]
                })
            },
            {
                test: /\.(js|vue)$/,
                loader: 'eslint-loader',
                enforce: 'pre',
                // 指定include,避免对第三方模块进行babel解析,以影响效率
                include: [path.resolve(__dirname, 'src'), path.resolve(__dirname, 'test')],
                options: {
                    formatter: require('eslint-friendly-formatter')
                }
            },
            {
                test: /\.js$/,
                use: ['babel-loader'],
                // 指定include,避免对第三方模块进行babel解析,以影响效率
                include: [path.resolve(__dirname, 'src'), path.resolve(__dirname, 'test')]
            },
            {
                test: /\.vue$/,
                use: ['vue-loader']
            },
            {
                test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                  limit: 1000,
                  // publicPath: path.resolve(__dirname, 'dist/'),
                  // outputPath: '/images/',
                  name: 'images/[name].[hash:8].[ext]'
                }
            },
            {
                test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                  limit: 10000,
                  name: 'media/[name].[hash:8].[ext]'
                }
            },
            {
                test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                  limit: 10000,
                  name: 'fonts/[name].[hash:8].[ext]'
                }
            },
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
    },
    plugins: [
        // new CleanWebpackPlugin(['dist']), //清理dist目录
        new HtmlWebpackPlugin({ //生成html文件
            template: path.resolve(__dirname, 'src/index.html'),
            filename: path.resolve(__dirname, 'dist/index.html'),
            chunks: ['manifest','vendor','commons','index'] //引入的chunks
        }),
        new HtmlWebpackPlugin({
            // template: path.resolve(__dirname, 'src/index2.html'),
            filename: path.resolve(__dirname, 'dist/about.html'),
            chunks: ['manifest','vendor','commons','about']
        }),
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, 'src/app.html'),
            filename: path.resolve(__dirname, 'dist/app.html'),
            chunks: ['manifest','commons','vendor','app'],
            chunksSortMode: 'manual' //设置手动排序
        }),
        //当webpack加载到某个js模块里，出现了未定义且名称符合（字符串完全匹配）配置中key的变量时，会自动require配置中value所指定的js模块。
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery',
            'window.jQuery': 'jquery',
            'window.$': 'jquery'
        }),
        new webpack.optimize.CommonsChunkPlugin({ //分离公共模块与第三方模块
            names: ['commons','vendor'],
            filename: 'scripts/commons/[name].[chunkhash:8].js',
            minChunks: 2
        }),
        new webpack.optimize.CommonsChunkPlugin({ //分离运行时的模块防止第三方模块hash值变动
            name:'manifest',
            // filename: 'scripts/commons/[name].[chunkhash:8].js',//启用热部署时，manifest不能使用chunkhash
            filename: 'scripts/commons/[name].[hash:8].js',
            minChunks: Infinity
        }),
        new ExtractTextPlugin({
            filename: 'styles/[name].[contenthash:8].css',
            // 从所有额外的 chunk(additional chunk) 提取（默认情况下，它仅从初始chunk(initial chunk) 中提取）。
            // 当使用 CommonsChunkPlugin 并且在公共 chunk 中有需要提取的 chunk（来自ExtractTextPlugin.extract）时，allChunks **必须设置为 true
            // 也可以在extract方法中设置fallback
            allChunks: true
        }), //从chunk中提取文本到单独的文件中。
    ]

};
