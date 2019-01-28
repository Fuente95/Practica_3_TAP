package TAP.Practica_3.Inventario;

import java.util.ArrayList;

public class Predeterminado {

	public void Predeterminado() {
		
		if (Almacen.getInstance().getProductosAlmacen().isEmpty()) {
			// Creamos algunos datos para añadir
			String nombre1 = "Palo";
			String nombre2 = "Acero";
			Integer cantidad1 = 3;
			Integer cantidad2 = 2;
			Double precio1 = (double) 2;
			Double precio2 = 4.5;
			
			// Un ArrayList para añadir los productos predeterminado
			ArrayList<Productos> predeterminado = new ArrayList<Productos>();
			
			// Añadimos el primer producto predeterminado
			Productos predeterminado1 = new Productos(nombre1, cantidad1, precio1,
					predeterminado);
			Almacen.getInstance().getProductosAlmacen().add(predeterminado1);
			
			// Añadimos el segundo producto
			Productos predeterminado2 = new Productos(nombre2, cantidad2, precio2,
					predeterminado);
			Almacen.getInstance().getProductosAlmacen().add(predeterminado2);
		}
	}
}
