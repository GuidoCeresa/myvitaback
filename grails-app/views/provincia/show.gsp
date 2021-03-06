













<%@ page import="it.algos.myvitaback.Provincia" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'provincia.label', default: 'Provincia')}" />
    <title><g:message code="provincia.show.label" args="[entityName]" default="Mostra"/></title>
</head>
<body>
<a href="#show-provincia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Home"/></a></li>
        <li><g:link class="list" action="list"><g:message code="provincia.list.label" args="[entityName]" default="Elenco"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="provincia.new.label" args="[entityName]" default="Nuovo"/></g:link></li>
    </ul>
</div>
<div id="show-provincia" class="content scaffold-show" role="main">
    <h1><g:message code="provincia.show.label" args="[entityName]" default="Mostra"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list provincia">
        
        <g:if test="${provinciaInstance?.nome}">
            <li class="fieldcontain">
                <span id="nome-label" class="property-label"><g:message code="provincia.nome.label" default="Nome" /></span>
                
                <span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${provinciaInstance}" field="nome"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${provinciaInstance?.sigla}">
            <li class="fieldcontain">
                <span id="sigla-label" class="property-label"><g:message code="provincia.sigla.label" default="Sigla" /></span>
                
                <span class="property-value" aria-labelledby="sigla-label"><g:fieldValue bean="${provinciaInstance}" field="sigla"/></span>
                
            </li>
        </g:if>
        
    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${provinciaInstance?.id}" />
            <g:link class="edit" action="edit" id="${provinciaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
