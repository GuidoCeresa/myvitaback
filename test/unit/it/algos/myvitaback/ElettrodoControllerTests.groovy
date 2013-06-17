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

@TestFor(ElettrodoController)
@Mock(Elettrodo)
class ElettrodoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/elettrodo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.elettrodoInstanceList.size() == 0
        assert model.elettrodoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.elettrodoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.elettrodoInstance != null
        assert view == '/elettrodo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/elettrodo/show/1'
        assert controller.flash.message != null
        assert Elettrodo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/elettrodo/list'

        populateValidParams(params)
        def elettrodo = new Elettrodo(params)

        assert elettrodo.save() != null

        params.id = elettrodo.id

        def model = controller.show()

        assert model.elettrodoInstance == elettrodo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/elettrodo/list'

        populateValidParams(params)
        def elettrodo = new Elettrodo(params)

        assert elettrodo.save() != null

        params.id = elettrodo.id

        def model = controller.edit()

        assert model.elettrodoInstance == elettrodo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/elettrodo/list'

        response.reset()

        populateValidParams(params)
        def elettrodo = new Elettrodo(params)

        assert elettrodo.save() != null

        // test invalid parameters in update
        params.id = elettrodo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/elettrodo/edit"
        assert model.elettrodoInstance != null

        elettrodo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/elettrodo/show/$elettrodo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        elettrodo.clearErrors()

        populateValidParams(params)
        params.id = elettrodo.id
        params.version = -1
        controller.update()

        assert view == "/elettrodo/edit"
        assert model.elettrodoInstance != null
        assert model.elettrodoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/elettrodo/list'

        response.reset()

        populateValidParams(params)
        def elettrodo = new Elettrodo(params)

        assert elettrodo.save() != null
        assert Elettrodo.count() == 1

        params.id = elettrodo.id

        controller.delete()

        assert Elettrodo.count() == 0
        assert Elettrodo.get(elettrodo.id) == null
        assert response.redirectedUrl == '/elettrodo/list'
    }
}
