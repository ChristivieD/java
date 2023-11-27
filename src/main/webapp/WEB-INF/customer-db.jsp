<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
  <title>Customers List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous">
</head>
<body>
<div class="container">
  <h1>Customers</h1>
  <p class="lead">
    There ${customers.size() == 1 ? "is" : "are"} ${customers.size()} customer${customers.size() != 1 ? "s" : ""}
  </p>
  <c:if test="${customers.size() > 0}">
  <div class="table-responsive">
    <table class="table">
      <thead>
      <tr>
        <th scope="col">ID</th>
        <th scope="col">First</th>
        <th scope="col">Last</th>
        <th scope="col">Email</th>
        <th scope="col">PhoneNumber</th>
        <th scope="col">Password</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${customers}" var="customer">
        <tr>
          <th scope="row">${customer.id}</th>
          <td>${customer.firstName}</td>
          <td>${customer.lastName}</td>
          <td>${customer.email}</td>
          <td>${customer.phoneNumber}</td>
          <td>${customer.password}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    </c:if>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
          crossorigin="anonymous">
  </script>
</body>
</html>
