












<%@ page import="it.algos.myvitaback.Categoria" %>



<div class="fieldcontain ${hasErrors(bean: categoriaInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="categoria.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${categoriaInstance?.nome}"/>
</div>

