












<%@ page import="it.algos.myvitaback.Disponibilita" %>



<div class="fieldcontain ${hasErrors(bean: disponibilitaInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="disponibilita.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${disponibilitaInstance?.nome}"/>
</div>

