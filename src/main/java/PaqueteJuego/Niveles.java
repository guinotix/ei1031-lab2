package PaqueteJuego;

public class Niveles {
	private int incrementoEspecial;
	private int umbralNivel;
	
	public Niveles(int iE, int uN){
		incrementoEspecial = iE;
		umbralNivel = uN;
	}
	
	public int actualizarNiveles(TablaJugadores tJ, TablaEquipos tE){
		int puntosEquipo;
		String mejorEquipo;
		byte encontrado;
		int codError = 0;  // valor que devolver (0 == sin incidencias)
		int numJugadores = tJ.obtenerNumJugadores();  // obtiene cantidad de jugadores de la tabla
		int numEquipos = tE.obtenerNumEquipos();  // obtiene cantidad de equipos de la tabla

		if (numJugadores == 0 || numEquipos == 0)
			codError = 1;  // 1 == al menos una tabla SIN entradas
		else{
			// busca mayor cantidad de puntos entre equipos
			int maxPuntos = mayorCantidadPuntosEquipos(tE)[0];
			// busca equipos con mayor cantidad de puntos (mejores equipos)
			for (int i=0; i<numEquipos; i++){
				puntosEquipo = tE.obtenerPuntos(i);
				if (puntosEquipo == maxPuntos){
					mejorEquipo = tE.obtenerNombre(i);
					// busca jugadores de mejor equipo
					encontrado = buscaJugadoresMejorEquipo(tJ, mejorEquipo);
					if (encontrado == 0)
						codError = 2;  // 2 == al menos un mejor equipo SIN deportistas
				}
			}
		}		
		return codError;
	}

	private int[] mayorCantidadPuntosEquipos(TablaEquipos tE) {
		int[] sol = new int[2];
		for (int i=0; i<tE.obtenerNumEquipos(); i++) {
			sol[1] = tE.obtenerPuntos(i);  // puntos del equipo #i en la tabla
			if (sol[1] > sol[0]) sol[0] = sol[1];
		}
		return sol;
	}

	private byte buscaJugadoresMejorEquipo(TablaJugadores tJ, String mejorEquipo) {
		byte encontrado = 0;
		for (int j=0; j<tJ.obtenerNumJugadores(); j++) {
			String equipoJugador = tJ.obtenerEquipo(j);
			if (mejorEquipo.equals(equipoJugador)) {
				encontrado = 1;
				int nivelJugador = tJ.obtenerNivel(j);
				boolean esPreJugador = tJ.obtenerEsPre(j);
				if (esPreJugador && nivelJugador < umbralNivel) {
					tJ.actualizarNivel(j, incrementoEspecial);
				} else tJ.actualizarNivel(j,1);
			}
		}
		return encontrado;
	}

}
