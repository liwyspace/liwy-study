<template>
    <div class="header">
        <!-- 商家信息 -->
        <div class="content-wraper">
            <!-- 头像 -->
            <div class="avatar">
                <img width='64' height='64' :src="seller.avatar"/>
            </div>
            <div class="content">
                <!-- 标题 -->
                <div class="title">{{seller.name}}</div>
                <!-- 介绍 -->
                <div class="description">{{seller.description}}</div>
                <!-- 优惠 -->
                <div class="support" v-if="seller.supports">
                    {{seller.supports[0].description}}
                </div>
                <!-- 优惠数目 -->
                <div class="support-count" v-if="seller.supports" v-on:click="showDetail">
                    <span class="count"> {{seller.supports.length}}个 </span>
                    <i class="iconfont icon-keyboard_arrow_right"></i>
                </div>
            </div>
        </div>
        <!-- 公告 -->
        <div class="bulletin-wraper">
            <span class="bulletin-title"></span>
            <span class="bulletin-text"> {{seller.bulletin}} </span>
            <i class="iconfont icon-keyboard_arrow_right" v-on:click="showDetail"></i>
        </div>
        <!-- 背景 -->
        <div class="background">
            <img v-bind:src="seller.avatar" width="100%" height="100%" alt=""/>
        </div>
        <!-- 弹出层 -->
        <transition name="fade">
            <div class="detail" v-show="detailShow">
                <div class="detail-wrapper clearfix">
                    <!-- 主题内容区域 -->
                    <div class="detail-main">
                        <h1 class="name"> {{seller.name}} </h1>

                        <!-- star组件 -->
                        <div class="star-wrapper">
                            <star size="48" v-bind:score="seller.score"></star>
                        </div>

                        <!-- 标题信息 -->
                        <div class="title">
                            <div class="line"></div>
                            <div class="text">优惠信息</div>
                            <div class="line"></div>
                        </div>

                        <!-- supports内容 -->
                        <ul v-if="seller.supports" class="support">
                            <li class="support-item" v-for="(item,index) in seller.supports">
                                <span class="icon" :class="classMap[seller.supports[index].type]"></span><span class="text"> {{seller.supports[index].description}} </span>
                            </li>
                        </ul>

                        <!-- 商家公告 -->
                        <div class="title">
                            <div class="line"></div>
                            <div class="text">商家公告</div>
                            <div class="line"></div>
                        </div>

                        <!-- 公告内容 -->
                        <div class="bulletin">
                            <p class="content"> {{seller.bulletin}} </p>
                        </div>

                    </div>
                </div>
                <!-- 关闭按钮 -->
                <div class="detail-close" v-on:click="hideDetail">
                    <i class="iconfont icon-close"></i>
                </div>
            </div>
        </transition>
    </div>
</template>

<script>
import star from '../star/star.vue';

export default {
    props: ['seller'],
    data: function() {
        return {
            detailShow: false
        };
    },
    components: {
        star: star
    },
    created: function() {
        this.classMap = ['decrease', 'discount', 'guarantee', 'invoice', 'special'];
    },
    methods: {
        showDetail: function() {
            this.detailShow = true;
        },
        hideDetail: function() {
            this.detailShow = false;
        }
    }
};
</script>

<style lang="scss">
@import "../../styles/commons/mixin";

.header {
    position: relative;
    color: #fff;
    background-color: rgba(7,17,27,0.5);

    .content-wraper {
        padding: 24px 12px 18px 24px;

        .avatar {
            display: inline-block;
            vertical-align: top;
            img {
                border-radius: 2px;
            }
        }
        .content {
            display: inline-block;
            margin-left: 16px;
            font-size: 14px;

            .title {
                margin: 2px 0 8px 0;
            }
            .description {
                margin-bottom: 10px;
                line-height: 12px;
                font-size: 12px;
            }
            .support {

            }
            .support-count {
                position: absolute;
                right: 12px;
                bottom: 50px;
                padding: 0 8px;
                height: 24px;
                line-height: 24px;
                border-radius: 14px;
                background-color: rgba(0,0,0,0.2);
                text-align: center;

                .count {
                    vertical-align: top;
                    font-size: 10px;
                }

            }
        }

    }

    .bulletin-wraper {
        position: relative;
        height: 28px;
        line-height: 28px;
        padding: 0 24px 0 12px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        background-color: rgba(7,17,27,0.2);

        .bulletin-title{
            display:inline-block;
            vertical-align:top;
            margin-top: 7px;
            width: 22px;
            height: 12px;
            @include bg-image('./assets/bulletin');
            background-size: 22px 12px;
            background-repeat: no-repeat;
        }
        .bulletin-text {
            vertical-align: top;
            margin: 0 4px;
            font-size: 10px;
        }
        .icon-keyboard_arrow_right {
            position: absolute;
            font-size: 10px;
            right: 12px;
            bottom: 8px;
        }
    }
    .background {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: -1;
        // 图片模糊
        filter: blur(10px);
    }
    .detail {
        // 相对于浏览器窗口进行定位
        position: fixed;
        z-index: 100;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        // 元素超出容器产生滚动条
        overflow: auto;
        background-color: rgba(7,17,27,0.8);

        // 给其父集添加类
        &.fade-enter-active, &.fade-leave-active {
            transition: all 1s;
        }
        &.fade-enter, &.fade-leave-active {
            opacity: 0;
            background-color: rgba(7,17,27,0);
        }

        .detail-wrapper {
            width: 100%;
            min-height: 100%;
            .detail-main {
                margin-top: 64px;
                padding-bottom: 64px;
                .name {
                    line-height: 16px;
                    text-align: center;
                    font-size: 16px;
                    font-weight: 700;
                }
                .star-wrapper {
                    margin-top: 18px;
                    padding: 2px 0;
                    text-align: center;
                }
                .title {
                    display: flex;
                    width: 80%;
                    margin: 28px auto 24px auto;
                    .line {
                        flex: 1;
                        position: relative;
                        top: -6px;
                        border-bottom: 1px solid rgba(255,255,255,0.2);
                    }
                    .text {
                        padding: 0 12px;
                        font-weight: 700;
                        font-size: 14px;
                    }
                }
                .support {
                    width: 80%;
                    margin: 0 auto;
                }
                .bulletin{
                    width: 80%;
                    margin: 0 auto;
                    .content {
                        padding: 0 12px;
                        line-height: 24px;
                        font-size: 12px;
                    }
                }
            }
        }

        // 关闭按钮
        .detail-close{
            position: absolute;
            width: 32px;
            height: 32px;
            margin: -50px auto 0 auto;
            clear: both;
            font-size: 32px;
            left: 0;
            right: 0;
        }
    }
}
</style>
