<template>
    <div css="root" ref="myRoot" style="height: 100vh;">

       <!-- <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="handleBaiduMap"
        >
            添加区域
        </el-button>-->


        <!--<h1>&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;地图测试api hosLocRegion</h1>-->
        <el-row :gutter="10">
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                <p class="title">{{sendLocName}}<span class="title_red"></span></p>
            </el-col>
        </el-row>
        <el-row :gutter="10" style="margin-bottom: 20px;">

            <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                <p class="title">经度:{{houseLocationObj.longitude}}<span class="title_red"></span></p>
            </el-col>
            <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                <p class="title">纬度:{{houseLocationObj.latitude}}<span class="title_red"></span></p>
            </el-col>

            <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" style="float: right;">
                <el-select v-model="selectWordName"
                           class="change-park"
                           size="small"
                           placeholder="输入关键字然后选择"
                           filterable
                           remote
                           :remote-method="remoteMethod"
                           :loading="loading"
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

                <el-button type="success" @click="saveSearchPoint">保存为此位置</el-button>
            </el-col>


        </el-row>

        <div id="allmap" style="overflow:hidden;zoom:1;position:relative;">
            <div id="map" style="height:100%;-webkit-transition: all 0.5s ease-in-out;transition: all 0.5s ease-in-out;"></div>
        </div>
        <div class="now_page col-sm-12  col-md-12  ">

            <div class="top_bar col-sm-12 col-md-12"></div>
            <div class="content_bar col-sm-12 col-md-12">
                <div id="result">
                    <!--<div id="r-result">请输入:<input type="text" id="suggestId" size="20" value="百度" style="width:150px;" /></div>
                    <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>-->

                    <div>

                        <el-button type="warning" @click="remove_overlay" >刷新查看</el-button>
                        <el-button type="danger" @click="saveMovePoint" style="float: right;">保存移动后位置</el-button>
                    </div>
                    <div style="text-align: right;">
                        <span slot="footer" class="dialog-footer" >

                           </span>
                    </div>
                    <!--<div >
                        城市名: <el-input v-model="cityName" placeholder="请输入内容"></el-input>
                        <el-button type="success" @click="theLocation()">查询</el-button>
                    </div>-->

                    <!--<div >
                        关键字: <el-input v-model="wordBaiduName" placeholder="请输入内容"></el-input>
                        <div id="r-result"   style="height: 200px;width: 200px;background-color: yellow" v-show="false"></div>
                        <el-button type="success" @click="handleBaiduSerach()">查询</el-button>
                    </div>-->


                </div>
            </div>


        </div >
        <div style="text-align: center;">
            <span slot="footer" class="dialog-footer" >
                    <el-button @click="handleCancle">取 消</el-button>
                    <el-button type="primary" @click="handleSure">确定区域</el-button>
                    <!--<el-button type="primary" @click="handleBack">后台区域</el-button>-->
            </span>
        </div>

    </div>
</template>



