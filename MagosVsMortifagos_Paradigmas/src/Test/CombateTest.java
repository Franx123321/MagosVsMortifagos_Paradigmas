package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Combate.Batallon;
import hechizos.*;
import personajes.*;

public class CombateTest {

    private Auror auror;
    private Profesor profesor;
    private Seguidor seguidor;
    private Comandante comandante;
    private Batallon batallonMagos;
    private Batallon batallonMortifagos;

    @BeforeEach
    void setUp() {
        auror      = new Auror("Kingsley", 150, 100);
        profesor   = new Profesor("Lupin", 110, 100);
        seguidor   = new Seguidor("Crabbe", 100, 100);
        comandante = new Comandante("Lucius", 150, 100);

        batallonMagos      = new Batallon();
        batallonMortifagos = new Batallon();
    }


    // =========================================================
    // GESTIÓN DEL BATALLÓN
    // =========================================================

    @Test
    void batallonIniciaVacio() {
        assertFalse(batallonMagos.tienePersonajesSaludables());
    }

    @Test
    void batallonConPersonajeVivoEsSaludable() {
        batallonMagos.agregarPersonaje(auror);
        assertTrue(batallonMagos.tienePersonajesSaludables());
    }

    @Test
    void batallonConTodosEliminadosNoEsSaludable() {
        batallonMagos.agregarPersonaje(auror);
        auror.recibirDanio(10000);
        assertFalse(batallonMagos.tienePersonajesSaludables());
    }

    @Test
    void batallonNoAgregaPersonajeNull() {
        batallonMagos.agregarPersonaje(null);
        assertFalse(batallonMagos.tienePersonajesSaludables());
    }

    @Test
    void batallonConUnVivoYUnMuertoEsSaludable() {
        batallonMagos.agregarPersonaje(auror);
        batallonMagos.agregarPersonaje(profesor);
        auror.recibirDanio(10000);
        assertTrue(batallonMagos.tienePersonajesSaludables());
    }


    // =========================================================
    // COLECCIÓN: obtenerIntegrantesVivos (List)
    // =========================================================

    @Test
    void obtenerVivosDevuelveTodosConTodosVivos() {
        batallonMagos.agregarPersonaje(auror);
        batallonMagos.agregarPersonaje(profesor);
        assertEquals(2, batallonMagos.obtenerIntegrantesVivos().size());
    }

    @Test
    void obtenerVivosExcluyeMuertos() {
        batallonMagos.agregarPersonaje(auror);
        batallonMagos.agregarPersonaje(profesor);
        auror.recibirDanio(10000);
        assertEquals(1, batallonMagos.obtenerIntegrantesVivos().size());
        assertEquals("Lupin", batallonMagos.obtenerIntegrantesVivos().get(0).getNombre());
    }

    @Test
    void obtenerVivosDevuelveVacioConTodosMuertos() {
        batallonMagos.agregarPersonaje(auror);
        auror.recibirDanio(10000);
        assertTrue(batallonMagos.obtenerIntegrantesVivos().isEmpty());
    }

    @Test
    void getIntegrantesIncluyeMuertos() {
        batallonMagos.agregarPersonaje(auror);
        batallonMagos.agregarPersonaje(profesor);
        auror.recibirDanio(10000);
        // getIntegrantes devuelve todos, vivos y muertos
        assertEquals(2, batallonMagos.getIntegrantes().size());
    }


    // =========================================================
    // LÓGICA DE TURNO (Set de hechizos y Map de historial)
    // =========================================================

    
    @Test
    void ejecutarTurnoSinExcepcion() {
        Hechizo expelliarmus = HechizoFactory.crearHechizo("Expelliarmus");
        auror.aprenderHechizo(expelliarmus);
        
        comandante.aprenderHechizo(HechizoFactory.crearHechizo("Diffindo"));

        batallonMagos.agregarPersonaje(auror);
        batallonMortifagos.agregarPersonaje(comandante); // Usamos comandante acá

        assertDoesNotThrow(() -> batallonMagos.ejecutarTurno(batallonMortifagos));
    }

    @Test
    void turnoReduceVidaEnemigo() {
        Hechizo expelliarmus = HechizoFactory.crearHechizo("Expelliarmus");
        auror.aprenderHechizo(expelliarmus);

        batallonMagos.agregarPersonaje(auror);
        batallonMortifagos.agregarPersonaje(seguidor);

        int vidaAntes = seguidor.getPuntosVida();
        batallonMagos.ejecutarTurno(batallonMortifagos);
        assertTrue(seguidor.getPuntosVida() < vidaAntes);
    }

