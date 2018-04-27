-- Insert query for filling faster the database database

INSERT INTO Sala (descrizione) VALUES
("Sala1"),
("Sala2"),
("Sala3"),
("Sala4");


INSERT INTO Genere (descrizione) VALUES
("Horror"),
("Fantascienza"),
("Thriller"),
("Drammatico"),
("Western"),
("Azione");

INSERT INTO Film (titolo,id_genere,url_locandina,url_trailer,durata,trama) VALUES
("Shining",1,"/locandine/the_shining.jpg","https://www.youtube.com/embed/wIf1Lnp02c8",146,"Sotto l'influenza malefica dell'Overlook Hotel sulle Montagne Rocciose dove si e' installato come guardiano d'inverno con moglie e figlio; Jack Torrence sprofonda in una progressiva schizofrenica follia che lo spinge a minacciare di morte i suoi cari."),
("2001 Odissea Nello Spazio",2,"/locandine/2001_a_space_odissey.jpg","https://www.youtube.com/embed/1CyADKy-3bw",141,"Nel 2001 un misterioso oggetto di origine intelligente viene scoperto sulla Luna. A seguito di questo rinvenimento; una squadra di astronauti viene inviata in missione verso Giove; a supervisionare le operazioni c'e' un computer di ultima generazione; l'infallibile HAL 9000."),
("I Soliti Sospetti",3,"/locandine/the_usual_suspects.jpg","https://www.youtube.com/embed/uD_zVpEtwsQ",105,"In California una nave esplode sul molo di San Pedro: un noto criminale (Dean Keaton) viene ucciso nella stiva da un ignoto dove ha appiccato il fuoco mentre l'equipaggio e' stato sterminato. L'unico sopravvissuto alla strage- un delinquente di mezza tacca- viene interrogato da David Kujan- poliziotto doganale."),
("The Truman Show",4,"/locandine/the_truman_show.jpg","https://www.youtube.com/embed/jmKQZ4v3yrs",103,"La vita di Truman Burbank nella cittadina di Seahaven scorre all'apparenza tranquilla: lui lavora come agente assicurativo ; ha una moglie infermiera in ospedale ; e i vicini di casa tutte le mattine lo salutano con un cordiale 'buon giorno!'. Truman a dir la verita' avverte un po' il peso di questa routine ; e progetta di fare viaggi ; visitare altri paesi ; fare nuove esperienze. Ma al momento di concretizzare queste idee ; qualcosa sempre lo rimanda indietro: l'impiegata dell'agenzia gli dice che i posti sono esauriti ; e anche in macchina il traffico impedisce di uscire di citta' Truman si scontra con ostacoli che col passare del tempo cominciano ad apparirgli strani e inspiegabili."),
("Pulp Fiction",4,"/locandine/pulp_fiction .jpg","https://www.youtube.com/embed/dmwjIpF8D3g",154,"Le vite di un pugile, di due gangster, di un boss e della sua pupa; di uno spacciatore e di una coppia di rapinatori si sfiorano e collidono in una serie di eventi imprevedibili e paradossali."),
("Django Unchained",5,"/locandine/django_unchained.jpg","https://www.youtube.com/embed/uALPPuJcnGQ",165,"Ambientato nel Sud degli attuali Stati Uniti, due anni prima dello scoppio della Guerra Civile, Django Unchained vede protagonista Jamie Foxx nel ruolo di Django, uno schiavo la cui storia brutale con il suo ex padrone, lo conduce faccia a faccia con il cacciatore di taglie di origine tedesca, il Dott. King Schultz (Christoph Waltz). Schultz e' sulle tracce degli assassini fratelli Brittle, e solo l'aiuto di Django lo portera' a riscuotere la taglia che pende sulle loro teste. Il poco ortodosso Schultz assolda Django con la promessa di donargli la liberta' una volta catturati i Brittle - vivi o morti. Il successo dell'operazione induce Schultz a liberare Django, sebbene i due uomini scelgano di non separarsi. Al contrario, Schultz parte alla ricerca dei criminali piu' ricercati del Sud con Django al suo fianco. Affinando vitali abilita' di cacciatore, Django resta concentrato su un solo obiettivo: trovare e salvare la moglie che aveva perso tempo prima."),
("Non e' un paese per vecchi",3,"/locandine/no_country_for_old_men.jpg","https://www.youtube.com/embed/MvON97qpOI8",122,"La sanguinosa vicenda di un uomo che, nel Texas occidentale, s'imbatte in una serie di assassinii, in una cospicua partita di droga e in 2,4 milioni di dollari in contanti."),
("Fight Club",4,"/locandine/fight_club.jpg","https://www.youtube.com/embed/FEqp8tSh1F4",135,"Non sei il tuo lavoro. Non sei il tuo conto in banca. Non sei il contenuto del tuo portafoglio. Non sei i tuoi eleganti pantaloni kaki. Non sei un bellissimo ed unico fiocco di neve. La prima cosa che ti succede e' che non riesci a dormire. Poi ti ritrovi una pistola in bocca. Dopo incontri Tyler Durden. Lascia che ti parli di lui. Lui aveva un piano. Credevamo in Tyler. Tyler dice che cio' che possiedi finisce per possedere te. Solo dopo aver perso tutto sei libero di fare qualsiasi cosa. Il Fight Club rappresenta quel tipo di liberta'. La prima regola del Fight Club e': non si parla del Fight Club. La seconda regola del Fight Club e': non si parla del Fight Club. Tyler dice che il miglioramento di se' stessi e' una masturbazione e l'autodistruzione puo' essere la risposta."),
("Matrix",6,"/locandine/the_matrix.jpg","https://www.youtube.com/embed/hrG2OMlAPDM",136,"Un hacker di nome Neo, grazie all'aiuto del misterioso Morpheus, scopre che quella per lui e' la realta' non e' altro che un facciata, un mondo virtuale creato dal super computer Matrix per controllare gli esseri umani. Ma Morpheus e' convinto che Neo sia il prescelto di cui parla un'antica profezia, e che sara' in grado di guidare la rivoluzione contro Matrix."),
("Inception",3,"/locandine/inception.jpg","https://www.youtube.com/embed/BV7z0I90MJk",148,"Inception, il film diretto da Christopher Nolan, e' un thriller fantascientifico ambientato in un futuro prossimo dove negli gli Stati Uniti e' stato sviluppato il cosiddetto 'sogno condiviso', un metodo che permette di influenzare l'inconscio della vittima mentre dorme. Dom Cobb (Leonardo DiCaprio) e' un ladro tra i piu' qualificati nella pericolosa arte dell'estrazione: la sua specialita' consiste, appunto, nell'appropriarsi dei segreti piu' preziosi di un individuo, sepolti nel profondo del suo subcosciente nel momento in cui e' piu' vulnerabile, ovvero quando dorme e sogna.");

