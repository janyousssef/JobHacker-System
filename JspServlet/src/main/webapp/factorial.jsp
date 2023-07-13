<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Factorial</title>
</head>
<body>
<h1 style="text-align: center;margin-bottom: auto;
    font-size: 70px;">Factorial
</h1>
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
    <table border="4px" style="position: relative; left:50%;bottom: 50%;font-size: xx-large;translate: -50%;text-align: center;
    width: 40%;">
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