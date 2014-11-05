<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="header-page">
	<div class="row">
		<div class="col-md-6">
			<img width="370"
				src="<c:url value="/resources/images/brasao-qxd.png" />"
				alt="Brasão UFC Quixadá">
		</div>
		<div class="col-md-6"></div>
	</div>
</div>
<div>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/projeto/index" />">GPA - Pesquisa</a>
			</div>
	
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li>
						<a href="<c:url value="/projeto/listar" />">Projetos <span class="glyphicon glyphicon-list"></span></a>
					</li>
					<li>
						<a href="<c:url value="/projeto/cadastrar" />">Novo Projeto <span class="glyphicon glyphicon-plus"></span></a>
					</li>
					<li>
                       <a href="<c:url value="/projeto/relatorios" />">Relatórios <span class="glyphicon glyphicon-list-alt"></span></a>
                    </li>
					<li>
						<a href="<c:url value="/j_spring_security_logout" />">Sair <span class="glyphicon glyphicon-off"></span></a>
					</li>
					
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a style="font-weight: bold;">Bem vindo, ${sessionScope.usuario.nome}!</a> 
					</li>
				</ul>
			</div>
		</div>
	</nav>
</div>