INSERT INTO Prezzo(tipo,prezzo) VALUES
("intero",10.0),
("ridotto",7.0);

INSERT INTO Posto(id_sala,riga,poltrona,esiste) VALUES
(1,1,1,1),(1,1,2,1),(1,1,3,1),(1,1,4,1),(1,1,5,1),(1,1,6,1),(1,1,7,1),(1,1,8,1),(1,1,9,1),(1,1,10,1),
(1,2,1,1),(1,2,2,1),(1,2,3,1),(1,2,4,1),(1,2,5,1),(1,2,6,1),(1,2,7,1),(1,2,8,1),(1,2,9,1),(1,2,10,1),
(1,3,1,1),(1,3,2,1),(1,3,3,1),(1,3,4,1),(1,3,5,1),(1,3,6,1),(1,3,7,1),(1,3,8,1),(1,3,9,1),(1,3,10,1),
(1,4,1,1),(1,4,2,1),(1,4,3,1),(1,4,4,1),(1,4,5,1),(1,4,6,1),(1,4,7,1),(1,4,8,1),(1,4,9,1),(1,4,10,1),
(1,5,1,1),(1,5,2,1),(1,5,3,1),(1,5,4,1),(1,5,5,1),(1,5,6,1),(1,5,7,1),(1,5,8,1),(1,5,9,1),(1,5,10,1),
(1,6,1,1),(1,6,2,1),(1,6,3,1),(1,6,4,1),(1,6,5,1),(1,6,6,1),(1,6,7,1),(1,6,8,1),(1,6,9,1),(1,6,10,1),
(1,7,1,1),(1,7,2,1),(1,7,3,1),(1,7,4,1),(1,7,5,1),(1,7,6,1),(1,7,7,1),(1,7,8,1),(1,7,9,1),(1,7,10,1),
(1,8,1,1),(1,8,2,1),(1,8,3,1),(1,8,4,1),(1,8,5,1),(1,8,6,1),(1,8,7,1),(1,8,8,1),(1,8,9,1),(1,8,10,1),

(2,1,1,1),(2,1,2,1),(2,1,3,1),(2,1,4,1),(2,1,5,1),(2,1,6,1),(2,1,7,1),(2,1,8,1),(2,1,9,1),(2,1,10,1),
(2,2,1,1),(2,2,2,1),(2,2,3,1),(2,2,4,1),(2,2,5,1),(2,2,6,1),(2,2,7,1),(2,2,8,1),(2,2,9,1),(2,2,10,1),
(2,3,1,1),(2,3,2,1),(2,3,3,1),(2,3,4,1),(2,3,5,1),(2,3,6,1),(2,3,7,1),(2,3,8,1),(2,3,9,1),(2,3,10,1),
(2,4,1,1),(2,4,2,1),(2,4,3,1),(2,4,4,1),(2,4,5,1),(2,4,6,1),(2,4,7,1),(2,4,8,1),(2,4,9,1),(2,4,10,1),
(2,5,1,1),(2,5,2,1),(2,5,3,1),(2,5,4,1),(2,5,5,1),(2,5,6,1),(2,5,7,1),(2,5,8,1),(2,5,9,1),(2,5,10,1),
(2,6,1,1),(2,6,2,1),(2,6,3,1),(2,6,4,1),(2,6,5,1),(2,6,6,1),(2,6,7,1),(2,6,8,1),(2,6,9,1),(2,6,10,1),
(2,7,1,1),(2,7,2,1),(2,7,3,1),(2,7,4,1),(2,7,5,1),(2,7,6,1),(2,7,7,1),(2,7,8,1),(2,7,9,1),(2,7,10,1),
(2,8,1,1),(2,8,2,1),(2,8,3,1),(2,8,4,1),(2,8,5,1),(2,8,6,1),(2,8,7,1),(2,8,8,1),(2,8,9,1),(2,8,10,1),

