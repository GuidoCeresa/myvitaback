package it.algos.myvitaback

import java.sql.Timestamp

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 14-6-13
 * Time: 14:34
 */
class Lib {
    private static String aCapo = '\n'

    /**
     * Controlla che la stringa non contenga il carattere "'"
     * @param entrata stringa in ingresso
     * @return uscita string in uscita
     */
    public static String setApice(String entrata) {
        // variabili e costanti locali di lavoro
        String uscita = entrata
        String tag = "'"

        if (entrata) {
            if (entrata.contains(tag)) {
                def stop
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return uscita
    } // fine del metodo statico

    /**
     * Forza il primo carattere della stringa a maiuscolo
     * Restituisce una stringa
     * Un numero ritorna un numero
     * Un nullo ritorna un nullo
     * Un oggetto non stringa ritorna uguale
     *
     * @param entrata stringa in ingresso
     * @return uscita string in uscita
     */
    public static primaMaiuscola(entrata) {
        // variabili e costanti locali di lavoro
        def uscita = entrata
        String primo
        String rimanente

        if (entrata && entrata in String) {
            primo = entrata.substring(0, 1)
            primo = primo.toUpperCase()
            rimanente = entrata.substring(1)
            uscita = primo + rimanente.toLowerCase()
        }// fine del blocco if

        // valore di ritorno
        return uscita
    } // fine del metodo statico

    /**
     * Elimina eventuali apicetti iniziali e finali della stringa
     *
     * @param entrata stringa in ingresso
     * @return uscita stringa in uscita
     */
    public static String levaApicetti(String entrata) {
        // variabili e costanti locali di lavoro
        String uscita = entrata
        String tagIni = '“'
        String tagEnd = '”'

        if (uscita) {
            if (uscita.startsWith(tagIni)) {
                uscita = uscita.substring(1)
            }// fine del blocco if
            if (uscita.endsWith(tagEnd)) {
                uscita = uscita.substring(0, uscita.length() - 1)
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return uscita
    } // fine del metodo statico

    static String getTitoloTabellaSorted(String app, String cont, String sort, String order, String title) {
        return "<th class=\"sortable\"><a href=\"/${app}/${cont}/list?sort=${sort}&amp;order=${order}\">${title}</a></th>"
    }// fine del metodo

    static String getTitoloTabellaNotSorted(String app, String cont, String sort, String order, String title) {
        String testo

        if (order && order.equals('desc')) {
            testo = "<th class=\"sortable sorted desc\"><a href=\"/${app}/${cont}/list?sort=${sort}&amp;order=${order}\">${title}</a></th>"
        } else {
            testo = "<th class=\"sortable sorted asc\"><a href=\"/${app}/${cont}/list?sort=${sort}&amp;order=${order}\">${title}</a></th>"
        }// fine del blocco if-else

        return testo
    }// fine del metodo

    static String getCampoTabella(String app, String cont, long id, def value) {
        return getCampoTabella(app, cont, id, value, 'show')
    }// fine del metodo

    static String getCampoTabella(String app, String cont, long id, def value, String action) {
        String testo = ''
        String ref = "<a href=\"/${app}/${cont}/${action}/${id}\">"

        if (value instanceof Long || value instanceof Boolean || value instanceof String || value instanceof Date) {
            if (value instanceof Long) {
                testo = getCampoTabellaLong(ref, value)
            }// fine del blocco if

            if (value instanceof Boolean) {
                testo = getCampoTabellaBooleano(value)
            }// fine del blocco if

            if (value instanceof String) {
                testo = getCampoTabellaStringa(ref, value)
            }// fine del blocco if

            if (value instanceof Date) {
                if (value instanceof Timestamp) {
                    testo = getCampoTabellaTime(app, cont, id, value)
                } else {
                    testo = getCampoTabellaData(app, cont, id, value)
                }// fine del blocco if-else
            }// fine del blocco if
        } else {
            if (value) {
                testo = getCampoTabellaStringa(ref, value)
            } else {
                testo = getCampoTabellaStringa(ref, '')
            }// fine del blocco if-else
        }// fine del blocco if-else

        return testo
    }// fine del metodo

    static String getCampoTabellaLong(String ref, def value) {
        String testo = ''

        testo += ref
        testo += "${value}"
        testo += "</a>"

        return Lib.tagCella(testo)
    }// fine del metodo

    static String getCampoTabellaBooleano(boolean value) {
        String testo = ''

        if (value) {
            testo = "<input type=\"checkbox\" checked>"
        } else {
            testo = "<input type=\"checkbox\" disabled>"
        }// fine del blocco if-else

        return Lib.tagCella(testo)
    }// fine del metodo

    static String getCampoTabellaStringa(String ref, def value) {
        String testo = ''
        String valore = "${value}"

        testo += ref
        testo += "${value}"
        testo += "</a>"

        return Lib.tagCella(testo)
    }// fine del metodo

    static String getCampoTabellaTime(String app, String cont, long id, def value) {
        String testo
        String timeTxt = value
        timeTxt = timeTxt.substring(0, 16)

        if (isData(value)) {
            testo = getCampoTabellaData(app, cont, id, value)
        } else {
            testo = "<a href=\"/${app}/${cont}/show/${id}\">${timeTxt}</a>"
            testo = Lib.tagCella(testo)
        }// fine del blocco if-else

        return testo
    }// fine del metodo

    static String getCampoTabellaData(String app, String cont, long id, def value) {
        String testo
        String dataTxt = Lib.presentaDataMese(value)

        testo = "<a href=\"/${app}/${cont}/show/${id}\">${dataTxt}</a>"
        return Lib.tagCella(testo)
    }// fine del metodo

    static boolean isData(def value) {
        boolean isData = false
        String tag0 = '0'
        String tag00 = '00'
        String timeTxt = value
        String ore = timeTxt.substring(11, 13)
        String minuti = timeTxt.substring(14, 16)
        String secondi = timeTxt.substring(17, 19)
        String decimi = timeTxt.substring(20, 21)

        if (ore.equals(tag00) && minuti.equals(tag00) && secondi.equals(tag00) && decimi.equals(tag0)) {
            isData = true
        }// fine del blocco if

        return isData
    }// fine del metodo

    /**
     * Presentazione della data.
     */
    def static presentaDataMese(Date data) {
        /* variabili e costanti locali di lavoro */
        String dataFormattata = ''
        GregorianCalendar cal = new GregorianCalendar()
        def giorno
        def mese
        def anno
        String sep = '-'

        try { // prova ad eseguire il codice
            if (data) {
                cal.setTime(data)
                giorno = cal.get(Calendar.DAY_OF_MONTH)
                mese = cal.get(Calendar.MONTH)
                mese++
                mese = Mese.getShort(mese)  //scrive il nome del mese, ma allarga la colonna
                anno = cal.get(Calendar.YEAR)
                anno = anno + ''
                anno = anno.substring(2)

                dataFormattata += giorno
                dataFormattata += sep
                dataFormattata += mese
                dataFormattata += sep
                dataFormattata += anno
            }// fine del blocco if

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return dataFormattata
    }// fine del metodo

    //--Inserisce il testo nel tag della cella (normale)
    public static tagCella(String testoIn) {
        return tag('td', testoIn, 'row', 1)
    } // fine del metodo statico

    //--Inserisce il testo nel tag di un elemento HTML
    public static tag(String tag, String testoIn, String prefixSpan, int span) {
        String testoOut = ''

        testoOut += "<${tag}"
        if (span && span > 1) {
            testoOut += " ${prefixSpan}span=\""
            testoOut += span
            testoOut += '"'
        }// fine del blocco if
        testoOut += '>'

        testoOut += testoIn
        testoOut += "</${tag}>"
        testoOut += aCapo

        return testoOut
    } // fine del metodo statico

} // fine della classe
