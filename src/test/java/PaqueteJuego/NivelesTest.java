package PaqueteJuego;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.stream.Stream;

@RunWith(JUnitPlatform.class)
@SelectPackages("PaqueteJuego")
class NivelesTest {

    private String tablaEquipos;
    private String tablaJugadores;
    private Niveles niveles;
    private int esperado;

    public NivelesTest(String tablaEquipos, String tablaJugadores, Niveles niveles, int esperado) {
        this.tablaEquipos = tablaEquipos;
        this.tablaJugadores = tablaJugadores;
        this.niveles = niveles;
        this.esperado = esperado;
    }

    @AfterEach
    public void limpia() {
        this.tablaEquipos = null;
        this.tablaJugadores = null;
        this.niveles = null;
        this.esperado = -1;
    }

    static Stream<Arguments> datos() {
        return Stream.of(
                Arguments.of("jugadores.txt", "equipos.txt", new Niveles(3, 50), 0)
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource("datos")
    void actualizarNiveles(String jugadores, String equipos, Niveles nivel, int esperado) {
        assertEquals(esperado, nivel.actualizarNiveles(
                new TablaJugadores(NivelesTest.class.getResource("/" + jugadores).getPath()),
                new TablaEquipos(NivelesTest.class.getResource("/" + equipos).getPath())
        ));
    }
}