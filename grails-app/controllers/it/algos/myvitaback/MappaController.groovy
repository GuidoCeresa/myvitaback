package myvitaback

import grails.converters.JSON

import java.sql.Timestamp

class MappaController {

    def index() {
        def mappa = []
        Dae dae

        Categoria categoria = null
        String categoriaTxt = ''
        Provincia provincia = null
        String provinciaTxt = ''
        Comune comune = null
        String comuneTxt = ''
        Modello modello = null
        String modelloTxt = ''
        Disponibilita disponibilita = null
        String disponibilitaTxt = ''
        Collocazione collocazione = null
        String collocazioneTxt = ''
        Stato stato = null
        String statoTxt = ''
        int codice = 0
        String indirizzo = ''
        String località = ''
        String nome = ''
        String serie = ''
        String riferimento = ''
        String mail = ''
        String telefono = ''
        String telpuntoblu = ''
        Elettrodo elettrodi = null
        String scadenzaElettrodi = ''
        Batteria batteria = null
        String scadenzaBatteria = ''
        String teca = ''
        String ultimaVerifica = ''
        String cartelli = ''
        Corso corso = null
        String ultimoCorso = ''
        String latitudine = ''
        String longitudine = ''
        boolean ok
        String okTxt = ''
        String note = ''
        Timestamp lastUpdated = null
        String lastUpdatedTxt = ''
        def rec
        def lista = Dae.findAll()

        if (lista && lista.size() > 0) {
            lista?.each {
                codice = 0
                categoriaTxt = ''
                provinciaTxt = ''
                nome = ''
                indirizzo = ''
                comuneTxt = ''
                modelloTxt = ''
                serie = ''
                riferimento = ''
                telefono = ''
                mail = ''
                telpuntoblu = ''
                disponibilitaTxt = ''
                collocazioneTxt = ''
                località = ''
                scadenzaElettrodi = ''
                scadenzaBatteria = ''
                teca = ''
                ultimaVerifica = ''
                cartelli = ''
                ultimoCorso = ''
                statoTxt = ''

                latitudine = ''
                longitudine = ''
                okTxt = ''
                note = ''
                lastUpdated = null
                lastUpdatedTxt = ''

                dae = (Dae) it

                if (dae.codice) {
                    codice = dae.codice
                }// fine del blocco if

                categoria = dae.cat
                if (categoria) {
                    categoriaTxt = categoria.sigla
                    categoriaTxt = Lib.setApice(categoriaTxt)
                }// fine del blocco if

                provincia = dae.prov
                if (provincia) {
                    provinciaTxt = provincia.sigla
                }// fine del blocco if

                if (dae.nome) {
                    nome = dae.nome
                    nome = Lib.setApice(nome)
                }// fine del blocco if

                if (dae.indirizzo) {
                    indirizzo = dae.indirizzo
                    indirizzo = Lib.setApice(indirizzo)
                }// fine del blocco if

                comune = dae.comune
                if (comune) {
                    comuneTxt = comune.nome
                }// fine del blocco if

                modello = dae.modello
                if (modello) {
                    modelloTxt = modello.nome
                }// fine del blocco if

                if (dae.serie) {
                    serie = dae.serie
                    serie = Lib.setApice(serie)
                }// fine del blocco if

                if (dae.rif) {
                    riferimento = dae.rif
                    riferimento = Lib.setApice(riferimento)
                }// fine del blocco if

                if (dae.tel) {
                    telefono = dae.tel
                    telefono = Lib.setApice(telefono)
                }// fine del blocco if

                if (dae.mail) {
                    mail = dae.mail
                    mail = Lib.setApice(mail)
                }// fine del blocco if

                if (dae.telpuntoblu) {
                    telpuntoblu = dae.telpuntoblu
                    telpuntoblu = Lib.setApice(telpuntoblu)
                }// fine del blocco if

                disponibilita = dae.disp
                if (disponibilita) {
                    disponibilitaTxt = disponibilita.nome
                }// fine del blocco if

                collocazione = dae.coll
                if (collocazione) {
                    collocazioneTxt = collocazione.nome
                }// fine del blocco if

                if (dae.loc) {
                    località = dae.loc
                    località = Lib.setApice(località)
                }// fine del blocco if

                elettrodi = dae.elettrodi
                if (elettrodi) {
                    scadenzaElettrodi = elettrodi.nome
                }// fine del blocco if

                batteria = dae.batteria
                if (batteria) {
                    scadenzaBatteria = batteria.nome
                }// fine del blocco if

                if (dae.teca) {
                    teca = dae.teca
                    telpuntoblu = Lib.setApice(telpuntoblu)
                }// fine del blocco if

                if (dae.verifica) {
                    ultimaVerifica = dae.verifica
                    ultimaVerifica = Lib.setApice(ultimaVerifica)
                }// fine del blocco if

                if (dae.cartelli) {
                    cartelli = dae.cartelli
                    cartelli = Lib.setApice(cartelli)
                }// fine del blocco if

                corso = dae.corso
                if (corso) {
                    ultimoCorso = corso.nome
                }// fine del blocco if

                stato = dae.stato
                if (stato) {
                    statoTxt = stato.nome
                }// fine del blocco if

                if (dae.lat) {
                    latitudine = dae.lat
                }// fine del blocco if

                if (dae.lon) {
                    longitudine = dae.lon
                }// fine del blocco if

                if (dae.ok) {
                    ok = dae.ok
                    if (ok) {
                        okTxt = 'vero'
                    } else {
                        okTxt = 'falso'
                    }// fine del blocco if-else
                }// fine del blocco if

                if (dae.note) {
                    note = dae.note
                }// fine del blocco if

                if (dae.lastUpdated) {
                    lastUpdatedTxt = dae.lastUpdated.toString()
                } else {
                    lastUpdatedTxt = ''
                }// fine del blocco if-else

                rec = [
                        codice: codice,
                        categoria: categoriaTxt,
                        provincia: provinciaTxt,
                        nome: nome,
                        indirizzo: indirizzo,
                        comune: comuneTxt,
                        modello: modelloTxt,
                        serie: serie,
                        riferimento: riferimento,
                        telefono: telefono,
                        mail: mail,
                        telpuntoblu: telpuntoblu,
                        disponibilita: disponibilitaTxt,
                        collocazione: collocazioneTxt,
                        localita: località,
                        scadenzaElettrodi: scadenzaElettrodi,
                        scadenzaBatteria: scadenzaBatteria,
                        teca: teca,
                        ultimaVerifica: ultimaVerifica,
                        cartelli: cartelli,
                        ultimoCorso: ultimoCorso,
                        stato: statoTxt,
                        latitudine: latitudine,
                        longitudine: longitudine,
                        ok: okTxt,
                        note: note,
                        immagine: '',
                        lastUpdated: lastUpdatedTxt
                ]
                mappa.add(rec)
            } // fine del ciclo each
        }// fine del blocco if

        render mappa as JSON
    }

} // fine della controller classe
