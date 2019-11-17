<template slot-scope="scope">
    <div css="root">
     <!--回款详情面板-->
     <el-dialog  :close-on-click-modal="false" class="dialog_search"  :visible.sync="orderPaybackEntityDialogFormVisible" width="20%"  append-to-body>
         <el-form :model="orderPaybackEntity" ref="ruleFormRef" label-width="0px" style="text-align: left;">
             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="订单编号" prop="roleName" label-width="80px">
                             <el-input size="mini" v-model="orderPaybackEntity.orderCode" ></el-input>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>

             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="回款金额"  label-width="80px">
                             <el-input size="mini" v-model="orderPaybackEntity.payment"></el-input>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>
             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="汇款描述" prop="roleName" label-width="80px">
                             <el-input size="mini" v-model="orderPaybackEntity.description" ></el-input>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>
             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="回款说明" prop="roleName" label-width="80px">
                             <el-input size="mini" v-model="orderPaybackEntity.remark" ></el-input>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>

             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="回款时间"  label-width="80px">
                             <el-date-picker
                                     size="mini"
                                     v-model="orderPaybackEntity.backTime"
                                     type="date"
                                     placeholder="选择日期"
                                     class="order_time">
                             </el-date-picker>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>

             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="回款渠道"  label-width="80px">
                             <el-select size="mini"  v-model="orderPaybackEntity.channel" filterable placeholder="请选择">
                                 <el-option
                                         v-for="item in optionsChannel"
                                         :key="item.value"
                                         :label="item.label"
                                         :value="item.value">
                                 </el-option>
                             </el-select>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>
         </el-form>
         <div style="text-align:center;line-height: 10px;">
             <el-button size="small" @click="orderPaybackEntityDialogFormVisible = false">取消</el-button>

             <el-button type="primary" size="small" @click="updateOrderPayback('ruleFormRef')">修改</el-button>
         </div>
    </el-dialog>

     <!--回款新增面板-->
     <el-dialog  :close-on-click-modal="false" class="dialog_search"  :visible.sync="orderPaybackCreateDialogFormVisible" width="20%" append-to-body>
         <el-form :model="orderPaybackEntity" :rules="rules" ref="ruleFormRef" label-width="0px" style="text-align: center;">
             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="订单编号" prop="orderCode" label-width="80px">
                             <el-input v-model="orderPaybackEntity.orderCode" disabled ></el-input>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>

             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="回款金额" prop="payment" label-width="80px">
                             <el-input v-model="orderPaybackEntity.payment"></el-input>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>
             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="汇款描述" prop="description" label-width="80px">
                             <el-input v-model="orderPaybackEntity.description" ></el-input>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>
             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="回款说明" prop="remark" label-width="80px">
                             <el-input v-model="orderPaybackEntity.remark" ></el-input>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>

             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="回款时间" prop="backTime" label-width="80px">
                             <el-date-picker
                                     size="small"
                                     v-model="orderPaybackEntity.backTime"
                                     type="date"
                                     placeholder="选择日期"
                                     class="order_time">
                             </el-date-picker>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>

             <el-row :gutter="5">
                 <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                     <div class="grid-content ">
                         <el-form-item label="回款渠道" prop="channel"   label-width="80px">
                             <el-select  v-model="orderPaybackEntity.channel" filterable placeholder="请选择">
                                 <el-option
                                         v-for="item in optionsChannel"
                                         :key="item.value"
                                         :label="item.label"
                                         :value="item.value">
                                 </el-option>
                             </el-select>
                         </el-form-item>
                     </div>
                 </el-col>
             </el-row>
         </el-form>
         <div style="text-align:center;line-height: 10px;">
             <el-button @click="orderPaybackCreateDialogFormVisible = false">取消</el-button>
             <el-button type="primary" @click="createOrderPayback('ruleFormRef')"
                        :disabled="isDisable">保存</el-button>
         </div>
    </el-dialog>


    <!-- 回款列表 -->
        <div class="now_page" >
            <div class="top_bar ">
                <el-button type="success" size="mini" plain class="navigate_select_button" >查询</el-button>
                <el-button v-if="orderPaybackCreateBtn" type="success" size="mini" plain class="navigate_select_button" @click="handleCreatRole()" >新增</el-button>
                <span style="float:right">
                    <span> 回款总额：<input type="text" size="15" v-model="sumPayment"
                                       style="border-color: #878787;
                                    border-style: solid;border-top-width: 0px;border-right-width: 0px;
                                    border-bottom-width: 1px;border-left-width: 0px">元&nbsp;&nbsp;
                    </span>
                    <el-date-picker
                            v-model="month"
                            type="month"
                            placeholder="选择月"
                            v-on="createSumPayment()">
                    </el-date-picker>
                </span>
            </div>
            <div class="content_bar">
                <el-table size="mini"
                          :height="fullHeight"
                          class="now_table"
                          :data="orderPaybackList"
                          border>
                    <el-table-column
                            align="center"
                            label="序号"
                            type="index"
                            width="60px">
                    </el-table-column>
                    <el-table-column
                            label="回款编码"
                            align="center"
                            width="230px"
                            prop="payBackOrderCode">
                    </el-table-column>
                    <el-table-column
                            label="订单编码"
                            align="center"
                            width="180px"
                            prop="orderCode">
                    </el-table-column>
                    <el-table-column
                            label="回款金额"
                            align="center"
                            width="120px"
                            prop="payment">
                    </el-table-column>
                    <el-table-column
                            label="回款描述"
                            align="center"
                            width="140px"
                            prop="description">
                    </el-table-column>
                    <el-table-column
                            label="回款说明"
                            align="center"
                            width="150px"
                            prop="remark">
                    </el-table-column>
                    <el-table-column
                            label="回款渠道"
                            align="center"
                            width="100px"
                            prop="channel">
                    </el-table-column>
                    <el-table-column
                            label="创建人"
                            align="center"
                            width="120px"
                            prop="createName">
                    </el-table-column>
                    <el-table-column
                            label="回款时间"
                            width="160px"
                            align="center">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.backTime | timeDate }}</span>
                        </template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      fixed="left"
                                      label="操作"
                                      width="60">
                        <template slot-scope="scope">
                            <el-button v-if="orderPaybackUpdateBtn" type="text" size="small"  @click=handleEditRole(scope.$index,scope.row)>编辑</el-button>
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


