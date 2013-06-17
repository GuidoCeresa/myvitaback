












<%@ page import="it.algos.myvitaback.Elettrodo" %>



<div class="fieldcontain ${hasErrors(bean: elettrodoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="elettrodo.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${elettrodoInstance?.nome}"/>
</div>

