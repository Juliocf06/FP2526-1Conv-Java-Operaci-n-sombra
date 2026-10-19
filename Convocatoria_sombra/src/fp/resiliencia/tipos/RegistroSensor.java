package fp.resiliencia.tipos;

import java.time.LocalDateTime;

import fp.utiles.Checkers;

public class RegistroSensor extends RegistroClimatico{
	private Integer bateria;
	private Boolean toldoDesplegado;
	
	public RegistroSensor(String id, LocalDateTime fechaHora, CoordenadaUrbana ubicacion, PerfilSujeto perfil, Double temperatura, Double hidratacion, Integer factorProteccion, Integer bateria, Boolean toldoDesplegado) {
		super(id, fechaHora, ubicacion, perfil, temperatura, hidratacion, factorProteccion);
		Checkers.checkNoNull(bateria);
		Checkers.check("La batería debe estar en el rango [0,100].", 0<=bateria && bateria<=100);
		this.bateria = bateria;
		this.setToldoDesplegado(toldoDesplegado);
	}

	public Boolean getToldoDesplegado() {
		return toldoDesplegado;
	}

	public void setToldoDesplegado(Boolean toldoDesplegado) {
		Checkers.checkNoNull(toldoDesplegado);
		this.toldoDesplegado = toldoDesplegado;
	}

	public Integer getBateria() {
		return bateria;
	}
	
	public Boolean getAlertaActiva() {
		Boolean res = false;
		if (!toldoDesplegado && getTemperatura()>40.0) {
			res = true;
		} else {
			if(getPerfil() == PerfilSujeto.TRABAJADOR_EXPUESTO && getTemperatura()>39.0 || getTemperatura()>42.5) {
				res = true;
			}
		}
		
		return res;
	}

	@Override
	public String toString() {
		return "RegistroSensor [getId()=" + getId() + ", getFechaHora()=" + getFechaHora() + ", getUbicacion()="
				+ getUbicacion() + ", getPerfil()=" + getPerfil() + ", getTemperatura()=" + getTemperatura()
				+ ", getHidratacion()=" + getHidratacion() + ", getFactorProteccion()=" + getFactorProteccion()
				+ ", bateria=" + bateria + ", toldoDesplegado=" + toldoDesplegado + "]";
	}

	

	
	
	
	

}
