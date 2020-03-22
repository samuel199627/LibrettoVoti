package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {
	
	Libretto lib ;
	
	private void run() {
		this.lib = new Libretto() ; // crea libretto vuoto
		
		// 1. Inserire alcuni voti
		Voto v1 = new Voto("Tecniche di programmazione", 30, LocalDate.of(2020, 06, 15)) ;
		Voto v2 = new Voto("Analisi II", 28, LocalDate.of(2020, 06, 28)) ; //2020-06-28

		lib.add(v1);
		lib.add(v2);
		if( lib.add(new Voto("Economia", 24, LocalDate.of(2020, 02, 14))) == false)
			System.out.println("Errore nell'inserimento di Economia") ;
		
		System.out.println(this.lib) ;
		
		// 2. Stampa tutti i voti uguali a 28
		System.out.println(this.lib.stampaVotiUguali(28)) ;
		
		System.out.println(this.lib.estraiVotiUguali(28)) ;
		
		// 3. Cerca un esame superato, conoscendo il nome del corso
		System.out.println("\n\nPUNTO 3\n\n") ;
		String nomeCorso = "Analisi II" ;
		Voto votoAnalisi = lib.cercaNomeCorso(nomeCorso) ;
		System.out.println("Il voto di "+nomeCorso+" è "+votoAnalisi.getVoto()) ;
		Voto votoMancante = lib.cercaNomeCorso("Fisica I") ; //FisicaI non c'e' quindi non ho nessun voto
		System.out.println("Il voto di Fisica I è "+votoMancante) ; //voto mancante e' null quindi non accedo ai campi altrimenti avrei eccezione e mi stampa proprio 'null'
		
		// 4. 5. Verifica voti duplicati o voti in conflitto
		//contano solo il nome del corso e il voto, non la data quindi non vado a crearla
		System.out.println("\n\nPUNTO 4/5\n\n") ;
		Voto economia2 = new Voto("Economia", 24, LocalDate.now()) ;
		Voto economia3 = new Voto("Economia", 21, LocalDate.now()) ;
		System.out.println("Economia con 24 è duplicato: "+
		lib.isDuplicato(economia2)+"/ conflitto:"+
		lib.isConflitto(economia2)) ;
		
		System.out.println("Economia con 21 è duplicato: "+
		lib.isDuplicato(economia3)+"/ conflitto:"+
		lib.isConflitto(economia3)) ;
		
		// 7. Migliora il libretto
		System.out.println("\n\nPUNTO 7\n\n") ;
		Libretto migliorato = lib.creaLibrettoMigliorato() ;
		System.out.println("Miglioramento del libretto") ;
		//come mi viene richiesto stampo entrambi i libretti
		System.out.println(lib); 
		System.out.println(migliorato);
		
		// 8. Stampa in ordine alfabetico
		//creiamo il copycostructor per copiare un libretto quando dobbiamo ordinare perche' magari quello con l'ordinamento iniziale lo vogliamo tenere
		System.out.println("\n\nPUNTO 8\n\n") ;
		//e' il copyConstructor questo che chiamiamo
		Libretto alfabetico = new Libretto(lib) ;
		alfabetico.ordinaPerCorso();
		System.out.println(alfabetico);
		// 8. Stampa in ordine di voto
		Libretto votidecrescenti = new Libretto(lib) ;
		votidecrescenti.ordinaPerVoto();
		System.out.println(votidecrescenti);
		
		// 9. Elimina voti bassi (sotto al 24)
		System.out.println("\n\nPUNTO 9\n\n") ;
		lib.add(new Voto("Chimica", 19, LocalDate.now())) ;
		lib.ordinaPerCorso();
		System.out.println(lib) ;
		lib.cancellaVotiScarsi();
		System.out.println(lib) ;

	}

	public static void main(String[] args) {
		TestLibretto test = new TestLibretto() ;
		test.run();
	}

}
