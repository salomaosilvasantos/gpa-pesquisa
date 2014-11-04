<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<jsp:include page="modulos/header-estrutura.jsp" />
<title>Problema no servidor</title>
</head>
<body>
    <jsp:include page="modulos/header.jsp" />

<div class = "error">
    <h1>Oops, o site teve um problema técnico.</h1>
    <a class ="errorback" href="/gpa-pesquisa/projeto/index">Voltar ao início</a>
</div>

    <jsp:include page="modulos/footer.jsp" />
</body>
</html>

