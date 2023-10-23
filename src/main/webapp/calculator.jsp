<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%-- JSP Comments will not appear the browser--%>
<!-- HTML Comments will appear in the browser-->

<%-- This is called a page directive. Use to import java files--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%-- Scriplets contain Java code embedded within HTML --%>

<html>
<head>
    <title>My Calculator App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container my-4">
    <div class="row">
        <div class="col-6">
            <h1>Add</h1>
            <p class="lead">Enter two numbers and press submit to calculate the sum.</p>
            <form method="POST" action="calculator">
                <div class="form-group mb-2">
                    <label for="firstNumber">First Number:</label>
                    <input name="num1" value="${results.get("num1")}" type="text" class="form-control" id="firstNumber">
                </div>
                <div class="form-group mb-2">
                    <label for="secondNumber">Second Number:</label>
                    <input name="num2" value="${results.get("num2")}" type="text" class="form-control" id="secondNumber">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <%-- Java Expressions are single line statements of Java embedded within HTML --%>
<%--            <c:if test ="${results.containsKey('sum')}">--%>
<%--                <p><c: out value="${results.sum}" /></p>--%>
<%--            </c:if>--%>
<%--            <c:if test ="${results.containsKey('invalidNumber')}">--%>
<%--                <p><c: out value="${results.invalidNumber}" /></p>--%>
<%--            </c:if>--%>
            <p>${results.get("sum")}</p>
            <p>${results.get("invalidNumber")}</p>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
