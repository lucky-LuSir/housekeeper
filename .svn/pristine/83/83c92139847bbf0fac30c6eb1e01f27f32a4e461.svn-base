<template>
    <div>

        <el-button style="margin-top:10px;margin-left: 20px;margin-right: 10px" type="primary" size="small" plain class="navigate_select_button" icon="el-icon-document" @click="fileOpen">{{title}}</el-button>


        <el-dialog  :close-on-click-modal="false" :visible.sync="isShowFile" width="75%" center top="10vh"  >
                <div class="size-left" style="line-height: 20px">
                    <div class="title-header">
                        <div class="upload">
                            <el-upload
                                    :action=" hkpBaseUrl + 'file/upload' "
                                    :data="param"
                                    :on-success="uploadSuccess"
                                    :on-error="uploadError" style="display: block;margin-top: 0px;">
                                <div class="xpp_up_btn"><el-button type="success" size="small" >点击上传</el-button></div>

                            </el-upload>
                        </div>
                        <div class="upload" >
                            <div class="xpp_create_btn">
                                <el-button type="primary" size="small" @click="creatFolder" >创建文件夹</el-button>
                            </div>
                        </div>
                        <div class="catalog" >
                            <div v-for="item in catalog" style="float: left">
                                <a style="cursor: pointer" @click="selectFolder(item.fileCode)">{{item.fileName}} >&nbsp</a>
                            </div>
                        </div>

                    </div>
                    <el-table
                            ref="multipleTable"
                            :data="files"
                            tooltip-effect="dark"
                            style="width: 100%;cursor: pointer"
                            height="450"
                            @selection-change="handleSelectionChange"
                            @row-click="rowClick"
                            @row-dblclick="dblclick">
                        <el-table-column
                                type="selection"
                                width="45">
                        </el-table-column>
                        <el-table-column
                                label="创建时间"
                                width="180"
                                show-overflow-tooltip>
                            <template slot-scope="scope">{{ scope.row.createTime  | time}}</template>
                        </el-table-column>
                        <el-table-column
                                prop="fileName"
                                label="文件名称"
                                width="160"
                                show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column
                                prop="fileTypeName"
                                label="文件类型"
                                width="80"
                                show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column
                                prop="fileCode"
                                label="文件编码"
                                width="190"
                                show-overflow-tooltip>
                        </el-table-column>
                    </el-table>
                    <div class="block">
                        <el-pagination
                                small
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                :current-page="start"
                                :page-sizes="pageSizes"
                                :page-size="pageSize"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="total">
                        </el-pagination>
                    </div>
                </div>
                <div class="size-right">
                    <div class="title-right">
                        展示栏
                    </div>
                    <div v-if="fileType == 'image'" >
                        <img width="100%" height="100%" style="max-height: 500px"  :src=" hkpBaseUrl + 'file/read/' + fileCode ">
                    </div>
                    <div v-if="fileType == 'video'" >
                        <video  width="100%" height="100%" controls autobuffer>
                            <source :src=" hkpBaseUrl + 'file/read/' + fileCode "/>
                        </video>
                    </div>
                </div>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="isShowFile = false">取 消</el-button>
                    <el-button type="primary" @click="submit">确 定</el-button>
                </span>
        </el-dialog>

    </div>
</template>


