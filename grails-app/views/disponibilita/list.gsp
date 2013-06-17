













<%@ page import="it.algos.myvitaback.Disponibilita" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'disponibilita.label', default: 'Disponibilita')}"/>
    <title><g:message code="disponibilita.list.label" args="[entityName]" default="Elenco"/></title>
</head>

<body>
<a href="#list-disponibilita" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Home"/></a></li>
        <li><g:link class="create" action="create"><g:message code="disponibilita.new.label"
                                                              args="[entityName]" default="Nuovo"/></g:link></li>
    </ul>
</div>

<div id="list-disponibilita" class="content scaffold-list" role="main">
    <h1><g:message code="disponibilita.list.label" args="[entityName]" default="Elenco"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>
            
            <g:sortableColumn property="nome"
                              title="${message(code: 'disponibilita.nome.label', default: 'Nome')}"/>
            
        </tr>
        </thead>
        <tbody>
        <g:each in="${disponibilitaInstanceList}" status="i" var="disponibilitaInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                
                <td><g:link action="show"
                            id="${disponibilitaInstance.id}">${fieldValue(bean: disponibilitaInstance, field: "nome")}</g:link></td>
                
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${disponibilitaInstanceTotal}"/>
    </div>
</div>
</body>
</html>