<script scoped>
    import FileSaver from 'file-saver';
    import XLSX from 'xlsx';
    import icon_locat from '../../../static/hkpImgIcon/icon_locat.svg';
    export default {
        components: {

        },
        created: function () {




            // alert("111子组件接收到了locationCode是:"+this.loccode);

            // alert("111子组件接收到了locationCode是:"+this.sendLocName);

            // var vm = {};
            // vm = this;
            // vm.basicReLoadOrderfollow();


        },
        props:["loccode" ,"sendLocName"],
        data() {
            return {

                leftDownLongi:'',
                leftDownLongiLati:'',
                rightUpLongi:'',
                rightUpLati:'',
                selectPointObj:{
                    lng:'',
                    lat:''
                },
                loading: false,
                selectWordName:'',
                searchPanelList:[],
                wordBaiduName:'上海虹桥火车站',
                canSeeRegionList:[],
                bdmap:'',
                cityName:'上海市',
                isShowFile: false,
                overlays:"",
                floatDivHeight: 50,
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
                    "longitude": 0,
                    "latitude": 0,
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


            this.basicNetHosLocDetail();




        },

        updated(){
            // var bs = this.bdmap.getBounds();   //获取可视区域
            // var bssw = bs.getSouthWest();   //可视区域左下角
            // var bsne = bs.getNorthEast();   //可视区域右上角
            // alert("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);


        },
        methods: {
            handleBack(){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var sendObj = {};
                    var obj = {};
                    obj.locationCode = vm.houseLocationObj.locationCode;
                    obj.locationAlias = vm.houseLocationObj.locationAlias;
                    sendObj.leftDown = {};
                    sendObj.leftDown.longitude = vm.leftDownLongi;
                    sendObj.leftDown.latitude = vm.leftDownLongiLati;

                    sendObj.rightUp = {};
                    sendObj.rightUp.longitude = vm.rightUpLongi;
                    sendObj.rightUp.latitude = vm.rightUpLati;

                    vm.locRegionList = [];
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

                    //验证画出的一个多变形和可视区域的多边形重复start

                    //验证画出的一个多变形和可视区域的多边形重复end

                    if(false){console.log("---重复了不提交");}
                    else{
                        var options = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "houseLocation/repeatRegion",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {

                            let chinaMsg = response.data.stackTrace;
                            let flag = response.data.result;
                            vm.$notify({
                                title: chinaMsg,
                                message: flag,
                                type: 'success'
                            });
                        }).catch(function (error) {
                            //vm.$message.error('页面:获取数据失败!post/query');
                            alert("页面:获取数据失败!orderfollowup/query");
                        });
                    };
                }

            },
            saveMovePoint(){
                var vm = {};
                vm = this;

                vm.selectPointObj.lng = vm.houseLocationObj.longitude;
                vm.selectPointObj.lat = vm.houseLocationObj.latitude;
                vm.basicNetEditLocPoint();
            },
            saveSearchPoint(){
                this.basicNetEditLocPoint();

            },
            basicNetEditLocPoint(){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj.longitude = vm.selectPointObj.lng;
                    obj.latitude = vm.selectPointObj.lat;
                    obj.locationCode = this.loccode;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "houseLocation/update",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        alert("修正位置成功");
                        //保存后重新加载
                        vm.bdmap.clearOverlays();
                        vm.basicNetHosLocDetail();
                    }).catch(function (error) {
                        //vm.$message.error('页面:获取数据失败!post/query');
                        alert("页面:修正位置成功失败!");
                    });
                }
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
                    pageCapacity:5
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
                    let aMark = new BMap.Marker(pp,{icon:myIcon});
                    vm.bdmap.addOverlay(aMark);    //添加标注
                    aMark.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                });

            },
            remoteMethod(query) {
                if (query !== '') {
                    //alert(query);
                    this.selectWordName = query;
                    this.loading = true;
                    setTimeout(() => {
                        this.loading = false;
                        this.handleBaiduSerach();
                    }, 200);
                } else {
                    // alert("超时,未远程搜索");
                    vm.searchPanelList = [];
                }
            },
            remove_overlay(){
                //alert("刷新了");
                this.bdmap.clearOverlays();
                this.basicNetHosLocDetail();
                // this.selectWordName = '';
                // this.searchPanelList = [];
            },
            handleBaiduSerach(){
                var vm = {};
                vm = this;
                //增加智能搜索
                var local = new BMap.LocalSearch(this.bdmap, {
                    renderOptions:{map: this.bdmap, panel:"r-result"},
                    pageCapacity:5
                });


                //local.search("天安门");
                // alert(this.selectWordName);
                var rr=local.search(this.selectWordName);

                local.setSearchCompleteCallback(function(){
                    var lr = local.getResults();
                    console.log("打印查询对象");
                    console.log(lr.Ar);
                    vm.searchPanelList = lr.Ar;
                });
                // console.log("打印查询对象");
                // console.log(local);
                // console.log("打印查询对象rr");
                // console.log(local.getResults());
                // local.searchInBounds(myKeys, map.getBounds());
                //
            },
            /*线段是否相交
            seg:[{lat:xxx,lng:xxx},{lat:xxx,lng:xxx}]
            */
            mapLapLine(segA,segB){
                const abc = (segA[0].latitude - segB[0].latitude)*(segA[1].longitude - segB[0].longitude) - (segA[0].longitude - segB[0].longitude)*(segA[1].latitude - segB[0].latitude);
                const abd = (segA[0].latitude - segB[1].latitude)*(segA[1].longitude - segB[1].longitude) - (segA[0].longitude - segB[1].longitude)*(segA[1].latitude - segB[1].latitude);
                if(abc * abd >=0){
                    return false;
                }
                const cda = (segB[0].latitude - segA[0].latitude)*(segB[1].longitude - segA[0].longitude) - (segB[0].longitude - segA[0].longitude)*(segB[1].latitude - segA[0].latitude);
                const cdb = cda+abc-abd;
                return !(cda*cdb>=0);
            },
            // 判断两多边形所有线段是否相交
            mapLapPolygon(plyA,plyB){
                for(let i=0,il=plyA.length;i<il;i++){
                    for(let j=0,jl=plyB.length;j<jl;j++){
                        const segA=[plyA[i],plyA[i===il -1 ?0:i+1]];
                        const segB=[plyB[j],plyB[j===jl -1 ?0:j+1]];
                        if(this.mapLapLine(segA,segB)){
                            alert("---线段相交了!---");
                            return true;
                        }
                    }
                }
                return false;
            },
            //判断两多边形是否存在点与区域的包含关系
            mapLapPoint(plyA,plyB){
                const [pA,pB]=[[],[]];

                plyA.forEach((item)=>{
                    pA.push(new BMap.Point(item.longitude,item.latitude));
                });
                plyB.forEach((item)=>{
                    pB.push(new BMap.Point(item.longitude,item.latitude));
                });

                let[a,b]=[false,false];
                a = pA.some(item => BMapLib.GeoUtils.isPointInPolygon(item, new BMap.Polygon(pB)));
                if(!a){
                    b = pB.some(item => BMapLib.GeoUtils.isPointInPolygon(item,new BMap.Polygon(pA)));
                }

                if(a==true){alert("---a点在多边形里面了!---");}
                if(b==true){alert("---b点在多边形里面了!---");}
                return a || b;
            },
            //判断多边形是否重叠,入口函数
            mapLapOverlap(plyA,plyB){
                return this.mapLapPolygon(plyA,plyB) || this.mapLapPoint(plyA,plyB);
            },
            theLocation(){
                var city = this.cityName;
                if(city != ""){
                    this.bdmap.centerAndZoom(city,11);      // 用城市名设置地图中心点
                }
            },
            handleSure(){

                this.basicNetAddLocRegion();
                let obj = {};
                obj.msg = "子组件--地图--确定按钮";
                obj.flagshow = false;
                this.$emit("sureMethod", obj);
            },
            basicNetAddLocRegion(){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var sendObj = {};
                    var obj = {};
                    obj.locationCode = vm.houseLocationObj.locationCode;
                    obj.locationAlias = vm.houseLocationObj.locationAlias;
                    vm.locRegionList = [];
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

                    //验证画出的一个多变形和可视区域的多边形重复start
                    var isRepeat = false;
                    var plyA = vm.locRegionList;
                    for(var j=0;j<vm.canSeeRegionList.length;j++){
                        var plyB = vm.canSeeRegionList[j].pointRegionList;
                        isRepeat = vm.mapLapOverlap(plyA,plyB);
                    }

                    //验证画出的一个多变形和可视区域的多边形重复end

                    if(isRepeat){console.log("---重复了不提交");}
                    else{
                        var options = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "houseLocation/createLocRegion",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$notify({
                                title: '新增区域成功',
                                message: '成功',
                                type: 'success'
                            });
                        }).catch(function (error) {
                            //vm.$message.error('页面:获取数据失败!post/query');
                            alert("页面:获取数据失败!orderfollowup/query");
                        });
                    };
                }
            },
            handleCancle(){
                let obj = {};
                obj.msg = "子组件--地图--取消";
                obj.flagshow = false;
                this.$emit("handleHosMap", obj);
            },
            handleBaiduMap(){


                this.isShowFile = true;

                var vm = {};
                vm = this;
                //alert(this.isShowFile);
                // 百度地图API功能
                //, { enableMapClick: false }   禁用地图景点点击
                var map = new BMap.Map('map', { enableMapClick: false });
                //var map = new BMap.Map('map');
                this.bdmap= map;
                var poi = new BMap.Point(vm.houseLocationObj.longitude,vm.houseLocationObj.latitude);
                map.centerAndZoom(poi, 16);//设置中心点坐标和地图级别
                map.enableScrollWheelZoom(); //启用鼠标滚动对地图放大缩小
                var marker = new BMap.Marker(poi);        // 创建标注
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
                var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
                var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
                /*缩放控件type有四种类型:
                BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮*/
                //map.addControl(top_left_control);
                //map.addControl(top_left_navigation);

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
                    var path = [];
                     path = e.overlay.getPath();//Array<Point> 返回多边型的点数组
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

                //-----------------------------------搜索框start-------------------------------

                var bs = map.getBounds();   //获取可视区域
                var bssw = bs.getSouthWest();   //可视区域左下角
                var bsne = bs.getNorthEast();   //可视区域右上角
                // alert("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
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
                    // alert("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
                    vm.basicNetSeeArea(bssw.lng,bssw.lat,bsne.lng,bsne.lat);
                    vm.leftDownLongi = bssw.lng;
                    vm.leftDownLongiLati = bssw.lat;
                    vm.rightUpLongi = bsne.lng;
                    vm.rightUpLati = bsne.lat;
                });
            },
            exportExcel(){
                /* 1创建 workbook 对象 from table */
                //alert("导出开始");
                var wb = XLSX.utils.table_to_book(document.querySelector('#odfollowId'))
                /* 2获得 binary string as output */
                var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
                try {
                    FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), 'sheetjs订单跟进表.xlsx')
                } catch (e) { if (typeof console !== 'undefined') console.log(e, wbout); console.log("导出excel捕获的异常ttt");}
                return wbout

            },
            basicNetHosLocDetail(){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var sendObj = {};
                    var obj = {};
                    obj.locationCode = this.loccode;
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
                        // alert("经度"+vm.houseLocationObj.longitude+"纬度"+vm.houseLocationObj.latitude);
                        // alert(vm.houseLocationObj.locationCode);
                        vm.handleBaiduMap();
                    }).catch(function (error) {
                        //vm.$message.error('页面:获取数据失败!post/query');
                        alert("页面:获取数据失败!houseLocation/detail");
                    });
                }
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
                        alert("页面:获取数据失败!houseLocation/detail");
                    });
                }
            },
        },//end vue methods

    }
