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

package ${packageName}

import org.springframework.dao.DataIntegrityViolationException

class ${className}Controller {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [${propertyName}List: ${className}.list(params), ${propertyName}Total: ${className}.count()]
    } // fine del metodo

    def create() {
        [${propertyName}: new ${className}(params)]
    } // fine del metodo

    def save() {
        def ${propertyName} = new ${className}(params)

        if (!${propertyName}.save(flush: true)) {
            render(view: 'create', model: [${propertyName}: ${propertyName}])
            return
        }// fine del blocco if e fine anticipata del metodo

        flash.message = message(code: 'default.created.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])
        redirect(action: 'list')
    } // fine del metodo

    def show(Long id) {
        def ${propertyName} = ${className}.get(id)

        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        [${propertyName}: ${propertyName}]
    } // fine del metodo

    def edit(Long id) {
        def ${propertyName} = ${className}.get(id)

        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        [${propertyName}: ${propertyName}]
    } // fine del metodo

    def update(Long id, Long version) {
        def ${propertyName} = ${className}.get(id)

        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        if (version != null) {
            if (${propertyName}.version > version) {<% def lowerCaseName = grails.util.GrailsNameUtils.getPropertyName(className) %>
                    ${propertyName}.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: '${domainClass.propertyName}.label', default: '${className}')] as Object[],
                            "Another user has updated this ${className} while you were editing")
                render(view: 'edit', model: [${propertyName}: ${propertyName}])
                return
            }// fine del blocco if e fine anticipata del metodo
        }// fine del blocco if

        ${propertyName}.properties = params

        if (!${propertyName}.save(flush: true)) {
            render(view: 'edit', model: [${propertyName}: ${propertyName}])
            return
        }// fine del blocco if e fine anticipata del metodo

        flash.message = message(code: 'default.updated.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])
        redirect(action: 'list')
    } // fine del metodo

    def delete(Long id) {
        def ${propertyName} = ${className}.get(id)
        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if e fine anticipata del metodo

        try {
            ${propertyName}.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: 'list')
        }// fine del blocco try
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: 'show', id: id)
        }// fine del blocco catch
    } // fine del metodo

} // fine della controller classe
