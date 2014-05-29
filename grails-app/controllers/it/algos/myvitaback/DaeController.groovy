package it.algos.myvitaback

import grails.plugins.springsecurity.Secured
import it.algos.algoslogo.Evento
import it.algos.algospref.Preferenze
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.springframework.dao.DataIntegrityViolationException

class DaeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def springSecurityService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def logoService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def utenteService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def exportService

    def index() {
        redirect(action: 'list', params: params)
    }

    def list(Integer max) {
        if (flash.error) {
            params.flash.error = flash.error
        }// fine del blocco if

        if (utenteService.isLoggatoAdminOrMore()) {
            redirect(action: 'listAdmin', params: params)
        } else {
            redirect(action: 'listOspiti', params: params)
        }// fine del blocco if-else
    } // fine del metodo

    def listAdmin(Integer max) {
        def lista
        def campiLista = [
                'id',
                'ok',
                'cat',
                'nome',
                'indirizzo',
                'comune',
                'rif',
                'telpuntoblu',
                'disp',
        ]

        if (!params.sort) {
            params.sort = 'comune'
        }// fine del blocco if-else

        if (params.order) {
            if (params.order == 'asc') {
                params.order = 'desc'
            } else {
                params.order = 'asc'
            }// fine del blocco if-else
        } else {
            params.order = 'asc'
        }// fine del blocco if-else

        lista = Dae.list()

        if (params?.format && params.format != "html") {
            List fields = []
            def properties = new DefaultGrailsDomainClass(Dae.class).persistentProperties
            properties?.each {
                fields.add(it.name)
            } // fine del ciclo each
            String oggi = Lib.presentaDataMese(new Date())
            Map parameters = [title: "Dae al ${oggi}"]
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=Dae.${params.extension}")
            exportService.export(params.format, response.outputStream, lista, fields, [:], [:], parameters)
        }

        render(view: 'list', model: [daeInstanceList: lista, daeInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    def listOspiti(Integer max) {
        def lista
        if (params.error) {
            flash.error = params.error
        }// fine del blocco if

        def campiLista = [
                'cat',
                'nome',
                'indirizzo',
                'comune',
        ]

        if (!params.sort) {
            params.sort = 'comune'
        }// fine del blocco if-else

        if (params.order) {
            if (params.order == 'asc') {
                params.order = 'desc'
            } else {
                params.order = 'asc'
            }// fine del blocco if-else
        } else {
            params.order = 'asc'
        }// fine del blocco if-else

        lista = Dae.list()
        render(view: 'list', model: [daeInstanceList: lista, daeInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    def comunimappa(Long id) {
        Comune comune
        String myTitolo = ''
        String myComune = ''
        ArrayList myLat = new ArrayList()
        ArrayList myLon = new ArrayList()
        ArrayList myTip = new ArrayList()
        def listaDae
        Dae dae
        String detail = ''
        float lat
        float lon
        boolean datiDefinitivi = Preferenze.getBool(Cost.PREF_DATI_DEFINITIVI)

        if (id) {
            comune = Comune.get(id)
        }// fine del blocco if

        if (comune) {
            myComune = comune.nome
            listaDae = Dae.findAllByComune(comune)
            def stop
        }// fine del blocco if

        if (listaDae) {
            listaDae?.each {
                lat = 0.0
                lon = 0.0
                detail = ''
                dae = (Dae) it
                if (!datiDefinitivi || (datiDefinitivi && dae.ok)) {
                    lat = dae.lat
                    lon = dae.lon
                    if (lat > 0) {
                        myLat.add(dae.lat)
                    }// fine del blocco if
                    if (lon > 0) {
                        myLon.add(dae.lon)
                    }// fine del blocco if
                    if (dae.nome) {
                        detail += dae.nome
                    }// fine del blocco if
                    if (dae.nome && dae.loc) {
                        detail += ' - '
                    }// fine del blocco if
                    if (dae.loc) {
                        detail += dae.loc
                    }// fine del blocco if
                    myTip.add(detail)
                }// fine del blocco if
            } // fine del ciclo each
            myTitolo = 'Mappa dei ' + myLat.size() + ' defibrillatori siti nel comune di ' + comune
        }// fine del blocco if


        if (myLat.size() < 1) {
            listaDae = Dae.findAll()
            myLat = new ArrayList()
            myLon = new ArrayList()
            myTip = new ArrayList()
            listaDae?.each {
                lat = 0.0
                lon = 0.0
                detail = ''
                dae = (Dae) it
                lat = dae.lat
                lon = dae.lon
                if (lat && lon) {
                    myLat.add(dae.lat)
                    myLon.add(dae.lon)
                    if (dae.nome) {
                        detail += dae.nome
                    }// fine del blocco if
                    if (dae.nome && dae.loc) {
                        detail += ' - '
                    }// fine del blocco if
                    if (dae.loc) {
                        detail += dae.loc
                    }// fine del blocco if
                    myTip.add(detail)
                }// fine del blocco if
            } // fine del ciclo each
            myTitolo = 'Mappa dei defibrillatori di tutta la provincia (dati provvisori).'
        }// fine del blocco if

        if (myLat && myLat.size() > 0 && myLon && myLon.size() > 0) {
            render(view: 'mappa', model: [myTitolo: myTitolo, myComune: myComune, myLat: myLat, myLon: myLon, myTip: myTip])
        } else {
            params.error = 'La mappa non può essere visualizzata, perché mancano tutte le coordinate dei defibrillatori'
            redirect(action: 'list', params: params)
        }// fine del blocco if-else
    }

    def comunilista(Long id) {
        Comune comune = Comune.findById(id)
        def lista
        def campiLista = [
                'ok',
                'comune',
                'disp',
                'indirizzo',
                'nome',
                'rif',
                'telpuntoblu'
        ]

        if (params.order) {
            if (params.order == 'asc') {
                params.order = 'desc'
            } else {
                params.order = 'asc'
            }// fine del blocco if-else
        } else {
            params.order = 'asc'
        }// fine del blocco if-else

        lista = Dae.findAllByComune(comune)

        render(view: 'list', model: [daeInstanceList: lista, daeInstanceTotal: lista.size(), campiLista: campiLista], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def create() {
        [daeInstance: new Dae(params)]
    }

    @Secured([Cost.ROLE_ADMIN])
    def save() {
        def daeInstance = new Dae(params)
        Evento evento = Evento.findByNome(Cost.EVENTO_EDIT)
        User currUser = (User) springSecurityService.getCurrentUser()
        String ruolo = 'admin'

        long numCodice = Dae.createCriteria().get {
            projections {
                max "codice"
            }
        } as Long
        numCodice++
        daeInstance.codice = numCodice

        if (!daeInstance.save(flush: true)) {
            render(view: "create", model: [daeInstance: daeInstance])
            return
        }

        //--registra la traccia di chi ha registrato e quando
        logoService.setInfo(request, evento, currUser?.username, ruolo, "Creato nuovo record: #${numCodice}")

        flash.message = message(code: 'default.created.message', args: [message(code: 'dae.label', default: 'Dae'), daeInstance.id])
        redirect(action: "show", id: daeInstance.id)
    }

    def show(Long id) {
        def daeInstance = Dae.get(id)
        if (!daeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dae.label', default: 'Dae'), id])
            redirect(action: "list")
            return
        }

        [daeInstance: daeInstance]
    }

    @Secured([Cost.ROLE_ADMIN])
    def edit(Long id) {
        def daeInstance = Dae.get(id)
        if (!daeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dae.label', default: 'Dae'), id])
            redirect(action: "list")
            return
        }

        [daeInstance: daeInstance]
    }

    @Secured([Cost.ROLE_ADMIN])
    def update(Long id, Long version) {
        def daeInstance = Dae.get(id)
        Evento evento = Evento.findByNome(Cost.EVENTO_EDIT)
        User currUser = (User) springSecurityService.getCurrentUser()
        String ruolo = ''
        String dettaglio

        if (!daeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dae.label', default: 'Dae'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (daeInstance.version > version) {
                daeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'dae.label', default: 'Dae')] as Object[],
                        "Another user has updated this Dae while you were editing")
                render(view: "edit", model: [daeInstance: daeInstance])
                return
            }
        }

        //--confronta i nuovi campi (params) coi vecchi (daeInstance.properties)
        dettaglio = getModifiche(daeInstance, params)
        daeInstance.properties = params

        if (!daeInstance.save(flush: true)) {
            render(view: "edit", model: [daeInstance: daeInstance])
            return
        }

        //--registra la traccia di chi ha modificato e quando
        logoService.setInfo(request, evento, currUser?.username, ruolo, dettaglio)

        flash.message = message(code: 'default.updated.message', args: [message(code: 'dae.label', default: 'Dae'), daeInstance.id])
        redirect(action: "show", id: daeInstance.id)
    }

    @Secured([Cost.ROLE_ADMIN])
    def delete(Long id) {
        def daeInstance = Dae.get(id)
        if (!daeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dae.label', default: 'Dae'), id])
            redirect(action: "list")
            return
        }

        try {
            daeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dae.label', default: 'Dae'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dae.label', default: 'Dae'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

    private static String getModifiche(Dae dae, def mappa) {
        String dettaglio = ''
        def numRec = dae.id
        def oldLat = dae.lat
        def newLat = mappa.lat
        def oldLon = dae.lon
        def newLon = mappa.lon

        if (newLat && newLon) {
            if (oldLat == 0.0 && oldLon == 0.0) {
                dettaglio = "Inserite le coordinate (rec#${numRec})"
            } else {
                dettaglio = "Modificate le coordinate (rec#${numRec})"
            }// fine del blocco if-else
        }// fine del blocco if

        return dettaglio
    } // fine del metodo

} // fine della controller classe
