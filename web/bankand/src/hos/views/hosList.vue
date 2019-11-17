<template>
    <div>

        <!-- 房源按条件查询面板 -->

        <hos-condition ref="hosByCondition" v-if="houseConditionOpenOrClose"
                       @changeHos="serchHosClick"/>


        <!--房源详情面板-->
        <hos-details :houseCodeDetails="houseCodeDetails"  @refresh = "getHouList"  ref="hosByDetails"/>

        <hos-create ref="hosCreates" v-if="houseLocationVisible" @subToFatherList="stfList">

        </hos-create>

        <div class="hst_first_topbar">
            <el-button v-if="houseCreateBtn" class="navigation_button_back" size="mini" plain
                       type="primary"
                       icon="el-icon-location" @click="openCreateHos()">房源新增
            </el-button>
            <el-button class="navigation_button_back" size="mini" plain type="primary"
                       icon="el-icon-search" @click="searchHos()">按条件查询
            </el-button>
            <el-button class="navigation_button_back" size="mini" plain type="warning"
                       icon="el-icon-delete" @click="emptyCondition()">条件清除
            </el-button>
            <el-button class="navigation_button_back" v-show="HouseListExportBtn" size="mini"
                       type="success"
                       icon="el-icon-download" @click="exportHos()">房源信息导出
            </el-button>
        </div>
        <!--房源导出面板-->
        <el-dialog  :close-on-click-modal="false" :visible.sync="houseExportDialogFormVisible" width="20%" append-to-body>
            <el-form :model="houseExportEntity">
                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="demonstration">创建日期开始时间</div>
                        <el-date-picker
                                v-model="houseExportEntity.createStartTime"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                        <div class="demonstration">创建日期结束时间</div>
                        <el-date-picker
                                v-model="houseExportEntity.createEndTime"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                    </el-col>
                </el-row>
                <el-form-item label="房源状态" prop="floorFlag">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-radio-group v-model="houseExportEntity.houseStatus">
                            <el-radio-button label="1">热租中</el-radio-button>
                            <el-radio-button label="2">已出租</el-radio-button>
                        </el-radio-group>
                    </el-col>
                </el-form-item>
            </el-form>
            <div style="text-align:center;margin-top: 30px;">
                <el-button @click="cleanExportEntity()">清空条件</el-button>
                <el-button type="primary" @click="hosExport()"
                           v-loading.fullscreen.lock="fullscreenLoading">导出
                </el-button>
            </div>
        </el-dialog>
        <el-dialog  :close-on-click-modal="false" :visible.sync="isShowFile" width="75%" center top="10vh">
            <hos-loc-region-list v-if="isShowFile" :loccode="sendLocationCode"
                                 v-on:handleHosMap="acceptHosMap"
                                 v-on:sureMethod="acceptSure"></hos-loc-region-list>
        </el-dialog>

        <!-- 房源查询 -->
        <!--数据表格-->
        <div>
            <el-table
                    @row-dblclick="houseDetails"
                    v-scrollBar
                    @sort-change='sortChange'
                    :data="hosList"
                    highlight-current-row
                    @selection-change="handleSelectionChange"
                    :row-style="rowClass"
                    border
                    :height="fullHeight-180"
                    :default-sort="{prop: 'daysNotFollowup', order: 'ascending'}"
                    class="hos-list">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="daysNotFollowup"
                        label="未跟进(天)"
                        align="center"
                        sortable='custom'
                        width="105"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="houseNumber"
                        label="房源编号"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="houseLocation"
                        label="地址"
                        align="center"
                        width="180"
                        show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{scope.row.houseLocation.provinceName
                    + scope.row.houseLocation.cityName
                    + scope.row.houseLocation.regionName
                    + scope.row.houseLocation.streetName}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="area"
                        label="面积(㎡)"
                        sortable
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="price"
                        align="center"
                        sortable
                        label="价格(元/㎡/天)"
                        width="130"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="whereLayer"
                        label="所在层数"
                        sortable
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="layerHeight"
                        align="center"
                        sortable
                        label="层高(米)"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="maxElectric"
                        align="center"
                        sortable
                        label="用电量(KVA)"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="forInsdustry"
                        label="适合行业"
                        align="center"
                        width="180"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="ownerTypeName"
                        align="center"
                        sortable
                        label="业主属性"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="houseStatusName"
                        align="center"
                        sortable
                        label="状态"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="housePurposeName"
                        label="库房用途"
                        sortable
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="features"
                        align="center"
                        sortable
                        label="房源特色"
                        width="150"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="houseFrom"
                        align="center"
                        label="房源来源"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        align="center"
                        sortable
                        label="创建时间"
                        width="160"
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.createTime!=null">
                        {{ scope.row.createTime | time}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="houseName"
                        label="房源标题"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="cityName"
                        label="所在城市"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="regionName"
                        align="center"
                        label="所在区域"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="streetName"
                        label="所在街道"
                        align="center"
                        width="100"
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
                        prop="fireLevelName"
                        label="消防等级"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="hasEia"
                        align="center"
                        label="是否可环评"
                        width="120"
                        show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{scope.row.hasEia == true ? "有" : "无"}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="hasCut"
                        label="是否可分割"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{scope.row.hasCut == true ? "有" : "无"}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="hasCertificate"
                        align="center"
                        label="是否有产证"
                        width="120"
                        show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{scope.row.hasCertificate == true ? "有" : "无"}}
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        prop="hasRegistry"
                        label="是否可注册"
                        width="120"
                        show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{scope.row.hasRegistry == true ? "有" : "无"}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="empName"
                        align="center"
                        label="服务专员"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="houseCode"
                        label="仓库编码"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="lastFollowupTime"
                        label="最新跟进时间"
                        align="center"
                        sortable
                        width="130"
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.lastFollowupTime!=null">
                        {{ scope.row.lastFollowupTime | time}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="hasShortLease"
                        align="center"
                        label="是否可短租"
                        width="120"
                        show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{scope.row.hasShortLease == true ? "有" : "无"}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="hasPlatform"
                        align="center"
                        label="是否有货台"
                        width="120"
                        show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{scope.row.hasPlatform == true ? "有" : "无"}}
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        prop="hasElevator"
                        label="是否有货梯"
                        width="120"
                        show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{scope.row.hasElevator == true ? "有" : "无"}}
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        prop="hasParking"
                        label="是否有停车位"
                        width="120"
                        show-overflow-tooltip>
                    <template slot-scope="scope">
                        {{scope.row.hasParking == true ? "有" : "无"}}
                    </template>
                </el-table-column>

                <el-table-column
                        prop="houseTypeName"
                        align="center"
                        label="库房类型"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column align="center" fixed="right" label="操作" width="100" height="12">
                    <template slot-scope="scope">
                        <el-button
                                type="success"
                                size="mini"
                                @click="houseDetail(scope.$index,scope.row)">
                            详 情
                        </el-button>
                    </template>

                </el-table-column>
            </el-table>
        </div>




        <!-- 分页 -->
        <div class="page-box">
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
</template>

