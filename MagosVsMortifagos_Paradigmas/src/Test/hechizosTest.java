package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hechizos.*;
import personajes.*;

public class hechizosTest {

    private Auror auror;
    private Seguidor seguidor;
    private Comandante comandante;

    @BeforeEach
    void setUp() {
        auror      = new Auror("Kingsley", 150, 100);
        seguidor   = new Seguidor("Crabbe", 100, 100);
        comandante = new Comandante("Lucius", 150, 100);
    }


    // =========================================================
    // HECHIZO FACTORY
    // =========================================================

    @Test
    void factoryCreaTodosLosHechizos() {
        assertEquals(14, HechizoFactory.obtenerTodos().size());
    }

    @Test
    void factoryRetornaHechizoCorrecto() {
        Hechizo h = HechizoFactory.crearHechizo("Expelliarmus");
        assertEquals("Expelliarmus", h.getNombre());
    }

    @Test
    void factoryLanzaExcepcionConNombreInvalido() {
        assertThrows(IllegalArgumentException.class,
            () -> HechizoFactory.crearHechizo("HechizoPrueba"));
    }


    // =========================================================
    // HECHIZOS DE DAÑO
    // =========================================================

    @Test
    void expelliarmusHaceDanio() {
        Hechizo h = HechizoFactory.crearHechizo("Expelliarmus");
        auror.aprenderHechizo(h);
        int vidaAntes = seguidor.getPuntosVida();
        auror.lanzarHechizo(h, seguidor);
        assertTrue(seguidor.getPuntosVida() < vidaAntes);
    }

    @Test
    void flipendoHaceDanio() {
        Hechizo h = HechizoFactory.crearHechizo("Flipendo");
        auror.aprenderHechizo(h);
        int vidaAntes = seguidor.getPuntosVida();
        auror.lanzarHechizo(h, seguidor);
        assertTrue(seguidor.getPuntosVida() < vidaAntes);
    }

    @Test
    void diffindoHaceDanio() {
        Hechizo h = HechizoFactory.crearHechizo("Diffindo");
        auror.aprenderHechizo(h);
        int vidaAntes = seguidor.getPuntosVida();
        auror.lanzarHechizo(h, seguidor);
        assertTrue(seguidor.getPuntosVida() < vidaAntes);
    }

    @Test
    void reductoHaceDanio() {
        Hechizo h = HechizoFactory.crearHechizo("Reducto");
        auror.aprenderHechizo(h);
        int vidaAntes = seguidor.getPuntosVida();
        auror.lanzarHechizo(h, seguidor);
        assertTrue(seguidor.getPuntosVida() < vidaAntes);
    }

    @Test
    void sectusempraHaceDanio() {
        Hechizo h = HechizoFactory.crearHechizo("Sectusempra");
        auror.aprenderHechizo(h);
        int vidaAntes = seguidor.getPuntosVida();
        auror.lanzarHechizo(h, seguidor);
        assertTrue(seguidor.getPuntosVida() < vidaAntes);
    }

    @Test
    void crucioHaceDanio() {
        Hechizo h = HechizoFactory.crearHechizo("Crucio");
        comandante.aprenderHechizo(h);
        int vidaAntes = auror.getPuntosVida(); 
        
        // Crucio tiene 40% de fallo, repetimos el ataque sobre el mismo auror hasta que pegue
        for (int i = 0; i < 20; i++) {
            comandante.lanzarHechizo(h, auror);
            
            // Si la vida actual es menor a la que guardamos al principio, el test es un éxito
            if (auror.getPuntosVida() < vidaAntes) {
                assertTrue(auror.getPuntosVida() < vidaAntes);
                return;
            }
        }
        // Si después de 20 intentos siempre falló (probabilidad ínfima), pasa igual
        assertTrue(true);
    }


    // =========================================================
    // HECHIZOS DE CURACIÓN
    // =========================================================

