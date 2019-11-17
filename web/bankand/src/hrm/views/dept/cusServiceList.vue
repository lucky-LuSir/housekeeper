<template slot-scope="scope" xmlns="http://www.w3.org/1999/html"
          xmlns="http://www.w3.org/1999/html">
    <div class="root" ref="myRoot">
        <!--导航栏-->
        <el-row v-bind:style="{width:floatDivWidth+'px'}">
            <div class="navigation_div" v-bind:style="{width:floatDivWidth+'px'}">
                <el-input size="small"
                          @keyup.enter.native="searchEnterFun"
                          class="navigation_button_input"
                          v-model="keyword"
                          style="margin-left: 20px;margin-top: 10px;width: 300px;"
                          placeholder="请输入部门或客服姓名查询">
                    <el-button slot="append" type="primary" @click="easySearch()">搜索</el-button>
                </el-input>

                <el-button class="navigation_button_back" @click="toRegistered()" size="small"
                           type="primary" icon="el-icon-circle-plus" plain>
                    新增客服配置
                </el-button>
            </div>
        </el-row>

        <el-form>

            <!--订单详情面板-->


            <!--订单查询面板-->
            <el-dialog  :close-on-click-modal="false" :visible.sync="createCusServiceFormVisible" width="35%" append-to-body>
                <el-form :model="cusServiceConfEntity">
                    <div>

                        <el-row :gutter="5">
                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                <span style="font-weight: 900;font-size: 15px;">新增部门客服配置</span>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="部门" label-width="50px">
                                    <el-select size="mini" v-model="cusServiceConfEntity.deptCode"
                                               filterable placeholder="请选择">
                                        <el-option
                                                v-for="item in deptEntity"
                                                :key="item.deptCode"
                                                :label="item.deptName"
                                                :value="item.deptCode">
                                            <span style="float: left">{{ item.deptName }}</span>
                                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode }}</span>
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="所属人员" prop="userName" label-width="80px">
                                    <el-input size="mini" v-model="cusServiceConfEntity.userName"
                                              placeholder="请选择客服人员">
                                        <el-button size="mini" slot="append" icon="el-icon-search"
                                                   @click="openUser()"></el-button>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </div>
                    <div class="grid-content ">
                        <el-form-item label="备注" prop="card" label-width="50px">
                            <el-input v-model="cusServiceConfEntity.remark"/>
                        </el-form-item>
                    </div>
                </el-form>
                <div style="text-align:center;line-height: 10px;">
                    <el-button size="mini" @click="cleanFrom()">重置</el-button>
                    <el-button size="mini" type="primary" @click="createCusServiceConf()">提交</el-button>
                </div>
            </el-dialog>




            <!--人员查询公共面板-->
            <CommonInternalUser v-if="userVisible" ref="user"
                                v-on:changed2="closeEmpCommon($event)"></CommonInternalUser>


            <!-- 列表 -->
            <div>
                <template>
                    <el-table
                            :data="cusServiceList"
                            size="small"
                            :height="fullHeight"
                            border
                            highlight-current-row
                            @current-change="selectTable">
                        <el-table-column align="center"
                                         prop="cusserviceCode"
                                         label="编码"
                                         width="180">
                        </el-table-column>
                        <el-table-column align="center"
                                         min-width="140px" label="创建时间"
                                         show-overflow-tooltip>
                            <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="deptName"
                                         label="部门名称"
                                         width="180">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="deptCode"
                                         label="部门编码"
                                         width="180">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="userName"
                                         label="客服姓名"
                                         width="100">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="userCode"
                                         label="客服编码"
                                         width="180">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="ownerDeptName"
                                         label="客服所属部门"
                                         width="180">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="remark"
                                         label="备注"
                                         width="280">
                        </el-table-column>
                        <el-table-column align="center"
                                         fixed="right"
                                         label="操作"
                                         width="130">
                            <template slot-scope="scope">


                                <el-button type="text" size="small"
                                           @click=deleteCusServiceConf(scope.row.cusserviceCode,scope.row.deptName)>删除
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </template>
            </div>


            <!-- 分页 -->
            <div class="page-box">
                <el-pagination background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="start"
                               :page-sizes="pageSizes"
                               :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total">
                </el-pagination>
            </div>

        </el-form>

    </div>
</template>


