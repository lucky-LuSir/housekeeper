<template slot-scope="scope">
    <div ref="myRoot" style="overflow-y: hidden;">



        <div class="now_page">

            <!--<div class="top_bar list-inline">-->
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
                <!--<el-button class="bar_in_btn" size="small" plain type="primary"  @click="dialogVisible = true">设置默认</el-button>-->
                <!--&lt;!&ndash;<el-button class="bar_in_btn" size="small" plain type="primary"  icon="el-icon-search" @click="searchHos()">按条件查询</el-button>&ndash;&gt;-->



            <!--</div>-->
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
                    <el-select v-model="selectWordName"
                               v-show="false"
                               class="change-park"
                               size="small"
                               placeholder="输入关键字然后选择"
                               filterable
                               remote
                               :remote-method="remoteMethod"
                               :loading="loadingLocRemote"
                               @change="changeAddressBase"
                    >
                        <el-option
                                v-for="item in searchPanelList"
                                :key="item.uid"
                                :label="item.title"
                                :value="item.title">
                            <span style="float: left">{{ item.title }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.address }}</span>
                        </el-option>
                    </el-select>

                    <!--<el-tag v-show="(totalCount)" style="margin-left: 13px;width: auto;">位置数量:{{totalCount}} 个</el-tag>-->

                    <!--<div style="float: right;">-->
                        <!--<el-tag>所在城市区域统计</el-tag>-->
                        <!--<span style="margin-left: 28px;">园区总数</span>-->
                        <!--<el-tag v-show="(houseDictionaryEntity.parkTotal)" style="width: auto;">{{houseDictionaryEntity.parkTotal}} 个</el-tag>-->
                        <!--<span style="margin-left: 28px;">房源总数</span>-->
                        <!--<el-tag v-show="(houseDictionaryEntity.houseTotal)" style="width: auto;">{{houseDictionaryEntity.houseTotal}} 套</el-tag>-->
                        <!--<span style="margin-left: 28px;">面积总数</span>-->
                        <!--<el-tag v-show="(houseDictionaryEntity.houseAreaTotal)" style="width: auto;">{{houseDictionaryEntity.houseAreaTotal}} 平方</el-tag>-->
                    <!--</div>-->

                    <!--<p class="title_city_btn" style="float: right;margin-right: 150px;">加载位置数量:&nbsp&nbsp&nbsp{{totalCount}}<span class="title_red"></span></p>-->
                </div>
            </div>
            <div class="content_bar col-sm-12 col-md-12">
                <div id="allmapLocAdd" v-loading="loading" style="overflow:hidden;zoom:1;position:relative;height:80%;">
                    <div id="poiAndRegionDiv" style="height:90%;-webkit-transition: all 0.5s ease-in-out;transition: all 0.5s ease-in-out;"></div>
                </div>

                <div>
                    <el-tag>选择点信息</el-tag>
                    <!--<el-tag>编码:{{houseLocationObj.locationCode}}</el-tag>-->
                    <!--<el-tag>别名:{{houseLocationObj.locationAlias}}</el-tag>-->
                    <el-tag>经度:{{houseLocationObj.longitude}}</el-tag>
                    <el-tag>纬度:{{houseLocationObj.latitude}}</el-tag>
                    <el-tag>多边形点数量:{{locRegionArr.length}}</el-tag>
                </div>

                <div style="text-align: center;">
                    <span>
                            <!--<el-button type="warning" @click="remove_overlay" v-show="showLook">缩放查看</el-button>-->
                            <el-button type="primary" @click="handleSure">确定区域</el-button>
                    </span>
                </div>



            </div>

        </div >



        <el-dialog  :close-on-click-modal="false"
                title="设置默认省市区"
                :visible.sync="dialogVisible"
                width="69%"
                append-to-body>

            <el-row>
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                    <div label="所属省份" prop="provinceCode">
                        <el-select id="provinceNameBase" ref="provinceNameBase"  v-model="baseAreaObj.provinceCode" @visible-change="handNeedProvinceBase" @change="changeProvBase" placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itProvinceArrBase"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name"
                            >
                            </el-option>
                        </el-select>
                    </div>
                </el-col>
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                    <div label="所属城市" prop="cityCode">
                        <el-select id="cityNameBase"  v-model="baseAreaObj.cityCode" @focus="getCitieListBase(baseAreaObj.provinceCode)" @change="changeCityBase"   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itCityArrBase"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </div>
                </el-col>
                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                    <div label="所属区域">
                        <el-select id="regionNameCpy" v-model="baseAreaObj.regionCode" @focus="getAreaListCpy(baseAreaObj.cityCode)"  @change="changeRegionCpy"    placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itAreaArrCpy"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </div>
                </el-col>
                <el-col style="margin-left: 20px;" :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <!--<el-button @click="queryQuickCity" >查询</el-button>-->
                    <el-button @click="addQuickCity" type="primary" plain >添加</el-button>
                    <el-button @click="cleanQuickCity" type="warning" plain>移除</el-button>
                </el-col>
            </el-row>
            <el-row style="margin-top: 20px;color: #409EFF;">
                <span style="color: #409EFF;">便捷城市</span>
            </el-row>

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
        <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible"   width="60%" append-to-body>
            <hos-loc-map-condition ref="hosByCondition" v-if="houseConditionOpenOrClose" @changeHos="serchHosClick"></hos-loc-map-condition>
        </el-dialog>

        <!--房源详情面板-->
        <el-dialog  :close-on-click-modal="false" style="height: 1000px" :visible.sync="dialogFormHouseDetail" top="80px"  width="95%"  center append-to-body>
            <hos-details  :houseCodeDetails="houseCodeDetails" ref="hosByDetails"/>
        </el-dialog>



        <div style="width: 100%;height: 150px;"></div>
    </div>
</template>



