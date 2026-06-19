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
		if (this.tieneEstado(Estado.PROTEGIDO)) {
			danio /= 2;
			this.quitarEstado(Estado.PROTEGIDO);
		}

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
		if (this.puedeAprender(h)) {
			this.hechizos.add(h);
		}
	}

	public void lanzarHechizo(Hechizo h, Personaje objetivo) {
		if (this.tieneEstado(Estado.PARALIZADO))
			return;

		h.ejecutar(this, objetivo);
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
