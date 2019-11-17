<template>
    <div class="root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <div class="navigation_div" v-bind:style="{width:floatDivWidth+'px'}">
            <div class="titles">
                <el-button class="navigation_button_back" size="small" type="warning" @click="goBack()" plain>返回</el-button>
                <el-button v-show="showSavaDividePostBtn" class="navigation-save-button" size="small" type="warning" @click="handleClickEdit()" plain>保存</el-button>
            </div>
        </div>

        <div class="navigation_button_background"> </div>

        <!--第1个方框 一行搜索-->
        <div class="block_box1"></div>
        <!--第1个方框(end)-->
        <el-container>
            <!--子菜单-->
            <el-aside width="201px" class="aside">
                <el-menu
                        style="min-height: 900px;text-align: left;"
                        :collapse="false"
                        :default-active="initPostItem"

                        :default-openeds="activityArr"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-star-on"></i>
                            <span>岗位列表</span>
                        </template>
                        <div v-for="(item, i) in postTbList" @click="handleClickAPost(item)">
                            <el-menu-item :index="item.postCode">
                                <i class="el-icon-star-off"></i>
                                <span slot="title">{{item.postName}}</span>
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
            //获取路由传过来的岗位code
            let postCodeLet = this.$route.params.ru_postCode;
            //请求岗位列表数据
            var vm = { };
            vm=this;
            //菜单和按钮权限控制 start
            vm.showSavaDividePostBtn =vm.$flagMenuStore.judgeMenu("savaDividePostBtn");
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
                url: "post/query",
            };
            this.$ajax(
                options
            ).then(function (response) {
                vm.postTbList=response.data.result.data;
                vm.basicInitAllMenu(vm,postCodeLet);

            }).catch(function (error) {
                vm.$message.error('页面:获取数据失败!post/query');
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
                floatDivWidth:"",
                showSavaDividePostBtn:false,
                initPostItem:"",
                treeMenuList: [

                    {
                        "id": 0,
                        "isDeleted": false,
                        "menuCode": "rootNum",
                        "menuName": "所有菜单(根菜单)",
                        "parentCode": "0",
                        "menuTypeName": "找不到指定词典",
                        "level": 0,
                        "children": [

                            {
                                "id": 1,
                                "isDeleted": false,
                                "menuCode": "1",
                                "menuName": "园区管理",
                                "parentCode": "rootNum",
                                "menuTypeName": "找不到指定词典",
                                "level": 1,
                                "children": [
                                    {
                                        "id": 5,
                                        "isDeleted": false,
                                        "menuCode": "1-1",
                                        "menuName": "园区列表",
                                        "parentCode": "1",
                                        "menuTypeName": "找不到指定词典",
                                        "level": 2,
                                        "children": []
                                    },
                                    {
                                        "id": 6,
                                        "isDeleted": false,
                                        "menuCode": "1-2",
                                        "menuName": "园区新增",
                                        "parentCode": "1",
                                        "menuTypeName": "找不到指定词典",
                                        "level": 2,
                                        "children": []
                                    },
                                    {
                                        "id": 7,
                                        "isDeleted": false,
                                        "menuCode": "1-3",
                                        "menuName": "园区详情",
                                        "parentCode": "1",
                                        "menuTypeName": "找不到指定词典",
                                        "level": 2,
                                        "children": []
                                    }
                                ]
                            }
                        ]}
                ],
                defaultProps: {
                    children: 'children',
                    label: 'menuName'
                },
                activityArr:["1"],
                postTbList: [

                ],
                dialogFormVisible: false,
                postObj: {
                    postCode:'',
                    postName: '',
                    remark: '',
                },

            }//end return
        },//end vue data
        methods: {
            basicInitAllMenu(vmThis,postCodeLet){
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
                    //初始化选中岗位todo
                    //alert(postCodeLet);
                    if(postCodeLet==null){
                        vm.initPostItem="岗位激活初始值";//测试数据,CEO(平测)的postCode

                    }else{
                        vm.initPostItem=postCodeLet;
                        var aPostObj = {};
                        for(var i=0;i<vm.postTbList.length;i++){
                            if(postCodeLet==vm.postTbList[i].postCode){
                                aPostObj=vm.postTbList[i];
                            }
                        }
                        //alert("看看跳转查到的岗位对象");

                        //alert(aPostObj.postName);
                        vm.handleClickAPost(aPostObj);//用于加载树菜单初值
                    }
                    //初始化勾选菜单todo
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!menu/query');
                });
            },
            handleClickEdit(){
                //alert("点击了保存");
                var aValPost = {};
                aValPost = this.postObj;
                var menuObjList = [];
                    menuObjList = this.$refs.tree.getCheckedNodes();
                var menuCodeList = [];
                for(var i=0;i<menuObjList.length;i++){
                    var aMenuCode = menuObjList[i].menuCode;
                    menuCodeList.push(aMenuCode);
                }

                this.basicEditPost(aValPost,menuCodeList);
            },
            handleClickAPost(aVal){
                //alert(aVal.postCode);
                this.postObj = aVal;
                this.basicQueryOnePost(aVal.postCode);
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
                //树形菜单通过节点获取
            },

            goBack(){
//                rows.splice(index, 1);
                    var sendRuData = "岗位分配页跳岗位列表页";
                    this.$router.push({
                        name: 'postList',   //rutes.js中配置的路由name
                        params: {
                            name: 'ru_postCodeName',
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
                        url: "post/update",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        //alert("更新岗位和菜单成功!");
                        vm.$message({
                            message: response.data.message,
                            type: 'success'
                        });
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!post/update');
                    });
                }
            },
            basicQueryOnePost(aVal){
                var vm = {};
                vm=this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj.postCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "post/queryOne",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        //vm.postTbList=response.data.result.data;
                        vm.$refs.tree.setCheckedNodes(response.data.result.menus);
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!post/queryOne');
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
                        url: "post/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.postTbList=response.data.result.data;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!post/query');
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
    /*body {*/
        /*margin-left: 200px;*/
        /*margin-right: 200px;*/
    /*}*/

    .root {
        min-width: 1000px;
        min-height: 900px;
        background-color: rgb(242, 242, 242);
    }

    .titles{
        min-height: 50px;
        background-color: white;
        margin-top:-20px;
        border-bottom: 1px solid rgb(242,242,242);
    }
    /*导航栏DIV*/
    .navigation_div{
        position: fixed;
        z-index: 200;
        width: 100%;
        height: 50px;
    }
    /*导航栏返回按钮*/
    .navigation_button_back {
        float: left;
        margin-top: 10px;
        margin-left: 20px;
    }
    /*导航栏保存按钮*/
    .navigation-save-button {
        float: right;
        margin-top: 10px;
        margin-right: 20px;
    }
    /*导航栏标题*/
    .navigation_title{
        float: left;
        height: 60px;
        margin-top: -55px;
        margin-left: 20px;
        color: #FE980F;z-index: 300;
    }
    /*导航栏背景控制*/
    .navigation_button_background{
        height: 35px
    }

    /*新增页样式*/
    .konge {
        min-height: 45px;
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
    }

    .post_resource{
        min-height: 900px;
        text-align: right;
        background-color: white;
        margin-left: 0px;
        margin-top: -20px;
        margin-bottom: -20px;
        padding-top: 15px;
        padding-left: 20px;
        padding-right: 20px;
        width: 100%;
    }




</style>