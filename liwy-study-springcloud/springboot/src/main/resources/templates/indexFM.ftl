<@compress single_line=true> <#--压缩HTML代码-->
<html>
<head>
    <title>freemarker Test</title>
</head>
<body>
<h1>Hello,${user}</h1>
<div>
    ${latestProduct.url}
    ${latestProduct.name}
    <#if isTrue>
        ${latestProduct.url}
    <#else>
        ${latestProduct.name}
    </#if>
    ${createTime?string('yyyy-MM-dd HH-mm-ss')}
    <#list list as item>
        ${item}
    </#list>
</div>
</body>
</html>
</@compress>