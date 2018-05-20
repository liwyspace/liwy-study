// https://eslint.org/docs/user-guide/configuring

module.exports = {
    root: true,
    parser: 'babel-eslint',
    parserOptions: {
        sourceType: 'module'
    },
    env: {
        browser: true,
        jquery: true
    },
    // https://github.com/standard/standard/blob/master/docs/RULES-en.md
    extends: 'standard',
    // required to lint *.vue files
    plugins: [
        'html'
    ],
    // add your custom rules here
    'rules': {
        // allow paren-less arrow functions
        'arrow-parens': 0,
        // allow async-await
        'generator-star-spacing': 0,
        // allow debugger during development
        'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0,

        'indent': ['warn', 4],//缩进4个空格
        'semi': ['warn', 'always'],//必须有分号,
        'no-multiple-empty-lines': ['warn',{'max': 2}], //最大两个空行
        'space-before-function-paren': 'off', //函数参数前保留一个空格
        'eol-last': 'off', //文件末尾保留一行空行
        'eqeqeq': ['warn', 'always', { 'null': 'ignore' }], //使用===或!==
        'keyword-spacing': ['error', { 'overrides': { // 设置关键字前后空格
            'if': { 'after': false },
            'for': { "after": false },
            'while': { "after": false }
        } }]
    }
}
