import './header.scss';
import $ from "jquery";

let Header = function(options) {
    $("div.header>.head_l img").attr("src", options.logoPic);
    $("div.header>.head_r>.userhd img").attr("src", options.headPic);
    $("div.header .nav li:eq("+options.index+") a").addClass("current");
    $("div.header .nav li:not(:eq("+options.index+")) a").removeClass("current");
};

export default Header;

// (function() {
//     $.ajax({
//         url: Constant.HOST+"/common/getSiteInfo",
//         dataType: "json",
//         success: function(data){
//             $("div.header>.head_l img").attr("src", data.logoPic);
//         }
//     });
//
//     $.ajax({
//         url: Constant.HOST+"/user/getUserInfo?id=10001",
//         dataType: "json",
//         success: function(data){
//             console.log(data);
//             $("div.header>.head_r>.userhd img").attr("src", data.headPic);
//         }
//     });
// })();
