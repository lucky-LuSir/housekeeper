<template>
    <div class="root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row>
            <div class="navigation-div" v-bind:style="{width:floatDivWidth+'px'}">

                <el-input size="small"
                        @keyup.enter.native="searchEnterFun"
                        class="navigation_button_input"
                        v-model="selectPostName"
                        style="margin-left: 0px;width: 300px;margin-top: 10px;"
                        placeholder="请输入角色名称查询">
                    <el-button slot="append" icon="el-icon-search" @click="findReLoadRole"></el-button>
                </el-input>


                <el-button v-show="showaddRoleBtn" class="navigation-add-button" icon="el-icon-circle-plus" @click="showAddDialog" size="small" type="primary"  plain>
                    新增角色
                </el-button>
            </div>
        </el-row>

        <!-- 新增角色对话框 -->
        <el-dialog  :close-on-click-modal="false"  :visible.sync="dialogFormVisible" width="35%" center style="min-width: 1150px;">

            <el-form :model="diaForm" :rules="rules" ref="ruleFormRef" label-width="0px" style="text-align: center;">

                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 50px;line-height: 10px;">
                        <span  style="font-weight: bolder;font-size: larger" v-show="submitBtnVisible">角色新增</span>
                        <span style="font-weight: bolder;font-size: larger" v-show="!submitBtnVisible">角色修改</span>
                    </div>
                </el-row>

                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="角色名称" prop="roleName" label-width="80px">
                                    <el-input v-model="diaForm.roleName" ></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="角色描述"  label-width="80px">
                                    <el-input v-model="diaForm.remark"  type="textarea"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="addRole('ruleFormRef')" v-show="submitBtnVisible">提交</el-button>
                <el-button type="primary" @click="editRole('ruleFormRef')" v-show="!(submitBtnVisible)">保存</el-button>
            </div>
        </el-dialog>


        <!-- 角色权限树分配框 -->
        <el-dialog  :close-on-click-modal="false"  :visible.sync="divideFormVisible" width="50%" center style="min-width: 1150px;">
            <el-form :model="diaForm" :rules="rules" ref="ruleFormRef" label-width="0px" style="text-align: center;">
                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 50px;line-height: 10px;">
                        <span  style="font-weight: bolder;font-size: larger">角色权限分配</span>
                    </div>
                </el-row>
                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content ">
                            <el-form-item label="角色编号" prop="roleCode" label-width="80px">
                                <el-input v-model="diaForm.roleCode" ></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content ">
                            <el-form-item label="角色名称" prop="roleName" label-width="80px">
                                <el-input v-model="diaForm.roleName" ></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content ">
                            <el-form-item label="角色描述" prop="remark" label-width="80px">
                                <el-input v-model="diaForm.remark"/>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                      <!--  <el-input
                                placeholder="输入关键字进行过滤"
                                v-model="filterText">
                        </el-input>-->
                        <div class="grid-content ">
                            <el-tree :data="diaForm.treeMenuList"
                                     :filter-node-method="filterNode"
                                     style="line-height: 50px;"
                                     show-checkbox=""
                                     :default-expand-all="false"
                                     :expand-on-click-node="true"
                                     node-key="id"
                                     ref="tree"
                                     highlight-current :props="defaultProps">
                            </el-tree>
                        </div>
                    </el-col>
                </el-row>
            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="editDivideRole('ruleFormRef')" v-show="submitBtnVisible">分配</el-button>
            </div>
        </el-dialog>

        <!-- 角色岗位绑定框 -->
        <el-dialog  :close-on-click-modal="false"  :visible.sync="bindingPostVisible" width="50%" center style="min-width: 1150px;">
            <el-form :model="diaForm" :rules="rules" ref="ruleFormRef" label-width="0px" style="text-align: center;">
                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 50px;line-height: 10px;">
                        <span  style="font-weight: bolder;font-size: larger">角色岗位绑定</span>
                    </div>
                </el-row>
                <el-row :gutter="5" style="margin-bottom: 30px;">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content ">
                            <el-form-item label="角色编号" prop="roleCode" label-width="80px">
                                <el-input v-model="diaForm.roleCode" ></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content ">
                            <el-form-item label="角色名称" prop="roleName" label-width="80px">
                                <el-input v-model="diaForm.roleName" ></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content ">
                            <el-form-item label="角色描述" prop="remark" label-width="80px">
                                <el-input v-model="diaForm.remark"/>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <div class="grid-content ">
                            <el-form-item label="选择岗位" label-width="80px">
                                <el-select v-model="empPost.empPostCode" clearable filterable  placeholder="请选择" style="width: 100%;">
                                    <el-option
                                            v-for="item in postSelect"
                                            :key="item.empPostCode"
                                            :label="item.empPostName"
                                            :value="item.empPostCode">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="editBindingPost()" v-show="submitBtnVisible">绑定</el-button>
            </div>
        </el-dialog>


        <!--第1个方框 一行搜索-->
        <div class="block_box1"></div>
        <!--第1个方框(end)-->

        <!--第2个方框 一行显示搜索条数-->
        <div class="block_box2"></div>
        <!--第2个方框(end)-->

        <!--第3个方框 一行树状表格-->
        <div class="role_table">
            <el-table
                    border
                    :data="roleTbList"
                    :height="fullHeight"
                    style="width: 100%;
                    text-align: left;"
                    :show-header="true">

                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        label="角色名称"
                        min-width="180">
                    <template slot-scope="scope">
                        <span >角色名称:</span>
                        <span style="margin-left: 10px">{{ scope.row.roleName }}</span>
                    </template>
                </el-table-column>


                <el-table-column
                        label="角色描述"
                        min-width="180"
                        >
                    <template slot-scope="scope">
                        <span >角色描述:</span>
                        <span style="margin-left: 10px">{{ scope.row.remark }}</span>
                    </template>
                </el-table-column>


                <el-table-column label="操作" width="360">
                    <template slot-scope="scope" class="operation">
                        <el-button
                                v-show="showEditPostBtn"
                                size="mini"
                                type="primary"
                                @click="handleEditRole(scope.$index, scope.row)" icon="el-icon-edit">修改</el-button>
                        <el-button
                                v-show="showDividePermitBtn"
                                type="primary"
                                size="mini"
                                @click="showDivideRoleDialog(scope.$index, scope.row)" icon="el-icon-warning">分配权限</el-button>
                        <el-button
                                v-show="showDividePermitBtn"
                                type="primary"
                                size="mini"
                                @click="showDividePostDialog(scope.$index, scope.row)" icon="el-icon-warning">岗位绑定角色</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <!--第3个方框(end)-->

        <!--第4个方框 页码条-->
        <div class="block_box4"></div>
        <!--第4个方框(end)-->


    </div><!--根部分(end)-->
