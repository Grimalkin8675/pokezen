const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const autoprefixer = require('autoprefixer');


module.exports = {
    entry: path.resolve(__dirname, 'app/index.tsx'),
    output: {
        path: path.resolve(__dirname, 'dist/'),
        filename: 'bundle.js',
    },
    resolve: {
        extensions: ['.ts', '.tsx', '.js'],
    },
    module: {
        rules: [
            // ts
            {
                test: /\.tsx?$/,
                use: 'awesome-typescript-loader',
            },
            // css
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader',
                    'postcss-loader',
                ]
            },
        ],
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, 'templates/index.html'),
            inject: false,
        }),
        autoprefixer,
    ],
}
