<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset='UTF-8'>
    <title> Преподаватели </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <!-- Image and text -->
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            Пожелания
        </a>
    </nav>

    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" href="/auto-generate">Авто-генерация</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="/teachers">Преподаватели</a>
        </li>
    </ul>
    <#if teachers?has_content>
    <table class="table table-striped border">
        <#list teachers as teacher>
            <tr>
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
                <td>
                    <form action="/saveWish" method="post">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <input type="hidden" name="teacher_id" value="${teacher.id}">
                        <input type="submit" value="Хочу учиться у ${teacher.name} ${teacher.patronymic}"
                               class="btn btn-secondary w-100">
                        <select class="w-100" name="subjects" id="subjects" multiple>
                        <#if (teacher.userSubjs)??>
                            <#list teacher.userSubjs as userSubj>
                                <option value="${userSubj.subject.name}">${userSubj.subject.name}</option>
                            </#list>
                        <#else>
                        </#if>
                        </select>
                    </form>
                    </div>
                </td>
            </tr>
        </#list>
    </table>
    </#if>
</div>
</body>