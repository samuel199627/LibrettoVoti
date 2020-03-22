package it.polito.tdp.libretto.model;

import java.time.LocalDate;

/**
 * Classe Voto, contiene le informazioni su un esame
 * superato.
 * 
 * @author Fulvio
 *
 */
public class Voto implements Comparable<Voto> { 
	//con implements Comparable<Voto> diciamo a Java che la classe Voto si sa confrontare con altri oggetti di tipo Voto
	//dobbiamo aggiungere il metodo CompareTo che sara' l'ordinamento naturale che definiamo sul voto (per noi sara' l'alfabetico per nome del corso)
	//Comparable e' un'interfaccia interna, mentre per ordinamenti non naturali serve un'interfaccia esterna che e' il Comparator
	
	private String corso ; // "Tecniche di Programmazione"
	private int voto ; // 28
	private LocalDate data ; // 15/06/2020
	
	/**
	 * Costruisce un nuovo Voto.
	 * 
	 * @param corso nome del corso superato
	 * @param voto valore del voto acquisito
	 * @param data data di superamento dell'esame
	 */
	public Voto(String corso, int voto, LocalDate data) {
		super();
		this.corso = corso;
		this.voto = voto;
		this.data = data;
	}
	
	//questo e' una maniera per fare la clonazione
	/**
	 * Copy constructor di {@link Voto}: crea un nuovo {@link Voto}, copiando
	 * il contenuto del parametro {@code v}.
	 * @param v il voto da copiare
	 */
	public Voto(Voto v) { //la sua alternativa e' il metodo clone che abbiamo anche creato
		//perche' a differenza del voto (per quanto riguarda la copia) qui con l'uguale non creaimo ambiguita' con due variabili che puntano allo stesso oggetto?
		//la risposta e' perche' questi oggetti qui sono immutabili a differenza di Voto.
		//In particolare String e LocalDate sono classi immutabili e dunque una volta che cambio la stringa, il riferimento punta ad un'altra cosa e dunque non ho ambiguita', la stessa cosa per la data.
		//int e' primitivo, quindi non e' un oggetto e ogni volta che ho un uguale e'come fare una copia
		this.corso = v.corso ; // non abbiamo v.getCorso() perche' siamo nell'oggetto this, che e' un voto e quindi posso accedere nei campi dell'oggetto dello stesso tipo semplicemente con la notazione puntata
		this.data = v.data ;
		this.voto = v.voto ;
	}

	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return corso + ": " + voto + " (" + data + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corso == null) ? 0 : corso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) { 
		//il metodo equals e' generato confrontando  i nomi del corso
		//il succo del confronto e' la parte finale. Generandolo con source java mi da
		//gratis tutti altri controlli di casi particolari che mi sono molto utili
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		return true;
	}
	
	/**
	 * crea una copia (clone) dell'oggetto presente (this), come nuovo oggetto
	 */
	public Voto clone() {
		Voto v = new Voto(this.corso, this.voto, this.data) ;
		return v ;
	}

	@Override
	public int compareTo(Voto other) {
		/*
		 * <0  se this < other
		 * ==0 se this ==other
		 * >0 se this > other
		 */
		//questo e' il confronto alfabetico crescente tra stringhe che hanno gia' di loro un metodo CompareTo definito in loro
		return this.corso.compareTo(other.corso) ;
	}
	
	

}
