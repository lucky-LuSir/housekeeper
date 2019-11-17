// 在vue-config.js 中加入
const CompressionWebpackPlugin = require("compression-webpack-plugin");
const isProduction = process.env.NODE_ENV === "production";
// 作为配置文件，直接导出配置对象即可

module.exports = {
    publicPath: isProduction ? "./" : "/",
    outputDir: "hkpweb",
    devServer: {
        disableHostCheck: true,
        port: 8082,
        proxy: { // 配置跨域
            '/api': {
                //要访问的跨域的api的域名
                target: 'http://localhost:6789/housekeeper/',
                changOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        },
    },
    css: {
        // 是否使用css分离插件 ExtractTextPlugin
        extract: isProduction ? true : false,
        // 开启 CSS source maps?
        sourceMap: true,
        // sourceMap: false,
        // css预设器配置项
        // 启用 CSS modules for all css / pre-processor files.
        modules: false
    },
    lintOnSave: true, // default false
    // 打包时不生成.map文件
    productionSourceMap: false
};
