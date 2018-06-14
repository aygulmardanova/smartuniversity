<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset='UTF-8'>
    <title> Студенты </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            Пожелания
        </a>
    </nav>

    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="/students">Студенты</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/teachers">Преподаватели</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/time-wishes">Пожелания ко времени</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/auto-generate">Авто-генерация</a>
        </li>
    </ul>
    <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
            Сортировка
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <li><a href="/students?sort=fio">По алфавиту</a></li>
            <li class="divider"></li>
            <li><a href="/students?sort=similarity">По совместимости</a></li>
        </ul>
    </div>
    <#if students?has_content>
    <table class="table table-striped border">
        <#list students as student>
            <tr>
                <td>
                    <img src="../../resources/images/no_photo.jpg" width="100" height="150">
                </td>
                <td>
                    <p>${student.surname} ${student.name} ${student.patronymic}</p>
                </td>
                <td>
                    <div style="display: flex;">
                <#if (student.userInterests)??>
                    <ul>
                    <#list student.userInterests as userInterest>
                        <li>${userInterest.interest.name}</li>
                    </#list>
                    </ul>
                </#if>
                </td>
                <td>
                    <form action="/saveWish" method="post">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <input type="hidden" name="student_id" value="${student.id}">
                        <input type="submit" value="Хочу учиться с ${student.surname} ${student.name}"
                               class="btn btn-secondary w-100">
                        <select class="w-100" name="subjects" id="subjects" multiple>
                        <#if (student.iupSubjs)??>
                            <option></option>
                            <#list student.iupSubjs as iupSubj>
                                <option value="${iupSubj.subject.name}">${iupSubj.subject.name}</option>
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