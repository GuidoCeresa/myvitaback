












<%@ page import="it.algos.myvitaback.Dae" %>



<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'comune', 'error')} ">
	<label for="comune">
		<g:message code="dae.comune.label" default="Comune" />
		
	</label>
	









<g:select id="comune" name="comune.id" from="${it.algos.myvitaback.Comune.list()}" optionKey="id" value="${daeInstance?.comune?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'indirizzo', 'error')} ">
	<label for="indirizzo">
		<g:message code="dae.indirizzo.label" default="Indirizzo" />
		
	</label>
	









<g:textField name="indirizzo" value="${daeInstance?.indirizzo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="dae.nome.label" default="Nome" />
		
	</label>
	









<g:textField name="nome" value="${daeInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'loc', 'error')} ">
	<label for="loc">
		<g:message code="dae.loc.label" default="Loc" />
		
	</label>
	









<g:textField name="loc" value="${daeInstance?.loc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'disp', 'error')} ">
	<label for="disp">
		<g:message code="dae.disp.label" default="Disp" />
		
	</label>
	









<g:select id="disp" name="disp.id" from="${it.algos.myvitaback.Disponibilita.list()}" optionKey="id" value="${daeInstance?.disp?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'rif', 'error')} ">
	<label for="rif">
		<g:message code="dae.rif.label" default="Rif" />
		
	</label>
	









<g:textField name="rif" value="${daeInstance?.rif}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'telpuntoblu', 'error')} ">
	<label for="telpuntoblu">
		<g:message code="dae.telpuntoblu.label" default="Telpuntoblu" />
		
	</label>
	









<g:textField name="telpuntoblu" value="${daeInstance?.telpuntoblu}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'cat', 'error')} ">
	<label for="cat">
		<g:message code="dae.cat.label" default="Cat" />
		
	</label>
	









<g:select id="cat" name="cat.id" from="${it.algos.myvitaback.Categoria.list()}" optionKey="id" value="${daeInstance?.cat?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'prov', 'error')} ">
	<label for="prov">
		<g:message code="dae.prov.label" default="Prov" />
		
	</label>
	









<g:select id="prov" name="prov.id" from="${it.algos.myvitaback.Provincia.list()}" optionKey="id" value="${daeInstance?.prov?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'modello', 'error')} ">
	<label for="modello">
		<g:message code="dae.modello.label" default="Modello" />
		
	</label>
	









<g:select id="modello" name="modello.id" from="${it.algos.myvitaback.Modello.list()}" optionKey="id" value="${daeInstance?.modello?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'coll', 'error')} ">
	<label for="coll">
		<g:message code="dae.coll.label" default="Coll" />
		
	</label>
	









<g:select id="coll" name="coll.id" from="${it.algos.myvitaback.Collocazione.list()}" optionKey="id" value="${daeInstance?.coll?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'tel', 'error')} ">
	<label for="tel">
		<g:message code="dae.tel.label" default="Tel" />
		
	</label>
	









<g:textField name="tel" value="${daeInstance?.tel}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'serie', 'error')} ">
	<label for="serie">
		<g:message code="dae.serie.label" default="Serie" />
		
	</label>
	









<g:textField name="serie" value="${daeInstance?.serie}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'mail', 'error')} ">
	<label for="mail">
		<g:message code="dae.mail.label" default="Mail" />
		
	</label>
	









<g:textField name="mail" value="${daeInstance?.mail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'elettrodi', 'error')} ">
	<label for="elettrodi">
		<g:message code="dae.elettrodi.label" default="Elettrodi" />
		
	</label>
	









<g:select id="elettrodi" name="elettrodi.id" from="${it.algos.myvitaback.Elettrodo.list()}" optionKey="id" value="${daeInstance?.elettrodi?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'batteria', 'error')} ">
	<label for="batteria">
		<g:message code="dae.batteria.label" default="Batteria" />
		
	</label>
	









<g:select id="batteria" name="batteria.id" from="${it.algos.myvitaback.Batteria.list()}" optionKey="id" value="${daeInstance?.batteria?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'teca', 'error')} ">
	<label for="teca">
		<g:message code="dae.teca.label" default="Teca" />
		
	</label>
	









<g:textField name="teca" value="${daeInstance?.teca}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'verifica', 'error')} ">
	<label for="verifica">
		<g:message code="dae.verifica.label" default="Verifica" />
		
	</label>
	









<g:textField name="verifica" value="${daeInstance?.verifica}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'cartelli', 'error')} ">
	<label for="cartelli">
		<g:message code="dae.cartelli.label" default="Cartelli" />
		
	</label>
	









<g:textField name="cartelli" value="${daeInstance?.cartelli}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'corso', 'error')} ">
	<label for="corso">
		<g:message code="dae.corso.label" default="Corso" />
		
	</label>
	









<g:select id="corso" name="corso.id" from="${it.algos.myvitaback.Corso.list()}" optionKey="id" value="${daeInstance?.corso?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'stato', 'error')} ">
	<label for="stato">
		<g:message code="dae.stato.label" default="Stato" />
		
	</label>
	









<g:select id="stato" name="stato.id" from="${it.algos.myvitaback.Stato.list()}" optionKey="id" value="${daeInstance?.stato?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'ok', 'error')} ">
	<label for="ok">
		<g:message code="dae.ok.label" default="Ok" />
		
	</label>
	









<g:checkBox name="ok" value="${daeInstance?.ok}" />
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'lat', 'error')} required">
	<label for="lat">
		<g:message code="dae.lat.label" default="Lat" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="lat" value="${fieldValue(bean: daeInstance, field: 'lat')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'lon', 'error')} required">
	<label for="lon">
		<g:message code="dae.lon.label" default="Lon" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="lon" value="${fieldValue(bean: daeInstance, field: 'lon')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: daeInstance, field: 'note', 'error')} ">
	<label for="note">
		<g:message code="dae.note.label" default="Note" />
		
	</label>
	









<g:textArea name="note" cols="40" rows="5" value="${daeInstance?.note}"/>
</div>

