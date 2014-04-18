package ar.com.gmn.android.core;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Numero {

	private List<Integer> numero = new Vector<Integer>();

	public Numero(String numero) {
		this.numero = toArray(numero);
	}
	public void rotarPosiciones(Integer a, Integer b){
		Integer valorA = get(a);
		Integer valorB = get(b);
		set(a,valorB);
		set(b, valorA);
	}
	public Integer getPosicionDigito(Integer valor){
		if(numero.contains(valor)){
			return this.numero.indexOf(valor);
		}
		return 0;
	}
	public Integer getSiguientePosicion(Integer a){
		if(a>= this.numero.size()){
			return 1;
		}else{
			return a +1;
		}
	}
	public Numero clone(){
		Numero n = new Numero();
		Collections.copy(n.numero, this.numero);
		return n;
	}

	
	
	public Numero() {
		this.numero = new Vector<Integer>();
		this.numero.add(-1);
		this.numero.add(-1);
		this.numero.add(-1);
		this.numero.add(-1);

	}

	public boolean isInitial() {
		return this.numero.get(0) < 0 && this.numero.get(1) < 0
				&& this.numero.get(2) < 0 && this.numero.get(3) < 0;

	}

	public boolean isCompleto() {
		return this.numero.get(0) >= 0 && this.numero.get(1) >= 0
				&& this.numero.get(2) >= 0 && this.numero.get(3) >= 0;
	}

	public void set(Integer posicion, Integer valor) {
		this.numero.set(posicion - 1, valor);
	}

	private List<Integer> toArray(String valor) {

		List<Integer> a = new Vector<Integer>();
		for (int i = 0; i < valor.length(); i++) {
			a.add(Integer.parseInt(String.valueOf(valor.charAt(i))));
		}
		return a;
	}

	public Integer get(Integer i) {
		return this.numero.get(i - 1);
	}

	@Override
	public boolean equals(Object obj) {

		Numero aComparar = (Numero) obj;
		for (int j = 1; j <= numero.size(); j++) {
			if (!aComparar.get(j).equals(this.get(j))) {
				return false;
			}
		}
		return true;

	}

	@Override
	public String toString() {

		return numero.toString();
	}

	public List<Integer> getNumero() {

		return this.numero;
	}

	public boolean contiene(Integer comparado) {

		return this.numero.contains(comparado);
	}
	public List<Integer> getPosicionesIniciales() {
		List<Integer> posiciones = new Vector<Integer>();
		for (Integer integer : numero) {
			if(integer>=0){
				posiciones.add(integer);
			}
		}
		return posiciones;
	}

}
