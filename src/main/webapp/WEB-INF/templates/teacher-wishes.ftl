<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset='UTF-8'>
    <title> Пожелания к преподавателям </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
</head>

<body>
<div class="container shadow p-0 w-75">
    <nav class="navbar navbar-dark bg-info">
        <a class="navbar-brand" href="#">
            Просмотр всех пожеланий
        </a>
    </nav>

    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" href="/view/students">Студенты</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="/view/teachers">Преподаватели</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/view/time-wishes">Ко времени</a>
        </li>
    </ul>

    <#if toTeachWishes?has_content>
    <table class="table table-striped m-0 mt-3">
        <tr>
            <th>Фото преподавателя</th>
            <th>ФИО преподавателя</th>
            <th>Занятие</th>
            <th></th>
        </tr>
        <#list toTeachWishes as toTeachWish>
            <tr>
                <td style="width: 15%">
                    <img src="../../resources/images/no_photo.jpg" width="100" height="125">
                </td>
                <td class="w-35">
                    <p>${toTeachWish.teachUser.surname} ${toTeachWish.teachUser.name} ${toTeachWish.teachUser.patronymic}</p>
                </td>
                <td class="w-50">
                    <#if toTeachWish.subject??>
                        <p>${toTeachWish.subject.name}</p>
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