<template>
    <div id="app">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="邮政编码">
                <el-input v-model="formInline.postcode" placeholder="邮政编码"></el-input>
            </el-form-item>
            <el-form-item label="国家">
                <el-select v-model="formInline.country" placeholder="国家">

                    <!--       <el-option label="区域一" value="shanghai"></el-option>
                           <el-option label="区域二" value="beijing"></el-option>-->
                    <el-option v-for="item in countries" :label="item.cn" :value="item.en"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
        </el-form>
        <el-table
                :data="tableData"
                stripe
                style="width: 100%">
            <el-table-column
                    prop="cn"
                    label="国家"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="state"
                    label="州"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="county"
                    label="郡">
            </el-table-column>
            <el-table-column
                    prop="city"
                    label="市">
            </el-table-column>
            <el-table-column
                    prop="community"
                    label="地区">
            </el-table-column>
        </el-table>
        <!--分页组件-->
        <page-helper :current-page="page" :total="total" @sizeChange="handleSizeChange"
                     @currentChange="handleCurrentChange"></page-helper>
    </div>

</template>

<script>
    // import myComponent from './my-component.vue';
    // import pageHelper from './page-helper.vue';
    let myComponent = httpVueLoader('./my-component.vue');
    let pageHelper = httpVueLoader('./page-helper.vue');
    module.exports = {
        components: {
            'page-helper': pageHelper,
            'my-component': myComponent
        },
        data: function () {
            return {
                formInline: {
                    openid: "",
                    name: "",
                    level: "",
                    beginTime: "",
                    endTime: ""
                },
                //分页
                page: 1,
                size: 10,
                total: 0,
                tableData: []
            }
        },
        methods: {
            handleSizeChange(size) {
                this.size = size;
                this.getData(this.page, size);
            },
            handleCurrentChange(page) {
                this.page = page;
                this.getData(page, this.size);
            },
            //获取列表数据
            getData: function (page, size) {
                axios.get(API_PATH + '/saleman/salemanList', {
                    params: {
                        page: page,
                        size: size,
                        openid: this.formInline.openid,
                        name: this.formInline.name,
                        beginTime: this.formInline.beginTime,
                        endTime: this.formInline.endTime,
                        level: this.formInline.level == 0 ? null : this.formInline.level
                    }
                }).then(resp => {
                    if (resp.data.code == 200) {
                        console.log(resp);
                        this.tableData = resp.data.data.list;
                        this.total = resp.data.data.total;
                    } else {

                    }
                }).catch(err => {

                })
            },
        },
        mounted() {
            this.getData(this.page, this.size);
        }

    }
</script>

<style>
    .hello {
        background-color: #ffe;
    }
</style>