package it.algos.myvitaback

import org.grails.plugins.csv.CSVMapReader

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 14-6-13
 * Time: 14:35
 */
class LibFile {

    private static String csvSuffix = '.csv'

    // Legge un file formattato csv
    // Legge la prima riga dei titoli SOLO per creare le mappe
    // Crea una mappa (titolo=valore) per ogni riga (esclusi i titoli)
    // Titolo e valore sono SEMPRE stringhe
    // Restituisce, per ogni riga, una mappa con TUTTE le colonne
    // Eventualmente vuote
    public static ArrayList leggeCsv(String filePath) {
        ArrayList righe = null
        InputStreamReader stream
        CSVMapReader reader

        // Controllo suffisso
        if (!filePath.endsWith(csvSuffix)) {
            filePath += csvSuffix
        }// fine del blocco if

        try { // prova ad eseguire il codice
            stream = new InputStreamReader(new URL(filePath).openStream(), "UTF-8")
            if (stream) {
                reader = new CSVMapReader(stream)
            }// fine del blocco if
        } catch (Exception unErrore) { // intercetta l'errore
            //log.error 'Non ho trovato il file '+filePath
        }// fine del blocco try-catch

        if (reader) {
            righe=new ArrayList()
            reader.each {   map->
                righe.add(map)
            } // fine del ciclo each
        }// fine del blocco if

        // valore di ritorno
        return righe
    }// fine della closure

} // fine della classe
