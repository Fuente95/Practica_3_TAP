package TAP.Practica_3.Inventario;

import java.sql.Date;

public class Transacciones {

	// Declaramos algunas variables
	private Date fechaTransaccion;
	private Integer identifacionTransaccion;
	private Integer cantidadTransaccion;
	private Double costeTransaccion = 0.0;
	
	public Transacciones() {
		
	}
	// Creamos el constructor
	public Transacciones (Date fechaTransaccion, Integer identificacionTransaccion, 
			Integer cantidadTransaccion) {
		super();
		this.fechaTransaccion = fechaTransaccion;
		this.identifacionTransaccion = identificacionTransaccion;
		this.cantidadTransaccion = cantidadTransaccion;
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
	public Integer getCantidadTransaccion() {
		return cantidadTransaccion;
	}
	
	// Setter de la cantidad
	public void setCantidadTransaccion(Integer cantidadTransaccion) {
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
	public Integer getIdentificacionTransaccion() {
		return identifacionTransaccion;
	}
	
	// Setter de la identificación
	public void setIdentificacionTransaccion(Integer identifacionTransaccion) {
		this.identifacionTransaccion = identifacionTransaccion;
	}
}
