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

@TestFor(BatteriaController)
@Mock(Batteria)
class BatteriaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/batteria/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.batteriaInstanceList.size() == 0
        assert model.batteriaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.batteriaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.batteriaInstance != null
        assert view == '/batteria/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/batteria/show/1'
        assert controller.flash.message != null
        assert Batteria.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/batteria/list'

        populateValidParams(params)
        def batteria = new Batteria(params)

        assert batteria.save() != null

        params.id = batteria.id

        def model = controller.show()

        assert model.batteriaInstance == batteria
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/batteria/list'

        populateValidParams(params)
        def batteria = new Batteria(params)

        assert batteria.save() != null

        params.id = batteria.id

        def model = controller.edit()

        assert model.batteriaInstance == batteria
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/batteria/list'

        response.reset()

        populateValidParams(params)
        def batteria = new Batteria(params)

        assert batteria.save() != null

        // test invalid parameters in update
        params.id = batteria.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/batteria/edit"
        assert model.batteriaInstance != null

        batteria.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/batteria/show/$batteria.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        batteria.clearErrors()

        populateValidParams(params)
        params.id = batteria.id
        params.version = -1
        controller.update()

        assert view == "/batteria/edit"
        assert model.batteriaInstance != null
        assert model.batteriaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/batteria/list'

        response.reset()

        populateValidParams(params)
        def batteria = new Batteria(params)

        assert batteria.save() != null
        assert Batteria.count() == 1

        params.id = batteria.id

        controller.delete()

        assert Batteria.count() == 0
        assert Batteria.get(batteria.id) == null
        assert response.redirectedUrl == '/batteria/list'
    }
}
