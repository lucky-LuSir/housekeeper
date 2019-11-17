<template>
    <div class="root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row v-bind:style="{width:floatDivWidth+'px'}">
            <div class="navigation_div" v-bind:style="{width:floatDivWidth+'px'}">
                <el-input size="small"
                          @keyup.enter.native="searchEnterFun"
                          class="navigation_button_input"
                          v-model="keyword"
                          style="margin-left: 20px;margin-top: 10px;width: 300px;"
                          placeholder="请输入用户名称查询">
                    <el-button slot="append" icon="el-icon-search" @click="findReLoadUser"></el-button>
                </el-input>

                <el-button v-show="showAddUserBtn" class="navigation_button_back" @click="toRegistered()" size="small"
                           type="primary" icon="el-icon-circle-plus" plain>
                    新增用户
                </el-button>
                <el-button type="primary" size="small" plain class="navigate_select_button" @click="exportExcel()" icon="el-icon-document" >
                    导出
                </el-button>
                <el-button type="primary" size="small" class="navigation_button_back"  @click="findUnreviewedUser()">
                    未审核用户
                </el-button>
            </div>
        </el-row>

        <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormRegistered"  @close="closeData()"  width="47%" center style="min-width: 1550px;">
            <span  style="font-weight: bolder;font-size: larger" v-show="submitBtnVisible">用户信息新增</span>
            <span style="font-weight: bolder;font-size: larger" v-show="!submitBtnVisible">用户信息修改</span>
            <el-container>

                <el-aside width="220px">

                    <div class="grid-content " style="margin-top:150px;">
                        <div v-if="fileType == 'image'" class="img-div1">
                            <template>
                                <i @click="removeUserImg()" class="el-icon-circle-close" style="float: right;"
                                   v-if="userEntity.userImg"></i>
                                <img height="100%" width="100%" :src=" hkpBaseUrl + 'file/read/' + userEntity.userImg "
                                     v-if="userEntity.userImg">
                                <file @selectFile="selectUserImg($event)" class="file_button"
                                      v-if="!userEntity.userImg"></file>
                            </template>
                        </div>
                    </div>
                    <div class="grid-content " style="margin-top:120px;">
                        <span  style= "text-align:center;display:block; ">免冠 黑色 (深) 外套 白底</span>
                    </div>
                </el-aside>
                <el-main>
                    <div style="margin-bottom: 25px; " >

                        <el-form :model="userEntity"  :rules="rules"  ref="ruleFormRef" label-width="0px" style=" text-align: center;">


                            <div class="grid-content ">
                                <el-form-item label="姓名" prop="userName" label-width="80px">
                                    <el-input v-model="userEntity.userName"/>
                                </el-form-item>
                            </div>
                            <div class="grid-content ">
                                <el-form-item label="性别" prop="sex" label-width="80px">
                                    <el-radio-group v-model="userEntity.sex">
                                        <el-radio label=1>先生</el-radio>
                                        <el-radio label=2>女士</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                            <div class="grid-content ">
                                <el-form-item label="电话" prop="userPhone"  v-show="submitBtnVisible" label-width="80px">
                                    <el-input v-model="userEntity.userPhone"/>
                                </el-form-item>
                                <el-form-item label="用户等级" prop="userLevel"  v-show="!submitBtnVisible" label-width="80px">
                                    <el-radio-group v-model="userEntity.userLevel">
                                        <el-radio label="K2">普通用户</el-radio>
                                        <el-radio label="K1">认证会员用户</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                            <div class="grid-content ">
                                <el-form-item label="身份证号" prop="card" label-width="80px">
                                    <el-input v-model="userEntity.card"/>
                                </el-form-item>
                            </div>
                            <div class="grid-content ">
                                <el-form-item label="业务区域" prop="userArea" label-width="80px">
                                    <el-cascader
                                            expand-trigger="hover"
                                            :options="departmentOptions"
                                            :props="props"
                                            v-model="userEntity.userArea"
                                            @change="handleChange">
                                    </el-cascader>
                                </el-form-item>

                            </div>
                            <div class="grid-content ">
                                <el-row :gutter="5">

                                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                        <div v-if="fileType == 'image'" class="img-div1">
                                            <template>
                                                <i @click="removeCardImage()" class="el-icon-circle-close" style="float: right;"
                                                   v-if="userEntity.bankCardImageName"></i>
                                                <img height="100%" width="100%" :src=" hkpBaseUrl + 'file/read/' + userEntity.cardImageName "
                                                     v-if="userEntity.cardImageName">
                                                <file @selectFile="selectCardImage($event)" class="file_button"
                                                      v-if="!userEntity.cardImageName"></file>
                                            </template>
                                        </div>
                                    </el-col>
                                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                        <div v-if="fileType == 'image'" class="img-div1">
                                            <template>
                                                <i @click="removeBankCardImage()" class="el-icon-circle-close" style="float: right;"
                                                   v-if="userEntity.bankCardImageName"></i>
                                                <img height="100%" width="100%" :src=" hkpBaseUrl + 'file/read/' + userEntity.bankCardImageName "
                                                     v-if="userEntity.bankCardImageName">
                                                <file @selectFile="selectBankCardImage($event)" class="file_button"
                                                      v-if="!userEntity.bankCardImageName"></file>
                                            </template>
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                            <div class="grid-content ">
                                <el-row :gutter="5">
                                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                        <span>身份证正面(必填)</span>
                                    </el-col>
                                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                        <span>银行卡正面</span>
                                    </el-col>
                                </el-row>
                            </div>
                            <div class="grid-content ">
                                <el-button @click="closeRegistered()">取消</el-button>
                                <el-button type="primary" @click="registered()" v-show="submitBtnVisible">提交</el-button>
                                <el-button type="primary" @click="editUser()" v-show="!(submitBtnVisible)">保存</el-button>
                            </div>
                        </el-form>
                    </div>
                </el-main>
            </el-container>
        </el-dialog>

        <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormExamine"  @close="closeData()"  width="47%" center style="min-width: 1550px;">
            <span style="font-weight: bolder;font-size: larger">用户信息审核</span>
            <el-container>

                <el-aside width="220px">

                    <div class="grid-content " style="margin-top:150px;">
                        <div v-if="fileType == 'image'" class="img-div1">
                            <template>
                                <i @click="removeUserImg()" class="el-icon-circle-close" style="float: right;"
                                   v-if="userEntity.userImg"></i>
                                <img height="100%" width="100%" :src=" hkpBaseUrl + 'file/read/' + userEntity.userImg "
                                     v-if="userEntity.userImg">
                                <file @selectFile="selectUserImg($event)" class="file_button"
                                      v-if="!userEntity.userImg"></file>
                            </template>
                        </div>
                    </div>
                    <div class="grid-content " style="margin-top:120px;">
                        <span  style= "text-align:center;display:block; ">免冠 黑色 (深) 外套 白底</span>
                    </div>
                </el-aside>
                <el-main>
                    <div style="margin-bottom: 25px; " >

                        <el-form :model="userEntity"  :rules="rules"  ref="ruleFormRef" label-width="0px" style=" text-align: center;">


                            <div class="grid-content ">
                                <el-form-item label="姓名" prop="userName" label-width="80px">
                                    <el-input v-model="userEntity.userName"/>
                                </el-form-item>
                            </div>
                            <div class="grid-content ">
                                <el-form-item label="性别" prop="sex" label-width="80px">
                                    <el-radio-group v-model="userEntity.sex">
                                        <el-radio label=1>先生</el-radio>
                                        <el-radio label=2>女士</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                            <div class="grid-content ">
                                <el-form-item label="电话" prop="userPhone" label-width="80px">
                                    <el-input v-model="userEntity.userPhone"/>
                                </el-form-item>
                                <el-form-item label="用户等级" prop="userLevel" label-width="80px">
                                    <el-radio-group v-model="userEntity.userLevel">
                                        <el-radio label="K2">普通用户</el-radio>
                                        <el-radio label="K1">认证会员用户</el-radio>
                                    </el-radio-group>
                                </el-form-item>

                                <el-form-item label="审核通过" prop="hasPass"  label-width="80px">
                                    <el-radio-group v-model="userEntity.hasPass">
                                        <el-radio label="0">审核通过</el-radio>
                                    </el-radio-group>
                                </el-form-item>

                            </div>
                            <div class="grid-content ">
                                <el-form-item label="身份证号" prop="card" label-width="80px">
                                    <el-input v-model="userEntity.card"/>
                                </el-form-item>
                            </div>
                            <div class="grid-content ">
                                <el-form-item label="业务区域" prop="userArea" label-width="80px">
                                    <el-cascader
                                            expand-trigger="hover"
                                            :options="departmentOptions"
                                            :props="props"
                                            v-model="userEntity.userArea"
                                            @change="handleChange">
                                    </el-cascader>
                                </el-form-item>

                            </div>
                            <div class="grid-content ">
                                <el-row :gutter="5">

                                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                        <div v-if="fileType == 'image'" class="img-div1">
                                            <template>
                                                <i @click="removeCardImage()" class="el-icon-circle-close" style="float: right;"
                                                   v-if="userEntity.bankCardImageName"></i>
                                                <img height="100%" width="100%" :src=" hkpBaseUrl + 'file/read/' + userEntity.cardImageName "
                                                     v-if="userEntity.cardImageName">
                                                <file @selectFile="selectCardImage($event)" class="file_button"
                                                      v-if="!userEntity.cardImageName"></file>
                                            </template>
                                        </div>
                                    </el-col>
                                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                        <div v-if="fileType == 'image'" class="img-div1">
                                            <template>
                                                <i @click="removeBankCardImage()" class="el-icon-circle-close" style="float: right;"
                                                   v-if="userEntity.bankCardImageName"></i>
                                                <img height="100%" width="100%" :src=" hkpBaseUrl + 'file/read/' + userEntity.bankCardImageName "
                                                     v-if="userEntity.bankCardImageName">
                                                <file @selectFile="selectBankCardImage($event)" class="file_button"
                                                      v-if="!userEntity.bankCardImageName"></file>
                                            </template>
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                            <div class="grid-content ">
                                <el-row :gutter="5">
                                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                        <span>身份证正面(必填)</span>
                                    </el-col>
                                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                        <span>银行卡正面</span>
                                    </el-col>
                                </el-row>
                            </div>
                            <div class="grid-content ">
                                <el-button @click="closeRegistered()">取消</el-button>
                                <el-button type="primary" @click="editUser()" v-show="!(submitBtnVisible)">保存</el-button>
                            </div>
                        </el-form>
                    </div>
                </el-main>
            </el-container>
        </el-dialog>


        <!--第1个方框 一行搜索-->
        <div class="block_box1"/>
        <!--第1个方框(end)-->

        <!--第2个方框 一行显示搜索条数-->
        <div class="block_box2"/>
        <!--第2个方框(end)-->

        <!--第3个方框 一行树状表格-->
        <!--用户信息列表-->
        <div class="user_table" v-loading.fullscreen.lock="loading"
             element-loading-text="拼命加载中">
            <el-table
                    id="userTbList"
                    :data="userTbList"
                    :highlight-current-row="true"
                    style="width: 100%;text-align: center;"
                    :show-header="true"
                    :border="true"
                    :height="fullHeight"
                    max-height="735"
                    >
                <el-table-column
                        type="selection"
                        width="45">
                </el-table-column>
                <!--<el-table-column align="center"
                                 label="id"
                                 width="200"
                                    >
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.id }}</span>
                    </template>
                </el-table-column>-->
                <el-table-column align="center"
                                 label="用户编号"
                                 width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userCode }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                        label="用户名称"
                        width="150">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="用户类型"
                                 width="150">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userType }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="手机号" width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userPhone }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="是否审核通过" width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.hasPass }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="邮箱" width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.email }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="操作" width="300" height="20">
                    <template slot-scope="scope" style="float: right;text-align: right;">
                        <el-button
                                v-show="showEditUserBtn"
                                size="mini"
                                type="primary"
                                @click="handleEditUser(scope.$index, scope.row)" icon="el-icon-edit">修改信息
                        </el-button>
                        <el-button v-show="showDividePermitBtn" type="primary" style="text-align: right;" size="mini"
                                   @click="handleDivideRole(scope.$index, scope.row)" icon="el-icon-warning">分配角色
                        </el-button>
                        <el-button
                                v-if="scope.row.hasPass=='1'"
                                size="mini"
                                type="primary"
                                @click="handleExamineUser(scope.$index, scope.row)" icon="el-icon-edit">审核
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <template>
                <div class="page-box">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page.sync="start"
                            :page-sizes="pageSizes"
                            :page-size="pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total"
                            >
                    </el-pagination>
                </div>
            </template>
        </div>
        <!--第3个方框(end)-->

        <!-- 用户,分配角色对话框 -->
        <el-dialog  :close-on-click-modal="false" :visible.sync="divideRoleVisible" width="35%" center  style="min-width: 1150px;">
            <el-form :model="diaUserRoleForm" ref="divideRoleRef" label-width="0px" style="text-align: center;">
                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 50px;line-height: 10px;">
                        <span style="font-weight: bolder;font-size: larger">用户分配角色</span>
                    </div>
                </el-row>
                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="用户名称" prop="userName" label-width="80px">
                                <el-input v-model="diaUserRoleForm.userName" :disabled="true"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="用户描述" label-width="80px">
                                <el-input v-model="diaUserRoleForm.remark" :disabled="true" type="textarea"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="选择角色" label-width="80px">
                                <el-select v-model="roleSelectList" multiple filterable placeholder="请选择" style="width: 100%;">
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
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="divideRoleVisible = false">取消</el-button>
                <el-button type="primary" @click="editUserRole()">保存选择角色</el-button>
            </div>
        </el-dialog>
        <!--第4个方框 页码条-->
        <div class="block_box4"></div>
        <!--第4个方框(end)-->


    </div><!--根部分(end)-->
