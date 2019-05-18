package TAP.Practica_3.Inventario;

import java.util.ArrayList;

public class Predeterminado {

	public void  Predeterminado() {
		
		if (Almacen.getInstance().getProductosAlmacen().isEmpty()) {
			// Creamos algunos datos para añadir
			String nombre1 = "Palo";
			String nombre2 = "Acero";
			Double precio1 = (double) 2.6;
			Double precio2 = (double) 1.5;
			Integer cantidad1 = 3;
			Integer cantidad2 = 2;
			Double precioF1 = (double) 2;
			Double precioF2 = 0.5;
			
			// Un ArrayList para añadir los productos predeterminado
			ArrayList<Productos> predeterminado = new ArrayList<Productos>();
			
			// Añadimos el primer producto predeterminado
			Productos predeterminado1 = new Productos(nombre1, cantidad1, precio1, precioF1,
					predeterminado);
			Almacen.getInstance().getProductosAlmacen().add(predeterminado1);
			
			// Añadimos el segundo producto
			Productos predeterminado2 = new Productos(nombre2, cantidad2, precio2, precioF2,
					predeterminado);
			Almacen.getInstance().getProductosAlmacen().add(predeterminado2);
		}
	}
}
