package it.algos.myvitaback

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 14-6-13
 * Time: 14:33
 */
public enum Accesso {

    pubblico('pubblico'),
    privato('privato'),
    interno('interno'),
    esterno('esterno'),
    autopattuglia('autopattuglia')

    String sigla

    Accesso(String sigla) {
        this.sigla = sigla
    }// fine del metodo costruttore

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        sigla
    } // end of toString

}// fine della classe Enumeration
