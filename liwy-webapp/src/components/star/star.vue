<template>
<div class="star" v-bind:class="starType">
    <span v-for="(itemClass,index) in itemClasses" v-bind:class="itemClass" class="star-item" key="index"></span>
</div>
</template>

<script>
    const LENGTH = 5; // 星星总长度
    const CLS_ON = 'on'; // 代表全星
    const CLS_HALF = 'half'; // 代表半星
    const CLS_OFF = 'off'; // 代表无星

    export default {
        props: ['size', 'score'],
        data: function() {
            return {
                starType: 'star-' + this.size
            };
        },
        computed: {
            itemClasses: function() {
                let result = [];

                let score = Math.floor(this.score * 2) / 2;
                let hasDecimal = score % 1 !== 0;
                let integer = Math.floor(score);

                // 将全星推进数组
                for(let i = 0; i < integer; i++) {
                    result.push(CLS_ON);
                }
                // 将半星推进数组
                if(hasDecimal) {
                    result.push(CLS_HALF);
                }
                // 剩余数组长度推进无星
                while(result.length < LENGTH) {
                    result.push(CLS_OFF);
                }
                return result;
            }
        }
    };
</script>

<style lang="scss">
@import "../../styles/commons/mixin";

.star {
    font-size: 0;
    .star-item {
        display: inline-block;
        background-repeat: no-repeat;
    }
    &.star-48 {
        .star-item {
            width: 20px;
            height: 20px;
            margin-right: 22px;
            background-size: 20px 20px;
            &:last-child {
                margin-right: 0;
            }
            &.on {
                @include bg-image('./assets/star48_on');
            }
            &.half {
                @include bg-image('./assets/star48_half');
            }
            &.off {
                @include bg-image('./assets/star48_off');
            }
        }
    }
    &.star-36 {
        .star-item {
            width: 15px;
            height: 15px;
            margin-right: 6px;
            background-size: 15px 15px;
            &:last-child {
                margin-right: 0;
            }
            &.on {
                @include bg-image('./assets/star36_on');
            }
            &.half {
                @include bg-image('./assets/star36_half');
            }
            &.off {
                @include bg-image('./assets/star36_off');
            }
        }
    }
    &.star-24 {
        .star-item {
            width: 10px;
            height: 10px;
            margin-right: 3px;
            background-size: 10px 10px;
            &:last-child {
                margin-right: 0;
            }
            &.on {
                @include bg-image('./assets/star24_on');
            }
            &.half {
                @include bg-image('./assets/star24_half');
            }
            &.off {
                @include bg-image('./assets/star24_off');
            }
        }
    }
}
</style>
