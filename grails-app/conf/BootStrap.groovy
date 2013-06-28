/* Created by Algos s.r.l. */
/* Date: mag 2013 */
/* Il plugin Algos ha inserito (solo la prima volta) questo header per controllare */
/* le successive release (tramite il flag di controllo aggiunto) */
/* Non verrà mai sovrascritto, in nessun caso, dalle successive release del plugin */
/* indipendentemente dal fatto che venga localmente modificato o meno */
/* Se si vuole reinstallarlo per una eventuale versione più aggiornata del plugin, */
/* occorre cancellarlo PRIMA di reinstallare il plugin */


import it.algos.algoslogo.Evento
import it.algos.algospref.Preferenze
import it.algos.algosvers.Versione
import it.algos.myvitaback.*
import java.sql.Timestamp

class BootStrap {

    // private static String DIR_PATH = '/Users/Gac/Documents/IdeaProjects/webambulanze/grails-app/webambulanze/'
    private static String DIR_PATH = 'http://77.43.32.198:80/myvita/'

    //--metodo invocato direttamente da Grails
    //--tutte le aggiunte, modifiche, patch e nuove croci vengono inserite con una versione
    //--l'ordine di inserimento è FONDAMENTALE
    def init = { servletContext ->

        //--ruoli per la security
        if (installaVersione(1)) {
            creaSecurity()
        }// fine del blocco if

        //--creazione accessi
        if (installaVersione(2)) {
            securitySetup()
        }// fine del blocco if

        //--creazione delle tabelle
        if (installaVersione(3)) {
            creaTabelleDaExcel()
        }// fine del blocco if

        //--patch ordine alfabetico comuni
        if (installaVersione(4)) {
            patchOrdineComuni()
        }// fine del blocco if

        //--creazione delle localizzazioni
        if (installaVersione(5)) {
            creaDaeDaExcel()
        }// fine del blocco if

        //--patch sulle categorie
        if (installaVersione(6)) {
            patchCategorie(true)
        }// fine del blocco if

        //--patch sulle province
        if (installaVersione(7)) {
            patchProvince(true)
        }// fine del blocco if

        //--univocità delle province
        if (installaVersione(8)) {
            provinceUnivoche()
        }// fine del blocco if

        //--patch sui comuni
        if (installaVersione(9)) {
            patchComuni(true)
        }// fine del blocco if

        //--aggiunta del campo codice
        if (installaVersione(10)) {
            addCodice()
        }// fine del blocco if

        //--elimina l'apostrofo
        if (installaVersione(11)) {
            eliminazioneApostrofo(true)
        }// fine del blocco if

        //--univocità delle disponibilità
        if (installaVersione(12)) {
            disponibilitaUnivoche()
        }// fine del blocco if

        //--elimina l'apostrofo
        if (installaVersione(13)) {
            eliminazioneApostrofo(true)
        }// fine del blocco if

        //--proprieta'
        if (installaVersione(14)) {
            fixProprieta(true)
        }// fine del blocco if

        //--aggiunta plugin crono di Quartz
        if (installaVersione(15)) {
            aggiuntaCrono()
        }// fine del blocco if

        //--aggiunta plugin della posta
        if (installaVersione(16)) {
            aggiuntaMail()
        }// fine del blocco if

        //--aggiunta plugin logo
        if (installaVersione(17)) {
            setupLogo()
        }// fine del blocco if

        //--aggiunta preferenza booleana di controllo dati definitivi
        if (installaVersione(18)) {
            addPreferenzaDatiDefinitivi()
        }// fine del blocco if

        //--elimina l'apostrofo
        if (installaVersione(19)) {
            fixComuneArquato(true)
        }// fine del blocco if

//        //--ricontrolla l'apostrofo dopo un nuovo inserimento dati grezzi
//        if (installaVersione(19)) {
//            ricontrollaApostrofo()
//        }// fine del blocco if

//        //--aggiunta dei campi dateCreated e lastUpdated
//        //--inserimento di una data iniziale
//        if (installaVersione(15)) {
//            creazioneDataModifica()
//        }// fine del blocco if
//
//        //--valore campo codice
//        if (installaVersione(17)) {
//            inserimentoCodiceDae()
//        }// fine del blocco if
//
//
    }// fine della closure

