<template>
    <el-dialog  :close-on-click-modal="false" style="height: 105%" top="10px" :before-close="closeDialog" append-to-body
               :visible.sync="visible" width="80%">
        <div  class="pre-scrollable">
            <el-form  :model="orderInvoiceApplyEntity" ref="refOrderInvoiceApply" :rules="rules" label-width="130px">
                <el-row style="padding-top: 5px">
                    <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                        <el-form-item label="订单编号：" prop="orderCode">
                            <el-input size="mini"  v-model="orderInvoiceApplyEntity.orderCode" disabled></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col  :xs="6" :sm="6" :md="6" :lg="6" :xl="6" style="text-align: left">
                        <el-form-item label="发票类型：" prop="invoiceType">
                            <el-select size="mini"  @change="changeInvoiceType" v-model="orderInvoiceApplyEntity.invoiceType" placeholder="请选择">
                                <el-option label="专用发票" value="1"></el-option>
                                <el-option label="普通发票" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col  :xs="10" :sm="10" :md="10" :lg="10" :xl="10" style="text-align: center">
                        <span style="font-size: 14px;color: red">注:自动获取公司一定要输入公司全称，如果公司部分信息未获取请自行输入</span>
                    </el-col>
                </el-row>
                <el-card class="box-border" shadow="hover">
                    <span style="padding: 2px 2px 2px 2px;font-weight: bold">
                                公司信息
                            </span>
                    <el-row style="padding-top: 5px">
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="公司全称：" prop="cpyName">
                                <el-input size="mini" placeholder="请输入公司全称" v-model="orderInvoiceApplyEntity.cpyName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: center">
                            <el-button size="mini" type="primary" @click="getCompany()">自动获取公司其他信息</el-button>
                        </el-col>
                    </el-row>
                    <el-row style="padding-top: 5px">
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="纳税人识别号：" prop="creditCode">
                                <el-input size="mini"  v-model="orderInvoiceApplyEntity.creditCode"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="银行支行：" prop="bankBranch">
                                <el-input size="mini"  v-model="orderInvoiceApplyEntity.bankBranch"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="银行账号：" prop="bankCard">
                                <el-input size="mini"  v-model="orderInvoiceApplyEntity.bankCard"></el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row style="padding-top: 5px">
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="地址：" prop="address">
                                <el-input size="mini"  v-model="orderInvoiceApplyEntity.address"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="联系方式：" prop="phone">
                                <el-input size="mini"  v-model="orderInvoiceApplyEntity.phone"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-card>
                <el-card class="box-border" shadow="hover">
                    <span style="padding: 2px 2px 2px 2px;font-weight: bold">
                        金额信息
                    </span>
                    <el-row style="padding-top: 5px">
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="开票总额：" prop="invoiceTotalPrice">
                                <el-input size="mini" @blur="changeMoney" v-model="orderInvoiceApplyEntity.invoiceTotalPrice"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="金额：" prop="invoiceUnitAmount">
                                <el-input size="mini" disabled v-model="orderInvoiceApplyEntity.invoiceUnitAmount"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="税金：" prop="invoiceTax">
                                <el-input size="mini" disabled v-model="orderInvoiceApplyEntity.invoiceTax"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row style="padding-top: 5px">
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="税率：" prop="invoiceTaxRate">
                                <el-select size="mini" @change="changeMoney" v-model="orderInvoiceApplyEntity.invoiceTaxRate" placeholder="请选择">
                                    <el-option label="3" value="3"></el-option>
                                    <el-option label="6" value="6"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="总额大写：" prop="invoiceUpperCase">
                                <el-input size="mini" disabled v-model="orderInvoiceApplyEntity.invoiceUpperCase"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="备注：" prop="invoiceTime">
                                <el-input size="mini"  v-model="orderInvoiceApplyEntity.remark"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-card>
                <el-card class="box-border" shadow="hover">
                    <span style="padding: 2px 64px 2px 2px;font-weight: bold">
                        收件人信息
                    </span>
                    <el-button type="primary" size="mini" @click="getConsignee()">自动获取收件人信息</el-button>
                    <span style="padding-left: 20px;font-size: 15px;color: red">注意：自动获取的收件人信息不一定准确，请自行确认在提交</span>
                    <el-row style="padding-top: 5px">
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="收件人名称：" prop="consigneeName">
                                <el-input size="mini" @blur="changeMoney" v-model="orderInvoiceApplyEntity.consigneeName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="收件人电话：" prop="consigneePhone">
                                <el-input size="mini"  v-model="orderInvoiceApplyEntity.consigneePhone"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                            <el-form-item label="收件人地址：" prop="consigneeAddress">
                                <el-input size="mini"  v-model="orderInvoiceApplyEntity.consigneeAddress"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-card>
            </el-form>
        </div>
        <div style="text-align: center">
            <el-button size="mini" type="primary" @click="cancel()" >取消</el-button>
            <el-button size="mini" type="primary" @click="addInvoice()">提交</el-button>
        </div>
    </el-dialog>
