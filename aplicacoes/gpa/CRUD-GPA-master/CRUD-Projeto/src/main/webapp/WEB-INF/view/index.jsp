<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GPA</title>
</head>
<body>
	<div class="container">
		<h1>Gestão de Projetos Acadêmicos</h1>

		<a href="listar"><button class="btn btn-primary">Listar Projetos  <span class="glyphicon glyphicon-list"></span></button></a>
		<a href="cadastro"><button class="btn btn-primary">Cadastrar Projeto  <span class="glyphicon glyphicon-plus"></span></button></a>
	</div>
</body>
</html>