<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="./modulos/header.jsp" />
	<div class="container">
		<h1>Gestão de Projetos Acadêmicos</h1>

		<a href="listar"><button class="btn btn-primary">Listar Projetos  <span class="glyphicon glyphicon-list"></span></button></a>
<!-- sec:authorize USADO PARA LIMITAR UMA OPÇÃO DA PAGINA PARA UM PAPEL		 -->
	<sec:authorize  ifAnyGranted="ROLE_COORDENADOR">
		<a href="cadastro"><button class="btn btn-primary">Cadastrar Projeto  <span class="glyphicon glyphicon-plus"></span></button></a>	
	</sec:authorize>
		
		<a href="<c:url value='j_spring_security_logout' />" ><button class="btn btn-primary">Sair  <span class="glyphicon glyphicon-off"></span></button></a>
		
	</div>

<jsp:include page="./modulos/footer.jsp" />
</html>