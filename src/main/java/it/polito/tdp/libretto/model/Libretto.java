package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Memorizza e gestisce un insieme di voti superati.
 * 
 * @author Fulvio
 *
 */
public class Libretto {

	private List<Voto> voti = new ArrayList<>();
	
	/**
	 * Crea un libretto nuovo (e vuoto)
	 */
	public Libretto() { //costruttore
		super() ;
	}
	
	/** 
	 * Copy Constructor
	 * "Shallow" (copia superficiale)
	 * @param lib
	 */
	public Libretto(Libretto lib) { 
		//copia superficiale perche' fa una copia dell'oggetto corrente, ma non degli oggetti che sono contenuti in esso che quindi rimangono collegati a quelli originali
		//quindi con la copia superficiale, abbiamo gli stessi oggetti, ma li mettiamo in una lista diversa e questo serve quando dobbiamo tipo andare a riordinare le liste
		//senza andare a modificare all'interno gli oggetti che la compongono.
		//Una copia profonda si puo' fare, cioe' una copia in cui anche gli oggetti all'interno vengono copiati e cioe' ne vengono creati di nuovo, ma se l'obiettivo non e' modificarli
		//ma solo ordinarli in maniera diversa, allora e' uno spreco di energie e computazionale.
		super() ;
		//non devo modificare i voti quindi li condivido tra le due liste
		
		//aggiungo tutti gli elementi in argomento
		this.voti.addAll(lib.voti) ;
	}

	/**
	 * Aggiunge un nuovo voto al libretto
	 * 
	 * @param v Voto da aggiungere
	 * @return {@code true} se ha inserito il voto, {@code false} se non l'ha
	 * inserito perché era in conflitto oppure era duplicato.
	 */
	public boolean add(Voto v) { //ha senso far restituire un booleano ad add perche' cosi' sappiamo se gli inserimenti sono andati a buon fine o meno
		//queste modifiche sono in seguito al punto 4 e 5 in cui abbiamo introdotto conflitto e duplicato
		if(this.isConflitto(v) || this.isDuplicato(v)) {
			// non inserire il voto
			return false ; // segnala al chiamante che non ha avuto successo
		} else {
			// inserisici il voto perché non è in conflitto né duplicato
			this.voti.add(v);
			return true ;
		}
	}

	/**
	 * Dato un Libretto, restituisce una stringa nella quale vi sono solamente i
	 * voti pari al valore specificato
	 * 
	 * @param voto valore specificato
	 * @return stringa formattata per visualizzare il sotto-libretto
	 */
	public String stampaVotiUguali(int voto) {
		String s = "";
		for (Voto v : this.voti) {
			if (v.getVoto() == voto) {
				s += v.toString() + "\n";
			}
		}
		return s;
	}
	
	/**
	 * Genera un nuovo libretto, a partire da quello esistente,
	 * che conterrà esclusivamenti i voti con votazione pari a quella specificata.
	 * @param voto votazione specificata
	 * @return nuovo Libretto "ridotto"
	 */
	public Libretto estraiVotiUguali(int voto) {
		Libretto nuovo = new Libretto() ;
		for(Voto v: this.voti) {
			if(v.getVoto()==voto) {
				nuovo.add(v);
			}
		}
		return nuovo ;
	}

	public String toString() {
		String s = "";
		for (Voto v : this.voti) {
			s += v.toString() + "\n";
		}
		return s;
	}

	/**
	 * Data il nome di un corso, ricerca se quell'esame esiste
	 * nel libretto, ed in caso affermativo restituisce l'oggetto
	 * {@link Voto} corrispondente.
	 * Se l'esame non esiste, restituisce {@ null}.
	 * 
	 * @param nomeCorso nome esame da cercare
	 * @return il {@link Voto} corrispondente, oppure {@code null} se non esiste
	 */
	public Voto cercaNomeCorso(String nomeCorso) {
		/*
		//questo per il punto 3 in cui si cerca il voto in base al nome scorrendo la lista di voti
		for (Voto v: this.voti) {
			if(nomeCorso.equals(v.getCorso())) {
				return v ;
			}
		}
		
		//se il voto non esiste restituisco null
		return null;
		*/
		
		//quella qua sopra e' una implementazione piu' classica e lineare
		
		//IndexOf cerca tra i voti e confronta mediante il metodo equals (di voti in questo caso) della classe Voto
		//appena trova la corrispondenza indexOf si ferma e quindi viene restituito l'indice della prima occorrenza (anche se le occorrenze fossero piu' di una)
		//siccome equals di Voto restituisce true con il nome del corso uguale, basta creare il voto (che non va nel libretto) con lo stesso nome del corso, gli altri due campi non
		//sono importanti per il metodo equals
		int pos = this.voti.indexOf(new Voto(nomeCorso, 0, null)) ;
		//estraiamo la posizione dalla lista di voti di un voto 
		if(pos != -1) {
			return this.voti.get(pos) ;
		} else { //se sono qui significa che non ho trovato la corrispondenza con il nome del corso
			return null ;
		}
	}
	
