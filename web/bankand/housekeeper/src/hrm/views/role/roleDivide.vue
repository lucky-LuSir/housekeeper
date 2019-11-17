<template>
    <div class="root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row>
            <div class="navigation_div" v-bind:style="{width:floatDivWidth+'px'}">
                    <div class="titles">
                        <el-button v-show="showSavaDivideRoleBtn" class="navigation-save" size="small" type="primary" @click="handleClickEdit()" icon="el-icon-circle-plus" plain>保存</el-button>
                    </div>
            </div>
        </el-row>

        <!--第1个方框 一行搜索-->
        <div class="block_box1"></div>
        <!--第1个方框(end)-->
        <el-container>
            <!--子菜单-->
            <el-aside width="300px" style="max-height: 800px" class="aside">
                <el-menu
                        style="text-align: left;"
                        :collapse="false"
                        :default-active="initPostItem"
                        :default-openeds="activityArr"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-bell"></i>
                            <span>角色列表</span>
                        </template>
                        <div v-for="(item, i) in roleTbList" @click="handleClickARole(item)">
                            <el-menu-item :index="item.roleCode">
                                <i class="el-icon-star-off"></i>
                                <span slot="title">{{item.roleName}}</span>
                            </el-menu-item>
                        </div>
                    </el-submenu>
                </el-menu>



            </el-aside>
            <!--主要内容-->
            <el-main>
                <!--岗位权限-->
                <div class="post_resource" >
                    <el-tree :data="treeMenuList"
                             style="line-height: 50px;"
                             show-checkbox=""
                             :default-expand-all="false"
                             :expand-on-click-node="true"
                             node-key="id"
                             ref="tree"
                             highlight-current :props="defaultProps">
                    </el-tree>
                </div>
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


<script scoped>

    export default {
        components: {},
        created:function () {
            //请求岗位列表数据
            var vm = { };
            vm=this;
            //菜单和按钮权限控制 start
            vm.showSavaDivideRoleBtn = vm.$flagMenuStore.judgeMenu("save-btn");
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
                vm.basicInitAllMenu(vm,null);

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
                name:'',
                ru_roleCode:'',
                floatDivWidth:"",
                showSavaDivideRoleBtn:false,
                initPostItem:"",
                treeMenuList: [],
                defaultProps: {
                    children: 'children',
                    label: 'menuName'
                },
                activityArr:["1"],
                roleTbList: [

                ],
                dialogFormVisible: false,
                roleObj: {
                    roleCode:'',
                    roleName: '',
                    remark: '',
                },

            }//end return
        },//end vue data
        methods: {
            //菜单权限页面初始化
            basicInitAllMenu(vmThis,roleCodeLet){
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
                    vm.treeMenuList=response.data.result;
                    vm.handleClickARole(aPostObj);
                    //初始化勾选菜单todo
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!menu/query');
                });
            },
            /**
             * 给角色分配菜单权限
             */
            handleClickEdit(){
                var aValPost = {};
                aValPost = this.roleObj;
                var menuObjList = [];
                    menuObjList = this.$refs.tree.getCheckedNodes();
                var menuCodeList = [];
                for(var i=0;i<menuObjList.length;i++){
                    var aMenuCode = menuObjList[i].menuCode;
                    menuCodeList.push(aMenuCode);
                }
                this.basicEditPost(aValPost,menuCodeList);
            },
            handleClickARole(aVal){
                this.roleObj = aVal;
                this.basicQueryOnePost(aVal.roleCode);
            },
            setCheckedNodes() {
                this.$refs.tree.setCheckedNodes([
                    {
                        "id": 12,
                        "isDeleted": false,
                        "menuCode": "3-2",
                        "menuName": "合同新增",
                        "parentCode": "3",
                        "menuTypeName": "找不到指定词典",
                        "level": 2,
                        "children": []
                    },
                ]);
            },
            getCheckedNodes() {
                //树形菜单通过节点获取 this.$refs.tree.getCheckedNodes()
            },

            goBack(){
//                rows.splice(index, 1);
                    var sendRuData = "角色分配页跳角色列表页";
                    this.$router.push({
                        name: 'roleList',   //rutes.js中配置的路由name
                        params: {
                            name: 'ru_roleCodeName',
                            ru_postCode: sendRuData,
                        },
                    });

            },
            basicEditPost(aValPost,menuCodeList){
                var vm = {};
                vm=this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj = aValPost;
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
                    }).catch(function (error) {
                        vm.$notify.error('页面:获取数据失败!role/update');
                    });
                }
            },
            basicQueryOnePost(aVal){
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
                        //vm.roleTbList=response.data.result;
                        vm.$refs.tree.setCheckedNodes(response.data.result.menus);
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/queryOne');
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
            handleOpen(key, keyPath) {

            },
            handleClose(key, keyPath) {

            },
        },//end vue methods
    }//end export default

</script>

<style scoped>


    .root {
        min-width: 1000px;
        max-height: 900px;
        /*background-color: rgb(242, 242, 242);*/
    }

    .titles{
        min-height: 50px;
    }
    /*导航栏DIV*/
    .navigation_div{

        position: fixed;
        width: 100%;

        height: 50px;
    }
    /*导航栏返回按钮*/
    .navigation_button_back {
        float: left;
        margin-left: 0px;
    }
    /*导航栏保存按钮*/
    .navigation-save {
        float: right;
        margin-right: 20px;
    }
    /*导航栏标题*/
    .navigation_title{
        float: left;
        height: 60px;
        margin-left: 20px;
        color: #FE980F;z-index: 300;
    }


    /*新增页样式*/
    .konge {
        min-height: 45px;
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
    }

    .post_resource{
        max-height: 800px;
        text-align: right;
        background-color: white;
        margin-left: 0px;
        margin-bottom: -20px;
        padding-top: 15px;
        padding-left: 20px;
        padding-right: 20px;
        width: 100%;
    }




</style>