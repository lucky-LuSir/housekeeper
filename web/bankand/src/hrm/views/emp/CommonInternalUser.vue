<template slot-scope="scope">
    <el-dialog  :close-on-click-modal="false"  :visible.sync="empCommonDialogFormVisible" width="52%" top="40px"   append-to-body>
    <div class="root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row>
            <div class="navigation_div">
                <el-input size="small"
                          @keyup.enter.native="searchEnterFun"
                          class="navigation_button_input"
                          v-model="selectUserName"
                          placeholder="请输入查询条件">
                    <el-button slot="append" icon="el-icon-search" @click="findReLoadUser"></el-button>
                </el-input>
            </div>
        </el-row>
        <!-- 新增用户,修改用户对话框 -->


        <!--第3个方框 一行树状表格-->
        <!--用户信息列表-->
        <div class="user_table">
            <el-table
                    :data="userTbList"
                    :highlight-current-row="true"
                    style="width: 100%;text-align: center;"
                    @current-change="handleCurrentChange2"
                    :show-header="true"
                    :height="380"
                    :border="true">
                <el-table-column align="center"
                                 label="工号"
                                 width="70">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.workNumber }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="姓名"
                                 width="100">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.userName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="人员编码" width="190">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.userCode }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="部门"
                                 width="150">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.ownerDeptName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="手机号" width="120">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.userPhone }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="岗位" width="110">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.empPostName }}</span>
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
                            :total="total"
                    >
                    </el-pagination>
                </div>
            </template>
        </div>

        <div style="text-align: center;">
            <el-button type="primary" @click="close()" size="small">取消</el-button>
            <el-button @click="ok()" size="small" type="primary">确定</el-button>
        </div>
        <!--第3个方框(end)-->
    </div><!--根部分(end)-->
    </el-dialog>
</template>


<script scoped>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    import file from '@/common/components/file.vue'

    export default {
        components: {
            ElInput,
            ElButton,
            file
        },
        created: function () {
          /*  var vm = {};
            vm = this;
            //菜单和按钮权限控制 end
            vm.basicReLoadUser();*/
        },
        updated() {

        },
        data() {
            return {
                empCommonDialogFormVisible:false,
                start: 1,
                pageSize: 20,
                total: 0,
                pageSizes: [ 20],
                floatDivWidth: "",
                selectUserName: '',
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
                        userRole: [{roleCode: '', roleName: '',userCode:''}],
                    },

                ],
                currentRow:{
                },
            }
        },
        prop:["empCommonDialogFormVisible"],
        methods: {
            handleCurrentChange2(val) {
                this.currentRow = val;

            },
            close(){
                this.empCommonDialogFormVisible=false;
            },
            ok(){
                var vm=this;
                //this.currentRow.userVisible=false;
                this.$emit("changed2",this.currentRow);
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
                        url: "internalUser/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.userTbList = response.data.result.data;
                        vm.total = response.data.result.totalCount;

                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!user/query');
                    });
                }
            },

            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                //'回车搜索', keyCode, e
                if (keyCode == 13) {
                    vm.findReLoadUser();
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
                    sendObj.keyword = this.selectUserName;
                    sendObj.start = 0;
                    sendObj.pageSize = 20;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "internalUser/query",
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
        }
    }

</script>

<style scoped>
.root{
    height: 500px;
}
 .user_table{
     height: 420px;
 }
.navigation_button_input {
    width: 300px;
    margin-bottom: 10px;
}
</style>