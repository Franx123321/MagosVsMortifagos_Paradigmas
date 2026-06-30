package Combate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hechizos.Hechizo;
import personajes.Estado;
import personajes.Personaje;

public class Batallon {
    private List<Personaje> integrantes;
    private Set<String> hechizosLanzadosEnTurno; 
    private Map<String, List<String>> historialHechizosPorPersonaje;

    public Batallon() {
        this.integrantes = new ArrayList<>();
        this.hechizosLanzadosEnTurno = new HashSet<>();
        this.historialHechizosPorPersonaje = new HashMap<>();
    }

    public void agregarPersonaje(Personaje p) {
        if (p != null) {
            integrantes.add(p);
        }
    }

    public boolean tienePersonajesSaludables() {
        for (Personaje p : integrantes) {
            if (p.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    public List<Personaje> obtenerIntegrantesVivos() {
        List<Personaje> vivos = new ArrayList<>();
        for (Personaje p : integrantes) {
            if (p.estaVivo()) {
                vivos.add(p);
            }
        }
        return vivos;
    }

    public void ejecutarTurno(Batallon batallonEnemigo) {
        // Al comenzar el turno del batallón, se limpia el Set de hechizos usados
        hechizosLanzadosEnTurno.clear();
        
        System.out.println("\n--- Turno de las acciones del Batallón ---");

        for (Personaje actuante : integrantes) {
        	
        	if (actuante.estaVivo() && actuante.tieneEstado(Estado.PARALIZADO)) {
                System.out.println(actuante.getNombre() + " está PARALIZADO y pierde su acción.");
                actuante.actualizarEstados();
                continue;
            } else if (!actuante.estaVivo()) {
            	continue;
            }
        	

            List<Hechizo> conocidos = actuante.getHechizos();
            if (conocidos.isEmpty()) {
                System.out.println(actuante.getNombre() + " no conoce ningún hechizo.");
                
                actuante.actualizarEstados();
                continue;
            }

            List<Hechizo> disponibles = new ArrayList<>();
            for (Hechizo h : conocidos) {
                if (!hechizosLanzadosEnTurno.contains(h.getNombre())) {
                    disponibles.add(h);
                }
            }

            Hechizo hechizoAEjecutar = null;
            if (!disponibles.isEmpty()) {
                // Elige un hechizo al azar de entre todos los que cumplen la condición
                hechizoAEjecutar = disponibles.get((int) (Math.random() * disponibles.size()));
            }

            if (hechizoAEjecutar != null) {
            	// Buscamos un objetivo enemigo vivo al azar
            	List<Personaje> enemigosObjetivo = batallonEnemigo.obtenerIntegrantesVivos();
            	if (enemigosObjetivo.isEmpty()) {
            	    break; // No quedan enemigos vivos, termina el combate
            	}
            	
            	Personaje objetivo;
            	if (hechizoAEjecutar.esTargetAliado()) {
            	    // Si es curación o protección (Protego), va a un aliado vivo del mismo batallón
            	    List<Personaje> aliadosVivos = this.obtenerIntegrantesVivos();
            	    objetivo = aliadosVivos.get((int) (Math.random() * aliadosVivos.size()));
            	} else {
            		actuante.notificarAntesDeAtaqueDanio(hechizoAEjecutar);
            	    objetivo = enemigosObjetivo.get((int) (Math.random() * enemigosObjetivo.size()));
            	}
            	System.out.printf("     %-20s -> lanzó [%-18s] -> a %s\n", 
            		    actuante.getNombre() + " (" + actuante.getClass().getSimpleName() + ")", 
            		    hechizoAEjecutar.getNombre(), 
            		    objetivo.getNombre());
                
                // Registrar en el turno y en el historial global del mapa
                hechizosLanzadosEnTurno.add(hechizoAEjecutar.getNombre());
                registrarEnHistorial(actuante, hechizoAEjecutar.getNombre());

                actuante.lanzarHechizo(hechizoAEjecutar, objetivo);
                actuante.despuesDeLanzarHechizo(hechizoAEjecutar);

                //si mato al objetivo le informa al otro batallon que se murio (para que el comandante actue en consecuencia), si se mato solo le informa a su equipo
                if (!objetivo.estaVivo()) {
                    if (batallonEnemigo.contiene(objetivo)) {
                        batallonEnemigo.notificarCaidaDeAliado(objetivo);
                    } 
                }
                else if(!actuante.estaVivo()) {
                	this.notificarCaidaDeAliado(actuante);
                }
                
            } else {
                System.out.println(actuante.getNombre() + " no pudo actuar porque todos sus hechizos ya se repitieron en este turno.");
            }
            
            actuante.actualizarEstados();
        }
    }

    public void notificarCaidaDeAliado(Personaje aliadoCaido) {
        for (Personaje integrante : integrantes) {
            integrante.reaccionarACaidaDeAliado(aliadoCaido);
        }
    }
    
    private void registrarEnHistorial(Personaje p, String nombreHechizo) {
        String clave = p.getNombre() + " (" + p.getClass().getSimpleName() + ")";
        
        if (!historialHechizosPorPersonaje.containsKey(clave)) {
            historialHechizosPorPersonaje.put(clave, new ArrayList<>());
        }
        
        historialHechizosPorPersonaje.get(clave).add(nombreHechizo);
    }

    public void mostrarEstadisticasBatallon() {
        if (historialHechizosPorPersonaje.isEmpty()) {
            System.out.println("   No se llegaron a lanzar hechizos.");
            return;
        }
        
        for (Map.Entry<String, List<String>> entrada : historialHechizosPorPersonaje.entrySet()) {
            System.out.println("    * " + entrada.getKey() + " lanzó: " + entrada.getValue());
        }
    }
    
    //a que personajes contiene el batallon
    public boolean contiene(Personaje personaje) {
        return integrantes.contains(personaje);
    }
    
    public List<Personaje> getIntegrantes() {
        return this.integrantes;
    }

    public int contarLanzamientosEnHistorial(String nombreHechizo) {
        int total = 0;
        for (List<String> lanzamientos : historialHechizosPorPersonaje.values()) {
            for (String hechizo : lanzamientos) {
                if (hechizo.equals(nombreHechizo)) {
                    total++;
                }
            }
        }
        return total;
    }
}