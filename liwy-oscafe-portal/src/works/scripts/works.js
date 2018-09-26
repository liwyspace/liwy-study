import $ from 'jquery';
import Constant from '@/commons/scripts/constant.js';
import Utils from '@/commons/scripts/utils.js';
import Swiper from 'swiper';

import 'normalize.css';
import '@/commons/styles/base.scss';
import '@/commons/styles/iconfont.css';
import '../styles/works.scss';

import Header from '@/components/header/header.js';
import Footer from '@/components/footer/footer.js';
import Tabs from '@/components/tabs/tabs.js';
import PicList from '@/components/picList/picList.js';
import Page from '@/components/page/page.js';

(function() {
    new Header({
        logoPic: "http://localhost:9000/uploads/logoko.png",
        headPic: "http://localhost:9000/uploads/40.jpg",
        index: 2
    });

    let courseParam = {
        type: '',
        sort: '',
        pager: {
            page: 1,
            size: 20
        }
    };

    // 加载分类标签页
    $.ajax({
        url: Constant.HOST + "/nav/queryTypeList",
        dataType: "json",
        success: function (data) {
            new Tabs($("div.layout_inner_contex"), data, function (id) {
                courseParam["type"] = id;
                projectList(courseParam);
            });
        }
    });

    // 加载分页
    new Page({
        total: 100
    }, function (result) {
        Utils.flatten(courseParam.pager, result);
        projectList(courseParam);
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

    /*轮播图模块-start*/
    var pagination_h=$(".pagination").width();
    var mySwiper = new Swiper('.swiper-container',{
        pagination: '.pagination',
        loop:true,
        speed:500,
        autoplay:4000,
        grabCursor: true,
        paginationClickable: true
    });

    $('#bannerbox').hover(
        function(){
            mySwiper.stopAutoplay();
            $('#banner-left,#banner-right').stop(true,true);
            $('#banner-left,#banner-right').fadeIn();
        },
        function(){
            mySwiper.startAutoplay();
            $('#banner-left,#banner-right').fadeOut();
        }
    );
    $('#banner-left').mouseover(function(){
        $(this).removeClass('arrow-left').addClass('arrow-left2');
    });
    $('#banner-left').mouseout(function(){
        $(this).removeClass('arrow-left2').addClass('arrow-left');
    });
    $('#banner-right').mouseover(function(){
        $(this).removeClass('arrow-right').addClass('arrow-right2');
    });
    $('#banner-right').mouseout(function(){
        $(this).removeClass('arrow-right2').addClass('arrow-right');
    });
    $('#banner-left').click(function(){mySwiper.swipePrev();});
    $('#banner-right').click(function(){mySwiper.swipeNext();});
    /*轮播图模块-end*/
})();

//根据条件查询课程信息
function projectList(param) {
    console.log(param);
    // 根据类型加载课程
    $.ajax({
        type: "GET",
        url: Constant.HOST + "/project/queryProjectList",
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

