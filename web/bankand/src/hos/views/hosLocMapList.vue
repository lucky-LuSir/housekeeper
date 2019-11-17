<template slot-scope="scope">
    <div css="root" ref="myRoot" style="height: 100vh;overflow-y: hidden;">


        <div class="ht_first_topbar">



        </div>





        <div class="now_page">

            <div class="top_bar list-inline">
                <!--<el-select id="provinceNameBase" size="small" ref="provinceNameBase"  v-model="baseAreaObj.provinceCode" @visible-change="handNeedProvinceBase" @change="changeProvBase" placeholder="请选择">-->
                    <!--<el-option-->
                            <!--v-for="(item, indexkey) in itProvinceArrBase"-->
                            <!--:key="item.areaCode"-->
                            <!--:value="item.areaCode"-->
                            <!--:label="item.name"-->
                    <!--&gt;-->
                    <!--</el-option>-->
                <!--</el-select>-->
                <!--<el-select id="cityNameBase" size="small"  v-model="baseAreaObj.cityCode" @focus="getCitieListBase(baseAreaObj.provinceCode)" @change="changeCityBase"   placeholder="请选择">-->
                    <!--<el-option-->
                            <!--v-for="(item, indexkey) in itCityArrBase"-->
                            <!--:key="item.areaCode"-->
                            <!--:value="item.areaCode"-->
                            <!--:label="item.name">-->
                    <!--</el-option>-->
                <!--</el-select>-->
                <!--<el-select id="regionNameCpy" size="small" v-model="baseAreaObj.regionCode" @focus="getAreaListCpy(baseAreaObj.cityCode)"  @change="changeRegionCpy"    placeholder="请选择">-->
                    <!--<el-option-->
                            <!--v-for="(item, indexkey) in itAreaArrCpy"-->
                            <!--:key="item.areaCode"-->
                            <!--:value="item.areaCode"-->
                            <!--:label="item.name">-->
                    <!--</el-option>-->
                <!--</el-select>-->
                <!--<el-select class="bar_in_street"  id="streetNameCpy" size="small" v-model="baseAreaObj.streetCode" @focus="getStreetListCpy(baseAreaObj.regionCode)" @change="changeStreetCpy"  placeholder="请选择">-->
                    <!--<el-option-->
                            <!--v-for="(item, indexkey) in itStreetArrCpy"-->
                            <!--:key="item.areaCode"-->
                            <!--:value="item.areaCode"-->
                            <!--:label="item.name">-->
                    <!--</el-option>-->
                <!--</el-select>-->

                <!--<el-button class="bar_in_btn" size="small" plain type="primary"  @click="queryQuickCity" >查询</el-button>-->
                <!--<el-button class="bar_in_btn" size="small" plain type="warning"  @click="reset" >重置</el-button>-->
                <el-button class="bar_in_btn" size="small" plain type="primary"  @click="dialogVisible = true">设置默认</el-button>
                <el-button class="bar_in_btn" size="small" plain type="primary"  icon="el-icon-search" @click="searchHos()">按条件查询</el-button>
                    <el-select id="cityNameBase" size="small"  v-model="baseAreaObj.cityCode"  @change="handleAllchangeCity"   placeholder="请选择">
                        <el-option
                                v-for="(item, indexkey) in itCityArrBase"
                                :key="item.areaCode"
                                :value="item.areaCode"
                                :label="item.name">
                        </el-option>
                    </el-select>



                <!--<div class="count_title_div_kong"></div>-->
                <div class="count_title_div">
                    <el-tag v-show="(totalCount)">位置数量:</el-tag>
                    <el-tag v-show="(totalCount)" style="margin-right: 13px;width: auto;">{{totalCount}} 个</el-tag>
                    <!--<span>缩放层级&#45;&#45;{{beforezoomN}}</span>-->
                    <span></span>

                    <!--<el-tag>定位城市&#45;&#45;{{geoCityNow}}</el-tag>-->
                    <span style="margin-left: 28px;">{{baseAreaObj.cityName}}:&nbsp&nbsp&nbsp园区总数</span>
                    <el-tag v-show="(houseDictionaryEntity.parkTotal)" style="width: auto;">{{houseDictionaryEntity.parkTotal}} 个</el-tag>
                    <span style="margin-left: 28px;">房源总数</span>
                    <el-tag v-show="(houseDictionaryEntity.houseTotal)" style="width: auto;">{{houseDictionaryEntity.houseTotal}} 套</el-tag>
                    <span style="margin-left: 28px;">面积总数</span>
                    <el-tag v-show="(houseDictionaryEntity.houseAreaTotal)" style="width: auto;">{{houseDictionaryEntity.houseAreaTotal}} 平方</el-tag>
                </div>




            </div>
            <!--后一个行-->
            <div class="first_block ">
                <div class="msg_show_name">
                    <!--<span style="margin-right: 15px;">已查询条件:</span>-->
                    <!--<el-tag v-show="(baseAreaObj.provinceName)" style="width: 70px">{{baseAreaObj.provinceName}}</el-tag>
                    <el-tag v-show="(baseAreaObj.cityName)" style="width: 70px">{{baseAreaObj.cityName}}</el-tag>
                    <el-tag v-show="(baseAreaObj.regionName)" style="width: 70px">{{baseAreaObj.regionName}}</el-tag>
                    <el-tag v-show="(baseAreaObj.streetName)" style="width: auto;">{{baseAreaObj.streetName}}</el-tag>-->
                    <!--<el-tag v-show="(keyWord)" style="width: auto;">{{keyWord}}</el-tag>-->
                    <!--<b style="margin-left: 30px">(及其下辖地区)</b>-->


                    <!--<span style="margin-left: 23px;"></span>-->


                    <!--<p class="title_city_btn" style="float: right;margin-right: 150px;">加载位置数量:&nbsp&nbsp&nbsp{{totalCount}}<span class="title_red"></span></p>-->
                </div>
            </div>
            <div class="content_bar col-sm-12 col-md-12">
                <div id="allmapLoc" v-loading="loading" style="overflow:hidden;zoom:1;position:relative;height:85%;">
                    <div id="mapLocDiv" style="height:110%;-webkit-transition: all 0.5s ease-in-out;transition: all 0.5s ease-in-out;"></div>
                </div>

            </div>


        </div >


        <el-dialog  :close-on-click-modal="false"
                title="设置默认省市区"
                :visible.sync="dialogVisible"
                width="280px"
                append-to-body>

            <div align="center">
                <!--<el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">-->
                    <!--<div label="所属省份" prop="provinceCode">-->
                        <!--<el-select id="provinceNameBase" ref="provinceNameBase"  v-model="baseAreaObj.provinceCode" @visible-change="handNeedProvinceBase" @change="changeProvBase" placeholder="请选择">-->
                            <!--<el-option-->
                                    <!--v-for="(item, indexkey) in itProvinceArrBase"-->
                                    <!--:key="item.areaCode"-->
                                    <!--:value="item.areaCode"-->
                                    <!--:label="item.name"-->
                            <!--&gt;-->
                            <!--</el-option>-->
                        <!--</el-select>-->
                    <!--</div>-->
                <!--</el-col>-->
                <!--<el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">-->
                    <!--<div label="所属城市" prop="cityCode">-->
                        <!--<el-select id="cityNameBase"  v-model="baseAreaObj.cityCode" @focus="getCitieListBase(baseAreaObj.provinceCode)" @change="changeCityBase"   placeholder="请选择">-->
                            <!--<el-option-->
                                    <!--v-for="(item, indexkey) in itCityArrBase"-->
                                    <!--:key="item.areaCode"-->
                                    <!--:value="item.areaCode"-->
                                    <!--:label="item.name">-->
                            <!--</el-option>-->
                        <!--</el-select>-->
                    <!--</div>-->
                <!--</el-col>-->
                <!--<el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">-->
                    <!--<div label="所属区域">-->
                        <!--<el-select id="regionNameCpy" v-model="baseAreaObj.regionCode" @focus="getAreaListCpy(baseAreaObj.cityCode)"  @change="changeRegionCpy"    placeholder="请选择">-->
                            <!--<el-option-->
                                    <!--v-for="(item, indexkey) in itAreaArrCpy"-->
                                    <!--:key="item.areaCode"-->
                                    <!--:value="item.areaCode"-->
                                    <!--:label="item.name">-->
                            <!--</el-option>-->
                        <!--</el-select>-->
                    <!--</div>-->
                <!--</el-col>-->
                    <div label="城市" prop="cityCode">
                        <el-select id="cityNameBase" size="small"  v-model="baseAreaObj.cityCode"  @change="handleAllchangeCity"   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itCityArrBase"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </div>

            </div>

            <div align="center">

                    <!--<el-button @click="queryQuickCity" >查询</el-button>-->
                    <div v-if="baseAreaObj.cityName!=null">您默认城市为:{{baseAreaObj.cityName}}</div>
                     <div v-if="baseAreaObj.cityName ==null">您默认城市为定位为:{{geoCityNow}}</div>
                    <el-button @click="cleanQuickCity" size="small" type="warning" plain>移除</el-button>
                    <el-button @click="addQuickCity" size="small" type="primary" plain >确定</el-button>

            </div>
            <!--<el-row style="margin-top: 20px;color: #409EFF;">-->
                <!--<span style="color: #409EFF;">便捷城市</span>-->
            <!--</el-row>-->

            <el-row style="margin-top: 10px;color: #409EFF;">
                <!--<a @click="cityBtnClick('杭州市')" >杭州市</a>-->
                <div v-for="qcity in quickArr" style="float: left;margin-right: 5px;">
                    <a @click="cityBtnClick(qcity)" >{{qcity}}</a>
                </div>


            </el-row>

            <!--<el-row style="margin-top: 10px;color: #409EFF;">
                <span>热门城市</span>
            </el-row>

            <el-row >
                <a   @click="cityBtnClick('北京市')" >北京市</a >
                <a   @click="cityBtnClick('上海市')" >上海市</a >
                <a   @click="cityBtnClick('广州市')" >广州市</a >
                <a   @click="cityBtnClick('深圳市')" >深圳市</a >
            </el-row>

            <el-row style="margin-top: 10px;color: #409EFF;">
                <span>常用城市</span>
            </el-row>

            <el-row>
                <a @click="cityBtnClick('苏州市')" >苏州市</a>
                <a @click="cityBtnClick('杭州市')" >杭州市</a>
                <a @click="cityBtnClick('嘉兴市')" >嘉兴市</a>
                <a @click="cityBtnClick('成都市')" >成都市</a>
                <a @click="cityBtnClick('宁波市')" >宁波市</a>



            </el-row>
            <el-row style="margin-top: 10px;">
                <a @click="cityBtnClick('绍兴市')" >绍兴市</a>
                <a @click="cityBtnClick('无锡市')" >无锡市</a>
                <a @click="cityBtnClick('南京市')" >南京市</a>
                <a @click="cityBtnClick('合肥市')" >合肥市</a>
                <a @click="cityBtnClick('温州市')" >温州市</a>
            </el-row>

            <el-row style="margin-top: 10px;">

                <a @click="cityBtnClick('常州市')" >常州市</a>
                <a @click="cityBtnClick('武汉市')" >武汉市</a>
                <a @click="cityBtnClick('济南市')" >济南市</a>
                <a @click="cityBtnClick('东莞市')" >东莞市</a>
                <a @click="cityBtnClick('德州市')" >德州市</a>
            </el-row>

            <el-row style="margin-top: 10px;">

                <a @click="cityBtnClick('湖州市')" >湖州市</a>
                <a @click="cityBtnClick('青岛市')" >青岛市</a>
                <a @click="cityBtnClick('郑州市')" >郑州市</a>
                <a @click="cityBtnClick('天津市')" >天津市</a>
                <a @click="cityBtnClick('南通市')" >南通市</a>
            </el-row>
            <el-row style="margin-top: 10px;">

                <a @click="cityBtnClick('长沙市')" >长沙市</a>
            </el-row>-->





            <!--<div style="text-align: center;margin-bottom: 30px;">-->
            <!--<span>-->
            <!--<el-button @click="dialogVisible = false">取 消</el-button>-->
            <!--<el-button type="primary" @click="dialogVisible = false">确 定</el-button>-->
            <!--</span>-->
            <!--</div>-->
        </el-dialog>

        <!-- 房源按条件查询面板 -->
        <hos-condition ref="hosByCondition" v-if="houseConditionOpenOrClose"
                       @changeHos="serchHosClick"/>
        <!--<el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible"   width="60%" append-to-body>-->
            <!--<hos-loc-map-condition ref="hosByCondition" v-if="houseConditionOpenOrClose" @changeHos="serchHosClick"></hos-loc-map-condition>-->

            <!--<div align="center">-->
            <!--<el-button size="small" plain type="primary"-->
                       <!--@click="reStartCondition">重置条件-->
            <!--</el-button>-->
            <!--<el-button size="small" plain type="success"-->
                       <!--@click="getHouse()">按条件查询-->
            <!--</el-button>-->
          <!--</div>-->
        <!--</el-dialog>-->

        <!--房源详情面板-->
        <hos-details  :houseCodeDetails="houseCodeDetails" v-if="dialogFormHouseDetail" ref="hosByDetails"/>

    </div>
