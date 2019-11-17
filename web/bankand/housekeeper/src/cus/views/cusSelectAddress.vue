<template>
    <el-dialog  :close-on-click-modal="false"  :style="{height: fullHeight +'px'}"  top="15px" width="58%"
                :visible.sync="visible"
                :before-close = "closeDialog"
                append-to-body>
        <div>
            <!-- 房源按条件查询面板 -->
            <hos-condition ref="hosByCondition" v-if="houseConditionOpenOrClose"
                               @changeHos="serchHosClick"></hos-condition>
            <div class="hst_first_topbar">
                <el-button class="navigation_button_back" size="mini" plain type="warning"
                           icon="el-icon-delete" @click="emptyCondition()">条件清除
                </el-button>
                <el-button class="navigation_button_back" size="mini" plain type="primary"
                           icon="el-icon-search" @click="getMatchHos()">智能匹配
                </el-button>
                <el-button class="navigation_button_back" size="mini" plain type="primary"
                           icon="el-icon-search" @click="searchHos()">按条件查询
                </el-button>

                <el-button class="navigation_button_back" size="mini" plain type="primary"
                           icon="el-icon-search" @click="toCommit()">提交
                </el-button>
            </div>
            <div class="pagex">
                <el-table
                        :data="hosList"
                        stripe
                        border
                        :height="fullHeight-180"
                        style="width: 100%;font-size: 11px"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            type="selection"
                            width="55">
                    </el-table-column>
                    <el-table-column
                            prop="daysNotFollowup"
                            label="未跟进(天)"
                            align="center"
                            width="80"
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
                            label="价格(元/㎡/天)"
                            width="110"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="whereLayer"
                            label="所在层数"
                            align="center"
                            width="80"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="layerHeight"
                            align="center"
                            label="层高(米)"
                            width="80"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="maxElectric"
                            align="center"
                            label="用电量(KVA)"
                            width="100"
                            show-overflow-tooltip>
                    </el-table-column>

                    <el-table-column
                            prop="housePurposeName"
                            label="库房用途"
                            align="center"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>

                    <el-table-column
                            prop="severalLayers"
                            label="共有层数"
                            align="center"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>

                    <el-table-column
                            prop="lastFollowupTime"
                            label="最新跟进时间"
                            align="center"
                            width="160"
                            show-overflow-tooltip>
                        <template slot-scope="scope" v-if="scope.row.lastFollowupTime!=null">
                            {{ scope.row.lastFollowupTime | time}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="houseTypeName"
                            align="center"
                            label="库房类型"
                            width="80"
                            show-overflow-tooltip>
                    </el-table-column>
                </el-table>
            </div>
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
            </div>
        </div>
    </el-dialog>
</template>

<script>
    import hosCondition from  "../../hos/views/hosCondition";
    export default {
        components: {
            hosCondition,
        },
        data() {
            return {
                fullHeight : document.documentElement.clientHeight,
                visible: false,
                cusCodeSelectAddress:'',//客户code
                cusNameSelectAddress: '',//客户name
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

                hosList: [],//房源数据

                selectHosCode: [],//选择的房源
                //按条件查询打开或者关闭
                houseConditionOpenOrClose: false,
                searchHosCondition: {
                    keyword: null,//关键字查
                    houseQuerys: {
                        startEnterTime: null,//入住时间开始
                        endEnterTime: null,//入住时间结束
                        companyName: null,//所属公司
                        forInsdustry: null,//行业性质
                        floorFlag: null,//所在楼层
                        layerHeight: null,//层高
                        hasEia: null,//可环评
                        hasRegistry: null,//可注册
                        hasCertificate: null,//有产证
                        hasShortLease: null,//可短租
                        hasCut: null,//可分割
                        hasInstallCrane: null,//有行车
                        hasParking: null,//有车位
                        daysNotFollowupType: null,
                        daysNotFollowup: null,
                        empName: null,//经纪人姓名
                        minArea: null,//最小面积
                        maxArea: null,//最大面积
                        minPrice: null,//最小租金
                        maxPrice: null,//最大租金
                        housePurpose: null,//房源用途
                        province: null,//省
                        city: null,//市
                        region: null,//区、县
                        street: null,//街道
                        community: null,
                        fireLevel: null,//消防等级
                        houseStatus: null,//房源状态
                        houseOwnerName: null,//业主name
                        houseownerType: '',//业主类型
                        minElectric: null,//最小电量
                        msxElectric: null,//最大电量
                        queryType: null,//查询类型  10全部  1我的  2收藏 3经纪人 4平台 8同部门 7合作房源
                        houseStyle: null,//风格
                    },

                },
                selectAddressEntity: {},
            }
        },
        created(){
            //开始默认查询类型为 全部
            this.searchHosCondition.houseQuerys.queryType = "6";
            this.handleSizeChange(30)
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
            handleSelectionChange(val){
                this.selectHosCode = val;
            },
            //重置条件
            emptyCondition(){
                this.selectHosCode = [];
                this.searchHosCondition.keyword = null//关键字查
                this.searchHosCondition.houseQuerys.minArea = null;//最小面积
                this.searchHosCondition.houseQuerys.maxArea = null;//最大面积
                this.searchHosCondition.houseQuerys.minPrice = null;//最小租金
                this.searchHosCondition.houseQuerys.maxPrice = null;//最大租金
                this.searchHosCondition.houseQuerys.housePurpose = null;//房源用途
                this.searchHosCondition.houseQuerys.province = null;//省
                this.searchHosCondition.houseQuerys.city = null;//市
                this.searchHosCondition.houseQuerys.region = null;//区、县
                this.searchHosCondition.houseQuerys.street = null;//街道
                this.searchHosCondition.houseQuerys.fireLevel = null;//消防等级
                this.searchHosCondition.houseQuerys.houseStatus = null;//房源状态
                this.searchHosCondition.houseQuerys.houseOwnerName = null;//业主name
                this.searchHosCondition.houseQuerys.createDeptCode = null;//所在部门
                this.searchHosCondition.houseQuerys.minElectric = null;//最小电量
                this.searchHosCondition.houseQuerys.msxElectric = null;//最大电量
                this.searchHosCondition.houseQuerys.houseownerType = '';//业主类型
                this.searchHosCondition.houseQuerys.empName = null;
                this.searchHosCondition.houseQuerys.queryType = '';//查询类型
                this.handleSizeChange(30)

            },

            //条件清空
            reStartCondition(){
                this.selectHosCode = []
                this.$refs.hosByCondition.emptyConditions();
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
                let obj = this.searchHosCondition.houseQuerys;
                this.getHouList(obj);
            },
            //自定义查询面板
            searchHos(){
                this.houseConditionOpenOrClose = true;
                this.$nextTick(()=>{
                    this.$refs.hosByCondition.dialogFormVisible = true;
                })
            },
            //自定义查询
            serchHosClick(obj){
                this.searchHosCondition = obj;

                if (obj.houseQuerys.queryType == '') {
                    this.searchHosCondition.houseQuerys.queryType = '6';
                }
                this.getHouList(obj.houseQuerys);
                this.$refs.hosByCondition.dialogFormVisible = false;
                this.houseConditionOpenOrClose = false;
            },
            //查看房源
            getHouList(objaaa){
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize;
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.keyword = this.searchHosCondition.keyword;
                sendObj.houseQuery = objaaa;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "house/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.hosList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!house/query');
                })
            },
            //智能匹配
            getMatchHos(){
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.cusCode = vm.cusCodeSelectAddress;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "cusApply/matchHos",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.hosList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!cusApply/matchHos');
                })
            },
            getHouse(){
                this.$nextTick(() => {
                    this.$refs.hosByCondition.getHouseByCondition();
                })

            },
            closeDialog(done){
                this.selectHosCode = [];
                this.emptyCondition();
                done()
            },
            //提交选址报告
            toCommit(){
                var vm = this;
                var sendObj = {};
                var obj = [];
                for(var i=0;i<vm.selectHosCode.length;i++){
                    obj.push({
                        houseCode : vm.selectHosCode[i].houseCode,
                        houseName : vm.selectHosCode[i].houseName
                    })
                }
                this.start = (this.currentPage - 1) * this.pageSize;
                sendObj.entity = {"cusCode":vm.cusCodeSelectAddress,
                                    "cusName":vm.cusNameSelectAddress,
                                    "selectHouseList":obj};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "selectaddress/create",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.selectAddressEntity = response.data.result;
                    vm.$emit("changeSelectAddress",vm.selectAddressEntity)
                    vm.$notify.success("提交选址报告成功");
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!house/query');
                })
            }
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
    .page-box-s {
        height: 30px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        width: 100%;
        left: 0px;
        bottom: -20px;
        position: absolute;
        z-index: 10;
    }
    .pagex{
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>