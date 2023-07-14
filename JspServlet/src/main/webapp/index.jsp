<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JobHackers Calculator</title>
</head>
<style>
    h1 {
        background-color: aqua;
        padding: 0;
        margin: 0;
        font-style: italic;
        font-size: 3rem;
        height: 3.3rem;

    }

    body {
        padding: 0;
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        text-align: center;
        background-image: url("bg.jpg");
    }

    #container {
        display: flex;
        flex-direction: column;
        margin: 0 auto;
        width: 50%;
        height: 15em;
        border: 3px solid #73AD21;
        justify-content: space-around;
    }

    #option1 {
        font-size: 2.25rem;
        padding-top: 2rem;
        flex-grow: 1;
        width: 100%;
        background-color: aqua;
    }

    #option2 {
        font-size: 2.25rem;
        padding-top: 2rem;
        flex-grow: 1;
        width: 100%;
        background-color: chocolate;
    }

    button {
        font-size: 2.5rem;
        background-color: rgba(0, 0, 0, 0);
        border: none;
        cursor: pointer;
    }
    button[id=btn1]:hover {
        background-color: #059191FF;
    }
    button[id=btn2]:hover {
        color: navajowhite;
        background-color: #672F0AFF;
    }
</style>
<body>
<h1>Welcome to The Job Hackers Calculator!</h1>
<h2>What would you like to do?</h2>
<div id="container">
    <div id="option1">
        <form action="${pageContext.request.contextPath}/factorial.jsp">
            <button id="btn1" type="submit" value="factorial.jsp">Calculate factorials</button>
        </form>
    </div>
    <div id="option2">
        <form action="${pageContext.request.contextPath}/age.jsp">
            <button id="btn2" type="submit" value="age.jsp">Calculate age</button>
        </form>
    </div>
</div>
</body>
</html>