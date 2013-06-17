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

@TestFor(CollocazioneController)
@Mock(Collocazione)
class CollocazioneControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/collocazione/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.collocazioneInstanceList.size() == 0
        assert model.collocazioneInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.collocazioneInstance != null
    }

    void testSave() {
        controller.save()

        assert model.collocazioneInstance != null
        assert view == '/collocazione/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/collocazione/show/1'
        assert controller.flash.message != null
        assert Collocazione.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/collocazione/list'

        populateValidParams(params)
        def collocazione = new Collocazione(params)

        assert collocazione.save() != null

        params.id = collocazione.id

        def model = controller.show()

        assert model.collocazioneInstance == collocazione
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/collocazione/list'

        populateValidParams(params)
        def collocazione = new Collocazione(params)

        assert collocazione.save() != null

        params.id = collocazione.id

        def model = controller.edit()

        assert model.collocazioneInstance == collocazione
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/collocazione/list'

        response.reset()

        populateValidParams(params)
        def collocazione = new Collocazione(params)

        assert collocazione.save() != null

        // test invalid parameters in update
        params.id = collocazione.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/collocazione/edit"
        assert model.collocazioneInstance != null

        collocazione.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/collocazione/show/$collocazione.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        collocazione.clearErrors()

        populateValidParams(params)
        params.id = collocazione.id
        params.version = -1
        controller.update()

        assert view == "/collocazione/edit"
        assert model.collocazioneInstance != null
        assert model.collocazioneInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/collocazione/list'

        response.reset()

        populateValidParams(params)
        def collocazione = new Collocazione(params)

        assert collocazione.save() != null
        assert Collocazione.count() == 1

        params.id = collocazione.id

        controller.delete()

        assert Collocazione.count() == 0
        assert Collocazione.get(collocazione.id) == null
        assert response.redirectedUrl == '/collocazione/list'
    }
}
