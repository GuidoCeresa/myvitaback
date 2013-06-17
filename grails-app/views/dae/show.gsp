













<%@ page import="it.algos.myvitaback.Dae" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'dae.label', default: 'Dae')}" />
    <title><g:message code="dae.show.label" args="[entityName]" default="Mostra"/></title>
</head>
<body>
<a href="#show-dae" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Home"/></a></li>
        <li><g:link class="list" action="list"><g:message code="dae.list.label" args="[entityName]" default="Elenco"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="dae.new.label" args="[entityName]" default="Nuovo"/></g:link></li>
    </ul>
</div>
<div id="show-dae" class="content scaffold-show" role="main">
    <h1><g:message code="dae.show.label" args="[entityName]" default="Mostra"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list dae">
        
        <g:if test="${daeInstance?.comune}">
            <li class="fieldcontain">
                <span id="comune-label" class="property-label"><g:message code="dae.comune.label" default="Comune" /></span>
                
                <span class="property-value" aria-labelledby="comune-label"><g:link controller="comune" action="show" id="${daeInstance?.comune?.id}">${daeInstance?.comune?.encodeAsHTML()}</g:link></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.indirizzo}">
            <li class="fieldcontain">
                <span id="indirizzo-label" class="property-label"><g:message code="dae.indirizzo.label" default="Indirizzo" /></span>
                
                <span class="property-value" aria-labelledby="indirizzo-label"><g:fieldValue bean="${daeInstance}" field="indirizzo"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.nome}">
            <li class="fieldcontain">
                <span id="nome-label" class="property-label"><g:message code="dae.nome.label" default="Nome" /></span>
                
                <span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${daeInstance}" field="nome"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.loc}">
            <li class="fieldcontain">
                <span id="loc-label" class="property-label"><g:message code="dae.loc.label" default="Loc" /></span>
                
                <span class="property-value" aria-labelledby="loc-label"><g:fieldValue bean="${daeInstance}" field="loc"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.disp}">
            <li class="fieldcontain">
                <span id="disp-label" class="property-label"><g:message code="dae.disp.label" default="Disp" /></span>
                
                <span class="property-value" aria-labelledby="disp-label"><g:link controller="disponibilita" action="show" id="${daeInstance?.disp?.id}">${daeInstance?.disp?.encodeAsHTML()}</g:link></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.rif}">
            <li class="fieldcontain">
                <span id="rif-label" class="property-label"><g:message code="dae.rif.label" default="Rif" /></span>
                
                <span class="property-value" aria-labelledby="rif-label"><g:fieldValue bean="${daeInstance}" field="rif"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.telpuntoblu}">
            <li class="fieldcontain">
                <span id="telpuntoblu-label" class="property-label"><g:message code="dae.telpuntoblu.label" default="Telpuntoblu" /></span>
                
                <span class="property-value" aria-labelledby="telpuntoblu-label"><g:fieldValue bean="${daeInstance}" field="telpuntoblu"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.cat}">
            <li class="fieldcontain">
                <span id="cat-label" class="property-label"><g:message code="dae.cat.label" default="Cat" /></span>
                
                <span class="property-value" aria-labelledby="cat-label"><g:link controller="categoria" action="show" id="${daeInstance?.cat?.id}">${daeInstance?.cat?.encodeAsHTML()}</g:link></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.prov}">
            <li class="fieldcontain">
                <span id="prov-label" class="property-label"><g:message code="dae.prov.label" default="Prov" /></span>
                
                <span class="property-value" aria-labelledby="prov-label"><g:link controller="provincia" action="show" id="${daeInstance?.prov?.id}">${daeInstance?.prov?.encodeAsHTML()}</g:link></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.modello}">
            <li class="fieldcontain">
                <span id="modello-label" class="property-label"><g:message code="dae.modello.label" default="Modello" /></span>
                
                <span class="property-value" aria-labelledby="modello-label"><g:link controller="modello" action="show" id="${daeInstance?.modello?.id}">${daeInstance?.modello?.encodeAsHTML()}</g:link></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.coll}">
            <li class="fieldcontain">
                <span id="coll-label" class="property-label"><g:message code="dae.coll.label" default="Coll" /></span>
                
                <span class="property-value" aria-labelledby="coll-label"><g:link controller="collocazione" action="show" id="${daeInstance?.coll?.id}">${daeInstance?.coll?.encodeAsHTML()}</g:link></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.tel}">
            <li class="fieldcontain">
                <span id="tel-label" class="property-label"><g:message code="dae.tel.label" default="Tel" /></span>
                
                <span class="property-value" aria-labelledby="tel-label"><g:fieldValue bean="${daeInstance}" field="tel"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.serie}">
            <li class="fieldcontain">
                <span id="serie-label" class="property-label"><g:message code="dae.serie.label" default="Serie" /></span>
                
                <span class="property-value" aria-labelledby="serie-label"><g:fieldValue bean="${daeInstance}" field="serie"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.mail}">
            <li class="fieldcontain">
                <span id="mail-label" class="property-label"><g:message code="dae.mail.label" default="Mail" /></span>
                
                <span class="property-value" aria-labelledby="mail-label"><g:fieldValue bean="${daeInstance}" field="mail"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.elettrodi}">
            <li class="fieldcontain">
                <span id="elettrodi-label" class="property-label"><g:message code="dae.elettrodi.label" default="Elettrodi" /></span>
                
                <span class="property-value" aria-labelledby="elettrodi-label"><g:link controller="elettrodo" action="show" id="${daeInstance?.elettrodi?.id}">${daeInstance?.elettrodi?.encodeAsHTML()}</g:link></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.batteria}">
            <li class="fieldcontain">
                <span id="batteria-label" class="property-label"><g:message code="dae.batteria.label" default="Batteria" /></span>
                
                <span class="property-value" aria-labelledby="batteria-label"><g:link controller="batteria" action="show" id="${daeInstance?.batteria?.id}">${daeInstance?.batteria?.encodeAsHTML()}</g:link></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.teca}">
            <li class="fieldcontain">
                <span id="teca-label" class="property-label"><g:message code="dae.teca.label" default="Teca" /></span>
                
                <span class="property-value" aria-labelledby="teca-label"><g:fieldValue bean="${daeInstance}" field="teca"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.verifica}">
            <li class="fieldcontain">
                <span id="verifica-label" class="property-label"><g:message code="dae.verifica.label" default="Verifica" /></span>
                
                <span class="property-value" aria-labelledby="verifica-label"><g:fieldValue bean="${daeInstance}" field="verifica"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.cartelli}">
            <li class="fieldcontain">
                <span id="cartelli-label" class="property-label"><g:message code="dae.cartelli.label" default="Cartelli" /></span>
                
                <span class="property-value" aria-labelledby="cartelli-label"><g:fieldValue bean="${daeInstance}" field="cartelli"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.corso}">
            <li class="fieldcontain">
                <span id="corso-label" class="property-label"><g:message code="dae.corso.label" default="Corso" /></span>
                
                <span class="property-value" aria-labelledby="corso-label"><g:link controller="corso" action="show" id="${daeInstance?.corso?.id}">${daeInstance?.corso?.encodeAsHTML()}</g:link></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.stato}">
            <li class="fieldcontain">
                <span id="stato-label" class="property-label"><g:message code="dae.stato.label" default="Stato" /></span>
                
                <span class="property-value" aria-labelledby="stato-label"><g:link controller="stato" action="show" id="${daeInstance?.stato?.id}">${daeInstance?.stato?.encodeAsHTML()}</g:link></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.ok}">
            <li class="fieldcontain">
                <span id="ok-label" class="property-label"><g:message code="dae.ok.label" default="Ok" /></span>
                
                <span class="property-value" aria-labelledby="ok-label"><g:formatBoolean boolean="${daeInstance?.ok}" /></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.lat}">
            <li class="fieldcontain">
                <span id="lat-label" class="property-label"><g:message code="dae.lat.label" default="Lat" /></span>
                
                <span class="property-value" aria-labelledby="lat-label"><g:fieldValue bean="${daeInstance}" field="lat"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.lon}">
            <li class="fieldcontain">
                <span id="lon-label" class="property-label"><g:message code="dae.lon.label" default="Lon" /></span>
                
                <span class="property-value" aria-labelledby="lon-label"><g:fieldValue bean="${daeInstance}" field="lon"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.note}">
            <li class="fieldcontain">
                <span id="note-label" class="property-label"><g:message code="dae.note.label" default="Note" /></span>
                
                <span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${daeInstance}" field="note"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.codice}">
            <li class="fieldcontain">
                <span id="codice-label" class="property-label"><g:message code="dae.codice.label" default="Codice" /></span>
                
                <span class="property-value" aria-labelledby="codice-label"><g:fieldValue bean="${daeInstance}" field="codice"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.dateCreated}">
            <li class="fieldcontain">
                <span id="dateCreated-label" class="property-label"><g:message code="dae.dateCreated.label" default="Date Created" /></span>
                
                <span class="property-value" aria-labelledby="dateCreated-label"><g:fieldValue bean="${daeInstance}" field="dateCreated"/></span>
                
            </li>
        </g:if>
        
        <g:if test="${daeInstance?.lastUpdated}">
            <li class="fieldcontain">
                <span id="lastUpdated-label" class="property-label"><g:message code="dae.lastUpdated.label" default="Last Updated" /></span>
                
                <span class="property-value" aria-labelledby="lastUpdated-label"><g:fieldValue bean="${daeInstance}" field="lastUpdated"/></span>
                
            </li>
        </g:if>
        
    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${daeInstance?.id}" />
            <g:link class="edit" action="edit" id="${daeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
