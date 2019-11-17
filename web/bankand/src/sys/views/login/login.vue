<template slot-scope="scope">
    <div class="root">

        <div class="login_left">
            <div class="login_title">
                <el-header class="logion_header" style="height: 100%;">
                    <div id="top">
                        <p id="welcome">欢&nbsp;迎&nbsp;使&nbsp;用</p>
                        <p id="glxt"><a id="hkps">H&nbsp;K&nbsp;P&nbsp;S&nbsp;</a><a id="system_name">库房管家后台管理系统</a></p>
                    </div>
                </el-header>
            </div>
            <div class="login_main">
                <el-main>
                    <el-form :model="loginObj" :rules="rules" ref="loginObj">
                        <div class="grid-content">
                            <el-form-item class="el_userPhone" prop="userPhone">
                                <el-input placeholder="请输入手机号" v-model="loginObj.userPhone" class="input-text"
                                          clearable>
                                </el-input>
                            </el-form-item>
                            <el-form-item class="el_password" prop="password">
                                <el-input @keyup.enter.native="loginSys('loginObj')" placeholder="请输入密码"
                                          v-model="loginObj.password" type="password" class="input-text" clearable>
                                </el-input>
                            </el-form-item>

                        </div>
                    </el-form>
                </el-main>
                <el-footer class="login_click" style="height: 140px;width: 450px">
                    <button class="button_login" @click="loginSys('loginObj')" type="primary">现&nbsp;在&nbsp;登&nbsp;录
                    </button>
                </el-footer>
            </div>


        </div>
        <div class="login_right">
            <div class="aside_slogan">
                <div class="slogan">
                    <p id="aside">合&nbsp;作&nbsp;共&nbsp;赢 · 共&nbsp;享&nbsp;未&nbsp;来</p>
                </div>
            </div>

        </div>

    </div>
</template>

<script>

    export default {
        created: function () {

        },
        mounted:function(){
            //登陆状态下不能访问login页面
            let sk = this.$utils.getUrlKey("_ssokey");
            if (this.$utils.isNotEmpty(sk)) {

                let vm = {};
                vm = this;

                let options = {
                    method: "POST",
                    data: {
                        ssoKey:sk
                    },
                    url: "user/ssoLogin",
                };
                this.$ajax(options).then(function (response) {
                    if (response) {
                        //获取用户信息
                        vm.loginSuccess(vm,response);
                    }
                }).catch(function (error) {
                    vm.$alert('页面：登录方法异常', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            }
        },
        data() {
            return {
                secure: "",
                userObj: {

                },
                loginObj: {
                    userPhone: "",
                    password: "",
                    secure: ""
                },
                rules: {
                    userPhone: [
                        {required: true, message: '请输入手机号', trigger: 'blur'}
                    ],
                    secure: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            //忘记密码
            forgetPwd() {
                this.$router.push({
                    name: "forgetPwd"
                })
            },
            //跳转到主页
            toHkpMainPage() {
                this.$router.push({
                    name: "hkpMainPage",
                })
            },
            //登陆
            loginSys(formName) {
                let vm = {};
                vm = this;
                let obj = {};
                vm.loginObj.secure = this.$md5(vm.loginObj.password);
                obj.entity = vm.loginObj;
                if (vm.loginObj) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            let options = {
                                method: "POST",
                                data: obj,
                                url: "user/login",
                            };
                            this.$ajax(options).then(function (response) {
                                if (response) {
                                    //获取用户信息
                                    vm.loginSuccess(vm,response);
                                }
                            }).catch(function (error) {
                                vm.$alert('页面：登录方法异常', '系统异常', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                    }
                                });
                            });
                        }
                    });
                }
            },
            loginSuccess(vm,response){
                vm.userObj.userName = response.data.result.userName;
                vm.userObj.userCode = response.data.result.userCode;
                vm.userObj.roleCode = response.data.result.roleCode;
                vm.userObj.userLevel = response.data.result.userLevel;
                vm.userObj.entryType = response.data.result.entryType;
                if (response.data.result.ownerDeptName != null) {
                    vm.userObj.ownerDeptName = response.data.result.ownerDeptName;
                    vm.$cookieStore.addCookie("ownerDeptName", vm.userObj.ownerDeptName, 0);
                }


                //获取后台返回的token
                let token = response.data.result.token;
                //将token存入Cookie中
                localStorage.setItem("gn_request_token", JSON.stringify(token), 0);
                vm.$cookieStore.addCookie("userName", vm.userObj.userName, 0);
                vm.$cookieStore.addCookie("userCode", vm.userObj.userCode, 0);
                vm.$cookieStore.addCookie("userLevel", vm.userObj.userLevel, 0);
                vm.$cookieStore.addCookie("entryType",vm.userObj.entryType,0);
                let menuTreeList = response.data.result.menuTreeList;
                let judgeMenuTreeList = response.data.result.judgeMenuTreeList;
                //转化为JSON字符串
                let menuListStr = JSON.stringify(menuTreeList);
                let judgeMenuTreeListStr = JSON.stringify(judgeMenuTreeList);
                //返回{"a":1,"b":2}
                localStorage.setItem("menuTreeList", menuListStr);
                localStorage.setItem("judgeMenuTreeList", judgeMenuTreeListStr);
                //登陆成功跳转到首页
                vm.toHkpMainPage();
            }
        },
    }

