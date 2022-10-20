<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <title>Document</title>
    <style>
        body,
        .signin {
            background-color: #d3d3d3;
            font-family: 'Montserrat', sans-serif;
            color: white;
            font-size: 14px;
            letter-spacing: 1px;
        }

        .login {
            position: relative;
            height: 560px;
            width: 405px;
            margin: auto;
            padding: 60px 60px;
            background: url(https://noithatmanhhe.vn/media/28439/thiet-ke-noi-that-phong-ngu-mau-den-dep-huyen-ao.jpg) no-repeat   center center #505050;
            background-size: cover;
            box-shadow: 0px 30px 60px -5px #000;
        }

        form {
            padding-top: 80px;
        }

        .active {
            border-bottom: 2px solid #1161ed;
        }

        .nonactive {
            color: rgba(255, 255, 255, 0.2);
        }

        h2 {
            padding-left: 12px;
            font-size: 22px;
            text-transform: uppercase;
            padding-bottom: 5px;
            letter-spacing: 2px;
            display: inline-block;
            font-weight: 100;
        }

        h2:first-child {
            padding-left: 0px;
        }

        span {
            text-transform: uppercase;
            font-size: 12px;
            opacity: 0.4;
            display: inline-block;
            position: relative;
            top: -65px;
            transition: all 0.5s ease-in-out;
        }

        .text {
            border: none;
            width: 89%;
            padding: 10px 20px;
            display: block;
            height: 15px;
            border-radius: 20px;
            background: rgba(240, 232, 232, 0.1);
            border: 2px solid rgba(255, 255, 255, 0);
            overflow: hidden;
            margin-top: 15px;
            transition: all 0.5s ease-in-out;
        }

        .text:focus {
            outline: 0;
            border: 2px solid rgba(236, 229, 229, 0.5);
            border-radius: 20px;
            background: rgba(0, 0, 0, 0);
        }

        .text:focus + span {
            opacity: 0.6;
        }

        input[type="text"],
        input[type="password"] {
            font-family: 'Montserrat', sans-serif;
            color: white;
        }



        input {
            display: inline-block;
            padding-top: 20px;
            font-size: 14px;
        }

        h2,
        span,
        .custom-checkbox {
            margin-left: 20px;
        }

        .custom-checkbox {
            -webkit-appearance: none;
            background-color: rgba(255, 255, 255, 0.1);
            padding: 8px;
            border-radius: 2px;
            display: inline-block;
            position: relative;
            top: 6px;
        }

        .custom-checkbox:checked {
            background-color: rgba(17, 97, 237, 1);
        }

        .custom-checkbox:checked:after {
            content: '\2714';
            font-size: 10px;
            position: absolute;
            top: 1px;
            left: 4px;
            color: #fff;
        }

        .custom-checkbox:focus {
            outline: none;
        }

        label {
            display: inline-block;
            padding-top: 10px;
            padding-left: 5px;
        }

        .signin {
            background-color: #1161ed;
            color: white;
            width: 100%;
            padding: 10px 20px;
            display: block;
            height: 39px;
            border-radius: 20px;
            margin-top: 30px;
            transition: all 0.5s ease-in-out;
            border: none;
            text-transform: uppercase;
        }

        .signin:hover {
            background: #4082f5;
            box-shadow: 0px 4px 35px -5px #4082f5;
            cursor: pointer;
        }

        .signin:focus {
            outline: none;
        }

        hr {
            border: 1px solid rgba(209, 198, 198, 0.1);
            top: 85px;
            position: relative;
        }

        a {
            text-align: center;
            display: block;
            top: 120px;
            position: relative;
            text-decoration: none;
            color: rgba(188, 176, 176, 0.2);
        }

    </style>
</head>
<body>


<div class="login">
    <h2 class="active"> sign in </h2>

    <h2 class="nonactive"> sign up </h2>
    <form method="post" action="/login?option=user" id="loginForm" name="userLogin">

        <input type="username" class="text" name="username" id="username">
        <span>username</span>

        <br>

        <br>

        <input type="password" class="text" name="password" id="password">
        <span>password</span>
        <br>

       <div class="checkbox">
           <input type="checkbox" id="rememberMeCheckBox">
           <label for="rememberMeCheckBox">Keep me Signed in</label>
       </div>

        <button class="signin" value="login" type="submit">
            Sign In
        </button>


        <hr>
        <div style="color: red"> <h2>${message}</h2></div>
        <a href="#">Forgot Password?</a>
    </form>

</div>
</body>
</html>