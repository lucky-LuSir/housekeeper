<template>
    <div css="root" ref="myRoot">
        <div class="now_page ">
            <div class="top_bar">


                <el-input v-model="keyWord"
                          placeholder="支持位置名称、省份、城市搜索"
                          class="input-with-select" size="small" clearable>
                    <el-button slot="append" icon="el-icon-search" @click="netFirstPage()"></el-button>
                </el-input>

                <el-select v-model="queryObject.createDeptCode" class="change-park" size="small"  placeholder="请选择创建部门" filterable @change="changeDeptBase">
                    <el-option
                            v-for="item in deptEntity"
                            :key="item.deptCode"
                            :label="item.deptName"
                            :value="item.deptCode">
                        <span style="float: left">{{ item.deptName }}</span>
                        <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode }}</span>
                    </el-option>
                </el-select>

                <el-input  v-model="queryObject.createName"  placeholder="请选创建人" size="small" class="change-park" :disabled="true">
                    <el-button slot="append" icon="el-icon-circle-plus-outline" @click="empCommonDialogFormVisible = true"></el-button>
                </el-input>

                <el-button class="more-term-button" size="small" type="success" style="float: left;" plain @click="netFirstPage()">查询</el-button>

                <el-button class="more-term-button" size="small" type="danger" style="float: left;" plain @click="clearQuery()">清空条件</el-button>


                <el-popover
                        placement="top-start"
                        width="200"
                        trigger="hover">

                    <el-tag class="term-tag" v-show="(keyWord)" style="max-width: 200px">{{keyWord}}
                    </el-tag>
                    <el-tag class="term-tag" v-show="(queryObject.createDeptName)" style="max-width: 170px">
                        {{queryObject.createDeptName}}
                    </el-tag>
                    <el-tag class="term-tag" v-show="(queryObject.createCode)" style="max-width: 170px">
                        {{queryObject.createName}}
                    </el-tag>

                    <span v-if="!keyWord && !queryObject.createDeptName && !queryObject.createName ">暂无筛选条件</span>
                    <el-button class="more-term-button" size="small" type="warning" slot="reference" plain>已查条件
                    </el-button>
                </el-popover>

            </div>
            <!--人员查询公共面板-->
            <el-dialog  :close-on-click-modal="false" :visible.sync="empCommonDialogFormVisible" append-to-body>
                <CommonInternalUser :empCommonDialogFormVisible="empCommonDialogFormVisible"  v-on:changed2="closeEmpCommon($event)"></CommonInternalUser>
            </el-dialog>
            <div class="content_bar">
                <!--<h1 style="text-align: center">订单跟进</h1>-->
                <el-table
                        id="odfollowId"
                        size="mini"  :height="fullHeight-180"
                        width="100"
                          class="now_table"
                          :data="locTbList"
                          border>
                    <el-table-column
                            align="center"
                            label="序号"
                            type="index"
                            fixed
                            width="60px">
                    </el-table-column>
                    <el-table-column
                            label="位置名称"
                            align="left"
                            prop="locationAlias"
                            fixed
                            width="260px">
                    </el-table-column>
                    <el-table-column
                            label="位置地址"
                            align="left"
                            min-width="560px">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.provinceName}} _ {{ scope.row.cityName}}_ {{ scope.row.regionName}}_ {{ scope.row.streetName}}_ {{ scope.row.detailAddress}}</span>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" label="操作" width="300" height="20">
                        <template slot-scope="scope" style="float: right;text-align: right;">

                            <el-button
                                    size="mini"
                                    type="primary"
                                    icon="el-icon-edit"
                                    @click="openDialog(scope.$index,scope.row)">
                                添加区域
                            </el-button>
                        </template>
                    </el-table-column>

                    <el-table-column
                            label="创建人编码"
                            align="center"
                            prop="createCode"
                            width="360px">
                    </el-table-column>
                    <el-table-column
                            label="创建人部门编码"
                            align="center"
                            prop="createDeptCode"
                            min-width="260px">
                    </el-table-column>

                    <el-table-column
                            label="创建时间"
                            align="center"
                            min-width="260px">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.createTime | timeDate }}</span>
                        </template>
                    </el-table-column>

                </el-table>
            </div>

            <el-dialog  :close-on-click-modal="false" :visible.sync="isShowFile" width="75%" center top="10vh"  >
                <hos-loc-region-list
                        v-if="isShowFile"
                        :loccode="sendLocationCode"
                        :sendLocName="sendLocName"
                        v-on:handleHosMap="acceptHosMap"
                        v-on:sureMethod="acceptSure">

                </hos-loc-region-list>
            </el-dialog>


            <!-- 分页栏 -->
            <div class="page-box">
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="basicNetLocQuery"
                    :current-page.sync="currentPage"
                    :page-sizes="[ 10, 20, 30]"
                    :page-size="pageSizeData"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="totalCount">
            </el-pagination>
            </div>

        </div>
    </div>
</template>


