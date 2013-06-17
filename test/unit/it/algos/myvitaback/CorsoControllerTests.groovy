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

@TestFor(CorsoController)
@Mock(Corso)
class CorsoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/corso/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.corsoInstanceList.size() == 0
        assert model.corsoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.corsoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.corsoInstance != null
        assert view == '/corso/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/corso/show/1'
        assert controller.flash.message != null
        assert Corso.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/corso/list'

        populateValidParams(params)
        def corso = new Corso(params)

        assert corso.save() != null

        params.id = corso.id

        def model = controller.show()

        assert model.corsoInstance == corso
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/corso/list'

        populateValidParams(params)
        def corso = new Corso(params)

        assert corso.save() != null

        params.id = corso.id

        def model = controller.edit()

        assert model.corsoInstance == corso
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/corso/list'

        response.reset()

        populateValidParams(params)
        def corso = new Corso(params)

        assert corso.save() != null

        // test invalid parameters in update
        params.id = corso.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/corso/edit"
        assert model.corsoInstance != null

        corso.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/corso/show/$corso.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        corso.clearErrors()

        populateValidParams(params)
        params.id = corso.id
        params.version = -1
        controller.update()

        assert view == "/corso/edit"
        assert model.corsoInstance != null
        assert model.corsoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/corso/list'

        response.reset()

        populateValidParams(params)
        def corso = new Corso(params)

        assert corso.save() != null
        assert Corso.count() == 1

        params.id = corso.id

        controller.delete()

        assert Corso.count() == 0
        assert Corso.get(corso.id) == null
        assert response.redirectedUrl == '/corso/list'
    }
}
