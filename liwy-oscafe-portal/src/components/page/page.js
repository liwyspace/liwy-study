import './page.scss';
import $ from "jquery";

let Page = function (options, rollback) {
    let pager = {page: 1, size: 20};
    let pageSize = Math.ceil(options.total/pager.size);

    $(".allspan").html("共 " + pageSize + " 页");

    for(let i=1; i<=pageSize; i++) {
        $("div.page .page_child").append('<a '+(i===1?'class="cur"':'')+' href="javascript:void(0);" value="'+i+'">'+i+'</a>');
    }

    if(rollback !== undefined) {
        rollback(pager);
        $("div.page .page_child").on('click', 'a', function () {
            $(this).addClass("cur").siblings().removeClass("cur");
            pager["page"] = parseInt($(this).attr("value"));
            rollback(pager);
        });
    }

    $("div.page .psubmit").click(function () {
        let nex = parseInt($("#nowpage").val());
        if(nex > 0 && nex <= pageSize) {
            $("div.page .page_child a[value='"+nex+"']").addClass("cur").siblings().removeClass("cur");
            pager["page"] = nex;
            rollback(pager);
        }
    });
    $("div.page .prev").click(function () {
        let nex = pager["page"] -1;
        pager["page"] = nex < 1 ? 1 : nex;
        $("div.page .page_child a[value='"+pager["page"]+"']").addClass("cur").siblings().removeClass("cur");
        rollback(pager);
    });
    $("div.page .next").click(function () {
        let nex = pager["page"] +1;
        pager["page"] = nex > pageSize ? pageSize : nex;
        $("div.page .page_child a[value='"+pager["page"]+"']").addClass("cur").siblings().removeClass("cur");
        rollback(pager);
    });
};

export default Page;