</script>


<style scoped>


    .root {
        /*min-width: ;*/
        /*min-height: ;*/
        /*margin: top right bottom left*/
        /*margin:0px 0px 0px 0px;*/
        /*border-style: solid;
        border-width: 15px 5px 15px 5px;
        border-color: #ff0000 rgb(0,0,255) #ff0000 rgb(0,0,255);*/
        /*padding: top right bottom left*/
        /*padding:0px 0px 0px 0px;*/
        /*内容的高度*/
        /*width: auto;*/
        /*height: auto;*/
        background-color: white;
    }

    .now_page{
        /*border-style: solid;
        border-width: 5px 5px 5px 5px;
        border-color: #ff0000 rgb(0,0,255) #ff0000 rgb(0,0,255);*/
        padding:0px 5px 0px 0px;
        background-color: white;
    }
    .top_bar{
    /*margin: top right bottom left*/
        margin:3px 3px 3px 3px;
        /*border-style: solid;
        border-width: 1px 1px 1px 1px;
        border-color: #00ff00 #00ff00 #00ff00 #00ff00;*/
        padding:10px 10px 38px 10px;
        height: 38px;

     }
    .content_bar{
        margin:3px 3px 3px 3px;
        /*border-style: solid;
        border-width: 1px 1px 1px 1px;
        border-color: green green green green;*/
        padding:0px 0px 0px 0px;
    }
    .now_table{
        margin:0px 0px 0px 0px;
        line-height: 30px;
    }


    body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
    #allmap {width: 100%; height:500px; overflow: hidden;}
    #result {width:100%;font-size:12px;}
    dl,dt,dd,ul,li{
        margin:0;
        padding:0;
        list-style:none;
    }
    p{font-size:12px;}
    dt{
        font-size:14px;
        font-family:"微软雅黑";
        font-weight:bold;
        border-bottom:1px dotted #000;
        padding:5px 0 5px 5px;
        margin:5px 0;
    }
    dd{
        padding:5px 0 0 5px;
    }
    li{
        line-height:28px;
    }

    .title{
        font-size: 13px;
        margin-left: 13px;
        margin-top: 10px;
        margin-bottom: 10px;
        font-weight:900;
        color: #3dbd7d;
    }
    .title_red{
        /*color:red;*/
    }
</style>