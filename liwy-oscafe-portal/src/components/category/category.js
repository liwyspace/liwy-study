import './category.scss';
import $ from "jquery";

let Category = function (options, rollback) {
    $.each(options.types, function (index, type) {
        $("div.category .cate_type ul").append('<li><a href="javascript:void(0);" subject="' + type.id + '">' + type.name + '</a></li>');
    });

    if(rollback !== undefined) {
        rollback({type: undefined, sort: undefined});
        $("div.category div ul").on('click', 'li a', function () {
            $(this).parent().addClass("cur").siblings().removeClass("cur");
            rollback({
                type: $("div.cate_type li[class='cur'] a").attr("subject"),
                sort: $("div.cate_sort li[class='cur'] a").attr("key")
            });
        });
    }
};

export default Category;
