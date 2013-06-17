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

@TestFor(ComuneController)
@Mock(Comune)
class ComuneControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/comune/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.comuneInstanceList.size() == 0
        assert model.comuneInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.comuneInstance != null
    }

    void testSave() {
        controller.save()

        assert model.comuneInstance != null
        assert view == '/comune/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/comune/show/1'
        assert controller.flash.message != null
        assert Comune.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/comune/list'

        populateValidParams(params)
        def comune = new Comune(params)

        assert comune.save() != null

        params.id = comune.id

        def model = controller.show()

        assert model.comuneInstance == comune
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/comune/list'

        populateValidParams(params)
        def comune = new Comune(params)

        assert comune.save() != null

        params.id = comune.id

        def model = controller.edit()

        assert model.comuneInstance == comune
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/comune/list'

        response.reset()

        populateValidParams(params)
        def comune = new Comune(params)

        assert comune.save() != null

        // test invalid parameters in update
        params.id = comune.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/comune/edit"
        assert model.comuneInstance != null

        comune.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/comune/show/$comune.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        comune.clearErrors()

        populateValidParams(params)
        params.id = comune.id
        params.version = -1
        controller.update()

        assert view == "/comune/edit"
        assert model.comuneInstance != null
        assert model.comuneInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/comune/list'

        response.reset()

        populateValidParams(params)
        def comune = new Comune(params)

        assert comune.save() != null
        assert Comune.count() == 1

        params.id = comune.id

        controller.delete()

        assert Comune.count() == 0
        assert Comune.get(comune.id) == null
        assert response.redirectedUrl == '/comune/list'
    }
}
