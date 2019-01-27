package TAP.Practica_3.Inventario;

import java.util.ArrayList;

public class Productos {

	// Creamos las variables necesarias
	private String nombreProducto;
	private Integer cantidadProducto;
	private Double preciofabProducto;
	
	// Creamos un arraylist con los componentes de cada producto
	private ArrayList<Productos> componentesProducto;
	
	public Productos() {
		
	}
	
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
	// Getter del arraylist de los componentes del producto
	public ArrayList <Productos> getComponentes() {
		return componentesProducto;
	}
	
	// Setter del arraylist de los componentes del producto
	public void setComponentes (ArrayList <Productos> componentesProducto) {
		this.componentesProducto = componentesProducto;
	}
	
	// Getter del nombre del producto
	public String getNombreProducto () {
		return nombreProducto;
	}
	
	// Setter del nombre del producto
	public void setNombreProducto (String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
	// Getter de la cantidad de producto
	public Integer getCantidadProducto () {
		return cantidadProducto;
	}
	
	// Setter de la cantidad de producto
	public void setCantidadProducto (Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	
	// Getter del precio de fabricación
	public Double getPrecioFabricacionProducto () {
		return preciofabProducto;
	}
	
	// Setter del precio de fabricación
	public void setPrecioFabricacionProducto (Double preciofabProducto) {
		this.preciofabProducto = preciofabProducto;
	}
}