    @Test
    void personajeParalizadoNoPuedeLanzarEnTurno() {
        Hechizo expelliarmus = HechizoFactory.crearHechizo("Expelliarmus");
        auror.aprenderHechizo(expelliarmus);
        auror.agregarEstado(Estado.PARALIZADO);

        batallonMagos.agregarPersonaje(auror);
        batallonMortifagos.agregarPersonaje(seguidor);

        int vidaAntes = seguidor.getPuntosVida();
        batallonMagos.ejecutarTurno(batallonMortifagos);
        assertEquals(vidaAntes, seguidor.getPuntosVida());
    }

    @Test
    void personajeMuertoNoActuaEnTurno() {
        Hechizo expelliarmus = HechizoFactory.crearHechizo("Expelliarmus");
        auror.aprenderHechizo(expelliarmus);
        auror.recibirDanio(10000); // muere

        batallonMagos.agregarPersonaje(auror);
        batallonMortifagos.agregarPersonaje(seguidor);

        int vidaAntes = seguidor.getPuntosVida();
        batallonMagos.ejecutarTurno(batallonMortifagos);
        assertEquals(vidaAntes, seguidor.getPuntosVida());
    }


    // =========================================================
    // BATALLA COMPLETA
    // =========================================================

    @Test
    void batallaTerminaConUnSoloGanador() {
        batallonMagos.agregarPersonaje(PersonajeFactory.crearAuror("Kingsley"));
        batallonMortifagos.agregarPersonaje(PersonajeFactory.crearSeguidor("Crabbe"));

        int maxRondas = 500;
        int ronda = 0;

        while (batallonMagos.tienePersonajesSaludables()
            && batallonMortifagos.tienePersonajesSaludables()
            && ronda < maxRondas) {
            batallonMagos.ejecutarTurno(batallonMortifagos);
            if (batallonMortifagos.tienePersonajesSaludables()) {
                batallonMortifagos.ejecutarTurno(batallonMagos);
            }
            ronda++;
        }

        boolean magosGanaron      = batallonMagos.tienePersonajesSaludables();
        boolean mortifagosGanaron = batallonMortifagos.tienePersonajesSaludables();

        // Exactamente uno gana
        assertTrue(magosGanaron || mortifagosGanaron);
        assertFalse(magosGanaron && mortifagosGanaron);
    }

    @Test
    void batallaConVariosPersonajesTerminaCorrectamente() {
        batallonMagos.agregarPersonaje(PersonajeFactory.crearEstudiante("Harry"));
        batallonMagos.agregarPersonaje(PersonajeFactory.crearProfesor("Lupin"));
        batallonMagos.agregarPersonaje(PersonajeFactory.crearAuror("Kingsley"));

        batallonMortifagos.agregarPersonaje(PersonajeFactory.crearSeguidor("Crabbe"));
        batallonMortifagos.agregarPersonaje(PersonajeFactory.crearSeguidor("Goyle"));
        batallonMortifagos.agregarPersonaje(PersonajeFactory.crearComandante("Lucius"));

        int maxRondas = 500;
        int ronda = 0;

        while (batallonMagos.tienePersonajesSaludables()
            && batallonMortifagos.tienePersonajesSaludables()
            && ronda < maxRondas) {
            batallonMagos.ejecutarTurno(batallonMortifagos);
            if (batallonMortifagos.tienePersonajesSaludables()) {
                batallonMortifagos.ejecutarTurno(batallonMagos);
            }
            ronda++;
        }

        boolean magosGanaron      = batallonMagos.tienePersonajesSaludables();
        boolean mortifagosGanaron = batallonMortifagos.tienePersonajesSaludables();

        assertTrue(magosGanaron || mortifagosGanaron);
        assertFalse(magosGanaron && mortifagosGanaron);
    }

    @Test
    void batallaNoEsInfinita() {
        batallonMagos.agregarPersonaje(PersonajeFactory.crearAuror("Kingsley"));
        batallonMortifagos.agregarPersonaje(PersonajeFactory.crearComandante("Lucius"));

        int maxRondas = 500;
        int ronda = 0;

        while (batallonMagos.tienePersonajesSaludables()
            && batallonMortifagos.tienePersonajesSaludables()
            && ronda < maxRondas) {
            batallonMagos.ejecutarTurno(batallonMortifagos);
            if (batallonMortifagos.tienePersonajesSaludables()) {
                batallonMortifagos.ejecutarTurno(batallonMagos);
            }
            ronda++;
        }

        assertTrue(ronda < maxRondas, "La batalla no terminó en " + maxRondas + " rondas");
    }
}