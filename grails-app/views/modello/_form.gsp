












<%@ page import="it.algos.myvitaback.Modello" %>



<div class="fieldcontain ${hasErrors(bean: modelloInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="modello.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${modelloInstance?.nome}"/>
</div>

