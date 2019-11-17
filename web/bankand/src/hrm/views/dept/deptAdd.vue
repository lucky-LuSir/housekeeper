<template>
    <div class="root"><!--根部分-->
        <p>
            新增页面,部门
        </p>

        <el-form ref="form" :model="form" label-width="80px">


        <!--空格-->
        <div class="konge"></div><!--空格(end)-->

        <!--第一个方框:测试提交-->
        <div class="block_box1">

            <!--block_box1第一行-->
            <el-row :gutter="60">
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                    <div class="grid-content ">
                    <el-form-item label="上级部门">
                        <el-input v-model="form.parentCode"></el-input>
                    </el-form-item>
                </div>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6"><div class="grid-content ">
                    <el-form-item label="部门编码">
                        <el-input v-model="form.deptCode"></el-input>
                    </el-form-item>
                </div></el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6"><div class="grid-content ">
                    <el-form-item label="部门名称">
                        <el-input v-model="form.deptName"></el-input>
                    </el-form-item>
                </div></el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6"><div class="grid-content ">
                    <el-form-item label="部门类型(传数字)">
                        <el-input v-model="form.deptType"></el-input>
                    </el-form-item>
                </div></el-col>
            </el-row>
            <!--block_box1第一行(end)-->

            <!--block_box1第二行-->
            <el-row :gutter="60">
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                    <div class="grid-content ">
                        <el-form-item label="部门领导">
                            <el-input v-model="form.leaderCode"></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6"><div class="grid-content ">
                    <el-form-item label="活动名称">
                        <el-input ></el-input>
                    </el-form-item>
                </div></el-col>
            </el-row>
            <!--block_box1第二行(end)-->

        </div><!--第一个方框(end)-->

        <!--空格-->
        <div class="konge"></div><!--空格(end)-->

        <!--第二个方框-->
        <div class="block_box2">
            <span class="title_pt">配套信息</span>

        </div><!--第二个方框(end)-->

        <!--空格-->
        <div class="konge">

        </div><!--空格(end)-->



        <!--空格-->
        <div class="konge"></div><!--空格(end)-->




            <!--末尾提交按钮处-->
            <el-row :gutter="30">
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content "></div></el-col>
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content "></div></el-col>
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content "></div></el-col>
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                    <div class="grid-content ">
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit">立即创建</el-button>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content ">
                    <el-form-item>
                        <el-button @click="onqx">取消</el-button>
                    </el-form-item>
                </div>
                </el-col>
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content "></div></el-col>
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content "></div></el-col>
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content "></div></el-col>

            </el-row>
            <!--末尾提交按钮处(end)-->


        </el-form>
    </div><!--根部分(end)-->
</template>

<script>
    import axios from 'axios';
    import qs from 'qs';
    export default {
        data() {
            return {
                radio: '1',//加入饿了么选择是否的单选组件预写值
                form: {    //加入饿了么表单组件预写值

                    parentCode:'',
                    deptCode:'',
                    deptName:'',
                    deptType:'',
                    leaderCode:''
                }
            }
        },
        methods: {
            onqx(){
                //get请求经过测试可用0518
                axios.get('https://www.apiopen.top/novelApi')
                    .then(function (response) {
                        //response说明:  { data: Object, status: 200, statusText: ""}
                        //response.data 说明: { code: 200, msg: "成功!", data: Array[4] }
                        var obj = response.data;
                    })
                    .catch(function (error) {
                    });

                axios({
                    method: 'post',
                    url: 'https://www.apiopen.top/novelApi',
                    params:{"a":"a"}
                });
            },
            onSubmit() {
                //post请求还有问题在测试中

                var obj = { };

                obj=this.form;

                var hkpBaseUrl = this.hkpBaseUrl;

                //json提交方式---需要后台开启跨域配置mvc:cors
                var options = {
                    method: 'POST',
                    headers: { 'content-type': 'application/json' },
                    data: obj,
                    url:hkpBaseUrl+"dept/add",

                };
                axios(
                    options
                ).then(function (response) {
                }).catch(function (error) {
                });
                //json提交方式---需要后台开启跨域配置end


            }//end method onSubmit
        }//end methods
    }//end export default
</script>


<style scoped>


    .root {
        min-width: 1210px;
        min-height: 900px;
        background-color: rgb(242, 242, 242);
    }

    /*新增页样式*/
    .konge {
        min-height: 45px;
        min-width: 1210px;
        background-color: rgb(242, 242, 242);
    }

    .block_box1 {
        min-width: 1160px;
        min-height: 165px;
        border: 1px solid black;
        margin-left: 30px;
        margin-right: 30px;
        background-color: white;
    }

    .block_box2 {
        min-width: 1160px;
        min-height: 195px;
        border: 1px solid black;
        margin-left: 30px;
        margin-right: 30px;
        background-color: white;
    }


    .el-col {
        border-radius: 4px;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }




</style>