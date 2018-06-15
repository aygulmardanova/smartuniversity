<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset='UTF-8'>
    <title> Преподаватели </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
</head>

<body>
<div class="container">
    <nav class="navbar navbar-dark bg-info">
        <a class="navbar-brand" href="#">
            Пожелания
        </a>
    </nav>

    <div class="border">
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link" href="/students">Студенты</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/teachers">Преподаватели</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/time-wishes">Пожелания ко времени</a>
            </li>
        </ul>
    <#if success_msg??>
    <div class="alert alert-success m-3" role="alert">
        Пожелание успешно сохранено!
    </div>
    </#if>
        <div class="row justify-content-center m-3">

        <#if teachers?has_content>
            <table class="table table-striped m-0">
            <#list teachers as teacher>
                <tr class="border">
                    <td>
                        <img src="../../resources/images/no_photo.jpg" width="100" height="150">
                    </td>
                    <td>
                        <p>${teacher.surname} ${teacher.name} ${teacher.patronymic}</p>
                    </td>
                    <td>
                        <div style="display: flex;">
                <#if (teacher.userInterests)??>
                    <ul>
                    <#list teacher.userInterests as userInterest>
                        <li>${userInterest.interest.name}</li>
                    </#list>
                    </ul>
                </#if>
                    </td>
                    <td class="w-50 rounded-0">
                        <form action="/saveWish" method="post">
                            <input type="hidden" name="user_from_id" value="${user.id}">
                            <input type="hidden" name="user_to_id" value="${teacher.id}">
                            <input type="submit" value="Хочу учиться у ${teacher.name} ${teacher.patronymic}"
                                   class="btn btn-info w-100 rounded-0">
                            <select class="w-100 bg-none border-0 overflow-hidden non-shadow custom-select" name="subjects" id="subjects" multiple size="${teacher.userSubjs?size}">
                        <#if (teacher.userSubjs)??>
                            <#list teacher.userSubjs as userSubj>
                                <option value="${userSubj.subject.name}">${userSubj.subject.name}</option>
                            </#list>
                        <#else>
                        </#if>
                            </select>
                        </form>
                    </td>
                </tr>
            </#list>
            </table>
        </#if>
        </div>
    </div>
</div>
</body>