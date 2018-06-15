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
            Пожелания
        </a>
    </nav>

    <div class="border">
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
        <div class="row justify-content-center m-5">
            <div class="col-8">
                <form action="/save-time-wish" method="post" class="w-100">
                    <fieldset>
                        <legend class="h3 w-100 text-center">Пожелания ко времени</legend>

                        <div class="alert alert-success" role="alert">
                            Успешно!
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend w-50">
                                <label class="input-group-text w-100" for="pair_st_num">Время начала занятий</label>
                            </div>
                            <select class="custom-select" name="pair_st_num" id="pair_st_num">
                                <option selected label="Не выбрано"></option>
                            <#list pairNums as pairNum>
                            <option value="${pairNum.id}">${pairNum.id}: ${pairNum.pairStTime}
                                - ${pairNum.pairEndTime}</option>
                            </#list>
                            </select>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend w-50">
                                <label class="input-group-text w-100" for="pair_end_num">Время окончания занятий</label>
                            </div>
                            <select class="custom-select" name="pair_end_num" id="pair_end_num">
                                <option selected  label="Не выбрано"></option>
                            <#list pairNums as pairNum>
                            <option value="${pairNum.id}">${pairNum.id}: ${pairNum.pairStTime}
                                - ${pairNum.pairEndTime}</option>
                            </#list>
                            </select>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend w-50">
                                <label class="input-group-text w-100" for="week_day">День недели</label>
                            </div>
                            <select class="custom-select" name="week_day" id="week_day">
                                <option selected  label="Не выбрано"></option>
                                <option value="1">Понедельник</option>
                                <option value="2">Вторник</option>
                                <option value="3">Среда</option>
                                <option value="4">Четверг</option>
                                <option value="5">Пятница</option>
                                <option value="6">Суббота</option>
                                <option value="7">Воскресенье</option>
                            </select>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend w-50">
                                <label class="input-group-text w-100" for="subject_id">Занятие</label>
                            </div>
                            <select class="custom-select" name="subject_id" id="subject_id">
                                <option selected label="Не выбрано"></option>
                            <#if subjects?has_content>
                                <#list subjects as subject>
                            <option value="${subject.id}">${subject.name}</option>
                                </#list>
                            <#else>
                            </#if>
                            </select>
                        </div>


                        <input type="hidden" name="user_id" value="${user.id}"/>
                        <input  type="submit" value="Сохранить пожелание ко времени" class="btn btn-info w-100 border">
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>