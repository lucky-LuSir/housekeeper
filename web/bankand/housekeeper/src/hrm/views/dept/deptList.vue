<template slot-scope="scope">
    <div class="root pre-scrollable " ref="myRoot"><!--根部分-->


        <el-container>
            <!--子菜单-->
            <el-aside width="200px" class="aside">
                <el-menu
                        default-active="2"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        style="height: 600px;overflow-y: auto"
                        @close="handleClose">
                    <!-- 部门树 -->
                    <div class="left_bar">
                        <el-tree :data="deptTree"
                                 :props="defaultProps"
                                 :expand-on-click-node="false"
                                 :highlight-current="true"
                                 @node-click="handleNodeClick"
                                 node-key="deptCode"
                                 ref="tree">
                        </el-tree>
                    </div>
                </el-menu>


            </el-aside>
            <!--主要内容-->
            <el-main>
                <!--部门信息-->
                <div class="message_div" ref="deptInfo">
                    <el-row>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="message_span">部门名称：</span>
                            <span class="dept_people_p_content">{{deptDetail.deptName}}</span>
                        </el-col>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="message_span">部门地址：</span>
                            <span class="dept_people_p_content">{{deptDetail.deptAddress}}</span>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="message_span">部门领导：</span>
                            <span class="dept_people_p_content">{{deptDetail.leaderName}}</span>
                        </el-col>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="message_span">领导人电话：</span>
                            <span class="dept_people_p_content">{{deptDetail.leaderPhone}}</span>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="message_span">上级部门：</span>
                            <span class="dept_people_p_content">{{deptDetail.parentName}}</span>
                        </el-col>
                        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                            <span class="message_span">部门介绍：</span>
                            <span class="dept_people_p_content">{{deptDetail.remark}}</span>
                        </el-col>
                    </el-row>
                </div>

                <!--部门成员-->
                <div class="dept_people">
                    <p class="dept_people_title">部门成员</p>
                </div>
                <div v-if="!empList" class="dept_people_null">
                    暂无部门人员信息
                </div>
                <el-table
                        :data="empList"
                        border
                        style="width: 100%;line-height: 30px;">
                    <el-table-column
                    prop="workNumber"
                    label="工号"
                    width="100"
                    height="50"
                    >
                    </el-table-column>
                    <el-table-column
                    prop="userName"
                    label="人员姓名"
                    width="180">
                    </el-table-column>
                    <el-table-column
                    prop="userCode"
                    label="人员编号"
                    width="180">
                    </el-table-column>

                    <el-table-column
                    prop="userPhone"
                    label="电话"
                    width="180">
                    </el-table-column>
                    <el-table-column
                    prop="ownerDeptName"
                    label="部门名称">
                    </el-table-column>
                </el-table>

                <div style="height: 50px;width: 10px;"></div>


            </el-main>
        </el-container>

    </div><!--根部分(end)-->
</template>

