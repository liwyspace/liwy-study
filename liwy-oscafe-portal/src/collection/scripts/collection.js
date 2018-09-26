import $ from 'jquery';
import Constant from '@/commons/scripts/constant.js';
import Utils from '@/commons/scripts/utils.js';

import 'normalize.css';
import '@/commons/styles/base.scss';
import '@/commons/styles/iconfont.css';
import '../styles/collection.scss';

import Header from '@/components/header/header.js';
import Footer from '@/components/footer/footer.js';
import SliderCourse from '@/components/sliderCourse/sliderCourse.js';
import SliderUser from '@/components/sliderUser/sliderUser.js';
import CollectionList from '@/components/collectionList/collectionList.js';

(function () {
    new Header({
        logoPic: "http://localhost:9000/uploads/logoko.png",
        headPic: "http://localhost:9000/uploads/40.jpg",
        index: 4
    });

    // 根据加载资源列表
    $.ajax({
        type: "GET",
        url: Constant.HOST + "/link/queryCollectList",
        // data: param,
        dataType: "json",
        success: function (courses) {
            new CollectionList(courses);
        },
        error: function (request, error) {
            alert("连接服务器失败，请稍后再试!");
        }
    });

    // 加载侧边栏热门文章
    $.ajax({
        type: "GET",
        url: Constant.HOST + "/project/queryProjectList",
        // data: param,
        dataType: "json",
        success: function (courses) {
            new SliderCourse(courses);
        },
        error: function (request, error) {
            alert("连接服务器失败，请稍后再试!");
        }
    });
    // 加载侧边栏活跃用户
    $.ajax({
        type: "GET",
        url: Constant.HOST + "/project/queryProjectList",
        // data: param,
        dataType: "json",
        success: function (courses) {
            new SliderUser(courses);
        },
        error: function (request, error) {
            alert("连接服务器失败，请稍后再试!");
        }
    });

    // footer页脚
    new Footer({
        qq: "332301842",
        weixin: "liwy1024611",
        email: "liwenyao_java@126.com",
        copy: "京ICP备15000252号-1",
        qqPic: "http://localhost:9000/uploads/QQ.jpg",
        weixinPic: "http://localhost:9000/uploads/weixin.jpg"
    });
})();
