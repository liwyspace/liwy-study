$(function() {
    // 导航滑动事件
    $('.navbar-left li').mouseover(function() {
        if($(this).find('a').hasClass('dropdown-toggle')) {
            $(this).find('.dropdown-menu').css('display', 'block');
        }
        var width = parseInt($(this).css('width')) + 1;
        var left = getNavLeft($(this));
        $('.liwy-bottom-line').css({
            'width': width + 'px',
            'left': left
        });
    });

    $('.navbar-left li').mouseout(function() {
        if($(this).find('a').hasClass('dropdown-toggle')) {
            $(this).find('.dropdown-menu').css('display', 'none');
            $('.liwy-bottom-line').css('width', 0);
        } else {
            $('.liwy-bottom-line').css('width', 0);
        }
    });

    function getNavLeft(el) {
        var index = el.index();
        var left = 0;
        for(var i = 0; i < index; i++) {
            var li = el.parent().find('li');
            var width = li.eq(i).css('width');
            left += parseInt(width);
        }
        return left;
    }

    // 产品介绍切换
    $('.product-item').mouseenter(function() {
        $(this).addClass('active');
    });
    $('.product-item').mouseleave(function() {
        $(this).removeClass('active');
    });
});
