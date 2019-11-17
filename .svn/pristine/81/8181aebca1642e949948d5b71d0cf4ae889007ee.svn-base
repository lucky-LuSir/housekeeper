<template>
    <div class="root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row v-bind:style="{width:floatDivWidth+'px'}">
            <div class="navigation_div" v-bind:style="{width:floatDivWidth+'px'}">
                <el-button type="primary" size="small" plain class="navigate_select_button" icon="el-icon-document" @click="exportExcel()"  >
                    导出
                </el-button>
                <up-file style="float:left;"
                         v-show="portUserTitleBtn"
                         :title="portUserTitle"
                         :url="portUserUrl"
                         v-on:refresh="findReLoadUser()"/>
                <el-input size="small"
                          @keyup.enter.native="searchEnterFun"
                          class="navigation_button_input"
                          v-model="keyword"
                          style="margin-left: 20px;margin-top: 10px;width: 300px;"
                          placeholder="请输入端口小号查询">
                    <el-button slot="append" icon="el-icon-search" @click="findReLoadUser"></el-button>
                </el-input>
            </div>
        </el-row>




        <!--第1个方框 一行搜索-->
        <div class="block_box1"/>
        <!--第1个方框(end)-->
        <!--第2个方框 一行显示搜索条数-->
        <div class="block_box2"/>
        <!--第2个方框(end)-->

        <!--第3个方框 一行树状表格-->
        <!--用户信息列表-->
        <div class="port_user_table" v-loading.fullscreen.lock="loading"
             element-loading-text="拼命加载中">
            <el-table
                    id="portUserList"
                    :data="portUserList"
                    :highlight-current-row="true"
                    style="width: 100%;text-align: center;"
                    :show-header="true"
                    :border="true"
                    :height="fullHeight"
                    max-height="735"
                    >
       <!--         <el-table-column align="center"
                                 label="id"
                                 width="100">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.id }}</span>
                    </template>
                </el-table-column>-->
                <el-table-column align="center"
                                 label="展示号码"
                                 width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.showPhone }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                        label="端口对应分部"
                        width="150">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.portDeptName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 label="大区"
                                 width="150">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.regionalName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="端口使用人" width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.portUserName }}</span>
                    </template>
                </el-table-column>
  <!--              <el-table-column align="center" label="创建时间" width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.createTime | time }}</span>
                    </template>
                </el-table-column>-->
            </el-table>
            <template>
                <div class="page-box">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page.sync="start"
                            :page-sizes="pageSizes"
                            :page-size="pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total"
                            >
                    </el-pagination>
                </div>
            </template>
        </div>
        <!--第3个方框(end)-->

        <div class="block_box4"></div>
        <!--第4个方框(end)-->


    </div><!--根部分(end)-->
</template>


<script scoped>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    import FileSaver from 'file-saver';
    import XLSX from 'xlsx';
    import upFile from "../../../common/components/portUserFile.vue";

    export default {
        components: {
            ElInput,
            ElButton,
            upFile,
        },
        created: function () {
            var vm = {};
            vm = this;
            vm.basicReLoadUser();
        },
        updated() {
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 205
                    that.fullHeight = window.fullHeight
                })()
            }
        },
        watch: {
            fullHeight (val) {
                if(!this.timer) {
                    this.fullHeight = val
                    this.timer = true
                    let that = this
                    setTimeout(function (){
                        that.timer = false
                    },400)
                }
            }
        },
        data() {
            return {
                portUserTitleBtn: true,
                portUserTitle: "端口人员数据上传",
                portUserUrl: "portUser/uploadPortUser",
                fullHeight: document.documentElement.clientHeight - 205,
                loading:true,
                start: 1,
                pageSize: 50,
                total: 0,
                pageSizes: [50],
                floatDivWidth: "",
                keyword:'',
                //上传类型
                fileType: "image",
                /*用户列表*/
                portUserList: [
                    {
                        showPhone: '',
                        portDeptName: '',
                        regionalName: '',
                        portUserName: '',
                        createTime:'',
                    },
                ],
            }
        },
        methods: {
            exportExcel(){
                /* 1创建 workbook 对象 from table */
                //alert("导出开始");
                var wb = XLSX.utils.table_to_book(document.querySelector('#portUserList'))
                /* 2获得 binary string as output */
                var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
                try {
                    FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '端口人员信息.xlsx')
                } catch (e) { if (typeof console !== 'undefined') console.log(e, wbout); console.log("导出excel捕获的异常ttt");}
                return wbout

            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.basicReLoadUser();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.basicReLoadUser();
            },
            basicReLoadUser() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = (vm.start - 1) * vm.pageSize;
                    sendObj.pageSize = vm.pageSize;
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "portUser/portUserQuery",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.loading = false;
                        vm.portUserList = response.data.result.data;
                        vm.total=response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败! portUser/portUserQuery');
                    });
                }
            },

            searchEnterFun: function (e) {
                var vm = this;
                var keyCode = window.event ? e.keyCode : e.which;
                if (keyCode == 13) {
                    vm.findReLoadUser();
                }
            },

            findReLoadUser() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.start = 0;
                    sendObj.pageSize = 50;
                    sendObj.entity = obj;
                    sendObj.keyword=this.keyword;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "portUser/portUserQuery",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.portUserList = response.data.result.data;
                    }).catch(()=> {
                        vm.$message.error('页面:获取数据失败! portUser/portUserQuery');
                    });
                }
            },
        }
    }

</script>

<style scoped>
    .root {
        min-width: 1000px;
    }
    /*导航栏DIV*/
    .navigation_div {
        width: 100%;
        height: 50px;
        min-width: 1000px;
    }
    .navigation_button_input {
        margin-left: 20px;
    }
    .port_user_table {
        min-width: 1000px;
        background-color: white;
        min-height: 60px;
        line-height: 40px;
    }
    .page-box {
        height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }
</style>
