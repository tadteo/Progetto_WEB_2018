# Progetto Finale: Sito E-commerce
<small>Versione: 04 (24-05-2017)</small>


<div>

### SCOPO

Si vuole progettare un sistema di vendita online e classificazione dei venditori da esporre via WEB e fruibile per dispositivi sia desktop che mobile in modalità responsive. L’applicazione deve essere sviluppata con tecnologia Servlet/JSP/JSTL ed ove fosse conveniente a vostra scelta web-services, CSS, javascript

</div>

<div>

### PUNTI DA IMPLEMENTARE

Il sistema prevede:

*   Un motore di ricerca che permetta di visualizzare un elenco di oggetti in vendita in base a varie tipologia di scelta tra cui dovranno esserci:
    *   Per Categoria;
    *   Per Oggetto;
    *   Per venditore [5+];
*   Un filtro sulla ricerca per:
    *   Zona Geografica
    *   Prezzo
    *   Costumer Review
*   Un sistema di review degli oggetti venduti (da intendere come oggetti, non come qualità del servizio di vendita, da dettagliare, con stelline);
*   Un sistema di classificazione dei venditori che dipende dalla qualitàa’ del servizio di vendita e postvendita ricevuto [5+] (devi tenere traccia che l’oggetto è arrivato);
*   La possibilità, per il venditore, di inserire una o più fotografie legate agli oggetti in vendita;
*   La possibilitaà’, per gli utenti registrati, di lasciare una review sull’oggetto comperato.
*   La possibilità, per gli utenti registrati di lasciare una recensione per il venditore, per ciascun oggetto acquistato [5+];
*   La possibilità, per i venditori, di rispondere alle recensioni che i clienti lasciano per i propri oggetti [5+];
*   Un sistema di georeferenziazione che permette di visualizzare, su una mappa, i negozi che vendono quell’oggetto. [5+]
*   **Il carrello di fine sessione, prima del pagamento, in cui potro’ valutare eventuali cancellazioni di articoli.**
*   La gestione dell’acquisto, attraverso pagina fittizia di pagamento con carta di credito oppure ritiro diretto presso il negozio, per i soli oggetti con tale possibilitàa’ di fruizione: I venditori possono esistere esclusivamente online, oppure avere un negozio fisico con indirizzo classico.. Per questa tipologia di negozio, sarà possibile recuperare la merce direttamente in negozio.
*   Un sistema di segnalazione anomalie (es: oggetti non arrivati) che invii al negozio e all’amministratore la richiesta e che poi sia gestita dall’amministratore:
    *   Segnanlao anomalia e viene recapitata a Negozio + Amministratore.
    *   L’amministratore decide come gestire: Soldi indietro; Segnalazione negativa al venditore; Non procedo; Rigetto l’anomalia;
*   L’internazionalizzazione del sito in base alle informazioni ricevute dal browser e/o scelta diretta dell’utente [7] (inteso come localizzazione dei menu e delle informazioni/headers tabelle e bottoni/pulsanti; non devono essere localizzate ovviamente le recensioni e le descrizioni che rimangono nella lingua originale). L’internazionalizzazione è intesa che segua le regole spiegate a lezione o superiori (essendo attivi formalmente uno standard de-facto presentato ed uno standard dell’IANA che codifica le nazioni a due o più lettere).

</div>

<div>

### OGGETTI ESSENZIALI

#### Negozio

*   Nome
*   Descrizione
*   Fotografie pubblicate dal proprietario
*   Link al sito ufficiale del negozio
*   Coordinate Geografiche (città, indirizzo, ecc)
*   Orari d' apertura; Tipologia di spedizione; (Per quelli che permettono il ritiro dal negozio)
*   Valutazione totale del venditore (da 0 a 5) (valutazione globale dei voti dati al venditore, tipo ebay)

#### Oggetti in vendita

Gli oggetti devono essere classificati almeno da:

*   Nome
*   Categoria
*   Descrizione
*   Fotografie dell'oggetto (una, o più [5+] )
*   Review dell'oggetto venduto [... da completare?!?!]
*   Negozio che lo vende

#### Utenti

Gli utenti saranno classificati almeno da:

