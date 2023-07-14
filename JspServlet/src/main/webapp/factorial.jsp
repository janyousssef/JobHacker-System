<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Factorial</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
    }

    h1 {
        text-align: center;
        margin-bottom: auto;
        font-size: 70px;
    }

    div {
        position: absolute;
        width: 100%;
    }

    table {
        position: relative;
        left: 50%;
        bottom: 50%;
        font-size: xx-large;
        translate: -50%;
        text-align: center;
        width: 40%;
    }
</style>
<body>
<h1>Factorial</h1>
<br/>
<%!
    public int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
%>
<div style="position: absolute; width: 100%">
    <table border="4px">
        <tr>
            <td>Number</td>
            <td>Factorial</td>
        </tr>
        <% for (int i = 0; i <= 10; i++) { %>
        <tr>
            <td><%= i %>
            </td>
            <td><%= factorial(i) %>
            </td>
        </tr>
        <% } %>

    </table>
</div>

</body>
</html>