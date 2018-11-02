import Vue from 'vue';
import portalSDK from 'PortalSDK';
import {routes} from './router/routes.js';

console.log('------ liwyTemp 开始加载 ------');

Vue.config.productionTip = false;

portalSDK.addRoutes('liwyTemp', routes);
console.log('------ liwyTemp 加载完成 ------');
