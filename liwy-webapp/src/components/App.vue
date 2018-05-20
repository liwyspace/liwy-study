<template>
    <div id="app">
        <v-header v-bind:seller="seller"></v-header>
        <div class="tab">
            <div class="tab-item">
                <router-link to="/goods">商品</router-link>
            </div>
            <div class="tab-item">
                <router-link to="/ratings">评论</router-link>
            </div>
            <div class="tab-item">
                <router-link to="/seller">商家</router-link>
            </div>
        </div>

        <!-- 如果把切换出去的组件保留在内存中，可以保留它的状态或避免重新渲染。 -->
        <keep-alive>
            <!-- 路由组件渲染位置 -->
            <router-view></router-view>
        </keep-alive>
    </div>
</template>

<script>
import header from './header/header.vue';

export default {
    data: function() {
        return {
            seller: {
                id: 1
            }
        };
    },
    components: {
        'v-header': header
    },
    created: function() {
        // 获取seller数据信息
        this.$http.get('http://localhost:9000/api/seller').then(function(response) {
            console.log(response);
            let data = response.data;
            console.log(data);
            this.seller = Object.assign({}, this.seller, data);
        });
    }
};
</script>

<style lang="scss">
#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    // text-align: center;
    color: #2c3e50;

    //导航
    .tab {
        display: flex;
        width: 100%;
        height: 40px;
        line-height: 40px;
        // border-bottom:1px solid rgba(7,17,27,0.1)
        // border-1px(rgba(7,17,27,0.1))
        .tab-item {
            flex: 1;
            text-align: center;
            & > a {
                display: block;
                font-size: 14px;
                color: #4d555d;
                &.active {
                    color: #f01414;
                }
            }
        }
    }
}
</style>