<script scoped>
    import FileSaver from 'file-saver';
    import icon_locat from '../../../static/hkpImgIcon/icon_locat.svg';
    import ElRow from "element-ui/packages/row/src/row";
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    import hosLocMapCondition from  "./hosLocMapCondition";
    import hosDetails from  "./hosDetails";
    export default {
        components: {
            ElButton,
            ElRow,
            hosLocMapCondition,
            hosDetails

        },
        created: function () {

            var vm = this;
            //alert("子组件新增:"+vm.houseLocationObj.detailAddress);
            this.baseAreaObj.provinceName=="";
            this.baseAreaObj.cityName=="";
            this.baseAreaObj.regionName=="";
            this.baseAreaObj.streetName=="";


        },
        props:["loccode" ,"sendLocName"],
        data() {
            return {

                hkpRegionCityAddList:{

                    provinceCode:null,
                    cityCode:null,
                    regionCode:null,
                    streetCode:null,

                    provinceName:null,
                    cityName:null,
                    regionName:null,
                    streetName:null,

                },
                loadingLocRemote:false,
                houseLocationCommonDialogFormVisible:false,
                g_locCode:'默认值',
                houseLocationObj: {
                    id: '',
                    locationAlias: '',
                    locationCode: '',
                    province: '',
                    provinceName: '',
                    city: '',
                    cityName: '',
                    region: '',
                    regionName: '',
                    street: '',
                    streetName: '',
                    detailAddress: '',
                    doorNumber: '',
                    longitude: '',
                    latitude: '',
                    communityName: '',
                    communityName: '',
                    trafficFacilities: '',
                    fileList: [
                        {
                            fileCode: '',
                            fileType: '',
                        }
                    ],
                    locRegionList: [
                        {
                            regionBatchCode: '',
                            locationCode: '',
                            locationAlias: '',
                            num: '',
                            longitude: '',
                            latitude: '',
                        }
                    ],
                    hasLocRegion: '',
                },
                showLook:false,
                needRemoveMarker:{},
                houseQuery:{},
                hosList:[],
                search:{},
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
                    cityName:"",
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
                loading: false,
                selectWordName:'',
                searchPanelList:[],
                wordBaiduName:'上海虹桥火车站',
                canSeeRegionList:[],
                bdmap:'',
                bdDrawObj:'',
                bdInfoWindow:'',
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
            //alert("子组件新增:"+vm.houseLocationObj.detailAddress);
            //window.tt = myFunction();
            console.log("传值给wendow.test");
            //console.log(window.test);
            vm.loading = true;
            //, { enableMapClick: false }   禁用地图景点点击
            var map = new BMap.Map('poiAndRegionDiv', { minZoom : 16, maxZoom : 19, enableMapClick: false });
            //十字架型
            map.setDefaultCursor("default");
            this.bdmap= map;

            let hkpRegionCityObj = vm.hkpRegionCityAddList;
            if(hkpRegionCityObj == null)
            {
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
                vm.dialogVisible=true;
            }else{

                //alert("省市区有值..."+hkpRegionCityObj.provinceCode);
                let provinceObj = {};
                provinceObj.areaCode = hkpRegionCityObj.provinceCode;
                provinceObj.name = hkpRegionCityObj.provinceName;
                vm.itProvinceArrBase.push(provinceObj);

                let cityObj = {};
                cityObj.areaCode = hkpRegionCityObj.cityCode;
                cityObj.name = hkpRegionCityObj.cityName;
                vm.itCityArrBase.push(cityObj);

                let areaObj = {};
                areaObj.areaCode = hkpRegionCityObj.regionCode;
                areaObj.name = hkpRegionCityObj.regionName;
                vm.itAreaArrCpy.push(areaObj);

                vm.baseAreaObj = hkpRegionCityObj;
                //开始浏览器定位,加载百度初始化方法
                this.loadHosLocDetail();

                vm.loading = false;
                //开始将省市区内统计的信息展示
                //vm.basicNetHosDicTotal();
            }

            vm.baseAreaObj = hkpRegionCityObj;








        },

        methods: {


            remoteMethod(query) {
                var vm = this;
                if (query !== '') {
                    //alert(query);
                    this.selectWordName = query;
                    this.loadingLocRemote = true;
                    setTimeout(() => {
                        this.loadingLocRemote = false;
                        this.handleBaiduSerach();
                    }, 200);
                } else {
                    // alert("超时,未远程搜索");
                    vm.searchPanelList = [];
                }
            },

            handleBaiduSerach(){
                var vm = {};
                vm = this;
                //增加智能搜索
                var local = new BMap.LocalSearch(this.bdmap, {
                    renderOptions:{map: this.bdmap, panel:"r-result"},
                    pageCapacity:1
                });


                //local.search("天安门");
                // alert(this.selectWordName);
                var rr=local.search(this.selectWordName);

                local.setSearchCompleteCallback(function(){
                    var lr = local.getResults();
                    console.log("handleBaiduSerach打印查询对象");
                    console.log(lr.Ar[0]);
                    vm.searchPanelList = lr.Ar;

                    let selectPoint = vm.searchPanelList[0].point;
                    vm.selectPointObj = selectPoint;
                    var pp = new BMap.Point(vm.selectPointObj.lng,vm.selectPointObj.lat);   //获取第一个智能搜索的结果
                    vm.bdmap.centerAndZoom(pp, 18);
                    var myIcon = new BMap.Icon("../../../static/hkpImgIcon/png_loc.png", new BMap.Size(30,30));

                    //alert("清除所有覆盖物");
                    vm.bdmap.clearOverlays();
                    vm.bdmap.removeOverlay(lr.Ar[0].marker);
                    let aMark = new BMap.Marker(pp,{icon:myIcon});
                    //vm.bdmap.addOverlay(aMark);    //添加标注
                    //aMark.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                    let mapvPoi = {};
                    mapvPoi.longitude = vm.selectPointObj.lng;
                    mapvPoi.latitude = vm.selectPointObj.lat;
                    mapvPoi.locationCode = "百度检索点";
                    vm.houseLocationObj = mapvPoi;
                    let tableList = [];
                    tableList.push(mapvPoi);
                    vm.showMapvFunc(tableList);
                });
                // console.log("打印查询对象");
                // console.log(local);
                // console.log("打印查询对象rr");
                // console.log(local.getResults());
                // local.searchInBounds(myKeys, map.getBounds());
                //
            },

            changeAddressBase(aval){
                //赋值地址name
                var vm = this;
                let areaObj = {};
                areaObj = this.searchPanelList.find((item)=>{
                    return item.title === aval;
                });
                let selectPoint = areaObj.point;
                vm.selectPointObj = selectPoint;
                // let poi = new BMap.Point(selectPoint.lng,selectPoint.lat);
                // this.bdmap.centerAndZoom(poi, 16);//设置中心点坐标和地图级别

                let myValue = areaObj.address;
                //alert(myValue);
                //增加智能搜索样例111
                this.bdmap.clearOverlays();
                this.basicNetHosLocDetail();
                // function myFun(){
                //     var pp = new BMap.Point(vm.selectPointObj.lng,vm.selectPointObj.lat);   //获取第一个智能搜索的结果
                //     vm.bdmap.centerAndZoom(pp, 18);
                //     var myIcon = new BMap.Icon("../../../static/hkpImgIcon/png_loc.png", new BMap.Size(300,157));
                //     let aMark = new BMap.Marker(pp,{icon:myIcon});
                //     vm.bdmap.addOverlay(aMark);    //添加标注
                //     aMark.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                // }
                // var local = new BMap.LocalSearch(this.bdmap, { //智能搜索
                //     onSearchComplete: myFun
                // });
                // local.search(myValue);

                //增加智能搜索222
                var localab = new BMap.LocalSearch(this.bdmap, {
                    renderOptions:{map: this.bdmap, panel:"r-result"},
                    pageCapacity:1
                });


                //local.search("天安门");
                // alert(this.selectWordName);
                var rr=localab.search(myValue);

                localab.setSearchCompleteCallback(function(){
                    var lr = localab.getResults();
                    console.log("打印查询对象");
                    console.log(lr.Ar);
                    //alert(lr.Ar);
                    var pp = new BMap.Point(vm.selectPointObj.lng,vm.selectPointObj.lat);   //获取第一个智能搜索的结果
                    vm.bdmap.centerAndZoom(pp, 18);
                    var myIcon = new BMap.Icon("../../../static/hkpImgIcon/png_loc.png", new BMap.Size(30,30));
                    alert("清除所有覆盖物");
                    vm.bdmap.clearOverlays();
                    let aMark = new BMap.Marker(pp,{icon:myIcon});
                    vm.bdmap.addOverlay(aMark);    //添加标注
                    aMark.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                });

            },
            handleSure(){

                var vm = this;
                var isPolygon = false;
                if(vm.locRegionArr!=null && vm.locRegionArr.length>0){isPolygon = true;}
                if(isPolygon==false){alert("此位置未加区域,请点击右上角多边形按钮,给此位置画多边形!");}
                else if(isPolygon==true){
                    //this.basicNetAddLocRegion();
                    let leftDown = {};
                    leftDown.longitude = vm.leftDownLongi;
                    leftDown.latitude = vm.leftDownLongiLati;

                    let rightUp = {};
                    rightUp.longitude = vm.rightUpLongi;
                    rightUp.latitude = vm.rightUpLati;

                    let poiObjregionList = {};
                    poiObjregionList.poiObj = vm.houseLocationObj;

                    let locRegionList = [];
                    console.log(vm.locRegionArr);
                    for(var i=0;i<vm.locRegionArr.length;i++){
                        console.log("lng:"+vm.locRegionArr[i].lng+"\n lat:"+vm.locRegionArr[i].lat);
                        var oneObj = {};
                        oneObj.num=i+1;
                        oneObj.longitude=vm.locRegionArr[i].lng;
                        oneObj.latitude=vm.locRegionArr[i].lat;
                        locRegionList.push(oneObj);

                    }
                    poiObjregionList.regionList = locRegionList;
                    poiObjregionList.leftDown = leftDown;
                    poiObjregionList.rightUp = rightUp;
                    this.$emit('changeHos', poiObjregionList);

                }
                let obj = {};
                obj.msg = "子组件--地图--确定按钮";
                //this.remove_overlay();

            },
            basicNetAddLocRegion(){
                var vm = {};
                vm = this;
                var flag = true;
                this.$notify({
                    title: vm.houseLocationObj.locationCode,
                    message: '位置编码',
                    type: 'warning'
                });
                if(vm.houseLocationObj.locationCode==null || vm.houseLocationObj.locationCode==""){
                    alert("请先点击一个位置点,然后画多边形");
                    flag = false;
                }
                if (flag) {        //flag留后面表单验证
                    var sendObj = {};
                    var obj = {};
                    obj.locationCode = vm.houseLocationObj.locationCode;

                    obj.locationAlias = vm.houseLocationObj.locationAlias;

                    obj.longitude=vm.houseLocationObj.longitude;
                    obj.latitude=vm.houseLocationObj.latitude;
                    vm.locRegionList = [];
                    console.log(vm.locRegionArr);
                    for(var i=0;i<vm.locRegionArr.length;i++){
                        console.log("lng:"+vm.locRegionArr[i].lng+"\n lat:"+vm.locRegionArr[i].lat);
                        var oneObj = {};
                        oneObj.num=i+1;
                        oneObj.longitude=vm.locRegionArr[i].lng;
                        oneObj.latitude=vm.locRegionArr[i].lat;
                        vm.locRegionList.push(oneObj);

                    }
                    obj.locRegionList = vm.locRegionList;
                    sendObj.entity = obj;

                    sendObj.leftDown = {};
                    sendObj.leftDown.longitude = vm.leftDownLongi;
                    sendObj.leftDown.latitude = vm.leftDownLongiLati;

                    sendObj.rightUp = {};
                    sendObj.rightUp.longitude = vm.rightUpLongi;
                    sendObj.rightUp.latitude = vm.rightUpLati;
                    //验证画出的一个多变形和可视区域的多边形重复start
                    var isInPolygon = false;
                    var plyA = vm.locRegionList;
                    //判断单个点是否在画的多边形中
                    var itemPoint = new BMap.Point(vm.houseLocationObj.longitude, vm.houseLocationObj.latitude);
                    const [pA,pB]=[[],[]];
                    plyA.forEach((item)=>{
                        pA.push(new BMap.Point(item.longitude,item.latitude));
                    });
                    if(pA !=null && pA.length >0){
                        isInPolygon= BMapLib.GeoUtils.isPointInPolygon(itemPoint, new BMap.Polygon(pA));
                    }



                    //验证画出的一个多变形和可视区域的多边形重复end

                    if(!isInPolygon){console.log("---位置点不在多边形中");alert("---位置点不在多边形中---");}
                    else{
                        var options = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "houseLocation/editPointAndRegion",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$notify({
                                title: '新增区域成功',
                                message: '成功',
                                type: 'success'
                            });
                            vm.locRegionArr = [];
                            vm.suerAfter();
                        }).catch(function (error) {
                            //vm.$message.error('页面:获取数据失败!post/query');
                            alert("页面:获取数据失败!orderfollowup/query");
                        });
                    };
                }
            },

            sendani(){

                var vm = this;

            },
            closeInfoAndDraw:function(locCodeVal){
//                 alert("vue中方法看看"+locCodeVal);
                 var vm = this;
                vm.bdDrawObj.setDrawingMode(BMAP_DRAWING_POLYGON);
                vm.bdDrawObj.open();
                vm.bdmap.closeInfoWindow();
                vm.bdmap.setZoom(19);
                //setCenter(center: Point | String)
                var curPoi = new BMap.Point(vm.houseLocationObj.longitude, vm.houseLocationObj.latitude);
                vm.bdmap.setCenter(curPoi);
                //跳动动画
                var marker = new BMap.Marker(curPoi);  // 创建标注
                //vm.bdmap.addOverlay(marker);               // 将标注添加到地图中
                marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

            },
            closeAndMovePoi:function(locCodeVal){
//                 alert("vue中方法看看"+locCodeVal);
                var vm = this;
                vm.bdmap.closeInfoWindow();
                let num = vm.bdmap.getZoom();
                if(num <17 ){num = 17;}
                vm.bdmap.setZoom(num);
                //setCenter(center: Point | String)
                var curPoi = new BMap.Point(vm.houseLocationObj.longitude, vm.houseLocationObj.latitude);
                vm.bdmap.setCenter(curPoi);

                //出现移动点
                var point = new BMap.Point(vm.houseLocationObj.longitude,vm.houseLocationObj.latitude);
                var myIcon = new BMap.Icon("../../../static/hkpImgIcon/png_flag_yellow.png", new BMap.Size(35,110));
                var markerNowPoi = new BMap.Marker(point,{icon:myIcon});        // 创建标注
                vm.bdmap.removeOverlay(vm.needRemoveMarker);
                vm.bdmap.addOverlay(markerNowPoi);
                markerNowPoi.enableDragging();   // 点标注可拖拽
                markerNowPoi.addEventListener("dragend", function(e){
                    //alert("当前位置：" + e.point.lng + ", " + e.point.lat);
                    vm.houseLocationObj.longitude = e.point.lng;
                    vm.houseLocationObj.latitude = e.point.lat;
                });
                vm.needRemoveMarker = markerNowPoi;
                vm.basicNetHosLocDetail(vm.g_locCode);

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
                    this.$refs.hosByDetails.getHouseDetails();
                })
            },
            //房源详情
            houseDetail(index,rows){
                this.dialogFormHouseDetail = true;
                this.houseCodeDetails = rows.houseCode;
                this.$nextTick(() => {
                    this.$refs.hosByDetails.getHouseDetails();
                })
            },
            quickGetHouList(obj){
                var vm = this;
                vm.bdmap.clearOverlays();

                vm.loading = true;
                vm.dialogFormVisible = false;
                this.getHouList(obj);

            },
            //查询全部房源
            getHouList(objaaa){
                var vm = this;
                var sendObj = {};
                sendObj.start = 0;
                sendObj.pageSize = 3;
                sendObj.keyword = this.search.keyword;
                sendObj.houseQuery = objaaa;
                vm.houseQuery = objaaa;
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
                    //vm.showMapvFunc(vm.locTbList);
                    console.log("请求的位置");
                    vm.loading = false;
                    vm.dialogFormVisible = false;
                }).catch(function (error) {
                    this.$notify({
                        title: '警告:请求失败',
                        message: 'queryMapLoc,请求失败',
                        type: 'warning'
                    });
                    vm.loading = false;
                })
            },

            //自定义查询面板
            searchHos(){
                this.dialogFormVisible = true;
                this.houseConditionOpenOrClose = true;
            },
            //自定义查询
            serchHosClick(obj){
                var vm = this;
                this.search = obj;

                // if(obj.houseQuerys.queryType==''){
                //     this.search.houseQuerys.queryType='8'; //8表示默认查部门
                // }
                console.log("看看调用查询条件子组件返回的对象");
                console.log(obj);
                this.quickGetHouList(obj.houseQuerys);
                vm.handleBaiduMap();
                //this.getHouList(obj.houseQuerys);
                // this.dialogFormVisible = false;
                // this.houseConditionOpenOrClose = false;
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
//                var vm = {};
//                vm = this;
//                var flag = true;
//                if (flag) {        //flag留后面表单验证
//                    var sendObj = {};
//                    var obj = {};
//                    obj.locationCode = aVal;
////                    sendObj.start = 0;
////                    sendObj.pageSize = 50;
//                    sendObj.entity = obj;
//
//                    var options = {
//                        method: 'POST',
//                        headers: {'content-type': 'application/json'},
//                        data: sendObj,
//                        url: "houseLocation/detail",
//                    };
//                    this.$ajax(
//                        options
//                    ).then(function (response) {
//                        vm.houseLocationObj = response.data.result;
//                        console.log("请求的位置详情");
//                        console.log(vm.houseLocationObj);
//                        //alert("请求详情");
//                        // alert("经度"+vm.houseLocationObj.longitude+"纬度"+vm.houseLocationObj.latitude);
//                        //alert(vm.houseLocationObj.locationCode);
//                        vm.handlePolygon();
//                    }).catch(function (error) {
//                        //vm.$message.error('页面:获取数据失败!post/query');
//                        alert("页面:获取数据失败!houseLocation/detail");
//                    });
//                }
            },
            showMapvFunc(aVal){
                var vm = this;
                var bmap = this.bdmap;
                // 第一步创建mapv示例
//                var mapv = new Mapv({
//                    drawTypeControl: true,
//                    map: bmap  // 百度地图的map实例
//                });

                var dataTotal = []; // 取城市的点来做示例展示的点数据
                for(var i = 0;i<aVal.length;i++){
                    let pointLoc = {};
                    pointLoc.lat = aVal[i].latitude;
                    pointLoc.lng = aVal[i].longitude;
                    pointLoc.count = 0.6 * 10;
                    //data.push(pointLoc);

                    console.log("aVal位置编码");
                    console.log(aVal[i].locationCode);
                    dataTotal.push({
                        geometry: {
                            locationCode:aVal[i].locationCode,
                            type: 'Point',
                            coordinates: [pointLoc.lng,pointLoc.lat]
                        },
//                        count: 2
                    });
                }


                var dataSetTotal = new mapv.DataSet(dataTotal);




//                var layer = new Mapv.Layer({
//                    mapv: mapv, // 对应的mapv实例
//                    zIndex: 1, // 图层层级
//                    dataType: 'point', // 数据类型，点类型
//                    data: data, // 数据
//                    drawType: 'bubble', // 展示形式
//
//                    lineWidth: 14, // 描边宽度
//                    drawOptions: { // 绘制参数
//
//                        size: 5 ,// 大小值
//                        splitList: [
//                            {
//                                start: 0,
//                                end: 2,
//                                size: 3
//                            },{
//                                start: 2,
//                                end: 5,
//                                size: 6
//                            },{
//                                start: 5,
//                                end: 7,
//                                size: 9
//                            },{
//                                start: 7,
//                                size: 12
//                            }
//                        ],
//                        //globalCompositeOperation: 'lighter', //叠加模式
//                        strokeStyle: 'rgba(255, 255, 255, 0.9)', // 描边颜色，不设置则不描边
//                        fillStyle: "rgba(255, 64, 87, 0.8)" ,// 填充颜色
//                        shadowColor: 'rgba(255, 255, 0, 1)', // 投影颜色
//                        lineWidth: 2, // 描边宽度，不设置则不描边
//                        radius: 6, // 半径大小
//
//                        lineCap: 'butt',
//                        lineJoin: 'miter',
//                        miterLimit: 10
//                    }
//                });

                console.log("看看新版的气泡图");
                console.log(mapv);

                var optionsTotal = {
                    draw: 'bubble',
                    size: 9, // 大小值
                    unit: 'px', // 'px': 以像素为单位绘制,默认值。'm': 以米制为单位绘制，会跟随地图比例放大缩小
                    fillStyle: "rgba(255, 64, 87, 0.8)" ,// 填充颜色rgba(255, 64, 87, 0.8)
                    strokeStyle: "rgba(255, 255, 255, 1)", // 描边颜色，不设置则不描边
                    lineWidth: 3, // 描边宽度，不设置则不描边
                    shadowColor: 'rgba(255,0,0,1)', // 投影颜色
                    shadowBlur: 2,  // 投影模糊级数
                    updateCallback: function (time) { // 重绘回调函数，如果是时间动画、返回当前帧的时间
                    },
                    methods: { // 一些事件回调函数
                        click: function (item) { // 点击事件，返回对应点击元素的对象值
                            //console.log(item);
                            //alert("点击覆盖mapv层");
                            if(item!=null){
                                vm.showLook=true;
                                console.log(item);
                                console.log(item.geometry.coordinates[0]);
//                                vm.$notify({
//                                    title: item.geometry.coordinates[0],
//                                    message: '页面:查询区域信息失败!cpyArea/queryNoPage',
//                                    type: 'success'
//                                });
                                let bmapPart = vm.bdmap;
                                let dataPart = [];
                                dataPart.push(item);
                                let dataSetPart = new mapv.DataSet(dataPart);
                                let optionsPart = {
                                    draw: 'bubble',
                                    size: 9, // 大小值
                                    unit: 'px', // 'px': 以像素为单位绘制,默认值。'm': 以米制为单位绘制，会跟随地图比例放大缩小
                                    fillStyle: "#409EFF" ,// 填充颜色rgba(255, 64, 87, 0.8)
                                    strokeStyle: "rgba(255, 255, 255, 1)", // 描边颜色，不设置则不描边
                                    lineWidth: 3, // 描边宽度，不设置则不描边
                                    shadowColor: 'rgba(255,0,0,1)', // 投影颜色
                                    shadowBlur: 2,  // 投影模糊级数

                                }

                                let mapvLayerPart = new mapv.baiduMapLayer(bmapPart, dataSetPart, optionsPart);
                                dataPart = [];
                                var aVal = item.geometry.locationCode;
//                                vm.basicNetHosByLoc(aVal,item);
//                                vm.basicNetHosLocDetail(aVal);


                                //原来点击事件
                                //alert('单击点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
                    vm.locRegionList = [];

                    var poi = new BMap.Point(item.geometry.coordinates[0],item.geometry.coordinates[1]);
//                    var point = new BMap.Point(vm.houseLocationObj.longitude,vm.houseLocationObj.latitude);
//                    var marker = new BMap.Marker(point);        // 创建标注
//                    vm.bdmap.addOverlay(marker);

                    var aVal = "181008d80c8ea3943";
                    for (var i = 0; i < vm.locTbList.length; i++) {
                        //points.push(new BMap.Point(vm.locTbList[i].longitude,vm.locTbList[i].latitude));
                        if(item.geometry.coordinates[0]==vm.locTbList[i].longitude,item.geometry.coordinates[1]==vm.locTbList[i].latitude){
                            aVal=vm.locTbList[i].locationCode;
                            vm.g_locCode = vm.locTbList[i].locationCode;
                        }
                    }
                    //vm.basicNetLocAnalysis(aVal,e);
//                    vm.remove_overlay();
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
                //localStorage.removeItem("hkpRegionCityAddList");
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

                //localStorage.setItem("hkpRegionCityAddList", JSON.stringify(vm.baseAreaObj));
                this.dialogVisible = false;

                //开始浏览器定位,加载百度初始化方法
                this.loadHosLocDetail();

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
                var vm = this;
                this.bdmap.clearOverlays();
                //开始将省市区内统计的信息展示
                vm.basicNetHosDicTotal();
                vm.secondBaiduMap();
                //vm.loading = false;
                //初始化请求queryMapLoc,根据地区编码查询房源位置
                //vm.basicNetLocQuery();
                // this.loadHosLocDetail();
                // this.selectWordName = '';
                // this.searchPanelList = [];
                //vm.bdmap.setZoom(16);
                //setCenter(center: Point | String)
                var curPoi = new BMap.Point(vm.houseLocationObj.longitude, vm.houseLocationObj.latitude);
                vm.bdmap.setCenter(curPoi);
                vm.bdmap.openInfoWindow(vm.bdInfoWindow,curPoi); //开启信息窗口
                //跳动动画
//                var marker = new BMap.Marker(curPoi);  // 创建标注
//                vm.bdmap.addOverlay(marker);               // 将标注添加到地图中
//                marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            },

            suerAfter(){
                var vm = this;
                this.bdmap.clearOverlays();
                //开始将省市区内统计的信息展示
                vm.basicNetHosDicTotal();
                vm.secondBaiduMap();
                //初始化请求queryMapLoc,根据地区编码查询房源位置
                //vm.basicNetLocQuery();
                var curPoi = new BMap.Point(vm.houseLocationObj.longitude, vm.houseLocationObj.latitude);
                vm.bdmap.setCenter(curPoi);
                vm.bdmap.openInfoWindow(vm.bdInfoWindow,curPoi); //开启信息窗口
            },


            handlePolygon(){

                var vm = this;

                vm.bdmap.removeOverlay(vm.lonlyPolygon);
                //vm.bdmap.clearOverlays();
                var netRegionArr = [];
                netRegionArr = vm.houseLocationObj.locRegionList;
                var regionArr = [];
                // alert("进入");
                vm.locRegionArr =[];
                for(var i=0;i<netRegionArr.length;i++){
                    // alert("循环纬度"+netRegionArr[i].longitude);
                    var region1 = new BMap.Point(netRegionArr[i].longitude,netRegionArr[i].latitude);
                    regionArr.push(region1);

                    var obj = {};
                    obj.lng = netRegionArr[i].longitude;
                    obj.lat = netRegionArr[i].latitude;
                    vm.locRegionArr.push(obj);
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
                //this.bdmap.clearOverlays();



                var poi = new BMap.Point(vm.houseLocationObj.longitude,vm.houseLocationObj.latitude);
                map.centerAndZoom(poi, 14);//设置中心点坐标和地图级别
                this.bdmap.centerAndZoom(vm.keyWord,14);      // 用城市名设置地图中心点
                map.enableScrollWheelZoom(); //启用鼠标滚动对地图放大缩小
                var marker = new BMap.Marker(poi);        // 创建标注
                vm.needRemoveMarker = marker;
                map.addOverlay(marker);                     // 将标注添加到地图中
                marker.enableDragging();   // 点标注可拖拽
                marker.addEventListener("dragend", function(e){
                    //alert("当前位置：" + e.point.lng + ", " + e.point.lat);
                    vm.houseLocationObj.longitude = e.point.lng;
                    vm.houseLocationObj.latitude = e.point.lat;
                });
                marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                //添加卫星图start
                var mapType1 = new BMap.MapTypeControl(
                    {
                        mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP],
                        anchor: BMAP_ANCHOR_TOP_LEFT
                    }
                );

                var overView = new BMap.OverviewMapControl();
                var overViewOpen = new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT});

                map.addControl(mapType1);          //2D图，混合图
                map.addControl(overView);          //添加默认缩略地图控件
                //map.addControl(overViewOpen);      //右下角，打开
                //添加卫星图end

                //添加比例尺start
                var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});// 左上角，添加比例尺
                var top_left_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});  //左上角，添加默认缩放平移控件
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
                var drawingManager = new BMapLib.DrawingManager(map, {
                    isOpen: false, //是否开启绘制模式
                    enableDrawingTool: true, //是否显示工具栏
                    drawingMode:BMAP_DRAWING_RECTANGLE,//绘制模式  多边形
                    drawingToolOptions: {
                        anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
                        offset: new BMap.Size(5, 5), //偏离值
                        drawingModes:[
                            BMAP_DRAWING_POLYGON
                        ]
                    },
                    polygonOptions: styleOptions //多边形的样式
                });

                //添加鼠标绘制工具监听事件，用于获取绘制结果
                drawingManager.addEventListener('overlaycomplete', overlaycomplete);
                function clearAll() {
                    for(var i = 0; i < overlays.length; i++){
                        map.removeOverlay(overlays[i]);
                    }
                    overlays.length = 0
                }

                vm.bdDrawObj = drawingManager;

                //-----------------------------------搜索框start-------------------------------

                var bs = map.getBounds();   //获取可视区域
                var bssw = bs.getSouthWest();   //可视区域左下角
                var bsne = bs.getNorthEast();   //可视区域右上角
                 //alert("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
                vm.basicNetSeeArea(bssw.lng,bssw.lat,bsne.lng,bsne.lat);
                vm.leftDownLongi = bssw.lng;
                vm.leftDownLongiLati = bssw.lat;
                vm.rightUpLongi = bsne.lng;
                vm.rightUpLati = bsne.lat;



                //------------------------------------搜索框end-----------------------------------

                map.addEventListener("zoomend", function(){
                    // alert("地图缩放至：" + this.getZoom() + "级");
                    var bs = map.getBounds();   //获取可视区域
                    var bssw = bs.getSouthWest();   //可视区域左下角
                    var bsne = bs.getNorthEast();   //可视区域右上角
                     //alert("suofang可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
                    vm.basicNetSeeArea(bssw.lng,bssw.lat,bsne.lng,bsne.lat);
                    vm.leftDownLongi = bssw.lng;
                    vm.leftDownLongiLati = bssw.lat;
                    vm.rightUpLongi = bsne.lng;
                    vm.rightUpLati = bsne.lat;
                });
            },

            secondBaiduMap(){


                this.isShowFile = true;

                var vm = {};
                vm = this;
                //alert(this.isShowFile);
                // 百度地图API功能
                let map = this.bdmap;
                //this.bdmap.clearOverlays();



                var poi = new BMap.Point(vm.houseLocationObj.longitude,vm.houseLocationObj.latitude);
                //map.centerAndZoom(poi, 12);//设置中心点坐标和地图级别
                //this.bdmap.centerAndZoom(vm.keyWord,12);      // 用城市名设置地图中心点
                map.enableScrollWheelZoom(); //启用鼠标滚动对地图放大缩小
                var marker = new BMap.Marker(poi);        // 创建标注
                //map.addOverlay(marker);                     // 将标注添加到地图中
                marker.enableDragging();   // 点标注可拖拽
                marker.addEventListener("dragend", function(e){
                    //alert("当前位置：" + e.point.lng + ", " + e.point.lat);
                    vm.houseLocationObj.longitude = e.point.lng;
                    vm.houseLocationObj.latitude = e.point.lat;
                });
                //marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                //添加卫星图start
                var mapType1 = new BMap.MapTypeControl(
                    {
                        mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP],
                        anchor: BMAP_ANCHOR_TOP_LEFT
                    }
                );

                var overView = new BMap.OverviewMapControl();
                var overViewOpen = new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT});

                map.addControl(mapType1);          //2D图，混合图
                map.addControl(overView);          //添加默认缩略地图控件
                //map.addControl(overViewOpen);      //右下角，打开
                //添加卫星图end

                //添加比例尺start
                var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});// 左上角，添加比例尺
                var top_left_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});  //左上角，添加默认缩放平移控件
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
                var drawingManager = new BMapLib.DrawingManager(map, {
                    isOpen: false, //是否开启绘制模式
                    enableDrawingTool: true, //是否显示工具栏
                    drawingMode:BMAP_DRAWING_RECTANGLE,//绘制模式  多边形
                    drawingToolOptions: {
                        anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
                        offset: new BMap.Size(5, 5), //偏离值
                        drawingModes:[
                            BMAP_DRAWING_POLYGON
                        ]
                    },
                    polygonOptions: styleOptions //多边形的样式
                });

                //添加鼠标绘制工具监听事件，用于获取绘制结果
                drawingManager.addEventListener('overlaycomplete', overlaycomplete);
                function clearAll() {
                    for(var i = 0; i < overlays.length; i++){
                        map.removeOverlay(overlays[i]);
                    }
                    overlays.length = 0
                }

                vm.bdDrawObj = drawingManager;

                //-----------------------------------搜索框start-------------------------------

                var bs = map.getBounds();   //获取可视区域
                var bssw = bs.getSouthWest();   //可视区域左下角
                var bsne = bs.getNorthEast();   //可视区域右上角
                //alert("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
                vm.basicNetSeeArea(bssw.lng,bssw.lat,bsne.lng,bsne.lat);
                vm.leftDownLongi = bssw.lng;
                vm.leftDownLongiLati = bssw.lat;
                vm.rightUpLongi = bsne.lng;
                vm.rightUpLati = bsne.lat;



                //------------------------------------搜索框end-----------------------------------

                map.addEventListener("zoomend", function(){
                    // alert("地图缩放至：" + this.getZoom() + "级");
                    var bs = map.getBounds();   //获取可视区域
                    var bssw = bs.getSouthWest();   //可视区域左下角
                    var bsne = bs.getNorthEast();   //可视区域右上角
                    //alert("suofang可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
                    vm.basicNetSeeArea(bssw.lng,bssw.lat,bsne.lng,bsne.lat);
                    vm.leftDownLongi = bssw.lng;
                    vm.leftDownLongiLati = bssw.lat;
                    vm.rightUpLongi = bsne.lng;
                    vm.rightUpLati = bsne.lat;
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
                        //vm.showMapvFunc(vm.locTbList);
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
                    if(this.baseAreaObj.provinceName!=""){
                        sendObj.code = this.baseAreaObj.provinceCode;
                        if(this.baseAreaObj.cityName!=""){
                            sendObj.code = this.baseAreaObj.cityCode;
                            if(this.baseAreaObj.regionName!=""){
                                sendObj.code = this.baseAreaObj.regionCode;

                                if(this.baseAreaObj.streetName!=""){
                                    sendObj.code = this.baseAreaObj.streetCode;


                                }

                            }

                        }

                    }
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
                        var num = vm.bdmap.getZoom();
                        vm.bdmap.setZoom(num+1);

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
                        var aFunc = vm.animation;
                        FixedTime.testApproves(aFunc);
                        var bFunc = vm.closeInfoAndDraw;
                        FixedAddRegion.testApproves(bFunc);
                        var cFunc = vm.closeAndMovePoi;
                        FixedMovePoi.testApproves(cFunc);
                         let tableList = [];
                         tableList.push(vm.houseLocationObj);
                        vm.hosTbList = tableList;
                        var tempLocCode = vm.hosTbList[0].locationCode;
                        FixedAddRegion.setCode(tempLocCode);
                        FixedMovePoi.setCode(tempLocCode);
//                        alert("总条数"+vm.hosTbList.length)
//                        console.log(vm.hosTbList);
                        var poi = new BMap.Point(eVal.geometry.coordinates[0],eVal.geometry.coordinates[1]);

//                        alert(tempLocCode);

                        var titleMsg = "";
//                        titleMsg = "<button type=\"button\" class=\"btn btn-success btn-xs\" onclick='addRegionFunc()'>添加区域</button>";
                        var obj = vm.hosTbList[0];
                        //alert("看看");
                        console.log(vm.hosTbList);
                        if(obj !=null){
                            titleMsg = titleMsg +"&nbsp&nbsp&nbsp---";
                        }


                    var point = new BMap.Point(vm.houseLocationObj.longitude,vm.houseLocationObj.latitude);
                        var myIcon = new BMap.Icon("../../../static/hkpImgIcon/png_flag_yellow.png", new BMap.Size(30,70));
                    var markerNowPoi = new BMap.Marker(point,{icon:myIcon});        // 创建标注
                        vm.bdmap.removeOverlay(vm.needRemoveMarker);
                    vm.bdmap.addOverlay(markerNowPoi);
                        markerNowPoi.enableDragging();   // 点标注可拖拽
                        markerNowPoi.addEventListener("dragend", function(e){
                            //alert("当前位置：" + e.point.lng + ", " + e.point.lat);
                            vm.houseLocationObj.longitude = e.point.lng;
                            vm.houseLocationObj.latitude = e.point.lat;
                        });
                    vm.needRemoveMarker = markerNowPoi;
                        titleMsg = titleMsg +"已关联房源"+"个.";



                        //上面是没处理格式的老数据

                        var infoMsg = "";

                        infoMsg = infoMsg+"<span>";
                        infoMsg = infoMsg+"编码:&nbsp&nbsp&nbsp"+vm.houseLocationObj.locationCode;
                        infoMsg = infoMsg+"</span></br>";


                        infoMsg = infoMsg+"<span>";
                        infoMsg = infoMsg+"经度:&nbsp&nbsp&nbsp"+vm.houseLocationObj.longitude;
                        infoMsg = infoMsg+"</span></br>";

                        infoMsg = infoMsg+"<span>";
                        infoMsg = infoMsg+"纬度:&nbsp&nbsp&nbsp"+vm.houseLocationObj.latitude;
                        infoMsg = infoMsg+"</span></br>";



                        infoMsg = infoMsg+"<button type=\"button\" class=\"btn btn-success btn-xs\" onclick='addRegionFunc()'>添加区域</button>";
                        infoMsg = infoMsg+"&nbsp&nbsp&nbsp<button type=\"button\" class=\"btn btn-warning btn-xs\" onclick='movePoiFunc()'>移动点</button>";
                        let countHeight = vm.hosTbList.length*40 + 50;

                        var opts = {
                            width : 330,     // 信息窗口宽度
                            height: 180,     // 信息窗口高度
                            title : titleMsg , // 信息窗口标题
                            enableMessage:true,//设置允许信息窗发送短息
                            message:"气泡信息,点击点触发~"
                        }
                        var infoWindow = new BMap.InfoWindow(infoMsg, opts);  // 创建信息窗口对象
                        vm.bdmap.openInfoWindow(infoWindow,poi); //开启信息窗口
                        vm.bdInfoWindow = infoWindow;
                        // infoWindow.addEventListener('close', function (e) {
                        //     //alert('移出去点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
                        //     alert("点击了");
                        //     console.log(e);
                        // });
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
                    // sendObj.keyWord = vm.cityName;
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
                        for(var i=0;i<vm.tableHeads.length;i++){
                            let houseName = vm.tableHeads[i].label;
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
                        //vm.$message.error('页面:获取数据失败!post/query');
//                        alert("页面:685行查询数据失败!houseLocation/query");
                        vm.loading = false;
                    });
                }
            },
            loadInputCity(){
                var vm = this;
                this.bdmap.clearOverlays();
                this.showNewCenter();
                //alert(vm.keyWord);
                //this.basicNetLocQuery();


            },
            loadHosLocDetail(){

                var vm = {};
                vm = this;
                let map = this.bdmap;
                //浏览器重新定位start
                var geolocation = new BMap.Geolocation();
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
                function myFun(result){
                    var cityName = result.name;
                    // map.setCenter(cityName);
                    console.log("当前定位城市:"+cityName);
                    console.log(result);
                    //vm.keyWord = cityName;//111222测试城市

                    // vm.keyWord = cityName;
                }
                // var myCity = new BMap.LocalCity();
                // myCity.get(myFun);
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
                map.setCenter(vm.keyWord);


                vm.handleBaiduMap();
                //vm.loading = false;
                //初始化请求queryMapLoc,根据地区编码查询房源位置
                //vm.basicNetLocQuery();




            },
            showNewCenter(){
                var vm = {};
                vm = this;
                vm.bdmap.setCenter(vm.keyWord);
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
//                var pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
//                pointCollection.addEventListener('click', function (e) {
//                    //alert('单击点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
//                    vm.locRegionList = [];
//                    console.log("e.tartget");
//                    console.log(e.tartget);
//                    console.log("e.type");
//                    console.log(e.type);
//
//                    var poi = new BMap.Point(e.point.lng,e.point.lat);
////                    var point = new BMap.Point(vm.houseLocationObj.longitude,vm.houseLocationObj.latitude);
////                    var marker = new BMap.Marker(point);        // 创建标注
////                    vm.bdmap.addOverlay(marker);
//                    console.log("e是");
//                    console.log(e);
//
//                    var aVal = "181008d80c8ea3943";
//                    for (var i = 0; i < vm.locTbList.length; i++) {
//                        points.push(new BMap.Point(vm.locTbList[i].longitude,vm.locTbList[i].latitude));
//                        if(e.point.lng==vm.locTbList[i].longitude,e.point.lat==vm.locTbList[i].latitude){
//                            aVal=vm.locTbList[i].locationCode;
//                            vm.g_locCode = vm.locTbList[i].locationCode;
//                        }
//                    }
//                    //vm.basicNetLocAnalysis(aVal,e);
////                    vm.remove_overlay();
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
                        //alert("查询出多边形数据量"+regionListPoly.length);
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
                        //alert("页面:获取数据失败!houseLocation/detail");
                    });
                }
            },
        },//end vue methods

    }



</script>


<style scoped>



    .now_page{
        padding:0px 5px 0px 0px;
    }
    .top_bar{
        z-index: 200;
        width: 100%;
        margin-left: 5px;
        height: auto;

    }
    .first_block{
        margin:10px 0px 0px 0px;

    }
    .content_bar{
        margin:3px 3px 3px 3px;
        padding:0px 0px 0px 0px;
        height: 70vh;
    }
    .bar_in_street {
        margin:0px 30px 0px 0px;
    }
    .bar_in_btn {
        margin:0px 0px 0px 12px;
    }



</style>