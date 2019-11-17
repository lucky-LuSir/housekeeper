<template slot-scope="scope">
    <el-dialog  :close-on-click-modal="false"  :visible.sync="houseLocationCommonDialogFormVisible" width="60%" top="40px"   append-to-body>
    <div class="root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row>
            <div class="navigation_div">
                <el-input size="mini"
                          @keyup.enter.native="searchEnterFun"
                          class="navigation_button_input"
                          v-model="keyWord"
                          placeholder="请输入查询条件">
                    <el-button slot="append" icon="el-icon-search" @click="reloadHouseLocation"/>
                </el-input>
            </div>
        </el-row>
        <!-- 新增用户,修改用户对话框 -->


        <!--第3个方框 一行树状表格-->
        <!--用户信息列表-->
        <div class="parttime_table">
            <el-table
                    :data="houseLocationList"
                    :highlight-current-row="true"
                    style="width: 100%;text-align: center;"
                    @current-change="handleCurrentChange2"
                    :show-header="true"
                    :height="380"
                    :border="true">
                <el-table-column align="center"
                                 label="位置编号"
                                 width="180">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.locationCode }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="地址别名"
                                 width="130">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.locationAlias }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="省名"
                                 width="70">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.provinceName}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="市名" width="70">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.cityName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="区域名" width="80">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.regionName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="街道名" width="100">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.streetName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="社区名" width="90">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.communityName }}</span>
                    </template>
                </el-table-column>

                <el-table-column align="center" label="详细地址" width="180">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.detailAddress }}</span>
                    </template>
                </el-table-column>

                <el-table-column align="center" label="门牌号" width="120">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.doorNumber }}</span>
                    </template>
                </el-table-column>

                <el-table-column align="center" label="经度" width="120">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.longitude }}</span>
                    </template>
                </el-table-column>


                <el-table-column align="center" label="纬度" width="120">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.latitude }}</span>
                    </template>
                </el-table-column>

                <el-table-column align="center" label="交通配套" width="150">
                    <template slot-scope="scope">
                        <span style="text-align: center">{{ scope.row.trafficFacilities }}</span>
                    </template>
                </el-table-column>
            </el-table>
            <template>
                <div class="block">
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
            </template>
        </div>

        <div style="text-align: center;">
            <el-button @click = "cancel()" type="primary" size="small">取消</el-button>
            <el-button @click="ok()" size="small" type="primary">确定</el-button>
        </div>

        <!--第3个方框(end)-->
    </div><!--根部分(end)-->
    </el-dialog>
</template>


<script scoped>
    import file from '../../common/components/file.vue'

    export default {
        components: {
            file
        },
        created: function () {
            var vm = {};
            vm = this;
            //菜单和按钮权限控制 end
            vm.basicReLoadHouseLocation();
        },
        updated() {

        },
        data() {
            return {
                keyWord:'',
                houseLocationCommonDialogFormVisible:false,
                start: 1,
                pageSize: 50,
                total: 0,
                pageSizes: [50],
                floatDivWidth: "",

                /*用户列表*/
                houseLocationList: [
                    {
                        locationCode:'',
                        locationAlias:'',
                        province:'',
                        provinceName:'',
                        city:'',
                        cityName:'',
                        region:'',
                        regionName:'',
                        street:'',
                        streetName:'',
                        detailAddress:'',
                        doorNumber:'',
                        longitude:'',
                        latitude:'',
                        trafficFacilities:'',
                        community:'',
                        communityName:'',
                        fileList:'',
                        locRegionList:[],
                        hasLocRegion:''
                    },

                ],
                currentRow:{

                },
            }
        },
        methods: {
            handleCurrentChange2(val) {
                this.currentRow = val;

            },
            cancel(){
                this.$emit("closeHouseLocation");
            },
            ok(){
                var vm=this;
                this.houseLocationCommonDialogFormVisible=false;
                this.$emit("setHouseLocation",this.currentRow);
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.basicReLoadHouseLocation();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.basicReLoadHouseLocation();
            },
            basicReLoadHouseLocation() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = (vm.start - 1) * vm.pageSize;
                    sendObj.pageSize = vm.pageSize;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "houseLocation/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.houseLocationList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!houseowner/select');
                    });
                }
            },

            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                //'回车搜索', keyCode, e
                if (keyCode == 13) {
                    vm.reloadHouseLocation();
                }

            },
            //查询按钮
            reloadHouseLocation() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 20;
                    sendObj.entity = obj;
                    sendObj.keyWord = this.keyWord;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "houseLocation/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.houseLocationList = response.data.result.data;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败! houseowner/select');
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
 .parttime_table{
     height: 420px;
 }
.navigation_button_input {
    width: 300px;
    margin-bottom: 10px;
}
</style>