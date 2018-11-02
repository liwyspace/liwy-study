import HttpUtil from 'HttpUtil';

let HttpInst = HttpUtil.create({
    baseURL: process.env.apiBaseUrl,
    timeout: 15000
});

export default HttpInst;

