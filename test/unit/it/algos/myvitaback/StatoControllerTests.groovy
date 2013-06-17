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

@TestFor(StatoController)
@Mock(Stato)
class StatoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stato/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.statoInstanceList.size() == 0
        assert model.statoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.statoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.statoInstance != null
        assert view == '/stato/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stato/show/1'
        assert controller.flash.message != null
        assert Stato.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stato/list'

        populateValidParams(params)
        def stato = new Stato(params)

        assert stato.save() != null

        params.id = stato.id

        def model = controller.show()

        assert model.statoInstance == stato
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stato/list'

        populateValidParams(params)
        def stato = new Stato(params)

        assert stato.save() != null

        params.id = stato.id

        def model = controller.edit()

        assert model.statoInstance == stato
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stato/list'

        response.reset()

        populateValidParams(params)
        def stato = new Stato(params)

        assert stato.save() != null

        // test invalid parameters in update
        params.id = stato.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stato/edit"
        assert model.statoInstance != null

        stato.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stato/show/$stato.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stato.clearErrors()

        populateValidParams(params)
        params.id = stato.id
        params.version = -1
        controller.update()

        assert view == "/stato/edit"
        assert model.statoInstance != null
        assert model.statoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stato/list'

        response.reset()

        populateValidParams(params)
        def stato = new Stato(params)

        assert stato.save() != null
        assert Stato.count() == 1

        params.id = stato.id

        controller.delete()

        assert Stato.count() == 0
        assert Stato.get(stato.id) == null
        assert response.redirectedUrl == '/stato/list'
    }
}