</template>



<script scoped>
    import FileSaver from 'file-saver';
    import icon_locat from '../../../static/hkpImgIcon/icon_locat.svg';
    import ElRow from "element-ui/packages/row/src/row";
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    import hosCondition from  "./hosCondition";
    import hosDetails from  "./hosDetails";
    export default {
        components: {
            ElButton,
            ElRow,
            hosCondition,
            hosDetails

        },
        created: function () {


            var vm = this;
            vm.handleAllCity();
            this.baseAreaObj.provinceName=="";
            this.baseAreaObj.cityName=="";
            this.baseAreaObj.regionName=="";
            this.baseAreaObj.streetName=="";


        },
        props:["loccode" ,"sendLocName"],
        data() {
            return {
                beforezoomN:0,
                geoCityNow:null,
                cityEntity:{
                    "id": null,
                    "createTime": null,
                    "lastUpdateTime": null,
                    "isDeleted": null,
                    "areaCode": null,
                    "parentCode": null,
                    "name": null,
                    "shortName": null,
                    "level": null,
                    "sort": null,
                    "status": null,
                    "longitude": null,
                    "latitude": null
                },
                togetherList:null,
                zoomNumb:null,
                mapvLayerTotal:null,
                leftDownLng:null,
                leftDownLat:null,
                rightUpLng:null,
                rightUpLat:null,
                houseQuery:{
                    hasEia:null,//可环评
                    hasRegistry:null,//可注册
                    hasCertificate:null,//有产证
                    hasShortLease:null,//可短租
                    hasCut:null,//可分割
                    hasInstallCrane:null,//有行车
                    hasParking:null,//有车位
                    daysNotFollowupType:null,
                    daysNotFollowup:null,
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
                hosList:[],
                search:{
                    keyword: '',//关键字查
                    houseQuerys: {
                        hasEia:null,//可环评
                        hasRegistry:null,//可注册
                        hasCertificate:null,//有产证
                        hasShortLease:null,//可短租
                        hasCut:null,//可分割
                        hasInstallCrane:null,//有行车
                        hasParking:null,//有车位
                        daysNotFollowupType:null,
                        daysNotFollowup:null,
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
                    }
                    },
                houseCodeDetails: '',//传给详情面板的值
                dialogFormHouseDetail:false,
                dialogFormVisible:false,
                houseConditionOpenOrClose:false,
                houseDictionaryEntity:{
                    "parkTotal": 0,
                    "houseTotal": 0,
                    "houseAreaTotal": 0
                },
                lonlyPolygon:{},
                locAnaEntity:{},
                reportEntities:[],
                tableHeads:[],
                itStreetArrCpy:[],
                itAreaArrCpy:[],
                quickArr:[],
                //全国基本地区
                baseAreaObj:{

                    provinceCode:"",
                    cityCode:"",
                    regionCode:"",
                    streetCode:"",

                    provinceName:"",
                    cityName:null,
                    regionName:"",
                    streetName:"",

                },
                dialogVisible: false,
                keyWord:'无锡市',
                locTbList:[],
                hosTbList:[],
                totalCount:'',
                selectPointObj:{
                    lng:'',
                    lat:''
                },
                //加载转动条
                loading: false,//true开启转动条,false关闭
                selectWordName:'',
                searchPanelList:[],
                wordBaiduName:'上海虹桥火车站',
                canSeeRegionList:[],
                bdmap:'',
                cityName:'上海市',
                isShowFile: false,
                overlays:"",
                floatDivHeight: 50,
                itProvinceArrBase:[],
                itCityArrBase:[],
                houseLocationObj:{
                    "id": 33,
                    "createCode": "",
                    "createDeptCode": "",
                    "lastUpdateCode": "",
                    "isDeleted": false,
                    "locationCode": "",
                    "locationAlias": "",
                    "province": "",
                    "provinceName": "",
                    "city": "",
                    "cityName": "",
                    "region": "",
                    "regionName": "",
                    "street": "",
                    "streetName": "",
                    "detailAddress": "",
                    "doorNumber": "",
                    "longitude": 116.331398,
                    "latitude": 39.897445,
                    "trafficFacilities": "",
                    "fileList": [],
                    "locRegionList": []
                },
                hosLocRegion:{
                    "locationCode":"位置编码",
                    "locationAlias":"地址别名",
                    "num":1,
                    "longitude":100,
                    "latitude":200
                },
                locRegionList:[],
                locRegionArr:[],
                "orderfollowTbList":[],
            }
        },
        mounted(){


            this.sendani();

            var vm = {};
            vm = this;
            vm.loading = true;
            //, { enableMapClick: false }   禁用地图景点点击
            var map = new BMap.Map('mapLocDiv', { minZoom : 8,
                maxZoom : 19, enableMapClick: false });
            //十字架型
            map.setDefaultCursor("default");
            vm.bdmap= map;
            //定位城市start
            function myFun(result){
                var cityName = result.name;
                vm.geoCityNow = result.name;
                // map.setCenter(cityName);
                console.log("当前定位城市:"+cityName);
//                vm.$notify({
//                    title:cityName+"___当前定位",
//                    message: '',
//                    type: 'success'
//                });
                console.log(result);
                //vm.keyWord = cityName;//111222测试城市
                //start定位附加逻辑
                if(vm.itCityArrBase.length>1){


                    let hkpRegionCityObj = JSON.parse(localStorage.getItem("hkpRegionCityMapList"));
                    if(hkpRegionCityObj == null)
                    {
                        vm.dialogVisible=true;
                        hkpRegionCityObj={
                            provinceCode:"",
                            cityCode:"",
                            regionCode:"",
                            streetCode:"",

                            provinceName:"",
                            cityName:"",
                            regionName:"",
                            streetName:"",

                        };


                        for(var i=0;i<vm.itCityArrBase.length;i++){
                            let cityObj = vm.itCityArrBase[i];
                            if(cityObj.name==vm.geoCityNow){
                                vm.baseAreaObj.cityCode = cityObj.areaCode;

                                //赋值市name
                                let areaObj = {};
                                areaObj = this.itCityArrBase.find((item)=>{
                                    return item.areaCode === aval;
                                });
                                vm.baseAreaObj.cityName= areaObj.name;

                                //查询单个城市实体
                            }
                        }
                        if(vm.baseAreaObj.cityCode==null){
                            alert("定位城市不在公司开启城市中,手动选择默认城市");
                        }

                    }else{

                        //alert("进入第二次");
                        vm.dialogVisible = false;
//                        let provinceObj = {};
//                        provinceObj.areaCode = hkpRegionCityObj.provinceCode;
//                        provinceObj.name = hkpRegionCityObj.provinceName;
//                        vm.itProvinceArrBase.push(provinceObj);

//                        let cityObj = {};
//                        cityObj.areaCode = hkpRegionCityObj.cityCode;
//                        cityObj.name = hkpRegionCityObj.cityName;
//                        vm.itCityArrBase.push(cityObj);

//                        let areaObj = {};
//                        areaObj.areaCode = hkpRegionCityObj.regionCode;
//                        areaObj.name = hkpRegionCityObj.regionName;
//                        vm.itAreaArrCpy.push(areaObj);

                        vm.baseAreaObj = hkpRegionCityObj;
                        //开始浏览器定位,加载百度初始化方法
                        //this.loadHosLocDetail();
                        //开始浏览器定位,加载百度初始化方法
                        vm.handleBaiduMap();
                        vm.handleAllchangeCity(vm.baseAreaObj.cityCode);

                        //开始将省市区内统计的信息展示
                        vm.basicNetHosDicTotal();
                    }

                    //vm.baseAreaObj = hkpRegionCityObj;
                }

                //end定位附加逻辑

                // vm.keyWord = cityName;
            }
            var myCity = new BMap.LocalCity();
            myCity.get(myFun);

            //定位城市end
            //加载所有城市列表












        },

        updated(){

            var vm = this;
            vm.zoomNumb = vm.bdmap.getZoom();
//            let houseQuery = vm.search.houseQuerys;
//            vm.quickGetHouList(houseQuery);
        },
        methods: {

            reStartCondition(){
                this.$refs.hosByCondition.emptyConditions();
            },
            getHouse(){
                this.$nextTick(() => {
                    this.$refs.hosByCondition.getHouseByCondition();
                })

            },
            sendani(){

                var vm = this;

            },
            animation:function(rowid){
                // alert("vue中方法看看"+rowid);
                var vm = this;
                // console.log(vm.hosTbList);
                var obj = vm.hosTbList[rowid-1];
                var houseCode = obj.houseCode;
                this.dialogFormHouseDetail = true;
                this.houseCodeDetails = houseCode;
                this.$nextTick(() => {
                    this.$refs.hosByDetails.visible = true
                    this.$refs.hosByDetails.getHouseDetails();
                })
                },
            //房源详情
            houseDetail(index,rows){
                this.dialogFormHouseDetail = true;
                this.houseCodeDetails = rows.houseCode;
                this.$nextTick(() => {
                    this.$refs.hosByDetails.visible = true
                    this.$refs.hosByDetails.getHouseDetails();
                })
            },
            quickGetHouList(obj){
                var vm = this;


                //vm.bdmap.clearOverlays();
                vm.loading = true;
                vm.dialogFormVisible = false;
                let NowzoomN = vm.bdmap.getZoom();
                if(NowzoomN>=15){

                    this.getHouList(obj);
                }else{

                    vm.bdmap.clearOverlays();
                    this.getTogetherHos(obj);
                }


            },
            onlyDrag(obj){
                var vm = this;
                vm.loading = false;
                let NowzoomN = vm.bdmap.getZoom();
                if(NowzoomN>=15){

                    this.getHouList(obj);
                }else{
                    vm.loading = false;
                    vm.dialogFormVisible = false;

                    //this.getTogetherHos(obj);
                }


            },
            //查询聚合房源
            getTogetherHos(objaaa){


                var vm = this;
//                vm.$notify({
//                    title: '调了查询接口',
//                    message: '',
//                    type: 'success'
//                });
                if(vm.mapvLayerTotal !=null){

                    vm.mapvLayerTotal.destroy();
//                    vm.$notify({
//                        title: '刷新了mapv',
//                        message: '',
//                        type: 'success'
//                    });
                }
                var sendObj = {};
                sendObj.leftDown = {};
                sendObj.leftDown.longitude = vm.leftDownLng;
                sendObj.leftDown.latitude = vm.leftDownLat;

                sendObj.rightUp = {};
                sendObj.rightUp.longitude = vm.rightUpLng;
                sendObj.rightUp.latitude = vm.rightUpLat;
                sendObj.keyword = this.search.keyword;
                vm.houseQuery = {
                    hasEia:null,//可环评
                    hasRegistry:null,//可注册
                    hasCertificate:null,//有产证
                    hasShortLease:null,//可短租
                    hasCut:null,//可分割
                    hasInstallCrane:null,//有行车
                    hasParking:null,//有车位
                    daysNotFollowupType:null,
                    daysNotFollowup:null,
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
                };
                vm.houseQuery = objaaa;
                if (vm.houseQuery.queryType == '' || vm.houseQuery.queryType==null) {
//                    vm.$notify({
//                        title: '查询条件筛选全部',
//                        message: '',
//                        type: 'success'
//                    });
                    vm.houseQuery.queryType = '6';
                }
                vm.houseQuery.locationCode = null;
                sendObj.houseQuery = vm.houseQuery;

                let NowzoomN = vm.bdmap.getZoom();
                if(NowzoomN<=12) {
                    sendObj.level = 3;
                }else if(NowzoomN>12){
                    sendObj.level = 4;
                }

//                vm.$notify({
//                    title: '缩放__'+NowzoomN+'__级别',
//                    message: 'level是__'+sendObj.level+'__等级',
//                    type: 'success'
//                });

                sendObj.cityOut = this.baseAreaObj.cityCode;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "house/togetherCount",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                     //alert("总条数");

//                    vm.$notify({
//                        title: '总条数',
//                        message: response.data.result.length+'个',
//                        type: 'success'
//                    });
                    vm.togetherList = response.data.result;
                    vm.showTogetherHos();
                    console.log("togetherCount数量:");
                    console.log(response.data.result);


                }).catch(function (error) {
                    vm.$notify({
                        title: '警告:请求失败',
                        message: 'togetherCount,请求失败',
                        type: 'warning'
                    });
                    vm.loading = false;
                })
            },

            //显示聚合图
            showTogetherHos(){
                var vm = this;
                vm.togetherList;
                for(var i=0;i<vm.togetherList.length;i++){
                    let tgObj = vm.togetherList[i];
                    var point = new BMap.Point(tgObj.longitude, tgObj.latitude);
                    var myIcon = new BMap.Icon("../../../static/hkpImgIcon/png_circle_red.png", new BMap.Size(80,80));
                    var markerNowPoi = new BMap.Marker(point,{icon:myIcon});        // 创建标注
                    vm.bdmap.addOverlay(markerNowPoi);
                    let msg ="<p>"+ tgObj.name+"</p>";
                    msg = msg+"<p>&nbsp"+ tgObj.count+"</p>";
                    var label = new BMap.Label(msg,{offset:new BMap.Size(20,30)});
                    label.setStyle({color:"white",fontSize:"12px",border:"none",background: "transparent",filter:"alpha(opacity=0)"});
                    markerNowPoi.setLabel(label);
                    markerNowPoi.addEventListener("mouseover",function (e) {
                        console.log("移入");
                        console.log(e);
                        var voiletIcon = new BMap.Icon("../../../static/hkpImgIcon/png_circle_yellow.png", new BMap.Size(80,80));
                        this.setIcon(voiletIcon);
                    });
                    markerNowPoi.addEventListener("mouseout",function (e) {
                        console.log("移出");
                        console.log(e);
                        var voiletIcon = new BMap.Icon("../../../static/hkpImgIcon/png_circle_red.png", new BMap.Size(80,80));
                        this.setIcon(voiletIcon);
                    });
                    markerNowPoi.addEventListener("click",function (e) {
                        console.log("移入");
                        console.log(e);

                        let NowzoomN = vm.bdmap.getZoom();
                        if(NowzoomN<=12){
                            vm.bdmap.centerAndZoom(e.point,13);
                        }else if(NowzoomN>12){
                            vm.bdmap.centerAndZoom(e.point,16);
                        }


                        let houseQuery = vm.search.houseQuerys;
                        vm.loading = true;
                        vm.quickGetHouList(houseQuery);
                    });
                }
                vm.loading = false;
                vm.dialogFormVisible = false;
            },
            //查询全部房源
            getHouList(objaaa){

                var vm = this;
//                vm.$notify({
//                    title: '调了查询接口',
//                    message: '',
//                    type: 'success'
//                });
                var sendObj = {};
                sendObj.leftDown = {};
                sendObj.leftDown.longitude = vm.leftDownLng;
                sendObj.leftDown.latitude = vm.leftDownLat;

                sendObj.rightUp = {};
                sendObj.rightUp.longitude = vm.rightUpLng;
                sendObj.rightUp.latitude = vm.rightUpLat;
                sendObj.keyword = this.search.keyword;
                vm.houseQuery = {
                    hasEia:null,//可环评
                    hasRegistry:null,//可注册
                    hasCertificate:null,//有产证
                    hasShortLease:null,//可短租
                    hasCut:null,//可分割
                    hasInstallCrane:null,//有行车
                    hasParking:null,//有车位
                    daysNotFollowupType:null,
                    daysNotFollowup:null,
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
                };
                vm.houseQuery = objaaa;
                if (vm.houseQuery.queryType == '' || vm.houseQuery.queryType==null) {
//                    vm.$notify({
//                        title: '查询条件筛选全部',
//                        message: '',
//                        type: 'success'
//                    });
                    vm.houseQuery.queryType = '6';
                }
                vm.houseQuery.locationCode = null;
                sendObj.houseQuery = vm.houseQuery;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "house/queryLocByHos",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.locTbList = [];
                    vm.locTbList = response.data.result;
                    vm.totalCount = vm.locTbList.length;
                    // alert("总条数"+vm.totalCount);
                    vm.showAllPoint();
                    vm.showMapvFunc(vm.locTbList);
                    console.log("请求的位置");
                    vm.loading = false;
                    vm.dialogFormVisible = false;
//                    vm.$notify({
//                        title: 'queryLocByHos成功',
//                        message: '',
//                        type: 'success'
//                    });
                }).catch(function (error) {
                    vm.$notify({
                        title: '警告:请求失败',
                        message: 'queryLocByHos,查询全部房源请求失败',
                        type: 'warning'
                    });
                    vm.loading = false;
                })
            },

            //自定义查询面板
            searchHos(){
                this.dialogFormVisible = true;
                this.houseConditionOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.hosByCondition.dialogFormVisible=true;
                })
            },
            //自定义查询
            serchHosClick(obj){
                var vm = this;
                this.search = obj;

                if (obj.houseQuerys.queryType == '') {
                    this.search.houseQuerys.queryType = '6';
                }


                // if(obj.houseQuerys.queryType==''){
                //     this.search.houseQuerys.queryType='8'; //8表示默认查部门
                // }
                console.log("看看调用查询条件子组件返回的对象");
                console.log(obj);
                //根据条件中的省市区调到对应位置
//                vm.$notify({
//                        title: '省',
//                        message: obj.houseQuerys.province,
//                        type: 'success'
//                    });
//                vm.$notify({
//                    title: '市',
//                    message: obj.houseQuerys.city,
//                    type: 'success'
//                });
//                vm.$notify({
//                    title: '区',
//                    message: obj.houseQuerys.region,
//                    type: 'success'
//                });

                let areaCode = null;
                if(obj.houseQuerys.province !=""&&obj.houseQuerys.province !=null){
                    areaCode = obj.houseQuerys.province;
                    if(obj.houseQuerys.city!=""&&obj.houseQuerys.city !=null){
                        areaCode = obj.houseQuerys.city;
                        if(obj.houseQuerys.region!=""&&obj.houseQuerys.region !=null){
                            areaCode = obj.houseQuerys.region;

                            if(obj.houseQuerys.street!=""&&obj.houseQuerys.street !=null){
                                areaCode = obj.houseQuerys.street;


                            }

                        }

                    }

                }
                if(areaCode!=null){
                    this.basicNetCenter(areaCode,obj.houseQuerys);
                }else{
                    this.quickGetHouList(obj.houseQuerys);
                }

                this.$nextTick(() => {
                    this.$refs.hosByCondition.dialogFormVisible=false;
                })




                //this.getHouList(obj.houseQuerys);
                // this.dialogFormVisible = false;
                // this.houseConditionOpenOrClose = false;
            },
            /*市区中心点查询*/
            basicNetCenter(aVal,houseQuerysNet) {
                if(aVal != "" && aVal!= null){
                    var vm=this;
                    var obj = { };
                    obj.areaCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"baseArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        let baseAreaObj = response.data.result;
//                        vm.$notify({
//                            title: baseAreaObj.longitude,
//                            message: baseAreaObj.latitude,
//                            type: 'success'
//                        });

                        //var poi = new BMap.Point(vm.houseLocationObj.longitude,vm.houseLocationObj.latitude);
                        let poi = new BMap.Point(baseAreaObj.longitude,baseAreaObj.latitude);
                        vm.bdmap.centerAndZoom(poi, 16);//设置中心点坐标和地图级别
                        var bs = vm.bdmap.getBounds();   //获取可视区域
                        var bssw = bs.getSouthWest();   //可视区域左下角
                        var bsne = bs.getNorthEast();   //可视区域右上角
                        //alert("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
                        vm.basicNetSeeArea(bssw.lng,bssw.lat,bsne.lng,bsne.lat);
                        vm.leftDownLng = bssw.lng;
                        vm.leftDownLat = bssw.lat;
                        vm.rightUpLng  = bsne.lng;
                        vm.rightUpLat  = bsne.lat;
                        let houseQuery = houseQuerysNet;
                        vm.quickGetHouList(houseQuery);
                    }).catch(function (error) {
                        vm.$notify({
                            showClose: true,
                            message: '页面:查询市区信息失败!baseArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }

            },
            reset(){
                this.baseAreaObj.provinceCode="";
                this.baseAreaObj.provinceName="";
                this.baseAreaObj.cityCode="";
                this.baseAreaObj.cityName="";
                this.baseAreaObj.regionCode="";
                this.baseAreaObj.regionName="";
                this.baseAreaObj.streetCode="";
                this.baseAreaObj.streetName="";

                this.itStreetArrCpy=[];
                this.itAreaArrCpy=[];
                this.itCityArrBase=[];

            },
            basicNetHosLocDetail(aVal){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var sendObj = {};
                    var obj = {};
                    obj.locationCode = aVal;
//                    sendObj.start = 0;
//                    sendObj.pageSize = 50;
                    sendObj.entity = obj;

                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "houseLocation/detail",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.houseLocationObj = response.data.result;
                        console.log("请求的位置详情");
                        console.log(vm.houseLocationObj);
                        //alert("请求详情");
                        // alert("经度"+vm.houseLocationObj.longitude+"纬度"+vm.houseLocationObj.latitude);
                        //alert(vm.houseLocationObj.locationCode);
                        vm.handlePolygon();
                    }).catch(function (error) {
                        //vm.$message.error('页面:获取数据失败!post/query');
                        alert("页面:获取数据失败!houseLocation/detail");
                    });
                }
            },
            showMapvFunc(aVal){
                var vm = this;
                var bmap = this.bdmap;

                if(vm.mapvLayerTotal !=null){

                    vm.mapvLayerTotal.destroy();
//                    vm.$notify({
//                        title: '刷新了mapv',
//                        message: '',
//                        type: 'success'
//                    });
                }
                // 第一步创建mapv示例
//                var mapv = new Mapv({
//                    drawTypeControl: true,
//                    map: bmap  // 百度地图的map实例
//                });

                //自定义图标
                var img = new Image();
                img.src = '../../../static/hkpImgIcon/png_redloc.png';
                var subimg = new Image();
                subimg.src = '../../../static/hkpImgIcon/png_blue2loc.png';
                var dataTotal = []; // 取城市的点来做示例展示的点数据
                for(var i = 0;i<aVal.length;i++){
                    let pointLoc = {};
                    pointLoc.lat = aVal[i].latitude;
                    pointLoc.lng = aVal[i].longitude;
                    pointLoc.count = 0.6 * 10;
                    //data.push(pointLoc);

//                    console.log("aVal位置编码-------");
//                    console.log(aVal[i]);
                    dataTotal.push({
                        geometry: {
                            locationCode:aVal[i].locationCode,
                            locationAlias:aVal[i].locationAlias,
                            type: 'Point',
                            coordinates: [pointLoc.lng,pointLoc.lat]
                        },
                        icon: img
                    });
                }


                var dataSetTotal = new mapv.DataSet(dataTotal);

                console.log("看看新版的气泡图");
                console.log(mapv);

                var optionsTotal = {
                    zIndex: 1, // 层级
                    draw: 'icon',
                    size: 11, // 大小值
                    unit: 'px', // 'px': 以像素为单位绘制,默认值。'm': 以米制为单位绘制，会跟随地图比例放大缩小
                    fillStyle: "rgba(255, 64, 87, 0.8)" ,// 填充颜色rgba(255, 64, 87, 0.8)
                    updateCallback: function (time) { // 重绘回调函数，如果是时间动画、返回当前帧的时间
                    },
                    methods: { // 一些事件回调函数
                        click: function (item) { // 点击事件，返回对应点击元素的对象值
                            //console.log(item);
//                            alert("点击覆盖mapv层");
                            var counti = 0;
                            if(item!=null && counti==0){
                                counti = counti+1;
                                console.log("点击的item详情");
                                console.log(item);
                                console.log(item.geometry.coordinates[0]);
                                vm.$notify({
                                    title: item.geometry.locationAlias,
                                    message: '',
                                    type: 'success'
                                });
                                let bmapPart = vm.bdmap;
                                let dataPart = [];
                                item.icon = subimg;
                                dataPart.push(item);
                                let dataSetPart = new mapv.DataSet(dataPart);
                                let optionsPart = {
                                    zIndex: 100, // 层级
                                    draw: 'icon',
                                    size: 11, // 大小值
                                    unit: 'px', // 'px': 以像素为单位绘制,默认值。'm': 以米制为单位绘制，会跟随地图比例放大缩小
                                    fillStyle: "#409EFF"// 填充颜色rgba(255, 64, 87, 0.8)

                                }

                                let mapvLayerPart = new mapv.baiduMapLayer(bmapPart, dataSetPart, optionsPart);
                                dataPart = [];
                                var aVal = item.geometry.locationCode;
                                vm.basicNetHosByLoc(aVal,item);
                                vm.basicNetHosLocDetail(aVal);


                            }
                        },
                        mousemove: function(item) { // 鼠标移动事件，对应鼠标经过的元素对象值

                            if(item!=null){
                                vm.bdmap.setDefaultCursor("pointer");
                            }
                            if(item==null){
                                vm.bdmap.setDefaultCursor("default");
                            }

                        }
                    },


                }



                var mapvLayerTotal = new mapv.baiduMapLayer(bmap, dataSetTotal, optionsTotal);
                vm.mapvLayerTotal = mapvLayerTotal;

                //alert("结束-----看看新版的气泡图");

            },
            openDialog(){
                this.dialogVisible = true;
                let arr = JSON.parse(localStorage.getItem("quickArr"));
                if(arr == null){ arr=[]; }
                this.quickArr = arr;
            },
            cleanQuickCity(){
                //this.quickArr = [];

                let vm ={};
                vm = this;
                localStorage.removeItem("hkpRegionCityMapList");
                vm.dialogVisible = false;

            },
            queryQuickCity(){
//                alert("添加");
                //alert(this.baseAreaObj.cityName);
                this.loading = true;
                if(this.baseAreaObj.provinceName!=""){
                    this.keyWord = this.baseAreaObj.provinceName;
                    if(this.baseAreaObj.cityName!=""){
                        this.keyWord = this.baseAreaObj.cityName;
                        if(this.baseAreaObj.regionName!=""){
                            this.keyWord = this.baseAreaObj.regionName;

                            if(this.baseAreaObj.streetName!=""){
                                this.keyWord = this.baseAreaObj.streetName;


                            }

                        }

                    }

                }
                this.loadInputCity();
                this.basicNetHosDicTotal();

            },
            addQuickCity(){
//                alert("添加");
                let vm = {};
                vm = this;
//                alert(this.baseAreaObj.cityName);
//                 if(this.quickArr.length ==null){
//                     this.quickArr.push(vm.baseAreaObj.cityName);
//                     localStorage.setItem("quickArr", JSON.stringify(vm.quickArr));
//                 }
//                 let flag =false;
//                 for ( var i = 0; i <this.quickArr.length; i++){
//                     if(this.quickArr[i]===vm.baseAreaObj.cityName){flag = true;}
//                 }
//                 if(flag == true){alert("已存在便捷城市中.");}else {
//                     if(this.quickArr.length >15 ){
//                         alert("便捷城市不能超过15个,需要添加新的,请先点击移除按钮");
//                     }else{
//                         this.quickArr.push(vm.baseAreaObj.cityName);
//                         localStorage.removeItem("quickArr");
//                         localStorage.setItem("quickArr", JSON.stringify(vm.quickArr));
//                     }
//                 }

                localStorage.setItem("hkpRegionCityMapList", JSON.stringify(vm.baseAreaObj));
                this.dialogVisible = false;

                //开始浏览器定位,加载百度初始化方法
                vm.handleBaiduMap();
                vm.handleAllchangeCity(vm.baseAreaObj.cityCode);
                //开始将省市区内统计的信息展示
                vm.basicNetHosDicTotal();

            },
            changeStreetCpy(aval){
                //清空code

                //赋值区域name
                let areaObj = {};
                areaObj = this.itStreetArrCpy.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.streetName= areaObj.name;
                this.queryQuickCity();
            },
            changeRegionCpy(aval){
                //清空code
                this.baseAreaObj.streetCode="";
                this.baseAreaObj.streetName="";
                //赋值区域name
                let areaObj = {};
                areaObj = this.itAreaArrCpy.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.regionName= areaObj.name;
                this.queryQuickCity();
            },
            //从所有城市中选择1个
            handleAllchangeCity(aval){
//                alert("开始选城市");
                var vm = this;
                vm.totalCount=null;
                //赋值市name
                let areaObj = {};
                areaObj = this.itCityArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                vm.baseAreaObj.cityName= areaObj.name;

                //查询单个城市实体
                vm.getOneCity();

                //查询
                let houseQuery = vm.search.houseQuerys;
                vm.quickGetHouList(houseQuery);
                //开始将省市区内统计的信息展示
                vm.basicNetHosDicTotal();

            },
            changeCityBase(aval){
//                alert("开始选城市");
                var vm = this;
                this.baseAreaObj.regionCode="";
                this.baseAreaObj.streetCode="";

                //赋值市name
                let areaObj = {};
                areaObj = this.itCityArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                vm.baseAreaObj.cityName= areaObj.name;
                //清空name
                vm.baseAreaObj.regionName="";
                vm.baseAreaObj.streetName="";
                //alert(vm.baseAreaObj.cityName);
//                alert(vm.baseAreaObj.cityCode);

            },
            /*街道查询*/
            getStreetListCpy(aVal){
                if(aVal != "" && aVal != null){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itStreetArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询街道信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }


            },
            /*区域查询*/
            getAreaListCpy(bVal) {
                if(bVal != "" && bVal != null){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = bVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itAreaArrCpy = response.data.result;
                    }).catch(function (error) {

                        vm.$notify({
                            title: '警告:queryNoPage请求失败',
                            message: '页面:查询区域信息失败!cpyArea/queryNoPage',
                            type: 'warning'
                        });
                    });
                }
            },
            /*所有市区查询*/
            handleAllCity() {
                    var vm=this;
                    var obj = { };
                    obj.level = 2;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCityArrBase = response.data.result;
//                        vm.$notify({
//                            title:vm.itCityArrBase.length+"___个开启城市",
//                            message: '',
//                            type: 'success'
//                        });


                    }).catch(function (error) {
                        vm.$notify({
                            title: '警告',
                            message: '页面:查询市区信息失败!baseArea/queryNoPage',
                            type: 'warning'
                        });
                    });

            },
            /*所有市区查询*/
            getOneCity() {
                var vm=this;
                var obj = { };
                obj.areaCode = this.baseAreaObj.cityCode;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url:"cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    let cityEntity = response.data.result;
                    vm.cityEntity = response.data.result;
                    //定位到查询城市的point上
                    let poi = new BMap.Point(vm.cityEntity.longitude,vm.cityEntity.latitude);
                    vm.bdmap.centerAndZoom(poi, 10);//设置中心点坐标和地图级别
                    //一个城市对象
                }).catch(function (error) {
                    vm.$notify({
                        title: '警告',
                        message: '页面:查询市区信息失败!baseArea/queryNoPage',
                        type: 'warning'
                    });
                });

            },
            /*市区查询*/
            getCitieListBase(aVal) {
                if(aVal != "" && aVal != null){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCityArrBase = response.data.result;
                    }).catch(function (error) {
                        vm.$notify({
                            title: '警告',
                            message: '页面:查询市区信息失败!baseArea/queryNoPage',
                            type: 'warning'
                        });
                    });
                }

            },
            changeProvBase(aval){
                //清空code
                this.baseAreaObj.cityCode="";
                this.baseAreaObj.regionCode="";
                this.baseAreaObj.streetCode="";

                //赋值省name
                let areaObj = {};
                areaObj = this.itProvinceArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.provinceName= areaObj.name;
                //清空name
                this.baseAreaObj.cityName="";
                this.baseAreaObj.regionName="";
                this.baseAreaObj.streetName="";


            },
            /*省份查询*/
            handNeedProvinceBase(val){
                var vm=this;
                var obj = { };
                obj.parentCode = 0;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url:"cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itProvinceArrBase = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询省份信息失败!baseArea/queryNoPage',
                        type: 'error'
                    });
                });
            },
            cityBtnClick(aVal){
                // alert(aVal);
                this.loading = true;
                this.keyWord = aVal;
                this.loadInputCity();
                this.dialogVisible = false;
            },
            /*市区查询*/

            remove_overlay(){
                //alert("刷新了");
                this.bdmap.clearOverlays();
                // this.loadHosLocDetail();
                // this.selectWordName = '';
                // this.searchPanelList = [];
            },


            handlePolygon(){

                var vm = this;

                vm.bdmap.removeOverlay(vm.lonlyPolygon);
                //vm.bdmap.clearOverlays();
                var netRegionArr = [];
                netRegionArr = vm.houseLocationObj.locRegionList;
                var regionArr = [];
                // alert("进入");
                for(var i=0;i<netRegionArr.length;i++){
                    // alert("循环纬度"+netRegionArr[i].longitude);
                    var region1 = new BMap.Point(netRegionArr[i].longitude,netRegionArr[i].latitude);
                    regionArr.push(region1);
                }

                vm.lonlyPolygon = {};
                vm.lonlyPolygon = new BMap.Polygon(regionArr, {strokeColor:"red", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
                vm.bdmap.addOverlay(vm.lonlyPolygon);           //增加多边形
            },

            handleBaiduMap(){


                this.isShowFile = true;

                var vm = {};
                vm = this;
                //alert(this.isShowFile);
                // 百度地图API功能
                let map = this.bdmap;



                var poi = new BMap.Point(vm.houseLocationObj.longitude,vm.houseLocationObj.latitude);
                map.centerAndZoom(poi, 10);//设置中心点坐标和地图级别
                this.bdmap.centerAndZoom(vm.baseAreaObj.cityName,12);      // 用城市名设置地图中心点
                map.enableScrollWheelZoom(); //启用鼠标滚动对地图放大缩小
                map.disableDoubleClickZoom();//禁用双击放大
                var marker = new BMap.Marker(poi);        // 创建标注
                map.addOverlay(marker);                     // 将标注添加到地图中
                marker.enableDragging();   // 点标注可拖拽
                marker.addEventListener("dragend", function(e){
                    alert("当前位置：" + e.point.lng + ", " + e.point.lat);
                    vm.houseLocationObj.longitude = e.point.lng;
                    vm.houseLocationObj.latitude = e.point.lat;
                });
                marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                //添加卫星图start
                var mapType1 = new BMap.MapTypeControl(
                    {
                        mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP],
                        anchor: BMAP_ANCHOR_TOP_RIGHT
                    }
                );

                var overView = new BMap.OverviewMapControl();
                var overViewOpen = new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_TOP_LEFT});

                map.addControl(mapType1);          //2D图，混合图
                map.addControl(overView);          //添加默认缩略地图控件
                //map.addControl(overViewOpen);      //右下角，打开
                //添加卫星图end

                //添加比例尺start
                var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});// 左上角，添加比例尺
                var top_left_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_ZOOM});  //左上角，添加默认缩放平移控件
                /*缩放控件type有四种类型:
                 BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮*/
                map.addControl(top_left_control);
                map.addControl(top_left_navigation);

                //添加比例尺end



                var netRegionArr = [];
                netRegionArr = vm.houseLocationObj.locRegionList;
                var regionArr = [];
                // alert("进入");
                for(var i=0;i<netRegionArr.length;i++){
                    // alert("循环纬度"+netRegionArr[i].longitude);
                    var region1 = new BMap.Point(netRegionArr[i].longitude,netRegionArr[i].latitude);
                    regionArr.push(region1);
                }

                var polygon = new BMap.Polygon(regionArr, {strokeColor:"red", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
                map.addOverlay(polygon);           //增加多边形


                //鼠标绘制完成回调方法   获取各个点的经纬度
                var overlays = [];
                var overlaycomplete = function(e){
                    overlays.push(e.overlay);
                    var path = e.overlay.getPath();//Array<Point> 返回多边型的点数组
                    vm.locRegionArr = path;
                    for(var i=0;i<path.length;i++){
                        console.log("lng:"+path[i].lng+"\n lat:"+path[i].lat);

                    }
                };
                var styleOptions = {
                    strokeColor:"red",    //边线颜色。
                    fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
                    strokeWeight: 3,       //边线的宽度，以像素为单位。
                    strokeOpacity: 0.8,       //边线透明度，取值范围0 - 1。
                    fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
                    strokeStyle: 'solid' //边线的样式，solid或dashed。
                }
                //实例化鼠标绘制工具
                /*var drawingManager = new BMapLib.DrawingManager(map, {
                 isOpen: false, //是否开启绘制模式
                 enableDrawingTool: true, //是否显示工具栏
                 drawingToolOptions: {
                 anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
                 offset: new BMap.Size(5, 5), //偏离值
                 },
                 //circleOptions: styleOptions, //圆的样式
                 //polylineOptions: styleOptions, //线的样式
                 polygonOptions: styleOptions, //多边形的样式
                 //rectangleOptions: styleOptions //矩形的样式
                 });*/

                //实例化鼠标绘制工具
//                var drawingManager = new BMapLib.DrawingManager(map, {
//                    isOpen: false, //是否开启绘制模式
//                    enableDrawingTool: true, //是否显示工具栏
//                    drawingMode:BMAP_DRAWING_RECTANGLE,//绘制模式  多边形
//                    drawingToolOptions: {
//                        anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
//                        offset: new BMap.Size(5, 5), //偏离值
//                        drawingModes:[
//                            BMAP_DRAWING_POLYGON
//                        ]
//                    },
//                    polygonOptions: styleOptions //多边形的样式
//                });
//
//                //添加鼠标绘制工具监听事件，用于获取绘制结果
//                drawingManager.addEventListener('overlaycomplete', overlaycomplete);
                function clearAll() {
                    for(var i = 0; i < overlays.length; i++){
                        map.removeOverlay(overlays[i]);
                    }
                    overlays.length = 0
                }

                //-----------------------------------搜索框start-------------------------------

                var bs = map.getBounds();   //获取可视区域
                var bssw = bs.getSouthWest();   //可视区域左下角
                var bsne = bs.getNorthEast();   //可视区域右上角
                // alert("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
                //vm.basicNetSeeArea(bssw.lng,bssw.lat,bsne.lng,bsne.lat);



                //------------------------------------搜索框end-----------------------------------

                map.addEventListener("zoomend", function(){
                    // alert("地图缩放至：" + this.getZoom() + "级");
                    var bs = map.getBounds();   //获取可视区域
                    var bssw = bs.getSouthWest();   //可视区域左下角
                    var bsne = bs.getNorthEast();   //可视区域右上角
                    //alert("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
                    let NowzoomN = vm.bdmap.getZoom();
                    if(NowzoomN>=15){
                        vm.basicNetSeeArea(bssw.lng,bssw.lat,bsne.lng,bsne.lat);
                    }
                    vm.leftDownLng = bssw.lng;
                    vm.leftDownLat = bssw.lat;
                    vm.rightUpLng  = bsne.lng;
                    vm.rightUpLat  = bsne.lat;
                    let houseQuery = vm.search.houseQuerys;
                        if(vm.beforezoomN>=8&&vm.beforezoomN<=12){
                            if(NowzoomN>12){
                                vm.quickGetHouList(houseQuery);
                                vm.bdmap.clearOverlays();
                            }
                        }
                    if(vm.beforezoomN>=13&&vm.beforezoomN<=14){
                        if(NowzoomN<13||NowzoomN>14){
                            vm.quickGetHouList(houseQuery);
                            vm.bdmap.clearOverlays();
                        }
                    }
                    if(NowzoomN>=14&&vm.beforezoomN>=14){
                        vm.quickGetHouList(houseQuery);
                    }

                    vm.beforezoomN=NowzoomN;
                    vm.totalCount=null;

                });

                //地图拖动回调事件
                map.addEventListener("dragend", function showInfo(){
//                    var cp = map.getCenter();
//                    alert(cp.lng + "," + cp.lat);
                    var bs = map.getBounds();   //获取可视区域
                    var bssw = bs.getSouthWest();   //可视区域左下角
                    var bsne = bs.getNorthEast();   //可视区域右上角
                    //alert("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
                    let NowzoomN = vm.bdmap.getZoom();
                    if(NowzoomN>=15){
                        vm.basicNetSeeArea(bssw.lng,bssw.lat,bsne.lng,bsne.lat);
                    }

                    vm.leftDownLng = bssw.lng;
                    vm.leftDownLat = bssw.lat;
                    vm.rightUpLng  = bsne.lng;
                    vm.rightUpLat  = bsne.lat;
                    let houseQuery = vm.search.houseQuerys;
                    vm.onlyDrag(houseQuery);
                });
            },

            basicNetLocQuery(){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    obj.street= vm.keyWord;
                    if(this.baseAreaObj.provinceName!=""){
                        obj.street = this.baseAreaObj.provinceCode;
                        if(this.baseAreaObj.cityName!=""){
                            obj.street = this.baseAreaObj.cityCode;
                            if(this.baseAreaObj.regionName!=""){
                                obj.street = this.baseAreaObj.regionCode;

                                if(this.baseAreaObj.streetName!=""){
                                    obj.street = this.baseAreaObj.streetCode;


                                }

                            }

                        }

                    }
                    //val当前页传入值
                    sendObj.entity = obj;
                    // sendObj.keyWord = vm.cityName;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "houseLocation/queryMapLoc",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.locTbList = response.data.result;
                        vm.totalCount = vm.locTbList.length;
                        // alert("总条数"+vm.totalCount);
                        vm.showAllPoint();
                        vm.showMapvFunc(vm.locTbList);
                        console.log("请求的位置");
                        vm.loading = false;

                    }).catch(function (error) {
                        vm.$notify({
                            title: '警告:请求失败',
                            message: 'queryMapLoc,请求失败',
                            type: 'warning'
                        });
                        vm.loading = false;
                    });
                }
            },

            basicNetHosDicTotal(){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var sendObj = {};
                    //val当前页传入值
                    //sendObj.code = vm.baseAreaObj.;
//                    if(this.baseAreaObj.provinceName!=""){
//                        sendObj.code = this.baseAreaObj.provinceCode;
//                        if(this.baseAreaObj.cityName!=""){
//                            sendObj.code = this.baseAreaObj.cityCode;
//                            if(this.baseAreaObj.regionName!=""){
//                                sendObj.code = this.baseAreaObj.regionCode;
//
//                                if(this.baseAreaObj.streetName!=""){
//                                    sendObj.code = this.baseAreaObj.streetCode;
//
//
//                                }
//
//                            }
//
//                        }
//
//                    }

                    sendObj.code = this.baseAreaObj.cityCode;
                    // sendObj.keyWord = vm.cityName;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "report/getHosDicByCurCode",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.houseDictionaryEntity = response.data.result;
                        var parkTotal = vm.houseDictionaryEntity.parkTotal;
                        //alert(parkTotal.toString());
                        // vm.$notify({
                        //     title: '成功',
                        //     message: vm.houseDictionaryEntity.parkTotal+" ",
                        //     type: 'success'
                        // });
//                        var num = vm.bdmap.getZoom();
//                        vm.bdmap.setZoom(num+1);

                    }).catch(function (error) {
                        //vm.$message.error('页面:获取数据失败!post/query');
//                        alert("页面:685行查询数据失败!houseLocation/query");
                        //vm.loading = false;
                    });
                }
            },

            basicNetHosByLoc(aVal,eVal){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    //val当前页传入值
                    obj.locationCode = aVal;
                    sendObj.houseQuery = vm.houseQuery;
                    sendObj.houseQuery.locationCode = obj.locationCode;
                    // sendObj.keyWord = vm.cityName;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "house/queryHosByLoc",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        var aFunc = vm.animation;
                        FixedTime.testApproves(aFunc);
                        vm.hosTbList = response.data.result;
                        var poi = new BMap.Point(eVal.geometry.coordinates[0],eVal.geometry.coordinates[1]);

                        var titleMsg = "房源存在:"+ response.data.stackTrace+"个,"+"符合条件为:"+vm.hosTbList.length+"个.";


                        // var infoMsg = "";
                        // for(var i=0;i<vm.hosTbList.length;i++){
                        //
                        //     let houseName = vm.hosTbList[i].houseName;
                        //     infoMsg = infoMsg+houseName+"&nbsp&nbsp&nbsp";
                        //     // 可租面积
                        //     let canRentArea = vm.hosTbList[i].area;
                        //     infoMsg = infoMsg+"可租"+canRentArea+"平&nbsp&nbsp&nbsp";
                        //     // 层总面积
                        //     let layerArea = vm.hosTbList[i].layerArea;
                        //     infoMsg = infoMsg+"层总"+layerArea+"平&nbsp&nbsp&nbsp";
                        //     infoMsg = infoMsg+"<br/>";
                        // }

                        //上面是没处理格式的老数据

                        var infoMsg = "";
                        infoMsg = "<table id=\"tab\" class=\"table  table-bordered table-hover table-condensed\" onclick='var td = event.srcElement;myFunction(td.parentElement.rowIndex,\"行数\");console.log(td.parentElement);console.log(td.innerText);console.log(\"行号：\" + (td.parentElement.rowIndex) + \"，列号：\" + td.cellIndex);" +
                            "var tab=document.getElementById(\"tab\");var rows=tab.rows;var rlen=rows.length; for (var i = 1; i <rlen; i++) {rows[i].style.background=\"#fcf8e3\" ;}rows[td.parentElement.rowIndex].style.background=\"#dff0d8\";" +
                            "'>" +
                            "\t<thead>\n" +
                            "\t\t<tr>";
                        vm.tableHeads = [
                            {
                                "prop": "numxu",
                                "label": "序号"
                            },
                            {
                                "prop": "buildNum",
                                "label": "楼栋"
                            },
                            {
                                "prop": "floor",
                                "label": "层"
                            },
                            {
                                "prop": "area",
                                "label": "面积"
                            },
                            {
                                "prop": "price",
                                "label": "价格"
                            },
                            {
                                "prop": "status",
                                "label": "状态"
                            },
                            {
                                "prop": "btnDetail",
                                "label": "操作详情"
                            }
                        ];
                        for(var i=0;i<vm.tableHeads.length;i++){
                            let houseName = vm.tableHeads[i].label;
                            infoMsg = infoMsg+"<th>"+houseName+"</th>";

                        }
                        infoMsg = infoMsg+"</tr>"+"</thead>";
                        infoMsg = infoMsg+"<tbody>";
                        for(var i=0;i<vm.hosTbList.length;i++){
                            let rEntity = vm.hosTbList[i];
                            infoMsg = infoMsg+"<tr style='background-color: #fcf8e3;' >";
                            infoMsg = infoMsg+"<td>"+(i+1)+"</td>";
                            infoMsg = infoMsg+"<td>"+rEntity.whereBuilding+"</td>";
                            infoMsg = infoMsg+"<td>"+rEntity.whereLayer+"</td>";
                            infoMsg = infoMsg+"<td>"+rEntity.area+"平"+"</td>";
                            infoMsg = infoMsg+"<td>"+rEntity.price+rEntity.unit+"</td>";
                            infoMsg = infoMsg+"<td>"+rEntity.houseStatusName+"</td>";
                            infoMsg = infoMsg+"<td>"+ "详情"+"</td>";
                        }

                        infoMsg = infoMsg+"</tbody></table>";
                        let countHeight = vm.hosTbList.length*40 + 50;

                        var opts = {
                            width : 500,     // 信息窗口宽度
                            height: countHeight,     // 信息窗口高度
                            title : titleMsg , // 信息窗口标题
                            enableMessage:true,//设置允许信息窗发送短息
                            message:"气泡信息,点击点触发~"
                        }
                        var infoWindow = new BMap.InfoWindow(infoMsg, opts);  // 创建信息窗口对象
                        vm.bdmap.openInfoWindow(infoWindow,poi); //开启信息窗口
                        // infoWindow.addEventListener('close', function (e) {
                        //     //alert('移出去点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
                        //     alert("点击了");
                        //     console.log(e);
                        // });
                    }).catch(function (error) {
                        //vm.$message.error('页面:获取数据失败!post/query');
//                        alert("页面:685行查询数据失败!houseLocation/query");
                        vm.loading = false;
                    });
                }
            },
            basicNetLocAnalysis(aVal,eVal){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var sendObj = {};
                    //val当前页传入值
                    sendObj.code = aVal;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "locationAnalysis/locMapCount",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.locAnaEntity = response.data.result;
                        //alert("总条数"+vm.locAnaEntity.remark);
                        var poi = new BMap.Point(eVal.point.lng,eVal.point.lat);

                        var titleMsg = vm.locAnaEntity.remark;
                        vm.tableHeads = vm.locAnaEntity.tableHeads;
                        vm.reportEntities = vm.locAnaEntity.reportEntities;

                        var infoMsg = "";
                        infoMsg = "<table class=\"table\">\n" +
                            "\t<thead>\n" +
                            "\t\t<tr>";
                        for(var key in vm.tableHeads){
                            let houseName = vm.tableHeads[key];
                            infoMsg = infoMsg+"<th>"+houseName+"</th>";

                        }
                        infoMsg = infoMsg+"</tr>"+"</thead>";
                        infoMsg = infoMsg+"<tbody>";
                        for(var i=0;i<vm.reportEntities.length;i++){
                            let rEntity = vm.reportEntities[i];
                            infoMsg = infoMsg+"<tr>";
                            infoMsg = infoMsg+"<td>"+rEntity.wordName+"</td>";
                            infoMsg = infoMsg+"<td>"+rEntity.heatRent+"</td>";
                            infoMsg = infoMsg+"<td>"+rEntity.alreadyRent+"</td>";
                            infoMsg = infoMsg+"<td>"+rEntity.willExpire+"</td>";
                        }

                        infoMsg = infoMsg+"</tbody></table>";


                        var opts = {
                            width : 350,     // 信息窗口宽度
                            height: 200,     // 信息窗口高度
                            title : titleMsg , // 信息窗口标题
                            enableMessage:true,//设置允许信息窗发送短息
                            message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
                        }
                        var infoWindow = new BMap.InfoWindow(infoMsg, opts);  // 创建信息窗口对象
                        vm.bdmap.openInfoWindow(infoWindow,poi); //开启信息窗口
                    }).catch(function (error) {
                        vm.loading = false;
                    });
                }
            },
            loadInputCity(){
                var vm = this;
                this.bdmap.clearOverlays();
                this.showNewCenter();
                //alert(vm.keyWord);
                this.basicNetLocQuery();


            },
            loadHosLocDetail(){

                var vm = {};
                vm = this;
                let map = this.bdmap;
                //浏览器重新定位start
                //var geolocation = new BMap.Geolocation();
                /*geolocation.getCurrentPosition(function(r){
                 if(this.getStatus() == BMAP_STATUS_SUCCESS){
                 var mk = new BMap.Marker(r.point);
                 map.addOverlay(mk);
                 map.panTo(r.point);
                 alert('您的位置：'+r.point.lng+','+r.point.lat);

                 vm.houseLocationObj.longitude = r.point.lng;
                 vm.houseLocationObj.latitude = r.point.lat;


                 }
                 else {
                 alert('failed'+this.getStatus());
                 }
                 },{enableHighAccuracy: true})*/

                //浏览器重新定位end
//                function myFun(result){
//                    var cityName = result.name;
//                    // map.setCenter(cityName);
//                    console.log("当前定位城市:"+cityName);
//                    console.log(result);
//                    //vm.keyWord = cityName;//111222测试城市
//
//                    // vm.keyWord = cityName;
//                }
                // var myCity = new BMap.LocalCity();
                // myCity.get(myFun);
//                if(this.baseAreaObj.provinceName!=""){
//                    this.keyWord = this.baseAreaObj.provinceName;
//                    if(this.baseAreaObj.cityName!=""){
//                        this.keyWord = this.baseAreaObj.cityName;
//                        if(this.baseAreaObj.regionName!=""){
//                            this.keyWord = this.baseAreaObj.regionName;
//
//                            if(this.baseAreaObj.streetName!=""){
//                                this.keyWord = this.baseAreaObj.streetName;
//
//
//                            }
//
//                        }
//
//                    }
//
//                }
                //map.setCenter(vm.keyWord);


                vm.handleBaiduMap();
                //vm.loading = false;
                //初始化请求queryMapLoc,根据地区编码查询房源位置
                //vm.basicNetLocQuery();




            },
            showNewCenter(){
                var vm = {};
                vm = this;
                //vm.bdmap.setCenter(vm.keyWord);
            },
            showAllPoint(){

                var vm = {};
                vm = this;
                // alert("进入"+vm.locTbList.length);
                // for(var i=0;i<vm.locTbList.length;i++){
                //     // alert("循环纬度"+vm.locTbList[i].longitude);
                //     var poi = new BMap.Point(vm.locTbList[i].longitude,vm.locTbList[i].latitude);
                //     var marker = new BMap.Marker(poi);        // 创建标注
                //     vm.bdmap.addOverlay(marker);                     // 将标注添加到地图中
                // }



                //加载海量点start
                var points = [];  // 添加海量点数据
                for (var i = 0; i < vm.locTbList.length; i++) {
                    points.push(new BMap.Point(vm.locTbList[i].longitude,vm.locTbList[i].latitude));
                }
                var options = {
                    size: BMAP_POINT_SIZE_BIG,
                    shape: BMAP_POINT_SHAPE_CIRCLE,
                    color: '#ff2f38'
                }
                //var pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
//                var pointCollection = {};
//                pointCollection.addEventListener('click', function (e) {
//                    //alert('单击点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
//                    console.log("e.tartget");
//                    console.log(e.tartget);
//                    console.log("e.type");
//                    console.log(e.type);
//
//                    var poi = new BMap.Point(e.point.lng,e.point.lat);
//                    console.log("e是");
//                    console.log(e);
//
//                    var aVal = "181008d80c8ea3943";
//                    for (var i = 0; i < vm.locTbList.length; i++) {
//                        points.push(new BMap.Point(vm.locTbList[i].longitude,vm.locTbList[i].latitude));
//                        if(e.point.lng==vm.locTbList[i].longitude,e.point.lat==vm.locTbList[i].latitude){
//                            aVal=vm.locTbList[i].locationCode;
//                        }
//                    }
//                    //vm.basicNetLocAnalysis(aVal,e);
//                    vm.basicNetHosByLoc(aVal,e);
//                    vm.basicNetHosLocDetail(aVal);
//
//                });
//                pointCollection.addEventListener('mouseover', function (e) {
//                    //alert('移动点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
//                    vm.bdmap.setDefaultCursor("pointer");
//                });
//                pointCollection.addEventListener('mouseout', function (e) {
//                    //alert('移出去点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
//                    vm.bdmap.setDefaultCursor("default");
//                });
//                vm.bdmap.addOverlay(pointCollection);  // 添加Overlay
                //加载海量点end


                //加载行政区域
                // let str = "上海市青浦区";
                // vm.getBoundary(keyWord);


            },
            getBoundary(aVal){
                var vm = {};
                vm = this;
                // alert("加载行政区域开始start");
                var bdary = new BMap.Boundary();
                bdary.get(aVal, function(rs){       //获取行政区域
                    //vm.bdmap.clearOverlays();        //清除地图覆盖物
                    var count = rs.boundaries.length; //行政区域的点有多少个
                    if (count === 0) {
                        alert('未能获取当前输入行政区域');
                        return ;
                    }
                    var pointArray = [];
                    for (var i = 0; i < count; i++) {
                        var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
                        vm.bdmap.addOverlay(ply);  //添加覆盖物
                        pointArray = pointArray.concat(ply.getPath());
                    }
                    vm.bdmap.setViewport(pointArray);    //调整视野
                });
                // alert("加载行政区域end");
            },
            basicNetSeeArea(leftDownLongi,leftDownLongiLati,rightUpLongi,rightUpLati){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var sendObj = {};
                    var obj = {};
                    obj.locationCode = this.loccode;
                    sendObj.entity = obj;
                    sendObj.leftDown = {};
                    sendObj.leftDown.longitude = leftDownLongi;
                    sendObj.leftDown.latitude = leftDownLongiLati;

                    sendObj.rightUp = {};
                    sendObj.rightUp.longitude = rightUpLongi;
                    sendObj.rightUp.latitude = rightUpLati;

                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "houseLocation/querySeeArea",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        var regionListPoly = [];
                        regionListPoly = response.data.result;
                        console.log("请求的可视区域的多边形");
                        console.log(regionListPoly);
                        vm.canSeeRegionList = regionListPoly;
                        for(var j=0;j<regionListPoly.length;j++){
                            var onePolygon = regionListPoly[j];
                            var netRegionArr = [];
                            netRegionArr = onePolygon.pointRegionList;
                            var regionArr = [];
                            for(var i=0;i<netRegionArr.length;i++){
                                // alert("循环纬度"+netRegionArr[i].longitude);
                                var region1 = new BMap.Point(netRegionArr[i].longitude,netRegionArr[i].latitude);
                                regionArr.push(region1);
                            }

                            var polygon = new BMap.Polygon(regionArr, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
                            vm.bdmap.addOverlay(polygon);           //增加多边形
                            // alert(onePolygon.polygonNum);
                        }
                        // alert("经度"+vm.houseLocationObj.longitude+"纬度"+vm.houseLocationObj.latitude);
                        // alert("请求的可视区域的多边形");
                        //vm.handleBaiduMap();
                    }).catch(function (error) {
                        //vm.$message.error('页面:获取数据失败!post/query');
                        alert("页面:获取数据失败!houseLocation/querySeeArea");
                    });
                }
            },
        },//end vue methods

    }



</script>

<!--页面全局script-->

<style scoped>


    .root {
    }

    .now_page{
        padding:0px 5px 0px 0px;
    }
  /*  .top_bar{
        position: fixed;
        z-index: 200;
        width: 100%;
        height: auto;
    }*/
    .first_block{
        margin:0px 0px 0px 0px;

    }
    .content_bar{
        margin:3px 3px 3px 3px;
        padding:0px 0px 0px 0px;
        height: 99vh;
    }
    .bar_in_street {
    }
    .bar_in_btn {
        margin:0px 0px 0px 10px;
    }

    .count_title_div {

        margin:3px 3px 0px 3px;
        padding:0px 0px 0px 0px;
        float: right;
    }
    .count_title_div_kong {
        float: right;
        width: 53px;
        height: 40px;
    }


</style>