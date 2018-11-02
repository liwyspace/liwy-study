<template>
    <section id="template">
        <!-- 面包屑 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>活动管理</el-breadcrumb-item>
            <el-breadcrumb-item>活动列表</el-breadcrumb-item>
            <el-breadcrumb-item>活动详情</el-breadcrumb-item>
        </el-breadcrumb>

        <el-row class="toolbar">
            <el-button type="primary" icon="el-icon-circle-plus" @click="dialogFormVisible = true">添加</el-button>
            <!-- 搜索模块 -->
            <el-form :inline="true" :model="query" class="demo-form-inline" style="float:right;">
                <el-form-item label="审批人">
                    <el-input v-model="query.user" placeholder="审批人"></el-input>
                </el-form-item>
                <el-form-item label="活动区域">
                    <el-select v-model="query.region" placeholder="活动区域">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit" :loading="loading">查询</el-button>
                </el-form-item>
            </el-form>
        </el-row>
        <!-- 表格 -->
        <template>
            <el-table
                :data="tableData3"
                stripe
                v-loading="loading"
                style="width: 100%;">
                <el-table-column
                    type="selection"
                    width="55">
                </el-table-column>
                <el-table-column label="操作" width="150">
                    <template slot-scope="scope">
                        <el-button
                            size="mini">编辑
                        </el-button>
                        <el-button
                            size="mini"
                            type="danger">删除
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column
                    prop="temp_1"
                    label="日期"
                    sortable
                    width="150">
                </el-table-column>
                <el-table-column label="配送信息">
                    <el-table-column
                        prop="temp_2"
                        label="姓名"
                        width="120">
                    </el-table-column>
                    <el-table-column label="地址">
                        <el-table-column
                            prop="temp_3"
                            label="省份"
                            width="120">
                        </el-table-column>
                        <el-table-column
                            prop="temp_4"
                            label="市区"
                            width="120">
                        </el-table-column>
                        <el-table-column
                            prop="temp_5"
                            label="地址">
                        </el-table-column>
                    </el-table-column>
                </el-table-column>
            </el-table>
        </template>
        <!-- 分页 -->
        <el-pagination
            :current-page="currentPage4"
            :page-sizes="[100, 200, 300, 400]"
            :page-size="100"
            layout="total, sizes, prev, pager, next, jumper"
            :total="400">
        </el-pagination>

        <!-- 表单 -->
        <el-dialog title="收货地址" :visible.sync="dialogFormVisible" :append-to-body="true">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="活动名称" prop="name">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="活动区域" prop="region">
                    <el-select v-model="ruleForm.region" placeholder="请选择活动区域">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="活动时间" required>
                    <el-col :span="11">
                        <el-form-item prop="date1">
                            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1"
                                            style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                        <el-form-item prop="date2">
                            <el-time-picker type="fixed-time" placeholder="选择时间" v-model="ruleForm.date2"
                                            style="width: 100%;"></el-time-picker>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="即时配送" prop="delivery">
                    <el-switch v-model="ruleForm.delivery"></el-switch>
                </el-form-item>
                <el-form-item label="活动性质" prop="type">
                    <el-checkbox-group v-model="ruleForm.type">
                        <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
                        <el-checkbox label="地推活动" name="type"></el-checkbox>
                        <el-checkbox label="线下主题活动" name="type"></el-checkbox>
                        <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="特殊资源" prop="resource">
                    <el-radio-group v-model="ruleForm.resource">
                        <el-radio label="线上品牌商赞助"></el-radio>
                        <el-radio label="线下场地免费"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="活动形式" prop="desc">
                    <el-input type="textarea" v-model="ruleForm.desc"></el-input>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
                <el-button @click="dialogFormVisible = false">取 消</el-button>
            </span>
        </el-dialog>
    </section>
</template>

<script>
    import $http from '@/api/http';
    import api from '@/api/api';

    export default {
        name: 'HelloWorld',
        data() {
            return {
                query: {
                    user: '',
                    region: ''
                },
                loading: false,
                tableData3: [],
                currentPage4: 4,
                dialogFormVisible: false,
                ruleForm: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                rules: {
                    name: [
                        {required: true, message: '请输入活动名称', trigger: 'blur'},
                        {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ],
                    region: [
                        {required: true, message: '请选择活动区域', trigger: 'change'}
                    ],
                    date1: [
                        {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
                    ],
                    date2: [
                        {type: 'date', required: true, message: '请选择时间', trigger: 'change'}
                    ],
                    type: [
                        {type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change'}
                    ],
                    resource: [
                        {required: true, message: '请选择活动资源', trigger: 'change'}
                    ],
                    desc: [
                        {required: true, message: '请填写活动形式', trigger: 'blur'}
                    ]
                }
            };
        },
        mounted() {
            this.loadData();
        },
        methods: {
            loadData() {
                let query = Object.assign({}, this.query);
                this.loading = true;
                $http.get(api.templateGetList, query).then(res => {
                    this.tableData3 = res.data;
                    this.loading = false;
                });
            },
            onSubmit() {
                console.log('submit!');
                this.loadData();
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if(valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    };
</script>

<style lang="scss" scoped>
    #template {
        .toolbar {
            margin-top: 20px;
        }
        .el-pagination {
            padding: 20px;
        }
    }
</style>
