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

class CorsoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [corsoInstanceList: Corso.list(params), corsoInstanceTotal: Corso.count()]
    } // fine del metodo

    def create() {
        [corsoInstance: new Corso(params)]
    } // fine del metodo

    def save() {
        def corsoInstance = new Corso(params)

        if (!corsoInstance.save(flush: true)) {
            render(view: 'create', model: [corsoInstance: corsoInstance])
            return
        }// fine del blocco if e fine anticipata del metodo

        flash.message = message(code: 'default.created.message', args: [message(code: 'corso.label', default: 'Corso'), corsoInstance.id])
        redirect(action: 'show', id: corsoInstance.id)
    } // fine del metodo

    def show(Long id) {
        def corsoInstance = Corso.get(id)

        if (!corsoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'corso.label', default: 'Corso'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        [corsoInstance: corsoInstance]
    } // fine del metodo

    def edit(Long id) {
        def corsoInstance = Corso.get(id)

        if (!corsoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'corso.label', default: 'Corso'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        [corsoInstance: corsoInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def corsoInstance = Corso.get(id)

        if (!corsoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'corso.label', default: 'Corso'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        if (version != null) {
            if (corsoInstance.version > version) {
                corsoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'corso.label', default: 'Corso')] as Object[],
                        "Another user has updated this Corso while you were editing")
                render(view: 'edit', model: [corsoInstance: corsoInstance])
                return
            }// fine del blocco if e fine anticipata del metodo
        }// fine del blocco if

        corsoInstance.properties = params

        if (!corsoInstance.save(flush: true)) {
            render(view: 'edit', model: [corsoInstance: corsoInstance])
            return
        }// fine del blocco if e fine anticipata del metodo

        flash.message = message(code: 'default.updated.message', args: [message(code: 'corso.label', default: 'Corso'), corsoInstance.id])
        redirect(action: 'show', id: corsoInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def corsoInstance = Corso.get(id)
        if (!corsoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'corso.label', default: 'Corso'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        try {
            corsoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'corso.label', default: 'Corso'), id])
            redirect(action: 'list')
        }// fine del blocco try
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'corso.label', default: 'Corso'), id])
            redirect(action: 'show', id: id)
        }// fine del blocco catch
    } // fine del metodo

} // fine della controller classe
