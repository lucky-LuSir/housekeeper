<template slot-scope="scope">
    <div class="root" ref="myRoot"><!--根部分-->

        <el-row>
            <div class="navigation-div">
                <el-input size="small"
                          @keyup.enter.native="searchEnterFun"
                          class="navigation_button_input"
                          v-model="keyword"
                          style="margin-left: 0px;width: 300px;"
                          placeholder="请输入查询条件">
                    <el-button slot="append" icon="el-icon-search"
                               @click="findReLoadUser"></el-button>
                </el-input>
            </div>
        </el-row>

        <div class="navigation_button_background"/>

        <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible" width="35%" center style="min-width: 1150px; ">
            <el-form :model="diaForm" :disabled=true  style="text-align: center;">
                <el-row :gutter="5" style="text-align: center;">
                    <div>
                        <span style="font-weight: bolder;font-size: larger;">人员信息详情</span>
                    </div>
                </el-row>


                <el-row :gutter="5" style="margin-bottom: 10px;">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="工号" prop="workNumber" label-width="80px">
                                <el-input v-model="diaForm.workNumber"/>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="姓名" prop="userName" label-width="80px">
                                <el-input v-model="diaForm.userName"/>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="margin-bottom: 20px;">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="部门" prop="deptName" label-width="80px">
                                <el-input v-model="diaForm.ownerDeptName"/>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="电话" prop="userPhone" label-width="80px">
                                <el-input v-model="diaForm.userPhone"/>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="margin-bottom: 20px;">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="岗位" prop="postName" label-width="80px">
                                <el-input v-model="diaForm.empPostName"/>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="margin-bottom: 20px;">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="性别" prop="sex" label-width="80px">
                                <el-radio-group v-model="diaForm.sex">
                                    <el-radio label=1>先生</el-radio>
                                    <el-radio label=2>女士</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="人员角色" label-width="80px">
                                <el-select v-model="roleSelectList" multiple placeholder="请选择"
                                           style="width: 100%;">
                                    <el-option
                                            v-for="item in roleTbList"
                                            :key="item.roleCode"
                                            :label="item.roleName"
                                            :value="item.roleCode">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </el-form>
        </el-dialog>

        <!--第1个方框 一行搜索-->
        <div class="block_box1"/>
        <!--第1个方框(end)-->

        <!--第2个方框 一行显示搜索条数-->
        <div class="block_box2"/>
        <!--第2个方框(end)-->

        <!--第3个方框 一行树状表格-->
        <!--用户信息列表-->
        <div class="user_table">
            <el-table
                    :data="userTbList"
                    :highlight-current-row="true"
                    style="width: 100%;text-align: center;"
                    :show-header="true"
                    :border="true"
                    :height="fullHeight">

                <el-table-column
                        type="selection"
                        width="45">
                </el-table-column>
                <el-table-column align="center"
                                 label="工号"
                                 width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.workNumber}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="姓名"
                                 width="150">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userName}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="人员编码" width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userCode}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="部门"
                                 width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.ownerDeptName}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="手机号" width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userPhone}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="岗位" width="150">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.empPostName}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" fixed="right" label="操作" width="200" height="20">
                    <template slot-scope="scope" style="float: right;text-align: right;">
                        <el-button
                                v-show="true"
                                size="mini"
                                type="primary"
                                @click="handleEditUser(scope.$index, scope.row)">详情
                        </el-button>
                        <el-button v-show="showDividePermitBtn" type="primary"
                                   style="text-align: right;" size="mini"
                                   @click="handleDivideRole(scope.$index, scope.row)">分配角色
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="page-box">
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page.sync="start"
                        :page-sizes="pageSizes"
                        :page-size="pageSize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="total">
                </el-pagination>
            </div>
        </div>

        <!--第3个方框(end)-->

        <!-- 用户,分配角色对话框 -->
        <el-dialog  :close-on-click-modal="false" :visible.sync="divideRoleVisible" width="35%" center style="min-width: 1150px;">
            <el-form :model="diaUserRoleForm" ref="divideRoleRef" label-width="0px"
                     style="text-align: center;">
                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 50px;line-height: 10px;">
                        <span>人员分配角色</span>
                    </div>
                </el-row>
                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="人员名称" prop="userName" label-width="80px">
                                <el-input v-model="diaUserRoleForm.userName"
                                          :disabled="true"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="人员描述" label-width="80px">
                                <el-input v-model="diaUserRoleForm.remark" :disabled="true"
                                          type="textarea"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="人员角色" label-width="80px">
                                <el-select v-model="roleSelectList" multiple placeholder="请选择"
                                           style="width: 100%;">
                                    <el-option
                                            v-for="item in  roleTbList"
                                            :key="item.roleCode"
                                            :label="item.roleName"
                                            :value="item.roleCode">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="divideRoleVisible = false">取消</el-button>
                <el-button type="primary" @click="editUserRole()">保存选择角色</el-button>
            </div>
        </el-dialog>
        <!--第4个方框 页码条-->
        <!--<div class="block_box4"></div>-->
        <!--第4个方框(end)-->


    </div><!--根部分(end)-->
