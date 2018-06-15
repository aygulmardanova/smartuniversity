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
    <nav class="navbar navbar-dark bg-info">
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
    </ul>
    <div class="dropdown">
        <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown">
            Сортировка
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <li><a href="/students?sort=fio">По алфавиту</a></li>
            <li class="divider"></li>
            <li><a href="/students?sort=similarity">По совместимости</a></li>
        </ul>
    </div>
<#if success_msg??>
    <div class="alert alert-success" role="alert">
        Пожелание успешно сохранено!
    </div>
</#if>
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
                        <input type="hidden" name="user_from_id" value="${user.id}">
                        <input type="hidden" name="user_to_id" value="${student.id}">
                        <input type="submit" value="Хочу учиться с ${student.surname} ${student.name}"
                               class="btn btn-info w-100">
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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>