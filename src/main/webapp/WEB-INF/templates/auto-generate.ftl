<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Сгенерировать пожелания </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>

<body>
<div class="container">
    <!-- Image and text -->
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            <img src="https://getbootstrap.com/docs/4.1/assets/brand/bootstrap-solid.svg" width="30" height="30"
                 class="d-inline-block align-top" alt="">
            Пожелания
        </a>
    </nav>

    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="/auto-generate">Авто-генерация</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/teachers">Преподаватели</a>
        </li>
    </ul>
    <div class="border">
        <div class="col-6">
    <#if generatedWishTypes?has_content>
<h6>Успешно сгенерированы следующие типы пожеланий:</h6>
        <#list generatedWishTypes as generatedWishType>
    <li>${generatedWishType}</li>
        </#list>
    <#else>
    </#if>
            <form action="/generate" method="post" class="w-100">
                <fieldset>
                    <legend>Автоматическая генерация пожеланий</legend>
                    <input type="submit" value="Сгенерировать пожелания" class="btn btn-secondary w-100">
                    <label for="wishTypes"> Выберите типы пожеланий </label>
                    <select name="wishTypes" id="wishTypes" multiple size="7">
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
        </div>
    </div>
</div>
</body>
