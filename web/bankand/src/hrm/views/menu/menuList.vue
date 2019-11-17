<template scoped>
    <div class="root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row>
            <div class="navigation_div">
                <div class="titles">
                    <el-button v-show="showAddMenuBtn" class="navigation-add-edit-button" size="small" type="primary"
                               @click="showAddDialog"
                               plain icon="el-icon-circle-plus">新增
                    </el-button>
                    <el-button v-show="showEditMenuBtn" class="navigation-add-edit-button" size="small" type="primary"
                               @click="handleEditMenu()" plain icon="el-icon-edit">修改
                    </el-button>
                    <!--<el-button class="navigation_button_back" size="small" type="warning" plain>删除</el-button>-->
                </div>
            </div>
        </el-row>
        <div class="navigation_button_background"></div>

        <!-- 新增菜单对话框 -->
        <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible" width="55%" center style="min-width: 1150px;">
            <el-form :model="dialogMenuObj" :rules="rules" ref="ruleFormRef" label-width="100px"
                     style="text-align: center;">
                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 50px;">
                        <span v-show="submitBtnVisible">菜单新增</span>
                        <span v-show="!(submitBtnVisible)">菜单修改</span>
                    </div>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="菜单名称:" prop="menuName">
                                <el-input v-model="dialogMenuObj.menuName"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="菜单图标:" prop="iconCls">
                                <el-input v-model="dialogMenuObj.iconCls"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="菜单Item:" prop="item">
                                <el-input v-model="dialogMenuObj.item"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="菜单排序:" prop="sort">
                                <el-input v-model="dialogMenuObj.sort"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>


                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="菜单级别:" prop="level">
                                <el-input v-model="dialogMenuObj.level"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="菜单类型:" prop="menuType">
                                <el-select v-model="dialogMenuObj.menuType" placeholder="请选择" style="width: 100%">
                                    <el-option
                                            v-for="item in menuTypeArr"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>


                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="父菜单编码:" prop="parentCode">
                                <el-input v-model="dialogMenuObj.parentCode"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="父菜单名称:">
                                <el-input v-model="dialogObjParentName" disabled></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">

                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="菜单描述:" prop="remark">
                                <el-input v-model="dialogMenuObj.remark" placeholder="(选填)"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

            </el-form>
            <div slot="footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="addMenu('ruleFormRef')" v-show="submitBtnVisible">提交</el-button>
                <el-button type="primary" @click="editMenu('ruleFormRef')" v-show="!(submitBtnVisible)">保存</el-button>
            </div>
        </el-dialog>

        <el-container>
            <!--子菜单-->
            <el-aside width="201px" class="aside">

                <!--菜单列表-->
                <div class="post_resource">
                    <el-tree :data="treeMenuList"
                             style="line-height: 50px;"
                             @node-click="handleNodeClick"
                             :default-expand-all="false"
                             :expand-on-click-node="false"
                             node-key="id"
                             ref="tree"
                             highlight-current
                             :props="defaultProps">
                    </el-tree>
                </div>


            </el-aside>
            <!--主要内容-->
            <el-main style="background: white;">
                <el-form label-position="right" size="mini" ref="ruleForm" label-width="90px">
                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单名称:">
                                    <span style="float: left">{{mainMenuObj.menuName}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单编码:">
                                    <span>{{mainMenuObj.menuCode}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单Item:">
                                    <span>{{mainMenuObj.item}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单排序:">
                                    <span>{{mainMenuObj.sort}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单所属包:">
                                    <span style="float: left">{{mainMenuObj.pack}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单图标:">
                                    <span>{{mainMenuObj.iconCls}}</span>
                                </el-form-item>
                            </div>
                        </el-col>

                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单级别:">
                                    <span style="float: left">{{mainMenuObj.level}}</span>
                                </el-form-item>
                            </div>
                        </el-col>

                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单类型:">
                                    <span style="float: left">{{mainMenuObj.menuTypeName}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" v-show="false">
                            <div class="grid-content ">
                                <el-form-item label="排序:">
                                    <span>{{mainMenuObj.sort}}</span>
                                </el-form-item>
                            </div>
                        </el-col>


                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="父菜单编码:">
                                    <span>{{mainMenuObj.parentCode}}</span>
                                </el-form-item>
                            </div>
                        </el-col>

                        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                            <div class="grid-content ">
                                <el-form-item label="菜单描述:">
                                    <span>{{mainMenuObj.remark}}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">


                    </el-row>

                </el-form>
            </el-main>
        </el-container>

        <!--第2个方框 一行显示搜索条数-->
        <div class="block_box2"></div>
        <!--第2个方框(end)-->

        <!--第3个方框 一行树状表格-->
        <div class="block_box3"></div>
        <!--第3个方框(end)-->

        <!--第4个方框 页码条-->
        <div class="block_box4"></div>
        <!--第4个方框(end)-->


    </div><!--根部分(end)-->
</template>


<script>
    export default {
        components: {},
        created: function () {
            //初始化所有菜单
            var vm = {};
            vm = this;

            //菜单和按钮权限控制 start
           /* vm.showAddMenuBtn = vm.$flagMenuStore.judgeMenu("addMenuBtn");
            vm.showEditMenuBtn = vm.$flagMenuStore.judgeMenu("editMenuBtn");*/
            vm.showAddMenuBtn = true;
            vm.showEditMenuBtn = true;

            vm.basicInitAllMenu(vm);
            this.reInitDialogMenuObj();
        },
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        data() {
            //校验数字只能为整数
            var checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('楼层数只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                floatDivWidth: "",
                showAddMenuBtn: false,
                showEditMenuBtn: false,
                dialogObjParentName: '',
                menuTypeArr: [{
                    value: '1',
                    label: '菜单'
                }, {
                    value: '2',
                    label: '功能'
                }],
                rules: {
                    menuName: [
                        {required: true, message: '请输入菜单名称', trigger: 'blur'},
                    ],//输入框校验
                    parentCode: [
                        {required: true, message: '请输入父菜单编码', trigger: 'blur'},
                    ], //输入框校验
                    level: [
                        {required: true, message: '请输入菜单等级', trigger: 'blur'},
                        {validator: checkInteger},
                    ],//输入框校验
                    menuType: [
                        {required: true, message: '请选择菜单类型', trigger: 'change'}
                    ],//下拉框校验
                    sort: [
                        {required: true, message: '请输入菜单展示顺序', trigger: 'change'}
                    ],//下拉框校验
                },
                initPostItem: "",
                submitBtnVisible: true,
                dialogFormVisible: false,
                dialogMenuObj: {
                    menuCode: '',//菜单编码
                    menuName: '',//菜单名称
                    item: '',//菜单元素

                    parentCode: '',//父级菜单编码
                    menuType: '',//菜单类型
                    menuTypeName: '',//菜单类型名称
                    pack: '',//菜单元素包

                    iconCls: '',//菜单图标
                    level: 0,//菜单级别(Integer)
                    sort: '',//排序
                    remark: '',//菜单描述
                    children: []//菜单List<MenuEntity>
                },
                mainMenuObj: {
                    menuCode: '',//菜单编码
                    menuName: '',//菜单名称
                    item: '',//菜单元素

                    parentCode: '',//父级菜单编码
                    menuType: '',//菜单类型
                    menuTypeName: '',//菜单类型名称
                    pack: '',//菜单元素包

                    iconCls: '',//菜单图标
                    level: 0,//菜单级别(Integer)
                    sort: '',//排序
                    remark: '',//菜单描述
                    children: []//菜单List<MenuEntity>
                },
                treeMenuList: [
                ],
                defaultProps: {
                    children: 'children',
                    label: 'menuName'
                },
                activityArr: ["1"],
                postTbList: [],
                dialogFormVisible: false,
                postObj: {
                    postCode: '',
                    postName: '',
                    remark: '',
                },
            }
        },
        methods: {
            basicGetOneMenu(aValMenuCode){
                //请求树形菜单数据
                var vm = this;
                var obj = {};
                obj.menuCode = aValMenuCode;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "menu/queryByMenuCode",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.dialogObjParentName = response.data.result.data[0].menuName;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!menu/queryByMenuCode');
                });
            },
            // 修改菜单
            editMenu(ruleFormVal){
                var vm = {};
                vm = this;
                //表单验证 start
                this.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
                        var obj = {};
                        obj = this.dialogMenuObj;
                        obj.id = '';
                        var sendObj = {};
                        sendObj.entity = obj;

                        var options = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "menu/update",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$notify({
                                message: response.data.message,
                                title: '操作提示',
                            });
                            vm.dialogFormVisible = false;
                            vm.basicInitAllMenu(vm);
                            vm.reInitDialogMenuObj();
                        }).catch(function (error) {
                            vm.$message.error('页面:获取数据失败!menu/update');
                        });
                    } else {
                        vm.$message.error('填写不规范,请检查!');
                        return false;
                    }
                });
            },
            handleEditMenu()  {
                this.dialogObjParentName = '';
                this.dialogMenuObj = this.mainMenuObj;
                this.basicGetOneMenu(this.dialogMenuObj.parentCode);
                this.dialogFormVisible = true;//打开对话框
                this.submitBtnVisible = false;//隐藏提交按钮,显示保存按钮
            },
            reInitDialogMenuObj(){
                this.dialogMenuObj = {
                    menuCode: '',//菜单编码
                    menuName: '',//菜单名称
                    item: '',//菜单元素

                    parentCode: '',//父级菜单编码
                    menuType: '1',//菜单类型
                    menuTypeName: '',//菜单类型名称
                    pack: '',//菜单元素包

                    iconCls: '',//菜单图标
                    level: 0,//菜单级别(Integer)
                    sort: '',//排序
                    remark: '',//菜单描述
                    children: []//菜单List<MenuEntity>
                }
            },
            handleNodeClick(data) {
                this.reInitDialogMenuObj();
                this.mainMenuObj = data;

            },
            // 新增菜单
            addMenu(ruleFormVal){
                var vm = {};
                vm = this;

                this.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
                        var obj = {};
                        obj = this.dialogMenuObj;
                        var sendObj = {};
                        sendObj.entity = obj;
                        var options = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "menu/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$notify({
                                message: response.data.message,
                                title: '操作提示',
                            });
                            vm.basicInitAllMenu(vm);
                            vm.dialogFormVisible = false;
                            vm.reInitDialogMenuObj();

                        }).catch(function (error) {
                            vm.$message.error('页面:获取数据失败!menu/create');
                        });
                    } else {
                        vm.$message.error('填写不规范,请检查!');
                        return false;
                    }
                });
            },
            showAddDialog(){
                this.reInitDialogMenuObj();
                this.dialogMenuObj.level = this.mainMenuObj.level + 1;
                this.dialogMenuObj.parentCode = this.mainMenuObj.menuCode;
                this.dialogObjParentName = this.mainMenuObj.menuName;
                this.submitBtnVisible = true;
                this.dialogFormVisible = true;
            },
            basicInitAllMenu(vmThis){
                //请求树形菜单数据
                var vm = vmThis;
                var obj = {};
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "menu/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.treeMenuList = response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!menu/query');
                });
            },
            goBack(){
                var sendRuData = "岗位分配页跳岗位列表页";
                this.$router.push({
                    name: 'postList',   //rutes.js中配置的路由name
                    params: {
                        name: 'ru_postCodeName',
                        ru_postCode: sendRuData,
                    },
                });
            },
        },
    }

</script>

<style scoped>


    .root {
        min-width: 1100px;
        max-height: 1000px;
    }

    .titles {
        min-height: 50px;
    }

    /*导航栏DIV*/
    .navigation_div {
        margin-top: 10px;
        width: 100%;
    }

    /*导航栏返回按钮*/
    .navigation_button_back {
        float: left;
        margin-left: 20px;
    }

    /*导航栏标题*/
    .navigation_title {
        float: left;
        height: 30px;
        margin-left: 20px;
        color: #FE980F;
        z-index: 300;
    }

    .navigation-add-edit-button{
        float: right;
        margin-right: 20px;
    }

    /*新增页样式*/
    .konge {
        min-height: 45px;
        min-width: 1100px;
        background-color: rgb(242, 242, 242);
    }

    .post_resource {
        background-color: white;
        margin-left: 0px;
        max-height: 850px;
    }

    /*每一列的块区域*/
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
        /*background-color: #69ff38;*/
    }


</style>