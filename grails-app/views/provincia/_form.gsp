












<%@ page import="it.algos.myvitaback.Provincia" %>



<div class="fieldcontain ${hasErrors(bean: provinciaInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="provincia.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${provinciaInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provinciaInstance, field: 'sigla', 'error')} ">
	<label for="sigla">
		<g:message code="provincia.sigla.label" default="Sigla" />
		
	</label>
	









<g:textField name="sigla" value="${provinciaInstance?.sigla}"/>
</div>

