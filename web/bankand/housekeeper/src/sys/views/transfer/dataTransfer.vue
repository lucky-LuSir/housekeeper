<template slot-scope="scope" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
    <div css="root" style="margin-left: 20px;margin-top: 20px">
        <el-form :model="transferEntity">
            <div>
                <span style="color: red;font-size: 16px">注1：房源编码与转移人至少填一个，如果两个都填则按转移人为准</span><br>
                <span style="color: red;font-size: 16px">注2：按转移人转移数据，可以配合省市区街道来转移</span><br>
                <span style="color: red;font-size: 16px">注3：按房源编码转移，只能转移自己部门的房源，不可以配合省市区街道来转移</span>
            </div>
            <div style="margin-top: 10px">
                <el-row :gutter="0">
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <el-form-item label="所属省份" prop="province">
                            <el-select id="provinceNameCpy"
                                       v-model="transferEntity.province"
                                       @visible-change="handNeedProvinceCpy"
                                       @change="changeProvCpy($event)"
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
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <el-form-item label="所属城市" prop="city">
                            <el-select id="cityNameCpy"
                                       v-model="transferEntity.city"
                                       @focus="getCitieListCpy(transferEntity.province)"
                                       @change="changeCityCpy($event)" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itCityArrCpy"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <el-form-item label="所属区县" prop="region">
                            <el-select id="regionNameCpy"
                                       v-model="transferEntity.region"
                                       @focus="getAreaListCpy(transferEntity.city)"
                                       @change="changeCityRegion($event)"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itAreaArrCpy"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <el-form-item label="所属街道" prop="street">
                            <el-select id="streetNameCpy"
                                       v-model="transferEntity.street"
                                       @focus="getStreetListCpy(transferEntity.region)"
                                       @change="changeStreet($event)"
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
                </el-row>
            </div>
            <div>
                <el-row :gutter="0">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="房源编码" prop="shiftHouseData">
                            <el-input v-model="transferEntity.shiftHouseData" placeholder="房源编码，多个编码英文逗号隔开"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>
            <div>
                <el-row :gutter="0">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="房源转移人" prop="hisEmpCode">
                            <el-select  v-model="transferEntity.hisEmpCode" @change="selectHisEmp($event)" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in userList"
                                        :key="item.userCode"
                                        :value="item.userCode"
                                        :label="item.userName">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="房源接收人" prop="newEmpCode">
                            <el-select  v-model="transferEntity.newEmpCode" @change="selectNewEmp($event)" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in userList"
                                        :key="item.userCode"
                                        :value="item.userCode"
                                        :label="item.userName">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </div>
            <div>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6"  style="text-align: right">
                    <el-button type="primary" @click="reset()">重置</el-button>
                    <el-button type="primary" @click="commit()">提交</el-button>
                </el-col>
            </div>
        </el-form>
    </div>
</template>


<script scoped>
    export default {
        components: {
        },
        data() {
            return {
                transferEntity: {
                    province: null,//省
                    provinceName: null,
                    city: null,//市
                    cityName: null,
                    region: null,//区县
                    regionName: null,
                    street: null,//街道
                    streetName: null,
                    shiftHouseData: null,//转移的房源house_number 逗号隔开
                    newEmpCode: null,//房源接收人
                    hisEmpCode: null,//房源转移人
                    newEmpName: null,//房源接收人
                    hisEmpName: null,//房源转移人
                },
                //所属省份
                itProvinceArrCpy: [],
                //所属城市
                itCityArrCpy: [],
                //所属区域
                itAreaArrCpy: [],
                itStreetArrCpy:[],
                userList: [],//转移人与接收人
            }
        },
        created(){
            this.getEmp() ;
        },
        methods: {
            //改变省
            changeProvCpy(a){

                let obj = {}
                obj = this.itProvinceArrCpy.find((i) =>{
                    return i.areaCode === a;
                })
                let name = obj.name;
                this.transferEntity.provinceName = name;
                //清空code
                this.transferEntity.city = null;
                this.transferEntity.region = null;
                this.transferEntity.cityName = null;
                this.transferEntity.regionName = null;
                this.transferEntity.street = null;
                this.transferEntity.streetName = null;
            },
            //改变市
            changeCityCpy(a){
                let obj = {}
                obj = this.itCityArrCpy.find((i) =>{
                    return i.areaCode === a;
                })
                let name = obj.name;
                this.transferEntity.cityName = name;
                this.transferEntity.region = null;
                this.transferEntity.regionName = null;
                this.transferEntity.street = null;
                this.transferEntity.streetName = null;
            },
            changeCityRegion(a){

                let obj = {}
                obj = this.itAreaArrCpy.find((i) =>{
                    return i.areaCode === a;
                })
                let name = obj.name;
                this.transferEntity.regionName = name;
                this.transferEntity.street = null;
                this.transferEntity.streetName = null;
            },

            changeStreet(a){

                let obj = {}
                obj = this.itStreetArrCpy.find((i) =>{
                    return i.areaCode === a;
                })
                let name = obj.name;
                this.transferEntity.streetName = name;
            },

            /*省份查询*/
            handNeedProvinceCpy(){
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
                            message: '页面:查询城市信息失败!cpyArea/queryNoPage',
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
                            message: '页面:查询区域信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
            /*街道查询*/
            getStreetListCpy(bVal) {
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
            //获取转移人与接收人
            getEmp(){
                var vm = this;
                var obj = {};
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "hkpTransfer/getUser",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.userList = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询街道信息失败!',
                        type: 'error'
                    });
                });
            },
            //选择转移人
            selectHisEmp(val){
                let obj = {}
                obj = this.userList.find((i) =>{
                    return i.userCode === val;
                })
                let userName = obj.userName;
                this.transferEntity.hisEmpName = userName;
            },
            //选择接收人
            selectNewEmp(val){
                let obj = {}
                obj = this.userList.find((i) =>{
                    return i.userCode === val;
                })
                let userName = obj.userName;
                this.transferEntity.newEmpName = userName;
            },
            //提交数据转移
            commit(){
                var vm = this;
                if(!vm.transferEntity.newEmpCode){
                    vm.$notify.warning("接收人是必填的哦");
                    return
                }
                if(!vm.transferEntity.hisEmpCode && !vm.transferEntity.shiftHouseData){
                    vm.$notify.warning("转移人与房源编码必须填一个,详情看注释");
                    return
                }
                if(vm.transferEntity.hisEmpCode == vm.transferEntity.newEmpCode){
                    vm.$notify.warning("转移人跟接收人不能是一个人");
                    return
                }

                var sendObj = {}
                sendObj.entity = vm.transferEntity
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "hkpTransfer/house",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if(response){
                        if(response.data.result){
                            vm.$notify.success(response.data.result);
                        }else {
                            vm.$notify.success("转移成功");
                        }
                    }
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!');
                })
            },
            //重置条件
            reset(){
                this.transferEntity =  {
                    province: null,//省
                        provinceName: null,
                        city: null,//市
                        cityName: null,
                        region: null,//区县
                        regionName: null,
                        street: null,//街道
                        streetName: null,
                        shiftHouseData: null,//转移的房源house_number 逗号隔开
                        newEmpCode: null,//房源接收人
                        hisEmpCode: null,//房源转移人
                        newEmpName: null,//房源接收人
                        hisEmpName: null,//房源转移人
                }
                //所属省份
                this.itProvinceArrCpy = []
                //所属城市
                this.itCityArrCpy = []
                //所属区域
                this.itAreaArrCpy = []
                this.itStreetArrCpy = []
            }
        }

    }
</script>


<style scoped>

</style>