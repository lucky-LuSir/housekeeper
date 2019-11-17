<template>
    <div class="root" ref="myRoot">



    <el-form>
        <el-row :gutter="5">
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                <el-form-item label="选择类型">
            <el-select  v-model="announcementType" filterable placeholder="请选择">
                <el-option
                        v-for="item in announcementType2"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
                </el-form-item>
            </el-col>
        </el-row>
         <el-row :gutter="5">
             <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                 <el-form-item label="选择部门">
                     <el-select v-model="deptCodes[0]" filterable placeholder="请选择">
                         <el-option
                                 v-for="item in deptEntity"
                                 :key="item.deptCode"
                                 :label="item.deptName"
                                 :value="item.deptCode">
                             <span style="float: left">{{ item.deptName }}</span>
                             <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode }}</span>
                         </el-option>
                     </el-select>
                 </el-form-item>
             </el-col>
             <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                 <el-form-item label="选择部门">
                     <el-select v-model="deptCodes[1]" filterable placeholder="请选择">
                         <el-option
                                 v-for="item in deptEntity"
                                 :key="item.deptCode"
                                 :label="item.deptName"
                                 :value="item.deptCode">
                             <span style="float: left">{{ item.deptName }}</span>
                             <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode }}</span>
                         </el-option>
                     </el-select>
                 </el-form-item>
             </el-col>
             <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                 <el-form-item label="选择部门">
                     <el-select v-model="deptCodes[2]" filterable placeholder="请选择">
                         <el-option
                                 v-for="item in deptEntity"
                                 :key="item.deptCode"
                                 :label="item.deptName"
                                 :value="item.deptCode">
                             <span style="float: left">{{ item.deptName }}</span>
                             <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode }}</span>
                         </el-option>
                     </el-select>
                 </el-form-item>
             </el-col>
             <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                 <el-form-item label="选择部门">
                     <el-select v-model="deptCodes[3]" filterable placeholder="请选择">
                         <el-option
                                 v-for="item in deptEntity"
                                 :key="item.deptCode"
                                 :label="item.deptName"
                                 :value="item.deptCode">
                             <span style="float: left">{{ item.deptName }}</span>
                             <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode }}</span>
                         </el-option>
                     </el-select>
                 </el-form-item>
             </el-col>
         </el-row>
        <el-row>
            <el-button @click="cleanSerchOrder()">清空条件</el-button>
            <el-button type="primary" @click="serchOrderClick()">按照条件查询</el-button>
        </el-row>
    </el-form>
    </div>
</template>


<script scoped>
    import ElForm from "../../../../node_modules/element-ui/packages/form/src/form";
    import ElRow from "element-ui/packages/row/src/row";

    export default {
        components: {
            ElRow,
            ElForm},
        created:function () {
            var vm=this;
            this.loadDeptList();
        },
        data(){
            return{
                    deptCodes:[],
                    announcementType:"",
                    announcementType2: [{
                    value: '1',
                    label: '业绩光荣榜'
                }, {
                    value: '2',
                    label: '业绩落后榜'
                }, {
                    value: '3',
                    label: '服务品质最好'
                }, {
                    value: '4',
                    label: '服务品质最差'
                }, {
                    value: '5',
                    label: '标准落地最好'
                }, {
                        value: '6',
                        label: '标准落地最差'
                    }],
                deptEntity:[]
                }
            },

        methods: {
            serchOrderClick(){
                var obj =this.deptCodes;
                var announcementType =this.announcementType;
                this.createAnnouncement(obj,announcementType);
            },
            //加载部门数据
            loadDeptList(){
                var vm = this;
                var obj = {};
                var sendObj = {};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "dept/findDeptName",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {

                    }
                    vm.deptEntity = response.data.result;
                }).catch(function (error) {

                });
            },
            createAnnouncement(data,announcementType){
                if(announcementType==null||announcementType==""){
                    alert("请先选择类型");
                }else if(data.length==0){
                    alert("部门至少填写一个");
                }else{
                    var vm=this;
                    var obj = {};
                    if(data!=null){
                        obj=data;
                    }
                    var sendObj = {};
                    sendObj.deptCodes = obj;
                    sendObj.announcementType = announcementType;
                    var option = {
                        method: 'POST',
                        data: sendObj,
                        url: "announcement/create ",
                    };
                    this.$ajax(
                        option
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                    }).catch(function (error) {

                    });
                }

            }
        }
    }//end export default

</script>

<style scoped>
</style>