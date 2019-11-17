<template>
    <div>
        <el-button type="warning" size="small" style="margin-right: 40px;" plain  @click="isShowBoard = true">添加看板</el-button>

        <el-dialog  :close-on-click-modal="false" title="添加看板" :visible.sync="isShowBoard" width="50%" center top="10vh" :before-close="handleClose" :append-to-body='true' style="height: 780px">
            <div class="left">
                <div style="margin-top: 10px;margin-left: 10px; width: 90%">
                    <el-input v-model="keyword" placeholder="搜索">
                        <el-button slot="append" icon="el-icon-search" @click="searchBoard()"></el-button>
                    </el-input>
                </div>
                <div class="searchTypeTop">
                    <span style="font-weight: bold;color: #333333">类别></span>
                    <ul class="searchType">
                        <li @click="searchByType(-1)" v-bind:class="{blue_color:-1 == isActive}">全部</li>
                        <li @click="searchByType(1)" v-bind:class="{blue_color:1 == isActive}">园区</li>
                        <li @click="searchByType(2)" v-bind:class="{blue_color:2 == isActive}">招商</li>
                        <li @click="searchByType(3)" v-bind:class="{blue_color:3 == isActive}">拓展</li>
                        <li @click="searchByType(4)" v-bind:class="{blue_color:4 == isActive}">财务</li>
                        <li @click="searchByType(5)" v-bind:class="{blue_color:5 == isActive}">其他</li>
                    </ul>
                </div>
            </div>


            <div class="right">
                <div style="height: 100%;overflow:auto;">
                    <div v-for="item in programs" style="height: 75px;padding: 10px;border-bottom: 1px solid #ccc;">
                        <div style="height: 92px;width: 25%;float: left">
                            <img src="../assets/img/thumb.png" style="width: 90%;height: 62px"/>
                        </div>
                        <div style="height: 92px;width: 49%;float: left;line-height: 10px">
                            <h4>{{ item.pgName }}</h4>
                            <p>{{ item.remark }}</p>
                        </div>
                        <div style="height: 92px;width: 25%;float: right">
                            <el-button type="warning" plain  @click="isShowParam(item.pgCode)">添加看板</el-button>
                        </div>
                        <div style="clear: both"></div>
                    </div>
                </div>
            </div>
            <div style="clear: both"></div>
        </el-dialog>

        <!--mine参数弹框-->
        <el-dialog  :close-on-click-modal="false"  :title="dialogTitle" :visible.sync="isShowMineParam"  :append-to-body='true'>
            <el-form ref="mineParam" :model="mineParam" label-width="80px">
                <el-form-item>
                    <el-button type="primary" @click="onMineParamSubmit">立即创建</el-button>
                    <el-button @click="isShowMineParam= false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <!--园区参数弹框-->
        <el-dialog  :close-on-click-modal="false"  :title="dialogTitle" :visible.sync="isShowParkParam"  :append-to-body='true'>
            <el-form ref="parkParam" :rules="rules" :model="parkParam" label-width="80px">
                <el-form-item label="入住园区 " prop="pkCode">
                    <el-select
                            v-model="parkParam.pkCode"
                            clearable
                            filterable
                            default-first-option
                            placeholder="输入园区名称或者地址">
                        <el-option
                                v-for="item in pkNames"
                                :key="item.code"
                                :label="item.name"
                                :value="item.code">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="onParkParamSubmit('parkParam')">立即创建</el-button>
                    <el-button @click="isShowParkParam= false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <!--部门参数弹框-->
        <el-dialog  :close-on-click-modal="false"  :title="dialogTitle" :visible.sync="isShowDeptParam"  :append-to-body='true'>
            <el-form ref="deptParam" :rules="rules" :model="deptParam" label-width="80px">
                <el-form-item label="选择部门" prop="deptCode">
                    <el-select
                            v-model="deptParam.deptCode"
                            clearable
                            filterable
                            default-first-option
                            placeholder="输入部门名称">
                        <el-option
                                v-for="item in deptNames"
                                :key="item.code"
                                :label="item.name"
                                :value="item.code">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="onDeptParamSubmit('deptParam')">立即创建</el-button>
                    <el-button @click="isShowDeptParam= false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

    </div>
</template>


