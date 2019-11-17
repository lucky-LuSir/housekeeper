<template>
    <el-dialog  :close-on-click-modal="false" :visible.sync="visible" style="height: 105%" top="20px"
               width="78%" :before-close="closeDialog" append-to-body>
         <div class="pre-scrollable-s" style="font-size: 10px">
         <hos-detail ref="hosDetailss"/>
            <div  style="margin-left: 5px;margin-bottom: 10px" class="pagex" v-for="(i,index) in customerFollowupEntity.followupHouseList" :key="index">
                <div>
                    <el-form :model="customerFollowupEntity.followupHouseList[index]"   label-width="0px">
                        <el-button @click="openhosDetail(i)" style="margin-bottom: 10px" size="mini" type="primary">{{i.houseName}}</el-button>
                        <!--<p>标题：{{i.houseName}}</p>-->
                        <p>客户评价：
                                <el-input  size="mini" style="width: 400px" v-model="customerFollowupEntity.followupHouseList[index].remark" placeholder="请输入客户评价"></el-input>
                        </p>
                        <p>添加图片，最多5张：
                            <el-button  size="mini" style="color: #5bc0de"  @click="addImg(index)">添加图片</el-button>
                            <el-button  size="mini" style="color: #d58512" @click="deleteImg(index)">清空图片</el-button>
                        </p>

                        <div v-if="fileType == 'image'" class="img-div1" v-for="(fileItem,fileIndex) in
                            customerFollowupEntity.followupHouseList[index].fileList" :key="fileIndex">
                            <template>
                                <i @click="removeImg(index,fileIndex)" class="el-icon-circle-close" style="float: right;"
                                   v-if="fileItem.fileCode"></i>
                                <img height="100%" width="100%" :src=" hkpBaseUrl + 'file/read/' + fileItem.fileCode"
                                     v-if="fileItem.fileCode">
                                <file  @selectFile="selectImage($event,index,fileIndex)" class="file_button"
                                      v-if="!fileItem.fileCode"></file>
                            </template>
                        </div>
                    </el-form>
                </div>
            </div>

         </div>
        <div>
            我想说：<el-input v-model="customerFollowupEntity.followupContent"  size="mini" style="width: 400px"  placeholder="请输入我想说的内容"></el-input>
            <el-button type="primary" v-if="customerFollowupEntity.cusCode != null" size="mini" @click="commitCreateFollowup()" >提交</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import file from "../../common/components/file.vue";
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    import hosDetail from "../../hos/views/hosDetails.vue"
    export default {
        components:{
            ElButton,
            file,
            hosDetail
        },
        data(){
            return{
                fileType : 'image',
                visible: false,
                cusFollowupTakeLook: [],
                followupContent: '',
                customerFollowupEntity:{
                    followupContent: '',
                    followupType: '1',//带看类型
                    cusCode:null,
                    empCode: '',
                    followupHouseList: [],
                }
            }
        },
        created(){

        },
        methods:{
            //带看类型 创建 客户跟进
            createFollowup(cusCode){
                if(cusCode){
                    this.customerFollowupEntity.cusCode = cusCode;
                    this.customerFollowupEntity.followupHouseList = this.cusFollowupTakeLook
                }else {
                    this.customerFollowupEntity.followupHouseList = this.cusFollowupTakeLook
                    this.customerFollowupEntity.cusCode = cusCode;
                    this.customerFollowupEntity.followupContent = this.followupContent;
                }
            },
            //提交带看类型 创建 客户跟进
            commitCreateFollowup(){
                var vm = this;
                var sendObj = {};
                sendObj.entity = this.customerFollowupEntity;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customerFollowup/create",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.$notify.success("新增跟进成功")
                    vm.successCreateFollowupFromTakeLook();//跟进成功 关闭面板
                }).catch(function (error) {
                    vm.$message.error('页面:!customerFollowup/create');
                })
            },
            successCreateFollowupFromTakeLook(){
                this.customerFollowupEntity.followupContent = ''
                this.customerFollowupEntity.followupHouseList = []
                this.customerFollowupEntity.cusCode=''
                this.$emit('changeSuccess')
            },
            selectImage(urls,ind1,ind2){
                if (urls.length == 1) {
                    this.customerFollowupEntity.followupHouseList[ind1].fileList[ind2].fileCode = urls.toString();
                    this.customerFollowupEntity.followupHouseList[ind1].fileList[ind2].fileType = this.fileType;
                } else {
                    this.$notify({
                        message: '仅支持上传一张照片',
                        title: '操作提示'
                    });
                    return;
                }
            },
            //新增时移除图片
            removeImg(ind1,ind2){
                this.customerFollowupEntity.followupHouseList[ind1].fileList[ind2].fileCode  = '';
                this.customerFollowupEntity.followupHouseList[ind1].fileList[ind2].fileType  = '';
            },
            //关闭面板 清空数据
            closeDialog(done){
                this.customerFollowupEntity.followupContent = null;
                this.customerFollowupEntity.cusCode = null
                this.customerFollowupEntity.empCode = null
                this.customerFollowupEntity.followupHouseList = []
                done()
            },
            //添加图片 最多5张
            addImg(ind){
                if(this.customerFollowupEntity.followupHouseList[ind].fileList.length < 5){
                    this.customerFollowupEntity.followupHouseList[ind].fileList.push({
                        fileCode: '',
                        fileType:this.fileType
                    })
                };

            },
            //删除图片
            deleteImg(ind1){
                this.customerFollowupEntity.followupHouseList[ind1].fileList=[{
                    fileCode: '',
                    fileType:''
                }]
            },
            openhosDetail(i){
                this.$refs.hosDetailss.houseCodeDetails=i.houseCode;
                this.$refs.hosDetailss.getHouseDetails();
            }
        }
    }
</script>

<style scoped>
    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }
    .pre-scrollable-s {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 558px;
        overflow-x: hidden;
        overflow-y: auto;
    }

    .pagex{
        overflow-y: hidden;
        overflow-x: hidden;
        box-shadow: 0px 5px 8px 5px rgba(63, 63, 64, 0.18);
        margin: 5px 5px;
        padding: 20px 10px;
        border-radius: 10px;
    }
    .img-div1 {
        float: left;
        border:1px solid #f2f2f2;
        height: 200px;
        width: 200px;
    }
    .file_button{
        margin-top: 80px;
        margin-left: 50px;
    }
    .box-border {
        box-shadow: 0px 1px 8px 0px rgba(63, 63, 64, 0.18);
        margin: 10px 5px;
        padding: 10px 5px;
        border-radius: 10px;
    }
</style>