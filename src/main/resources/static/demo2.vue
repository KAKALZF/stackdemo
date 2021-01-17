<template>
    <div>
        Hello {{who}}
        <h1>test</h1>
        <el-button type="primary">查询</el-button>
        <my-component2></my-component2>
    </div>


</template>

<script>
    import myComponent2 from './my-component2.vue';

    export default {
        data() {
            return {
                who: 'world2'
            }
        },
        components: {
            'my-component2': myComponent2
        }
    }
</script>

<style>
    .hello {
        background-color: #ffe;
    }
</style>