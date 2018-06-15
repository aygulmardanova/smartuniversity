<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Сгенерировать пожелания </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>

<body>
<div class="container">
    <nav class="navbar navbar-dark bg-info">
        <a class="navbar-brand" href="#">
            Автоматическая генерация пожеланий
        </a>
    </nav>

    <div class="border">
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" href="/auto-generate">Авто-генерация</a>
            </li>
        </ul>
        <div class="row justify-content-center m-5">
            <div class="col-7">
                    <#if generatedWishTypes?has_content>
                    <div class="alert alert-success" role="alert">
                        <h6>Успешно сгенерированы следующие типы пожеланий:</h6>
                    <#list generatedWishTypes as generatedWishType>
                        <li>${generatedWishType}</li>
                    </#list>
                    </div>
                    </#if>
                <form action="/generate" method="post" class="w-100">
                    <fieldset>
                        <label class="h4" for="wishTypes"> Выберите типы пожеланий: </label>
                        <select class="custom-select custom-select mb-3 border-0 overflow-hidden"
                                name="wishTypes" id="wishTypes" multiple size="7">
                            <option value="all">Все пожелания</option>
                            <#if wishes?has_content>
                                <#list wishes as wish>
                            <option value="${wish.type}">${wish.name}</option>
                                </#list>
                            <#else>
                            </#if>
                        </select>
                        <input type="submit" value="Сгенерировать пожелания" class="btn btn-info w-100">

                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
