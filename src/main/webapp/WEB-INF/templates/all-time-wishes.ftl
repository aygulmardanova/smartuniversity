<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset='UTF-8'>
    <title> Все пожелания ко времени </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
</head>

<body>
<div class="container shadow p-0">
    <nav class="navbar navbar-dark bg-info">
        <a class="navbar-brand" href="#">
            Все пожелания
        </a>
    </nav>

    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" href="/view/students">Студенты</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/view/teachers">Преподаватели</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="/view/time-wishes">Ко времени</a>
        </li>
    </ul>
    <div class="dropright m-3">
        <button class="btn btn-info dropdown-toggle rounded-0" type="button" data-toggle="dropdown">
            Ко времени
            <span class="caret"></span>
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/view/time-wishes?filter=start">первой пары</a>
            <a class="dropdown-item" href="/view/time-wishes?filter=end">последней пары</a>
        </div>
    </div>
    <#if timeWishes?has_content>
    <table class="table table-striped m-0 mt-3">
        <tr>
            <th>Первое занятие</th>
            <th>Последнее занятие</th>
            <th>День недели</th>
            <th>Занятие</th>
            <th></th>
        </tr>
        <#list timeWishes as timeWish>
            <tr>
                <td>
                    <#if timeWish.pairStNum??>
                    <#if timeWish.pairStNum == 1>
                        ${timeWish.pairStNum}: 8.30 - 10.00
                    </#if>
                    <#if timeWish.pairStNum == 2>
                        ${timeWish.pairStNum}: 10.10 - 11.40
                    </#if>
                    <#if timeWish.pairStNum == 3>
                        ${timeWish.pairStNum}: 11.50 - 13.20
                    </#if>
                    <#if timeWish.pairStNum == 4>
                        ${timeWish.pairStNum}: 13.40 - 15.10
                    </#if>
                    <#if timeWish.pairStNum == 5>
                        ${timeWish.pairStNum}: 15.20 - 16.50
                    </#if>
                    <#if timeWish.pairStNum == 6>
                        ${timeWish.pairStNum}: 17.00 - 18.30
                    </#if>
                            <#if timeWish.pairStNum == 7>
                                ${timeWish.pairStNum}: 18.40 - 20.10
                            </#if>
                    </#if>
                </td>
                <td>
            <#if timeWish.pairEndNum??>
<#if timeWish.pairEndNum == 1>
    ${timeWish.pairEndNum}: 8.30 - 10.00
</#if>
                <#if timeWish.pairEndNum == 2>
                    ${timeWish.pairEndNum}: 10.10 - 11.40
                </#if>
                <#if timeWish.pairEndNum == 3>
                    ${timeWish.pairEndNum}: 11.50 - 13.20
                </#if>
                <#if timeWish.pairEndNum == 4>
                    ${timeWish.pairEndNum}: 13.40 - 15.10
                </#if>
                <#if timeWish.pairEndNum == 5>
                    ${timeWish.pairEndNum}: 15.20 - 16.50
                </#if>
                <#if timeWish.pairEndNum == 6>
                    ${timeWish.pairEndNum}: 17.00 - 18.30
                </#if>
                        <#if timeWish.pairEndNum == 7>
                            ${timeWish.pairEndNum}: 18.40 - 20.10
                        </#if>
            </#if>
                </td>
                <td class="w-20">
            <#if timeWish.weekDay??>
                <#if timeWish.weekDay == 1>
                    Понедельник
                </#if>
                <#if timeWish.weekDay == 2>
                    Вторник
                </#if>
                <#if timeWish.weekDay == 3>
                    Среда
                </#if>
                <#if timeWish.weekDay == 4>
                    Четверг
                </#if>
                <#if timeWish.weekDay == 5>
                    Пятница
                </#if>
                <#if timeWish.weekDay == 6>
                    Суббота
                </#if>
            </#if>
                </td>
                <td class="w-40">
            <#if timeWish.subject??>
                ${timeWish.subject.name}
            </#if>
                </td>
                <td>
                    <button class="btn btn-info rounded-0" type="button">
                        Удалить
                    </button>
                </td>
            </tr>
        </#list>
    </table>
    <#else>
    <p>Нет пожеланий ко времени</p>
    </#if>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
        crossorigin="anonymous"></script>
</body>