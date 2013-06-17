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

class UtenteService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def springSecurityService

    //--controlla se l'utente attualmente loggato è un programmatore
    public boolean isLoggatoProgrammatore() {
       def a=Cost.ROLE_PROG
        return this.isLoggatoNelRuolo(Cost.ROLE_PROG)
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un admin
    public boolean isLoggatoAdmin() {
        return this.isLoggatoNelRuolo(Cost.ROLE_ADMIN)
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un admin
    //--oppure un ruolo superiore (custode o programmatore)
    public boolean isLoggatoAdminOrMore() {
        return this.isLoggatoAdmin() || this.isLoggatoProgrammatore()
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un milite
    public boolean isLoggatoOspite() {
        return this.isLoggatoNelRuolo(Cost.ROLE_OSPITE)
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un milite
    //--oppure un ruolo superiore (admin o custode o programmatore)
    public boolean isLoggatoOspiteOrMore() {
        return this.isLoggatoOspite() || this.isLoggatoAdminOrMore()
    }// fine del metodo

    //--controlla se l'utente attualmente loggato ha il ruolo previsto
    private boolean isLoggatoNelRuolo(String ruoloPrevisto) {
        boolean risposta = false
        def authentication = springSecurityService.authentication
        def ruoli
        String ruolo

        if (authentication) {
            ruoli = authentication.authorities
            ruoli?.each {
                ruolo = (String) it
                if (ruolo.equals(ruoloPrevisto)) {
                    risposta = true
                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if

        return risposta
    }// fine del metodo

} // end of Service Class
