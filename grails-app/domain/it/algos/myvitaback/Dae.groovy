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

import java.sql.Timestamp

class Dae {

    /** nomi interni dei campi (ordine non garantito) */
    Categoria cat
    Provincia prov
    String nome
    String indirizzo
    Comune comune
    Modello modello
    String serie
    String rif
    String tel
    String mail
    String telpuntoblu
    Disponibilita disp
    Collocazione coll
    String loc
    Elettrodo elettrodi
    Batteria batteria
    String teca
    String verifica
    String cartelli
    Corso corso
    Stato stato

    float lat
    float lon
    boolean ok
    String note
    int codice
    Timestamp dateCreated // Predefined names by Grails will be filled automatically
    Timestamp lastUpdated // Predefined names by Grails will be filled automatically

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui,
     * viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        comune(nullable: true)
        indirizzo(nullable: true)
        nome(nullable: true)
        loc(nullable: true)
        disp(nullable: true)
        rif(nullable: true)
        telpuntoblu(nullable: true)

        cat(nullable: true)
        prov(nullable: true)
        modello(nullable: true)
        coll(nullable: true)
        tel(nullable: true)
        serie(nullable: true)
        mail(nullable: true)
        elettrodi(nullable: true)
        batteria(nullable: true)
        teca(nullable: true)
        verifica(nullable: true)
        cartelli(nullable: true)
        corso(nullable: true)
        stato(nullable: true)

        ok(nullable: true)
        lat(nullable: true)
        lon(nullable: true)
        note(widget: 'textarea', nullable: true, blank: true)
        codice(nullable: false, unique: false, display: false)
        dateCreated(nullable: true)
        lastUpdated(nullable: true)
    } // end of static constraints

    /**
     * Grails support two tipe of Inheritance:
     * 1) table-per-hierarchy mapping (default)
     * 2) table-per-subclasses
     * By default GORM classes use table-per-hierarchy inheritance mapping.
     * This has the disadvantage that columns cannot have a NOT-NULL constraint applied to them at the database level.
     * If you would prefer to use a table-per-subclass inheritance strategy you can do so as follows:
     *         tablePerHierarchy false
     * The mapping of the root Xyz class specifies that it will not be using table-per-hierarchy mapping for all child classes.
     */
    static mapping = {
        tablePerHierarchy true  //standard
        autoTimestamp true
        note type: 'text'
    } // end of static mapping

    /**
     * valore di testo restituito per una istanza della classe
     * @todo da regolare (obbligatorio)
     */
    String toString() {
        return nome
    } // end of toString

    /**
     * GORM supports the registration of events as methods that get fired
     * when certain events occurs such as deletes, inserts and updates
     * The following is a list of supported events:
     * beforeInsert - Executed before an object is initially persisted to the database
     * beforeUpdate - Executed before an object is updated
     * beforeDelete - Executed before an object is deleted
     * beforeValidate - Executed before an object is validated
     * afterInsert - Executed after an object is persisted to the database
     * afterUpdate - Executed after an object has been updated
     * afterDelete - Executed after an object has been deleted
     * onLoad - Executed when an object is loaded from the database
     */

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
    } // end of def beforeUpdate

    /**
     * metodo chiamato automaticamente da Grails
     * prima di cancellare un record
     */
    def beforeDelete = {
    } // end of def beforeDelete

    /**
     * metodo chiamato automaticamente da Grails
     * prima di convalidare un record
     */
    def beforeValidate = {
    } // end of def beforeDelete

    /**
     * metodo chiamato automaticamente da Grails
     * dopo aver creato un nuovo record
     */
    def afterInsert = {
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * dopo aver registrato un record esistente
     */
    def afterUpdate = {
    } // end of def beforeUpdate

    /**
     * metodo chiamato automaticamente da Grails
     * dopo aver cancellato un record
     */
    def afterDelete = {
    } // end of def beforeDelete

    /**
     * metodo chiamato automaticamente da Grails
     * dopo che il record è stato letto dal database e
     * le proprietà dell'oggetto sono state aggiornate
     */
    def onLoad = {
    } // end of def onLoad

} // fine della domain classe
