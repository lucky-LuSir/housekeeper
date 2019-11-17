<template>
    <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible" style="height: 105%" top="20px"
               width="60%" append-to-body>
        <div class="pre-scrollable">
            <el-form :model="searchCusCondition">
                <el-row :gutter="10">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <span style="font-weight: 900;font-size: 15px;">按条件查询</span>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="关键字查">
                            <el-input v-model="searchCusCondition.keyword" placeholder="客户姓名"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="客户编码">
                            <el-input v-model="searchCusCondition.customerQuery.cusCode" placeholder="客户编码"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                        <el-form-item labelWidth="70px" label="查询类型">
                            <el-radio-group v-model="searchCusCondition.customerQuery.queryType">
                                <el-radio>全部</el-radio>
                                <el-radio :label="1">我的</el-radio>
                                <el-radio :label="4">收藏</el-radio>
                                <el-radio :label="2">经纪人</el-radio>
                                <el-radio :label="3">平台</el-radio>
                                <el-radio :label="5">部门</el-radio>
                                <el-radio :label="6">平台拉私</el-radio>
                                <el-radio :label="7">集中获客</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="选择部门" labelWidth="70px">
                            <el-select @visible-change="loadDeptList()"
                                       v-model="searchCusCondition.customerQuery.createDeptCode"
                                       filterable placeholder="请选择">
                                <el-option
                                        v-for="item in deptEntity"
                                        :key="item.deptCode"
                                        :label="item.deptName"
                                        :value="item.deptCode">
                                    <span style="float: left">{{ item.deptName }}</span>
                                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode
                                        }}</span>
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="最早创建时间" prop="createTimeStart">
                            <el-date-picker
                                    v-model="searchCusCondition.customerQuery.createTimeStart"
                                    type="date"
                                    placeholder="最早创建时间">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="最晚创建时间" prop="createTimeEnd">
                            <el-date-picker
                                    v-model="searchCusCondition.customerQuery.createTimeEnd"
                                    type="date"
                                    placeholder="最晚创建时间">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="0">
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <el-form-item label="所属省份" prop="province">
                            <el-select id="provinceNameCpy"
                                       v-model="searchCusCondition.customerQuery.province"
                                       @visible-change="handNeedProvinceCpy" @change="changeProvCpy"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itProvinceArrCpy"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属城市" prop="city">
                            <el-select id="cityNameCpy"
                                       v-model="searchCusCondition.customerQuery.city"
                                       @focus="getCitieListCpy(searchCusCondition.customerQuery.province)"
                                       @change="changeCityCpy" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itCityArrCpy"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属区域" prop="region">
                            <el-select id="regionNameCpy"
                                       v-model="searchCusCondition.customerQuery.region"
                                       @focus="getAreaListCpy(searchCusCondition.customerQuery.city)"
                                       @change="changeRegionCpy" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itAreaArrCpy"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属街道" prop="street">
                            <el-select id="streetNameCpy"
                                       v-model="searchCusCondition.customerQuery.street"
                                       @focus="getStreetListCpy(searchCusCondition.customerQuery.region)"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itStreetArrCpy"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属社区" prop="community">
                            <el-select id="communityNameCpy"
                                       v-model="searchCusCondition.customerQuery.community"
                                       @focus="getCommunityListCpy(searchCusCondition.customerQuery.street)"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itCommunityArrCpy"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>

                </el-row>
                <el-row :gutter="5">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <span style="font-weight: 900;font-size: 15px;">客户状态：</span>
                        <el-radio-group v-model="searchCusCondition.customerQuery.cusStatus">
                            <el-radio-button label="1">跟进中</el-radio-button>
                            <el-radio-button label="2">已租好</el-radio-button>
                        </el-radio-group>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <span style="font-weight: 900;font-size: 15px;">公开状态：</span>
                        <el-radio-group v-model="searchCusCondition.customerQuery.openFlag">
                            <el-radio-button label="true">已公开</el-radio-button>
                            <el-radio-button label="false">已隐藏</el-radio-button>
                        </el-radio-group>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="客户电话" prop="cusPhone">
                            <el-input v-model="searchCusCondition.customerQuery.cusPhone"
                                      placeholder="客户电话"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="经纪人姓名" prop="empName">
                            <el-input v-model="searchCusCondition.customerQuery.empName"
                                      placeholder="经纪人姓名"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :xs="17" :sm="17" :md="17" :lg="17" :xl="17">
                        <el-form-item label="未跟进天数">
                            <el-radio-group
                                    v-model="searchCusCondition.customerQuery.customerNotFollowupType"
                                    size="small" @change="notFollowupDay($event)">
                                <el-radio-button label="1">一天内</el-radio-button>
                                <el-radio-button label="2">1-3天</el-radio-button>
                                <el-radio-button label="3">3-5天</el-radio-button>
                                <el-radio-button label="4">5-10天</el-radio-button>
                                <el-radio-button label="5">10天以上</el-radio-button>
                                <el-radio-button label="6">自定义</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-input size="middle"
                                  v-model="searchCusCondition.customerQuery.customerNotFollowupDay"
                                  v-show="notFollowupDayDis" placeholder="自定义未跟进天数"></el-input>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="最小需求面积(㎡)" prop="needAcreageStart">
                            <el-input v-model="searchCusCondition.customerQuery.needAcreageStart"
                                      placeholder="最小需求面积(㎡)"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="最大需求面积(㎡)" prop="needAcreageStart">
                            <el-input v-model="searchCusCondition.customerQuery.needAcreageEnd"
                                      placeholder="最大需求面积(㎡)"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="最低价格(元/㎡/天)" prop="needPriceStart">
                            <el-input v-model="searchCusCondition.customerQuery.needPriceStart"
                                      placeholder="最低价格(元/㎡/天)"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="最高价格(元/㎡/天)" prop="needPriceEnd">
                            <el-input v-model="searchCusCondition.customerQuery.needPriceEnd"
                                      placeholder="最高价格(元/㎡/天)"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="最低电量(KVA)" prop="needVoltageStart">
                            <el-input v-model="searchCusCondition.customerQuery.needVoltageStart"
                                      placeholder="最低电量(KVA)"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="最高电量(KVA)" prop="needVoltageEnd">
                            <el-input v-model="searchCusCondition.customerQuery.needVoltageEnd"
                                      placeholder="最高电量(KVA)"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="需求层高(米)" prop="layerHeight">
                            <el-input v-model="searchCusCondition.customerQuery.layerHeight"
                                      placeholder="需求层高(米)"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="需求楼层" prop="layerNum">
                            <el-radio-group v-model="searchCusCondition.customerQuery.layerNum">
                                <el-radio-button label="1">底楼</el-radio-button>
                                <el-radio-button label="2">楼上</el-radio-button>
                                <el-radio-button label="3">全部</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="最早入住时间" prop="enterTimeStart">
                            <el-date-picker
                                    v-model="searchCusCondition.customerQuery.enterTimeStart"
                                    type="date"
                                    placeholder="最早入住时间">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="最晚入住时间" prop="enterTimeEnd">
                            <el-date-picker
                                    v-model="searchCusCondition.customerQuery.enterTimeEnd"
                                    type="date"
                                    placeholder="最晚入住时间">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="所属公司" prop="companyName">
                            <el-input v-model="searchCusCondition.customerQuery.companyName"
                                      placeholder="所属公司"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="行业性质" prop="industry">
                            <el-input v-model="searchCusCondition.customerQuery.industry"
                                      placeholder="行业性质"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <el-button size="small" plain type="primary" @click="emptyConditions()">重置条件</el-button>
        <el-button size="small" plain type="success" @click="getCusByCondition()">按条件查询</el-button>
    </el-dialog>
