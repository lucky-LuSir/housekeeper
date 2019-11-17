<template  slot-scope="scope">
    <div css="root">

        <!-- 订单开票详情 -->
        <el-dialog  :close-on-click-modal="false" class="dialog_search"  :visible.sync="orderInvoiceDetaildialogFormVisible" width="20%" append-to-body>
            <el-form :model="orderInvoiceEntity" label-width="0px" style="text-align: center;">
                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="订单编号" prop="roleName" label-width="80px">
                                <el-input v-model="orderInvoiceEntity.orderCode" :disabled="true"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="开票编号" prop="roleName" label-width="80px">
                                <el-input v-model="orderInvoiceEntity.orderInvoiceCode" :disabled="true"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="发票号"  label-width="80px">
                                <el-input v-model="orderInvoiceEntity.invoiceCode"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="发票总额"  label-width="80px">
                                <el-input v-model="orderInvoiceEntity.invoiceTotalPrice" @blur="calculate()"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="税率"  label-width="80px">
                                <el-select  v-model="orderInvoiceEntity.invoiceTaxRate" filterable placeholder="请选择" @change="calculate()">
                                    <el-option
                                            v-for="item in invoiceTaxRateChannel"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content">
                            <el-form-item label="税额"  label-width="80px">
                                <el-input v-model="orderInvoiceEntity.invoiceTax" :disabled="true"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>


                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="总额(大写)"  label-width="80px">
                                <el-input v-model="orderInvoiceEntity.invoiceUpperCase" :disabled="true"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="grid-content ">
                            <el-form-item label="金额"  label-width="80px">
                                <el-input v-model="orderInvoiceEntity.invoiceUnitAmount" :disabled="true"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="dialogFormVisible = false">取消</el-button>

                <el-button type="primary" @click="updateOrderInvoice()">修改</el-button>
            </div>
        </el-dialog>
       <order-invoice-apply-export  ref="refExport" v-if="invoiceApplyExportOpenOrClose"
                              @changeExport="successExport"></order-invoice-apply-export>
        <div class="top_bar">
            <el-button type="primary" size="mini" plain class="navigate_select_button" >查询</el-button>
            <el-button size="mini" type="success"
                       icon="el-icon-download" @click="openExport()">订单开票申请信息导出
            </el-button>
        </div>
        <!-- 订单开票列表 -->
        <div class="now_page col-sm-12  col-md-12  ">
            <div class="content_bar col-sm-12  col-md-12  ">
                <el-table size="mini" :height="fullHeight"
                          class="now_table"
                          :data="orderInvoiceList"
                          border>
                    <el-table-column
                            label="订单编码"
                            align="center"
                            prop="orderCode">
                    </el-table-column>

                    <el-table-column
                            label="发票号"
                            align="center"
                            prop="invoiceCode">
                    </el-table-column>

                    <el-table-column
                            label="开票日期"
                            align="center">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.invoiceTime | timeDate }}</span>
                        </template>
                    </el-table-column>


                    <el-table-column
                            label="开票总金额"
                            align="center"
                            prop="invoiceTotalPrice">
                    </el-table-column>


                    <el-table-column
                            label="开票总金额(大写)"
                            align="center"
                            prop="invoiceUpperCase">
                    </el-table-column>


                    <el-table-column
                            label="税率"
                            align="center"
                            prop="invoiceTaxRate">
                    </el-table-column>


                    <el-table-column
                            label="税额"
                            align="center"
                            prop="invoiceTax">
                    </el-table-column>

                    <el-table-column  align="center"
                                      fixed="right"
                                      label="操作"
                                      width="100">
                        <template slot-scope="scope">
                            <el-button  v-if = "invoiceUpdateBtn" type="text" size="small"  @click=editOrderInvoice(scope.$index,scope.row)>编辑</el-button>
                        </template>
                    </el-table-column>
                </el-table>



                <div class="page-box" >
                    <el-pagination   background
                                     @size-change="handleSizeChange"
                                     @current-change="handleCurrentChange"
                                     :current-page.sync="currentPage"
                                     :page-size="pageSizeData"
                                     layout="total, prev, pager, next, jumper"
                                     :total="totalCount">
                    </el-pagination >
                </div>
            </div>



        </div>
    </div>
</template>


