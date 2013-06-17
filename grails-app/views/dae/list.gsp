
<%@ page import="it.algos.myvitaback.Dae" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'dae.label', default: 'Dae')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-dae" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                          default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <sec:ifLoggedIn>
            <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="list-dae" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <vita:titoliLista campiLista="${campiLista}"></vita:titoliLista>
        <g:if test="${campiExtra}">
            <vita:titoliExtraLista campiExtra="${campiExtra}"></vita:titoliExtraLista>
        </g:if>
        </thead>
        <tbody>
        <g:each in="${daeInstanceList}" status="i" var="daeInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <vita:rigaLista campiLista="${campiLista}" rec="${daeInstance}" campiExtra="${campiExtra}"></vita:rigaLista>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${daeInstanceTotal}"/>
    </div>
</div>
</body>
</html>
