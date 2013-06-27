/* Created by Algos s.r.l. */
/* Date: mag 2013 */
/* Il plugin Algos ha creato o sovrascritto il templates che ha creato questo file. */
/* L'header del templates serve per controllare le successive release */
/* (tramite il flag di controllo aggiunto) */
/* Tipicamente VERRA sovrascritto (il template, non il file) ad ogni nuova release */
/* del plugin per rimanere aggiornato. */
/* Se vuoi che le prossime release del plugin NON sovrascrivano il template che */
/* genera questo file, perdendo tutte le modifiche precedentemente effettuate, */
/* regola a false il flag di controllo flagOverwrite© del template stesso. */
/* (non quello del singolo file) */
/* flagOverwrite = true */

package it.algos.myvitaback

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

class VitaTagLib {

    static namespace = "vita" //stand for myvitaback
    static String app = 'myvitaback'

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def utenteService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def springSecurityService

    /**
     * Titolo della pagina <br>
     *
     * @return testo del tag
     */
    def titoloPagina = {
        String testoOut = ''
        String testo = ''

        testo += cellaTitoloImmagine()
        testo += cellaTitoloTesto()
        testo += cellaTitoloLogin()

        testoOut += '<table>'
        testoOut += testo
        testoOut += '</table>'

        out << testoOut
    }// fine della closure


    private String cellaTitoloImmagine() {
        String testoOut = ''

        testoOut += '<th class="titoloimmagine">'
        testoOut += '<a href="http://www.progetto-vita.eu/">'
        testoOut += "<img src=\"${resource(dir: 'images', file: 'dae80.png')}\"/>"
        testoOut += '</a>'
        testoOut += '</th>'

        return testoOut
    }// fine del metodo

    private static String cellaTitoloTesto() {
        String testoOut = ''

        testoOut += '<th class="titolotesto">'
        testoOut += 'Progetto vita'
        testoOut += '</th>'

        return testoOut
    }// fine del metodo

    private String cellaTitoloLogin() {
        String testoOut = ''
        String testo = ''
        String tagLink = ''
        boolean mostraLogin = true
        boolean usaDueRighe = true
        boolean usaLinkLog = false
        String htmlBase = '/myvitaback/'
        String htmlLogin = htmlBase + 'login'
        String htmlLogout = htmlBase + 'logout'
        String tagAnonimo = 'anonymousUser'
        String tagOspite = 'ospite'
        String tagCollegato = "Sei collegato come:"
        def user = springSecurityService.principal

        if (user && user instanceof String && user.equals(tagAnonimo)) {
            mostraLogin = true
            testo = tagOspite
        } else {
            mostraLogin = false
            if (user instanceof GrailsUser) {
                if (user) {
                    testo = user.username
                }// fine del blocco if
            }// fine del blocco if
        }// fine del blocco if-else

        testoOut += '<th class="titolologin">'
        if (usaDueRighe) {
            testoOut += tagCollegato
            testoOut += '<br>'
            testoOut += testo
            if (usaLinkLog) {
                testoOut += '<br><br>'
                testoOut += '<a href="'
                if (mostraLogin) {
                    testoOut += htmlLogin
                    testoOut += '">'
                    testoOut += 'login'
                } else {
                    testoOut += htmlLogout
                    testoOut += '">'
                    testoOut += 'logout'
                }// fine del blocco if-else
                testoOut += '</a>'
            }// fine del blocco if
        } else {
            testoOut += testo
        }// fine del blocco if-else
        testoOut += '</th>'


        return testoOut
    }// fine del metodo

    /**
     * Lista dei controllers per la videata Home <br>
     *
     * @return testo del tag
     */
    def listaControllers = {
        String testoOut = ''

        if (utenteService.isLoggatoProgrammatore()) {
            testoOut += tagController('Versione')
            testoOut += tagController('Logo')
            testoOut += tagController('Role')
            testoOut += tagController('User')
            testoOut += tagController('UserRole')
        }// fine del blocco if
        if (utenteService.isLoggatoAdminOrMore()) {
            testoOut += tagController('Batteria', 'Scadenza batterie')
            testoOut += tagController('Elettrodo', 'Scadenza elettrodi')
            testoOut += tagController('Categoria')
            testoOut += tagController('Collocazione')
            testoOut += tagController('Comune')
            testoOut += tagController('Corso')
            testoOut += tagController('Disponibilita')
            testoOut += tagController('Modello')
            testoOut += tagController('Provincia')
            testoOut += tagController('Stato')
        }// fine del blocco if
        //testoOut += tagController('Comune', 'Selezione delle installazioni per comune')
        testoOut += tagController('Dae', 'Elenco completo')

        out << testoOut
    }// fine della closure

    //--Costruisce il tag controller per il testo indicato
    public static String tagController(String controller, String titolo, String azione) {
        String testoOut = ''

        if (controller && titolo) {
            testoOut += '<li class="controller">'
            testoOut += '<a href="/myvitaback/'
            testoOut += controller
            testoOut += '/'
            if (azione) {
                testoOut += azione
            } else {
                testoOut += 'index'
            }// fine del blocco if-else
            testoOut += '">'
            testoOut += titolo
            testoOut += '</a>'
            testoOut += '</li>'
        }// fine del blocco if

        return testoOut
    } // fine del metodo statico

