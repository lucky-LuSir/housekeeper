<template>
    <div>
        <!--房源详情面板-->
        <hos-details :houseCodeDetails="houseCodeDetails" ref="hosByDetails"/>
        <el-table
                @row-dblclick="openHosDetail"
                :data="hosContractCensusList"
                stripe
                size="mini"
                border
                :height="460"
                style="width: 100%">
            <el-table-column
                    prop="leaseExpiration"
                    label="合同到期时间"
                    align="center"
                    width="130"
                    show-overflow-tooltip>
                <template slot-scope="scope" v-if="scope.row.leaseExpiration!=null">
                    {{ scope.row.leaseExpiration | time}}
                </template>
            </el-table-column>
            <el-table-column
                    prop="createTime"
                    label="创建时间"
                    align="center"
                    width="130"
                    show-overflow-tooltip>
                <template slot-scope="scope" v-if="scope.row.createTime!=null">
                    {{ scope.row.createTime | time}}
                </template>
            </el-table-column>
            <el-table-column
                    prop="enterTime"
                    label="可入住时间"
                    align="center"
                    width="130"
                    show-overflow-tooltip>
                <template slot-scope="scope" v-if="scope.row.enterTime!=null">
                    {{ scope.row.enterTime | time}}
                </template>
            </el-table-column>
            <el-table-column
                    prop="lastFollowupTime"
                    label="最新跟进时间"
                    align="center"
                    width="130"
                    show-overflow-tooltip>
                <template slot-scope="scope" v-if="scope.row.lastFollowupTime!=null">
                    {{ scope.row.lastFollowupTime | time}}
                </template>
            </el-table-column>
            <el-table-column
                    prop="ownerDeptName"
                    align="center"
                    label="部门名称"
                    width="120"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="empName"
                    align="center"
                    label="服务专员"
                    width="80"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="houseCode"
                    label="房源编码"
                    align="center"
                    width="160"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="houseName"
                    label="房源标题"
                    align="center"
                    width="160"
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="area"
                    label="面积(㎡)"
                    align="center"
                    width="100"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="价格"
                    align="center"
                    width="60"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="unit"
                    label="价格单位"
                    align="center"
                    width="75"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="commission"
                    label="服务费(元)"
                    align="center"
                    width="100"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="propertyFee"
                    label="物业费(元/㎡/月)"
                    align="center"
                    width="120"
                    show-overflow-tooltip>
            </el-table-column>
        </el-table>
        <span style="float: right;margin-right: 10px">
            <el-button  type="primary" size="mini" icon="el-icon-refresh" @click="handleSizeChange(50)" circle/>
            <span style="font-weight: bold">刷新</span>
        </span>
        <!-- 分页 -->
            <el-pagination   background
                             @size-change="handleSizeChange"
                             @current-change="handleCurrentChange"
                             :current-page.sync="currentPage"
                             :page-size="pageSizeData"
                             layout="total,pager"
                             :total="totalCount">
            </el-pagination >
    </div>
</template>

<script>
    import hosDetails from "./hosDetails.vue"
    export default {
        components: {
            hosDetails
        },
       data(){
           return {
               fullHeight: document.documentElement.clientHeight,
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
               hosContractCensusList: [],//合同周期list
               houseCodeDetails: '',//房源详情面板的值
           }
       },
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight
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

           //分页
           handleSizeChange(val) {
               this.pageSizeData = val;
               this.pageSize=val;
               this.handleCurrentChange(1)
           },
           //改变每页数量
           handleCurrentChange(val) {
               this.currentPage = val;
               this.getHosContractCensusList();
           },
           //获取房源合同周期
           getHosContractCensusList(){
               var vm = this;
               var sendObj = {};
               this.start = (this.currentPage-1)*this.pageSize;
               sendObj.start = this.start;
               sendObj.pageSize = this.pageSize;
               sendObj.entity={};
               var option = {
                   method: 'POST',
                   headers: {'content-type': 'application/json'},
                   data: sendObj,
                   url: "house/selectContractCensus",
               };
               this.$ajax(
                   option
               ).then(function (response) {
                   vm.hosContractCensusList= response.data.result.data;
                   vm.totalCount = response.data.result.totalCount;
                   vm.pageSizeData = vm.pageSize;
               }).catch(function (error) {
                   vm.$message.error('页面:获取数据失败!house/selectContractCensus');
               })
           },

           //打开房源详情
           openHosDetail(rows){
               this.houseCodeDetails = rows.houseCode;
               this.$nextTick(() => {
                   this.$refs.hosByDetails.getHouseDetails();
               })
           }
       }
    }
</script>

<style scoped>
    .page-box-s {
        height: 30px;
        background-color: rgb(242, 242, 242);
        display: inline-block;
    }
</style>