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
            <a class="nav-link" href="/students">Студенты</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/teachers">Преподаватели</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="/time-wishes">Пожелания ко времени</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/auto-generate">Авто-генерация</a>
        </li>
    </ul>
    <div class="border">
        <div class="col-6">
            <form action="/save-time-wish" method="post" class="w-100">
                <fieldset>
                    <legend>Пожелания ко времени</legend>

                    <label for="pair_st_num">Время начала занятий</label>
                    <select name="pair_st_num" id="pair_st_num">
                        <option selected></option>
                    <#list pairNums as pairNum>
                        <option value="${pairNum.id}">${pairNum.id}: ${pairNum.pairStTime}
                            - ${pairNum.pairEndTime}</option>
                    </#list>
                    </select>

                    <label for="pair_end_num">Время окончания занятий</label>
                    <select name="pair_end_num" id="pair_end_num">
                        <option selected></option>
                    <#list pairNums as pairNum>
                        <option value="${pairNum.id}">${pairNum.id}: ${pairNum.pairStTime}
                            - ${pairNum.pairEndTime}</option>
                    </#list>
                    </select>

                    <label for="week_day">День недели</label>
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
                    <label for="subject_id">Занятие</label>
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
