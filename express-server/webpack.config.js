const path = require("path")
const nodeExternals = require('webpack-node-externals')
const webpack = require('webpack')

let mode = "development"
let devtool = "inline-source-map"

module.exports = env => {
    return {
        target: "node",
        node: {
            // Need this when working with express, otherwise the build fails
            __dirname: false,   // if you don't put this is, __dirname
            __filename: false,  // and __filename return blank or /
        },
        entry: ['babel-polyfill', './src/index.ts'],
        mode,
        devtool,
        externals: [nodeExternals()],
        output: {
            path: path.join(__dirname, 'dist'),
            publicPath: '/',
            filename: '[name].js'
        },
        module: {
            rules: [
                {
                    test: [/\.js$/],
                    exclude: /(node_modules)/,
                    use: {
                        loader: "babel-loader",
                        options: {
                            presets: [
                                "@babel/preset-env"
                            ]
                        }
                    }
                },
                { test: /\.ts$/, loader: "ts-loader" }
            ]
        },
        resolve: {
            extensions: ['.ts', '.tsx', '.js']
        },
        plugins: [
            new webpack.ProgressPlugin(),
        ]
    }
}

