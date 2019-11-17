<template slot-scope="scope">
    <el-dialog  :close-on-click-modal="false" :visible.sync="hosCommonDialogFormVisible" width="60%" append-to-body>
    <div ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row>
            <div>
                <el-input size="small"
                          @keyup.enter.native="searchEnterFun"
                          class="navigation_button_input"
                          v-model="selectUserName"
                          style="margin-left: 20px;margin-top: 10px;width: 300px;"
                          placeholder="请输入查询条件">
                    <el-button slot="append" icon="el-icon-search" @click="findReLoadHouse"></el-button>
                </el-input>
            </div>
        </el-row>
        <!-- 新增用户,修改用户对话框 -->


        <!--第3个方框 一行树状表格-->
        <!--用户信息列表-->
        <div class="hos_table">
            <el-table
                    :data="HouseList"
                    :highlight-current-row="true"
                    style="width: 100%;text-align: center;"
                    @current-change="handleCurrentChange2"
                    :show-header="true"
                    :border="true"
                    height="380"
                    max-height="380">
                <el-table-column align="center"
                                 label="房源编号"
                                 width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.houseCode }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="房源标题"
                                 width="300">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.houseName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="服务专员" width="100">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.empName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="可租面积"
                                 width="100">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.area }} ㎡</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="价格" width="80">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.price }}</span>
                    </template>
                </el-table-column>
            </el-table>
                <div>
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page.sync="start"
                            :page-sizes="pageSizes"
                            :page-size="pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </div>
        </div>

        <div style="text-align: center">
            <el-button size="small" @click="close()" type="primary">取消</el-button>
            <el-button size="small" @click="ok()" type="primary">确定</el-button>
        </div>
        <!--第3个方框(end)-->


    </div><!--根部分(end)-->
    </el-dialog>
</template>


<script scoped>


    export default {
        components: {

        },
        created: function () {

        },
        updated() {

        },
        data() {
            return {
                start: 1,
                hosCommonDialogFormVisible:false,
                pageSize: 50,
                total: 0,
                pageSizes: [50],
                floatDivWidth: "",
                selectUserName: '',
                /*用户列表*/
                HouseList: [],
                currentRow:{
                },
                house: {
                    hasCollect:null,
                    area: null,
                    houseCode: '',//房源编码
                    empName: '',//服务专员名称
                    empPhone: '',//服务专员电话
                    ownerName: '',//业主姓名
                    ownerPhone: '',//业主电话
                    ownerTypeName: '',//业主属性
                    companyName: '',//业主公司

                    hasEia: '',//是否可环评
                    hasRegistry: '',// 是否可注册
                    hasCertificate: '',//是否有产证
                    hasCut: '',//是否可分割
                    hasShortLease: '',//是否可短租
                    hasPlatform: '',//是否有货台
                    hasElevator: '',//是否有货梯
                    hasParking: '',//是否有停车位
                    hasInstallCrane: '',//是否有行车
                    hasBathroom: '',//是否有卫生间
                    hasOfficeArea: '',//有无办公区域
                    hasDischargeSewage: '',//有无排污证

                    houseStatusName: '',// 状态
                    layerArea: '',//总面积
                    area: '',//剩余面积
                    housePurposeName: '',//房源用途
                    price: '',//价格
                    severalLayers: '',//共有层数
                    whereLayer: '',//所在层数
                    layerHeight: '',//层高
                    minAcreage: '',//最小分割面积
                    fireLevelName: '',//消防等级
                    maxElectric: '',//用电量
                    houseTypeName: '',//房源类型
                    enterTime: '',//可入住时间
                    houseFrom: '',//房源来源
                    lessLease: '',//最短租期
                    moreLease: '',//最长租期
                    houseStructureName: '',//房源结构
                    industryRestriction: '',//行业限制

                    provinceName: '',//所属省份
                    cityName: '',//所属城市
                    regionName: '',//所属区域
                    streetName: '',//所属街道
                    houseLocation: {
                        provinceName: "",
                        cityName: "",
                        regionName: "",
                        streetName: "",
                        communityName: "",
                        trafficFacilities: ""
                    },//所属地址

                    houseName: '',//房源标题
                    features: '',//房源特色
                    forInsdustry: '',//适合行业
                    houseowner: {
                        houseownerTypeName: '',
                        houseownerName: '',
                        houseownerSexName:'',
                        houseownerTypeName:''
                    },
                    houseownerContacts: [
                        {
                            contactName: "",
                            contactPhone: ""
                        }
                    ]
                },
            }
        },
        methods: {
            close(){
                this.hosCommonDialogFormVisible=false;
            },
            handleCurrentChange2(val) {
                this.currentRow = val;

            },
            ok(){
                var vm=this;
                this.hosCommonDialogFormVisible=false;
                this.$emit("changed",this.currentRow);
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.basicReLoadHouse();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.basicReLoadHouse();
            },
            basicReLoadHouse() {
                var vm = {};
                vm = this;
                var flag = true
                if (flag) {        //flag留后面表单验证
                    var obj = vm.house;
                    var sendObj = {};
                    sendObj.start = (vm.start - 1) * vm.pageSize;
                    sendObj.pageSize = vm.pageSize;
                    sendObj.entity = obj;
                    sendObj.houseQuery = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "house/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.HouseList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!house/query');
                    });
                }
            },

            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                //'回车搜索', keyCode, e
                if (keyCode == 13) {
                    vm.findReLoadHouse();
                }

            },
            //查询按钮
            findReLoadHouse() {
                var vm = {};
                vm = this;
                var flag = true;
                //flag留后面表单验证
                if (flag) {
                    var obj = {};
                    var sendObj = vm.house;
                    sendObj.keyword = this.selectUserName;

                    sendObj.start = 0;
                    sendObj.pageSize = 30;
                    sendObj.houseQuery = obj;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "house/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.HouseList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败! house/query');
                    });
                }
            },
        }
    }

</script>

<style scoped>
    .root{
        height: 500px;
    }
    .hos_table{
        margin-top: 10px;
        height: 420px;
    }
    .navigation_button_input {
        width: 300px;
    }
</style>