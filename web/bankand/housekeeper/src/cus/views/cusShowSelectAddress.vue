<template>
    <el-dialog  :close-on-click-modal="false"  :before-close="closeDialog" append-to-body :visible.sync="visible" center width="40%">
        <div style="text-align: center;height: 200px;">
            <div style="color: #2aabd2;font-weight: bold;font-size: 20px">分享选址报告</div>
            <div style="margin-top: 50px;font-size: 16px">{{addressUrl}}</div>
            <div style="margin-top: 50px;">
                <el-button type="primary"
                           v-clipboard:copy="addressUrl"
                           v-clipboard:success="onCopy"
                           v-clipboard:error="onError">
                    点击按钮复制选址报告
                </el-button>
            </div>
        </div>
    </el-dialog>
</template>

<script>
    export default {
        data(){
            return{
                visible: false,
                addressUrl: '',
            }
        },
        created(){},
        methods: {
            showAddress(val){
                this.addressUrl = val.addressUrl;
            },
            onCopy(){
                this.$notify.success('复制成功');
                this.$emit("changeShowAddress")
            },
            onError(){
                this.$notify.error('复制失败');
            },
            closeDialog(done){
                this.addressUrl = ''
                done()
            }
        }
    }
</script>

<style scoped>

</style>