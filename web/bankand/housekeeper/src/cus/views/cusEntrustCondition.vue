<template>
    <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible" style="height: 105%" center
               width="30%" append-to-body>
        <div >
            <el-form :model="customerQuery" ref="customerQuerys"
                     labelWidth="100px">
                <el-row>
                    <span style="font-weight: 900;font-size: 15px;">按条件查询委托</span>
                </el-row>
                <el-row :gutter="5">

                    <el-col >
                        <el-form-item label="委托状态" prop="status">
                            <el-radio-group v-model="customerQuery.status" size="mini">
                                <el-radio-button label="1">待分配</el-radio-button>
                                <el-radio-button label="2">待处理</el-radio-button>
                                <el-radio-button label="3">已转化</el-radio-button>
                                <el-radio-button label="4">已作废</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-form-item label="客户电话" prop="linkmanPhone">
                        <el-input style="width:220px;"
                                  v-model="customerQuery.linkmanPhone"
                                  placeholder="客户手机号码"></el-input>
                    </el-form-item>
                </el-row>

                <el-row>
                    <el-form-item label="服务专员" prop="empName">
                        <el-input style="width:220px;"
                                  v-model="customerQuery.empName"
                                  placeholder="经纪人姓名"></el-input>
                    </el-form-item>
                </el-row>

                <!--<el-row>-->
                    <!--<el-form-item label="创建开始时间" prop="createTimeStart">-->
                        <!--<el-date-picker-->
                                <!--v-model="customerQuery.startTime"-->
                                <!--type="date"-->
                                <!--placeholder="创建开始时间">-->
                        <!--</el-date-picker>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="创建结束时间" prop="createTimeEnd">-->
                        <!--<el-date-picker-->
                                <!--v-model="customerQuery.endTime"-->
                                <!--type="date"-->
                                <!--placeholder="创建结束时间">-->
                        <!--</el-date-picker>-->
                    <!--</el-form-item>-->
                <!--</el-row>-->

                <el-row :gutter="0">
                    <el-col >
                        <el-form-item label="所属省份" prop="province">
                            <el-select id="provinceNameCpy"
                                       v-model="customerQuery.province"
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
                    <el-col >
                        <el-form-item label="所属城市" prop="city">
                            <el-select id="cityNameCpy"
                                       v-model="customerQuery.city"
                                       @focus="getCitieListCpy(customerQuery.province)"
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
                    <el-col >
                        <el-form-item label="所属区域" prop="region">
                            <el-select id="regionNameCpy"
                                       v-model="customerQuery.region"
                                       @focus="getAreaListCpy(customerQuery.city)"
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
                </el-row>
            </el-form>
        </div>
        <div style="text-align: center">
            <el-button size="small" plain type="primary"
                       @click="resetForm('customerQuerys')">重置条件
            </el-button>
            <el-button size="small" plain type="success" @click="getCusByCondition()">按条件查询
            </el-button>
        </div>

    </el-dialog>
</template>

<script>
    import ElCol from "element-ui/packages/col/src/col";
    import ElRow from "element-ui/packages/row/src/row";
    import ElFormItem from "../../../node_modules/element-ui/packages/form/src/form-item";
    export default {
        components: {
            ElFormItem,
            ElRow,
            ElCol
        },
        data() {
            return {
                //所属省份
                itProvinceArrCpy: [],
                //所属城市
                itCityArrCpy: [],
                //所属区域
                itAreaArrCpy: [],
                dialogFormVisible: false,
                deptEntity: [],
                customerQuery: {

                    //下面是委托查询条件
                    status:null, //"状态"   2待处理 3 已转化 4 已作废
                    empCode:null,
                    empName: null,//经纪人name
                    linkmanPhone:null,//联系电话
                    province:null,//省
                    city:null,//市
                    region:null,
                    street:null,
                    startTime:null,
                    endTime:null


                },
            }
        },
        created() {

        },
        mounted(){

        },
        methods: {
            //改变省
            changeProvCpy(){
                //清空code
                this.customerQuery.city = null;
                this.customerQuery.region = null;
            },
            //改变市
            changeCityCpy(){
                this.customerQuery.region = null;
            },
            //改变区域
            changeRegionCpy(){
                //this.customerQuery.street = null;
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
                this.$emit('changeCus', this.customerQuery)
            },
            //重置按条件查询条件
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            //自定义未跟进天数 与枚举
            notFollowupDay(val){
                if (val == "6") {
                    this.notFollowupDayDis = true;
                } else {
                    this.notFollowupDayDis = false;
                    this.searchCusFollowupCondition.customerQuery.customerNotFollowupDay = '';
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
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>