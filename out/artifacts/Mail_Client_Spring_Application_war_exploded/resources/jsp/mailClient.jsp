<%--
  Created by IntelliJ IDEA.
  User: gordo
  Date: 26.07.2019
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="static java.lang.System.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>Mail Client Post Form </title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />" type="text/css"/>
</head>
<body>
<div id="main">
    <h1> Mail Client get/post request through sessions with <br> SMTP Protocol</h1>
    <div class="form_box">
        <form action="${pageContext.request.contextPath}/MailClient" method="post" class="rf">

            <input type="radio" name="radio" class="radio" id="googleRadio" value="Google"> Google
            <input type="radio" name="radio" class="radio" id="yandexRadio" value="Yandex"> Yandex
            <input type="radio" name="radio" class="radio" id="mailRURadio" value="MailRU"> MailRU

            <label for="sender">E-mail Sender:</label>
            <input type="text" class="rfield" id="sender" name="sender"/>


            <label for="receiver">E-mail Receiver:</label>
            <input type="text" class="rfield" id="receiver" name="receiver"/>

            <label for="password">E-mail password:</label>
            <input type="password" class="rfield" id="password" name="password"/>


            <label for="subjectMessage">Message header:</label>
            <input type="text" class="rfield" id="subjectMessage" name="subjectMessage"/>

            <label for="bodyMessage">Body message:</label>
            <textarea class="rfield" id="bodyMessage" name="bodyMessage"
                      placeholder="Enter Body Message Here"></textarea>

            <input type="submit" class="btn_submit disabled" value="Отправить данные"/>
        </form>
    </div>
    <div class="clear"></div>
</div>
</body>
<script src="<c:url value='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js' />"></script>
<script src="<c:url value='/resources/js/required_fields.js' />"></script>
<script>
    (function ($) {
        $(document).ready(function () {
            $('input[name=radio]:radio').on('change', function () {
                if ($(this).val() === 'Google') {
                    $('#sender').val("<%= getProperty("gmailSender") %>");
                    $('#receiver').val("<%= getProperty("gmailSender") %>");
                    $('#password').val("<%= getProperty("ppassword") %>");
                    $('#subjectMessage').val("test");
                } else if ($(this).val() === 'Yandex') {
                    $('#sender').val("<%= getProperty("yandexMailSender") %>");
                    $('#receiver').val("<%= getProperty("yandexMailSender") %>");
                    $('#password').val("<%= getProperty("ppassword") %>");
                    $('#subjectMessage').val("test");
                } else if ($(this).val() === 'MailRU') {
                    $('#sender').val("<%= getProperty("mailRUSender") %>");
                    $('#receiver').val("<%= getProperty("mailRUSender") %>");
                    $('#password').val("<%= getProperty("ppassword") %>");
                    $('#subjectMessage').val("test");
                }
            });
        });
    })(jQuery);
</script>
</html>