<script scoped>
    import FileSaver from 'file-saver';
    import XLSX from 'xlsx';
    import CommonInternalUser from "../../hrm/views/emp/CommonInternalUser"
    import hosLocRegionList from "./hosLocRegionList";
    export default {
        components: {
            CommonInternalUser,
            hosLocRegionList
        },
        created: function () {



            var vm = {};
            vm = this;
            vm.netFirstPage();
            this.loadDeptList();


        },
        props:["orderCode"],
        data() {
            return {
                fullHeight: document.documentElement.clientHeight,
                sendLocName:'',
                sendLocationCode:'aaa',
                isShowFile: false,
                //总条目数
                totalCount:666,
                //当前页数
                currentPage:1,
                //每页条数
                pageSizeData:30,
                //人员查询组件dialog
                empCommonDialogFormVisible:false,
                deptEntity:[],
                floatDivHeight: 50,
                "locTbList":[],
                keyWord:"",
                queryObject:{
                    "id": 85426,
                    "createCode": "",
                    "createName":"",
                    "createDeptCode": "",
                    "createDeptName": "",
                    "lastUpdateCode": "E201805180003de30",
                    "createTime": 1547128027731,
                    "lastUpdateTime": 1547128027731,
                    "isDeleted": false,
                    "locationCode": "loc190110a18beaf3602",
                    "locationAlias": "钢自成剪切中心",
                    "province": "330000",
                    "provinceName": "浙江省",
                    "city": "330100",
                    "cityName": "杭州市",
                    "region": "330110",
                    "regionName": "余杭区",
                    "street": "330110008",
                    "streetName": "崇贤街道",
                    "detailAddress": "运河路",
                    "doorNumber": "898",
                    "longitude": 120.208623,
                    "latitude": 30.408832,
                    "trafficFacilities": "320国道与运河路路口"
                },
        }//end vue data return
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
        mounted(){
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight;
                    that.fullHeight = window.fullHeight
                })()
            }

        },
        methods: {
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
            openDialog(index, rowsRole){
                this.isShowFile = true;
                // alert("往下看一个房源.");
                console.log(rowsRole);
                this.sendLocationCode=rowsRole.locationCode;
                this.sendLocName= rowsRole.provinceName + '_'+rowsRole.cityName+ '_' + rowsRole.regionName + '_'+rowsRole.streetName + '_'+rowsRole.detailAddress ;
                //{{ scope.row.provinceName}} _ {{ scope.row.cityName}}_ {{ scope.row.regionName}}_ {{ scope.row.streetName}}_ {{ scope.row.detailAddress}}
            },
            acceptSure(aVal){
                // alert(aVal.msg);
                // alert(aVal.flagshow);
                this.isShowFile = aVal.flagshow;
            },
            acceptHosMap(aVal){
                // alert(aVal.msg);
                // alert(aVal.flagshow);
                this.isShowFile = aVal.flagshow;

            },
            netFirstPage(){
                this.currentPage = 1;
                this.basicNetLocQuery(1);
            },
            // 分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.currentPage = 1;
                this.basicNetLocQuery(1);
            },
            clearQuery(){
              this.keyWord='';
              this.queryObject.createDeptCode='';
              this.queryObject.createDeptName='';

                this.queryObject.createCode='';
                this.queryObject.createName='';
                this.netFirstPage();
            },
            changeDeptBase(aval){
                //赋值部门name
                let areaObj = {};
                areaObj = this.deptEntity.find((item)=>{
                    return item.deptCode === aval;
                });
                this.queryObject.createDeptCode= areaObj.deptCode;
                this.queryObject.createDeptName= areaObj.deptName;
                this.netFirstPage();
            },
            //关闭人员公共面板
            closeEmpCommon(data){
                var ddd=data;
                this.empCommonDialogFormVisible=data.empCommonDialogFormVisible;
                this.queryObject.createCode=data.userCode;
                this.queryObject.createName=data.userName;
                this.netFirstPage();
            },
            basicNetLocQuery(val){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj.createDeptCode = vm.queryObject.createDeptCode;
                    obj.createCode = vm.queryObject.createCode;
                    var sendObj = {};
                    sendObj.pageSize = this.pageSizeData;//每页条数
                    //val当前页传入值
                    sendObj.start = (val - 1) * (sendObj.pageSize);
                    sendObj.entity = obj;
                    sendObj.keyWord = vm.keyWord;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "houseLocation/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.locTbList = response.data.result.data;
                        vm.totalCount = response.data.result.totalCount;
                        // alert("总条数"+vm.totalCount);
                        let h = vm.floatDivHeight;
                        vm.floatDivHeight = h-1;
                        console.log("请求的houseLocation/query");
                    }).catch(function (error) {
                        //vm.$message.error('页面:获取数据失败!post/query');
                        alert("页面:获取数据失败!houseLocation/query");
                    });
                }
            },
            //加载部门数据
            loadDeptList(){
                var vm=this;
                var obj = {};
                var sendObj = {};
                sendObj.start = 0;
                   sendObj.pageSize = 10000;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "dept/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {

                    }
                    vm.deptEntity=response.data.result.data;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!user/query');
                });
            },
        },

    }
</script>


<style scoped>
     /*位置列表*/
    .root {
        background-color: white;
    }

    .now_page{
        background-color: white;
    }
    .top_bar{
        height: 38px;
     }
    .content_bar{
        padding:0px 0px 0px 0px;
    }
    .now_table{
        margin:0px 0px 0px 0px;
        line-height: 30px;
    }

    /*搜索框*/
    .input-with-select {
        width: 270px;
        float: left;
        margin-left: -10px;
    }
    .change-park {
        float: left;
        margin-left: 20px;
        height: 30px;
        line-height: 30px;
        width: 150px;
    }
    /*已选条件展示*/
    .term-tag {
        float: left;
        margin-left: 10px;
        font-size: 14px;
        background-color: white;
    }
    /*更多条件按钮*/
    .more-term-button {
        float: right;
        margin-left: 20px;
    }

    /*分页栏样式*/
    .page-box {
        height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }
</style>