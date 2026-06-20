package hechizos;

import java.util.ArrayList;
import java.util.List;

public class HechizoFactory {
	public static Hechizo crearHechizo(String nombre) {

		switch (nombre) {
		case "Expelliarmus":
			return new Expelliarmus();
			
		case "Avada Kedavra":
			return new AvadaKedavra();
			
		case "Crucio":
			return new Crucio();
			
		case "Diffindo":
			return new Diffindo();
			
		case "Episkey":
			return new Episkey();
			
		case "Ferula":
			return new Ferula();
			
		case "Flipendo":
			return new Flipendo();
			
		case "Imperio":
			return new Imperio();
			
		case "Petrificus Totalus":
			return new PetrificusTotalus();
			
		case "Protego":
			return new Protego();
			
		case "Reducto":
			return new Reducto();
			
		case "Reparifors":
			return new Reparifors();
			
		case "Sectusempra":
			return new Sectusempra();
			
		case "Vulnera Sanentur":
			return new VulneraSanentur();

		default:
			throw new IllegalArgumentException("Hechizo inexistente.");
		}
	}
	
	public static List<Hechizo> obtenerTodos() {
		List<Hechizo> hechizos = new ArrayList<>();
		
		hechizos.add(new Expelliarmus());
	    hechizos.add(new AvadaKedavra());
	    hechizos.add(new Crucio());
	    hechizos.add(new Diffindo());
	    hechizos.add(new Episkey());
	    hechizos.add(new Ferula());
	    hechizos.add(new Flipendo());
	    hechizos.add(new Imperio());
	    hechizos.add(new PetrificusTotalus());
	    hechizos.add(new Protego());
	    hechizos.add(new Reducto());
	    hechizos.add(new Reparifors());
	    hechizos.add(new Sectusempra());
	    hechizos.add(new VulneraSanentur());
	    
	    return hechizos;
	}
}
