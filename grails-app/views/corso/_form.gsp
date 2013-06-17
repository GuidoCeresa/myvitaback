












<%@ page import="it.algos.myvitaback.Corso" %>



<div class="fieldcontain ${hasErrors(bean: corsoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="corso.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${corsoInstance?.nome}"/>
</div>

