import portalSDK from 'PortalSDK';
import Example from '@/components/Example';
export const routes = [{
    path: '/temp',
    name: '示例页面',
    component: portalSDK.AppMain,
    children: [{
        path: '/example',
        name: '示例1',
        component: Example,
        leaf: true
    }, {
        path: '/example2',
        name: '示例2',
        component: portalSDK.AppMainChildren,
        children: [{
            path: '/example2_1',
            name: '示例2-1',
            component: portalSDK.AppMainChildren,
            leaf: true,
            redirect: '/example2_1/list',
            children: [{
                path: '/example2_1/list',
                name: '内容列表',
                component: Example
            }, {
                path: '/example2_1/save',
                name: '内容添加'
            }, {
                path: '/example2_1/update',
                name: '内容修改'
            }]
        }]
    }]
}];
