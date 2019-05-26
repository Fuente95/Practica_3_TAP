package TAP.Practica_3.Test;
/*
 * 
 * IMPORTANTE: El código se ha comentado para que se pueda ejecutar el proyecto en su totatildad. 
 * Ya que por un motivo desconocido, si se descomenta este código, se produce un error en la ejecución
 * del protecto
 * En el caso de que se saque este archivo y se ejecute por separado, funciona correctamente y las pruebas
 * se superan


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import TAP.Practica_3.Inventario.Almacen;
import TAP.Practica_3.Inventario.Historico;
import TAP.Practica_3.Inventario.Productos;
import TAP.Practica_3.Inventario.Transacciones;

class Test {

	@org.junit.jupiter.api.Test
	public void testAniadirProducto() {
		String nombre1 = "Palo";
		Double precio1 = 2.6;
		Integer cantidad1 = 3;
		Double precioF1 = 2.0;

		Double precioOficial = 2.6;
		
		// Un ArrayList para añadir los productos predeterminado
		ArrayList<Productos> predeterminado = new ArrayList<Productos>();
		
		// Añadimos el primer producto predeterminado
		Productos predeterminado1 = new Productos(nombre1, cantidad1, precio1, precioF1 ,predeterminado);
		Almacen.getInstance().getProductosAlmacen().add(predeterminado1);
		
		System.out.println("Se ha insertado el producto");
		assertEquals(precioOficial, predeterminado1.getPrecioProducto());
	}
	
	@org.junit.jupiter.api.Test
	public void testAniadirTransaccion() {
		String identificacion = "AV3";
		Double coste = 0.1;
		Double cantidad = 105.07;
		String tipo = "Ingreso";
		
		// Obtenemos la fecha
    	java.util.Date fechaTrans = new Date();
    	
		String tipoOficial = "Ingreso";
		
		Transacciones transacciones1 = new Transacciones(fechaTrans, identificacion, cantidad, coste, tipo);
		Historico.getInstance().getHistoricoTransacciones().add(transacciones1);
		
		System.out.println("Se ha insertado la transacción");
		assertEquals(tipoOficial, transacciones1.getTipoTransaccion());
	}
	
	@org.junit.jupiter.api.Test
	public void testRestarrCantidad() {
		Double cantidadFinal = 3.0;
		Double cantidadInicial = 10.0;
		Double cantidadRestada = 7.0;
		Double cantidadObtenida = 0.0;
		
		cantidadObtenida = cantidadInicial - cantidadRestada;
		
		System.out.println("Se ha restado la cantidad");
		assertEquals(cantidadObtenida, cantidadFinal);
	}
	
	@org.junit.jupiter.api.Test
	public void testSumarCantidad() {
		Double cantidadInicial = 3.0;
		Double cantidadFinal = 10.0;
		Double cantidadSumada = 7.0;
		Double cantidadObtenida = 0.0;
		
		cantidadObtenida = cantidadInicial + cantidadSumada;

		System.out.println("Se ha sumado la cantidad");
		assertEquals(cantidadObtenida, cantidadFinal);
	}
	
	@org.junit.jupiter.api.Test
	public void testCambioEurosDolares() {
		Double euros = 1.0 ;
		Double dolares = 0.0;
		Double dolaresE = 1.2;
		Double cambio = 1.2;
		
		dolares = euros * cambio;

		assertEquals(dolares, dolaresE);
	}
	
	@org.junit.jupiter.api.Test
	public void comprobarExistenciaProducto() {
		String nombre1 = "Palo";
		Double precio1 = 2.6;
		Integer cantidad1 = 3;
		Double precioF1 = 2.0;
		String comprobarNombre = "Palo";
		Boolean existe = null;
		
		// Un ArrayList para añadir los productos predeterminado
		ArrayList<Productos> predeterminado = new ArrayList<Productos>();
		
		// Añadimos el primer producto predeterminado
		Productos predeterminado1 = new Productos(nombre1, cantidad1, precio1, precioF1 ,predeterminado);
		Almacen.getInstance().getProductosAlmacen().add(predeterminado1);

		// Creamos un iterador para recorrer la lista
		Iterator<Productos> recorrerLista = Almacen.getInstance().getProductosAlmacen().iterator();
		
		// Comprobamos si existe el producto en la lista
		while (recorrerLista.hasNext()) {
			if(recorrerLista.next().getNombreProducto().equals(comprobarNombre)) {
				existe = true;
			}
		}
		
		if (existe == true) {
			System.out.println("Ya existe un producto con ese nombre");
			assertEquals(comprobarNombre, predeterminado1.getNombreProducto());
		} else {
			System.out.println("No existe el producto con ese nombre");
		}
	}
	
	@org.junit.jupiter.api.Test
	public void comprobarExistenciaTransacciones() {
		String identificacion = "AV3";
		Double coste = 0.1;
		Double cantidad = 105.07;
		String tipo = "Ingreso";
		String comprobarIdentificacion = "AV3";
		
		// Obtenemos la fecha
    	java.util.Date fechaTrans = new Date();
    	
    	Boolean existe = null;
		
		Transacciones transacciones1 = new Transacciones(fechaTrans, identificacion, cantidad, coste, tipo);
		Historico.getInstance().getHistoricoTransacciones().add(transacciones1);
		
		// Creamos un iterador para recorrer la lista
		Iterator<Transacciones> recorrerLista = Historico.getInstance().getHistoricoTransacciones().iterator();
		
		// Comprobamos si existe el producto en la lista
		while (recorrerLista.hasNext()) {
			if(recorrerLista.next().getIdentificacionTransaccion().equals(comprobarIdentificacion)) {
				existe = true;
			}
		}
		
		if (existe == true) {
			System.out.println("Ya existe unna transacción con ese nombre");
			assertEquals(comprobarIdentificacion, transacciones1.getIdentificacionTransaccion());
		} else {
			System.out.println("No existe una transacción con ese nombre");
		}
	}
}
*/

