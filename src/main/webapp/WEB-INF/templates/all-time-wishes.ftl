<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset='UTF-8'>
    <title> Студенты </title>
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
            <th>Начало занятий</th>
            <th>Окончание занятий</th>
            <th>День недели</th>
            <th>Занятие</th>
            <th></th>
        </tr>
        <#list timeWishes as timeWish>
            <tr>
                <td>
                    <#if timeWish.pairStNum??>
                        ${timeWish.pairStNum}
                    </#if>
                </td>
                <td>
            <#if timeWish.pairEndNum??>
                ${timeWish.pairEndNum}
            </#if>
                </td>
                <td class="w-35">
            <#if timeWish.weekDay??>
                ${timeWish.weekDay}
            </#if>
                </td>
                <td class="w-40">
            <#if timeWish.subject??>
                ${timeWish.subject.name}
            </#if>
                </td>
                <td>
            <p>Удалить</p>
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