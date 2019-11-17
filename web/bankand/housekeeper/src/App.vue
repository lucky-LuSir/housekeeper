<template>
    <div id="app">


        <router-view></router-view>
        <!--<button>这里是app.vue 主根页</button>-->
    </div>
</template>

<script>

    export default {
        name: 'app',
        components: {}
    }

</script>

<style>
    .el-dialog__headerbtn {
        position: absolute;
        top: 0px;
        right: 10px;
        padding: 0;
        background: 0 0;
        border: none;
        outline: 0;
        cursor: pointer;
        font-size: 16px;
    }

    .el-button.is-circle {
        border-radius: 100%;
        padding: 6px;
    }

    .aside {
        text-align: left;
        max-height: 500px;
        overflow-y: auto;
    }

    .el-dialog__body {
        padding: 10px 10px;
        color: #606266;
        font-size: 14px;
    }

    .el-table td, .el-table th {
        padding: 0;
        min-width: 0;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        text-overflow: ellipsis;
        vertical-align: middle;
        position: relative;
        text-align: left;
    }

    .el-button--mini, .el-button--mini.is-round {
        padding: 5px 8px;
    }

    ::-webkit-scrollbar {
        width: 10px;
        height: 10px;
    }

    ::-webkit-scrollbar-thumb {
        background-color: #a1a3a9;
        border-radius: 3px;
    }
    .ant-menu-inline .ant-menu-submenu-title {
        font-size: 13px;
    }

    .ant-menu-vertical .ant-menu-item, .ant-menu-inline .ant-menu-item {
        font-size: 13px;
        margin-left: 7px;
    }
</style>
