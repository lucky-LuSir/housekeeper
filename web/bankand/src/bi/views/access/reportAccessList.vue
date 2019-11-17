<template slot-scope="scope">
    <div class="root" ref="myRoot">

        <!--搜索框-->
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
                <el-button v-show="showAllDividePermitBtn" type="primary"
                           style="text-align: right;" size="small"
                           @click="handleAllDivideAccess()">批量分配权限
                </el-button>
            </div>
        </el-row>

        <!--用户信息列表-->
        <div class="user_table">
            <el-table
                    :data="userTbList"
                    :highlight-current-row="true"
                    style="width: 100%;text-align: left;"
                    :show-header="true"
                    :border="true"
                    :height="fullHeight"
                    @select-all="handleSelectAll"
                    @selection-change="handleSelectionChange">
            >

                <el-table-column
                        type="selection"
                        width="45">
                </el-table-column>

                <el-table-column align="center"
                                 label="工号"
                                 min-width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.workNumber}}</span>
                    </template>
                </el-table-column>

                <el-table-column align="center"
                                 label="姓名"
                                 min-width="150">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userName}}</span>
                    </template>
                </el-table-column>

                <el-table-column align="center" label="人员编码" min-width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userCode}}</span>
                    </template>
                </el-table-column>

                <el-table-column align="center"
                                 label="部门"
                                 min-width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.ownerDeptName}}</span>
                    </template>
                </el-table-column>

                <el-table-column align="center" label="手机号" min-width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userPhone}}</span>
                    </template>
                </el-table-column>

                <el-table-column align="center" label="岗位" min-width="150">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.empPostName}}</span>
                    </template>
                </el-table-column>

                <el-table-column align="center" fixed="right" label="操作" min-width="200" height="20">
                    <template slot-scope="scope" style="float: right;text-align: right;">
                        <el-button
                                v-show="true"
                                size="mini"
                                type="primary"
                                @click="handleEditUser(scope.$index, scope.row)">详情
                        </el-button>
                        <el-button v-show="showDividePermitBtn" type="primary"
                                   style="text-align: right;" size="mini"
                                   @click="handleDivideAccess(scope.$index, scope.row)">分配权限
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

        <!--详情面板-->
        <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible" width="35%" center style="min-width: 1150px; ">
            <el-form :model="diaForm" :disabled=true style="text-align: center;">

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
                            <el-form-item label="人员权限" label-width="80px">
                                <el-select v-model="deptSelectList" multiple placeholder="请选择"
                                           style="width: 100%;">
                                    <el-option
                                            v-for="item in deptTbList"
                                            :key="item.deptCode"
                                            :label="item.deptName"
                                            :value="item.deptCode">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </el-form>
        </el-dialog>

        <!-- 分配权限对话框 -->
        <el-dialog  :close-on-click-modal="false" :visible.sync="divideAccessVisible" title="分配权限" width="30%" center style="min-width: 1150px;">
            <el-form :model="diaUserAccessForm" ref="divideRoleRef" label-width="0px"
                     style="text-align: center;">

                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="人员名称" prop="userName" label-width="80px">
                                <el-input v-model="diaUserAccessForm.userName"
                                          :disabled="true"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="人员描述" label-width="80px">
                                <el-input v-model="diaUserAccessForm.remark" :disabled="true"
                                          type="textarea"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="人员权限" label-width="80px">
                                <el-select v-model="deptSelectList" multiple placeholder="请选择"
                                           style="width: 100%;">
                                    <el-option
                                            v-for="item in  deptTbList"
                                            :key="item.deptCode"
                                            :label="item.deptName"
                                            :value="item.deptCode">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="divideAccessVisible = false">取消</el-button>
                <el-button type="primary" @click="addReportAccess()">保存选择</el-button>
            </div>
        </el-dialog>

        <!-- 批量分配权限对话框 -->
        <el-dialog  :close-on-click-modal="false" :visible.sync="allDivideAccessVisible" title="批量分配权限" width="30%" center style="min-width: 1150px;">

            <el-form :model="diaUserAccessForm" ref="divideRoleRef" label-width="0px"
                     style="text-align: center;">

                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-table :data="userList" height="300px">
                            <el-table-column property="userName" label="姓名" width="150"></el-table-column>
                            <el-table-column property="ownerDeptName" label="部门" width="200"></el-table-column>
                            <el-table-column property="empPostName" label="岗位"></el-table-column>
                        </el-table>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="人员权限" label-width="80px">
                                <el-select v-model="deptSelectList" multiple placeholder="请选择"
                                           style="width: 100%;">
                                    <el-option
                                            v-for="item in  deptTbList"
                                            :key="item.deptCode"
                                            :label="item.deptName"
                                            :value="item.deptCode">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="divideAccessVisible = false">取消</el-button>
                <el-button type="primary" @click="addAllReportAccess()">保存选择</el-button>
            </div>
        </el-dialog>

    </div>
</template>


