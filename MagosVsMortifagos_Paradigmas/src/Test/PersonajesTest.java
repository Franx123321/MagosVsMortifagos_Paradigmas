package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Combate.Batallon;
import hechizos.*;
import personajes.*;

public class PersonajesTest {

    private Auror auror;
    private Estudiante estudiante;
    private Profesor profesor;
    private Seguidor seguidor;
    private Comandante comandante;

    @BeforeEach
    void setUp() {
        auror      = new Auror("Kingsley", 150, 100);
        estudiante = new Estudiante("Harry", 60, 100);
        profesor   = new Profesor("Lupin", 110, 100);
        seguidor   = new Seguidor("Crabbe", 100, 100);
        comandante = new Comandante("Lucius", 150, 100);
    }


    // =========================================================
    // VIDA Y ESTADO BÁSICO
    // =========================================================

    @Test
    void personajeIniciaConVidaCorrecta() {
        assertEquals(100, auror.getPuntosVida());
    }

    @Test
    void personajeEstaVivoConVidaPositiva() {
        assertTrue(auror.estaVivo());
    }

    @Test
    void personajeNoEstaVivoConVidaCero() {
        auror.recibirDanio(10000);
        assertFalse(auror.estaVivo());
    }

    @Test
    void vidaNoBajaDeZero() {
        auror.recibirDanio(10000);
        assertEquals(0, auror.getPuntosVida());
    }

    @Test
    void personajePierdeVidaAlRecibirDanio() {
        int vidaInicial = seguidor.getPuntosVida();
        seguidor.recibirDanio(50);
        assertTrue(seguidor.getPuntosVida() < vidaInicial);
    }

    @Test
    void curacionNoSuperaVidaMaxima() {
        auror.recibirDanio(10);
        auror.curarse(10000);
        assertEquals(100, auror.getPuntosVida());
    }

    @Test
    void curacionRecuperaVida() {
        auror.recibirDanio(40);
        int vidaAntes = auror.getPuntosVida();
        auror.curarse(20);
        assertTrue(auror.getPuntosVida() > vidaAntes);
    }


    // =========================================================
    // ESTADOS
    // =========================================================

    @Test
    void agregarEstadoFunciona() {
        auror.agregarEstado(Estado.CONFUNDIDO, 1);
        assertTrue(auror.tieneEstado(Estado.CONFUNDIDO));
    }

    @Test
    void quitarEstadoFunciona() {
        auror.agregarEstado(Estado.CONFUNDIDO, 1);
        auror.quitarEstado(Estado.CONFUNDIDO);
        assertFalse(auror.tieneEstado(Estado.CONFUNDIDO));
    }

    @Test
    void agregarEstadoDuplicadoPisaElContador() {
        auror.agregarEstado(Estado.PARALIZADO, 1);
        auror.agregarEstado(Estado.PARALIZADO, 3);
        auror.quitarEstado(Estado.PARALIZADO);
        assertFalse(auror.tieneEstado(Estado.PARALIZADO));
    }
    
    @Test
    void estadoExpiraDespuesDeRondasCorrespondientes() {
        auror.agregarEstado(Estado.PARALIZADO, 2);
        auror.actualizarEstados(); // ronda 1 = queda en 1
        assertTrue(auror.tieneEstado(Estado.PARALIZADO));
        auror.actualizarEstados(); // ronda 2 = llega a 0, se elimina
        assertFalse(auror.tieneEstado(Estado.PARALIZADO));
    }

    @Test
    void estadoConDuracionUnaRondaExpiraAlActualizar() {
        auror.agregarEstado(Estado.CONFUNDIDO, 1);
        auror.actualizarEstados();
        assertFalse(auror.tieneEstado(Estado.CONFUNDIDO));
    }

    @Test
    void tieneAlgunEstadoRetornaFalseSinEstados() {
        assertFalse(auror.tieneAlgunEstado());
    }

    @Test
    void tieneAlgunEstadoRetornaVerdaderoConEstado() {
        auror.agregarEstado(Estado.CONFUNDIDO, 1);
        assertTrue(auror.tieneAlgunEstado());
    }

    @Test
    void protegoReduceDanioALaMitad() {
        Auror aurorSinProtego = new Auror("Test", 150, 100);
        auror.agregarEstado(Estado.PROTEGIDO, 1);
        auror.recibirDanio(50);
        aurorSinProtego.recibirDanio(50);
        assertTrue(auror.getPuntosVida() > aurorSinProtego.getPuntosVida());
    }

    @Test
    void protegoSeConsumeDespuesDeUsarse() {
        auror.agregarEstado(Estado.PROTEGIDO, 1);
        auror.recibirDanio(10);
        assertFalse(auror.tieneEstado(Estado.PROTEGIDO));
    }

    @Test
    void personajeParalizadoNoPuedeLanzarHechizo() {
        Hechizo expelliarmus = HechizoFactory.crearHechizo("Expelliarmus");
        auror.aprenderHechizo(expelliarmus);
        auror.agregarEstado(Estado.PARALIZADO, 1);
        int vidaEnemigo = seguidor.getPuntosVida();
        auror.lanzarHechizo(expelliarmus, seguidor);
        assertEquals(vidaEnemigo, seguidor.getPuntosVida());
    }

    @Test
    void personajePuedeTenerVariosEstadosALaVez() {
        auror.agregarEstado(Estado.CONFUNDIDO, 1);
        auror.agregarEstado(Estado.PARALIZADO, 1);
        assertTrue(auror.tieneEstado(Estado.CONFUNDIDO));
        assertTrue(auror.tieneEstado(Estado.PARALIZADO));
    }


    // =========================================================
    // APRENDIZAJE DE HECHIZOS
    // =========================================================

