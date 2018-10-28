import portalSDK from 'PortalSDK';
import HelloWorld from '@/components/HelloWorld';

export const routes = [{
    path: '/temp',
    name: '模板系统',
    component: portalSDK.AppMain,
    children: [{
        path: '/helloWorld',
        name: 'Hello',
        component: HelloWorld,
        leaf: true
    }, {
        path: '/content',
        name: '内容',
        component: portalSDK.AppMainChildren,
        children: [{
            path: '/contentManger',
            name: '内容管理',
            component: portalSDK.AppMainChildren,
            leaf: true,
            redirect: '/content/list',
            children: [{
                path: '/content/list',
                name: '内容列表',
                component: HelloWorld
            }, {
                path: '/content/save',
                name: '内容添加'
            }, {
                path: '/content/update',
                name: '内容修改'
            }]
        }]
    }]
}];
