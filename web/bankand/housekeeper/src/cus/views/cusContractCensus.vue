<template>
    <div>
        <cus-details ref="cusDetail"/>
        <el-table
                @row-dblclick="openCusDetail"
                :data="cusContractCensusList"
                highlight-current-row
                @selection-change="handleSelectionChange"
                :row-style="rowClass"
                size="mini"
                border
                :height="460">
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
                    prop="cusCode"
                    label="客户编码"
                    align="center"
                    width="80"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="cusName"
                    label="客户姓名"
                    align="center"
                    width="80"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="contractEndTime"
                    label="合同到期时间"
                    align="center"
                    width="130"
                    show-overflow-tooltip>
                <template slot-scope="scope" v-if="scope.row.contractEndTime!=null">
                    {{ scope.row.contractEndTime | time}}
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
                    width="80"
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
                    prop="createName"
                    label="创建专员"
                    align="center"
                    width="80"
                    show-overflow-tooltip>
            </el-table-column>
        </el-table>
        <span style="float: right;margin-right: 10px">
            <el-button  type="primary" size="mini" icon="el-icon-refresh" @click="handleSizeChange(30)" circle/>
            <span style="font-weight: bold">刷新</span>
        </span>
        <!-- 分页 -->
            <el-pagination background
                           @size-change="handleSizeChange"
                           @current-change="handleCurrentChange"
                           :current-page.sync="currentPage"
                           :page-size="pageSizeData"
                           layout="total,pager"
                           :total="totalCount">
            </el-pagination>

    </div>
</template>

<script>
    import cusDetails from "./cusDetails.vue"
    export default {

        components: {
            cusDetails,
        },
        data(){
            return {
                selectRow: [],
                selectData: [],
                fullHeight: document.documentElement.clientHeight,
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
                cusContractCensusList: [],//合同周期list
            }
        },
        create(){
            this.handleCurrentChange(50);
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
            selectData(data) {
                this.selectRow = [];
                if (data.length > 0) {
                    data.forEach((item, index) => {
                        this.selectRow.push(this.cusContractCensusList.indexOf(item));
                    });
                }
            },
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
// 选中筛选结果时候
            handleSelectionChange(data) {
                this.selectData = data;
            },
            // 多选高亮选中行
            rowClass({row, rowIndex}){
                if (this.selectRow.includes(rowIndex)) {
                    return {"background-color": "rgba(185, 221, 249, 0.75)"}
                }
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
                this.getcusContractCensusList();
            },
            //获取房源合同周期
            getcusContractCensusList(){
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize;
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = {};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customer/selectContractCensus",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.cusContractCensusList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!customer/selectContractCensus');
                })
            },
            openCusDetail(row){
                this.$refs.cusDetail.visible = true;
                this.$refs.cusDetail.cusCodeDetails = row.cusCode;//传值给组件
                this.$refs.cusDetail.getCusDetails();
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