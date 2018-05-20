var MathUtil = (function(){
    var mod = {};
    mod.add = function(a, b) {
        return a + b + 2;
    };
    mod.sub = (a, b) => a - b;
    return mod;
})();