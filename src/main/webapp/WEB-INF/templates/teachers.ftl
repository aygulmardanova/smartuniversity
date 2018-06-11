<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset='UTF-8'>
    <title> Преподаватели </title>
</head>

<body>
<form action="/generate" method="get">
    <fieldset>
        <legend>Auto generate</legend>
        <input type="submit" value="Сгенерировать пожелания">
    </fieldset>
</form>

<div style="color: black; font-size: 14pt; width: 100%; margin-left: auto; margin-right: auto;">

<#if teachers?has_content>
    <#list teachers as teacher>

        <div style="padding: 15px; border: 1px solid #ccc; border-bottom: none; border-top: none;">
            <div style="display: flex;">
                <img src="../../resources/images/no_photo.jpg" width="100" height="150">
                <p style="width: 300px; height: 150px">${teacher.surname} ${teacher.name} ${teacher.patronymic}</p>
                <#if (teacher.userInterests)??>
                    <ul style="width: 300px; height: 150px">
                    <#list teacher.userInterests as userInterest>
                        <li>${userInterest.interest.name}</li>
                    </#list>
                <#else>
                    </ul>
                </#if>
                <form action="/saveWish" method="post" style="width: 300px; height: 150px">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <input type="hidden" name="teacher_id" value="${teacher.id}">
                    <input type="submit" value="Хочу учиться у ${teacher.name} ${teacher.patronymic}" class="submit">
                    <select name="subjects" id="subjects" multiple>
                        <#if (teacher.userSubjs)??>
                            <#list teacher.userSubjs as userSubj>
                                <option value="${userSubj.subject.name}">${userSubj.subject.name}</option>
                            </#list>
                        <#else>
                        </#if>
                    </select>
                </form>

            </div>
        </div>

    <hr size=1px color="#ccc">
    </#list>
<#else>
</#if>

</div>
</body>