</template>


<script scoped>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    import file from '@/common/components/file.vue';


    export default {
        components: {
            ElInput,
            ElButton,
            file,

        },

        created: function () {
            var vm = {};
            vm = this;
            vm.showAddPostBtn = true;
            vm.showDividePermitBtn = true;
            vm.showEditPostBtn = true;
            //菜单和按钮权限控制 end
            vm.basicReLoadUser();
        },

        data() {
            return {
                fullHeight: document.documentElement.clientHeight - 205,
                start: 1,
                pageSize: 20,
                total: 0,
                pageSizes: [20, 50],
                floatDivWidth: "",
                showAddPostBtn: false,
                showDividePermitBtn: false,
                showEditPostBtn: false,
                divideRoleVisible: false,
                roleInList: [],
                roleSelectList: [],
                roleTbList: [],
                keyword: '',
                submitBtnVisible: true,
                dialogFormVisible: false,

                diaForm: {
                    id: '',
                    workNumber: '',
                    userCode: '',
                    userName: '',
                    userPhone: '',
                    password: '',
                    email: '',
                    empImg: '',
                    createTime: '',
                    lastUpdateTime: '',
                    userType: '',
                    postName: '',
                    postCode: '',
                    ownerDeptCode: '',
                    ownerDeptName: '',
                    userRole: [{roleCode: '', roleName: '', userCode: ''}],
                },
                diaUserRoleForm: {
                    userCode: '',
                    userName: '',
                    remark: '',
                    userRole: [{roleCode: '', roleName: '', userCode: ''}],
                },
                /*用户列表*/
                userTbList: [
                    {
                        workNumber: '',
                        userCode: '',
                        userName: '',
                        email: '',
                        userPhone: '',
                        ownerDeptCode: '',
                        ownerDeptName: '',
                        empPostCode: '',
                        empPostName: '',
                        sex: '',
                        userRole: [{roleCode: '', roleName: '', userCode: ''}],
                    },

                ],

            }
        },

        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 205
                    that.fullHeight = window.fullHeight
                })()
            }
        },

        methods: {
            selectCardImage(urls){
                if (urls.length == 1) {
                    this.userEntity.cardImageName = urls.toString();
                } else {
                    vm.$notify({
                        message: '仅支持上传一张照片',
                        title: '操作提示'
                    });
                    return;
                }
            },
            //新增时移除图片
            removeCardImage(){
                this.userEntity.cardImageName = '';
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.basicReLoadUser();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.basicReLoadUser();
            },

            basicReLoadUser() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = (vm.start - 1) * vm.pageSize;
                    sendObj.pageSize = vm.pageSize;
                    sendObj.keyword = this.keyword;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "internalUser/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.userTbList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!internalUser/query');
                    });
                }
            },
            //头像上传
            selectImg(urls){
                if (urls.length == 1) {
                    this.diaForm.empImg = urls.toString();
                    this.imgCode = urls.toString();
                } else {
                    this.$message({
                        showClose: true,
                        message: '仅支持上传一张照片',
                        type: 'warning'
                    });
                    return;
                }
            },
            //新增时移除图片
            removeImg(){
                this.diaForm.empImg = '';
            },
            basicReLoadRole() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 50;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "role/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.roleTbList = response.data.result.data;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/query');
                    });
                }
            },
            getUserRoleCodes(userRole){
                var vm = {};
                vm = this;
                vm.roleSelectList = [];
                var tempRoles = [];
                for (var i = 0; i < userRole.length; i++) {
                    tempRoles.push(userRole[i].roleCode);
                }
                vm.roleSelectList = tempRoles;
            },

            handleDivideRole(index, rowsUser) {
                this.diaUserRoleForm.userCode = rowsUser.userCode;
                this.diaUserRoleForm.userName = rowsUser.userName;
                this.diaUserRoleForm.userRole = rowsUser.userRole;
                this.divideRoleVisible = true;//打开对话框
                this.basicReLoadRole();//加载角色下拉框
                this.getUserRoleCodes(rowsUser.userRole);
            },

            editUserRole() {
                var vm = {};
                vm = this;
                var flag = true;
                //flag留后面表单验证
                if (flag) {
                    var obj = {};
                    obj = this.diaUserRoleForm;
                    var sendObj = {};
                    sendObj.entity = obj;
                    sendObj.roleCodes = this.roleSelectList;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "user/updateUserRoleList",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        //alert("更新岗位和菜单成功!");
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.divideRoleVisible = false;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!user/updateUserRoleList');
                    });
                }
            },

            handleEditUser(index, rowsUser) {
                this.diaForm.userCode = rowsUser.userCode;
                this.diaForm.workNumber = rowsUser.workNumber;
                this.diaForm.userPhone = rowsUser.userPhone;
                this.diaForm.empPostName = rowsUser.empPostName;
                this.diaForm.ownerDeptName = rowsUser.ownerDeptName;
                this.diaForm.userName = rowsUser.userName;
                this.diaForm.userRole = rowsUser.userRole;
                this.diaForm.sex = rowsUser.sex;
                this.diaForm.email = rowsUser.email;
                this.diaForm.userName = rowsUser.userName;
                this.dialogFormVisible = true;//打开对话框
                this.submitBtnVisible = false;//隐藏提交按钮
                this.basicReLoadRole();//加载角色下拉框
                this.getUserRoleCodes(rowsUser.userRole);
            },

            goPostDivide(index, rowsCus) {
                var sendRuData = rowsCus.postCode;
                this.$router.push({
                    name: 'postDivide',   //rutes.js中配置的路由name
                    params: {
                        name: 'ru_postCodeName',
                        ru_postCode: sendRuData,
                    },
                });

            },
            goBack() {
                this.basicReLoadUser();
            },
            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                //'回车搜索', keyCode, e
                if (keyCode == 13) {
                    vm.findReLoadUser();

                }

            },
            //查询按钮
            findReLoadUser() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 20;
                    sendObj.entity = obj;
                    sendObj.keyword = this.keyword;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "internalUser/query",
                    };

                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.userTbList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                        ``
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败! user/query');
                    });
                }
            },


            handleEdit(index, row) {
                //打印index, row
            },
            handleDelete(index, row) {
                //打印index, row
            },

        }
    }