    //--creazione ruoli generali di accesso alle autorizzazioni gestite dal security-plugin
    //--occorre SEMPRE un ruolo ROLE_PROG
    //--occorre SEMPRE un ruolo ROLE_ADMIN
    //--occorre SEMPRE un ruolo ROLE_OSPITE
    //--li crea SOLO se non esistono già
    private static void creaSecurity() {
        Role.findOrCreateByAuthority(Cost.ROLE_PROG).save(failOnError: true)
        Role.findOrCreateByAuthority(Cost.ROLE_ADMIN).save(failOnError: true)
        Role.findOrCreateByAuthority(Cost.ROLE_OSPITE).save(failOnError: true)

        newVersione('Security', 'Creazione iniziale dei ruoli.')
    }// fine del metodo

    //--creazione accessi
    //--occorre SEMPRE un accesso come programmatore
    //--occorre SEMPRE un accesso come admin
    //--occorre SEMPRE un accesso come ospite
    //--li crea SOLO se non esistono già
    private static void securitySetup() {
        User utenteProg
        Role roleAdmin = Role.findOrCreateByAuthority(Cost.ROLE_ADMIN).save(failOnError: true)

        // ruolo di ospite
        newUtente(Cost.ROLE_OSPITE, 'ospite', '')

        // ruoli di admin
        newUtente(Cost.ROLE_ADMIN, 'chicca', 'chicca987')
        newUtente(Cost.ROLE_ADMIN, 'daniela', 'daniela123')
        newUtente(Cost.ROLE_ADMIN, 'enrico', 'enrico123')

        // ruolo di programmatore generale
        utenteProg = newUtente(Cost.ROLE_PROG, 'gac', 'fulvia')

        // ulteriore ruolo di admin per il programmatore
        if (utenteProg) {
            UserRole.findOrCreateByRoleAndUser(roleAdmin, utenteProg).save(failOnError: true)
        }// fine del blocco if


        newVersione('Security', 'Creazione iniziale degli accessi.')
    }// fine del metodo