*   Nome
*   Cognome
*   Email
*   Password
*   Avatar[7+]

</div>

<div>

### Utenti

Il portale dovrà prevedere quattro tipologie di utenti:

1.  Utente Anonimo
2.  Utente Registrato
3.  Utente Venditore
4.  Utente Amministratore

#### Utente Anonimo

Tale utente è quello che accede al sito senza fare login all’interno dello stesso. Tale utente può eseguire le ricerche (come già descritte in SCOPO). L’utente anonimo potrà registrarsi al portale via FORM di registrazione. L’utente anonimo può creare un carrello provvisorio ma non può comperare fino a quando avrà fatto login (o si sia registrato e poi fatto login).

#### Utente Registrato

Tale utente è quello che ha fatto login al sito. L’utente registrato è quello che esegue la maggior parte delle iterazioni con il portale web. Oltre a poter svolgere le attività dell’utente anonimo potrà: comperare, fare recensioni di oggetti e negozi, fare segnalazioni di anomalia.  
[Modificare/immettere il proprio avatar che deve essere visualizzato nelle recensioni 7+]

#### Utente Venditore

Tale utente potrà: rispondere alle recensioni degli utenti registrati; modificare i dati relativi al proprio business; Inserire nuovi oggetti da vendere [5+ ?Perchè non lo facciamo anche noi?]. Inoltre, potrà eseguire tutte le attività di un utente registrato.

#### Utente Amministratore

Tale utente è il gestore del sito web e si occupa di gestire le iterazioni tra utenti registrati e utenti venditori in caso di anomalie. Tale utente gestisce le anomalie (vedi sopra); verrà notificato della richiesta di rimborso di un oggetto per cui dovrà valutarne la validità e, quindi, autorizzare il rimborso. Dovrà, quindi, notificare il venditore e decidere se penalizzarne la reputazione.

</div>

<div>

### Use Case

Riferito all' intera applicazionedeve essere congruente a quanto richiesto (in base al numero di persone)

#### Menù principale

Ogni pagina del portale dovrà avere lo stesso menu principale personalizzato per tipologia di utente.

L’**utente anonimo** avrà accesso al seguente menu principale:

*   Pulsante “Registrati”: che lo farà accedere alla form di registrazione;
*   Pulsante “Accedi”: che lo farà accedere alla form di login;
*   Pulsante “Lingua”: che gli permetterà di modificare l’internazionalizzazione del portale[7+].(dovranno essere presenti almeno due lingue ed un locale di default. Attenzione a fare una traduzione di tutte le keywords (come spiegato a lezione con Google Translator) in modo che sia evidente all’utilizzatore la lingua scelta e non di solo alcune).

L’**utente registrato** avrà accesso al seguente menu principale:

*   Pulsante “Nome e Cognome”:
    *   Profilo: che permetterà di modificare i dati di profilo dell’utente;
    *   Chiedere rimborso oggetto/segnalare anomalia: che permette di chiedere il rimborso di un oggetto da comprato;
    *   Esci: che permetterà di fare logout dell’utente.
*   Pulsante “Lingua”: che gli permetterà di modificare l’internazionalizzazione del portale [7+]

L’utente venditore avrà accesso al seguente menu principale:

*   Pulsante “Notifiche”: visualizzerà l’elenco delle ultime notifiche ricevute più un pulsante per accedere a tutte le notifiche;
*   Pulsante “Nome e Cognome”:
    *   Profilo: che permetterà di modificare i dati di profilo dell’utente;
    *   Il vostro negozio: che permetterà di accedere alla pagina di gestione del proprio negozio;
    *   Esci: che permetterà di fare logout dell’utente.
    *   Sistema di aggiunta/cancellazione oggetti in vendita [5+]
*   Pulsante “Lingua”: che gli permetterà di modificare l’internazionalizzazione del portale.

L’**utente amministratore** avrà accesso al seguente menu principale:

*   Pulsante “Notifiche”: visualizzerà l’elenco delle ultime notifiche ricevute più un pulsante per accedere a tutte le notifiche e gestione delle controversie (da spiegare meglio)
*   Pulsante “Nome e Cognome”;
    *   Profilo: che permetterà di modificare i dati di profilo dell’utente;
    *   Esci: che permetterà di fare logout dell’utente.