<script>

    export default {
        data() {
            //校验电话号码
            var checkNumber = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (!pattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                // 树结构默认指定
                defaultProps: {
                    children: 'children',
                    label: 'deptName'
                },

                // 部门树
                deptTree: [],
//                floatDivWidth: "",
//                deptDataWidth:"",

                deptList: {

                },
                deptObj:{
                    deptName:"",
                    deptCode:"",
                },
                dept: {
                    deptName:"",
                    deptCode:"",
                    leaderCode:"",
                    leaderName:"",
                    leaderPhone:"",
                    parentName:"",
                    parentCode:"",
                    deptAddress:"",

                },
                deptDetail:{},
                empList: [],
                user:{
                    workNumber: '',
                    userCode: '',
                    userName: '',
                    email: '',
                    userPhone: '',
                    deptCode: '',
                    deptName: '',
                    postCode: '',
                    postName: '',
                },

            }
        },
        mounted(){

//            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
//            this.deptDataWidth = this.$refs.deptInfo.offsetWidth;
//            window.onresize = () => {
//                return (() => {
//                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
//                    this.deptDataWidth = this.$refs.deptInfo.offsetWidth;
//                })();
//            }



        },
        created:function (){
            var vm = {};
            vm = this;
            var sendObj = {};
            var options = {
                method: 'POST',
                data: sendObj,
                url: "dept/selectTrees",
            };
            this.$ajax(
                options
            ).then(function (response) {
                vm.deptTree = response.data.result;
            }).catch(function (error) {
                vm.$message.error('页面：获取部门树失败！');
            });
        },
        methods: {
            getDeptdeptDetail(){
                var vm = {};
                vm = this;
                var sendObj = {};
                sendObj.entity={"deptCode":this.dept.deptCode};
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "dept/deptDetails",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.deptDetail = response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面：获取部门树失败！');
                });

            },
            handleNodeClick(data) {
                this.initDeptObj();
                this.dept=data;
                this.getDeptdeptDetail();
                this.getEmpByDept(data.deptCode);

            },
            initDeptObj(){
                this.deptObj={
                    deptName:'',
                    deptCode:'',
                }
            },

            getEmpByDept(aVal){
                var vm = {};
                vm = this;
                var pageData = {};
                pageData.entity = {};
                pageData.entity.ownerDeptCode = aVal;
                var options = {
                    method: "POST",
                    data: pageData,
                    url: "internalUser/selectUserByDeptAll"
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.empList = response.data.result;
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:人员列表加载失败!internalUser/selectUserByDeptAll',
                        type: 'error'
                    })
                })
            },
            /*返回键*/
            goBack(){
                window.history.back();
            },
            /*子菜单打开事件*/
            handleOpen(key, keyPath) {

            },
            /*子菜单关闭事件*/
            handleClose(key, keyPath) {

            },
            created:function (){
                var vm = {};
                vm = this;
                var sendObj = {};
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "dept/selectTrees",

                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.deptTree = response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面：获取部门树失败！');
                });
            },
            //查看详情
            empDetails(empCode,flag){
                this.$router.push({
                    name: "empDetails",
                    params: {
                        empParams: {
                            empCode: empCode,
                            flag: flag,
                        }
                    }
                })
            },
        },

    }

</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .root {
        min-width: 1000px;
        min-height: 900px;
    }
    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height:  83vh;
        overflow-y: auto;
    }
    .left_bar{
        bottom: 50px;
    }
    .titles {

        min-height: 50px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*导航栏DIV*/
    .navigation_div {
        width: 100%;
        height: 50px;
    }

    /*导航栏返回按钮*/
    .navigation_button_back {
        float: left;
        margin-left: 20px;
    }


    /*导航栏背景控制*/
    .navigation_button_background {
        height: 35px
    }

    /*部门信息div*/
    .message_div {
        width: 100%;
        padding-right: 20px;
        background-color: white;
    }


    /*部门信息span*/
    .message_span {
        color: #FE980F;
        float: left;
        line-height: 30px;
        margin-left: 20px;
        margin-top: 5px;
    }


    /*部门成员div*/
    .dept_people {
        width: 100%;
        margin-top: 10px;
        padding-right: 20px;
        background-color: white;
        min-height: 60px;
    }

    .dept_people_null {
        width: 100%;
        height: 450px;
        background-color: white;
        margin-top: 10px;
        padding-right: 20px;
    }

    /*部门成员标题*/
    .dept_people_title {
        float: left;
        line-height: 30px;
        margin-left: 10px;
        color: #FE980F;
    }


    /*人员信息查看详情*/
    .dept_people_a {
        float: left;
        margin: 10px 30px;
        color: #FE980F;
        font-size: 16px;
    }

    /*人员信息标题*/
    .dept_people_p_title {
        float: left;
        line-height: 30px;
        margin-top: 5px;
    }

    /*人员信息内容*/
    .dept_people_p_content {
        float: left;
        line-height: 30px;
        margin-top: 5px;
        color: #8c939d;
    }


    .aside {
        text-align: left;
        min-height: 900px;
    }

</style>