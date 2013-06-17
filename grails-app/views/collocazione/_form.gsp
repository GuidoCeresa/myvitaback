












<%@ page import="it.algos.myvitaback.Collocazione" %>



<div class="fieldcontain ${hasErrors(bean: collocazioneInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="collocazione.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${collocazioneInstance?.nome}"/>
</div>