</template>


<script scoped>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    export default {
        components: {
            ElInput,
            ElButton
        },

        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 180
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

        created:function () {
                var vm = { };
                vm=this;
            //菜单和按钮权限控制 start
            vm.showaddRoleBtn = true;
            vm.showDividePermitBtn =true;
            vm.showEditPostBtn = true;
           /* vm.showaddRoleBtn =vm.$flagMenuStore.judgeMenu("addRoleBtn");
            vm.showDividePermitBtn =vm.$flagMenuStore.judgeMenu("dividePermitBtn");
            vm.showEditPostBtn =vm.$flagMenuStore.judgeMenu("editPostBtn");*/
            //菜单和按钮权限控制 end
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
                    vm.roleTbList=response.data.result.data;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!role/query');
                });
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
            return {
                fullHeight: document.documentElement.clientHeight - 180,
                //角色绑定的岗位
                empPost:{
                    empPostCode:'',
                    empPostName:''
                },
                //岗位选择
                postSelect:[
                    {
                        empPostCode:'',
                        empPostName:''
                    }
                ],
                //默认菜单子节点
                defaultProps: {
                    children: 'children',
                    label: 'menuName'
                },
                floatDivWidth:"",
                //角色新增按钮
                showaddRoleBtn:false,
                //角色编辑按钮
                showEditPostBtn:false,
                //角色分配按钮
                showDividePermitBtn:false,
                //rules 验证对象
                rules: {
                    roleName: [
                        { required: true, message: '请输入角色名称', trigger: 'blur' },
                    ],//输入框校验
                    remark: [
                        { required: true, message: '请输入角色备注', trigger: 'blur' },
                    ] //输入框校验

                },
                //查询岗位名
                selectPostName:'',
                //提交按钮
                submitBtnVisible:true,
                //角色编辑表单框
                dialogFormVisible: false,
                //角色权限树分配框
                divideFormVisible:false,
                //分配角色框
                divideRoleVisible:false,
                //角色岗位绑定框
                bindingPostVisible:false,
                //新增、修改角色信息的表单元素
                diaForm: {
                    roleCode:'',
                    roleName: '',
                    remark: '',
                },
                //页面角色数据分页集合的model
                roleTbList: [
                    {
                        roleCode:'',
                        roleName: '',
                        remark: '',
                        treeMenuList:[]
                    },

                ]
            }
        },
        watch: {
            filterText(val) {
                this.$refs.tree.filter(val);
            }
        },
        methods: {
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            //菜单权限页面初始化
            basicInitAllMenu(vmThis,rowsRole){
                //请求树形菜单数据
                var vm=vmThis;
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
                    vm.diaForm.treeMenuList=response.data.result;
                    //初始化勾选菜单todo
                    vm.diaForm.roleName= rowsRole.roleName;
                    vm.diaForm.roleCode= rowsRole.roleCode;
                    vm.diaForm.remark = rowsRole.remark;
                    vm.handleClickARole(rowsRole.roleCode);
                    vm.submitBtnVisible = true;
                    vm.divideFormVisible=true;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!menu/query');
                });
            },

            //菜单权限页面初始化
            basicInitAllPost(vmThis,rowsRole){
                //请求树形菜单数据
                var vm=vmThis;
                var obj = {};
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "empPost/queryAll",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.postSelect=response.data.result;
                    //初始化勾选菜单todo
                    vm.diaForm.roleName= rowsRole.roleName;
                    vm.diaForm.roleCode= rowsRole.roleCode;
                    vm.diaForm.remark = rowsRole.remark;
                    vm.basicQueryOne(rowsRole.roleCode);
                    vm.submitBtnVisible = true;
                    vm.bindingPostVisible=true;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!role/queryAllPost');
                });
            },
            //通过角色roleCode查询该角色绑定的岗位
            basicQueryOne(roleCode) {
                var vm = {};
                vm = this;
                var flag = true;
                //flag留后面表单验证
                if (flag) {
                    var obj = {};
                    obj.roleCode = roleCode;
                    var sendObj = {};
                    sendObj = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "role/queryBindingPost",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        var tempPost = response.data.result;
                        vm.empPost.empPostCode = tempPost;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取角色数据失败(basicQueryOne)!role/queryBindingPost');
                    });
                }
            },
            //编辑绑定岗位
            editBindingPost() {
                var vm = {};
                vm = this;
                var flag = true;
                //flag留后面表单验证
                if (flag) {
                    var obj = {};
                    obj = this.diaForm;
                    var sendObj = {};
                    sendObj.entity = obj;
                    let postObj = {};
                    if (this.empPost.empPostCode!=null && this.empPost.empPostCode!=""){
                        sendObj.empPostCode = this.empPost.empPostCode;
                        postObj = this.postSelect.find((item)=>{
                            return item.empPostCode === this.empPost.empPostCode;
                        });
                        sendObj.empPostName = postObj.empPostName;
                    }
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "role/editBindingPost",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.bindingPostVisible=false;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/editBindingPost');
                    });
                }


            },
            //处理点击角色
            handleClickARole(aVal){
                this.basicQueryOneRole(aVal);
            },
            //通过角色code找到角色权限
            basicQueryOneRole(aVal){
                var vm = {};
                vm=this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj.roleCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "role/queryOne",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$refs.tree.setCheckedNodes(response.data.result.menus);
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/queryOne');
                    });
                }
            },
            showAddDialog(){
                this.diaForm = {};
                this.submitBtnVisible = true;
                this.dialogFormVisible = true;

            },
            handleEditRole(index, rowsRole)  {
                this.diaForm.roleCode= rowsRole.roleCode;
                this.diaForm.roleName= rowsRole.roleName;
                this.diaForm.remark= rowsRole.remark;

                this.dialogFormVisible = true;//打开对话框
                this.submitBtnVisible =false;//隐藏提交按钮,显示保存按钮
            },
            //角色权限分配页面
            showDivideRoleDialog(index,rowsRole){
                var vm = { };
                vm=this;
                vm.basicInitAllMenu(vm,rowsRole);
            },
            //岗位绑定角色页面
            showDividePostDialog(index,rowsRole){
                var vm = { };
                vm=this;
                vm.basicInitAllPost(vm,rowsRole);
            },
            //编辑分配角色菜单权限
            editDivideRole(){
                    var menuObjList = [];
                    menuObjList = this.$refs.tree.getCheckedNodes();
                    var menuCodeList = [];
                    for(var i=0;i<menuObjList.length;i++){
                        var aMenuCode = menuObjList[i].menuCode;
                        menuCodeList.push(aMenuCode);
                    }
                    this.basicEditRole(menuCodeList);
            },
            //修改角色查看权限方法
            basicEditRole(menuCodeList){
                var vm = {};
                vm=this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj = vm.diaForm;
                    var sendObj = {};
                    sendObj.entity = obj;
                    sendObj.menuCodes = menuCodeList;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "role/update",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.divideFormVisible=false;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/update');
                    });
                }
            },

            editRole(ruleFormVal){
                var vm = {};
                vm=this;
                var flag = false;
                //表单验证 start
                this.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
//                        alert('submit!');
                        flag = true;
                    } else {
                        vm.$message.error('填写不规范,请检查!');
                        return false;
                    }
                });
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj = this.diaForm;
                    var sendObj = {};
                    sendObj.entity = obj;
                    sendObj.menuCodes = [];
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "role/update",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.dialogFormVisible =false;
                        vm.basicReLoadPost();
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/update');
                    });
                }
            },
            goRoleDivide(index, rowsCus)  {
                var sendRuData = rowsCus.roleCode;
                this.$router.push({
                    name: 'roleDivide',   //routes.js中配置的路由name
                    params: {
                        name: 'ru_roleCodeName',
                        ru_roleCode: sendRuData,
                    },
                });

            },//end handleUpdate方法

            addRole(ruleFormVal){
                var vm = {};
                vm=this;
                var flag = false;
                //表单验证 start
                this.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
//                        alert('submit!');
                        flag = true;
                    } else {
                        vm.$message.error('填写不规范,请检查!');
                        return false;
                    }
                });
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj = this.diaForm;
                    var sendObj = {};
                    sendObj.entity = obj;
                    //sendObj.menuCodes = [];
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "role/create",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.dialogFormVisible =false;
                        vm.basicReLoadPost();
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/create');
                    });
                }
            },
            goBack(){
                this.basicReLoadPost();
            },
            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                if (keyCode == 13) {
                    vm.findReLoadRole();

                }

            },
            findReLoadRole(){
                var vm = {};
                vm=this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj.roleName=this.selectPostName;
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
                        vm.roleTbList=response.data.result.data;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/query');
                    });
                }
            },
            basicReLoadPost(){
                var vm = {};
                vm=this;
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
                        vm.roleTbList=response.data.result.data;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/query');
                    });
                }
            },

            handleEdit(index, row) {

            },
            handleDelete(index, row) {

            }
        }
    }

</script>

<style scoped>


    .root {
        min-width: 1000px;
    }
    .operation{
        float: right;text-align: right;
    }
    /*导航栏DIV*/
    .navigation-div{
        margin-left: 50px;
        width: 100%;
        height: 40px;
        min-width: 1000px;
    }

    /*导航栏新增按钮*/
    .navigation-add-button {
        float: left;
        margin-top: 10px;
        margin-left: 30px;
    }

    /*导航栏搜索输入框*/
    .navigation_button_input{
        float: left;
        margin-left: 20px;
    }



    /*新增页样式*/
    .konge {
        min-height: 45px;
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
    }

    /*角色表格div*/
    .role_table{
        min-width: 1000px;
        margin-top: 10px;
        max-height: 900px;
        background-color: white;
        overflow-y: auto;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
</style>