*   Pulsante “Lingua”: che gli permetterà di modificare l’internazionalizzazione del portale.

#### Landing Page

Ogni tipologia di utente avrà accesso alla landing page del portale web che sarà composto da:

*   Form di ricerca:
    *   Text field con auto-completamento: che permetterà di inserire le parole da utilizzare per la ricerca degli oggetti/venditori;
    *   Pulsante “Cerca”: che avvierà la ricerca.
*   Elenco di possibili filtri:
    *   Filtro per vicinanza (solo per oggetti con ritiro)
    *   Filtro per prezzo
    *   Filstro per review

#### Form di registrazione

L’utente anonimo avrà accesso a tale form che dovrà permettere la registrazione dell’utente e dovrà prevedere:

*   Email: che verrà utilizzato anche come username di login;
*   Password: che dovrà avere dei meccanismi di scelta di una password solida;
*   L’accettazione (anche non immediata, ma prima della prima connessione) alla normativa sulla privacy da accettare;
*   Pulsante “Iscriviti”: che invierà la richiesta d’iscrizione;
*   Pulsante “Annulla”: che rimanderà l’utente alla pagina da cui era arrivato.

L’iscrizione al portale dovrà essere validato attraverso il click di un link inviato per email.

#### Form di login

L’utente anonimo avrà accesso a tale form che dovrà permettere il login dell’utente e dovrà prevedere:

*   Username: l’email con cui l’utente si è registrato;
*   Password: la password con cui l’utente si è registrato;
*   Modalità reset della password se l’utente se l’è dimenticata;
*   Pulsante “Accedi”: che effettuerà il login dell’utente e ritornerà alla pagina di provenienza;
*   Pulsante “Annulla”: che rimanderà l’utente alla pagina da cui era arrivato.

#### Form di ricerca (da vedere se lo stesso di cui alla landing page)

Ogni tipologia di utente avrà accesso a tale form che dovrà permettere la ricerca di oggetti e dovrà prevedere:

*   Text field (uno o più consigliati il meno possibile): verranno inserite le parole da ricercare;
*   Pulsante “Cerca”: avvierà la ricerca e rimanderà l’utente alla pagina dei risultati.

Il campo di ricerca avrà l’auto completamento che aiuterà l’utente nella scelta delle parole da ricercare. La ricerca dovrà poter includere almeno: la tipologia di oggetto/oggetto, via/cittàa’/regione.

#### Pagina dei risultati

Ogni tipologia di utente avrà accesso alla pagina dei risultati che dovrà prevedere:

*   L’elenco degli oggetti ordinati per prezzo; per ogni oggetto dovrà essere visibile:
    *   Nome del venditore con link alla pagina del negozio;
    *   Una foto;
    *   Il voto totale dell’oggetto (da 1 a 5);
    *   Il numero di recensioni ricevute;
    *   Link alla mappa (se serve)
    *   Prezzo.
*   Possibilità di modificare l’ordinamento della classifica:
    *   Stelline(review);
    *   Fascia di prezzo.

#### Pagina della mappa

Dovrà visualizzare una mappa di Google centrata sul negozio selezionato [per tutti] e [seguente per 5+] che presenti tutti i negozi presenti in zona che vendono lo stesso oggetto.

#### Pagina del negozio

Questa pagina dovrà avere almeno le seguenti informazioni:

*   Nome;
*   Valutazione totale;
*   Indirizzo;
*   Almeno una fotografia;
*   Elenco delle ultime recensioni;

#### Pagina delle notifiche

L’utente negoziante visualizzerà, in questa pagina, tutte le notifiche di avvenuta recensione o di anomalie. Da ogni notifica potrà accedere, rispettivamente, a:

*   La pagina di risposta alle recensioni;

L’utente amministratore visualizzerà, in questa pagina, tutte le notifiche di anomalie con il sistema di gestione annesso. Per ogni notifica potrà:

*   Rigettarla con motivo;
*   Le decisioni sara\nno notfictenotificate ad entrambi
*   Eventualmente dare giudizio negativo al negozio
*   Gestire la restituzione del credito all’utente (il credito rimane a disposizione dell’utente per nuovi acquisti) [7+]