	/**
	 * Ritorna {@code true} se il corso specificato da {@code v}
	 * esiste nel libretto, con la stessa valutazione.
	 * Se non esiste, o se la valutazione è diversa, ritorna {@code false}.
	 * @param v il {@link Voto} da ricercare
	 * @return l'esistenza di un duplicato
	 */
	public boolean isDuplicato(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso()) ;
		if(esiste==null) // non l'ho trovato ==> non è un duplicato
			return false ;
		
		/*
		if(esiste.getVoto()==v.getVoto())
			return true ;
		else
			return false ;
			*/
		//ritorniamo direttamente il confronto booleano tra i voti, per evitare di fare l'if di sopra
		return ( esiste.getVoto() == v.getVoto() ) ;
	}
	
	/**
	 * Determina se esiste un voto con lo stesso nome corso ma valutazione
	 * diversa
	 * @param v
	 * @return
	 */
	public boolean isConflitto(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso()) ;
		if(esiste==null) // non l'ho trovato ==> non è un duplicato
			return false ;
		//se le votazioni sono diverse allora ho un conflitto perche' stesso corso con due valutazioni diverse fa un po' strano
		return ( esiste.getVoto() != v.getVoto() ) ;
	}

	/**
	 * Restituisce un NUOVO libretto, migliorando i voti del libretto attuale.
	 * 
	 * @return
	 */
	public Libretto creaLibrettoMigliorato() {
		Libretto nuovo = new Libretto() ;
		
		for(Voto v : this.voti) {
			
			//errore nel fare
			//Voto v2=v; 
			//perche' con la riga di sopra non creo un nuovo oggetto, ma v2 e v puntano agli stessi oggetti
			//e dunque quindi quando modifico l'oggetto a cui punta v2, allora anche quello a cui punta v
			//cambia, perche' v2 e v puntano alla stessa cosa
			
			//bisogna creare una copia dell'oggetto a cui v punta, quindi si puo' fare il copyCostructor
			//questa prima riga qui sotto e' quello del CopyConstructor
			//Voto v2 = new Voto(v) ; //e' un'alternativa per la copia in cui creo il nuovo oggetto partendo da un voto esistente
			//metodo che abbiamo creato in Voto che crea un clone dell'oggetto esistente, cioe' crea un'oggetto nuovo in cui ricopio le cose di partenza
			Voto v2 = v.clone() ;
			
			// NON CI PIACE : Voto v3 = new Voto(v.getCorso(), v.getVoto(), v.getData()) ;
			
			if(v2.getVoto()>=24) {
				v2.setVoto(v2.getVoto()+2);
				if(v2.getVoto()>30)
					v2.setVoto(30);
			} else if (v2.getVoto()>=18) { //il voto originale tra 18 e 24
				v2.setVoto(v2.getVoto()+1);
			}
			
			nuovo.add(v2) ;
		}
		
		return nuovo ;
	}
	
	/**
	 * riordina i voti presenti nel libretto corrente
	 * alfabeticamente per corso
	 */
	public void ordinaPerCorso() {
		//l'ordinamento naturale basato sul metodo CompareTo che abbiamo definito su Voto
		Collections.sort(this.voti) ;
	}
	
	public void ordinaPerVoto() {
		//con un ordinamento non naturale dobbiamo andare a ridefinire il comparatore rispetto a cui vogliamo ordinare
		Collections.sort(this.voti, new ConfrontaVotiPerValutazione());
		//this.voti.sort(new ConfrontaVotiPerValutazione());
	}

	/**
	 * Elimina dal libretto tutti i voti <24
	 */
	public void cancellaVotiScarsi() {
		List<Voto> daRimuovere=new ArrayList<>() ;
		for(Voto v: this.voti) {
			if(v.getVoto()<24) {
				daRimuovere.add(v);
			}
		}

		//togliamo tutti quelli presenti nell'argomento
		this.voti.removeAll(daRimuovere) ;
		
		//non posso fare questa cosa qui sotto perche' non posso modificare una lista sulla quale sto iterando in quanto si possono creare 
		//delle incongruenze
//		for(Voto v: daRimuovere) {
//			this.voti.remove(v) ;
//		}
		
	}
	
}
