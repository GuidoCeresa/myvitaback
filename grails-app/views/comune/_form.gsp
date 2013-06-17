












<%@ page import="it.algos.myvitaback.Comune" %>



<div class="fieldcontain ${hasErrors(bean: comuneInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="comune.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${comuneInstance?.nome}"/>
</div>

