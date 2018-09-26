import './collectionList.scss';
import $ from "jquery";

let CollectionList = function (options) {
    $.each(options, function (index, collection) {
        let collectionHtml = '<div class="collect">';
        collectionHtml += '<div class="hd"><h1><a href="article.html">' + collection.typeName + '</a></h1></div>';
        collectionHtml += '<div class="bd"><ul>';
        $.each(collection.collects, function (index, collect) {
            collectionHtml += '<li><a href="' + collect.url + '" target="_blank" style="background: url(' + collect.logoPic + ') no-repeat; ">' + collect.name + '</a></li>';
        });
        collectionHtml += '</ul></div></div>';

        $("div.collect_list").append(collectionHtml);
    });
};

export default CollectionList;
