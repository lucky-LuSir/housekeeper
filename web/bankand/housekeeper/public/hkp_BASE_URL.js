
let BASE_URL = ""; //这里是一个默认的url，可以没有

if (process.env.NODE_ENV === "production") {
    // console.log("设置生产环境api接口url");
    //BASE_URL = "http://hkp.kufangwuyou.com/housekeeper/"; //生产环境url
     BASE_URL = "http://test.hkp.kufangwuyou.com/housekeeper/"; //测试环境url
} else {
    //dev 开发环境
    // console.log("开发环境api接口url");
    BASE_URL = "/api"; //开发环境url
}
export default { BASE_URL };