</template>

<script>
    export default {
        data(){
            return {
                visible: false,
                orderInvoiceApplyEntity: {
                    orderCode: '',//订单编码
                    cpyName: '',//公司全称
                    invoiceTotalPrice: '',//开票总价
                    invoiceTaxRate: '3',//税率
                    invoiceTax: '',//税金
                    invoiceUnitAmount: '',//金额
                    invoiceUpperCase: '',//总额大写
                    bankBranch: '',//银行支行信息
                    bankCard: '',//银行账号
                    address: '',//地址
                    creditCode: '',//纳税人识别号
                    phone: '',//联系方式
                    invoiceType: '',//发票类型
                    invoiceTypeName: '',//发票类型name
                    remark: '',//备注
                    consigneePhone: '',//收件人电话
                    consigneeAddress: '',//收件人地址
                    consigneeName: '',//收件人姓名
                },
                orderTotalPrice: '',//订单总额
                rules: {
                    cpyName:[
                        {required: true, message: '公司全称不完善', trigger: 'change'},
                    ],
                    creditCode:[
                        {required: true, message: '纳税人识别号不完善', trigger: 'blur'},
                    ],
                    bankBranch:[
                        {required: true, message: '银行支行不完善', trigger: 'blur'},
                    ],
                    bankCard:[
                        {required: true, message: '银行账号不完善', trigger: 'blur'},
                    ],
                    address:[
                        {required: true, message: '地址不完善', trigger: 'change'},
                    ],
                    phone:[
                        {required: true, message: '联系方式不完善', trigger: 'blur'},
                    ],
                    invoiceTotalPrice:[
                        {required: true, message: '开票总额不完善', trigger: 'blur'},
                    ],
                    invoiceUnitAmount:[
                        {required: true, message: '金额不完善', trigger: 'blur'},
                    ],
                    invoiceTax:[
                        {required: true, message: '税金不完善', trigger: 'blur'},
                    ],
                    invoiceTaxRate:[
                        {required: true, message: '税率不完善', trigger: 'blur'},
                    ],
                    invoiceUpperCase:[
                        {required: true, message: '总额大写不完善', trigger: 'blur'},
                    ],
                    consigneeName:[
                        {required: true, message: '收件人名称', trigger: 'change'},
                    ],
                    consigneePhone:[
                        {required: true, message: '收件人电话', trigger: 'change'},
                    ],
                    consigneeAddress:[
                        {required: true, message: '收件人地址', trigger: 'change'},
                    ],
                    invoiceType:[
                        {required: true, message: '发票类型', trigger: 'change'},
                    ],
                },
            }
        },
        mounted(){

        },
        methods: {
            init(val,val2){
                this.orderInvoiceApplyEntity.orderCode = val;
                this.orderTotalPrice = val2;
            },
            //订单发票类型 改了  必填选项改变
            changeInvoiceType(){
                var vm = this;
                var rules = vm.rules;
                var invoiceType = vm.orderInvoiceApplyEntity.invoiceType;
                var flag = true
                if(invoiceType != null && invoiceType == '2'){
                    flag = false;
                }
                rules.bankBranch[0].required = flag;
                rules.bankCard[0].required = flag;
                rules.address[0].required = flag;
                rules.phone[0].required = flag;
            },
            //改变钱
            changeMoney(){
                var invoiceTotalPrice=this.orderInvoiceApplyEntity.invoiceTotalPrice;
                if(invoiceTotalPrice>this.orderTotalPrice){
                    this.$notify.info("开票总额不能大于订单总额");
                    this.orderInvoiceApplyEntity.invoiceTotalPrice = ''
                    return;
                }
                var invoiceTaxRate=this.orderInvoiceApplyEntity.invoiceTaxRate;

                var ss  = (Number(invoiceTotalPrice)/Number(1 + Number(invoiceTaxRate)/100)) * (Number(invoiceTaxRate)/100);
                var taxRate=Math.round(ss * 100) / 100;
                this.orderInvoiceApplyEntity.invoiceTax = taxRate;
                this.orderInvoiceApplyEntity.invoiceUpperCase = this.test(invoiceTotalPrice);
                this.orderInvoiceApplyEntity.invoiceUnitAmount = Math.round((invoiceTotalPrice-taxRate)*100)/100
            },
            //自动获取公司
            getCompany(){
                var vm = this;
                if(!vm.orderInvoiceApplyEntity.cpyName || vm.orderInvoiceApplyEntity.cpyName.length == 0){
                    vm.$notify.info("公司全称为空，不能获取公司其他信息");
                    return;
                }
                var sendObj = {}
                sendObj.entity = {"cpyName":vm.orderInvoiceApplyEntity.cpyName}
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "orderInvoice/apply/getCompany",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.orderInvoiceApplyEntity.bankBranch = response.data.result.bankBranch//银行支行信息
                    vm.orderInvoiceApplyEntity.bankCard = response.data.result.bankCard//银行账号
                    vm.orderInvoiceApplyEntity.address = response.data.result.address//地址
                    vm.orderInvoiceApplyEntity.creditCode = response.data.result.creditCode//纳税人识别号
                    vm.orderInvoiceApplyEntity.phone = response.data.result.phone//联系方式
                    vm.$notify.success("自动获取公司成功,本部门本月还有"+response.data.result.remark+"次机会");
                }).catch(function (error) {
                    vm.$message.error('自动获取公司失败');
                })

            },
            getConsignee(){
                var vm = this;
                var userCode = vm.$cookieStore.getCookie("userCode");

                var sendObj = {}
                sendObj.entity = {"userCode":userCode}
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "user/detail",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    var depts = response.data.result.deptAreas
                    if(depts){
                        vm.orderInvoiceApplyEntity.consigneeAddress = depts[0].streetName
                    }
                    vm.orderInvoiceApplyEntity.consigneeName = response.data.result.userName;
                    vm.orderInvoiceApplyEntity.consigneePhone = response.data.result.userPhone;

                }).catch(function (error) {
                    vm.$message.error('自动获取收件人失败');
                })
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
            //订单开票申请
            addInvoice(){
                var vm = this;
                this.$refs['refOrderInvoiceApply'].validate((valid) => {
                    if(valid){
                        var sendObj = {}
                        sendObj.entity = this.orderInvoiceApplyEntity
                        var option = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "orderInvoice/apply/create",
                        };
                        this.$ajax(
                            option
                        ).then(function (response) {
                            vm.$emit("changeOrderInvoiceApplyCreate");
                            if(response.data.code == '200'){
                                vm.$notify.success("订单开票成功");
                            }
                        }).catch(function (error) {
                            vm.$message.error('页面:获取数据失败!orderInvoice/apply/create');
                        })
                    }else {
                        return false;
                    }
                })
            },
            //清空数据
            emptyData(){
                this.orderTotalPrice = '',//订单总额
                this.orderInvoiceApplyEntity= {
                    orderCode : '',
                    invoiceTotalPrice: '',
                    invoiceTaxRate: '3',//税率
                    invoiceTax: '',//税金
                    invoiceUnitAmount: '',//金额
                    invoiceUpperCase: '',//总额大写
                    bankBranch: '',//银行支行信息
                    bankCard: '',//银行账号
                    address: '',//地址
                    creditCode: '',//纳税人识别号
                    phone: '',//联系方式
                    consigneePhone: '',//收件人电话
                    consigneeAddress: '',//收件人地址
                    consigneeName: '',//收件人姓名
                }
            },
            //取消开票申请
            cancel(){
                this.$emit("changeOrderInvoiceApplyCreate");
            },
            closeDialog(done){
                this.emptyData()
                done()
            }
        }
    }
</script>

<style scoped>
    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 630px;
        font-size: 12px;
        overflow-y: auto;
        overflow-x: hidden;
    }
    .box-border {
        box-shadow: 0px 1px 8px 0px rgba(63, 63, 64, 0.18);
        margin: 2px 2px;
        padding: 2px 2px;
        border-radius: 10px;
    }
</style>