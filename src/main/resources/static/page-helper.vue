<template>
    <div class="block">
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[2,10, 20, 50, 100]"
                :page-size="10"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
    </div>
</template>

<script>
    module.exports = {
        // name: "pageHelper",
        props: ['currentPage', 'total'],
        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.$emit("sizeChange", val);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.$emit("currentChange", val);
            }
        }
    }
</script>

<!--<style scoped>

</style>-->
