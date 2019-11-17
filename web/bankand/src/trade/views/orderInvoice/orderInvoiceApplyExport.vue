<template>
    <el-dialog  :close-on-click-modal="false" style="height: 105%" top="10px" :before-close="closeDialog" append-to-body
               :visible.sync="visible" width="18%">
        <div>
            <el-form :model="exportEntity">
                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="demonstration">开票日期开始时间</div>
                        <el-date-picker
                                v-model="exportEntity.invoiceTimeStart"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                        <div class="demonstration">开票日期结束时间</div>
                        <el-date-picker
                                v-model="exportEntity.invoiceTimeEnd"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <div style="text-align:center;margin-top: 30px;">
            <el-button @click="cleanEntity()" size="mini" >清空条件</el-button>
            <el-button type="primary" size="mini" @click="invoiceExport()"
                       v-loading.fullscreen.lock="fullscreenLoading">导出
            </el-button>
        </div>
        </el-dialog>
</template>

<script>
    export default {
        data(){
            return {
                visible: false,
                fullscreenLoading: false,
                exportEntity: {
                    invoiceTimeStart: null,//开票日期 开始时间
                    invoiceTimeEnd: null,//开票日期 结束时间
                }
            }
        },
        methods: {
            invoiceExport(){
                var invoiceTimeStart = this.exportEntity.invoiceTimeStart;
                var invoiceTimeEnd = this.exportEntity.invoiceTimeEnd;
                if (invoiceTimeStart <= invoiceTimeEnd) {
                    var url="/housekeeper/orderInvoice/apply/export?invoiceTimeStart=" + invoiceTimeStart + "&invoiceTimeEnd=" + invoiceTimeEnd;
                    //var url="http://localhost:6789/housekeeper/orderInvoice/apply/export?invoiceTimeStart=" + invoiceTimeStart + "&invoiceTimeEnd=" + invoiceTimeEnd
                    window.location.href = url;
                    this.fullscreenLoading = true;
                    setTimeout(() => {
                        this.fullscreenLoading = false;
                    }, 5000);
                    this.$emit("changeExport")
                } else {
                    this.$notify.info("开始时间不要大于结束时间");
                }
            },
            //关闭面板
            closeDialog(done){
                this.cleanEntity()
                done()
            },
            //清空实体
            cleanEntity(){
                this.fullscreenLoading= false,
                    this.exportEntity= {
                        invoiceTimeStart: null,//开票日期 开始时间
                        invoiceTimeEnd: null,//开票日期 结束时间
                }
            }
        }
    }
</script>

<style scoped>

</style>