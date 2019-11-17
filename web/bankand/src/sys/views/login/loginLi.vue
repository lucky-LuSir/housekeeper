<!--
<template>
    <div class="root">
        <div class="div-a">
            <el-form :model="loginObj" :rules="rules" ref="loginObj">
                <div id="login-top">
                    <div id="top">
                        <p id="welcome">欢迎使用</p>
                        <p id="glxt"><span>HKPS</span>库房管家后台管理系统</p>
                    </div>
                </div>
                <div id="login-middle">
                    <div class="login-box-root-div">
                        <div class="grid-content">
                            <ul class="login-ul">
                                <li class="username-pwd-icon">
                                    <img style="margin-top: 5px;" src="./img/usericon.png"/>
                                </li>
                                <li class="username-pwd-font">
                                    <span style="line-height: 20px;font-size: 15px;font-weight: bold">手机号：</span>
                                </li>
                            </ul>
                            <el-form-item prop="userPhone" style="width: 300px;margin-left: 38px">
                                <el-input placeholder="请输入登录手机号" v-model="loginObj.userPhone" class="input-text"
                                          clearable>
                                </el-input>
                            </el-form-item>
                            <ul class="login-ul-pwd">
                                <li class="username-pwd-icon">
                                    <img style="margin-top: 7px;" src="./img/pwdicon.png"/>
                                </li>
                                <li class="username-pwd-font">
                                    <span style="line-height: 30px;font-size: 15px;font-weight: bold">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
                                </li>
                            </ul>
                            <el-form-item prop="password" style="width: 300px;margin-left: 38px">
                                <el-input @keyup.enter.native="loginSys('loginObj')" placeholder="请输入密码"
                                          v-model="secure" type="password" class="input-text" clearable>
                                </el-input>
                            </el-form-item>
                        </div>
                    </div>
                </div>
                <div id="login-bottom">
                    <el-button style="width:380px;height:138px;font-size: 30px" type="success" @click="loginSys('loginObj')">
                        现在登录
                    </el-button>
                </div>
            </el-form>
        </div>
        <div class="div-b"><p style="text-align: center;font-size: 60px;padding-top: 400px;color: white">合作共赢，共享未来</p>
        </div>

    </div>
</template>
<script>
    import hkpMainPage from '../../../common/views/hkpMainPage'
    export default {
        created: function () {
            //登陆状态下不能访问login页面
            if (this.$cookieStore.getCookie("gn_request_token")) {
                this.toHkpMainPage();
            }
        },
        data() {
            return {
                secure: "",
                userObj: {},
                loginObj: {
                    cpyName: "",
                    workNumber: "",
                    userCode:"",
                    roleCode:"",
                    secure:"",
                    password: ""
                },
                rules: {
                    cpyName: [
                        {required: true, message: '请输入公司名', trigger: 'blur'}
                    ],
                    workNumber: [
                        {required: true, message: '请输入工号', trigger: 'blur'}
                    ],
                    password: [
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

            toUserList() {
                this.$router.push({
                    name: "test",
                })
            },

            //登陆
            loginSys(formName) {
                //全局页面信息
                let vm = {};
                vm = this;
                let obj = {};
                vm.loginObj.password = this.$md5(vm.secure);
                vm.loginObj.secure = vm.secure;
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
                                    vm.userObj.userName = response.data.result.userName;
                                    vm.userObj.userCode = response.data.result.userCode;
                                    //vm.userObj.roleCode = response.data.result.roleCode;
                                    // vm.userObj.empCode = response.data.result.empCode;

                                    //获取后台返回的token
                                    let token = response.data.result.token;

                                    //将token存入Cookie中
                                    vm.$cookieStore.addCookie("gn_request_token", JSON.stringify(token), 0);
                                    //let tok = vm.$cookieStore.getCookie("gn_request_token");
                                    //将userName，userCode，postCode,empCode存入cookie
                                    vm.$cookieStore.addCookie("userName", vm.userObj.userName, 0);
                                    vm.$cookieStore.addCookie("userCode", vm.userObj.userCode, 0);
                                    //vm.$cookieStore.addCookie("postCode", vm.userObj.postCode, 0);
                                    // vm.$cookieStore.addCookie("empCode", vm.userObj.empCode, 0);

                                    let menuTreeList = response.data.result.menuTreeList;
                                    let menuListStr = JSON.stringify(menuTreeList); //转化为JSON字符串
                                    localStorage.setItem("menuTreeList", menuListStr);//返回{"a":1,"b":2}

                                    //登陆成功跳转到首页
                                    vm.toHkpMainPage();
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
        },
    }

</script>

<style scoped>
    .div-a {
        float: left;
        width: 20%;
        height: 100%;
        /*     border: 1px solid #F00;*/
        display: inline-block
    }

    #welcome {
        font-weight: bolder;
    }

    #top {
        padd-top: 250px;
    }

    /*登录顶端div*/
    .div-a #login-top {
        height: 500px;
        /* border: 1px solid #F00;*/
        color: white;
        text-align: left;
        padding-top: 150px;
        padding-left: 30px;
        line-height: 60px;
        font-size: 25px;
        /*background-color: rgba(0, 0, 0, 0.8);*/
    }

    .div-a #login-middle {
        height: 30%;
        /*border: 1px solid #F00;*/
        /*  background-color: rgba(0, 0, 0, 0.8);*/
    }

    .div-a #login-bottom {
        height: 10%;
    }

    /*hkps*/
    .div-a #login-top span {
        font-size: 35px;
    }

    .div-b {
        float: left;
        width: 80%;
        height: 100%;
        /* border: 1px solid #000;*/
        display: inline-block;

    }

    .root {
        text-align: center;
        background-size: cover;
        position: absolute;
        top: 0px;
        bottom: 0px;
        left: 0px;
        width: 100%;
        height: 100%;
        background-image: url("./img/登录bg.png");
        background-size: cover;
    }

    .login-box-root-div {
        width: 380px;
        height: 270px;
        text-align: center;
        margin-left: 0px;
        margin-top: 0px;
    }

    .grid-content {
        margin-top: 25px;
        padding-top: 20px;
    }

    /*用户名*/
    .login-ul-pwd {
        margin-top: 0px;
    }

    /*用户名 密码 图标 li*/
    .username-pwd-icon {
        height: 30px;
        width: 20px;
        margin-left: 50px;
        list-style: none;
        float: left;
    }

    /*用户名 密码 文字 li*/
    .username-pwd-font {
        height: 30px;
        width: 80px;
        list-style: none;
        float: left;
        color: white;
    }

    /*输入框*/
    .input-text {
        width: 300px;
        margin-top: 5px;
        margin-left: 10px;
    }
</style>-->
