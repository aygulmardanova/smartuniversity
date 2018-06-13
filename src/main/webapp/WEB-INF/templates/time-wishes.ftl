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
            <a class="nav-link" href="/teachers">Преподаватели</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="/time-wishes">Пожелания ко времени</a>
        </li>
    </ul>
    <div class="border">
        <div class="col-6">
            <form action="/save-time-wish" method="post" class="w-100">
                <fieldset>
                    <legend>Пожелания ко времени</legend>

                    <label for="pair_st_num">Выберите время начала первого занятия</label>
                    <select name="pair_st_num" id="pair_st_num">
                        <option selected></option>
                        <option value="1">1: 8.30-10.00</option>
                        <option value="2">1: 10.10-11.40</option>
                        <option value="3">1: 11.50-13.20</option>
                        <option value="4">1: 13.35-15.05</option>
                        <option value="5">1: 15.20-16.50</option>
                        <option value="6">1: 17.00-18.30</option>
                        <option value="7">1: 18.40-20.10</option>
                    </select>

                    <label for="pair_end_num">Выберите время окончания последнего занятия</label>
                    <select name="pair_end_num" id="pair_end_num">
                        <option selected></option>
                        <option value="1">1: 8.30-10.00</option>
                        <option value="2">1: 10.10-11.40</option>
                        <option value="3">1: 11.50-13.20</option>
                        <option value="4">1: 13.35-15.05</option>
                        <option value="5">1: 15.20-16.50</option>
                        <option value="6">1: 17.00-18.30</option>
                        <option value="7">1: 18.40-20.10</option>
                    </select>

                    <label for="week_day">Выберите день недели</label>
                    <select name="week_day" id="week_day">
                        <option selected></option>
                        <option value="1">Понедельник</option>
                        <option value="2">Вторник</option>
                        <option value="3">Среда</option>
                        <option value="4">Четверг</option>
                        <option value="5">Пятница</option>
                        <option value="6">Суббота</option>
                        <option value="7">Воскресенье</option>
                    </select>

                    <br/>
                    <label for="subject_id">Выберите занятие</label>
                    <select name="subject_id" id="subject_id">
                        <option selected></option>
                    <#if subjects?has_content>
                        <#list subjects as subject>
                            <option value="${subject.id}">${subject.name}</option>
                        </#list>
                    <#else>
                    </#if>
                    </select>

                    <input type="hidden" id="user_id" value="${user.id}"/>
                    <input type="submit" value="Сохранить пожелание ко времени" class="btn btn-secondary w-100">
                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
