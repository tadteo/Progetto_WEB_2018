<h1>Sistema di prenotazione cinema multisala</h1>

<h4>Istruzioni setup database</h4>
Impostare connessione con:
<ul>
	<li>User: "root"</li>
	<li>Password: "root"</li>
	<li>Porta Standard: "3306"</li>
</ul>
<h5>Setup:</h5>
<ol>
	<li>Eseguire cinema.sql per creare il db</li>
	<li>Importare in ordine:
		<ol>
			<li>data_sala.csv	(NON IMPORTARE LA PRIMA COLONNA ID) (Record importati: 4)</li>
			<li>data_genere.csv	(NON IMPORTARE LA PRIMA COLONNA ID) (Record importati: 6)</li>
			<li>data_film.csv (Record importati: 10)</li>
			<li>data_prezzo.csv (Record importati: 2)</li>
			<li>data_posto.csv (Record importati: 320)</li>
			<li>data_ruolo.csv (Record importati: 2)</li>
			<li>data_utente.csv (Record importati: 3)</li>
		</ol>
	</li>
</ol>
