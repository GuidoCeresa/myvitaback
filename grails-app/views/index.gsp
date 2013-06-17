<%--Created by Algos s.r.l.--%>
<%--Date: mag 2013--%>
<%--Il plugin Algos ha inserito (solo la prima volta) questo header per controllare--%>
<%--le successive release (tramite il flag di controllo aggiunto)--%>
<%--Tipicamente VERRA sovrascritto ad ogni nuova release del plugin--%>
<%--per rimanere aggiornato--%>
<%--Se vuoi che le prossime release del plugin NON sovrascrivano questo file,--%>
<%--perdendo tutte le modifiche precedentemente effettuate,--%>
<%--regola a false il flag di controllo flagOverwrite©--%>
<%--flagOverwrite = false--%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <title>Progetto Vita</title>
    <style type="text/css" media="screen">
    #status {
        background-color: #eee;
        border: .2em solid #fff;
        margin: 2em 2em 1em;
        padding: 1em;
        width: 12em;
        float: left;
        -moz-box-shadow: 0px 0px 1.25em #ccc;
        -webkit-box-shadow: 0px 0px 1.25em #ccc;
        box-shadow: 0px 0px 1.25em #ccc;
        -moz-border-radius: 0.6em;
        -webkit-border-radius: 0.6em;
        border-radius: 0.6em;
    }

    .ie6 #status {
        display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
    }

    #status ul {
        font-size: 0.9em;
        list-style-type: none;
        margin-bottom: 0.6em;
        padding: 0;
    }

    #status li {
        line-height: 1.3;
    }

    #status h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin: 0 0 0.3em;
    }

    #page-body {
        margin: 2em 1em 1.25em 8.6em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    #controller-list ul {
        list-style-position: inside;
    }

    #controller-list li {
        line-height: 1.3;
        list-style-position: inside;
        margin: 0.25em 0;
    }

    @media screen and (max-width: 480px) {
        #status {
            display: none;
        }

        #page-body {
            margin: 0 1em 1em;
        }

        #page-body h1 {
            margin-top: 0;
        }
    }
    </style>
</head>
<body>
<div class="navd" role="navigation">
    <ul>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>
<div id="page-body" role="main">
    <h1>Progetto Vita - Defibrillatori in provincia di Piacenza</h1>
    <p>Elenco dei defibrillatori installati ed operativi nella provincia di Piacenza. <br>
        Per ognuno viene riportato il comune, l'eventuale frazione comunale e la località d'installazione.<br>
        Vengono individuati dal nome del privato o dell'ente pubblico che li ospita.<br>
        Segue la tipologia di accesso (pubblico/privato) e l'orario di disponibilità.<br>
        Vengono indicati eventuali persone di riferimento da contattare ed il numero di telefono.
        <br><br></p>

    <div id="controller-list" role="navigation">
        <vita:listaControllers></vita:listaControllers>
    </div>
</div>
</body>
</html>