<script scoped>
    export default {
        data() {
            return {

                fullHeight: document.documentElement.clientHeight - 205,

                // 批量分配权限按钮
                showAllDividePermitBtn: false,

                // 分配权限按钮
                showDividePermitBtn: false,

                // 人员详情
                dialogFormVisible: false,

                // 批量分配弹窗
                allDivideAccessVisible: false,

                // 分配弹窗
                divideAccessVisible: false,

                // 查询参数
                start: 1,
                pageSize: 20,
                total: 0,
                pageSizes: [20, 50],
                keyword: '',

                // 用户选择的权限
                deptSelectList: [],

                // 批量选择的用户
                userCodes: [],

                // 分配权限保存按钮
                submitBtnVisible: true,

                // 下拉框数据
                deptTbList: [],

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
                    },
                ],

                /*选择的用户列表*/
                userList: [],

                // 详情信息
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
                },

                // 分配详情
                diaUserAccessForm: {
                    userCode: '',
                    userName: '',
                    remark: '',
                },

            }
        },
        created: function () {
            var vm = {};
            vm = this;
            vm.showDividePermitBtn = true;
            //菜单和按钮权限控制 end
            vm.basicReLoadUser();
        },
        methods: {

            // 初始人员查询
            basicReLoadUser() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {
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
                        url: "internalUser/queryValid",
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
                        url: "internalUser/queryValid",
                    };

                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.userTbList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败! user/query');
                    });
                }
            },

            // 回车搜索用户
            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                //'回车搜索', keyCode, e
                if (keyCode == 13) {
                    vm.findReLoadUser();
                }
            },

            // 批量新增
            addAllReportAccess() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {
                    var sendObj = {};
                    sendObj.userCodes = vm.userCodes;
                    sendObj.deptCodes = vm.deptSelectList;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "reportAccess/createAll",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.allDivideAccessVisible = false;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!user/updateUserRoleList');
                    });
                }
            },

            // 新增
            addReportAccess() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {
                    var sendObj = {};
                    sendObj.entity = {"userCode": this.diaUserAccessForm.userCode};
                    sendObj.deptCodes = this.deptSelectList;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "reportAccess/create",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.divideAccessVisible = false;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!user/updateUserRoleList');
                    });
                }
            },

            // 人员详情
            handleEditUser(index, rowsUser) {
                this.diaForm.userCode = rowsUser.userCode;
                this.diaForm.workNumber = rowsUser.workNumber;
                this.diaForm.userPhone = rowsUser.userPhone;
                this.diaForm.empPostName = rowsUser.empPostName;
                this.diaForm.ownerDeptName = rowsUser.ownerDeptName;
                this.diaForm.userName = rowsUser.userName;
                this.diaForm.sex = rowsUser.sex;
                this.diaForm.email = rowsUser.email;
                this.diaForm.userName = rowsUser.userName;
                this.dialogFormVisible = true;//打开对话框
                this.submitBtnVisible = false;//隐藏提交按钮
                this.queryAccess(rowsUser.userCode); // 查询权限
                this.basicReLoadAccess();//加载角色下拉框
            },

            // 分配权限
            handleDivideAccess(index, rowsUser) {
                this.diaUserAccessForm.userCode = rowsUser.userCode;
                this.diaUserAccessForm.userName = rowsUser.userName;
                this.divideAccessVisible = true;//打开对话框
                this.queryAccess(rowsUser.userCode); // 查询权限
                this.basicReLoadAccess();//加载权限下拉框
            },

            // 分配权限
            handleAllDivideAccess() {
                this.allDivideAccessVisible = true;//打开对话框
                this.basicReLoadAccess();//加载权限下拉框
            },

            // 查询当前人权限
            queryAccess(userCode) {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {
                    var sendObj = {};
                    sendObj.entity = {"userCode": userCode};
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "reportAccess/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.getUserAccessCodes(response.data.result);
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!');
                    });
                }
            },

            // 加载权限
            getUserAccessCodes(access) {
                var vm = {};
                vm = this;
                vm.deptSelectList = [];
                var tempDepts = [];
                for (var i = 0; i < access.length; i++) {
                    tempDepts.push(access[i].deptCode);
                }
                vm.deptSelectList = tempDepts;
            },

            // 下拉框
            basicReLoadAccess() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {
                    var sendObj = {};
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "reportAccess/selectDeptAccess",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.deptTbList = response.data.result;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!');
                    });
                }
            },

            handleSelectAll(users) {
                this.handleSelectFn(users);
            },

            handleSelectionChange(users) {
                this.handleSelectFn(users);
            },

            handleSelectFn(users){
                var vm = {};
                vm = this;
                if (users.length != 0 && users.length > 1) {
                    vm.deptSelectList = [];
                    vm.userList = users;
                    vm.userCodes = [];
                    var tempUsers = [];
                    for (var i = 0; i < users.length; i++) {
                        tempUsers.push(users[i].userCode);
                    }
                    vm.userCodes = tempUsers;
                    vm.showAllDividePermitBtn = true;
                } else {
                    vm.showAllDividePermitBtn = false;
                }
            },

            // 分页查询
            handleSizeChange(val) {
                this.pageSize = val;
                this.basicReLoadUser();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.basicReLoadUser();
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

    /*导航栏DIV*/
    .navigation-div {
        margin: 10px 50px;
        padding: 0px 0px 0px 0px;
        width: 100%;
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

    .el-select .el-input {
        width: 130px;
    }

    .input-with-select .el-input-group__prepend {
        background-color: #fff;
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