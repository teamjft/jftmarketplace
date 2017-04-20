<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Login Form</title>
</head>
<style>
    .error {
        color: red;
    }
</style>
<body>
<c:if test="${not empty error}">
    <h3 class="error">You have entered wrong credentials!!</h3>
</c:if>
<form  action='/market/j_spring_security_check' method='POST'>
    <table>
        <tr><td>User:</td><td><input type='text' name='j_username' value=''></td></tr>
        <tr><td>Password:</td><td><input type='password' name='j_password'/></td></tr>
        <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
    </table>
</form>

<button id="loginButton">Click Ajax Login Event</button>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script>
    $("#loginButton").click(function () {
        $.ajax({
            url: "http://localhost:9191/market/j_spring_security_check",
            method: 'POST',
            data : {
                "j_username": "ashutosh.ojha92@gmail.com",
                "j_password": "asdf"
            },
            success: function(result){
                console.log('succ.......ess', result)
            },
            error: function (error) {
                console.log('errroorrr', error)
            }
        });
    })
</script>

<script>
    $(document).ready(function(){
        console.log("hhhhhhhhhh");
    });
</script>
</body>
</html>
