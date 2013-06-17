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

@TestFor(DaeController)
@Mock(Dae)
class DaeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/dae/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.daeInstanceList.size() == 0
        assert model.daeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.daeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.daeInstance != null
        assert view == '/dae/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dae/show/1'
        assert controller.flash.message != null
        assert Dae.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dae/list'

        populateValidParams(params)
        def dae = new Dae(params)

        assert dae.save() != null

        params.id = dae.id

        def model = controller.show()

        assert model.daeInstance == dae
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dae/list'

        populateValidParams(params)
        def dae = new Dae(params)

        assert dae.save() != null

        params.id = dae.id

        def model = controller.edit()

        assert model.daeInstance == dae
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dae/list'

        response.reset()

        populateValidParams(params)
        def dae = new Dae(params)

        assert dae.save() != null

        // test invalid parameters in update
        params.id = dae.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dae/edit"
        assert model.daeInstance != null

        dae.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dae/show/$dae.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dae.clearErrors()

        populateValidParams(params)
        params.id = dae.id
        params.version = -1
        controller.update()

        assert view == "/dae/edit"
        assert model.daeInstance != null
        assert model.daeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dae/list'

        response.reset()

        populateValidParams(params)
        def dae = new Dae(params)

        assert dae.save() != null
        assert Dae.count() == 1

        params.id = dae.id

        controller.delete()

        assert Dae.count() == 0
        assert Dae.get(dae.id) == null
        assert response.redirectedUrl == '/dae/list'
    }
}
