<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Main page </title>
</head>

<body>
<form action="/generate" method="get">
    <fieldset>
        <legend>Auto generate</legend>
        <input type="submit" value="Сгенерировать пожелания">
    </fieldset>
</form>

<#if users?has_content>
    <#list users as user>
        <h3>${user.surname}</h3>
        <h4>${user.name}</h4>
        <#if user.userComps??>
            <#list user.userComps as userComp>
                <ol>${userComp.competence.name}</ol>
            </#list>
        <#else>
            <h5>No comp for user</h5>
        </#if>
    </#list>
<#else>
    <p>There are no users</p>
</#if>
</body>