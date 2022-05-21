package PaqueteJuego;

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

    static Stream<Arguments> datos() {
        return Stream.of(
                Arguments.of("jugadores.txt", "equipos.txt", new Niveles(3, 50), 0),

                // Tablas vacías
                Arguments.of("jugadores0.txt", "equipos.txt", new Niveles(3, 50), 1),
                Arguments.of("jugadores.txt", "equipos0.txt", new Niveles(3, 50), 1),
                Arguments.of("jugadores0.txt", "equipos0.txt", new Niveles(3, 50), 1),

                // Jugadores de un mismo equipo - codigo: 2
                Arguments.of("jugadores1.txt", "equipos1.txt", new Niveles(3, 50), 2),

                // Resto de casos
                Arguments.of("jugadores2.txt", "equipos2.txt", new Niveles(3, 100), 0),
                Arguments.of("jugadores3.txt", "equipos3.txt", new Niveles(3, 100), 0),
                Arguments.of("jugadores4.txt", "equipos4.txt", new Niveles(3, 100), 0),
                Arguments.of("jugadores2.txt", "equipos3.txt", new Niveles(3, 100), 0),
                Arguments.of("jugadores3.txt", "equipos4.txt", new Niveles(3, 100), 0),
                Arguments.of("jugadores4.txt", "equipos2.txt", new Niveles(3, 100), 0),
                Arguments.of("jugadores2.txt", "equipos4.txt", new Niveles(3, 100), 0),
                Arguments.of("jugadores3.txt", "equipos2.txt", new Niveles(3, 100), 0),
                Arguments.of("jugadores4.txt", "equipos3.txt", new Niveles(3, 100), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("datos")
    void actualizarNiveles(String jugadores, String equipos, Niveles nivel, int esperado) {

        int codigo = nivel.actualizarNiveles(
                new TablaJugadores(TablaJugadores.class.getResource("/" + jugadores).getPath()),
                new TablaEquipos(TablaEquipos.class.getResource("/" + equipos).getPath())
        );

        System.out.println("Codigo: " + codigo);
        System.out.println("Esperado: " + esperado);
        System.out.println("");

        assertThat(codigo, is(esperado));
    }
}