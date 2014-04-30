<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Lista de projetos</h1>
	<p>Listagem de projetos cadastrados.</p>





	<table border="1px" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th width="10%">id</th>
				<th width="15%">name</th>
				<th width="5%"></th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="projeto" items="${projetos}">
				<tr>
					<td>${projeto.id}</td>
					<td>${projeto.nome}</td>
					<td><a href="<c:url value="/${projeto.id}/editarProjeto" ></c:url>">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table>
</body>
</html>