</div>

<div>

### Database e precompilazione

Ciascun progetto (gruppo) dovrà presentare il proprio lavoro con un database già popolato per almeno:

*   Quattro regioni amministrative con almeno ciascuna 5 città differenti (anche in differenti stati se volete)
*   Per ciascuna città (vista come area estesa attorno alla città) almeno 10 negozi diversi. Per ciascuna città la somma delle recensioni (per il totale dei 10 negozi) dovrà essere di almeno 20. Dovranno inoltre essere presenti almeno 4 negozi per città con foto pubblicate da utenti.
*   10 utenti minimo già registrati

</div>

*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

<div>

### Hint Vari

*   String similarity: https://github.com/tdebatty/java-string-similarity. Si tenga presente che per una ricerca efficiente (i.e. motore di ricerca vengono impiegati algoritmi che pre-memorizzano l’indice scelto e quindi ne fanno una sola comparazione al momento della query).
*   Geocoding:  
    https://developers.google.com/maps/documentation/geocoding/intro#GeocodingRequests e https://developer.mapquest.com/plan_purchase/steps/business_edition/business_edition_free/re gister + http://www.mapquestapi.com/geocoding/ Ricordarsi che bisogna pre--memorizzare le coordinate geografiche o box per poi fare la ricerca su numeri.
*   Riempite il database con dati plausibili, evitate nomi tipo ‘negozio1 negozio2’ ecc... cercate di rendere presentabile e credibile l’implementazione (in totale: 2*5* 20 recensioni, suggerimenti per recuperare recensioni/ ricerca: ebay, http://global.rakuten.com/en/, http://www.fromjapan.co.jp/en/

</div>

<div>

### Raccomandazioni

Il sito deve essere progettato in modalità responsive e dimostrato funzionante per risoluzioni pc/tablet/telefono attraverso il device o la modalità relativa sul browser. Il progetto dovrà essere mostrato con un database minimo credibile ed intelligentemente popolato per quanto richiesto e nel numero di voci richieste nel presente testo come minimo.

</div>

<div>

### Documentazione

Dovrà essere presentata una documentazione del progetto illustrante le caratteristiche e le scelte effettuate

</div>

<div>

### Ricordarsi che per una buona valutazione:

*   all'esame servono due browser per dimostrare le funzionalità
*   gli uri di tutti i file devono essere relativi e non assoluti sul file system
*   La documentazione presentata deve essere efficace
*   Attenzione a chi userà javascript: devono sapere sostenere le scelte implementative *tutti i componenti del gruppo, e non deve violare o rendere inutile il paradigma MVC
*   Il database con cui implementare il progetto è a libera scelta (postgres/mysql/derby/ ectc...), ricordandosi che nella consegna dovrà essere data una copia anche del database

</div>

<div>

### Regole di presentazione e consegna progetto

Consegna progetto 2 giorni prima del giorno dell’appello in cui vorrete sostenere l’esame, da un solo componente del gruppo con chiara indicazione dei partecipanti al gruppo. Consegna progetto elettronica tramite link a strumento di archiviazione cloud a vostra scelta. Dare un NICKNAME al progetto in modo che sia identificabile facilmente rispetto agli altri L’esame prevede che:

*   Sia Presentato il progetto come sopra
*   Il giorno dell’esame si faràa’ l’appello e stabilito l’ordine di presentazione. Eventuali problemi di orario saranno da concordare (anche in anticipo) via mail nel rispetto dei reciproci impegni e delle liste di partecipanti all’esame
*   Dovrà quindi essere effettuata una presentazione del progetto, da tutti i componenti, che spiegheranno (il relatore del progetto può essere uno o più degli appartenenti al progetto), la filosofia di implementnazione e dimostreranno le funzionalità.
*   Verranno fatte domande sulle funzionalitàa’ o sulle scelte implementative e potràa’ rispondere chiunque del gruppo.

Se , per qualche motivo, il gruppo non fosse tutto presente, i componenti mancanti dovranno sostenere l’esame come se il resto del non gruppo esistesse, e dovranno dimostrare padronanza di tutto quanto presentato.</div>
