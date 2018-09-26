import './footer.scss';
import $ from "jquery";

let Footer = function(options) {
    $("div.footer .ft_c .it_wx img").attr("src", options.qqPic);
    $("div.footer .ft_c .boniu_wx img").attr("src", options.weixinPic);
    $("div.footer .ft_r p.pp2:eq(0)").html("QQ：" + options.qq);
    $("div.footer .ft_r p.pp2:eq(1)").html("微信：" + options.weixin);
    $("div.footer .ft_r p.pp2:eq(2)").html("邮箱：" + options.email);
    $("div.copy span.email").html(options.email);
    $("div.copy span.qq").html(options.qq);
    $("div.copy span.weixin").html(options.weixin);
    $("div.copy span.copycode").html(options.copy);
};
export default Footer;