<script>
    import orderInvoiceApplyExport from "./orderInvoiceApplyExport.vue"
    export default {
        components: {
            orderInvoiceApplyExport,
        },
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 200;
                    that.fullHeight = window.fullHeight
                })()
            }
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
        created: function () {
            var vm = {};
            vm = this;
            vm.invoiceUpdateBtn =  vm.$flagMenuStore.judgeMenu("invoice-update");
            vm.loadOrderInvoice();
        },
        data() {return {
            invoiceUpdateBtn:false,
            fullHeight: document.documentElement.clientHeight - 200,
            orderInvoiceDetaildialogFormVisible:false,
            invoiceApplyExportOpenOrClose: false,//订单开票申请信息导出
            pageSizeData: 20,
            totalCount: 1,
            currentPage: 1,
            orderCode:null,
            orderInvoiceList:[],
            orderInvoiceEntity:{
                //订单编号
                 orderCode:"",
                //开票编号
                orderInvoiceCode:"",
                //发票号
                 invoiceCode:"",
                //金额
                 invoiceUnitAmount:"",
                //税率
                 invoiceTaxRate:"",
                //税额
                 invoiceTax:"",
                //发票总额
                 invoiceTotalPrice:"",
                //总额大写
                 invoiceUpperCase:"",
                //备注
                 remark:"",
                //创建者编码
                 createCode:"",
                //开票日期
                 invoiceTime:"",
                //创建日期
                 createTime:"",
                //最后修改日期
                 lastUpdateTime:"",
            },
            //税率
            invoiceTaxRateChannel:[
                {
                    value: '3',
                    label: '3'
                }, {
                    value: '6',
                    label: '6'
                }
            ]
        }},
        methods: {
            //默认加载方法
            loadOrderInvoice(){
                var vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.pageSize = this.pageSizeData;
                    sendObj.start = 0;
                    sendObj.entity = obj;
                    if(vm.orderCode!=null){
                        sendObj.entity.orderCode = vm.orderCode;
                    }

                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "orderInvoice/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.orderInvoiceList = response.data.result.data;
                        vm.totalCount=response.data.result.totalCount;
                    }).catch(function (error) {

                    });
                }
            },

            //编辑开票信息
            editOrderInvoice(index, rowsRole) {
                this.orderInvoiceDetaildialogFormVisible=true;
                this.orderInvoiceEntity.id=rowsRole.id;
                this.orderInvoiceEntity.orderCode= rowsRole.orderCode;
                this.orderInvoiceEntity.orderInvoiceCode= rowsRole.orderInvoiceCode;
                this.orderInvoiceEntity.invoiceCode= rowsRole.invoiceCode;
                this.orderInvoiceEntity.invoiceTaxRate= rowsRole.invoiceTaxRate;
                this.orderInvoiceEntity.invoiceTax=rowsRole.invoiceTax;
                this.orderInvoiceEntity.invoiceTotalPrice=rowsRole.invoiceTotalPrice;
                this.orderInvoiceEntity.invoiceUpperCase=rowsRole.invoiceUpperCase;
                this.orderInvoiceEntity.invoiceTime=rowsRole.invoiceTime;
                this.orderInvoiceEntity.invoiceUnitAmount=rowsRole.invoiceUnitAmount;
            },

            //修改开票信息
            updateOrderInvoice(){
                    var vm=this;
                    var obj = {};
                    obj = this.orderInvoiceEntity;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "orderInvoice/update",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.orderInvoiceDetaildialogFormVisible =false;
                        vm.loadOrderInvoice();
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/update');
                    });
            },

            //开票金额计算
            calculate(){
                var invoiceTotalPrice=this.orderInvoiceEntity.invoiceTotalPrice;
                var invoiceTaxRate=this.orderInvoiceEntity.invoiceTaxRate;

                var ss  = (Number(invoiceTotalPrice)/Number(1 + Number(invoiceTaxRate)/100)) * (Number(invoiceTaxRate)/100);
                var taxRate=Math.round(ss * 100) / 100;
                this.orderInvoiceEntity.invoiceTax = taxRate;
                this.orderInvoiceEntity.invoiceUpperCase = this.test(invoiceTotalPrice);
                this.orderInvoiceEntity.invoiceUnitAmount = Math.round((invoiceTotalPrice-taxRate)*100)/100
            },

            //阿拉伯数字转大写
            test(n){
                if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
                    return "数据非法";
                var unit = "千百拾亿千百拾万千百拾元角分", str = "";
                n += "00";
                var p = n.indexOf('.');
                if (p >= 0)
                    n = n.substring(0, p) + n.substr(p+1, 2);
                unit = unit.substr(unit.length - n.length);
                for (var i=0; i < n.length; i++)
                    str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
                return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
            },

            // 分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.currentPage = 1;
                this.handleCurrentChange(1);
            },

            // 分页:改变每页数量
            handleCurrentChange(val) {
                let vm = this;

                let sendObj = {};
                sendObj.pageSize = this.pageSizeData;
                sendObj.start = (val - 1) * (sendObj.pageSize);
                sendObj.keyword = this.keyword;
                sendObj.entity = {};
                if(vm.orderCode!=null){
                    sendObj.entity.orderCode = vm.orderCode;
                }
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "orderInvoice/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.orderInvoiceList = response.data.result.data;
                    vm.totalCount=response.data.result.totalCount;

                }).catch(function (error) {
                    //vm.$message.error('页面：获取数据失败！');
                });
            },
            clearInvoiceData(){
               var vm = this;
               vm.orderInvoiceList=[];
            },
            //订单开票申请导出
            openExport(){
                this.invoiceApplyExportOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.refExport.visible = true;
                })
            },
            //导出成功
            successExport(){
                this.invoiceApplyExportOpenOrClose = false;
                this.$refs.refExport.visible = false;
            }
        },

    }
</script>


<style scoped>

    .root {

    }

    .now_page{
    }
    .top_bar{
        margin-left: 50px;
        margin-top: 20px;
        margin-bottom: 20px;
     }
    .content_bar{
        padding:0px 0px 0px 0px;
    }
    .now_table{
        margin:0px 0px 0px 0px;
    }
</style>