import $ from "jquery";
import Constant from '@/commons/scripts/constant.js';
import Utils from '@/commons/scripts/utils.js';

import 'normalize.css';
import '@/commons/styles/base.scss';
import '@/commons/styles/iconfont.css';
import '../styles/course.scss';

import Header from '@/components/header/header.js';
import Footer from '@/components/footer/footer.js';
import Category from '@/components/category/category.js';
import PicList from '@/components/picList/picList.js';
import Page from '@/components/page/page.js';

(function() {
    new Header({
        logoPic: "http://localhost:9000/uploads/logoko.png",
        headPic: "http://localhost:9000/uploads/40.jpg",
        index: 1
    });

    let courseParam = {
        type: '',
        sort: '',
        pager: {
            page: 1,
            size: 20
        }
    };

    // 加载分类
    $.ajax({
        url: Constant.HOST + "/nav/queryTypeList",
        dataType: "json",
        success: function (data) {
            new Category({
                types: data
            }, function (result) {
                Utils.flatten(courseParam, result);
                courseList(courseParam);
            });
        }
    });

    // 加载分页
    new Page({
        total: 100
    }, function (result) {
        Utils.flatten(courseParam.pager, result);
        courseList(courseParam);
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

//根据条件查询课程信息
function courseList(param) {
    console.log(param);
    // 根据类型加载课程
    $.ajax({
        type: "GET",
        url: Constant.HOST + "/course/queryCourseList",
        // data: param,
        dataType: "json",
        beforeSend: function () {
            $("div.layout_inner_contex .picList").html("<p style='padding:200px; text-align:center'><img src='" + require('@/commons/images/loading.gif') + "' /></p>");
        },
        success: function (courses) {
            new PicList($("div.layout_inner_contex"), courses);
        },
        error: function (request, error) {
            alert("连接服务器失败，请稍后再试!");
        }
    });
}
