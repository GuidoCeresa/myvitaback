/* Created by Algos s.r.l. */
/* Date: mag 2013 */
/* Questo file (che non esisteva) è stato creato ed installato dal plugin Algos */
/* Tipicamente VERRA sovrascritto ad ogni nuova release del plugin */
/* per rimanere aggiornato */
/* Se vuoi che le prossime release del plugin NON sovrascrivano questo file, */
/* perdendo tutte le modifiche precedentemente effettuate, */
/* regola a false il flag di controllo flagOverwrite© */
/* flagOverwrite = true */

package it.algos.myvitaback



import org.junit.*
import grails.test.mixin.*

@TestFor(ModelloController)
@Mock(Modello)
class ModelloControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/modello/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.modelloInstanceList.size() == 0
        assert model.modelloInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.modelloInstance != null
    }

    void testSave() {
        controller.save()

        assert model.modelloInstance != null
        assert view == '/modello/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/modello/show/1'
        assert controller.flash.message != null
        assert Modello.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/modello/list'

        populateValidParams(params)
        def modello = new Modello(params)

        assert modello.save() != null

        params.id = modello.id

        def model = controller.show()

        assert model.modelloInstance == modello
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/modello/list'

        populateValidParams(params)
        def modello = new Modello(params)

        assert modello.save() != null

        params.id = modello.id

        def model = controller.edit()

        assert model.modelloInstance == modello
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/modello/list'

        response.reset()

        populateValidParams(params)
        def modello = new Modello(params)

        assert modello.save() != null

        // test invalid parameters in update
        params.id = modello.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/modello/edit"
        assert model.modelloInstance != null

        modello.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/modello/show/$modello.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        modello.clearErrors()

        populateValidParams(params)
        params.id = modello.id
        params.version = -1
        controller.update()

        assert view == "/modello/edit"
        assert model.modelloInstance != null
        assert model.modelloInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/modello/list'

        response.reset()

        populateValidParams(params)
        def modello = new Modello(params)

        assert modello.save() != null
        assert Modello.count() == 1

        params.id = modello.id

        controller.delete()

        assert Modello.count() == 0
        assert Modello.get(modello.id) == null
        assert response.redirectedUrl == '/modello/list'
    }
}
