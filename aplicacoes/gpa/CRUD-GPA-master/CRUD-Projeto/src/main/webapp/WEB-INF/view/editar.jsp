<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!DOCTYPE html>

	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="utf-8">
<script src="<c:url value="/webjars/jquery/2.1.0/jquery.min.js" />"></script>
<script
	src="<c:url value="/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.min.js" />"></script>
<script
	src="<c:url value="/webjars/jquery-validation/1.12.0/jquery.validate.min.js" />"></script>
</head>

<body>
	<div class="novo-projeto" align="left">
		<div class="form" align="center">
			<h2>Cadastrar Projeto</h2>
			
			<form:form commandName="editarProjeto" id="reg"
				action="editarProjetoForm">
				<h2>Editar Projeto</h2>

				<form:label path="id">ID:</form:label>
				<br>
				<form:input path="id" disabled="true" />
				<br>

				<form:label path="nome">Nome:</form:label>
				<br>
				<form:input path="nome" />
				<br>
				<form:errors class="invalid" path="nome" />

				<form:label path="status">Status:</form:label>
				<br>
				<form:input path="status" />
				<br>
				
				<form:label path="descricao">Descrição:</form:label>
				<br>
				<form:input path="descricao" />
				<br>
				
				<form:label path="atividades">Atividades:</form:label>
				<br>
				<form:input path="atividades" />
				<br>
				
				<form:label path="participantes">Participantes:</form:label>
				<br>
				<form:input path="participantes" />
				<br>
				
				
				<form:label path="numero_bolsas">Número de Bolsas:</form:label>
				<br>
				<form:input path="numero_bolsas" />
				<br>
				
				<form:label path="inicio">Início:</form:label>
				<br>
				<form:input path="inicio" />
				<br>
				
				<form:label path="termino">Término:</form:label>
				<br>
				<form:input path="termino" />
				<br>


				<input type="submit" value="Submit" />

			</form:form>
		</div>
	</div>
</body>

</html>

</body>
</html>