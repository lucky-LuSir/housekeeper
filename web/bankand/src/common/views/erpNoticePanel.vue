<template slot-scope="scope">
    <el-dialog  :close-on-click-modal="false" id="notice" :visible.sync="visible" fullscreen :style="{height: fullHeight +'px'}" top="30px"
               append-to-body class="notice_dialog">
        <el-carousel :interval="3000" arrow="always" :style="{height: fullHeight -60 +'px'}"
                     class="notice_carousel">
            <el-carousel-item v-for="(item,index) in erpNoticeList" :key="index" class="notice_carousel_item"
                              :style="{height: fullHeight-60 +'px'}">
                <h1 class="notice_h1">{{item.title}}</h1>
                <div class="notice_div">
                    发布时间:{{item.createTime | time}}
                </div>
                <div v-html="item.content" class="notice_div_content"/>
            </el-carousel-item>
        </el-carousel>
    </el-dialog>
</template>

<script scoped>

    export default {
        components: {

        },
        created: function () {
        },
        data() {
            return {

                erpNoticeList: [],
                visible: false,
                fullHeight: document.documentElement.clientHeight,
            }
        },

        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight
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
        methods: {
            erpNotice(){
                var vm = this;
                var sendObj = {};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "erpNoticeApi/queryList",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.erpNoticeList = response.data.result;
                        var userLevel = vm.$cookieStore.getCookie("userLevel");
                        if (userLevel == 'K0') {
                            vm.visible = true;
                        }
                    }
                }).catch(function (error) {

                });
            },
        }
    }
</script>


<style scoped>
    .notice_dialog{
        overflow-y: hidden;
        overflow-x: auto;
    }
    .notice_dialog .el-carousel__arrow{
        height:50px;
        width: 50px;
    }
    .notice_div_content{
        padding: 40px 20px 20px 20px;
        overflow-y: hidden;
        overflow-x: auto;
        line-height: 30px
    }
    .notice_carousel_item{
        overflow-y: auto;
        overflow-x: auto;
    }
    .notice_h1{
        text-align: center;
        font-weight: 900;
    }
    .notice_div{
        color:darkgray;
        width:100%;
        border-top:1px darkgray dashed;
        border-bottom:1px darkgray dashed;
        height: 4%;
    }
</style>
<style>
    #notice .el-dialog__header {
        padding: 20px;
        padding-bottom: 10px;
        background-color: #337ab7;
    }
    #notice .el-dialog__body {
        padding: 10px 10px;
        color: #606266;
        font-size: 14px;
        border: 5px solid #337ab7;
    }
    #notice .el-icon-close:before {
        content: "\E6DB";
        background-color:#EBEEF5;
        border-radius: 8px;
    }
</style>