    //--Costruisce il tag controller per il testo indicato
    public static String tagController(String controller, String titolo) {
        return tagController(controller, titolo, '')
    } // fine del metodo statico

    //--Costruisce il tag controller per il testo indicato
    public static String tagController(String controller) {
        return tagController(controller, controller)
    } // fine del metodo statico

    //--disegna i titoli delle colonne della tavola/lista
    def titoliLista = { args ->
        String testoOut = ''
        ArrayList lista = null
        def bean = null
        String contPath = app + '.'
        def campi
        String cont
        String oldSort = 'id'
        String sort = ''
        String order = 'asc'
        String title = 'void'
        String campo
        def elementoLista

        if (args.campiLista) {
            lista = args.campiLista
        }// fine del blocco if
        if (params.controller) {
            cont = params.controller
            contPath += Lib.primaMaiuscola(cont)
        }// fine del blocco if
        if (params.sort) {
            oldSort = params.sort
        }// fine del blocco if
        if (params.order) {
            order = params.order
        }// fine del blocco if

        if (cont) {
            if (lista) {
                lista?.each {
                    elementoLista = it
                    if (elementoLista instanceof String) {
                        campo = it
                        sort = campo
                        title = message(code: "${cont}.${campo}.labellist")
                    } else {
                        if (elementoLista instanceof Map && elementoLista.titolo && elementoLista.campo) {
                            campo = elementoLista.campo
                            sort = elementoLista.campo
                            title = elementoLista.titolo
                        } else { // emergency error
                            campo = 'it'
                            sort = 'asc'
                            title = 'ID'
                        }// fine del blocco if-else
                    }// fine del blocco if-else

                    if (sort.equals(oldSort)) {
                        testoOut += Lib.getTitoloTabellaNotSorted(app, cont, sort, order, title)
                    } else {
                        testoOut += Lib.getTitoloTabellaSorted(app, cont, sort, order, title)
                    }// fine del blocco if-else
                } // fine del ciclo each
            } else {
                bean = applicationContext.getBean(contPath)
                if (bean) {
                    campi = bean.properties.keySet()
                    campi?.each {
                        campo = it
                        sort = campo
                        title = message(code: "${cont}.${campo}.labellist")
                        if (sort.equals(oldSort)) {
                            testoOut += Lib.getTitoloTabellaNotSorted(app, cont, sort, order, title)
                        } else {
                            testoOut += Lib.getTitoloTabellaSorted(app, cont, sort, order, title)
                        }// fine del blocco if-else
                    } // fine del ciclo each
                }// fine del blocco if
            }// fine del blocco if-else
        }// fine del blocco if

        out << testoOut
    }// fine della closure

    //--disegna tutti i campi di una riga della tavola/lista
    //--disegna eventuali campi extra per le funzioni dei militi di una croce
    def rigaLista = { args ->
        String testoOut = ''
        ArrayList lista = null
        ArrayList listaExtra = null
        def bean = null
        String contPath = app + '.'
        def campi
        String cont
        String campo = ''
        def rec = null
        long id = 0.0
        def value = null
        def elementoLista

        if (args.campiLista) {
            lista = args.campiLista
        }// fine del blocco if
        if (params.controller) {
            cont = params.controller
            contPath += Lib.primaMaiuscola(cont)
        }// fine del blocco if
        if (args.rec) {
            rec = args.rec
        }// fine del blocco if
        try { // prova ad eseguire il codice
            if (rec && rec.id) {
                id = rec.id
            }// fine del blocco if
        } catch (Exception unErrore) { // intercetta l'errore
            log.error unErrore
        }// fine del blocco try-catch
        if (args.campiExtra) {
            listaExtra = args.campiExtra
        }// fine del blocco if

        if (cont && rec) {
            if (lista) {
                lista?.each {
                    elementoLista = it
                    if (elementoLista instanceof String) {
                        campo = it
                    } else {
                        if (elementoLista instanceof Map && elementoLista.campo) {
                            campo = elementoLista.campo
                        }// fine del blocco if
                    }// fine del blocco if-else
                    if (rec."${campo}") {
                        value = rec."${campo}"
                    } else {
                        value = null
                    }// fine del blocco if-else

                    testoOut += Lib.getCampoTabella(app, cont, id, value)
                } // fine del ciclo each
            } else {
                bean = applicationContext.getBean(contPath)
                if (bean) {
                    campi = bean.properties.keySet()
                    campi?.each {
                        campo = it
                        if (rec."${campo}") {
                            value = rec."${campo}"
                        } else {
                            value = null
                        }// fine del blocco if-else
                        testoOut += Lib.getCampoTabella(app, cont, id, value)
                    } // fine del ciclo each
                }// fine del blocco if
            }// fine del blocco if-else
        }// fine del blocco if

        if (cont && rec && listaExtra) {
            value = false
            listaExtra?.each {
                campo = it
                testoOut += Lib.getCampoTabella(app, cont, id, false)
            } // fine del ciclo each
        }// fine del blocco if

        out << testoOut
    }// fine della closure

    /**
     * Copyright e/o versione in fondo <br>
     */
    def copyright = {
//        out << 'Algos© - v1.5 del 11 aprile 2013'
        out << 'Algos© - v1.6 del 17 giugno 2013'
    }// fine della closure

} // fine della classe TagLib