<script>
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    export default {
        components: {
            ElButton
        },
        created:function() {
            let vm = this;

            //加载看板数据
            vm.programList({});

            //房源搜索栏
            let pkobj = {};
            let options = {
                method: 'POST',
                data: pkobj,
                url: "hkp/list",
            };

            this.$ajax(
                options
            ).then(function (response) {
                let result = response.data.result;
                let pkNameList = [];
                let pkObj = [];
                for (let s = 0; s < result.length; s++) {
                    let code = result[s].pkCode;
                    let name = result[s].pkName;
                    pkNameList.push(
                        {code: code, name: name}
                    );
                    pkObj[code] = name;
                }
                let aObj = {code: "", name: "全部园区"};
                vm.pkNames = pkNameList;
                vm.pkNames.unshift(aObj);

                vm.pkObj = pkObj;
            }).catch(function (error) {
                vm.$alert('页面:添加看板时房源搜索查询异常', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            });



            //部门搜索栏
            let deptObj = {};
            let deptOptions = {
                method: 'POST',
                data: deptObj,
                url:"dept/list",
            };
            this.$ajax(
                deptOptions
            ).then(function (response) {
                let result = response.data.result;
                let deptNameList = [];
                let deptObj = [];
                for(let s = 0; s < result.length; s++){
                    let code = result[s].deptCode;
                    let name = result[s].deptName;
                    deptNameList.push(
                        {code:code,name:name}
                    );
                    deptObj[code] = name;
                }
                vm.deptNames = deptNameList;
                vm.deptObj = deptObj;
            }).catch(function (error) {
                vm.$alert('页面:添加看板时部门搜索查询异常', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            });
        },
        data() {
            return {
                isShowBoard: false,
                dialogTitle:"",
                isShowBoardParam: false,
                isShowMineParam: false,
                isShowParkParam: false,
                isShowDeptParam: false,
                onPgCode:"",
                itemHoverIndex: null,
                keyword:null,
                isActive:false,
                programs: [
//                    {
//                        pgCode:"001",
//                        pgName:"我的数据",
//                        url:"../assets/img/thumb.png",
//                        remark:"显示个人本月招商面积、意向客户、新签租户。",
//                    },
//                    {
//                        pgCode:"002",
//                        pgName:"园区去化看板",
//                        url:"../assets/img/thumb.png",
//                        remark:"显示选择园区的去化面积。",
//                    },
//                    {
//                        pgCode:"003",
//                        pgName:"招商人员数据看板",
//                        url:"../assets/img/thumb.png",
//                        remark:"显示招商人员的每月数据统计。",
//                    },
//                    {
//                        pgCode:"004",
//                        pgName:"招商人员任务数据看板",
//                        url:"../assets/img/thumb.png",
//                        remark:"显示招商人员的任务展示",
//                    },
//                    {
//                        pgCode:"005",
//                        pgName:"应收租金费用异常看板",
//                        url:"../assets/img/thumb.png",
//                        remark:"显示应收租金费用异常的统计数据",
//                    },
//                    {
//                        pgCode:"006",
//                        pgName:"应收电费费用异常看板",
//                        url:"../assets/img/thumb.png",
//                        remark:"显示应收电费费用异常的统计数据",
//                    },
//                    {
//                        pgCode:"007",
//                        pgName:"应收水费用异常看板",
//                        url:"../assets/img/thumb.png",
//                        remark:"显示应收水费费用异常的统计数据",
//                    },
//                    {
//                        pgCode:"008",
//                        pgName:"应收停车费用异常看板",
//                        url:"../assets/img/thumb.png",
//                        remark:"显示应收停车费用异常的统计数据",
//                    }
                ],
                pkNames:[],
                mineParam:{},
                parkParam: {
                    pkCode: ''
                },
                deptNames:[],
                deptParam: {
                    deptCode: ''
                },
                rules: {
//                    pkCode: [
//                        {required: true, message: '园区不能为空', trigger: 'blur'},
//                    ],
                    deptCode: [
                        {required: true, message: '部门名称不能为空', trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
            handleClose(done) {
                done();
            },
            mouseenter(index){
                this.itemHoverIndex = index;
            },
            mouseleave(){
                this.itemHoverIndex = null;
            },
            isShowParam(pgCode){
                this.isShowBoard = false;
                if (pgCode === "pg001") {
                    this.isShowMineParam = true;
                    this.dialogTitle = "mine看板参数配置";
                    this.onPgCode = "001";
                }
                if (pgCode === "pg002") {
                    this.isShowParkParam = true;
                    this.dialogTitle = "园区去化看板参数配置";
                    this.onPgCode = "002";
                }
                if (pgCode === "pg003") {
                    this.isShowDeptParam = true;
                    this.dialogTitle = "招商人员数据看板参数配置";
                    this.onPgCode = "003";
                }
                if (pgCode === "pg004") {
                    this.isShowDeptParam = true;
                    this.dialogTitle = "每日任务看板参数配置";
                    this.onPgCode = "004";
                }
                if (pgCode === "pg005") {
                    this.isShowParkParam = true;
                    this.dialogTitle = "应收租金费用异常看板参数配置";
                    this.onPgCode = "005";
                }
                if (pgCode === "pg006") {
                    this.isShowParkParam = true;
                    this.dialogTitle = "应收电费用异常看板参数配置";
                    this.onPgCode = "006";
                }
                if (pgCode === "pg007") {
                    this.isShowParkParam = true;
                    this.dialogTitle = "应收水费用异常看板参数配置";
                    this.onPgCode = "007";
                }
                if (pgCode === "pg008") {
                    this.isShowParkParam = true;
                    this.dialogTitle = "应收停车费用异常看板参数配置";
                    this.onPgCode = "008";
                }

            },
            searchBoard() {
                let vm = this;
                let obj = {};
                obj.keyword = vm.keyword;

                let option = {
                    method: 'POST',
                    data: obj,
                    url: 'program/selectByKeyWord'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.programs = response.data.result;
                }).catch(function (error) {
                    vm.$alert('页面:搜索看板失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            searchByType(val) {
                this.isActive = val;
                if (val == -1) {
                    val = "";
                }
                let obj = {};
                obj.entity = {};
                obj.entity.programType = val;
                this.programList(obj);
            },
            onMineParamSubmit() {
                this.isShowMineParam = false;

                let vm = this;
                //房源搜索栏
                let param = {};
                let entity = {
                    pgCode:this.onPgCode
                };
                param.entity = entity;

                let options = {
                    method: 'POST',
                    data: param,
                    url:"board/create",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$message({
                        showClose: true,
                        message: '添加看板成功',
                        type: 'success'
                    });
                    vm.refresh();
                }).catch(function (error) {
                    vm.$alert('页面:添加mine看板失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            onParkParamSubmit(subForm) {
                this.$refs[subForm].validate((valid) => {
                    if (valid){
                        this.isShowParkParam = false;

                        let vm = this;
                        //房源搜索栏
                        let param = {};
                        let entity = {
                            pgCode:this.onPgCode,
                            param:this.parkParam.pkCode
                        };
                        param.entity = entity;

                        let options = {
                            method: 'POST',
                            data: param,
                            url:"board/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$message({
                                showClose: true,
                                message: '添加看板成功',
                                type: 'success'
                            });
                            vm.refresh();
                        }).catch(function (error) {
                            vm.$alert('页面:添加园区类看板失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },
            onDeptParamSubmit(subForm) {
                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        this.isShowDeptParam = false;

                        let vm = this;
                        //部门搜索栏
                        let param = {};
                        let entity = {
                            pgCode:this.onPgCode,
                            param:this.deptParam.deptCode
                        };
                        param.entity = entity;

                        let options = {
                            method: 'POST',
                            data: param,
                            url:"board/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$message({
                                showClose: true,
                                message: '添加看板成功',
                                type: 'success'
                            });
                            vm.refresh();
                        }).catch(function (error) {
                            vm.$alert('页面:添加部门类看板失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },
            refresh(){
                this.$router.replace('/back')
            },

            //公用查询
            programList(obj) {
                let vm = this;

                //加载看板数据
                let board_option = {
                    method: 'POST',
                    data: obj,
                    url: "program/list"
                };
                this.$ajax(
                    board_option
                ).then(function (response) {
                    vm.programs = response.data.result;
                }).catch(function (error) {
                    vm.$alert('页面:加载看板初始数据失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            }

        }
    }

</script>

<style scoped>
    .left{
        height: 500px;
        width: 25%;
        border: 1px solid ;
        float: left;
    }
    .right{
        height: 500px;
        width: 74%;
        border: 1px solid ;
        float: left;
    }

    .searchType {
        margin-left: -30px;
    }

    .searchType li{
        list-style-type: none;
        margin-bottom: 4px;
        cursor: pointer;
    }

    .searchType li:hover {
        background-color: #d9d9d9;
    }

    .searchTypeTop {
        margin-left: 10px;
        margin-right: 10px;
        margin-top: 20px;
        margin-bottom: 10px;
        color: #999999;
        min-height: 60px;
    }

    /*.searchTypeTop a {*/
        /*margin-right: 10px;*/
    /*}*/

    /*.searchTypeTop a:hover{*/
        /*cursor: pointer;*/
    /*}*/

    .blue_color{
        color: blue;
    }
</style>