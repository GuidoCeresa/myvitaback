<%@ page import="it.algos.myvitaback.Comune" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'comune.label', default: 'Comune')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-comune" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <sec:ifLoggedIn>
            <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>
<div id="list-comune" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="nome" title="${message(code: 'comune.nome.label', default: 'Nome')}" />
            <g:sortableColumn property="nome" title="Mappa dei DAE del comune" />
            <g:sortableColumn property="nome" title="Lista dei DAE del comune" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${comuneInstanceList}" status="i" var="comuneInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${comuneInstance.id}">${fieldValue(bean: comuneInstance, field: "nome")}</g:link></td>
                <td><g:link controller="dae" action="comunimappa" id="${comuneInstance.id}">Vai alla mappa dei DAE esistenti nel comune di ${fieldValue(bean: comuneInstance, field: "nome")}</g:link></td>
                <td><g:link controller="dae" action="comunilista" id="${comuneInstance.id}">Vai alla lista dei DAE esistenti nel comune di ${fieldValue(bean: comuneInstance, field: "nome")}</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${comuneInstanceTotal}" />
    </div>
</div>
</body>
</html>
