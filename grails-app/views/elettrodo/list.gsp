













<%@ page import="it.algos.myvitaback.Elettrodo" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'elettrodo.label', default: 'Elettrodo')}"/>
    <title><g:message code="elettrodo.list.label" args="[entityName]" default="Elenco"/></title>
</head>

<body>
<a href="#list-elettrodo" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Home"/></a></li>
        <li><g:link class="create" action="create"><g:message code="elettrodo.new.label"
                                                              args="[entityName]" default="Nuovo"/></g:link></li>
    </ul>
</div>

<div id="list-elettrodo" class="content scaffold-list" role="main">
    <h1><g:message code="elettrodo.list.label" args="[entityName]" default="Elenco"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>
            
            <g:sortableColumn property="nome"
                              title="${message(code: 'elettrodo.nome.label', default: 'Nome')}"/>
            
        </tr>
        </thead>
        <tbody>
        <g:each in="${elettrodoInstanceList}" status="i" var="elettrodoInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                
                <td><g:link action="show"
                            id="${elettrodoInstance.id}">${fieldValue(bean: elettrodoInstance, field: "nome")}</g:link></td>
                
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${elettrodoInstanceTotal}"/>
    </div>
</div>
</body>
</html>
