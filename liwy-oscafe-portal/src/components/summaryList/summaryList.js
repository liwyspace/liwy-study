import './summaryList.scss';
import $ from "jquery";

let SummaryList = function (options) {
    $.each(options, function (index, summary) {

        let summaryHtml = '<div class="summary">';
        summaryHtml += '<div class="hd"><h1><a href="article.html">'+summary.title+'</a></h1></div>';
        summaryHtml += '<div class="bd"><p>'+summary.general+'</p></div>';
        summaryHtml += '</div>';

        $("div.summary_list").append(summaryHtml);
    });
};

export default SummaryList;
