<template>
    <div class="root" ref="myRoot">
        <el-form>
            <div class="nav-root-div" v-bind:style="{width:floatDivWidth+'px'}">
                <el-button class="back-button" size="small" type="primary" icon="el-icon-back"   plain @click="goBack()">返回</el-button>
                <el-button v-if="showAddCpy" class="insert-button" size="small" type="warning" plain @click="dialogFormVisible = true">添加</el-button>
            </div>

            <div style="min-height: 55px"></div>

            <el-dialog  :close-on-click-modal="false" class="insert-card-div" title="添加公司" :visible.sync="dialogFormVisible" v-if="">
                <el-form :model="cpyObj" :rules="rules" ref="cpyObj" label-width="100px">
                    <el-row :gutter="20">
                        <el-col :span="20">
                            <div class="grid-content">
                                <el-form-item label="公司名称：" prop="cpyName">
                                    <el-input clearable v-model="cpyObj.cpyName"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col >
                            <div class="grid-content">
                                <el-form-item label="公司图标：" >
                                    <file  @selectFile="selectImg" class="file-button"></file>
                                </el-form-item>
                                <!--<img v-if="cpyObj.cpyImg" :src=" parkBaseUrl + 'file/read/' + cpyObj.cpyImg ">-->
                                <i v-if="cpyObj.cpyImg" class="el-icon-circle-close-outline" @click="deleteImg()"></i>
                            </div>
                        </el-col>
                    </el-row>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submitForm('cpyObj')">保 存</el-button>
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                </div>
            </el-dialog>

            <div class="common-title-div">
                <span class="common-title-font">公司列表</span>
            </div>

            <div class="cpy-list-root-div" v-for="(item,index) in cpyList" v-if="showCpyList">
                <el-row :gutter="20" >
                    <el-col :span="24">
                        <div class="cpy-list-single-div">
                            <el-form-item label="公司名称: ">
                                <span class="name-style">{{ item.cpyName }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

        </el-form>
    </div>
</template>

<script>
    import ElOption from "../../../../node_modules/element-ui/packages/select/src/option";
    import file from '@/common/components/file.vue';

    export default {
        components: {
            ElOption,
            file
        },
        created:function () {
            var vm = this;

            vm.showCpyList = vm.$flagMenuStore.judgeMenu("cpyList");
            vm.showAddCpy = vm.$flagMenuStore.judgeMenu("addCpy");

            var options = {
                method: "POST",
                data: {},
                url: "company/query"
            };
            this.$ajax(
                options
            ).then(response => {
                if (!response.data.isException){
                    vm.cpyList = response.data.result.data;
                }
            }).catch(error => {
                vm.$message({
                    showClose: true,
                    message: '页面：公司列表加载失败！',
                    type: 'error'
                })
            })
        },
        data(){
            return{
                showCpyList: false,// 公司列表
                showAddCpy: false,// 公司新增
                cpyObj:{
                    cpyName:"",
                    cpyImg:""
                },
                cpyList:{},
                rules:{
                    cpyName:[
                        {required: true, message: '请输入公司名', trigger: 'blur'}
                    ]
                },
                dialogFormVisible: false,
                floatDivWidth:"",
            }
        },
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        methods: {
            //添加
            submitForm(formName){
                var vm = this;

                var obj = {
                    entity:{
                        cpyName: vm.cpyObj.cpyName
                    }
                }

                if (vm.cpyObj){
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            var options = {
                                method: "POST",
                                data: obj,
                                url: "company/create"
                            };
                            this.$ajax(
                                options
                            ).then(response => {
                                if (!response.isException) {
                                    vm.$message({
                                        showClose: true,
                                        message: '公司新增成功',
                                        type: 'success'
                                    });
                                    window.location.reload();
                                }
                            }).catch(error => {
                                vm.$message({
                                    showClose: true,
                                    message: '页面:公司新增失败!company/create',
                                    type: 'error'
                                });
                            });
                        } else {
                            this.$message({
                                showClose: true,
                                message: '数据填写不完整',
                                type: 'warning'
                            });
                        }
                    })
                }

            },
            //返回
            goBack(){
                this.$router.go(-1);
            },
            selectImg(urls){
                if(urls.length>1){
                    this.$message({
                        showClose: true,
                        message: '只能选择一张图片，请重新选择',
                        type: 'error'
                    });
                }else{
                    this.cpyObj.cpyImg=urls;
                }
            },
            deleteImg(){
                this.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.cpyObj.cpyImg=null;
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {
                });
            }
        },
    }

</script>

<style scoped>
    /*根div*/
    .root {
        min-width: 1000px;
        height: 100%;
        background: rgb(242, 242, 242);
        margin-top: -20px;
    }
    /*------------------------------------------------------------------------*/
    /*导航栏 根div*/
    .nav-root-div{
        background-color: white;
        width: 100%;
        height: 50px;
        color: rgba(255, 153, 0, 1);
        position: fixed;
        /*margin-top: -60px;*/
        z-index: 200;
    }

    /*返回按钮*/
    .back-button {
        float: left;
        margin-top: 10px;
        margin-left: 20px;
    }

    /*导航栏名字*/
    .nav-title{
        float: left;
        line-height: 50px;
        margin-left: 20px;
        margin-top: 1px;
    }

    /*新增按钮*/
    .insert-button{
        float: right;
        margin-top: 10px;
        margin-right: 20px;
    }
    /*------------------------------------------------------------------------*/
    /*新增公司弹窗*/
    .insert-card-div{
        margin-top: 0px;
        text-align: left;
        line-height: 60px;
    }

    .grid-content{
        /*border: solid 1px;*/
        height: 60px;
    }
    /*------------------------------------------------------------------------*/
    /*  title  公共div */
    .common-title-div {
        background-color: white;
        text-align: left;
        height: 50px;
        width: 100%;
        line-height: 50px;
        margin-top: 5px;
        /*border-bottom: 1px solid rgb(242, 242, 242);*/
    }

    /*  title  公共文字  */
    .common-title-font {
        font-size: 17px;
        color: rgba(255, 153, 0, 1);
        margin-left: 20px;
        font-weight: bold;
    }
    /*------------------------------------------------------------------------*/
    /*公司列表div*/
    .cpy-list-root-div{
        background-color: white;
        height: 100px;
        margin-top: 10px;
        margin-bottom: 10px;
    }

    /*公司单个div*/
    .cpy-list-single-div{
        margin-left: 20px;
        margin-top: 30px;
        text-align: left;
        font-weight: bold;
    }
    /*------------------------------------------------------------------------*/
</style>