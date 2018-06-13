<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Error page </title>
</head>

<body>
<h1>some error</h1>
<#if errorMsg??>
    <p>${errorMsg}</p>
<#else>
</#if>

<#if msg??>
    <p>${msg}</p>
<#else>
</#if>

</body>