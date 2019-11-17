<template slot-scope="scope">
    <el-dialog  :close-on-click-modal="false" :visible.sync="cusCommonDialogFormVisible" width="60%" append-to-body>
    <div ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row>
            <div class="navigation_div">
                <el-input size="small"
                          @keyup.enter.native="searchEnterFun"
                          class="navigation_button_input"
                          v-model="cusName"
                          style="margin-left: 20px;width: 300px;"
                          placeholder="请输入查询条件">
                    <el-button slot="append" icon="el-icon-search" @click="findReLoadCustomer"></el-button>
                </el-input>
            </div>
        </el-row>
        <!-- 新增用户,修改用户对话框 -->


        <!--第3个方框 一行树状表格-->
        <!--用户信息列表-->
        <div class="cus_table">
            <el-table
                    :data="customerList"
                    :highlight-current-row="true"
                    style="width: 100%;text-align: center;"
                    @current-change="handleCurrentChange2"
                    :show-header="true"
                    :border="true"
                    height="380"
                    max-height="380">
                <el-table-column align="center"
                                 label="客户编号"
                                 width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.cusCode }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="客户姓名"
                                 width="150">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.cusName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="服务专员" width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.empName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="需求面积"
                                 width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.needAcreage }} ㎡</span>
                    </template>
                </el-table-column>
                <!--<el-table-column align="center" label="价格" width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.needPrice }}</span>
                    </template>
                </el-table-column>-->
            </el-table>
            <template>
                <div class="block">
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
            </template>
        </div>

        <div style="text-align: center;">
            <el-button size="small" @click="close()" type="primary">取消</el-button>
            <el-button size="small" @click="ok()" type="primary">确定</el-button>
        </div>
        <!--第3个方框(end)-->


    </div><!--根部分(end)-->
    </el-dialog>
</template>


<script scoped>


    export default {
        components: {

        },
        created: function () {

        },
        updated() {

        },
        data() {
            return {
                cusCommonDialogFormVisible:false,
                start: 1,
                pageSize: 50,
                total: 0,
                pageSizes: [50],
                floatDivWidth: "",
                cusName: '',
                /*用户列表*/
                customerList: [

                ],
                currentRow:{
                },
            }
        },
        methods: {
            handleCurrentChange2(val) {
                this.currentRow = val;

            },
            ok(){
                var vm=this;
                this.cusCommonDialogFormVisible=false;
                this.$emit("changed",this.currentRow);
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.basicReLoadCustomer();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.basicReLoadCustomer();
            },
            basicReLoadCustomer() {
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
                        url: "customer/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.customerList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!customer/query');
                    });
                }
            },

            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                //'回车搜索', keyCode, e
                if (keyCode == 13) {
                    vm.findReLoadCustomer();
                }

            },
            //查询按钮
            findReLoadCustomer() {
                var vm = {};
                vm = this;
                var flag = true;
            //flag留后面表单验证
                if (flag) {
                    var obj = {};
                    var sendObj = {};
                    sendObj.keyword = this.cusName;
                    sendObj.start = 0;
                    sendObj.pageSize = 30;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "customer/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {

                        vm.customerList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败! customer/query');
                    });
                }
            },
            close(){
                this.cusCommonDialogFormVisible=false;
            }
        }
    }

</script>
<style>
    .cus_table{
        margin-top: 10px;
        height: 420px;
    }
    .navigation_button_input {
        width: 300px;
    }
</style>