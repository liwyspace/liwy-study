import './sliderUser.scss';
import $ from "jquery";

let SliderUser = function (options) {
    $.each(options, function (index, summary) {
        $("div.sliderUser").append('<dl><dt><img src="' + summary.mainpicture + '" /></dt><dd><h3>feng-ing</h3><p>已学完第 85 节</p></dd></dl>');
    });
};

export default SliderUser;
