<template>
    <div>
        Hello {{who}}
        <h1>test</h1>
        <el-button type="primary">查询</el-button>
    </div>

</template>

<script>
    module.exports = {
        data: function () {
            return {
                who: 'world'
            }
        }
    }
</script>

<style>
    .hello {
        background-color: #ffe;
    }
</style>