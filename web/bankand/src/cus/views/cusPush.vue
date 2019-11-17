<template>
    <el-dialog  :close-on-click-modal="false" style="height: 105%"  top="15px" width="52%"
               :visible.sync="visible"
               :before-close = "closeDialog"
               append-to-body>
        <div class="pre-scrollable-s">
            <p>
                <el-input size="small" style="width: 300px" v-model="keyword" placeholder="关键字:姓名，电话，部门"></el-input>
                <el-button size="mini" type="success" plain @click="keywordQuery()"  icon="el-icon-search" circle></el-button>
            </p>
            <div style="margin-top: 5px">
                <el-button v-if="isIndividual" type="primary" size="small" style="margin-left: 20px;float: left" @click="checkTypeMethod(1)" round>推送给个人</el-button>
                <el-button v-if="isIndividual"  size="small" style="margin-left: 60px;float: left" @click="checkTypeMethod(2)" round>推送给部门</el-button>

                <el-button v-if="!isIndividual" size="small" style="margin-left: 20px;float: left" @click="checkTypeMethod(1)" round>推送给个人</el-button>
                <el-button v-if="!isIndividual" type="primary" size="small" style="margin-left: 60px;float: left" @click="checkTypeMethod(2)" round>推送给部门</el-button>
            </div>
            <!--部门列表-->
            <el-table
                    v-if="!isIndividual"
                    :data="deptList"
                    stripe
                    border
                    height= "430"
                    style="width: 100%;font-size: 11px"
                    @selection-change="handleSelectionChangeDept">
                <el-table-column
                        type="selection"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="parentName"
                        label="上级部门"
                        align="center"
                        width="240"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="deptName"
                        label="部门"
                        align="center"
                        width="240"
                        show-overflow-tooltip>
                </el-table-column>
            </el-table>

            <!--经纪人列表-->
            <el-table
                    v-if="isIndividual"
                    :data="userList"
                    stripe
                    border
                    height= "430"
                    style="width: 100%;font-size: 11px"
                    @selection-change="handleSelectionChangeUser">
                <el-table-column
                        type="selection"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="userName"
                        label="经纪人"
                        align="center"
                        width="160"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="ownerDeptName"
                        label="部门"
                        align="center"
                        width="160"
                        show-overflow-tooltip>
                </el-table-column>

                <el-table-column
                        prop="userPhone"
                        label="手机号"
                        align="center"
                        width="160"
                        show-overflow-tooltip>
                </el-table-column>
            </el-table>


            <!-- 分页 -->
            <div class="page-box-s">
                <el-pagination background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="currentPage"
                               :page-sizes="[30,50]"
                               :page-size="pageSizeData"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="totalCount">
                </el-pagination>

                <span>客户号码向他公开：</span>
                <el-radio-group v-model="openFlag" style="padding-right: 80px">
                    <el-radio :label=true>公开</el-radio>
                    <el-radio :label=false>隐藏</el-radio>
                </el-radio-group>
                <el-button  size="small" type="primary" @click="createPush()">客户推送</el-button>
            </div>
        </div>
    </el-dialog>
</template>

<script>
    export default {
        data() {
            return {
                visible: false,
                cusCodeCusPush: '',//被推送的客户code
                pushMessage:'',//推送留言
                keyword: '',//关键字查询
                isIndividual: true,//是否是个人 否则是部门
                openFlag: true,//客户号码是否公开 1公开 2隐藏
                //一页数据
                pageSize: 30,
                //一页数据
                pageSizeData: 30,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                //第几个数
                start: 0,

                userList: [],//经纪人数组
                deptList: [],//部门数组

                selectUser: [],//选择推送的客户
                selectDept: [],//选择推送的部门
            }
        },

        methods: {
            //关键字查询
            keywordQuery(){
                if(this.isIndividual){
                    this.selectDept = []
                    this.queryIndividual();
                } else {
                    this.selectUser = []
                    this.queryDepartment();
                }
            },
            //选择推送类型
            checkTypeMethod(val){
                if(val==1){
                    this.isIndividual = true;
                    this.selectDept = []
                    this.queryIndividual();
                }
                if(val==2){
                    this.isIndividual = false;
                    this.selectUser = []
                    this.queryDepartment();
                }
            },
            //选择的用户数据
            handleSelectionChangeUser(val){
                this.selectUser = val;
            },
            //选择的部门数据
            handleSelectionChangeDept(val){
                this.selectDept = val;
            },
            //分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.pageSize = val;
                this.handleCurrentChange(1)
            },
            //改变每页数量
            handleCurrentChange(val) {
                this.currentPage = val;
                if(this.isIndividual){
                    this.queryIndividual();
                }else {
                    this.queryDepartment();
                }
            },
            //部门列表
            queryDepartment(){
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize;
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.keyword = vm.keyword;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "dept/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.deptList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                    vm.keyword = ''
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!dept/query');
                })
            },
            //经纪人列表
            queryIndividual(){
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = {"keyword":vm.keyword};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "employee/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.userList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                    vm.keyword = ''
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!employee/query');
                })
            },
            //推送创建
            createPush(){
                var vm = this;
                var sendObj = {};

                var obj = [];
                if(vm.selectUser.length == 0 && vm.selectDept.length == 0){
                    vm.$notify.info("请选择推送人或者部门");
                    return;
                }
                if(vm.isIndividual){
                    for(var i=0;i<vm.selectUser.length;i++) {
                        obj.push({
                            pushType: "Personal",
                            receiveCode: vm.selectUser[i].userCode,
                            receiveName: vm.selectUser[i].userName,
                            cusCode: vm.cusCodeCusPush,
                            pushOpenFlag: vm.openFlag,
                            pushMessage:vm.pushMessage
                        })
                    }
                }else {
                    for(var j=0;j<vm.selectDept.length;j++) {
                        obj.push({
                            pushType: "Department",
                            receiveDeptCode: vm.selectDept[j].deptCode,
                            receiveDeptName: vm.selectDept[j].deptName,
                            cusCode: vm.cusCodeCusPush,
                            pushOpenFlag: vm.openFlag,
                            pushMessage:vm.pushMessage
                        })
                    }
                }
                sendObj.pushEntities = obj;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customerPush/create",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.$emit("changeSuccessCusPush")
                    vm.$notify.success("客户推送成功");
                }).catch(function (error) {
                    vm.$message.error('客户推送失败');
                })
            },
            //关闭
            closeDialog(done){
                this.selectUser = []
                this.selectDept = []
                this.userList = []
                this.deptList = []
                this.cusCodeCusPush = ''
                done()
            },

        }
    }

</script>

<style scoped>
    .pre-scrollable-s {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 540px;
        overflow-x: hidden;
        overflow-y: auto;
        /*background-color: #2aabd2;*/
    }
    .page-box-s {
        height: 65px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        width: 100%;
        left: 0px;
        bottom: -47px;
        position: absolute;
        z-index: 10;
    }
</style>