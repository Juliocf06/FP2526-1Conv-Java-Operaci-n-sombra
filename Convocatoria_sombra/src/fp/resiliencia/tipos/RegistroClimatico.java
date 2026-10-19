package fp.resiliencia.tipos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class RegistroClimatico implements Comparable<RegistroClimatico>{
	private String id;
	private LocalDateTime fechaHora;
	private CoordenadaUrbana ubicacion;
	private PerfilSujeto perfil;
	private Double temperatura;
	private Double hidratacion;
	private Integer factorProteccion;
	
	public RegistroClimatico (String id, LocalDateTime fechaHora, CoordenadaUrbana ubicacion, PerfilSujeto perfil, Double temperatura, Double hidratacion, Integer factorProteccion) {
		Checkers.checkNoNull(id);
		Checkers.check("El ID no puede ser nulo ni estar vacío.", !id.isBlank());
		Checkers.check("La hidratación debe estar en el rango cerrado [0.0, 1.0].", 0.0<=hidratacion && hidratacion<=1.0);
		this.id = id;
		this.fechaHora = fechaHora;
		this.ubicacion = ubicacion;
		this.perfil = perfil;
		this.setTemperatura(temperatura);
		this.hidratacion = hidratacion;
		this.setFactorProteccion(factorProteccion);
		
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		Checkers.check("La temperatura en grados debe estar en el rango cerrado [-5.0, 55.0].", -5.0<=temperatura && temperatura<=55.0);
		this.temperatura = temperatura;
	}

	public Integer getFactorProteccion() {
		return factorProteccion;
	}

	public void setFactorProteccion(Integer factorProteccion) {
		Checkers.check("El factor de protección solar no puede ser un número negativo.", factorProteccion>=0);
		this.factorProteccion = factorProteccion;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public CoordenadaUrbana getUbicacion() {
		return ubicacion;
	}

	public PerfilSujeto getPerfil() {
		return perfil;
	}

	public Double getHidratacion() {
		return hidratacion;
	}
	
	public Double getIndiceSeveridad() {
		Double r = temperatura/hidratacion;
		if (factorProteccion<30) {
			r *= 1.3;
		}
		return r;
	}
	
	public Boolean getAlertaActiva() {
		Boolean res = false;
		if(perfil == PerfilSujeto.TRABAJADOR_EXPUESTO && temperatura>39.0 || temperatura>42.5) {
			res = true;
		}
		return res;
	}
	
	public List<MedidaCorrectora> getMedidasCorrectoras() {
		List<MedidaCorrectora> res = new ArrayList<>();
		if (temperatura>40.0) {
			res.add(MedidaCorrectora.DESPLIEGUE_TOLDOS);
			res.add(MedidaCorrectora.ALERTA_SMS);
		}
		else {
			res.add(MedidaCorrectora.MONITOREO_PASIVO);
		}
		return res;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroClimatico other = (RegistroClimatico) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "RegistroClimatico [id=" + id + ", fechaHora=" + fechaHora + ", ubicación=" + ubicacion + ", perfil="
				+ perfil + ", temperatura=" + temperatura + ", hidratación=" + hidratacion + ", factorProtección="
				+ factorProteccion + "]";
	}

	@Override
	public int compareTo(RegistroClimatico o) {
		int r = id.compareTo(o.id);
		return r;
	}

	
	
	
}
