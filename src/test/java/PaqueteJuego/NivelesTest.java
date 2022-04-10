package PaqueteJuego;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
                Arguments.of("jugadores.txt", "equipos.txt", new Niveles(3, 50), 0)
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

        assertThat(codigo, is(esperado));
    }
}