package TAP.Practica_3.Inventario;

import java.util.ArrayList;

public class Productos {

	// Creamos las variables
	private String nombreProducto;
	private Integer cantidadProducto;
	private Double preciofabProducto;
	
	// Creamos un arraylist con los componentes de cada producto
	private ArrayList<Productos> componentesProducto;
	
	// Creamos el constructor
	public Productos(String nombreProducto, Integer cantidadProducto, Double preciofabProducto,
			ArrayList<Productos> componentesProducto) {
		super();
		this.nombreProducto = nombreProducto;
		this.cantidadProducto = cantidadProducto;
		this.preciofabProducto = preciofabProducto;
		this.componentesProducto = componentesProducto;
	}
	
	// Creamos los getters y setters necesarios
	public ArrayList <Productos> getComponentes() {
		return componentesProducto;
	}
	
	public void setComponentes (ArrayList <Productos> componentesProducto) {
		this.componentesProducto = componentesProducto;
	}
}
