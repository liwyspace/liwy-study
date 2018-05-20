<template>
    <div class="goods">
        <!-- 左侧菜单栏 -->
        <div class="menu-wrapper">
            <ul>
                <li v-for="(item, index) in goods"  class="menu-item">
                    <span class="text"><span v-show="item.type>0" class="icon" v-bind:class="classMap[item.type]"></span>{{item.name}}</span>
                </li>
            </ul>
        </div>
        <!-- 右侧商品详情 -->
        <div class="foods-wrapper">
            <ul>
                <li v-for="(item, index) in goods">
                    <ul>
                        <li v-for="(food, index) in item.foods" class="food-item">
                            <div class="icon">
                                <img width="57" height="57" v-bind:src="food.icon" />
                            </div>
                            <div class="content">
                                <div class="name">{{food.name}}</div>
                                <div class="desc">{{food.description}}</div>
                                <div class="extra">
                                    <span class="count">月售{{food.sellCount}}份</span>
                                    <span>好评率{{food.rating}}%</span>
                                </div>
                                <div class="price">
                                    <span class="now">￥{{food.price}}</span>
                                    <span class="old" v-show="food.oldPrice">￥{{food.oldPrice}}</span>
                                </div>

                                <!-- cartcontrol组件 -->
                                <div class="cart-wrapper">
                                    <!-- add自定义事件用于派发当前点击的dom元素，add为子组件方法，addFood为父组件方法 -->
                                    <cartcontrol v-bind:food="food" v-on:add="addFood"></cartcontrol>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- 底部购物车 -->
        <div></div>
    </div>
</template>

<script>
import cartcontrol from '../cartcontrol/cartcontrol.vue';

export default {
    data: function() {
        return {
            goods: []
        };
    },
    components: {
        cartcontrol: cartcontrol
    },
    created: function() {
        // 商品type对应的类名类名
        this.classMap = ['decrease', 'discount', 'guarantee', 'invoice', 'special'];
        // 获取数据
        this.$http.get('http://localhost:9000/api/goods').then(function(response) {
            this.goods = response.data;
        });
    },
    methods: {
        addFood: function() {
            console.log('添加一个食物');
        }
    }
};
</script>

<style lang="scss">
@import "../../styles/commons/mixin";

.goods {
    position: absolute;
    top: 180px;
    bottom: 46px;
    display: flex;
    width: 100%;
    overflow: hidden;

    // flex布局左栏固定
    .menu-wrapper {
        flex: 0 0 80px;
        width: 80px;
        background-color: #f4f5f7;

        .menu-item {
            display: table;// 用于垂直居中
            width: 56px;
            height: 54px;
            background-color: #f3f5f7;
            padding: 0 12px;
            line-height: 14px;

            .text {
                display: table-cell;
                width: 56px;
                vertical-align: middle;
                position: relative;
                font-size: 12px;
                @include border-1px(rgba(7,17,27,0.1));

                .icon {
                    display: inline-block;
                    vertical-align: top;
                    position: relative;
                    width: 12px;
                    height: 12px;
                    margin-right: 2px;
                    background-size: 12px 12px;
                    background-repeat: no-repeat;

                    &.decrease {
                        @include bg-image('./assets/decrease_3');
                    }
                    &.discount {
                        @include bg-image('./assets/discount_3');
                    }
                    &.guarantee {
                        @include bg-image('./assets/guarantee_3');
                    }
                    &.invoice {
                        @include bg-image('./assets/invoice_3');
                    }
                    &.special {
                        @include bg-image('./assets/special_3');
                    }
                }
            }
        }

    }
    .foods-wrapper {
        flex: 1;
        .food-item {
            display: flex;
            margin: 18px;
            padding-bottom: 18px;
            @include border-1px(rgba(7,17,27,0.1));

            .icon {
                flex: 0 0 57px;
                margin-right: 18px;
            }

            .content {
                flex: 1;
                .name {
                    margin: 2px 0 8px 0;
                    line-height: 14px;
                    color: rgb(7,17,27);
                    font-size: 14px;
                }
                .desc {
                    font-size: 10px;
                    line-height: 10px;
                    color: rgb(147,153,159);
                    margin-bottom: 8px;
                }
                .extra {
                    font-size: 10px;
                    color: rgb(147,153,159);
                    line-height: 12px;
                    .count{
                        margin-right: 8px;
                    }
                }
                .price {
                    font-weight: 700;
                    line-height: 24px;
                    .now {
                        margin-right: 8px;
                        font-size: 14px;
                        color: rgb(240, 20, 20);
                    }
                    .old {
                        text-decoration: line-through;
                        font-size: 10px;
                        color: rgb(147, 153, 159);
                    }
                }
                .cart-wrapper {
                    position: absolute;
                    right: 0;
                    bottom: 12px;
                }
            }
        }
    }
}
</style>
