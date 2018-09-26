import './sliderCourse.scss';
import $ from "jquery";

let SliderCourse = function (options) {
    $.each(options, function (index, summary) {
        $("div.sliderCourse ul").append('<li><a href="9.html">' + summary.title + '</a><span><strong>' + summary.lookcount + '</strong>人学习</span></li>');
    });
};

export default SliderCourse;
