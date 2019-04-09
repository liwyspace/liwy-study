<html>
<head>
    <title>Freemarker模板</title>
</head>
<body>
你好！${username},欢迎<#if username=="liwy">"大神"</#if>进入freemarker模板！<br/>

成绩为：${random}<br/>
<#if random gte 90>
    优秀
<#elseif random gt 70>
    良好
<#elseif random gte 60>
    及格
<#else>
    不及格
</#if>

<#list userList as user>
    ${user.username}
</#list>

<#include "footer.html"/>

======================
<#macro forList pm1 pm2>
    <#list pm1 as user>
        ${user}++++${pm2}
        <#nested />
    </#list>
</#macro>
======================
<#--
<@forList userList "liwy">liwenyao</@forList>-->
------------
<@forList userList "nan">llllnana</@forList>
</body>
</html>