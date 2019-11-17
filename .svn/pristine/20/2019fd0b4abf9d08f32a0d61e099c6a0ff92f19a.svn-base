<template>
    <div  class="pre-scrollable-s">
        <!--房源详情面板-->
        <hos-details  :houseCodeDetails="houseCodeDetails" ref="hosByDetails" v-if="houseDetailOpenOrClose"></hos-details>
        <div class="pagex">
            <el-table
                    :data="seeHosEntity"
                    border
                    size="small"
                    :height="fullHeight-235"
                    style="width: 100%">
                <el-table-column
                        prop="createTime"
                        align="center"
                        label="带看时间"
                        width="140"
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.createTime!=null">{{ scope.row.createTime | time}}</template>
                </el-table-column>
                <el-table-column
                        prop="remark"
                        label="带看结果"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="houseName"
                        label="房源标题"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="area"
                        label="面积(㎡)"
                        align="center"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="price"
                        align="center"
                        label="价格"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="unit"
                        align="center"
                        label="单位"
                        width="70"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="createName"
                        align="center"
                        label="服务专员"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="ownerDeptName"
                        align="center"
                        label="所属部门"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="houseCode"
                        label="房源编码"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column align="center"  fixed="right" label="操作" width="100" height="20">
                    <template slot-scope="scope" >
                        <el-button
                                type="success"
                                size="mini"
                                @click="toHouseDetail(scope.$index,scope.row)">
                            详情
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
                             :page-sizes="[30,50]"
                             :page-size="pageSizeData"
                             layout="total, sizes, prev, pager, next, jumper"
                             :total="totalCount">
            </el-pagination >
        </div>
    </div>

</template>

<script>
    import hosDetails from  "../../hos/views/hosDetails.vue"
    export default {
        components: {
            hosDetails,
        },
        data(){
            return{
                fullHeight : document.documentElement.clientHeight,
                cusCodeSeeHos:'',
                //房源详情面板打开或者关闭
                houseDetailOpenOrClose: false,
                houseCodeDetails: '',//传值给面板
                //一页数据
                pageSize:30,
                //一页数据
                pageSizeData: 30,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                //第几个数
                start:0,
                //智能匹配的房源list
                seeHosEntity: [],
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
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight =document.documentElement.clientHeight
                    that.fullHeight =window.fullHeight
                })()
            }
        },
        methods: {
            //分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.pageSize = val;
                this.handleCurrentChange(1)
            },
            //改变每页数量
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getSeeHos();
            },
            getSeeHos() {
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = {"cusCode": vm.cusCodeSeeHos};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customer/selectCusSeeHouseList",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.seeHosEntity = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!customer/selectCusSeeHouseList');
                })
            },
            //打开房源详情
            toHouseDetail(index,rows){
                var vm = this;
                vm.houseDetailOpenOrClose = true;
                vm.houseCodeDetails = rows.houseCode;
                vm.$nextTick(()=>{
                    vm.$refs.hosByDetails.visible = true
                    vm.$refs.hosByDetails.getHouseDetails();
                })
            },
            //关闭面板清空数据
            closeDialog(){
                this.seeHosEntity = [];
            },
        }
    }
</script>

<style scoped>
    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }
    .pre-scrollable-s {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 30px 0px;
        overflow-x: scroll;
        overflow-y: auto;
        /*background-color: #2aabd2;*/
    }
    .page-box-s {
        height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        width: 100%;
        left: 0px;
        bottom: 0px;
        position: absolute;
        z-index: 10;
    }
    .pagex{
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>