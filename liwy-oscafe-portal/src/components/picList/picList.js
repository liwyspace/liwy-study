import './picList.scss';
import $ from "jquery";

let PicList = function(fdiv, options) {
    var html = "<ul>";
    $.each(options, function(index, course){
        html += "<li><a href='./course/"+course.id+".html' target='_blank'><img src='"+course.mainpicture+"' alt='"+course.title+"' width='283' height='186'><div class='zhe'></div><span class='star'></span></a><div class='bdtxt'><h3><a href='./course/"+course.id+".html' class='afont'>"+course.title+"</a></h3><p>已经有<strong>"+course.lookcount+"</strong>人学习</p></div></li>";
    });
    html += "</ul>";
    $(fdiv).find("div.picList").html(html);
};

export default PicList;
