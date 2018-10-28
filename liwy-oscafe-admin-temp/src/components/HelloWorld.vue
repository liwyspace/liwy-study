<template>
    <div>
        <h1>{{ msg }}</h1>
    </div>
</template>

<script>
    export default {
        name: 'HelloWorld',
        data() {
            return {
                msg: 'Hello World 开源小屋[www.oscafe.net]!'
            };
        }
    };
</script>

<style lang="scss" scoped>
    h1 {
        color: red;
    }
</style>
