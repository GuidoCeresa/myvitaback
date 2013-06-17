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

import org.springframework.dao.DataIntegrityViolationException

class CollocazioneController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [collocazioneInstanceList: Collocazione.list(params), collocazioneInstanceTotal: Collocazione.count()]
    } // fine del metodo

    def create() {
        [collocazioneInstance: new Collocazione(params)]
    } // fine del metodo

    def save() {
        def collocazioneInstance = new Collocazione(params)

        if (!collocazioneInstance.save(flush: true)) {
            render(view: 'create', model: [collocazioneInstance: collocazioneInstance])
            return
        }// fine del blocco if e fine anticipata del metodo

        flash.message = message(code: 'default.created.message', args: [message(code: 'collocazione.label', default: 'Collocazione'), collocazioneInstance.id])
        redirect(action: 'show', id: collocazioneInstance.id)
    } // fine del metodo

    def show(Long id) {
        def collocazioneInstance = Collocazione.get(id)

        if (!collocazioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'collocazione.label', default: 'Collocazione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        [collocazioneInstance: collocazioneInstance]
    } // fine del metodo

    def edit(Long id) {
        def collocazioneInstance = Collocazione.get(id)

        if (!collocazioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'collocazione.label', default: 'Collocazione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        [collocazioneInstance: collocazioneInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def collocazioneInstance = Collocazione.get(id)

        if (!collocazioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'collocazione.label', default: 'Collocazione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        if (version != null) {
            if (collocazioneInstance.version > version) {
                collocazioneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'collocazione.label', default: 'Collocazione')] as Object[],
                        "Another user has updated this Collocazione while you were editing")
                render(view: 'edit', model: [collocazioneInstance: collocazioneInstance])
                return
            }// fine del blocco if e fine anticipata del metodo
        }// fine del blocco if

        collocazioneInstance.properties = params

        if (!collocazioneInstance.save(flush: true)) {
            render(view: 'edit', model: [collocazioneInstance: collocazioneInstance])
            return
        }// fine del blocco if e fine anticipata del metodo

        flash.message = message(code: 'default.updated.message', args: [message(code: 'collocazione.label', default: 'Collocazione'), collocazioneInstance.id])
        redirect(action: 'show', id: collocazioneInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def collocazioneInstance = Collocazione.get(id)
        if (!collocazioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'collocazione.label', default: 'Collocazione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        try {
            collocazioneInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'collocazione.label', default: 'Collocazione'), id])
            redirect(action: 'list')
        }// fine del blocco try
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'collocazione.label', default: 'Collocazione'), id])
            redirect(action: 'show', id: id)
        }// fine del blocco catch
    } // fine del metodo

} // fine della controller classe
