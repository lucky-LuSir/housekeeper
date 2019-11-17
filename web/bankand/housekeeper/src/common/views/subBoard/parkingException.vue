<template>
    <div class="root"><!--根部分-->

        <div class="rece-cost-exception-div">
            <span class="park-data-title-font">应收停车费用异常看板： {{pkName}}</span>
            <el-button class="edit-button" size="small" type="warning" plain @click="deleteBoard">删除</el-button>
            <el-button class="edit-button" size="small" type="warning" plain @click="editBoard">配置</el-button>

            <div class="rece-cost-exception-table">
                <el-table
                        class="cusListTable"
                        :data="parkingExcObj.parkingExceptionTable"
                        max-height="400"
                        border>
                    <el-table-column
                            prop="companyName"
                            label="客户名称">
                    </el-table-column>
                    <el-table-column
                            prop="customerType"
                            label="类型">

                    </el-table-column>
                    <el-table-column
                            prop="relateName"
                            label="联系人">

                    </el-table-column>
                    <el-table-column
                            prop="relatePhone"
                            label="电话">

                    </el-table-column>
                    <el-table-column
                            label="开始时间">
                        <template slot-scope="scope">{{ scope.row.startTime  | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            label="结束时间">
                        <template slot-scope="scope">{{ scope.row.endTime  | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            prop="receivablePrice"
                            label="停车费">

                    </el-table-column>
                    <el-table-column
                            prop="receivablePrice"
                            label="应收总计">

                    </el-table-column>
                    <el-table-column
                            prop="realPrice"
                            label="实收">

                    </el-table-column>
                </el-table>
            </div>
        </div>
        <div class="page_down">
            <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page.sync="start"
                    :page-size="pageSize"
                    layout="total, prev, pager, next"
                    :total="total">
            </el-pagination>
        </div>

        <!--园区参数弹框-->
        <el-dialog  :close-on-click-modal="false"  :title="dialogTitle" :visible.sync="isShowParkParam" >
            <el-form ref="parkParam" :rules="rules" :model="parkParam" label-width="80px">
                <el-form-item label="入住园区 " prop="pkCode">
                    <el-select
                            v-model="parkParam.pkCode"
                            filterable
                            default-first-option
                            placeholder="输入园区名称或者地址">
                        <el-option
                                v-for="item in pkNames"
                                :key="item.code"
                                :label="item.name"
                                :value="item.code">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="onParkParamSubmit('parkParam')">立即配置</el-button>
                    <el-button @click="isShowParkParam= false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script scoped>
    export default {
        props: ['id','param'],
        components: {
        },
        created:function () {

            if (this.param) {
                this.parkingExcObj.pkCode = this.param;
                //查询园区名
                this.queryParkName(this.parkingExcObj.pkCode);
            } else {
                this.parkingExcObj.pkCode = "";
                this.pkName = "全部园区";
            }
            this.queryList();

        },
        data() {
            return {
                isShowParkParam:false,
                pkNames:[],
                dialogTitle:"",
                parkParam: {
                    pkCode: ''
                },
                parkingExcObj: {
                    pkCode:"",
                    parkingExceptionTable:[{
                        companyName:"",
                        customerType:"",
                        relateName:"",
                        relatePhone:"",
                        startTime:"",
                        endTime:"",
                        receivablePrice:"",
                        realPrice:""
                    }]
                },
                /*-----------标签start-----------*/
                start: 1,
                pageSize: 10,
                total: 0,
                /*-----------标签end-----------*/
                pkName:"",
                rules: {
                    pkCode: [
                        {required: true, message: '园区名称不能为空', trigger: 'blur'},
                    ],
                },

            }
        },
        methods: {
            /*---------------------分页start---------------------*/
            handleCurrentChange(val) {
                this.start = val;
                this.queryList();
            },
            /*---------------------分页end----------------------*/

            //查询园区名
            queryParkName(pkCode){
                let vm = this;
                let param = {};
                param.entity = {};
                param.entity.pkCode = pkCode;

                let option = {
                    method: 'POST',
                    data: param,
                    url: 'hkp/list'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (response.data.result.length > 0){
                        vm.pkName = response.data.result[0].pkName;
                    }
                }).catch(function (error) {
                    vm.$alert('页面:停车费异常看板园区查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            //查询
            queryList() {
                let vm = this;
                let parkParam ={};

                parkParam.start = (vm.start - 1) * vm.pageSize;
                parkParam.pageSize = vm.pageSize;
                parkParam.entity = {};
                parkParam.entity.pkCode = this.parkingExcObj.pkCode;

                let option = {
                    method: 'POST',
                    data: parkParam,
                    url: 'contract/queryParkingCostException'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    let costExcList = [];
                    vm.total = response.data.result.totalCount;
                    costExcList = response.data.result.data;
                    if (costExcList && costExcList.length > 0){
                        let index = 0;
                        let companyName,customerTypeName,relateName,relatePhone;

                        vm.parkingExcObj.pkName = costExcList[0].pkName;
                        let arr=[];
                        for (let i=0;i<costExcList.length;i++) {
                            companyName = costExcList[i].companyName;
                            relateName = costExcList[i].relateName;
                            relatePhone = costExcList[i].relatePhone;
                            if (costExcList[i].customerType === "1") {
                                customerTypeName = "公司";
                            } else if (costExcList[i].customerType === "2") {
                                customerTypeName = "个人";
                                companyName = relateName;
                            }

                            for (let j=0;j<costExcList[i].parkingReceiptList.length;j++) {
                                let paramList = {};
                                paramList.companyName = companyName;
                                paramList.customerTypeName = customerTypeName;
                                paramList.relateName = relateName;
                                paramList.relatePhone = relatePhone;
                                paramList.startTime = costExcList[i].parkingReceiptList[j].startTime;
                                paramList.endTime = costExcList[i].parkingReceiptList[j].endTime;
                                paramList.receivablePrice = costExcList[i].parkingReceiptList[j].receivablePrice;
                                paramList.realPrice = costExcList[i].parkingReceiptList[j].realPrice;

                                arr[index]= paramList;
                                index = index + 1;
                            }
                        }
                        vm.parkingExcObj.parkingExceptionTable = arr;
                    } else {
                        vm.parkingExcObj.parkingExceptionTable = [];
                    }
                }).catch(function (error) {
                    vm.$alert('页面:停车费异常看板信息查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            //通过id删除board
            deleteBoard(){
                this.$confirm('此操作将永久删除该看板, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let param = {};
                    let entity = {
                        id:this.id,
                    };
                    param.entity = entity;

                    let options = {
                        method: 'POST',
                        data: param,
                        url:"board/delete",
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        vm.$message({
                            showClose: true,
                            message: '删除看板成功',
                            type: 'success'
                        });
                        this.$router.replace('/back');
                    }).catch(error => {
                        vm.$alert('页面:删除停车费异常看板失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });



            },

            //配置按钮
            editBoard() {
                this.dialogTitle = "应收停车费费用异常看板参数配置";
                let vm = this;
                vm.isShowParkParam = true;
                //房源搜索栏
                let pkobj = {};
                let options = {
                    method: 'POST',
                    data: pkobj,
                    url:"hkp/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    let result = response.data.result;
                    let pkNameList = [];
                    let pkObj = [];
                    for(let s = 0; s < result.length; s++){
                        let code = result[s].pkCode;
                        let name = result[s].pkName;
                        pkNameList.push(
                            {code:code,name:name}
                        );
                        pkObj[code] = name;
                    }
                    let aObj = {code: "", name: "全部园区"};
                    vm.pkNames = pkNameList;
                    vm.pkNames.unshift(aObj);
                    vm.pkObj = pkObj;
                }).catch(function (error) {
                    vm.$alert('页面:停车费异常看板房源搜索失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
                vm.parkParam.pkCode = vm.parkingExcObj.pkCode;

            },
            onParkParamSubmit(subForm) {
                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        this.isShowParkParam = false;

                        let vm = this;
                        //房源搜索栏
                        let param = {};
                        let entity = {
                            id:this.id,
                            param:this.parkParam.pkCode
                        };
                        param.entity = entity;

                        let options = {
                            method: 'POST',
                            data: param,
                            url:"board/update",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$message({
                                showClose: true,
                                message: '配置看板成功',
                                type: 'success'
                            });
                            vm.$router.replace('/back');
                        }).catch(function (error) {
                            vm.$alert('页面:配置停车费异常看板失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },
        }
    }
</script>
<style >
    .cusListTable.el-table th {
        background-color: #f5f7fa;
    }

</style>

<style scoped>
    .root {
        margin-right: 15px;
        min-width: 1000px;
        height: auto;
        max-height: 480px;
        background-color: white;

        overflow-x: hidden;
    }

    /*-----------------------------------------------------------------------------------*/
    /*公共元素  每个div结束后分隔的时候用*/
    .comm-box-div {
        height: 10px;
        background-color: #F2F2F2
    }

    /*园区去化看板文字*/
    .park-data-title-font {
        font-size: medium;
        color: #FF9900;
        font-weight: bold;
        line-height: 50px;
        margin-left: 15px;
    }

    /*编辑按钮*/
    .edit-button {
        float: right;
        margin-top: 10px;
        margin-right: 20px;
    }

    .ul-park-data li {
        float: left;
        margin-left: 5%;
        color: #919191;
    }


    /*--------------------------------应收费用异常看板 start------------------------------*/

    .rece-cost-exception-table{
        margin-left: 5px;
        width: 99%;
    }

    .page_down{
        float: right;
        margin-right: 40px;
    }
    /*--------------------------------应收费用异常看板 end----------------------------------*/

</style>