</template>


<script scoped>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    import file from '@/common/components/file.vue'
    import FileSaver from 'file-saver';
    import XLSX from 'xlsx';
    import File from "../../../common/components/file.vue";

    export default {
        components: {
            ElInput,
            ElButton,
            file,
            File,
        },
        created: function () {
            var vm = {};
            vm = this;
            //菜单和按钮权限控制 start
            vm.showAddUserBtn = vm.$flagMenuStore.judgeMenu("add-user-btn");
            vm.showDividePermitBtn = vm.$flagMenuStore.judgeMenu("show-divide-permit-btn");
            vm.showEditUserBtn = vm.$flagMenuStore.judgeMenu("edit-user-btn");
            //菜单和按钮权限控制 end
            vm.basicReLoadUser();
        },
        updated() {
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        mounted() {
            const that = this
            that.handleChange();
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 205
                    that.fullHeight = window.fullHeight
                })()
            }
        },
        watch: {
            fullHeight (val) {
                if(!this.timer) {
                    this.fullHeight = val
                    this.timer = true
                    let that = this
                    setTimeout(function (){
                        that.timer = false
                    },400)
                }
            }
        },
        data() {
            return {
                fullHeight: document.documentElement.clientHeight - 205,
                loading:true,
                start: 1,
                pageSize: 10,
                total: 0,
                pageSizes: [10,20,30],
                floatDivWidth: "",
                roleInList: [],
                //用户新增展示按钮
                showAddUserBtn:false,
                //用户修改展示按钮
                showEditUserBtn:false,
                showAddPostBtn: false,
                showDividePermitBtn: false,
                showEditPostBtn: false,
                divideRoleVisible: false,
                roleTbList: [{
                    "roleCode": 'r001',
                    "roleName": '选项角色1',
                }, {
                    "roleCode": 'r002',
                    "roleName": '选项角色1',
                }, {
                    "roleCode": 'r003',
                    "roleName": '选项角色1',
                }],
                roleSelectList: [],
                rules: {
                    userName:[
                        {required: true, message: '请填写姓名(身份证上的)', trigger: 'blur'},
                    ],
                    sex:[
                        {required: true, message: '请填写性别', trigger: 'blur'},
                    ],
                    card:[
                        {required: true, message: '请输入身份证号', trigger: 'blur'},
                    ],
                    userArea:[
                        {required: true, message: '请输入业务区域', trigger: 'blur'},
                    ],
                    userPhone:[
                        {required: true, message: '请输入手机号', trigger: 'blur'},
                    ],
                },
                //end obj rules 验证对象
                selectUserName: '',
                keyword:'',
                submitBtnVisible: true,
                dialogFormVisible: false,
                dialogFormRegistered: false,
                dialogFormExamine:false,
                departmentOptions: [],
                props:{
                    value: 'areaCode',
                    label:'name',
                    children: 'cities'
                },
                userEntity:{
                    userName: '',
                    userPhone: '',
                    bankCardImageName: '',
                    cardImageName: '',
                    userImg: '',
                    password: '',
                    card: '',
                    userArea:[],
                    userType: '',//用户类型
                    userLevel:'',//用户等级 默认k2
                    hasPass:'',
                },
                diaForm: {
                    id:'',
                    userCode: '',
                    userName: '',
                    userPhone: '',
                    password:'',
                    email: '',
                    empImg:'',
                    createTime:'',
                    lastUpdateTime:'',
                    userType:''
                },
                diaUserRoleForm: {
                    userCode: '',
                    userName: '',
                    remark: '',
                },
                //上传类型
                fileType: "image",
                /*用户列表*/
                userTbList: [
                    {
                        userCode: '',
                        userName: '',
                        email: '',
                        userPhone: '',
                        hasPass:''
                    },
                ],
                hasPass:"",
            }
        },
        methods: {
            selectUserImg(urls){
                if (urls.length == 1) {
                    this.userEntity.userImg = urls.toString();
                } else {
                    vm.$notify({
                        message: '仅支持上传一张照片',
                        title: '操作提示'
                    });
                    return;
                }
            },
            //新增时移除图片
            removeUserImg(){
                this.userEntity.userImg = '';
            },
            selectBankCardImage(urls){
                if (urls.length == 1) {
                    this.userEntity.bankCardImageName = urls.toString();
                } else {
                    vm.$notify({
                        message: '仅支持上传一张照片',
                        title: '操作提示'
                    });
                    return;
                }
            },
            //新增时移除图片
            removeBankCardImage(){
                this.userEntity.bankCardImageName = '';
            },
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
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "user/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.loading = false;
                        vm.userTbList = response.data.result.data;
                        vm.total=response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!user/query');
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
            basicQueryOne(codeVal) {
                var vm = {};
                vm = this;
                var flag = true;
                //flag留后面表单验证
                if (flag) {
                    var obj = {};
                    obj.userCode = codeVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "user/queryOne",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.roleInList = response.data.result.roles;
                        var tempRoles = [];
                        vm.roleSelectList = [];
                        for (var i = 0; i < vm.roleInList.length; i++) {
                            tempRoles.push(vm.roleInList[i].roleCode);
                        }
                        vm.roleSelectList = tempRoles;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取角色数据失败(basicQueryOne)!user/queryOne');
                    });
                }
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
            handleDivideRole(index, rowsUser) {
                this.diaUserRoleForm.userCode = rowsUser.userCode;
                this.diaUserRoleForm.userName = rowsUser.userName;

                this.divideRoleVisible = true;//打开对话框
                this.submitBtnVisible = false;//隐藏提交按钮,显示保存按钮

                this.basicReLoadRole();//加载角色下拉框
                this.roleSelectList = [];
                this.basicQueryOne(rowsUser.userCode);

            },
            showAddDialog() {
                this.diaForm = {};
                this.submitBtnVisible = true;
                this.dialogFormVisible = true;

            },
            handleEditUser(index, rowsUser) {
                var vm = {};
                vm = this;
                vm.handleChange();//这个主要是把全部业务区域查出来
                //根据userCode 查出其信息 回显到面板上
                let userCode = rowsUser.userCode;
                vm.userEntity.userCode = userCode;
                var sendObj = {};
                sendObj.entity = vm.userEntity;
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "user/getUser",
                };
                this.$ajax(
                    options
                ).then(function (res) {
                    let result = res.data.result;
                    console.log(result)
                    vm.userEntity = result;

                    vm.dialogFormRegistered = true;//打开对话框
                    vm.submitBtnVisible = false;//隐藏提交按钮,显示保存按钮
                }).catch(function (error) {
                    vm.$notify.error('页面:获取数据失败! user/getUser');
                });

            },

            handleExamineUser(index, rowsUser) {
                var vm = {};
                vm = this;
                vm.handleChange();//这个主要是把全部业务区域查出来
                //根据userCode 查出其信息 回显到面板上
                let userCode = rowsUser.userCode;
                vm.userEntity.userCode = userCode;
                var sendObj = {};
                sendObj.entity = vm.userEntity;
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "user/getUser",
                };
                this.$ajax(
                    options
                ).then(function (res) {
                    let result = res.data.result;
                    console.log(result)
                    vm.userEntity = result;
                    vm.dialogFormExamine = true;//打开对话框
                    vm.submitBtnVisible = false;//隐藏提交按钮,显示保存按钮
                }).catch(function (error) {
                    vm.$notify.error('页面:获取数据失败! user/getUser');
                });

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

            },//end handleUpdate方法

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
            findReLoadUser() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 50;
                    sendObj.entity = obj;
                    sendObj.keyword=this.keyword;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "user/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.userTbList = response.data.result.data;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败! user/query');
                    });
                }
            },
            findUnreviewedUser() {
                var vm = {};
                vm = this;
                if(vm.hasPass==''){
                    vm.hasPass='1'
                }else{
                    vm.hasPass=''
                }
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {
                        hasPass:vm.hasPass,
                    };
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 50;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "user/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.userTbList = response.data.result.data;
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
            exportExcel(){
                /* 1创建 workbook 对象 from table */
                //alert("导出开始");
                var wb = XLSX.utils.table_to_book(document.querySelector('#userTbList'))
                /* 2获得 binary string as output */
                var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
                try {
                    FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '用户信息列表.xlsx')
                } catch (e) { if (typeof console !== 'undefined') console.log(e, wbout); console.log("导出excel捕获的异常ttt");}
                return wbout

            },
            //省市区街道
            handleChange(){

                var vm=this;
                var options = {
                    method: 'POST',
                    url:"cpyArea/queryAll",
                    data: {}
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.departmentOptions=response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败! cpyArea/queryAll');
                });
            },
            //打开新增人员面板
            toRegistered(){
                this.dialogFormRegistered= true;
                this.submitBtnVisible = true;
            },
            //关闭新增人员面板
            closeRegistered(){
                this.dialogFormRegistered= false;
            },
            registered(){
                var vm = {};
                vm = this;

                var obj = {};
                var sendObj = {};
                obj = vm.userEntity;
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "user/registered",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.closeRegistered();
                    vm.basicReLoadUser();

                    if(response){
                        let code = response.data.code;
                        if(code=="200"){
                            vm.userEntity.userImg = '';
                            vm.userEntity.bankCardImageName= '';
                            vm.userEntity.card= '';
                            vm.userEntity.sex='';
                            vm.userEntity.cardImageName= '';
                            vm.userEntity.userName= '';
                            vm.userEntity.userPhone= '';
                            vm.userEntity.userArea= [];
                            vm.$notify.success("新增人员成功,密码为12345678")
                        }
                    }

                }).catch(function (error) {
                    vm.$message.error('新增失败');
                });
            },
            /*修改用户信息*/
            editUser() {
                var vm = {};
                vm = this;

                var obj = {};
                var sendObj = {};
                obj = vm.userEntity;
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "user/editUser",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.closeRegistered();
                    vm.basicReLoadUser();
                    vm.$notify.success("用户修改成功")
                }).catch(function (error) {
                    vm.$message.error('用户修改失败');
                });
            },
            //清除人员新增修改的数据
            closeData(){
                let a = this.submitBtnVisible;
                if(a==false){
                    this.userEntity.userImg = '';
                    this.userEntity.bankCardImageName= '';
                    this.userEntity.card= '';
                    this.userEntity.cardImageName= '';
                    this.userEntity.userName= '';
                    this.userEntity.sex='';
                    this.userEntity.userPhone= '';
                    this.userEntity.userArea= [];
                }

            }
        }
    }

</script>

<style scoped>


    .root {
        min-width: 1000px;
    }

    /*导航栏DIV*/
    .navigation_div {
        width: 100%;
        height: 50px;
        min-width: 1000px;
    }

    /*导航栏返回按钮*/
    .navigation_button_back {
        margin-left: 20px;
    }

    .navigation_button_input {
        margin-left: 20px;
    }

    /*导航栏标题*/
    .navigation_title {
        height: 60px;
        margin-left: 10px;
        color: #FE980F;
        z-index: 300;
    }

    /*导航栏背景控制*/
    .navigation_button_background {
        height: 25px
    }

    /*新增页样式*/
    .konge {
        min-height: 45px;
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
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
    .page-box {
        height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }
    .el-select .el-input {
        width: 130px;
    }
    .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }

    /*表格*/
    .costTable{
        line-height: 40px;
        text-align: center;
        width: 100%;
    }
    .file_button{
        margin-top: 80px;
        margin-left: 50px;
    }
    .img-div1 {
        float: left;
        border:1px solid #f2f2f2;
        height: 200px;
        width: 200px;
    }
</style>
