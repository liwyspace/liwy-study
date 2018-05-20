import Vue from 'vue';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource';

import 'normalize.css';
import '../styles/commons/base.scss';
import '../styles/commons/iconfont.css';

import App from '../components/App';
import goods from '../components/goods/goods';
import ratings from '../components/ratings/ratings';
import seller from '../components/seller/seller';

Vue.use(VueRouter);
Vue.use(VueResource);

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router: new VueRouter({
        routes: [ // 注意：是routes而不是routers
            {path: '/goods', component: goods},
            {path: '/ratings', component: ratings},
            {path: '/seller', component: seller},
            {path: '/', redirect: '/goods'}
        ],
        'linkActiveClass': 'active' // 修改router-link的激活类名。默认：router-link-active
    }),
    // el元素将被template替换
    template: '<App/>',
    // template元素中的组件将被compnents内容替换
    components: {App}
});
