package myvitaback

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured([Cost.ROLE_PROG])
class VersioneController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [versioneInstanceList: Versione.list(params), versioneInstanceTotal: Versione.count()]
    }

    def create() {
        [versioneInstance: new Versione(params)]
    }

    def save() {
        def versioneInstance = new Versione(params)
        if (!versioneInstance.save(flush: true)) {
            render(view: "create", model: [versioneInstance: versioneInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'versione.label', default: 'Versione'), versioneInstance.id])
        redirect(action: "show", id: versioneInstance.id)
    }

    def show(Long id) {
        def versioneInstance = Versione.get(id)
        if (!versioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "list")
            return
        }

        [versioneInstance: versioneInstance]
    }

    def edit(Long id) {
        def versioneInstance = Versione.get(id)
        if (!versioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "list")
            return
        }

        [versioneInstance: versioneInstance]
    }

    def update(Long id, Long version) {
        def versioneInstance = Versione.get(id)
        if (!versioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (versioneInstance.version > version) {
                versioneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'versione.label', default: 'Versione')] as Object[],
                        "Another user has updated this Versione while you were editing")
                render(view: "edit", model: [versioneInstance: versioneInstance])
                return
            }
        }

        versioneInstance.properties = params

        if (!versioneInstance.save(flush: true)) {
            render(view: "edit", model: [versioneInstance: versioneInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'versione.label', default: 'Versione'), versioneInstance.id])
        redirect(action: "show", id: versioneInstance.id)
    }

    def delete(Long id) {
        def versioneInstance = Versione.get(id)
        if (!versioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "list")
            return
        }

        try {
            versioneInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "show", id: id)
        }
    }
}