    //--creazione delle tabelle dal file di excel
    //--li crea SOLO se non esistono già
    private static void creaTabelleDaExcel() {
        String nomeFile = 'dae'
        def righe
        Map mappa = null

        //--prosegue solo se il database è vuoto
        if (Dae.findAll().size() > 0) {
            return
        }// fine del blocco if

        //--avviso di avanzamento
        println('Inizio creazione tabelle')

        //--recupera la mappa completa
        righe = LibFile.leggeCsv(DIR_PATH + nomeFile)

        if (righe && righe.size() > 0) {
            righe?.each {
                if (it instanceof Map) {
                    mappa = (Map) it
                }// fine del blocco if

                if (mappa) {
                    if (mappa.CATEGORIA) {
                        creaCategoria(fixNome(mappa.CATEGORIA))
                    }// fine del blocco if

                    if (mappa.SEDE) {
                        creaProvincia(fixNome(mappa.SEDE))
                    }// fine del blocco if

                    if (mappa.COMUNE) {
                        creaComune(fixNome(mappa.COMUNE))
                    }// fine del blocco if

                    if (mappa.MODELLO) {
                        creaModello(fixNome(mappa.MODELLO))
                    }// fine del blocco if

                    if (mappa.DISPPUNTOBLU) {
                        creaDisponibilita(fixNome(mappa.DISPPUNTOBLU))
                    }// fine del blocco if

                    if (mappa.COLLOCAZIONE) {
                        creaCollocazione(fixNome(mappa.COLLOCAZIONE))
                    }// fine del blocco if

                    if (mappa.SCADELETTRODI) {
                        creaElettrodo(fixNome(mappa.SCADELETTRODI))
                    }// fine del blocco if

                    if (mappa.SCADBATT) {
                        creaBatteria(fixNome(mappa.SCADBATT))
                    }// fine del blocco if

                    if (mappa.ULTIMOCORSO) {
                        creaCorso(fixNome(mappa.ULTIMOCORSO))
                    }// fine del blocco if

                    if (mappa.STATO) {
                        creaStato(fixNome(mappa.STATO))
                    }// fine del blocco if
                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if

        //--avviso di avanzamento
        println('Fine creazione tabelle')

        newVersione('Tabelle', 'Creazione iniziale delle tabelle.')
    }// fine del metodo

    //--creazione delle localizzazioni dal file di excel
    //--li crea SOLO se non esistono già
    private static void creaDaeDaExcel() {
        Dae dae
        String nomeFile = 'dae'
        def righe
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
        float lat = 0.0
        String longitudine = ''
        float lon = 0.0
        Map mappa

        //--prosegue solo se il database è vuoto
        if (Dae.findAll().size() > 0) {
            return
        }// fine del blocco if

        //--avviso di avanzamento
        println('Inizio creazione defibrillatori')

        //--recupera la mappa completa
        righe = LibFile.leggeCsv(DIR_PATH + nomeFile)

        righe?.each {
            mappa = (Map) it
            if (mappa) {
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

                categoria = null
                provincia = null
                comune = null
                modello = null
                disponibilita = null
                collocazione = null
                elettrodi = null
                batteria = null
                corso = null
                stato = null
                lat = 0.0
                lon = 0.0

                if (mappa.CATEGORIA) {
                    categoria = Categoria.findByNome(fixNome(mappa.CATEGORIA))
                }// fine del blocco if

                if (mappa.SEDE) {
                    provincia = Provincia.findByNome(fixNome(mappa.SEDE))
                }// fine del blocco if

                if (mappa.NOME) {
                    nome = fixNome(mappa.NOME)
                }// fine del blocco if

                if (mappa.INDIRIZZO) {
                    indirizzo = fixNome(mappa.INDIRIZZO)
                }// fine del blocco if

                if (mappa.COMUNE) {
                    comune = Comune.findByNome(fixNome(mappa.COMUNE))
                }// fine del blocco if

                if (mappa.MODELLO) {
                    modello = Modello.findByNome(fixNome(mappa.MODELLO))
                }// fine del blocco if

                if (mappa.NSERIE) {
                    serie = fixNome(mappa.NSERIE)
                }// fine del blocco if

                if (mappa.REFERENTE) {
                    riferimento = fixNome(mappa.REFERENTE)
                }// fine del blocco if

                if (mappa.TEL) {
                    telefono = fixNome(mappa.TEL)
                }// fine del blocco if

                if (mappa.MAIL) {
                    mail = fixNome(mappa.MAIL)
                }// fine del blocco if

                if (mappa.TELPERPUNTOBLU) {
                    telpuntoblu = fixNome(mappa.TELPERPUNTOBLU)
                }// fine del blocco if

                if (mappa.DISPPUNTOBLU) {
                    disponibilita = Disponibilita.findByNome(fixNome(mappa.DISPPUNTOBLU))
                }// fine del blocco if

                if (mappa.COLLOCAZIONE) {
                    collocazione = Collocazione.findByNome(fixNome(mappa.COLLOCAZIONE))
                }// fine del blocco if

                if (mappa.SEDEPRECISADAE) {
                    località = fixNome(mappa.SEDEPRECISADAE)
                }// fine del blocco if

                if (mappa.SCADELETTRODI) {
                    elettrodi = Elettrodo.findByNome(fixNome(mappa.SCADELETTRODI))
                }// fine del blocco if

                if (mappa.SCADBATT) {
                    batteria = Batteria.findByNome(fixNome(mappa.SCADBATT))
                }// fine del blocco if

                if (mappa.TECA) {
                    teca = fixNome(mappa.TECA)
                }// fine del blocco if

                if (mappa.ULTIMAVERIFICA) {
                    ultimaVerifica = fixNome(mappa.ULTIMAVERIFICA)
                }// fine del blocco if

                if (mappa.CARTELLI) {
                    cartelli = fixNome(mappa.CARTELLI)
                }// fine del blocco if

                if (mappa.ULTIMOCORSO) {
                    corso = Corso.findByNome(fixNome(mappa.ULTIMOCORSO))
                }// fine del blocco if

                if (mappa.STATO) {
                    stato = Stato.findByNome(fixNome(mappa.STATO))
                }// fine del blocco if

                if (mappa.latitudine) {
                    latitudine = mappa.latitudine.trim()
                    latitudine = Lib.levaApicetti(latitudine)
                    lat = Float.valueOf(latitudine)
                }// fine del blocco if

                if (mappa.longitudine) {
                    longitudine = mappa.longitudine.trim()
                    longitudine = Lib.levaApicetti(longitudine)
                    lon = Float.valueOf(longitudine)
                }// fine del blocco if

                dae = new Dae()
                if (dae) {
                    if (categoria) {
                        dae.cat = categoria
                    }// fine del blocco if
                    if (provincia) {
                        dae.prov = provincia
                    }// fine del blocco if
                    if (nome) {
                        dae.nome = nome
                    }// fine del blocco if
                    if (indirizzo) {
                        dae.indirizzo = indirizzo
                    }// fine del blocco if
                    if (comune) {
                        dae.comune = comune
                    }// fine del blocco if
                    if (modello) {
                        dae.modello = modello
                    }// fine del blocco if
                    if (serie) {
                        dae.serie = serie
                    }// fine del blocco if
                    if (riferimento) {
                        dae.rif = riferimento
                    }// fine del blocco if
                    if (telefono) {
                        dae.tel = telefono
                    }// fine del blocco if
                    if (mail) {
                        dae.mail = mail
                    }// fine del blocco if
                    if (telpuntoblu) {
                        dae.telpuntoblu = telpuntoblu
                    }// fine del blocco if
                    if (disponibilita) {
                        dae.disp = disponibilita
                    }// fine del blocco if
                    if (collocazione) {
                        dae.coll = collocazione
                    }// fine del blocco if
                    if (località) {
                        dae.loc = località
                    }// fine del blocco if
                    if (elettrodi) {
                        dae.elettrodi = elettrodi
                    }// fine del blocco if
                    if (batteria) {
                        dae.batteria = batteria
                    }// fine del blocco if
                    if (teca) {
                        dae.teca = teca
                    }// fine del blocco if
                    if (ultimaVerifica) {
                        dae.verifica = ultimaVerifica
                    }// fine del blocco if
                    if (corso) {
                        dae.corso = corso
                    }// fine del blocco if
                    if (stato) {
                        dae.stato = stato
                    }// fine del blocco if
                    if (lat) {
                        dae.lat = lat
                    }// fine del blocco if
                    if (lon) {
                        dae.lon = lon
                    }// fine del blocco if

                    dae.save(flush: true)
                }// fine del blocco if
            }// fine del blocco if
        } // fine del ciclo each

        //--avviso di avanzamento
        println('Fine creazione defibrillatori')

        newVersione('Elenco DAE', 'Creazione iniziale delle localizzazioni.')
    }// fine del metodo

    //--crea il singolo record di categoria
    private static void creaCategoria(String categoriaTxt) {
        if (categoriaTxt) {
            Categoria.findOrCreateByNome(categoriaTxt).save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--crea il singolo record di provincia
    private static void creaProvincia(String provinciaTxt) {
        Provincia provincia

        if (provinciaTxt) {
            provincia = Provincia.findOrCreateByNome(provinciaTxt)
            provincia.sigla = Lib.primaMaiuscola(provinciaTxt)
            provincia.save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--crea il singolo record di comune
    private static void creaComune(String comuneTxt) {
        if (comuneTxt) {
            Comune.findOrCreateByNome(comuneTxt).save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--crea il singolo record di modello
    private static void creaModello(String modelloTxt) {
        if (modelloTxt) {
            Modello.findOrCreateByNome(modelloTxt).save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--crea il singolo record di disponibilità
    private static void creaDisponibilita(String disponibilitaTxt) {
        if (disponibilitaTxt) {
            Disponibilita.findOrCreateByNome(disponibilitaTxt).save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--crea il singolo record di collocazione
    private static void creaCollocazione(String collocazioneTxt) {
        if (collocazioneTxt) {
            Collocazione.findOrCreateByNome(collocazioneTxt).save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--crea il singolo record di scadenza elettrodi
    private static void creaElettrodo(String elettrodoTxt) {
        if (elettrodoTxt) {
            Elettrodo.findOrCreateByNome(elettrodoTxt).save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--crea il singolo record di scadenza batterie
    private static void creaBatteria(String batteriaTxt) {
        if (batteriaTxt) {
            Batteria.findOrCreateByNome(batteriaTxt).save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--crea il singolo record di ultimo corso
    private static void creaCorso(String corsoTxt) {
        if (corsoTxt) {
            Corso.findOrCreateByNome(corsoTxt).save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--crea il singolo record di collocazione
    private static void creaStato(String statoTxt) {
        if (statoTxt) {
            Stato.findOrCreateByNome(statoTxt).save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--patch ordine alfabetico comuni
    private static void patchOrdineComuni() {
        def lista
        String nome

        lista = Comune.executeQuery("select nome from Comune order by nome asc")

        Comune.executeUpdate("delete Comune c")

        lista?.each {
            nome = (String) it
            Comune.findOrCreateByNome(nome).save(flush: true)
        } // fine del ciclo each

        newVersione('Comuni', "Ordinati alfabeticamente.")
    }// fine del metodo

    //--patch sulle categorie
    private static void patchCategorie(boolean registraVersione) {

        patchCat("Forze dell'ordine", "Forze di polizia")
        patchCat("Scuole ed istituti", "Scuole/istituti")

        if (registraVersione) {
            newVersione('Categorie', "Patch per la lunghezza di alcune categorie.")
        }// fine del blocco if
    }// fine del metodo

    //--singola patch sulle categorie
    private static void patchCat(String oldNome, String newNome) {
        Categoria categoria

        categoria = Categoria.findByNome(oldNome)
        if (categoria) {
            categoria.nome = newNome
            categoria.save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--patch sulle province
    private static void patchProvince(boolean registraVersione) {

        patchProv("Provincia", "Piacenza")
        patchProv("Citta'", "Piacenza")

        if (registraVersione) {
            newVersione('Province', "Patch per i nomi di alcune province.")
        }// fine del blocco if
    }// fine del metodo

    //--singola patch sulle province
    private static void patchProv(String oldSigla, String newSigla) {
        Provincia provincia

        provincia = Provincia.findBySigla(oldSigla)
        if (provincia) {
            provincia.sigla = newSigla
            provincia.save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--univocità delle province
    private static void provinceUnivoche() {
        Provincia provincia
        Provincia provinciaDoppia
        def lista = Provincia.findAll()
        String sigla
        String nome
        long id = 0
        def listaDoppi
        def listaDae
        Dae dae
        def listaVuoti

        lista?.each {
            provincia = (Provincia) it
            sigla = provincia.sigla
            nome = provincia.nome
            id = provincia.id

            if (nome && nome.length() > 0) {
                listaDoppi = Provincia.findAllBySiglaAndIdNotEqual(sigla, id)
                listaDoppi?.each {
                    provinciaDoppia = (Provincia) it
                    listaDae = Dae.findAllByProv(provinciaDoppia)
                    listaDae?.each {
                        dae = (Dae) it
                        dae.prov = provincia
                        dae.save(flush: true)
                    } // fine del ciclo each
                    provinciaDoppia.nome = ''
                    provinciaDoppia.save(flush: true)
                } // fine del ciclo each
            }// fine del blocco if
        } // fine del ciclo each

        listaVuoti = Provincia.findAll()
        listaVuoti?.each {
            provincia = (Provincia) it
            nome = provincia.nome

            if (nome && nome.length() > 0) {
            } else {
                provincia.delete(flush: true)
            }// fine del blocco if-else
        } // fine del ciclo each

        newVersione('Province', "Eliminati i doppioni delle province.")
    }// fine del metodo

    //--patch sui comuni
    private static void patchComuni(boolean registraVersione) {
        patchCom("San rocco", "San Rocco al Porto")
        patchCom("Castelsangiovanni", "Castel San Giovanni")
        patchCom("Ponte dell'olio", "Ponte dell'Olio")
        patchCom("San giorgio", "San Giorgio")
        patchCom("San nicolo'", "San Nicolò")
        patchCom("Castell'arquato", "Castell'Arquato")
        patchCom("Saliceto di cadeo", "Saliceto di Cadeo")
        patchCom("Chiavenna landi", "Chiavenna Landi")
        patchCom("Grazzano visconti", "Grazzano Visconti")
        patchCom("San pietro in cerro", "San Pietro in Cerro")

        if (registraVersione) {
            newVersione('Province', "Patch per i nomi di alcuni comuni. Maiuscole nel secondo nome")
        }// fine del blocco if
    }// fine del metodo

    //--singola patch sui comuni
    private static void patchCom(String oldNome, String newNome) {
        Comune comune

        comune = Comune.findByNome(oldNome)
        if (comune) {
            comune.nome = newNome
            comune.save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--creazione di alcuni esempi di localizzazioni
//    private static void creaLocalizzazioni() {
//        creaDae('Agazzano', '', 'via Aldo Moro', 'Carabinieri', Accesso.autopattuglia, Orario.H24, '', '0523 975212', 'Il DAE è sulla pattuglia, il servizio è disponibile H24')
//        creaDae('Alseno', '', 'piazza Bachalet', 'Palazzetto dello Sport', Accesso.privato, Orario.privato, '', '', "all'interno dell'infermeria, è per uso interno")
//        creaDae('Alseno', '', 'via Turati, 1', 'AVIS', Accesso.pubblico, Orario.lunedidomenica1823, 'Romeo Fanti', '333 4218016', 'reperibile Romeo Fanti 3334218016 (lunedì-dom 18-23)')
//        creaDae('Alseno', 'Chiaravalle', 'in Piazza', 'sotto il portico', Accesso.pubblico, Orario.H24, '', '', "è all'esterno sotto al portico, ad uso pubblico")
//        creaDae('Alseno', 'Lusurasco', 'via Molinazzo 1630', 'portico del ristorante', Accesso.pubblico, Orario.H24, '', '', "è all'esterno sotto al portico del ristorante,ad uso pubblico")
//        creaDae('Bettola', '', 'via C.A. dalla Chiesa', 'Carabinieri', Accesso.autopattuglia, Orario.lunedidomenica918, '', '0523 917710', "Il DAE è sulla pattuglia, il servizio è disponibile dal lunedì alla domenica ore 9-18")
//        creaDae('Bettola', '', 'viale Vittoria, 56', 'farmacia Bianchi', Accesso.pubblico, Orario.limitato, 'Marco Bianchi', '0523 917827 ', "reperibile Marco Bianchi tel 0523917827 orario farmacia")
//    }// fine del metodo

//    private static void creaDae(String comune,
//                                String fraz,
//                                String loc,
//                                String nome,
//                                Accesso accesso,
//                                Orario orario,
//                                String rif,
//                                String tel,
//                                String note) {
//        Dae dae
//        Provincia provincia = Provincia.piacenza
//
//        dae = Dae.findOrCreateByProvAndComuneAndFrazAndLoc(provincia, comune, fraz, loc)
//        if (dae) {
//            dae.prov = provincia
//            dae.comune = comune
//            dae.fraz = fraz
//            dae.loc = loc
//            dae.nome = nome
//            dae.accesso = accesso
//            dae.orario = orario
//            dae.rif = rif
//            dae.tel = tel
//            dae.note = note
//
//            dae.save(flush: true)
//        }// fine del blocco if
//
//    }// fine del metodo

    //--crea un utente con ruolo, nick e password indicati
    //--crea la tavola di incrocio
    //--lo crea SOLO se non esiste già
    private static User newUtente(String siglaRuolo, String username, String password) {
        User utente = null
        Role ospiteRole = Role.findOrCreateByAuthority(Cost.ROLE_OSPITE).save(failOnError: true)
        Role ruolo = Role.findByAuthority(siglaRuolo)

        if (username && ruolo) {
            utente = User.findOrCreateByUsername(username)
            utente.password = password
            utente.enabled = true
            utente.save(failOnError: true)
            if (utente) {
                UserRole.findOrCreateByRoleAndUser(ruolo, utente).save(failOnError: true)
                if (!siglaRuolo.equals(Cost.ROLE_OSPITE)) {
                    UserRole.findOrCreateByRoleAndUser(ospiteRole, utente).save(failOnError: true)
                }// fine del blocco if
            }// fine del blocco if
        }// fine del blocco if

        return utente
    }// fine del metodo

    //--controlla la versione installat
    private static boolean installaVersione(int numeroVersioneDaInstallare) {
        boolean installa = false
        int numeroVersioneEsistente = getVersione()

        if (numeroVersioneDaInstallare > numeroVersioneEsistente) {
            installa = true
        }// fine del blocco if

        return installa
    }// fine del metodo

    private static int getVersione() {
        int numero = 0
        def lista = Versione.findAll("from Versione order by numero desc")

        if (lista && lista.size() > 0) {
            numero = lista[0].numero
        }// fine del blocco if

        return numero
    }// fine del metodo

    //--crea una versione
    //--lo crea SOLO se non esiste già
    private static void newVersione(String titolo, String descrizione) {
        Versione versione
        int numero = getVersione() + 1
        Date giorno = new Date()

        versione = new Versione()
        versione.numero = numero
        versione.giorno = giorno
        versione.titolo = titolo
        versione.descrizione = descrizione
        versione.save(flush: true)

    }// fine del metodo

    //--aggiunta del campo codice
    private static void addCodice() {
        String update
        def lista
        Dae dae

        lista = Dae.findAll()
        lista?.each {
            dae = (Dae) it
            dae.codice = dae.id
            dae.save(flush: true)
        } // fine del ciclo each

        newVersione('Database', "Aggiunta del campo codice.")
    }// fine del metodo

    //--reso unico il campo codice
    private static void codiceUnico() {
        newVersione('Database', "Reso unico il campo codice.")
    }// fine del metodo

    //--elimina l'apostrofo
    //--localita' ... liberta' ...
    private static void eliminazioneApostrofo(boolean registraVersione) {
        def lista
        Dae dae
        String nome
        String indirizzo
        String riferimento
        String localita
        ArrayList listaTag = new ArrayList()
        ArrayList singoloTag
        String vecchio
        String nuovo
        listaTag.add(["Castell'arquato", "CastellArquato"]) //@todo da rimettere
        listaTag.add(["castell'arquato", "castellArquato"]) //@todo da rimettere
        listaTag.add(["castell'Arquato", "castellArquato"]) //@todo da rimettere
        listaTag.add(["d'antona", "diantona"]) //@todo da rimettere
        listaTag.add(["all'ingresso", "ingresso"]) //@todo da rimettere
        listaTag.add(["nell'oratorio", "in oratorio"]) //@todo da rimettere
        listaTag.add(["dell'entrata", "entrata"]) //@todo da rimettere
        listaTag.add(["all'entrata", "entrata"]) //@todo da rimettere
        listaTag.add(["nell'ufficio", "in ufficio"]) //@todo da rimettere

        listaTag.add(["localita'", "località"])
        listaTag.add(["liberta'", "libertà"])
        listaTag.add(["odesta'", "odestà"])
        listaTag.add(["niversita'", "niversità"])
        listaTag.add(["noe'", "noè"])
        listaTag.add(["demalde'", "demaldè"])
        listaTag.add(["nicolo'", "nicolò"])
        listaTag.add(["trinita'", "trinità"])
        listaTag.add(["caffe'", "caffè"])

        lista = Dae.findAll()
        lista?.each {
            dae = (Dae) it
            nome = dae.nome
            indirizzo = dae.indirizzo
            riferimento = dae.rif
            localita = dae.loc
            listaTag?.each {
                singoloTag = (ArrayList) it
                vecchio = singoloTag[0]
                nuovo = singoloTag[1]
                if (nome && nome.contains(vecchio)) {
                    nome = nome.replace(vecchio, nuovo)
                    dae.nome = nome
                    dae.save(flush: true)
                }// fine del blocco if
                if (indirizzo && indirizzo.contains(vecchio)) {
                    indirizzo = indirizzo.replace(vecchio, nuovo)
                    dae.indirizzo = indirizzo
                    dae.save(flush: true)
                }// fine del blocco if
                if (riferimento && riferimento.contains(vecchio)) {
                    riferimento = riferimento.replace(vecchio, nuovo)
                    dae.rif = riferimento
                    dae.save(flush: true)
                }// fine del blocco if
                if (localita && localita.contains(vecchio)) {
                    localita = localita.replace(vecchio, nuovo)
                    dae.loc = localita
                    dae.save(flush: true)
                }// fine del blocco if
            } // fine del ciclo each
        } // fine del ciclo each

        if (registraVersione) {
            newVersione('Testo', "Eliminazione apostrofo dai testi")
        }// fine del blocco if
    }// fine del metodo

    //--univocità delle disponibilità
    private static void disponibilitaUnivoche() {
        Disponibilita disponibilita
        Disponibilita disponibilitaDoppia
        def lista = Disponibilita.findAll()
        String nome
        Dae dae
        long id = 0
        def listaDae
        def listaDoppi
        def listaVuoti

        lista?.each {
            disponibilita = (Disponibilita) it
            nome = disponibilita.nome
            id = disponibilita.id

            if (nome && nome.length() > 0) {
                listaDoppi = Disponibilita.findAllByNomeAndIdNotEqual(nome, id)
                listaDoppi?.each {
                    disponibilitaDoppia = (Disponibilita) it
                    listaDae = Dae.findAllByDisp(disponibilitaDoppia)
                    listaDae?.each {
                        dae = (Dae) it
                        dae.disp = disponibilita
                        dae.save(flush: true)
                    } // fine del ciclo each
                    disponibilitaDoppia.nome = ''
                    disponibilitaDoppia.save(flush: true)
                } // fine del ciclo each
            }// fine del blocco if
        } // fine del ciclo each

        listaVuoti = Disponibilita.findAll()
        listaVuoti?.each {
            disponibilita = (Disponibilita) it
            nome = disponibilita.nome

            if (nome && nome.length() > 0) {
            } else {
                disponibilita.delete(flush: true)
            }// fine del blocco if-else
        } // fine del ciclo each

        newVersione('Disponibilità', "Eliminati i doppioni delle disponibilità.")
    }// fine del metodo

    //--aggiunta plugin crono di Quartz
    private static void aggiuntaCrono() {
        newVersione('Plugin', "Aggiunta plugin di Quartz (crono) per refresh video.")
    }// fine del metodo

    //--aggiunta plugin della posta
    private static void aggiuntaMail() {
        newVersione('Plugin', "Aggiunta plugin della posta.")
    }// fine del metodo

    //--aggiunta plugin logo
    //--creazione Eventi base da usare per il logo
    private static void setupLogo() {
        def listaEventi = Evento.findAll()

        if (!listaEventi) {
            Evento.findOrCreateByNome(Cost.EVENTO_NEW).save(failOnError: true)
            Evento.findOrCreateByNome(Cost.EVENTO_EDIT).save(failOnError: true)
            Evento.findOrCreateByNome(Cost.EVENTO_DELETE).save(failOnError: true)
        }// fine del blocco if

        newVersione('Plugin', "Aggiunta plugin logo.")
    }// fine del metodo

    //--aggiunta dei campi dateCreated e lastUpdated
    //--inserimento di una data iniziale
    private static void creazioneDataModifica() {
        Timestamp adesso = new Date().toTimestamp()
        def lista = Dae.findAll()
        Dae dae

        if (lista) {
            lista?.each {
                dae = (Dae) it
                dae.dateCreated = adesso
                dae.lastUpdated = adesso
                dae.save(flush: true)
            } // fine del ciclo each
        }// fine del blocco if

        newVersione('lastUpdated', "DAE: inserimento valore iniziale in tutti i records esistenti.")
    }// fine del metodo

    //--valore campo codice
    private static void inserimentoCodiceDae() {
        newVersione('Dae', "Inserimento valore progressivo campo codice alla creazione del record.")
    }// fine del metodo

    //--elimina l'apostrofo
    //--proprieta'
    //--COMODATO in minuscolo
    private static void fixProprieta(boolean registraVersione) {
        def listaStato = Stato.findAll()
        Stato stato
        String nome
        String tagOld = "Proprieta'"
        String tagNew = "Proprietà"

        listaStato?.each {
            stato = (Stato) it
            nome = stato.nome

            if (nome && nome.contains(tagOld)) {
                nome = nome.replace(tagOld, tagNew)
                stato.nome = nome
                stato.save(flush: true)
            }// fine del blocco if
        } // fine del ciclo each

        if (registraVersione) {
            newVersione('Stato', "Eliminazione apostrofo della parola proprieta'.")
        }// fine del blocco if
    }// fine del metodo

    //--elimina l'apostrofo
    //--Castell'arquato
    private static void fixComuneArquato(boolean registraVersione) {
        def listaComuni = Comune.findAll()
        Comune comune
        String nome
        String tagOld = "Castell'arquato"
        String tagOld2 = "Castell'Arquato"
        String tagNew = "CastellArquato"
        String tag2Old = "Ponte dell'olio"
        String tag2Old2 = "Ponte dell'Olio"
        String tag2New = "Ponte dellOlio"

        listaComuni?.each {
            comune = (Comune) it
            nome = comune.nome

            if (nome && nome.contains(tagOld)) {
                nome = nome.replace(tagOld, tagNew)
                comune.nome = nome
                comune.save(flush: true)
            }// fine del blocco if
            if (nome && nome.contains(tagOld2)) {
                nome = nome.replace(tagOld2, tagNew)
                comune.nome = nome
                comune.save(flush: true)
            }// fine del blocco if
            if (nome && nome.contains(tag2Old)) {
                nome = nome.replace(tag2Old, tag2New)
                comune.nome = nome
                comune.save(flush: true)
            }// fine del blocco if
            if (nome && nome.contains(tag2Old2)) {
                nome = nome.replace(tag2Old2, tag2New)
                comune.nome = nome
                comune.save(flush: true)
            }// fine del blocco if
        } // fine del ciclo each

        if (registraVersione) {
            newVersione('Comune', "Eliminazione apostrofo da Castell'arquato e Ponte dell'olio.")
        }// fine del blocco if
    }// fine del metodo

    //--aggiunta preferenza booleana di controllo dati definitivi
    private static void addPreferenzaDatiDefinitivi() {
        Preferenze pref = Preferenze.findOrCreateByCodeAndTypeAndValue(Cost.PREF_DATI_DEFINITIVI, 'boolean', 'false')
        if (pref) {
            pref.save(flush: true)
        }// fine del blocco if

        newVersione('Preferenze', "Aggiunta preferenza booleana di controllo dati definitivi")
    }// fine del metodo

    //--ricontrolla l'apostrofo dopo un nuovo inserimento dati grezzi
    private static void ricontrollaApostrofo() {
        eliminazioneApostrofo(false)
        fixProprieta(false)
        fixComuneArquato(false)
        patchCategorie(false)
        patchProvince(false)
        patchComuni(false)

        newVersione('Testo', "Ricontrollato l'apostrofo dopo un nuovo inserimento dati grezzi")
    }// fine del metodo


    private static String fixNome(def nomeGrezzo) {
        String nomeFinale = nomeGrezzo

        if (nomeGrezzo) {
            nomeGrezzo = nomeGrezzo.trim()
            nomeFinale = Lib.primaMaiuscola(nomeGrezzo)
        }// fine del blocco if

        return nomeFinale
    }// fine del metodo

    def destroy = {
    }// fine della closure

}// fine della classe
