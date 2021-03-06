













<%@ page import="it.algos.myvitaback.Collocazione" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'collocazione.label', default: 'Collocazione')}"/>
    <title><g:message code="collocazione.list.label" args="[entityName]" default="Elenco"/></title>
</head>

<body>
<a href="#list-collocazione" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Home"/></a></li>
        <li><g:link class="create" action="create"><g:message code="collocazione.new.label"
                                                              args="[entityName]" default="Nuovo"/></g:link></li>
    </ul>
</div>

<div id="list-collocazione" class="content scaffold-list" role="main">
    <h1><g:message code="collocazione.list.label" args="[entityName]" default="Elenco"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>
            
            <g:sortableColumn property="nome"
                              title="${message(code: 'collocazione.nome.label', default: 'Nome')}"/>
            
        </tr>
        </thead>
        <tbody>
        <g:each in="${collocazioneInstanceList}" status="i" var="collocazioneInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                
                <td><g:link action="show"
                            id="${collocazioneInstance.id}">${fieldValue(bean: collocazioneInstance, field: "nome")}</g:link></td>
                
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${collocazioneInstanceTotal}"/>
    </div>
</div>
</body>
</html>
