const merge = require('webpack-merge');

const common = require('./webpack.common.js');


module.exports = merge(common, {
    mode: 'development',
    devtool: 'inline-source-map',
    devServer: {
        port: 51265,
        contentBase: common.output.path,
        overlay: {
            errors: true,
            warnings: true
        },
        historyApiFallback: true,
        inline: true, // iframe or inline script
        host: '0.0.0.0',
    },
});
