












<%@ page import="it.algos.myvitaback.Stato" %>



<div class="fieldcontain ${hasErrors(bean: statoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="stato.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${statoInstance?.nome}"/>
</div>

