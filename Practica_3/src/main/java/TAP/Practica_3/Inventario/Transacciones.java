package TAP.Practica_3.Inventario;

import java.util.Date;

public class Transacciones {

	// Declaramos algunas variables
	private Date fechaTransaccion;
	private String identifacionTransaccion;
	private Double cantidadTransaccion = 0.0;
	private Double costeTransaccion = 0.0;
	
	public Transacciones() {
		
	}
	// Creamos el constructor
	public Transacciones (Date fechaTransaccion, String identificacionTransaccion, 
			Double cantidadTransaccion, Double costeTransaccion) {
		super();
		this.fechaTransaccion = fechaTransaccion;
		this.identifacionTransaccion = identificacionTransaccion;
		this.cantidadTransaccion = cantidadTransaccion;
		this.costeTransaccion = costeTransaccion;
	}
	
	// Creamos los setters y getters
	// Getter del coste
	public Double getCosteTransaccion() {
		return costeTransaccion;
	}
	
	// Setter del coste
	public void setCosteTransaccion(Double costeTransaccion) {
		this.costeTransaccion = costeTransaccion;
	}
	
	// Getter de la cantidad
	public Double getCantidadTransaccion() {
		return cantidadTransaccion;
	}
	
	// Setter de la cantidad
	public void setCantidadTransaccion(Double cantidadTransaccion) {
		this.cantidadTransaccion = cantidadTransaccion;
	}
	
	// Getter de la fecha
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}
	
	// Setter de la fecha
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	
	// Getter de la identificación
	public String getIdentificacionTransaccion() {
		return identifacionTransaccion;
	}
	
	// Setter de la identificación
	public void setIdentificacionTransaccion(String identifacionTransaccion) {
		this.identifacionTransaccion = identifacionTransaccion;
	}
}