</template>

<script>
    import ElCol from "element-ui/packages/col/src/col";
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {
            ElRow,
            ElCol
        },
        data() {
            return {
                dialogFormVisible: false,
                //按条件查询面板 里的 自定义未跟进天数
                notFollowupDayDis: false,
                //所属省份
                itProvinceArrCpy: [],
                //所属城市
                itCityArrCpy: [],
                //所属区域
                itAreaArrCpy: [],
                //所属街道
                itStreetArrCpy: [],
                //所属社区
                itCommunityArrCpy: [],
                deptEntity: [
                    {
                        deptCode:'',
                        deptName:''
                    }
                ],
                searchCusCondition: {
                    keyword: null,//关键字查
                    customerQuery: {
                        cusCode:null,
                        createDeptCode: '',
                        queryType: null,//查询类型
                        cusStatus: null,//客户状态
                        openFlag: null,//是否公开状态
                        cusPhone: null,//客户手机号
                        empName: null,//经纪人name
                        customerNotFollowupType: null,//未跟进天数枚举
                        customerNotFollowupDay: null,//未跟进天数
                        needAcreageStart: null,//需求最小面积
                        needAcreageEnd: null,//需求最大面积
                        needVoltageStart: null,//最小电量
                        needVoltageEnd: null,//最大电量
                        needPriceStart: null,//最小价格
                        needPriceEnd: null,//最大价格
                        layerHeight: null,//需求层高
                        layerNum: null,//   楼层类型（1. 底楼 2. 楼上 3. 全部）
                        enterTimeStart: null,// 最小入住时间
                        enterTimeEnd: null,// 最大入住时间
                        createTimeStart:null,
                        createTimeEnd:null,
                        companyName: null,//客户公司
                        industry: null,//行业性质
                    },
                },
            }
        },
        created() {

        },
        mounted(){

        },
        methods: {
            //加载部门数据
            loadDeptList(){
                var vm = this;
                var obj = {};
                var sendObj = {};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "dept/findDeptName",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {

                    }
                    vm.deptEntity = response.data.result;
                }).catch(function (error) {

                });
            },
            getCusByCondition(){
                this.$emit('changeCus', this.searchCusCondition)
            },
            //重置按条件查询条件
            emptyConditions() {
                this.searchCusCondition.keyword = null;
                this.searchCusCondition.customerQuery.queryType = null;//查询类型
                this.searchCusCondition.customerQuery.cusStatus = null;//客户状态
                this.searchCusCondition.customerQuery.openFlag = null;//是否公开状态
                this.searchCusCondition.customerQuery.cusPhone = null;//客户手机号
                this.searchCusCondition.customerQuery.empName = null;//经纪人name
                this.searchCusCondition.customerQuery.customerNotFollowupType = null;//未跟进天数枚举
                this.searchCusCondition.customerQuery.customerNotFollowupDay = null;//未跟进天数
                this.searchCusCondition.customerQuery.needAcreageStart = null;//需求最小面积
                this.searchCusCondition.customerQuery.needAcreageEnd = null;//需求最大面积
                this.searchCusCondition.customerQuery.needVoltageStart = null;//最小电量
                this.searchCusCondition.customerQuery.needVoltageEnd = null;//最大电量
                this.searchCusCondition.customerQuery.needPriceStart = null;//最小价格
                this.searchCusCondition.customerQuery.needPriceEnd = null;//最大价格
                this.searchCusCondition.customerQuery.layerHeight = null;//需求层高
                this.searchCusCondition.customerQuery.layerNum = null;//   楼层类型（1. 底楼 2. 楼上 3. 全部）
                this.searchCusCondition.customerQuery.enterTimeStart = null;// 最小入住时间
                this.searchCusCondition.customerQuery.enterTimeEnd = null;// 最大入住时间
                this.searchCusCondition.customerQuery.createTimeStart = null;
                this.searchCusCondition.customerQuery.createTimeEnd = null;
                this.searchCusCondition.customerQuery.cusCode = null;
                this.searchCusCondition.customerQuery.companyName = null;//客户公司
                this.searchCusCondition.customerQuery.industry = null;//行业性质
            },
            //自定义未跟进天数 与枚举
            notFollowupDay(val){
                if (val == "6") {
                    this.notFollowupDayDis = true;
                    this.searchCusCondition.customerQuery.customerNotFollowupType = null;
                } else {
                    this.notFollowupDayDis = false;
                    this.searchCusCondition.customerQuery.customerNotFollowupDay = '';
                }
            },
            //改变省
            changeProvCpy(){
                //清空code
                this.searchCusCondition.customerQuery.city = null;
                this.searchCusCondition.customerQuery.region = null;
                this.searchCusCondition.customerQuery.street = null;
            },
            //改变市
            changeCityCpy(){
                this.searchCusCondition.customerQuery.region = null;
                this.searchCusCondition.customerQuery.street = null;
            },
            //改变区域
            changeRegionCpy(){
                this.searchCusCondition.customerQuery.street = null;
            },

            /*省份查询*/
            handNeedProvinceCpy(val){
                var vm = this;
                var obj = {};
                obj.parentCode = 0;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itProvinceArrCpy = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                        type: 'error'
                    });
                });
            },
            /*市区查询*/
            getCitieListCpy(aVal) {
                if (aVal != null && aVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCityArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }

            },
            /*区域查询*/
            getAreaListCpy(bVal) {
                if (bVal != null && bVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = bVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itAreaArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
            /*街道查询*/
            getStreetListCpy(aVal){
                if (aVal != null && aVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itStreetArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }


            },
            /*社区查询*/
            getCommunityListCpy(aVal){
                if (aVal != null && aVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCommunityArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
        }
    }
</script>

<style scoped>
    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 500px;
        font-size: 12px;
        overflow-y: scroll;
        overflow-x: hidden;
        /*background-color: #2aabd2;*/
    }
</style>
