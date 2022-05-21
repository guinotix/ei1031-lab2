package juego;

public class Niveles {
	private int incrEsp;
	private int umbralNivel;
	
	public Niveles(final int incrEsp, final int umbral){
		this.incrEsp = incrEsp;
		umbralNivel = umbral;
	}
	
	public int actualizarNiveles(final TablaJugadores jugadores, final TablaEquipos equipos){
		int puntosEquipo;
		String mejorEquipo;
		byte encontrado;
		int codError = 0;  // valor que devolver (0 == sin incidencias)
		final int numJugadores = jugadores.obtenerNumJugadores();  // obtiene cantidad de jugadores de la tabla
		final int numEquipos = equipos.obtenerNumEquipos();  // obtiene cantidad de equipos de la tabla

		if (numJugadores == 0 || numEquipos == 0) {
            codError = 1;  // 1 == al menos una tabla SIN entradas
        } else {
			// busca mayor cantidad de puntos entre equipos
			final int maxPuntos = mayorCantidadPuntosEquipos(equipos)[0];
			// busca equipos con mayor cantidad de puntos (mejores equipos)
			for (int i=0; i<numEquipos; i++){
				puntosEquipo = equipos.obtenerPuntos(i);
				if (puntosEquipo == maxPuntos){
					mejorEquipo = equipos.obtenerNombre(i);
					// busca jugadores de mejor equipo
					encontrado = buscaJugadoresMejorEquipo(jugadores, mejorEquipo);
					if (encontrado == 0) {
                        codError = 2;  // 2 == al menos un mejor equipo SIN deportistas
                    }
				}
			}
		}		
		return codError;
	}

	private int[] mayorCantidadPuntosEquipos(final TablaEquipos equipos) {
		int[] sol = new int[2];
		for (int i=0; i<equipos.obtenerNumEquipos(); i++) {
			sol[1] = equipos.obtenerPuntos(i);  // puntos del equipo #i en la tabla
			if (sol[1] > sol[0]) {
			    sol[0] = sol[1];
            }
		}
		return sol;
	}

	private byte buscaJugadoresMejorEquipo(final TablaJugadores jugadores, final String mejorEquipo) {
		byte encontrado = 0;
		for (int j=0; j<jugadores.obtenerNumJugadores(); j++) {
			final String equipoJugador = jugadores.obtenerEquipo(j);
			if (mejorEquipo.equals(equipoJugador)) {
				encontrado = 1;
				final int nivelJugador = jugadores.obtenerNivel(j);
				final boolean esPreJugador = jugadores.obtenerEsPre(j);
				if (esPreJugador && nivelJugador < umbralNivel) {
                    jugadores.actualizarNivel(j, incrEsp);
				} else {
                    jugadores.actualizarNivel(j,1);
                }
			}
		}
		return encontrado;
	}

}