<script scoped>

    import CommonInternalUser from "../../../hrm/views/emp/CommonInternalUser.vue"
    export default{
        components: {
            CommonInternalUser
        },
        created: function () {
            var vm = this;


            this.selectCusServiceList(null);
            this.loadDeptList();
        },
        mounted(){

        },
        updated(){

        },
        data(){
            return {
                userVisible:false,
                fullHeight: document.documentElement.clientHeight - 205,
                //人员查询组件dialog
                empCommonDialogFormVisible: false,
                floatDivWidth:"",

                //新增客服配置
                createCusServiceFormVisible: false,

                pageSize: 20,
                start: 1,
                total: 0,
                //列表值
                cusServiceList: [],
                pageSizes: [20,50],
                //关键字搜索
                keyword: '',
                //自定义查询参数aaa
                cusServiceConfEntity: {
                    cusserviceCode:"",
                    deptName: "",
                    deptCode: "",
                    userCode: "",
                    userName: "",
                    remark: "",

                },
                //部门
                deptEntity: [],

                fullscreenLoading: false,

            }
        },
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 205;
                    that.fullHeight = window.fullHeight
                })()
            }
        },

        watch: {
            fullHeight (val) {
                if (!this.timer) {
                    this.fullHeight = val
                    this.timer = true
                    let that = this
                    setTimeout(function () {
                        that.timer = false
                    }, 400)
                }
            }
        },
        methods: {
            //打开新增面板
            toRegistered(){
                this.createCusServiceFormVisible= true;
            },
            //关闭新增面板
            closeRegistered(){
                this.createCusServiceFormVisible= false;
            },
            openUser(){
                var vm = this;
                vm.userVisible=true;
                vm.$nextTick(()=>{
                    vm.$refs.user.empCommonDialogFormVisible = true ;
                })
            },

            //关闭人员公共面板
            closeEmpCommon(data){
                var ddd = data;
                this.userVisible = data.userVisible;
                this.cusServiceConfEntity.userCode = data.userCode;
                this.cusServiceConfEntity.userName = data.userName;
            },

            selectTable(val) {
                var currentRow = this.currentRow = val;
            },

            //清空自定义查询面板页面参数
            cleanFrom(){
                this.cusServiceConfEntity = {};
            },
            //关键字查询方法
            easySearch(){
                var sendObj = {
                    keyword: this.keyword
                }
                this.selectCusServiceList(sendObj);
            },
            //加载订单列表数据
            selectCusServiceList(data){
                var vm = this;
                var sendObj = {};
                if (data != null) {
                    sendObj = data;
                }
                var obj = {};
                sendObj.start = (vm.start - 1) * vm.pageSize;
                sendObj.pageSize = vm.pageSize;
                sendObj.entity = obj;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "cusservice/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {

                    }
                    vm.cusServiceList = response.data.result.data;
                    vm.total = response.data.result.totalCount;

                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!cusservice/query');
                });
            },
            createCusServiceConf(data){
                var vm = this;
                vm.createCusServiceFormVisible = false;
                var obj = {};
                obj = this.cusServiceConfEntity;
                var sendObj = {};
                sendObj.entity = obj;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "cusservice/create",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {
                    }
                    vm.handleCurrentChange(1);

                }).catch(function (error) {
                });
            },
            deleteCusServiceConf(cusserviceCode,deptName){
                this.$confirm('是否删除'+deptName+'客服配置', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var vm = this;
                    var obj = {};
                    var sendObj = {};
                    sendObj.entity = obj;
                    obj.cusserviceCode=cusserviceCode;
                    var option = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "cusservice/delete",
                    };
                    this.$ajax(
                        option
                    ).then(function (response) {
                        if (!response.data.isException) {
                            vm.searchDialogFormVisible = false;
                        }
                        vm.handleCurrentChange(1);
                        vm.searchDialogFormVisible = false;
                    }).catch(function (error) {
                    });
                })

            },
            //加载部门数据
            loadDeptList(){
                var vm = this;
                var obj = {};
                var sendObj = {};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "dept/findDeptName",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {

                    }
                    vm.deptEntity = response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!dept/findDeptName');
                });
            },


            // 分页
            handleSizeChange(val) {
                this.pageSize = val;

                this.handleCurrentChange(1);
            },
            // 分页:改变每页数量
            handleCurrentChange(val) {
                this.start = val;
                this.selectCusServiceList();
            },

        }
    }
</script>


<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }




    /*导航栏DIV*/
    .navigation_div {
        margin-top: 20px;
        margin-left: 20px;
        margin-bottom: 20px;
        width: 100%;
    }

    .navigation_button_back {
        display: inline-block;
    }

    .navigation-title {
        font-size: 13px;
    }

    .input-with-select {
        width: 270px;
        margin-right:20px ;
    }

    .contract_start {
        width: 92%;
        height: 26px;
    }

    .page-box {
        height: 40px;
        line-height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }

</style>