    @Test
    void magoNoPuedeAprenderHechizosOscuros() {
        Hechizo avada = HechizoFactory.crearHechizo("Avada Kedavra");
        auror.aprenderHechizo(avada);
        boolean loAprendio = auror.getHechizos().stream()
            .anyMatch(h -> h.getNombre().equals("Avada Kedavra"));
        assertFalse(loAprendio);
    }

    @Test
    void mortifagoNoPuedeAprenderHechizosLuminosos() {
        Hechizo reducto = HechizoFactory.crearHechizo("Reducto");
        seguidor.aprenderHechizo(reducto);
        boolean loAprendio = seguidor.getHechizos().stream()
            .anyMatch(h -> h.getNombre().equals("Reducto"));
        assertFalse(loAprendio);
    }

    @Test
    void personajeNoPuedeAprenderHechizoDeMayorNivel() {
        // Estudiante nivel 60, Petrificus Totalus requiere 70
        Hechizo petrificus = HechizoFactory.crearHechizo("Petrificus Totalus");
        estudiante.aprenderHechizo(petrificus);
        boolean loAprendio = estudiante.getHechizos().stream()
            .anyMatch(h -> h.getNombre().equals("Petrificus Totalus"));
        assertFalse(loAprendio);
    }

    @Test
    void personajePuedeAprenderHechizoDeNivelExacto() {
        // Estudiante nivel 60, Diffindo requiere exactamente 50 → puede aprenderlo
        Hechizo diffindo = HechizoFactory.crearHechizo("Diffindo");
        estudiante.aprenderHechizo(diffindo);
        boolean loAprendio = estudiante.getHechizos().stream()
            .anyMatch(h -> h.getNombre().equals("Diffindo"));
        assertTrue(loAprendio);
    }

    @Test
    void noSeAgreganHechizosDuplicados() {
        Hechizo exp1 = HechizoFactory.crearHechizo("Expelliarmus");
        Hechizo exp2 = HechizoFactory.crearHechizo("Expelliarmus");
        auror.aprenderHechizo(exp1);
        auror.aprenderHechizo(exp2);
        long cantidad = auror.getHechizos().stream()
            .filter(h -> h.getNombre().equals("Expelliarmus"))
            .count();
        assertEquals(1, cantidad);
    }

    @Test
    void aprenderHechizoNullLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> auror.aprenderHechizo(null));
    }

    @Test
    void lanzarHechizoNullLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> auror.lanzarHechizo(null, seguidor));
    }

    @Test
    void personajeNoLanzaHechizosQueNoConoce() {
        Hechizo avada = HechizoFactory.crearHechizo("Avada Kedavra");
        int vidaAntes = auror.getPuntosVida();
        seguidor.lanzarHechizo(avada, auror);
        assertEquals(vidaAntes, auror.getPuntosVida());
    }

    @Test
    void personajeMuertoNoPuedeLanzarHechizo() {
        Hechizo expelliarmus = HechizoFactory.crearHechizo("Expelliarmus");
        auror.aprenderHechizo(expelliarmus);
        auror.recibirDanio(10000);
        int vidaAntes = seguidor.getPuntosVida();
        auror.lanzarHechizo(expelliarmus, seguidor);
        assertEquals(vidaAntes, seguidor.getPuntosVida());
    }


    // =========================================================
    // MODIFICADORES Y POLIMORFISMO
    // =========================================================

    @Test
    void aurorHaceMasDanioQueEstudiante() {
        assertTrue(auror.modificadorDanio(50) > estudiante.modificadorDanio(50));
    }

    @Test
    void profesorCuraMasQueEstudiante() {
        assertTrue(profesor.modificadorCuracion(30) > estudiante.modificadorCuracion(30));
    }

    @Test
    void comandanteHaceMasDanioQueSeguidor() {
        assertTrue(comandante.modificadorDanio(50) > 50);
    }

    @Test
    void mortifagoHaceMasDanioQueMago() {
        assertTrue(seguidor.modificadorDanio(50) > estudiante.modificadorDanio(50));
    }

    @Test
    void magoCuraMejorQueMortifago() {
        assertTrue(auror.modificadorCuracion(30) > seguidor.modificadorCuracion(30));
    }

    @Test
    void venganzaSeActivaAlCaerUnAliado() {
        Seguidor aliado = new Seguidor("Aliado", 100, 100);
        Batallon batallon = new Batallon();
        batallon.agregarPersonaje(comandante);
        batallon.agregarPersonaje(aliado);

        aliado.recibirDanio(10000);
        batallon.notificarCaidaDeAliado(aliado);

        assertTrue(comandante.tieneEstado(Estado.VENGANZA));
    }

    @Test
    void venganzaNoSeConsumeConHechizoSinDanio() {
        Hechizo protego = HechizoFactory.crearHechizo("Protego");
        comandante.agregarEstado(Estado.VENGANZA, 1);

        comandante.despuesDeLanzarHechizo(protego);

        assertTrue(comandante.tieneEstado(Estado.VENGANZA));
    }

    @Test
    void venganzaSeConsumeConHechizoDeDanio() {
        Hechizo diffindo = HechizoFactory.crearHechizo("Diffindo");
        comandante.agregarEstado(Estado.VENGANZA, 1);

        comandante.despuesDeLanzarHechizo(diffindo);

        assertFalse(comandante.tieneEstado(Estado.VENGANZA));
    }

    @Test
    void venganzaPotenciaElDanioDelComandante() {
        int danioSinVenganza = comandante.modificadorDanio(50);
        comandante.agregarEstado(Estado.VENGANZA, 1);
        int danioConVenganza = comandante.modificadorDanio(50);

        assertTrue(danioConVenganza > danioSinVenganza);
    }
}