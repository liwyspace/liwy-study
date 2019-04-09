<html>
<body>
您好呀<#if username!="">${username}</#if>，欢迎登陆${webSite}!

导航栏：
<#list navers as name>
    <b>${name}</b>
</#list>

文章列表：
<@context article.title>${article.context}</@context>
===========================


<#macro context title>
标题：${title}
    <#nested >
结束！
</#macro>
</body>
</html>