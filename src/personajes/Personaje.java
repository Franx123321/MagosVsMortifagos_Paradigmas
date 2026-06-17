package personajes;
import java.util.List;
import java.util.ArrayList;
import hechizos.Hechizo;

public abstract class Personaje {
	private String nombre;
	private int nivelMagia;
	private int puntosVida;
	private int puntosVidaMax; //Esto para que no se pueda curar hasta el infinito y tener 1000 de vida si arranca con 100 por ej.
	private List<Hechizo> hechizos;
	
	public Personaje(String nombre, int nivelMagia, int puntosVida) {
		this.nombre = nombre;
		this.nivelMagia = nivelMagia;
		this.puntosVida = puntosVida;
		this.puntosVidaMax = puntosVida;
		this.hechizos = new ArrayList<>();
	}
	
	public void recibirDanio(int danio) {
		this.puntosVida -= danio;
		
		if(this.puntosVida < 0) {
			this.puntosVida = 0;
		}
	}
	
	public void curarse(int vida) {
		this.puntosVida += vida;
		
		if(this.puntosVida > this.puntosVidaMax) {
			this.puntosVida = this.puntosVidaMax;
		}
	}
	
	public boolean estaVivo() {
		return this.puntosVida > 0;
	}
	
	public void aprenderHechizo(Hechizo h) {
		if(this.puedeAprender(h)) {
			this.hechizos.add(h);	
		}
	}

	public void lanzarHechizo(Hechizo h) {
		//a implementar una vez armados los hechizos
	}
	
	//esto es porque los magos no pueden aprender hechizos oscuros, y los mortifagos no pueden aprender Expecto Patronum.
	public abstract boolean puedeAprender(Hechizo h);
	//esto es para las "especializaciones" que tiene cada bando.
	public abstract int modificadorDanio(int base);
	public abstract int modificadorCuracion(int base);
	public abstract int modificadorDefensa(int base);
	
	
	//getters
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
