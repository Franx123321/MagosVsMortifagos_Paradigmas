package personajes;

public class Comandante extends Mortifago{
	
	private static final double MULTIPLICADOR_VENGANZA = 1.5; //aumento de danio en base a la venganza
	
	public Comandante(String nombre, int nivelMagia, int puntosVida) {
		super(nombre, nivelMagia, puntosVida);
	}
	
	public void reaccionarACaidaDeAliado(Personaje aliadoCaido) {
		if (!this.estaVivo() || aliadoCaido == this) {
			return;
		}

		if (!this.tieneEstado(Estado.VENGANZA)) {
			this.agregarEstado(Estado.VENGANZA, 1);
			System.out.println("     >>> " + this.getNombre()
					+ " entra en VENGANZA tras la caída de " + aliadoCaido.getNombre()
					+ ". Su próximo ataque va a inflingir daño potenciado.");
		}
	}

	@Override
	public int modificadorDanio(int base) {
		int danio = super.modificadorDanio(base) + 15;
		
		if (this.tieneEstado(Estado.VENGANZA)) { //le aumento el danio si tenia venganza activada
			danio = (int) (danio * MULTIPLICADOR_VENGANZA);
		}
		
		return danio;
	}
	
	@Override
	public int modificadorCuracion(int base) {
		return super.modificadorCuracion(base) - 10;
	}
	
	@Override
	public int modificadorDefensa(int base) {
		return super.modificadorDefensa(base) - 5;
	}
}