<script scoped>
    export default {
        components: {
        },
        created: function () {
            var vm = {};
            vm = this;
            vm.orderPaybackCreateBtn =  vm.$flagMenuStore.judgeMenu("order-payback-create");
            vm.orderPaybackUpdateBtn =  vm.$flagMenuStore.judgeMenu("order-payback-update");
            vm.basicReLoadOrderfollow();
        },
        data() {

            //校验数字只能为整数
            var checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('金额只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {
                orderPaybackCreateBtn:false,
                orderPaybackUpdateBtn:false,
                sumPayment:0,
                month: new Date(),
                fullHeight: document.documentElement.clientHeight  - 185,
                //回款详情面板Dialog
                orderPaybackEntityDialogFormVisible:false,
                //回款新增面板Dialog
                orderPaybackCreateDialogFormVisible:false,
                isDisable: false,
                pageSizeData: 30,
                totalCount: 1,
                currentPage: 1,
                orderCodeThis:null,
                //列表
                orderPaybackList:[],
                //回款实体
                orderPaybackEntity:{
                id:"",
                //订单编号
                orderCode:"",
                payBackOrderCode:"",
                //回款金额
                payment:"",
                createName:"",

                //回款时间
                backTime:"",
                remark:"",
                description:"",
                //回款渠道
                channel:"",
            },
            //回款渠道
            optionsChannel:[{
                value: '银行',
                label: '银行'
            }, {
                value: '支付宝',
                label: '支付宝'

            }, {
                value: '微信',
                label: '微信'

            }, {
                value: '现金',
                label: '现金'
            }],

            // 验证
            rules: {
                orderCode: [
                    {required: true, message: '请填写订单编号', trigger: 'blur'},
                ],
                payment: [
                    {required: true, message: '请填写回款金额', trigger: 'blur'},
                    {validator: checkInteger},
                ],
                description: [
                    {required: true, message: '请填写回款描述', trigger: 'blur'},
                ],
                remark: [
                    {required: true, message: '请填写回款说明', trigger: 'blur'},
                ],
                backTime: [
                    {required: true, message: '请选择回款时间', trigger: 'blur'},
                ],
                channel: [
                    {required: true, message: '请选择回款渠道', trigger: 'blur'},
                ],
            },
        }},
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 185;
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
            },

        },

        methods: {
            clearPayBackData(){
                var vm = this;
                vm.orderPaybackList=[];
                vm.orderPaybackEntity={};
                vm.orderCodeThis=null;
            },
            //默认加载
            basicReLoadOrderfollow(){
                var vm = {};
                vm = this;
                var flag = true;

                if (flag) {        //flag留后面表单验证
                    var obj = {};

                    var sendObj = {};
                    sendObj.pageSize = this.pageSizeData;
                    sendObj.start = 0;
                    sendObj.entity = obj;
                    if(this.orderCodeThis!=null){
                        sendObj.entity.orderCode = this.orderCodeThis;
                    }
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "orderPayback/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.orderPaybackList = response.data.result.data;
                        vm.totalCount=response.data.result.totalCount;
                    }).catch(function (error) {
                        //vm.$message.error('页面:获取数据失败!post/query');
                        alert("页面:获取数据失败!orderfollowup/query");
                    });
                }
            },

            createSumPayment(){
                var vm = {};
                vm = this;

                var obj = {};

                var sendObj = {};
                sendObj.entity = obj;
                sendObj.entity.backTime = this.month;
                if(this.orderCodeThis!=null){
                    sendObj.entity.orderCode = this.orderCodeThis;
                }
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "orderPayback/selectPayBackSum",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.sumPayment= response.data.result.payment;
                }).catch(function (error) {
                    alert("回款总额统计失败");
                });

            },
            //修改赋值
            handleEditRole(index, rowsRole) {
                this.orderPaybackEntity.id=rowsRole.id;
                this.orderPaybackEntity.payBackOrderCode=rowsRole.payBackOrderCode;
                this.orderPaybackEntity.orderCode= rowsRole.orderCode;
                this.orderPaybackEntity.description= rowsRole.description;
                this.orderPaybackEntity.remark= rowsRole.remark;
                this.orderPaybackEntity.payment= rowsRole.payment;
                this.orderPaybackEntity.backTime= rowsRole.backTime;
                this.orderPaybackEntity.channel=rowsRole.channel;
                this.orderPaybackEntityDialogFormVisible = true//打开对话框
            },
            //新增赋值
            handleCreatRole() {
                this.orderPaybackEntity.orderCode= this.orderCodeThis;
                this.orderPaybackCreateDialogFormVisible = true//打开对话框
            },

            // 新增回款信息
            createOrderPayback(ruleFormVal){
                var vm =this;
                this.isDisable = true;
                //表单验证 start
                this.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
                        var obj = {};
                        obj = this.orderPaybackEntity;
                        var sendObj = {};
                        sendObj.entity = obj;
                        var options = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "orderPayback/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$notify({
                                message: response.data.message,
                                title: '操作提示',
                            });
                            vm.orderPaybackCreateDialogFormVisible =false;
                            vm.basicReLoadOrderfollow();
                        }).catch(function (error) {
                            vm.$message.error('页面:获取数据失败!role/update');
                        });
                    } else {
                        vm.$message.error('填写不规范,请检查!');
                        return false;
                    }
                });
                setTimeout(() => {
                    this.isDisable = false;
                }, 2000);
            },

            //修改回款信息
            updateOrderPayback(ruleFormVal){
                var vm =this;
                var flag = false;
                this.isDisable = true;
                //表单验证 start
                this.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
//                        alert('submit!');
                        flag = true;
                    } else {
                        vm.$message.error('填写不规范,请检查!');
                        return false;
                    }
                });
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    obj = this.orderPaybackEntity;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "orderPayback/update",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.orderPaybackEntityDialogFormVisible =false;
                        vm.basicReLoadOrderfollow();
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!role/update');
                    });
                }
                setTimeout(() => {
                    this.isDisable = false;
                }, 2000);
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
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "orderPayback/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.orderPaybackList = response.data.result.data;
                    vm.totalCount=response.data.result.totalCount;

                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },
        },

    }
</script>


<style scoped>
    .root {
        background-color: white;
    }

    .now_page{
        background-color: white;
    }
    .top_bar{
        margin: 10px 30px;
     }
    .content_bar{
        padding:0px 0px 0px 0px;
    }
    .now_table{
        margin:0px 0px 0px 0px;
    }
    .grid-content {
        width: 100%;
        text-align: center;
    }
    .order_time{
        width: 100%;
    }
    .page-box {
        height: 40px;
        padding-top: 0px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        width: 100%;
        z-index: 200;
    }
</style>