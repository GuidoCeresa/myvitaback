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

class ProvinciaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [provinciaInstanceList: Provincia.list(params), provinciaInstanceTotal: Provincia.count()]
    } // fine del metodo

    def create() {
        [provinciaInstance: new Provincia(params)]
    } // fine del metodo

    def save() {
        def provinciaInstance = new Provincia(params)

        if (!provinciaInstance.save(flush: true)) {
            render(view: 'create', model: [provinciaInstance: provinciaInstance])
            return
        }// fine del blocco if e fine anticipata del metodo

        flash.message = message(code: 'default.created.message', args: [message(code: 'provincia.label', default: 'Provincia'), provinciaInstance.id])
        redirect(action: 'show', id: provinciaInstance.id)
    } // fine del metodo

    def show(Long id) {
        def provinciaInstance = Provincia.get(id)

        if (!provinciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        [provinciaInstance: provinciaInstance]
    } // fine del metodo

    def edit(Long id) {
        def provinciaInstance = Provincia.get(id)

        if (!provinciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        [provinciaInstance: provinciaInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def provinciaInstance = Provincia.get(id)

        if (!provinciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        if (version != null) {
            if (provinciaInstance.version > version) {
                provinciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'provincia.label', default: 'Provincia')] as Object[],
                        "Another user has updated this Provincia while you were editing")
                render(view: 'edit', model: [provinciaInstance: provinciaInstance])
                return
            }// fine del blocco if e fine anticipata del metodo
        }// fine del blocco if

        provinciaInstance.properties = params

        if (!provinciaInstance.save(flush: true)) {
            render(view: 'edit', model: [provinciaInstance: provinciaInstance])
            return
        }// fine del blocco if e fine anticipata del metodo

        flash.message = message(code: 'default.updated.message', args: [message(code: 'provincia.label', default: 'Provincia'), provinciaInstance.id])
        redirect(action: 'show', id: provinciaInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def provinciaInstance = Provincia.get(id)
        if (!provinciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        try {
            provinciaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'provincia.label', default: 'Provincia'), id])
            redirect(action: 'list')
        }// fine del blocco try
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'provincia.label', default: 'Provincia'), id])
            redirect(action: 'show', id: id)
        }// fine del blocco catch
    } // fine del metodo

} // fine della controller classe
