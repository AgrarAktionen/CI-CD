const { resolve } = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const { CleanWebpackPlugin } = require('clean-webpack-plugin')
const webpack = require('webpack')
const commandLineArgs = require("command-line-args")

const pkg = require('./package.json')

// parse the command line of the npm build command:
const options = commandLineArgs([
    { name: "application-server", alias: 'a', type: String },
    { name: "debug", alias: 'd', type: String, defaultValue: false }
])
const IS_DEV_SERVER = !!process.argv.find(arg => arg.includes('webpack-dev-server'))
const isDebug = IS_DEV_SERVER || options["debug"]
const ENV = isDebug ? "development" : "production"

const APPLICATION_SERVER = options["application-server"]
const shopEnv = {
    NODE_ENV: JSON.stringify(ENV),
    appVersion: JSON.stringify(pkg.version),
    APPLICATION_SERVER: JSON.stringify(APPLICATION_SERVER),
}
const OUTPUT_PATH = IS_DEV_SERVER ? resolve(__dirname, ".") : resolve('./dist/')
console.log("processEnv=", shopEnv)
console.log("options=", options, "isDevServer", IS_DEV_SERVER, "output_path=", OUTPUT_PATH, "appication server=", APPLICATION_SERVER)

const entryPoints = [
    { chunk: "main", entry: "index.html", src: "view/app.js" }
]
const entry = {}
entryPoints.forEach(ep => {
    entry[ep.chunk] = "./src/" + ep.src
})

const htmlWebpackPlugins = entryPoints.map(ep =>
    new HtmlWebpackPlugin({
        compile: false,
        chunks: [ep.chunk],
        template: `!!ejs-webpack-loader!${resolve("./" + ep.entry)}`,
        filename: ep.entry
    })
)

const plugins = [
    ...htmlWebpackPlugins,
    new MiniCssExtractPlugin({
        filename: '[name]-[contenthash].css',
        chunkFilename: '/[id].css',
    }),
    new CleanWebpackPlugin({ verbose: true }),
    new webpack.DefinePlugin({ 'process.env': shopEnv })
]
module.exports = {
    mode: ENV,
    entry,
    plugins,
    output: {
        path: resolve(__dirname, 'dist'),
        filename: "[name]-[hash].js",
        chunkFilename: '[name]-[hash].bundle.js',
        publicPath: "/"
    },
    module: {
        rules: [
            {
                test: /\.html$/,
                use: [
                    "html-loader",
                ]
            },
            {
                test: /\.scss$/i,
                use: [
                    MiniCssExtractPlugin.loader,
                    //isDebug ? "style-loader" : MiniCssExtractPlugin.loader,
                    //"style-loader",
                    "css-loader",
                    "sass-loader"
                ]
            },
            {
                test: /\.css$/i,
                use: [
                    "style-loader",
                    {
                        loader: "css-loader",
                        options: {
                            modules: true,
                            sourceMap: true,
                            importLoader: 2
                        }
                    },
                    "sass-loader"
                ]
            },
            {
                test: /\.js$/,
                //exclude: /(node_modules)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [[
                            '@babel/preset-env',
                            {
                                targets: {
                                    browsers: [
                                        // Best practice: https://github.com/babel/babel/issues/7789
                                        '>=1%',
                                        'not ie 11',
                                        'not op_mini all'
                                    ]
                                },
                                debug: false
                            }
                        ]],
                        plugins: [
                            ['@babel/plugin-syntax-object-rest-spread', { useBuiltIns: true }],
                            "@babel/plugin-proposal-private-methods",
                            "@babel/plugin-proposal-class-properties"
                        ]
                    }
                }
            },
            {
                test: /\.(png|jpg|gif)$/i,
                use: [
                    {
                        loader: 'url-loader',
                        options: {
                            limit: 8192,
                            name: "img/[name]_[hash].[ext]"
                        }
                    }
                ]
            },
            {
                test: /\.(woff(2)?|ttf|eot|svg)(\?v=\d+\.\d+\.\d+)?$/,
                use: [
                    {
                        loader: 'file-loader',
                        options: {
                            name: '[name].[ext]',
                            outputPath: 'fonts/'
                        }
                    }
                ]
            },
        ]
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
        alias: {
            Rest: resolve(__dirname, 'src/rest/'),
            Lib: resolve(__dirname, 'src/lib/'),
            Model: resolve(__dirname, 'src/model/'),
            Styles: resolve(__dirname, 'src/styles/')
        }
    },
    devtool: 'cheap-source-map',
    devServer: {
        contentBase: OUTPUT_PATH,
        compress: false,
        overlay: {
            errors: true
        },
        hot: true,
        port: 4000,
        host: '0.0.0.0',
        disableHostCheck: true,

        historyApiFallback: {         
            verbose: true
        },
        watchOptions: {
            ignored: ['node_modules']
        },
        proxy: {
            '/api/': {
                target: 'http://localhost:8080/',
                ws: true
            }
        }
    }
}