(3,1,1,1),(3,1,2,1),(3,1,3,1),(3,1,4,1),(3,1,5,1),(3,1,6,1),(3,1,7,1),(3,1,8,1),(3,1,9,1),(3,1,10,1),
(3,2,1,1),(3,2,2,1),(3,2,3,1),(3,2,4,1),(3,2,5,1),(3,2,6,1),(3,2,7,1),(3,2,8,1),(3,2,9,1),(3,2,10,1),
(3,3,1,1),(3,3,2,1),(3,3,3,1),(3,3,4,1),(3,3,5,1),(3,3,6,1),(3,3,7,1),(3,3,8,1),(3,3,9,1),(3,3,10,1),
(3,4,1,1),(3,4,2,1),(3,4,3,1),(3,4,4,1),(3,4,5,1),(3,4,6,1),(3,4,7,1),(3,4,8,1),(3,4,9,1),(3,4,10,1),
(3,5,1,1),(3,5,2,1),(3,5,3,1),(3,5,4,1),(3,5,5,1),(3,5,6,1),(3,5,7,1),(3,5,8,1),(3,5,9,1),(3,5,10,1),
(3,6,1,1),(3,6,2,1),(3,6,3,1),(3,6,4,1),(3,6,5,1),(3,6,6,1),(3,6,7,1),(3,6,8,1),(3,6,9,1),(3,6,10,1),
(3,7,1,1),(3,7,2,1),(3,7,3,1),(3,7,4,1),(3,7,5,1),(3,7,6,1),(3,7,7,1),(3,7,8,1),(3,7,9,1),(3,7,10,1),
(3,8,1,1),(3,8,2,1),(3,8,3,1),(3,8,4,1),(3,8,5,1),(3,8,6,1),(3,8,7,1),(3,8,8,1),(3,8,9,1),(3,8,10,1),

(4,1,1,1),(4,1,2,1),(4,1,3,1),(4,1,4,1),(4,1,5,1),(4,1,6,1),(4,1,7,1),(4,1,8,1),(4,1,9,1),(4,1,10,1),
(4,2,1,1),(4,2,2,1),(4,2,3,1),(4,2,4,1),(4,2,5,1),(4,2,6,1),(4,2,7,1),(4,2,8,1),(4,2,9,1),(4,2,10,1),
(4,3,1,1),(4,3,2,1),(4,3,3,1),(4,3,4,1),(4,3,5,1),(4,3,6,1),(4,3,7,1),(4,3,8,1),(4,3,9,1),(4,3,10,1),
(4,4,1,1),(4,4,2,1),(4,4,3,1),(4,4,4,1),(4,4,5,1),(4,4,6,1),(4,4,7,1),(4,4,8,1),(4,4,9,1),(4,4,10,1),
(4,5,1,1),(4,5,2,1),(4,5,3,1),(4,5,4,1),(4,5,5,1),(4,5,6,1),(4,5,7,1),(4,5,8,1),(4,5,9,1),(4,5,10,1),
(4,6,1,1),(4,6,2,1),(4,6,3,1),(4,6,4,1),(4,6,5,1),(4,6,6,1),(4,6,7,1),(4,6,8,1),(4,6,9,1),(4,6,10,1),
(4,7,1,1),(4,7,2,1),(4,7,3,1),(4,7,4,1),(4,7,5,1),(4,7,6,1),(4,7,7,1),(4,7,8,1),(4,7,9,1),(4,7,10,1),
(4,8,1,1),(4,8,2,1),(4,8,3,1),(4,8,4,1),(4,8,5,1),(4,8,6,1),(4,8,7,1),(4,8,8,1),(4,8,9,1),(4,8,10,1);

INSERT INTO Ruolo(id_ruolo,ruolo) VALUES
(1,"standard"),
(2,"admin");

INSERT INTO Utente(id_ruolo,email,password,credito) VALUES
(2,"domenico.stefani@cinema.com","domste",10.0),
(2,"matteo.tadiello@cinema.com","mattad",10.0),
(2,"ivan.martini@cinema.com","ivamar",10.0);