</script>

<style scoped>

    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .root {
        min-width: 1000px;
    }

    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        overflow-y: scroll;
    }

    /*导航栏DIV*/
    .navigation-div {
        margin: 10px 50px;
        padding: 0px 0px 0px 0px;
        width: 100%;
    }

    /*导航栏返回按钮*/
    .navigation_button_back {
        margin-left: 20px;
    }

    .navigation_button_input {
        margin-left: 20px;
    }

    /*用户表格div*/
    .user_table {
        min-width: 1000px;
        background-color: white;
        min-height: 60px;
        line-height: 40px;
    }

    /*照片*/
    .photo-div {
        width: 220px;
        float: left;
        border: solid 1px;
    }

    .img-div1 {
        float: left;
        height: 200px;
        width: 200px;
        margin-left: 20px;
    }

    /*判断是是否上传了头像,如果没有加边框,反之不加*/
    .imgDiv2 {
        border: 1px solid rgb(242, 242, 242);
        margin-left: 10px;
    }

    .el-select .el-input {
        width: 130px;
    }

    .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }

    /*表格*/
    .costTable {
        line-height: 40px;
        text-align: center;
        width: 100%;
    }

    .file_button {
        margin-left: 50px;
    }

    .img-div1 {
        float: left;
        border: 1px solid #f2f2f2;
        height: 200px;
        width: 200px;
    }

    .page-box {
        height: 40px;
        padding-top: 0px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }
</style>
