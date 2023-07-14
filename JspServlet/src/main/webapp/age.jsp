<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Age Calculator</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        background-image: url("bg.jpg");
    }
    input[type=date] {
        width: 90%;
        padding: 12px 20px;
        margin: 8px 0;
        box-sizing: border-box;
        border: 3px solid black;
    }

    button[type=submit] {
        font-weight: bolder;
        width: 90%;
        border: 3px solid black;
        text-align: center;
        background-color: #4CAF50;
        color: black;
        font-size: 1.5rem;
        padding: 12px 20px;
        margin: 8px 0;
        cursor: pointer;
    }

    button[type=submit]:hover {
        background-color: #45a049;
    }

    #container {
        width: 50%;
        margin: 0 auto;
        text-align: center;
    }

    #header {

        font-size: 2.5rem;
        text-align: center;
    }

    .result {
        width: 90%;
        font-size: 2.5rem;
        text-align: center;
        margin: auto;
        background-color: aqua;
        border-right: 3px solid black;
        border-left: 3px solid black;
        border-bottom: 3px solid black;
    }

    #result-header {
        padding-bottom: 7px;
        padding-top: 7px;
        font-weight: bolder;
        width: 90%;
        background-color: #45a049;
        border: 3px solid black;
        font-size: 2.5rem;
        text-align: center;
        margin: auto;
    }
    #error-result-header {
        padding-bottom: 7px;
        padding-top: 7px;
        font-weight: bolder;
        width: 90%;
        background-color: red;
        border: 3px solid black;
        font-size: 200%;
        text-align: center;
        margin: auto;
    }
    #result-container {
        width: 50%;
        margin: 0 auto;
        text-align: center;
    }

    label {
        font-size: 2rem;
    }
</style>
<body>
<h1 id="header">Calculate your age</h1>
<div id="container">
    <form action="${pageContext.request.contextPath}/calculate-age">
        <label for="dob">Date of birth</label>
        <input name="dob" id="dob" type="date">
        <br>
        <label for="age-at">Age at the date of</label>
        <input name="age-at" id="age-at" type="date">
        <br>
        <button type="submit">Calculate</button>
    </form>
</div>
</body>
</html>