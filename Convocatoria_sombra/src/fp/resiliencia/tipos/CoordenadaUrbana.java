package fp.resiliencia.tipos;
import fp.utiles.Checkers;

public record CoordenadaUrbana (String distrito, String barrio, String puntoInteres) implements Comparable<CoordenadaUrbana> {
	public CoordenadaUrbana {
		Checkers.checkNoNull(distrito, barrio, puntoInteres);
		Checkers.check("Se debe asegurar que ninguna propiedad sea nula, vacía, ni contenga únicamente\r\n"
				+ "espacios en blanco.", !distrito.isBlank() && !barrio.isBlank() && !puntoInteres.isBlank());
		
	}

	@Override
	public int compareTo(CoordenadaUrbana o) {
		int r = distrito.compareTo(o.distrito);
		if (r==0) {
			r = barrio.compareTo(o.barrio);
			if (r==0) {
				r = puntoInteres.compareTo(o.puntoInteres);
			}
		}
		return r;
	}
	
	
	
}
