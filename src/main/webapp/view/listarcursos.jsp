<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Listar Cursos</title>
</head>

<body>
<h1>List of Courses</h1>
<ul>
  <c:forEach items="${cursos}" var="curso">
    <li>${curso.nome} - ${curso.descricao}</li>
  </c:forEach>
</ul>
</body>

</html>
