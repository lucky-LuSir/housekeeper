<template>
    <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible" style="height: 105%" top="20px"
               width="60%" append-to-body>
        <div class="pre-scrollable">
            <el-form :model="searchHosCondition" ref="searchHosConditions" size="mini">
                <el-row :gutter="50">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <span style="font-weight: 900;font-size: 15px;">按条件查询</span>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="18" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item prop="keyword" label="关键字查">
                            <el-input v-model="searchHosCondition.keyword"
                                      placeholder="标题/省市区街道/电话号/配置/适合行业"></el-input>

                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14">
                        <el-form-item prop="queryType" label="查询类型">
                            <el-radio-group size="mini"
                                            v-model="searchHosCondition.houseQuerys.queryType">
                                <el-radio-button>全部</el-radio-button>
                                <el-radio-button :label="1">我的</el-radio-button>
                                <el-radio-button :label="2">收藏</el-radio-button>
                                <el-radio-button :label="3">经纪人</el-radio-button>
                                <el-radio-button :label="4">平台</el-radio-button>
                                <el-radio-button :label="8">部门</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="选择部门" labelWidth="70px">
                            <el-select @visible-change="loadDeptList" v-model="searchHosCondition.houseQuerys.createDeptCode" filterable placeholder="请选择">
                                <el-option
                                        v-for="item in deptEntity"
                                        :key="item.deptCode"
                                        :label="item.deptName"
                                        :value="item.deptCode">
                                    <span style="float: left">{{ item.deptName }}</span>
                                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode }}</span>
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="0">
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <el-form-item label="所属省份" prop="province">
                            <el-select id="provinceNameCpy"
                                       v-model="searchHosCondition.houseQuerys.province"
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
                                       v-model="searchHosCondition.houseQuerys.city"
                                       @focus="getCitieListCpy(searchHosCondition.houseQuerys.province)"
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
                                       v-model="searchHosCondition.houseQuerys.region"
                                       @focus="getAreaListCpy(searchHosCondition.houseQuerys.city)"
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
                                       v-model="searchHosCondition.houseQuerys.street"
                                       @focus="getStreetListCpy(searchHosCondition.houseQuerys.region)"
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
                                       v-model="searchHosCondition.houseQuerys.community"
                                       @focus="getCommunityListCpy(searchHosCondition.houseQuerys.street)"
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
                <el-row>
                    <el-form-item label="房源楼层" prop="floorFlag">
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                            <el-radio-group v-model="searchHosCondition.houseQuerys.floorFlag">
                                <el-radio-button label="1">底楼</el-radio-button>
                                <el-radio-button label="2">楼上</el-radio-button>
                            </el-radio-group>
                        </el-col>
                    </el-form-item>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item style="padding: 0px;margin: 0px" label="房源用途："
                                      prop="housePurpose">

                            <el-radio-group size="mini"
                                            v-model="searchHosCondition.houseQuerys.housePurpose">
                                <el-radio-button label="1">仓库</el-radio-button>
                                <el-radio-button label="2">生产</el-radio-button>
                                <el-radio-button label="3">仓库生产</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-form-item prop="houseownerType" style="padding: 0px;margin: 0px"
                                  label="业主类型：">
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                            <el-radio-group size="mini"
                                            v-model="searchHosCondition.houseQuerys.houseownerType">
                                <el-radio-button label="1">大房东</el-radio-button>
                                <el-radio-button label="2">二房东</el-radio-button>
                                <el-radio-button label="3">转租户</el-radio-button>
                            </el-radio-group>
                        </el-col>
                    </el-form-item>
                </el-row>
                <el-row>
                    <el-form-item prop="houseStyle" label="房源类型：" class="house-style">
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                            <el-radio-group size="mini"
                                            v-model="searchHosCondition.houseQuerys.houseStyle">
                                <el-radio-button label="1">普通仓库厂房</el-radio-button>
                                <el-radio-button label="2">冷链仓库</el-radio-button>
                                <el-radio-button label="3">高台仓库</el-radio-button>
                                <el-radio-button label="4">危险品仓库</el-radio-button>
                            </el-radio-group>
                        </el-col>
                    </el-form-item>
                </el-row>
                <el-row :gutter="0">
                    <el-form-item prop="daysNotFollowupType" label="未跟进天数">
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                            <el-radio-group
                                    v-model="searchHosCondition.houseQuerys.daysNotFollowupType"
                                    size="mini" @change="notFollowupDay($event)">
                                <el-radio-button label="1">一天内</el-radio-button>
                                <el-radio-button label="2">1-3天</el-radio-button>
                                <el-radio-button label="3">3-5天</el-radio-button>
                                <el-radio-button label="4">5-10天</el-radio-button>
                                <el-radio-button label="5">10天以上</el-radio-button>
                                <el-radio-button label="6">自定义</el-radio-button>
                            </el-radio-group>
                        </el-col>
                    </el-form-item>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-input size="middle"
                                  v-model="searchHosCondition.houseQuerys.daysNotFollowup"
                                  v-show="hosNotFollowupDayShow" placeholder="自定义未跟进天数"></el-input>
                    </el-col>
                </el-row>
                <el-row :gutter="50">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="业主电话" prop="ownerPhone">
                            <el-input v-model="searchHosCondition.houseQuerys.ownerPhone"
                                      placeholder="业主电话"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="业主姓名" prop="ownerCode">
                            <el-input v-model="searchHosCondition.houseQuerys.houseOwnerName"
                                      placeholder="业主姓名"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="50">

                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="所属经纪人" prop="empName">
                            <el-input v-model="searchHosCondition.houseQuerys.empName"
                                      placeholder="所属经纪人"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="房源层高（米）" prop="layerHeight">
                            <el-input v-model="searchHosCondition.houseQuerys.layerHeight"
                                      placeholder="房源层高（米）"/>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="50">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="最小面积" prop="minArea">
                            <el-input v-model="searchHosCondition.houseQuerys.minArea"
                                      placeholder="最小面积(㎡)"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="最大面积" prop="maxArea">
                            <el-input v-model="searchHosCondition.houseQuerys.maxArea"
                                      placeholder="最大面积(㎡)"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="50">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="最小租金" prop="minPrice">
                            <el-input v-model="searchHosCondition.houseQuerys.minPrice"
                                      placeholder="最小租金(元/㎡/天)"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="最大租金" prop="maxPrice">
                            <el-input v-model="searchHosCondition.houseQuerys.maxPrice"
                                      placeholder="最大租金(元/㎡/天)"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="0">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="消防等级" prop="fireLevel">
                            <el-select v-model="searchHosCondition.houseQuerys.fireLevel"
                                       placeholder="消防等级">
                                <el-option label="甲类" value="1"></el-option>
                                <el-option label="乙类" value="2"></el-option>
                                <el-option label="丙类" value="3"></el-option>
                                <el-option label="丁类" value="4"></el-option>
                                <el-option label="戍类" value="5"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="房源状态" prop="houseStatus">
                            <el-select v-model="searchHosCondition.houseQuerys.houseStatus"
                                       placeholder="房源状态">
                                <el-option label="热租中" value="1"></el-option>
                                <el-option label="已成交" value="2"></el-option>
                                <el-option label="全部" value=""></el-option>

                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>


                <el-row :gutter="50">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="最小电量" prop="minElectric">
                            <el-input v-model="searchHosCondition.houseQuerys.minElectric"
                                      placeholder="最小电量(KVA)"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="最大电量" prop="maxElectric">
                            <el-input v-model="searchHosCondition.houseQuerys.maxElectric"
                                      placeholder="最大电量(KVA)"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="0">
                    <el-form-item prop="status" label="房源常规属性">
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                            <el-checkbox-button size="mini"
                                                v-model="searchHosCondition.houseQuerys.hasRegistry">
                                可注册
                            </el-checkbox-button>
                            <el-checkbox-button size="mini"
                                                v-model="searchHosCondition.houseQuerys.hasEia">可环评
                            </el-checkbox-button>
                            <el-checkbox-button size="mini"
                                                v-model="searchHosCondition.houseQuerys.hasCertificate">
                                有产证
                            </el-checkbox-button>
                            <el-checkbox-button size="mini"
                                                v-model="searchHosCondition.houseQuerys.hasCut">可分割
                            </el-checkbox-button>
                            <el-checkbox-button size="mini"
                                                v-model="searchHosCondition.houseQuerys.hasShortLease">
                                可短租
                            </el-checkbox-button>
                            <el-checkbox-button size="mini"
                                                v-model="searchHosCondition.houseQuerys.hasParking">
                                有车位
                            </el-checkbox-button>
                            <el-checkbox-button size="mini"
                                                v-model="searchHosCondition.houseQuerys.hasInstallCrane">
                                可安装行车
                            </el-checkbox-button>
                        </el-col>
                    </el-form-item>
                </el-row>
                <el-row :gutter="50">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="所属公司" prop="companyName">
                            <el-input v-model="searchHosCondition.houseQuerys.companyName"
                                      placeholder="所属公司"/>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="行业性质" prop="forInsdustry">
                            <el-input v-model="searchHosCondition.houseQuerys.forInsdustry"
                                      placeholder="行业性质"/>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="50">
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-date-picker
                                size="mini"
                                style="width: 270px"
                                v-model="searchHosCondition.houseQuerys.startEnterTime"
                                type="date"
                                placeholder="最早入住时间">
                        </el-date-picker>
                    </el-col>

                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-date-picker
                                style="width: 270px"
                                size="mini"
                                v-model="searchHosCondition.houseQuerys.endEnterTime"
                                type="date"
                                placeholder="最晚入住时间">
                        </el-date-picker>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="small" plain type="primary"
                       @click="emptyConditions">重置条件
            </el-button>
            <el-button size="small" plain type="success" @click="getHouseByCondition()">按条件查询
            </el-button>
        </div>
    </el-dialog>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {ElRow},
        data() {
            return {
                dialogFormVisible: false,
                hosNotFollowupDayShow: false,
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
                deptEntity: [],//部门实体
                searchHosCondition: {
                    keyword: '',//关键字查
                    houseQuerys: {
                        createDeptCode:'',
                        startEnterTime: '',//入住时间开始
                        endEnterTime: '',//入住时间结束
                        companyName: '',//所属公司
                        forInsdustry: '',//行业性质
                        floorFlag: '',//所在楼层
                        layerHeight: '',//层高
                        hasEia: '',//可环评
                        hasRegistry: '',//可注册
                        hasCertificate: '',//有产证
                        hasShortLease: '',//可短租
                        hasCut: '',//可分割
                        hasInstallCrane: '',//有行车
                        hasParking: '',//有车位
                        daysNotFollowupType: '',
                        daysNotFollowup: '',
                        empName: '',//经纪人姓名
                        minArea: '',//最小面积
                        maxArea: '',//最大面积
                        minPrice: '',//最小租金
                        maxPrice: '',//最大租金
                        housePurpose: '',//房源用途
                        province: '',//省
                        city: '',//市
                        region: '',//区、县
                        street: '',//街道
                        community: '',
                        fireLevel: '',//消防等级
                        houseStatus: '',//房源状态
                        houseOwnerName: '',//业主name
                        houseownerType: '',//业主类型
                        minElectric: '',//最小电量
                        msxElectric: '',//最大电量
                        queryType: '',//查询类型  10全部  1我的  2收藏 3经纪人 4平台 8同部门 7合作房源
                        houseStyle: '',//风格
                    },
                    deptEntity:[]
                },
            }
        },
        created() {
            this.dialogFormVisible = false;

        },
        mounted() {
            this.loadDeptList();
        },
        methods: {
            getHouseByCondition(){
                this.$emit('changeHos', this.searchHosCondition)
            },

            //重置条件
            emptyConditions(){
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
            },
            //改变省
            changeProvCpy(){
                //清空code
                this.searchHosCondition.houseQuerys.city = null;
                this.searchHosCondition.houseQuerys.region = null;
                this.searchHosCondition.houseQuerys.street = null;
            },
            //改变市
            changeCityCpy(){
                this.searchHosCondition.houseQuerys.region = null;
                this.searchHosCondition.houseQuerys.street = null;
            },
            //改变区域
            changeRegionCpy(){
                this.searchHosCondition.houseQuerys.street = null;
            },
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
            //自定义未跟进天数 与枚举
            notFollowupDay(val){
                if (val == "6") {
                    this.hosNotFollowupDayShow = true;
                    this.searchHosCondition.houseQuerys.daysNotFollowupType = '';
                } else {
                    this.hosNotFollowupDayShow = false;
                    this.searchHosCondition.houseQuerys.daysNotFollowup = null;
                }
            },
        }
    }
</script>

<style scoped>
    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        font-size: 12px;
        max-height: 500px;
        overflow-y: scroll;
        overflow-x: hidden;
    }

    .el-radio + .el-radio {
        margin-left: 10px;
    }

    .el-form-item--mini .el-form-item__content, .el-form-item--mini .el-form-item__label {
        line-height: 14px;
    }

    .house-style {
        padding: 0px;
        margin: 0px;
    }
</style>

