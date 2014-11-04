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


		<table>
			<tr>
				<th>Relatorios</th>
			</tr>
			<tr>
				<td><a href="<c:url value="/projeto/relatorioEmAndamento"/>" > Projetos em Andamento</a></td>
			</tr>
			<tr>
				<td>Quantidade de Bolsa</td>
			</tr>
		</table>

	</div>

	<jsp:include page="../modulos/footer.jsp"></jsp:include>
</body>
</html>