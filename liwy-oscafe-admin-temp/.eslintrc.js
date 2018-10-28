// https://eslint.org/docs/user-guide/configuring

module.exports = {
    root: true,
    parserOptions: {
        parser: 'babel-eslint'
    },
    env: {
        browser: true,
        jquery: true
    },
    extends: [
        // https://github.com/vuejs/eslint-plugin-vue#priority-a-essential-error-prevention
        // consider switching to `plugin:vue/strongly-recommended` or `plugin:vue/recommended` for stricter rules.
        'plugin:vue/essential',
        // https://github.com/standard/standard/blob/master/docs/RULES-en.md
        'standard'
    ],
    // required to lint *.vue files
    plugins: [
        'vue'
    ],
    // add your custom rules here
    rules: {
        // allow async-await
        'generator-star-spacing': 'off',
        // allow debugger during development
        'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',

        'indent': ['warn', 4],//缩进4个空格
        'semi': ['warn', 'always'],//必须有分号,
        'no-multiple-empty-lines': ['warn', {'max': 2}], //最大两个空行
        'space-before-function-paren': 'off', //函数参数前保留一个空格
        'eol-last': 'off', //文件末尾保留一行空行
        'eqeqeq': ['warn', 'always', {'null': 'ignore'}], //使用===或!==
        'keyword-spacing': ['error', {
            'overrides': { // 设置关键字前后空格
                'if': {'after': false},
                'for': {"after": false},
                'while': {"after": false}
            }
        }],
        'handle-callback-err': 'off', // 关闭异常必须处理规则
        'no-unused-vars': 'off' // 关闭变量必须使用规则
    },
    overrides: [
        {
            "files": ["*.vue"],
            "rules": {
                "indent": "off",
                "vue/script-indent": ["warn", 4, {"baseIndent": 1}]
            }
        }
    ]
}
