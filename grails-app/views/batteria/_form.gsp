












<%@ page import="it.algos.myvitaback.Batteria" %>



<div class="fieldcontain ${hasErrors(bean: batteriaInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="batteria.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${batteriaInstance?.nome}"/>
</div>

