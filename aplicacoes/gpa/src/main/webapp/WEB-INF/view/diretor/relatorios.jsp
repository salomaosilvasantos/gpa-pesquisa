<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<jsp:include page="../modulos/header-estrutura.jsp" />
<title>Relatórios</title>
</head>


<body>
	<jsp:include page="../modulos/header.jsp" />
	<div class="container">


		<ul>
			<li><a href="<c:url value="/projeto/relatorioEmAndamento"/>">
					Projetos em Andamento</a></li>
			<li>Quantidade de Bolsa</li>
			<li><a href="<c:url value="/projeto/relatorioBolsaProjeto" />">Bolsa por Projeto</a></li>
		</ul>


	</div>

	<jsp:include page="../modulos/footer.jsp"></jsp:include>
</body>
</html>