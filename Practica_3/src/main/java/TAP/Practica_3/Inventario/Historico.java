package TAP.Practica_3.Inventario;

import java.util.ArrayList;

public class Historico {

	// Creamos algunas variables
	private static Historico historicoTransacciones;
	
	// Creamos un ArrayList con las transacciones realizadas
	private ArrayList<Transacciones> historico = null;
	
	// Creamos el constructor
	private Historico() {
		historico = new ArrayList<Transacciones>();
	}

	// Creamos la instancia
	public static Historico getInstance() {
		if (historicoTransacciones == null) {
			historicoTransacciones = new Historico();
		}
		return historicoTransacciones;
	}
	
	// Creamos los getters y setters necesarios
	// Getter de las transacciones
	public ArrayList <Transacciones> getHistoricoTransacciones() {
		return historico;
	}
	
	// Setter de los productos que se encuentran en el almacen
	public void setHistoricoTransacciones(ArrayList<Transacciones> historico) {
		this.historico = historico;
	}	
}
