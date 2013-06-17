package it.algos.myvitaback

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 14-6-13
 * Time: 14:36
 */
public enum Orario {

    H24('24H'),
    privato('non accessibile'),
    limitato('limitato'),
    lunedivenerdi820('lun/ven 8-20'),
    lunedidomenica1823('lun/dom 18-23'),
    lunedidomenica918('lun/dom 9-18')

    String sigla

    Orario(String sigla) {
        this.sigla = sigla
    }// fine del metodo costruttore

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        sigla
    } // end of toString

}// fine della classe Enumeration
