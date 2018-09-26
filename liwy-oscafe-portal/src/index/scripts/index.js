import $ from 'jquery';
import Constant from '@/commons/scripts/constant.js';

import 'normalize.css';
import '@/commons/styles/base.scss';
import '@/commons/styles/iconfont.css';
import '../styles/index.scss';

import Header from '@/components/header/header.js';
import Footer from '@/components/footer/footer.js';
import Slide3d from '@/components/slide3d/slide.js';
import Tabs from '@/components/tabs/tabs.js';
import PicList from '@/components/picList/picList.js';

(function () {

    new Header({
        logoPic: "http://localhost:9000/uploads/logoko.png",
        headPic: "http://localhost:9000/uploads/40.jpg",
        index: 0
    });

    new Slide3d([
        {
            pic: "http://localhost:9000/uploads/57a952e3c49b6.jpg"
        }, {
            pic: "http://localhost:9000/uploads/57db69405d7bb.jpg"
        }, {
            pic: "http://localhost:9000/uploads/57a062d5af946.jpg"
        }, {
            pic: "http://localhost:9000/uploads/57a9523318860.jpg"
        }, {
            pic: "http://localhost:9000/uploads/57a9524ce3338.jpg"
        }
    ]);

    // 加载课程类型
    $.ajax({
        url: Constant.HOST + "/nav/queryTypeList",
        dataType: "json",
        success: function (data) {
            new Tabs($("div.layout_inner_study"), data, function (id) {
                // 根据类型加载课程
                $.ajax({
                    type: "GET",
                    url: Constant.HOST + "/course/queryCourseList",
                    data: "type=" + id,
                    dataType: "json",
                    beforeSend: function () {
                        $("div.layout_inner_study .picList").html("<p style='padding:200px; text-align:center'><img src='" + require('@/commons/images/loading.gif') + "' /></p>");
                    },
                    success: function (courses) {
                        new PicList($("div.layout_inner_study"), courses);
                    },
                    error: function (request, error) {
                        alert("连接服务器失败，请稍后再试!");
                    }
                });
            });
        }
    });

    // 项目分享
    new Tabs($("div.layout_inner_project"), [
        {
            id: 0,
            name: "项目分享"
        }
    ]);

    $.ajax({
        type: "GET",
        url: Constant.HOST + "/project/queryProjectList",
        dataType: "json",
        beforeSend: function () {
            $("div.layout_inner_project .picList").html("<p style='padding:200px; text-align:center'><img src='" + require('@/commons/images/loading.gif') + "' /></p>");
        },
        success: function (data) {
            new PicList($("div.layout_inner_project"), data);
        },
        error: function (request, error) {
            alert("连接服务器失败，请稍后再试!");
        }
    });

    //友情链接
    new Tabs($("div.layout_inner_fruand"), [
        {
            id: 0,
            name: "友情链接"
        }, {
            id: 1,
            name: "好友推荐"
        }
    ], function (id) {
        if(id === '0') {
            $("div.layout_inner_fruand .friend .links").slideDown().siblings().slideUp();
        } else if(id === '1') {
            $("div.layout_inner_fruand .friend .friends").slideDown().siblings().slideUp();
        }
    });

    $.ajax({
        type: "GET",
        url: Constant.HOST + "/link/queryLinkList",
        dataType: "json",
        success: function (data) {
            for(var i = 0; i < data.length; i++) {
                let link = data[i];
                if(link.type === "1") {
                    $("div.layout_inner_fruand .friend .links").append('<a href="' + link.url + '" target="_blank">' + link.name + '</a>');
                } else if(link.type === "2") {
                    $("div.layout_inner_fruand .friend .friends").append('<a href="' + link.url + '" target="_blank"><img src="' + link.logoPic + '" title="' + link.name + '" alt="' + link.name + '" /></a>');
                }
            }
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

