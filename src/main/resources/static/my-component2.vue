<template>
    <div>
        Hello2 {{who}}
        <h1>test2</h1>
        <el-button type="primary">查询2</el-button>
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