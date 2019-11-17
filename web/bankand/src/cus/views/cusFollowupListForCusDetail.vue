<template>
    <div  class="pre-scrollable-s">

        <!--创建跟进 类型为带看-->
        <cus-create-followup-take-look
                ref="refCusFollowupTakeLook"
                v-if="cusCreateFollowupTakeLookOpenOrClose">
        </cus-create-followup-take-look>

            <div class="pagex">
                <el-table
                        :data="cusFollowupEntity"
                        stripe
                        border
                        size="small"
                        :height="fullHeight-235"
                        style="width: 100%">
                    <el-table-column
                            prop="createTime"
                            label="跟进时间"
                            align="center"
                            width="140"
                            show-overflow-tooltip>
                        <template slot-scope="scope" v-if="scope.row.createTime!=null">{{ scope.row.createTime | time}}</template>
                    </el-table-column>
                    <el-table-column
                            prop="jointRemark"
                            label="跟进结果"
                            align="center"
                            width="300"
                            show-overflow-tooltip>
                    </el-table-column>

                    <el-table-column
                            prop="followupTypeName"
                            label="跟进类型"
                            align="center"
                            width="80"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="deptName"
                            label="所属部门"
                            align="center"
                            width="100"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="empName"
                            label="服务专员"
                            align="center"
                            width="100"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="cusCode"
                            label="客户编码"
                            align="center"
                            width="150"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="lastUpdateTime"
                            label="更新时间"
                            align="center"
                            width="140"
                            show-overflow-tooltip>
                        <template slot-scope="scope" v-if="scope.row.lastUpdateTime!=null">{{ scope.row.lastUpdateTime | time}}</template>
                    </el-table-column>
                    <el-table-column align="center" fixed="right" label="操作" width="100" height="10">
                        <template slot-scope="scope">
                            <el-button
                                    v-if="scope.row.followupType == '1'"
                                    type="success"
                                    size="mini"
                                    @click="toCreateFollowupTakeLook(scope.$index,scope.row)">
                                详 情
                            </el-button>
                        </template>

                    </el-table-column>
                </el-table>
            </div>
            <!-- 分页 -->
            <div class="page-box-s">
                <el-pagination   background
                                 @size-change="handleSizeChange"
                                 @current-change="handleCurrentChange"
                                 :current-page.sync="currentPage"
                                 :page-sizes="[50,100]"
                                 :page-size="pageSizeData"
                                 layout="total, sizes, prev, pager, next, jumper"
                                 :total="totalCount">
                </el-pagination >
            </div>

    </div>
</template>

<script>

    import cusCreateFollowupTakeLook from "./cusCreateFollowupTakeLook"

    export default {
        components: {
            cusCreateFollowupTakeLook,
        },
        data(){
            return{
                fullHeight : document.documentElement.clientHeight,

                //客户带看详情
                cusCreateFollowupTakeLookOpenOrClose: false,
                cusFollowupTakeLook: [],//传值给面板

                cusCodeFollowup: '',
                //一页数据
                pageSize:50,
                //一页数据
                pageSizeData: 50,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                //第几个数
                start:0,
                //跟进数据
                cusFollowupEntity:[],
                cusFollowupDetail:{},
                customerQuery: {
                    cusCode:'',
                    followupType:'',
                    empCode:'',
                    empName:'',
                    deptCode:'',
                    deptName:'',
                    createTimeStart:'',
                    createTimeEnd:''
                },
            }
        },
        created(){
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
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight =document.documentElement.clientHeight
                    that.fullHeight =window.fullHeight
                })()
            }
        },
        methods:{
            //分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.pageSize = val;
                this.handleCurrentChange(1)
            },
            //改变每页数量
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getCusFollowupList();
            },
            getCusFollowupList(){
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = vm.customerQuery;

                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customerFollowup/select",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.cusFollowupEntity = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!customerFollowup/select');
                })
            },
            //关闭面板清空数据
            closeDialog(){
                this.cusFollowupEntity = [];
            },


            toCreateFollowupTakeLook(index,rows){
                var vm = this;
                vm.cusCreateFollowupTakeLookOpenOrClose = true;
                var sendObj = {};
                sendObj.entity =  {"followupType":"1","followupCode":rows.followupCode}
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customerFollowup/detail",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.cusFollowupDetail = response.data.result;
                }).then(() => {
                    let arr = [];
                    arr = vm.cusFollowupDetail.followupHouseList;
                    vm.$nextTick(() =>{
                        vm.$refs.refCusFollowupTakeLook.visible = true;
                        vm.$refs.refCusFollowupTakeLook.cusFollowupTakeLook = arr;
                        vm.$refs.refCusFollowupTakeLook.followupContent = vm.cusFollowupDetail.followupContent
                        vm.$refs.refCusFollowupTakeLook.createFollowup(null)
                    })
                })



            },
        }
    }
</script>

<style scoped>
    .hst_first_topbar{
        margin: 0px 0px 10px 0px;
        padding: 0px 0px 0px 0px ;
    }
    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }
    .pre-scrollable-s {
        margin: 0px px 0px 0px;
        padding: 0px 0px 30px 0px;
        overflow-x: scroll;
        overflow-y: auto;
    }
    .page-box-s {
        height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        width: 100%;
        position: absolute;
        z-index: 100;
    }
    .pagex{
        overflow-y: scroll;
        overflow-x: hidden;
    }
</style>