<script>
    export default {
        create:function () {
            this.fileOpen();
        },
        data() {
            return {
                /*-----------标签start-----------*/
                start: 1,
                pageSize: 8,
                pageSizes: [8,10,12],
                /*-----------标签end-----------*/
                isShowFile: false,
                total: 0,
                files: [],
                multipleSelection: [],
                fileType: '',
                fileCode:'',
                //上传参数
                param:{
                    parentCode:"0"
                },
                //目录
                catalog:[
                    {
                        fileCode:"0",
                        fileName:"我的文件夹"
                    }
                ]

            }
        },
        props:["title","url"],
        methods: {
            //打开文件管理器
            fileOpen(){
                this.isShowFile = true;
                this.param.parentCode = "0";
                this.start = 1;
                //调用文件查询列表方法
                this.fileQuery();
            },
            //查询列表
            fileQuery(){

                var vm = this;
                var param = {};
                let entity = {};
                entity.parentCode = vm.param.parentCode;
                param.entity = entity;
                param.start = (this.start - 1) * this.pageSize;
                param.pageSize = this.pageSize;
                var options = {
                    method: 'POST',
                    data:   param,
                    url:    "file/data/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.files = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(function (error) {
    /*                    vm.$message({
                            showClose: true,
                            message: '查看文件信息失败',
                            type: 'error'
                        });*/
                });


            },
            //列表选择后调的方法
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            //单击一行,在右侧显示该行文件
            rowClick(row, event, column){

                this.fileType = row.fileType;
                this.fileCode = row.fileCode;

            },
            //双击一行,是文件夹就进入
            dblclick(row, event, column){
                if(row.fileType == "folder"){
                    this.param.parentCode = row.fileCode;
                    //处理目录
                    this.handleCatalog();
                    //刷新列表
                    this.start = 1;
                    this.fileQuery();
                }

            },


            //创建文件夹
            creatFolder(){

                var vm = this;

                this.$prompt('请输入文件夹名称', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputPattern: /[^\\\/\*\?\|\<\>\:]+/,
                    inputErrorMessage: '文件夹名称不正确'
                }).then(({ value }) => {

                    var param = {};
                    var entity = {};
                    entity.fileName = value;
                    entity.parentCode = vm.param.parentCode;
                    param.entity = entity;
                    //父文件夹
                    var options = {
                        method: 'POST',
                        data:   param,
                        url:    "file/data/create",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        if (!response.data.isException) {
                            let parentCode = response.data.result;
                            vm.fileQuery();
                        }
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '查看文件信息失败',
                            type: 'error'
                        });
                    });



                    this.$message({
                        type: 'success',
                        message: '你的文件夹名称是: ' + value
                    });
                }).catch(() => {
                    vm.$notify({
                        type: 'info',
                        message: '取消输入'
                    });
                });

            },
            //点击目录文件夹名
            selectFolder(val){
                this.start = 1;
                //设置parentCode
                this.param.parentCode = val;
                //处理目录
                this.handleCatalog();
                //刷新列表
                this.fileQuery();

            },
            //处理目录
            handleCatalog(){
                var vm = this;
                var param = {};
                let entity = {};
                entity.parentCode = vm.param.parentCode;
                param.entity = entity;
                var options = {
                    method: 'POST',
                    data:   param,
                    url:    "file/data/catalog",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        let result = response.data.result;
                        vm.catalog = result;
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '目录搜索失败',
                        type: 'error'
                    });
                });

            },

            /*---------------------分页start---------------------*/
            handleSizeChange(val) {
                this.pageSize = val;
                this.fileQuery();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.fileQuery();
            },
            /*---------------------分页end----------------------*/
            /*---------------------文件上传start----------------------*/
            uploadSuccess(response, file, fileList){
                this.start = 1;
                this.fileQuery();
             /*   this.$message({
                    showClose: true,
                    message: '添加文件成功',
                    type: 'success'
                });*/

            },
            uploadError(response, file, fileList){
               /* this.$message({
                    showClose: true,
                    message: '添加文件失败',
                    type: 'error'
                });*/

            },
            /*---------------------文件上传end----------------------*/


            //点击确定,将选中的文件url传回父组件
            submit(){
                this.isShowFile = false;
                let list = this.multipleSelection;
                let urls = [];
                for(let i=0; i<list.length; i++){
                    if(list[i].fileType == "folder"){
                        this.$message({
                            showClose: true,
                            message: '不允许选择文件夹',
                            type: 'error'
                        });
                        return;
                    }
                    urls[i] = list[i].fileCode;
                }
                this.test(urls);

            },
            test(urls){
                let vm = this;
                var obj = {};
                var sendObj ={};
                sendObj.fileCode=urls[0];
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: vm.url,
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$notify({
                        message: response.data.message,
                        title: '操作提示',
                    });
                    vm.$emit("refresh");
                }).catch(function (error) {
                    vm.$notify({
                        message: error.data.message,
                        title: '操作提示',
                    });
                });
            }

        }
    }

</script>

<style scoped>
    .size-left{
        height: 550px;
        width: 49%;
        border: 2px solid ;
        float: left;
    }
    .size-right{
        height: 550px;
        width: 49%;
        border: 2px solid ;
        float: left;
    }
    .title-header{
        height: 50px;
        width: 100%;
        background: #E8E8E8;
        float: left;
    }
    .block{
        margin-top: 10px;
        float: right;
    }
    .upload{


        margin:3px 3px 3px 3px;
        /*border-style: solid;
        border-width: 9px 9px 9px 9px;
        border-color: yellow yellow yellow yellow;*/
        width: 280px;
        float: left;
        padding:0px 0px 0px 0px;

    }
    .catalog{
        float: right;
        padding-top: 15px;
        padding-right: 10px;
    }
    .title-right{
        padding-top: 15px;
        padding-left: 10px;
        height: 35px;
        text-align:center;
        background: #E8E8E8;
    }
    .xpp_up_btn{

        margin:0px 30px 90px 0px;
        /*border-style: solid;
        border-width: 1px 1px 1px 1px;
        border-color: #00ff00 #00ff00 #00ff00 #00ff00;*/
        padding:0px 0px 0px 0px;
        width: 160px;
        height: 50px;

    }
    .xpp_create_btn{
        margin:0px 0px 0px 0px;
        /*border-style: solid;
        border-width: 1px 1px 1px 1px;
        border-color: red red red red;*/
        padding:0px 0px 0px 0px;
        width: 100px;
        height: 38px;
    }


</style>
