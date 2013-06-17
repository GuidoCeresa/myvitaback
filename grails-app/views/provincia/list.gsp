













<%@ page import="it.algos.myvitaback.Provincia" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'provincia.label', default: 'Provincia')}"/>
    <title><g:message code="provincia.list.label" args="[entityName]" default="Elenco"/></title>
</head>

<body>
<a href="#list-provincia" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Home"/></a></li>
        <li><g:link class="create" action="create"><g:message code="provincia.new.label"
                                                              args="[entityName]" default="Nuovo"/></g:link></li>
    </ul>
</div>

<div id="list-provincia" class="content scaffold-list" role="main">
    <h1><g:message code="provincia.list.label" args="[entityName]" default="Elenco"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>
            
            <g:sortableColumn property="nome"
                              title="${message(code: 'provincia.nome.label', default: 'Nome')}"/>
            
            <g:sortableColumn property="sigla"
                              title="${message(code: 'provincia.sigla.label', default: 'Sigla')}"/>
            
        </tr>
        </thead>
        <tbody>
        <g:each in="${provinciaInstanceList}" status="i" var="provinciaInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                
                <td><g:link action="show"
                            id="${provinciaInstance.id}">${fieldValue(bean: provinciaInstance, field: "nome")}</g:link></td>
                
                <td><g:link action="show"
                            id="${provinciaInstance.id}">${fieldValue(bean: provinciaInstance, field: "sigla")}</g:link></td>
                
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${provinciaInstanceTotal}"/>
    </div>
</div>
</body>
</html>
