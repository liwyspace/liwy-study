function flatten(target, src) {
    if(!isObject(target) || !isObject(src)) return;

    Object.keys(src).forEach(key => {
        target.hasOwnProperty(key) && src[key] !== undefined && (target[key] = src[key]);
    });
}

function isObject(o) {
    return Object.prototype.toString.call(o) === '[object Object]';
}

export default {
    flatten,
    isObject
};