<script>


    import hosLocRegionList from "./hosLocRegionList";
    import hosDetails from  "./hosDetails";
    import hosCondition from  "./hosCondition";
    import hosCreate from "./hosCreateTwoVersion.vue";

    export default {
        components: {
            hosLocRegionList,
            hosDetails,
            hosCondition,
            hosCreate
        },
        data() {
            return {
                houseLocationVisible:false,
                selectRow: [],
                selectData: [],
                fullHeight: document.documentElement.clientHeight,
                sendLocationCode: 'aaa',
                isShowFile: false,
                //按条件查询组件
                //dialogFormVisible: false,
                //按条件查询打开或者关闭
                houseConditionOpenOrClose: false,
                houseCreateBtn: false,
                houseExportEntity: {
                    createStartTime:null,
                    createEndTime:null,
                    houseStatus:null,
                },
                houseExportDialogFormVisible: false,
                createStartTime: false,
                createEndTime: false,
                fullscreenLoading: false,
                deptEntity: [],

                //房源详情面板
                houseCodeDetails: '',//传给房源详情的值
                //房源信息
                hosList: [],
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
                HouseListExportBtn: false,
                searchHosCondition: {
                    keyword: null,//关键字查
                    houseQuerys: {
                        empName: '',//经纪人姓名
                        minArea: null,//最小面积
                        maxArea: null,//最大面积
                        minPrice: null,//最小租金
                        maxPrice: null,//最大租金
                        housePurpose: null,//房源用途
                        province: null,//省
                        city: null,//市
                        region: null,//区、县
                        street: null,//街道
                        fireLevel: null,//消防等级
                        houseStatus: null,//房源状态
                        houseOwnerName: null,//业主name
                        houseownerType: '',//业主类型
                        minElectric: null,//最小电量
                        msxElectric: null,//最大电量
                        queryType: '',//查询类型  6全部  1我的  2收藏 3经纪人 4平台 8同部门 7合作房源
                        orderFlag: null,
                    },

                },
            }
        },

        created: function () {
            var vm = this;
            //开始默认查询类型为 全部
            this.searchHosCondition.houseQuerys.queryType = "6";
            vm.HouseListExportBtn = vm.$flagMenuStore.judgeMenu("house-list-export-btn");
            vm.houseCreateBtn = vm.$flagMenuStore.judgeMenu("house-create");
            this.handleSizeChange(30)
        },

        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight;
                    that.fullHeight = window.fullHeight
                })()
            }
        },

        watch: {
            selectData(data) {
                this.selectRow = [];
                if (data.length > 0) {
                    data.forEach((item, index) => {
                        this.selectRow.push(this.hosList.indexOf(item));
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
            stfList(aData){
                var flag=false;
                flag=aData=='canclose';
                if(flag){
                    //alert("销毁房源新增");
                    this.houseLocationVisible=false;
                }
            },
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
            sortChange: function (column, prop, order) {
                if (column != null) {
                    if (column.prop == "daysNotFollowup") {
                        if (column.order == "ascending") {
                            this.searchHosCondition.houseQuerys.orderFlag = "6";
                        } else {
                            this.searchHosCondition.houseQuerys.orderFlag = "5";
                        }
                    }
                } else {
                    this.searchHosCondition.houseQuerys.orderFlag = "6";
                }
                this.getHouList(this.searchHosCondition.houseQuerys);
            },
            // 时间戳转换成时间
            // 使用element table组件中的formatter属性，传入一个函数
            timestampToTimex (date) {
                var date = new Date(date) //时间戳为10位需*1000，时间戳为13位的话不需乘1000
                var Y = date.getFullYear() + '-'
                var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
                var D = date.getDate() + ' '
                var h = date.getHours() + ':'
                var m = date.getMinutes() + ':'
                var s = date.getSeconds()
                return Y + M + D + h + m + s
            },
            openDialog(index, rowsRole){
                this.isShowFile = true;
                this.sendLocationCode = rowsRole.locationCode;
            },
            acceptSure(aVal){
                this.isShowFile = aVal.flagshow;
            },
            acceptHosMap(aVal){
                this.isShowFile = aVal.flagshow;

            },

            //重置条件
            emptyCondition(){
                this.searchHosCondition.keyword = null;//关键字查
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
                this.searchHosCondition.houseQuerys.houseownerType = null;//业主类型
                this.searchHosCondition.houseQuerys.queryType = null;//查询类型
                this.searchHosCondition.houseQuerys.empName = null;
                this.searchHosCondition.houseQuerys.daysNotFollowup = null;
                this.searchHosCondition.houseQuerys.daysNotFollowupType = null;
                this.searchHosCondition.houseQuerys.hosNotFollowupDayShow = false;
                this.searchHosCondition.houseQuerys.houseStyle = null;
                //清空房源属性条件
                this.searchHosCondition.houseQuerys.hasEia = null;
                this.searchHosCondition.houseQuerys.hasRegistry = null;
                this.searchHosCondition.houseQuerys.hasCertificate = null;
                this.searchHosCondition.houseQuerys.hasCut = null;
                this.searchHosCondition.houseQuerys.hasShortLease = null;
                this.searchHosCondition.houseQuerys.hasParking = null;
                this.searchHosCondition.houseQuerys.hasInstallCrane = null;
                this.searchHosCondition.houseQuerys.hasShortLease = null;
                this.searchHosCondition.houseQuerys.ownerPhone = null;

                this.searchHosCondition.houseQuerys.startEnterTime = null;//入住时间开始
                this.searchHosCondition.houseQuerys.endEnterTime = null;//入住时间结束
                this.searchHosCondition.houseQuerys.companyName = null;//所属公司
                this.searchHosCondition.houseQuerys.forInsdustry = null;//行业性质
                this.searchHosCondition.houseQuerys.floorFlag = null;//所在楼层
                this.searchHosCondition.houseQuerys.layerHeight = null;//层高
                this.handleSizeChange(30)
            },

            //新增房源
            openCreateHos(){
                var vm = this;
                vm.houseLocationVisible = true;


                vm.$nextTick(() => {
                    console.log(vm.$refs.hosCreates);
                    vm.$refs.hosCreates.hctvisible = true;
                    console.log(vm.$refs.hosCreates.hctvisible);
                    vm.$refs.hosCreates.title = "新增房源";
                })
            },

            //自定义查询面板
            searchHos(){
                this.houseConditionOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.hosByCondition.dialogFormVisible = true;
                })
            },
            exportHos(){
                this.houseExportDialogFormVisible = true;
            },
            hosExport(){
                var createStartTime = this.houseExportEntity.createStartTime;
                var createEndTime = this.houseExportEntity.createEndTime;
                var houseStatus = this.houseExportEntity.houseStatus;
                if (createStartTime < createEndTime) {
                    var url="/housekeeper/house/houseExport?createStartTime=" + createStartTime + "&createEndTime=" + createEndTime;
                    // var url="http://localhost:6789/housekeeper/house/houseExport?createStartTime=" + createStartTime + "&createEndTime=" + createEndTime;

                    if (null!=houseStatus){
                        url=url+ "&houseStatus=" + houseStatus;
                    }
                    window.location.href = url;
                    this.houseExportDialogFormVisible = false;
                    this.fullscreenLoading = true;
                    setTimeout(() => {
                        this.fullscreenLoading = false;
                    }, 5000);
                } else {
                    alert("时间不对");
                }
            },
            //清空导出条件
            cleanExportEntity(){
                this.houseExportEntity = {};
            },
            //自定义查询
            serchHosClick(obj){
                this.searchHosCondition = obj;

                if (obj.houseQuerys.queryType == '') {
                    this.searchHosCondition.houseQuerys.queryType = '6';
                }
                this.getHouList(obj.houseQuerys);
                this.$nextTick(() => {
                    this.$refs.hosByCondition.dialogFormVisible = false;
                })
            },
            //查询全部房源
            getHouList(objaaa){
                if(objaaa == null){
                    objaaa = this.searchHosCondition.houseQuerys;
                }
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
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

            //房源详情
            houseDetail(index, rows){
                this.houseCodeDetails = rows.houseCode;
                this.$nextTick(() => {
                    this.$refs.hosByDetails.getHouseDetails();
                })
            },

            //房源详情
            houseDetails(rows){
                this.houseCodeDetails = rows.houseCode;
                this.$nextTick(() => {
                    this.$refs.hosByDetails.getHouseDetails();
                })
            },


            //关闭房源详情
            /*    closeDialog(){
             this.$refs.hosByDetails.closeDialog();
             },*/
            getHouse(){
                this.$nextTick(() => {
                    this.$refs.hosByCondition.getHouseByCondition();
                })

            }
        }

    }

</script>

<style scoped>
    .hst_first_topbar {
        margin: 5px 0px 10px 15px;
        padding: 0px 0px 0px 0px;
    }

    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }

    .page-box {
        height: 40px;
        padding-top: 0px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }

    .hos-list {
        padding: 0px 0px;
        font-size: 12px;
        bottom: 0px;
    }
</style>

