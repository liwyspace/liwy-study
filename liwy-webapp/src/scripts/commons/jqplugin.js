/*
 * jQuery插件
 */
;(function($, window, document) {
    $.fn.green = function() {
        $(this).each(function() {
            $(this).css('color', 'green');
        });
    };
})(jQuery, window, document);
