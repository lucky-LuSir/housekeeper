<template>
    <div class="root">
        <el-form>
            <div class="welcome-title-div">
                <p class="welcome-title">欢迎使用智慧园区SAAS系统</p>
            </div>

            <div class="login-box-root-div">
                <div class="grid-content">
                    <ul class="login-ul">
                        <li class="username-pwd-icon">
                            <img style="margin-top: 5px;" src="./img/u62.png"/>
                        </li>
                        <li class="username-pwd-font">
                            <span style="line-height: 30px;">用户名：</span>
                        </li>
                    </ul>
                    <el-form-item>
                        <el-input placeholder="请输入用户名" class="input-text" clearable style="width: 300px;margin-top: 5px;"></el-input>
                    </el-form-item>


                    <ul class="login-ul-pwd">
                        <li class="username-pwd-icon">
                            <img width="22" height="22" style="margin-top: 7px;" src="./img/phone.png"/>
                        </li>
                        <li class="phone-font">
                            <span style="line-height: 30px;">手机号码：</span>
                        </li>
                    </ul>
                    <el-form-item>
                        <el-input placeholder="请输入手机号码" type="password" class="input-text" clearable style="width: 300px;margin-top: 5px;"></el-input>
                    </el-form-item>


                    <ul class="login-ul-pwd">
                        <li class="username-pwd-icon">
                            <img style="margin-top: 7px;" src="./img/u91.png"/>
                        </li>
                        <li class="username-pwd-font">
                            <span style="line-height: 30px;">新密码：</span>
                        </li>
                    </ul>
                    <el-form-item>
                        <el-input placeholder="请输入新密码" type="password" class="input-text" clearable style="width: 300px;margin-top: 5px;"></el-input>
                    </el-form-item>


                    <ul class="login-ul-pwd">
                        <li class="username-pwd-icon">
                            <img width="20" height="20" style="margin-top: 7px;" src="./img/yzm.png"/>
                        </li>
                        <li class="username-pwd-font">
                            <span style="line-height: 30px;">验证码：</span>
                        </li>
                    </ul>

                    <el-form-item>
                        <el-input placeholder="请输入验证码" class="input-text" style="width: 300px;margin-top: 5px;">
                            <template slot="append">发送验证码</template>
                        </el-input>
                    </el-form-item>

                    <el-button style="" type="primary">取消</el-button>
                    <el-button style="" type="primary">确认</el-button>

                </div>
            </div>
        </el-form>
    </div>
</template>

<script>

</script>

<style scoped>
    .root{
        text-align: center;
        background-size: cover;
        position: absolute;
        top: 0px;
        bottom: 0px;
        left: 0px;

        width: 100%;
        height: 100%;
        background-image: url("./img/forgetpwd.jpg");
        /*background-attachment:fixed;*/
    }
    /***************************************************************/
    /*欢迎栏 div*/
    .welcome-title-div{
        height: 150px;
        /*border: solid 1px red;*/
    }
    /*欢迎标题*/
    .welcome-title{
        font-size: 35px;
        font-weight: bold;
        color: white;
        line-height: 120px;
    }
    /***************************************************************/
    /*登陆框*/
    .login-box-root-div{
        /*border: solid 1px red;*/
        width: 380px;
        height: 450px;
        text-align: center;
        margin: auto;
        background-color:rgba(255,255,255,0.9)
    }
    .grid-content{
        margin-top: 25px;
        padding-top: 10px;
    }
    /*用户名*/
    .login-ul-pwd{
        margin-top: -10px;
    }
    /*用户名 密码 图标 li*/
    .username-pwd-icon{
        height:30px;
        width:20px;
        /*border: solid 1px white;*/
        list-style: none;
        float: left;
    }
    /*用户名 密码 文字 li*/
    .username-pwd-font{
        height:30px;
        width:90px;
        /*border: solid 1px red;*/
        list-style: none;
        float: left;
        color: black;
    }
    /*手机号码 文字 li*/
    .phone-font{
        height:30px;
        width:100px;
        /*border: solid 1px red;*/
        list-style: none;
        float: left;
        color: black;
        margin-top: 2px;
    }


</style>