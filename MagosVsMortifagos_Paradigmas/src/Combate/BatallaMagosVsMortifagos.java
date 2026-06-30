package Combate;

import java.util.Scanner;
import personajes.Personaje;
import personajes.PersonajeFactory;

public class BatallaMagosVsMortifagos {
    public static void main(String[] args) {
        // Creamos el Scanner para capturar el Enter del usuario
        Scanner scanner = new Scanner(System.in);

        // Creamos los dos batallones
        Batallon batallonMagos = new Batallon();
        Batallon batallonMortifagos = new Batallon();

        // Poblamos los equipos usando la Factory
        batallonMagos.agregarPersonaje(PersonajeFactory.crearEstudiante("Harry"));
        batallonMagos.agregarPersonaje(PersonajeFactory.crearProfesor("Lupin"));
        batallonMagos.agregarPersonaje(PersonajeFactory.crearAuror("Kingsley"));

        batallonMortifagos.agregarPersonaje(PersonajeFactory.crearSeguidor("Crabbe Sr."));
        batallonMortifagos.agregarPersonaje(PersonajeFactory.crearSeguidor("Goyle Sr."));
        batallonMortifagos.agregarPersonaje(PersonajeFactory.crearComandante("Lucius Malfoy"));
        
        int ronda = 1;

     // Loop principal de la batalla
        while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {
        	limpiarConsola();
            System.out.println("\n======================================================================");
            System.out.println("                         RONDA N° " + ronda);
            System.out.println("======================================================================");

            // 1. Mostramos el estado de los luchadores antes de que se peguen
            System.out.println("COMBATIENTES EN PIE:");
            mostrarEstadoVida("MAGOS", batallonMagos);
            mostrarEstadoVida("MORTÍFAGOS", batallonMortifagos);
            System.out.println("----------------------------------------------------------------------");
            
            System.out.println("ACCIONES DE LA RONDA:");

            // 2. Decidimos quién arranca al azar
            if (Math.random() < 0.5) {
                System.out.println(" [INICIATIVA: MAGOS]");
                batallonMagos.ejecutarTurno(batallonMortifagos);
                
                if (batallonMortifagos.tienePersonajesSaludables()) {
                    System.out.println("\n [CONTRAATAQUE: MORTÍFAGOS]");
                    batallonMortifagos.ejecutarTurno(batallonMagos);
                }
            } else {
                System.out.println(" [INICIATIVA: MORTÍFAGOS]");
                batallonMortifagos.ejecutarTurno(batallonMagos);
                
                if (batallonMagos.tienePersonajesSaludables()) {
                    System.out.println("\n [CONTRAATAQUE: MAGOS]");
                    batallonMagos.ejecutarTurno(batallonMortifagos);
                }
            }

            System.out.println("======================================================================");
            ronda++;

            // 3. LA PAUSA INTERACTIVA
            if (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {
                System.out.print(" Presioná [ENTER] para la siguiente ronda...");
                scanner.nextLine();
            }
        }
        System.out.println("\n El combate ha concluido. Todos los hechizos cesaron...");
        System.out.print(" Presioná [ENTER] para ver el desenlace y las estadísticas finales...");
        scanner.nextLine();
        limpiarConsola();

        System.out.println("\n==============================================");
        System.out.println("               FIN DE LA BATALLA              ");
        System.out.println("==============================================");

        if (batallonMagos.tienePersonajesSaludables()) {
            System.out.println("¡Los magos han ganado la batalla!");
        } else {
            System.out.println("¡Los mortífagos han ganado la batalla!");
        }
        
        System.out.println();
        System.out.println("==============================================");
        System.out.println("             ESTADÍSTICAS FINALES             ");
        System.out.println("==============================================");
        
        //Estado final de los combatientes
        System.out.println("RECUENTO DE DAÑOS Y BAJAS:");
        mostrarEstadoVida("MAGOS", batallonMagos);
        System.out.println();
        mostrarEstadoVida("MORTÍFAGOS", batallonMortifagos);
        System.out.println("----------------------------------------------");
        
        // Imprimimos el bloque de los historiales que ya tenías
        System.out.println(" HISTORIAL DE HECHIZOS LANZADOS:");
        System.out.println(" -> MAGOS:");
        batallonMagos.mostrarEstadisticasBatallon();
        
        System.out.println(); 
        
        System.out.println(" -> MORTÍFAGOS:");
        batallonMortifagos.mostrarEstadisticasBatallon();
        
        System.out.println("==============================================");

        scanner.close();
    }

    private static void mostrarEstadoVida(String bando, Batallon b) {
        System.out.println(" -> " + bando + ":");
        
        for (Personaje p : b.getIntegrantes()) {
            if (p.estaVivo()) {
                System.out.print("    * " + p.getNombre() + " (" + p.getClass().getSimpleName() + "): " + p.getPuntosVida() + " HP");
                
                if (p.tieneAlgunEstado()) {
                	System.out.print("  Estados: [" + p.obtenerEstados() + "]");
                }
                
                System.out.println();
            } else {
                System.out.println("    * " + p.getNombre() + " (" + p.getClass().getSimpleName() + "): [ELIMINADO]");
            }
        }
    }
    
    private static void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 40; i++) System.out.println();
        }
    }
}