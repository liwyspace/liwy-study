import './tabs.scss';
import $ from "jquery";

let Tabs = function(fdiv, options, rollback) {
    for(var i = 0; i < options.length; i++) {
        let type = options[i];
        $(fdiv).find("div.tabs ul.slidenav").append('<li><a href="javascript:void(0);" subject="' + type.id + '">' + type.name + '</a></li>');
    }

    if(rollback !== undefined) {
        rollback(options[0].id);
        $(fdiv).find("div.tabs ul.slidenav").on('click', 'li a', function () {
            var posL = $(this).parent().position().left;
            $(this).parent().parent().siblings(".curBg").stop(true, true).animate({"left": posL}, "fast");

            rollback($(this).attr("subject"));

            // if($(this).attr("subject")){
            //     courseList($(this).attr("subject"));
            // } else {
            //     $(this).parent().parent().parent().siblings(".tabs_content").children().eq($(this).parent().index()).slideDown().siblings().slideUp();
            // }
        });
    }
};

export default Tabs;


