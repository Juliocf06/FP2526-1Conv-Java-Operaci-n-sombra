package fp.resiliencia.tipos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

// TODO: añade el resto de imports que necesites (LocalDateTime, PerfilSujeto,
// RegistroSensor, Checkers...)

public class FactoriaResiliencia {

	// TODO: define aquí una constante para cada uno de los separadores
	private final String sep_principal = "|";
	private final String sep_ubicacion = "#";
	

	/**
	 * Lee el fichero csv y devuelve la lista de registros que contiene.
	 * (Método dado: no hay que modificarlo.)
	 */
	public static List<RegistroClimatico> leeOperacionSombra(String rutaFichero) {
		List<RegistroClimatico> res = null;
		try {
			res = Files.lines(Path.of(rutaFichero))
					.map(FactoriaResiliencia::parseaRegistro)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Toma una cadena con el formato de una línea del fichero csv y devuelve un
	 * RegistroClimatico (o un RegistroSensor si la línea trae metadatos).
	 */
	public static RegistroClimatico parseaRegistro(String registroStr) {
		// TODO: implementar
		return null;
	}

	/**
	 * Toma una cadena con el formato distrito#barrio#punto_interes y devuelve la
	 * CoordenadaUrbana correspondiente.
	 */
	private static CoordenadaUrbana parseaCoordenadaUrbana(String celda) {
		// TODO: implementar
		return null;
	}

	/**
	 * Toma "abierto" o "cerrado" y devuelve true si el toldo está abierto y false
	 * si está cerrado.
	 */
	private static Boolean parseaToldo(String cad) {
		// TODO: implementar
		return null;
	}

}