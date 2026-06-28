package personajes;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import hechizos.Hechizo;

public abstract class Personaje {
	private String nombre;
	private int nivelMagia;
	private int puntosVida;
	private int puntosVidaMax; // Esto para que no se pueda curar hasta el infinito y tener 1000 de vida si
								// arranca con 100 por ej.
	private List<Hechizo> hechizos;
	private Set<Estado> estados; // Un set para que no haya estados duplicados.

	public Personaje(String nombre, int nivelMagia, int puntosVida) {
		this.nombre = nombre;
		this.nivelMagia = nivelMagia;
		this.puntosVida = puntosVida;
		this.puntosVidaMax = puntosVida;
		this.hechizos = new ArrayList<>();
		this.estados = new HashSet<>();
	}

	public void recibirDanio(int danio) {
	    danio = modificadorDefensa(danio); 
	    
	    //Si la defensa redujo el daño a menos de 0, lo dejamos en 0 para que no cure
	    if (danio < 0) {
	        danio = 0; 
	    }
	    
	    //Si tiene Protego, se reduce a la mitad el daño restante
	    if (this.tieneEstado(Estado.PROTEGIDO)) {
	        danio /= 2;
	        this.quitarEstado(Estado.PROTEGIDO);
	    }
	    
	    //Restamos la vida final
	    this.puntosVida -= danio;
	    if (this.puntosVida < 0) {
	        this.puntosVida = 0;
	    }
	}

	public void curarse(int vida) {
		this.puntosVida += vida;

		if (this.puntosVida > this.puntosVidaMax) {
			this.puntosVida = this.puntosVidaMax;
		}
	}

	public boolean estaVivo() {
		return this.puntosVida > 0;
	}

	public void aprenderHechizo(Hechizo h) {
		if (h == null) {
			throw new NullPointerException("Debe especificarse un hechizo.");
		}

		if (!this.puedeAprender(h)) {
			return;
		}

		for (Hechizo hechizo : hechizos) {
			if (hechizo.getNombre().equals(h.getNombre()))
				return;
		}
		
		hechizos.add(h);
	}

	public void lanzarHechizo(Hechizo h, Personaje objetivo) {
		if(h == null || objetivo == null) {
			throw new NullPointerException("Para lanzar un hechizo deben especificarse hechizo y objetivo.");
		}
		
		if (tieneEstado(Estado.PARALIZADO) || !estaVivo() || !conoceHechizo(h)) {
	        return;
	    }
			
		h.ejecutar(this, objetivo);
	}
	
	private boolean conoceHechizo(Hechizo h) {
		for(Hechizo hechizo : hechizos) {
			if(hechizo.getNombre().equals(h.getNombre())) {
				return true;
			}
		}
		
		return false;
	}

	// Esto es porque los magos no pueden aprender hechizos oscuros, y losmortifagos
	// no pueden aprender
	// Expecto Patronum. De la misma forma, hay hechizos de diferentes niveles.
	public abstract boolean puedeAprender(Hechizo h);

	// Esto es para las "especializaciones" que tiene cada lado.
	public abstract int modificadorDanio(int base);

	public abstract int modificadorCuracion(int base);

	public abstract int modificadorDefensa(int base);

	public void agregarEstado(Estado estado) {
		estados.add(estado);
	}

	public void quitarEstado(Estado estado) {
		estados.remove(estado);
	}

	public boolean tieneEstado(Estado estado) {
		return estados.contains(estado);
	}

	// getters
	public String getNombre() {
		return nombre;
	}

	public int getNivelMagia() {
		return nivelMagia;
	}

	public int getPuntosVida() {
		return puntosVida;
	}

	public List<Hechizo> getHechizos() {
		return hechizos;
	}
}
