package TAP.Practica_3.Inventario;

import java.util.ArrayList;

public class Almacen {

	// Creamos algunas variables
	private static Almacen productosAlmacen;
	
	// Creamos un ArrayList con los productos del inventario
	private ArrayList <Productos> almacen;
	
	// Creamos el constructor
	private Almacen() {
		almacen = new ArrayList<Productos>();
	}
	
	// Creamos la instancia
	public static Almacen getInstance() {
		if (productosAlmacen == null) {
			productosAlmacen = new Almacen();
		}
		return productosAlmacen;
	}
	
	// Creamos los getters y setters necesarios
	// Getter de los productos que se encuentran en el almacen
	public ArrayList <Productos> getProductosAlmacen() {
		return almacen;
	}

	// Setter de los productos que se encuentran en el almacen
	public void setProductosAlmacen(ArrayList<Productos> almacen) {
		this.almacen = almacen;
	}	
}
