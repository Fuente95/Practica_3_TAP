package TAP.Practica_3.Logica;

public class CambiarEuros implements CambiarMoneda{

	public CambiarEuros() {
		
	}
	@Override
	public Double Cambio(Double cantidad) {
		// Cambiamos la moneda a Euros
		cantidad = cantidad / 1.2;
		return cantidad;
	}

}
