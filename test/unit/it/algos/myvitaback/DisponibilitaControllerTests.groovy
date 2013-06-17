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

@TestFor(DisponibilitaController)
@Mock(Disponibilita)
class DisponibilitaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/disponibilita/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.disponibilitaInstanceList.size() == 0
        assert model.disponibilitaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.disponibilitaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.disponibilitaInstance != null
        assert view == '/disponibilita/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/disponibilita/show/1'
        assert controller.flash.message != null
        assert Disponibilita.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/disponibilita/list'

        populateValidParams(params)
        def disponibilita = new Disponibilita(params)

        assert disponibilita.save() != null

        params.id = disponibilita.id

        def model = controller.show()

        assert model.disponibilitaInstance == disponibilita
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/disponibilita/list'

        populateValidParams(params)
        def disponibilita = new Disponibilita(params)

        assert disponibilita.save() != null

        params.id = disponibilita.id

        def model = controller.edit()

        assert model.disponibilitaInstance == disponibilita
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/disponibilita/list'

        response.reset()

        populateValidParams(params)
        def disponibilita = new Disponibilita(params)

        assert disponibilita.save() != null

        // test invalid parameters in update
        params.id = disponibilita.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/disponibilita/edit"
        assert model.disponibilitaInstance != null

        disponibilita.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/disponibilita/show/$disponibilita.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        disponibilita.clearErrors()

        populateValidParams(params)
        params.id = disponibilita.id
        params.version = -1
        controller.update()

        assert view == "/disponibilita/edit"
        assert model.disponibilitaInstance != null
        assert model.disponibilitaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/disponibilita/list'

        response.reset()

        populateValidParams(params)
        def disponibilita = new Disponibilita(params)

        assert disponibilita.save() != null
        assert Disponibilita.count() == 1

        params.id = disponibilita.id

        controller.delete()

        assert Disponibilita.count() == 0
        assert Disponibilita.get(disponibilita.id) == null
        assert response.redirectedUrl == '/disponibilita/list'
    }
}
