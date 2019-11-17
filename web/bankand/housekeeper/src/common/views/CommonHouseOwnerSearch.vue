<template slot-scope="scope">
    <el-dialog  :close-on-click-modal="false"  :visible.sync="houseOwnerCommonDialogFormVisible" width="60%" top="40px"   append-to-body>
    <div class="root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row>
            <div class="navigation_div">
                <el-input size="mini"
                          @keyup.enter.native="searchEnterFun"
                          class="navigation_button_input"
                          v-model="houseownerName"
                          placeholder="请输入查询条件">
                    <el-button slot="append" icon="el-icon-search" @click="reloadHouseOwner"></el-button>
                </el-input>
            </div>
        </el-row>
        <!-- 新增用户,修改用户对话框 -->


        <!--第3个方框 一行树状表格-->
        <!--用户信息列表-->
        <div class="parttime_table">
            <el-table
                    :data="houseOwnerList"
                    :highlight-current-row="true"
                    style="width: 100%;text-align: center;"
                    @current-change="handleCurrentChange2"
                    :show-header="true"
                    :height="380"
                    :border="true">
                <el-table-column align="center"
                                 label="业主编号"
                                 width="180">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.houseownerCode }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="业主姓名"
                                 width="100">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.houseownerName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="业主性别"
                                 width="120">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.houseownerSexName}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="业主电话" width="120">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.houseownerPhone }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="业主类型" width="110">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.houseownerTypeName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="业主服务专员" width="110">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.empName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="公司" width="120">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.companyName }}</span>
                    </template>
                </el-table-column>
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
            <el-button @click="cancel()" type="primary" size="small">取消</el-button>
            <el-button @click="ok()" size="small" type="primary">确定</el-button>
        </div>
        <!--第3个方框(end)-->
    </div><!--根部分(end)-->
    </el-dialog>
</template>


<script scoped>
    import file from '../../common/components/file.vue'

    export default {
        components: {
            file
        },
        created: function () {
            var vm = {};
            vm = this;
            //菜单和按钮权限控制 end
            vm.basicReLoadHouseOwner();
        },
        updated() {

        },
        data() {
            return {
                houseownerName:'',
                houseOwnerCommonDialogFormVisible:false,
                start: 1,
                pageSize: 30,
                total: 0,
                pageSizes: [30],
                floatDivWidth: "",

                /*用户列表*/
                houseOwnerList: [
                    {
                        houseownerTypeName: '',
                        houseownerCode:'',
                        houseownerName: '',
                        houseownerSexName:'',
                        houseownerTypeName:'',
                        houseownerPhone:'',
                        empName:'',
                        companyName:'',
                    },

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
                this.houseOwnerCommonDialogFormVisible=false;
                this.$emit("setHouseOwner",this.currentRow);
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.basicReLoadHouseOwner();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.basicReLoadHouseOwner();
            },
            basicReLoadHouseOwner() {
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
                        url: "houseowner/select",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.houseOwnerList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!houseowner/select');
                    });
                }
            },

            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                //'回车搜索', keyCode, e
                if (keyCode == 13) {
                    vm.reloadHouseOwner();
                }

            },
            //查询按钮
            reloadHouseOwner() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 20;
                    sendObj.entity = obj;
                    sendObj.entity.keyword = this.houseownerName;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "houseowner/select",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.houseOwnerList = response.data.result.data;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败! houseowner/select');
                    });
                }
            },
            cancel(){
                this.$emit("closeHouseOwner");
            }
        }
    }

</script>

<style scoped>
.root{
    height: 500px;
}
 .parttime_table{
     height: 420px;
 }
.navigation_button_input {
    width: 300px;
    margin-bottom: 10px;
}
</style>