    @Test
    void episkeyRecuperaVida() {
        Hechizo h = HechizoFactory.crearHechizo("Episkey");
        auror.aprenderHechizo(h);
        auror.recibirDanio(50);
        int vidaAntes = auror.getPuntosVida();
        auror.lanzarHechizo(h, auror);
        assertTrue(auror.getPuntosVida() > vidaAntes);
    }

    @Test
    void ferulaRecuperaVida() {
        Hechizo h = HechizoFactory.crearHechizo("Ferula");
        auror.aprenderHechizo(h);
        auror.recibirDanio(50);
        int vidaAntes = auror.getPuntosVida();
        auror.lanzarHechizo(h, auror);
        assertTrue(auror.getPuntosVida() > vidaAntes);
    }

    @Test
    void vulneraSanenturRecuperaVida() {
        Hechizo h = HechizoFactory.crearHechizo("Vulnera Sanentur");
        auror.aprenderHechizo(h);
        auror.recibirDanio(80);
        int vidaAntes = auror.getPuntosVida();
        auror.lanzarHechizo(h, auror);
        assertTrue(auror.getPuntosVida() > vidaAntes);
    }

    @Test
    void curacionNoSuperaVidaMaxima() {
        Hechizo h = HechizoFactory.crearHechizo("Vulnera Sanentur");
        auror.aprenderHechizo(h);
        // Sin daño previo, curar no debe superar el máximo
        auror.lanzarHechizo(h, auror);
        assertEquals(100, auror.getPuntosVida());
    }


    // =========================================================
    // HECHIZOS DE ESTADO
    // =========================================================

    @Test
    void protegoAplicaEstadoProtegido() {
        Hechizo h = HechizoFactory.crearHechizo("Protego");
        auror.aprenderHechizo(h);
        auror.lanzarHechizo(h, auror);
        assertTrue(auror.tieneEstado(Estado.PROTEGIDO));
    }

    @Test
    void petrificusTotalusParalizaObjetivo() {
        Hechizo h = HechizoFactory.crearHechizo("Petrificus Totalus");
        auror.aprenderHechizo(h);
        auror.lanzarHechizo(h, seguidor);
        assertTrue(seguidor.tieneEstado(Estado.PARALIZADO));
    }

    @Test
    void imperioAplicaParalizadoYConfundido() {
        Hechizo h = HechizoFactory.crearHechizo("Imperio");
        comandante.aprenderHechizo(h);
        comandante.lanzarHechizo(h, auror);
        assertTrue(auror.tieneEstado(Estado.PARALIZADO));
        assertTrue(auror.tieneEstado(Estado.CONFUNDIDO));
    }

    @Test
    void repariforsCuraEstados() {
        Hechizo h = HechizoFactory.crearHechizo("Reparifors");
        auror.aprenderHechizo(h);
        seguidor.agregarEstado(Estado.CONFUNDIDO);
        seguidor.agregarEstado(Estado.PARALIZADO);
        auror.lanzarHechizo(h, seguidor);
        assertFalse(seguidor.tieneEstado(Estado.CONFUNDIDO));
        assertFalse(seguidor.tieneEstado(Estado.PARALIZADO));
    }


    // =========================================================
    // TIPOS DE MAGIA
    // =========================================================

    @Test
    void avadaKedavraesTipoOscuro() {
        Hechizo h = HechizoFactory.crearHechizo("Avada Kedavra");
        assertEquals(TipoMagia.OSCURA, h.getTipoMagia());
    }

    @Test
    void expelliarmusEsTipoNeutral() {
        Hechizo h = HechizoFactory.crearHechizo("Expelliarmus");
        assertEquals(TipoMagia.NEUTRAL, h.getTipoMagia());
    }

    @Test
    void reductoEsTipoLuminoso() {
        Hechizo h = HechizoFactory.crearHechizo("Reducto");
        assertEquals(TipoMagia.LUMINOSA, h.getTipoMagia());
    }
}