</script>

<style scoped>
    .root {
        text-align: center;
        background-size: cover;
        position: absolute;
        top: 0px;
        bottom: 0px;
        left: 0px;
        width: 100%;
        height: 100%;
        background-image: url("./img/login.jpg");
    }

    .el-header, .el-footer {
        background-color: rgba(0, 98, 196, 0.5);
        color: #333;
        text-align: center;
        line-height: 60px;
        padding-left: 0px;
    }

    .el-main {
        background-color: rgba(0, 0, 0, 0.1);
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    /*登录点击效果*/
    .button_login {
        cursor: pointer;
        line-height: 110px;
        width: 450px;
        height: 140px;
        text-align: center;
        font-weight: bolder;
        font-size: 30px;
        color: white;
        background-color: #4CAF50; /* Green */
        border: none;
        text-decoration: none;
        display: inline-block;
        background-color: rgba(0, 98, 196, 0.3);
    }

    .button_login:hover {
        background-color: rgba(0, 98, 196, 0.8);
    }

    .button_login:active {
        background-color: rgba(0, 98, 196, 0.5);
        box-shadow: 0 5px #666;
        transform: translateY(4px);
    }

    .login_left {
        position: absolute;
        width: 450px;
        height: 100%;
    }

    .login_title {
        width: 450px;
        position: absolute;
        top: 0;
        bottom: 364px
    }

    .logion_header {
        position: relative;
        min-height: 200px
    }

    .login_right {
        position: absolute;
        border: 2px;
        left: 450px;
        height: 100%;
        right: 0;
        background-color: rgba(0, 0, 0, 0.25);
    }

    .aside_slogan {
        position: relative;
        width: 100%;
        height: 100%;
    }

    .slogan {
        position: absolute;
        width: 700px;
        height: 150px;
        margin: auto;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
    }

    .login_main {
        width: 450px;
        bottom: 0;
        position: fixed;
    }

    #top {
        padding-top: 250px;
        font-size: 35px;
        color: white;
        position: absolute;
        margin-left: 35px;
        right: 0;
    }

    #aside {
        cursor: default;
        font-weight: bolder;
        font-size: 55px;
        color: white;
    }

    #welcome {
        cursor: default;
        font-weight: bolder;
        font-size: 40px;
        float: left;
    }

    #glxt {
        margin: 20px 0px 0px 0px;
        font-weight: bolder;
        font-size: 20px;
        color: white;
        float: left;
    }

    #hkps {
        cursor: default;
        font-size: 32px;
        color: white;
    }

    #system_name {
        cursor: default;
        font-size: 16px;
        color: white;

    }

    /*login*/
    .grid-content {
        margin-top: 0px;
        padding-top: 50px;
    }

    /*输入框*/
    .input-text {
        width: 300px;
        margin-top: 5px;
    }

</style>
