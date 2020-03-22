package it.polito.tdp.libretto.model;

import java.util.Comparator;

//comparatore per l'ordine di voto decrescente
class ConfrontaVotiPerValutazione implements Comparator<Voto> {

	@Override
	public int compare(Voto o1, Voto o2) {
		//ordine decrescente perche' voglio che o2 venga prima se il suo voto e' piu' alto
		return o2.getVoto() - o1.getVoto();
	}

}
