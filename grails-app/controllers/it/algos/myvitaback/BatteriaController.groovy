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

class BatteriaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [batteriaInstanceList: Batteria.list(params), batteriaInstanceTotal: Batteria.count()]
    } // fine del metodo

    def create() {
        [batteriaInstance: new Batteria(params)]
    } // fine del metodo

    def save() {
        def batteriaInstance = new Batteria(params)

        if (!batteriaInstance.save(flush: true)) {
            render(view: 'create', model: [batteriaInstance: batteriaInstance])
            return
        }// fine del blocco if e fine anticipata del metodo

        flash.message = message(code: 'default.created.message', args: [message(code: 'batteria.label', default: 'Batteria'), batteriaInstance.id])
        redirect(action: 'show', id: batteriaInstance.id)
    } // fine del metodo

    def show(Long id) {
        def batteriaInstance = Batteria.get(id)

        if (!batteriaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batteria.label', default: 'Batteria'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        [batteriaInstance: batteriaInstance]
    } // fine del metodo

    def edit(Long id) {
        def batteriaInstance = Batteria.get(id)

        if (!batteriaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batteria.label', default: 'Batteria'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        [batteriaInstance: batteriaInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def batteriaInstance = Batteria.get(id)

        if (!batteriaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batteria.label', default: 'Batteria'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        if (version != null) {
            if (batteriaInstance.version > version) {
                    batteriaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'batteria.label', default: 'Batteria')] as Object[],
                            "Another user has updated this Batteria while you were editing")
                render(view: 'edit', model: [batteriaInstance: batteriaInstance])
                return
            }// fine del blocco if e fine anticipata del metodo
        }// fine del blocco if

        batteriaInstance.properties = params

        if (!batteriaInstance.save(flush: true)) {
            render(view: 'edit', model: [batteriaInstance: batteriaInstance])
            return
        }// fine del blocco if e fine anticipata del metodo

        flash.message = message(code: 'default.updated.message', args: [message(code: 'batteria.label', default: 'Batteria'), batteriaInstance.id])
        redirect(action: 'show', id: batteriaInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def batteriaInstance = Batteria.get(id)
        if (!batteriaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'batteria.label', default: 'Batteria'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        try {
            batteriaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'batteria.label', default: 'Batteria'), id])
            redirect(action: 'list')
        }// fine del blocco try
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'batteria.label', default: 'Batteria'), id])
            redirect(action: 'show', id: id)
        }// fine del blocco catch
    } // fine del metodo

} // fine della controller classe
