package TAP.Practica_3.Logica;

public class CambiarDolares implements CambiarMoneda {

	public CambiarDolares() {
		
	}
	
	@Override
	public Double Cambio(Double cantidad) {
		// Cambiamos la moneda a Dólares
		cantidad = cantidad * 1.2;
		return cantidad;
	}
}
