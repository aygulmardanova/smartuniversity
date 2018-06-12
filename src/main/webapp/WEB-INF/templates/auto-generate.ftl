<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Сгенерировать пожелания </title>
</head>

<body>
<#if generatedWishTypes?has_content>
<p>Успешно сгенерированы следующие типы пожеланий:</p>
    <#list generatedWishTypes as generatedWishType>
    <li>${generatedWishType}</li>
    </#list>
<#else>
</#if>
<form action="/generate" method="post">
    <fieldset style="width: 500px; height: 250px;">
        <legend>Автоматическая генерация пожеланий</legend>
        <input type="submit" value="Сгенерировать пожелания" style="width: 500px;">
        <label for="wishTypes" style="width: 500px; font-size: 16px"> Выберите типы пожеланий </label>
        <select name="wishTypes" id="wishTypes" multiple style="width: 500px; border: none" size="7">
            <option selected></option>
            <option value="all">Все пожелания</option>
        <#if wishes?has_content>
            <#list wishes as wish>
                <option value="${wish.type}">${wish.name}</option>
            </#list>
        <#else>
        </#if>
        </select>

    </fieldset>
</form>

</body>