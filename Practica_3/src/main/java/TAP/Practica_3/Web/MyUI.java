package TAP.Practica_3.Web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import TAP.Practica_3.Inventario.Almacen;
import TAP.Practica_3.Inventario.Historico;
import TAP.Practica_3.Inventario.Predeterminado;
import TAP.Practica_3.Inventario.Productos;
import TAP.Practica_3.Inventario.Transacciones;

@Theme("mytheme")
public class MyUI extends UI {
	
	// Creamos algunas variables 
	private Productos productoSeleccionado;
	private String divisa = "Euros";
	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		
		// Cargamos los productos que se encuentran por defecto 
		Predeterminado productosPre = new Predeterminado();
		productosPre.Predeterminado();
		
		// Indicamos el cambio entre dólares y euros
		// 1 Euro es un 1.2 Dólares
    	Double precioDolares = 1.2;
    	
        // Creamos una instancia al almacen de productos y a las transacciones
        Almacen almacen = Almacen.getInstance();
		
		// Creamos unas nuevas pestañas
		Window avisoError = new Window("Aviso");
		Window pestanaMasOpciones = new Window("Opciones disponibles");

		// Creamos los FormLayout necesarios
		FormLayout organizacion = new FormLayout();
		FormLayout organizacion1 = new FormLayout();
		FormLayout organizacion2 = new FormLayout();
		FormLayout organizacion3 = new FormLayout();
    	FormLayout organizacion4 = new FormLayout();
    	FormLayout organizacion5 = new FormLayout();
    	FormLayout organizacion6 = new FormLayout();
    	
		// Creamos los textfield necesarios para introducir datos
		TextField campoNombreProducto = new TextField("Nombre del producto:");
		TextField campoCantidadProducto = new TextField("Cantidad del producto:");
		TextField campoPrecioProducto = new TextField("Precio del producto:");
		TextField campoCosteFabProducto = new TextField("Coste de fabricación del producto:");
		TextField campoIngreso = new TextField("Cantidad a ingresar: ");
		TextField campoAniadirProducto = new TextField("Sumar cantidad al producto: ");
    	TextField campoRestarProducto = new TextField("Restar cantidad al producto: ");
    	TextField campoIdentificarTransaccion = new TextField("Identificación de la transacción: ");
    	TextField campoCosteTransaccion = new TextField("Coste de la transacción: ");
    	TextField campoDivisa = new TextField("Divisa actual: ");
    	TextField campoTipoTransacciones = new TextField("Tipo de transacción: ");
    	
    	// Introducimos los place holders en los campos
    	campoNombreProducto.setPlaceholder("Campo de texto");
    	campoCantidadProducto.setPlaceholder("Campo numérico");
    	campoPrecioProducto.setPlaceholder("Campo numérico");
    	campoCosteFabProducto.setPlaceholder("Campo numérico");
    	campoIngreso.setPlaceholder("Campo numérico");
    	campoAniadirProducto.setPlaceholder("Campo numérico");
    	campoRestarProducto.setPlaceholder("Campo numérico");
    	campoCosteTransaccion.setPlaceholder("Campo numérico");
    	campoIdentificarTransaccion.setPlaceholder("Campo alfanúmerico");
    	campoTipoTransacciones.setPlaceholder("Campo de texto");
    	
		// Creamos los labels que usaremos para mostrar datos o información
		Label labelIndicacionDatos = new Label("Datos de los productos:");
		Label labelIndicacionTransacciones = new Label("Datos de las transacciones:");
		Label labelNombreProducto = new Label();
        Label labelCantidadProducto = new Label();
        Label labelPrecioProducto = new Label();
        Label labelFabricacionProducto = new Label();
        Label labelerrorSinProducto = new Label("Error, debe seleccionar un producto");
        Label labelerrorConProducto = new Label("Error, no debe seleccionar un producto");
        Label labelerrorSuma = new Label("Error, la cantidad a sumar debe ser mayor o igual que cero");
        Label labelerrorResta = new Label("Error, la cantidad a restar debe ser mayor o igual que cero");
    	Label labelnombreProducto = new Label("Nombre del producto: ");
    	Label labelverNombreProducto = new Label("");
    	Label labelcantidadProducto = new Label("Cantidad del producto: ");
    	Label labelverCantidadProducto = new Label("");
    	Label labelprecioProducto = new Label("Precio del producto: ");
    	Label labelverPrecioProducto = new Label("");
    	Label labelprecioFabProducto = new Label("Coste de fabricación del producto: ");
    	Label labelverPrecioFabProducto = new Label("");
    	Label labelDeseleccion = new Label("Se ha deseleccionado el producto");
    	Label labelDeseleccion2 = new Label("Debe seleccionar un producto");
    	Label labelFaltaDatos = new Label("Error, existen datos sin completar");
    	Label labelProductoExistente = new Label("Producto ya existente");
    	Label labelNumerosNegativos = new Label("No puede haber números menores de cero");
    	Label labelTransaccionExistente = new Label("Transacción ya existente");
    	
        // Creamos los horizontallayout necesarios
    	HorizontalLayout horizontalLayout = new HorizontalLayout();
    	HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    	HorizontalLayout horizontalLayout2 = new HorizontalLayout();
    	
    	// Creamos los verticalLayout necesarios
    	VerticalLayout verticalLayout = new VerticalLayout();
    	VerticalLayout verticalLayout2 = new VerticalLayout();
    	VerticalLayout verticalLayout3 = new VerticalLayout();
    	VerticalLayout verticalLayout4 = new VerticalLayout();
    	VerticalLayout verticalLayout5 = new VerticalLayout();
    	VerticalLayout verticalLayout6 = new VerticalLayout();
    	VerticalLayout verticalLayout7 = new VerticalLayout();
    	VerticalLayout verticalLayout8 = new VerticalLayout();
    	VerticalLayout verticalLayout9 = new VerticalLayout();
    	VerticalLayout verticalLayout10 = new VerticalLayout();
    	VerticalLayout verticalLayout11 = new VerticalLayout();
    	VerticalLayout verticalLayout12 = new VerticalLayout();
    	VerticalLayout verticalLayout13 = new VerticalLayout();
    	
    	// Creamos las tablas qué usaremos para visualizar datos
    	Grid<Productos> tablaDatos = new Grid<Productos>();
    	Grid<Transacciones> tablaTransacciones = new Grid<Transacciones>();
    	
      	// Creamos los botones necesarios para usar las funcionalidades
    	Button botonAniadirCantidad = new Button("Sumar cantidad");
    	Button botonRestarCantidad = new Button("Restar cantidad");
    	Button botonAniadirProducto = new Button("Añadir el producto");
    	Button botonModificarProducto = new Button("Modificar el producto");
    	Button botonEliminarProducto = new Button("Eliminar el producto");
    	Button botonMasOpcionesProducto = new Button("Más opciones del producto");
    	Button botonAniadirIngreso = new Button("Insertar transacción");
    	Button botonCambiarDivisa = new Button("Cambiar entre Euros/Dólares");
    	Button botonDeseleccionar = new Button("Deseleccionar producto");
    	
    	// Creamos lo botones para cerrar las pestañas
        Button botonCerrar = new Button("Cerrar pestaña");
        Button botonCerrarP = new Button("Cerrar pestaña");
       
    	// Damos un formato a los distintos campos, botones, labels
    	campoNombreProducto.setWidth("260px");
    	campoCantidadProducto.setWidth("260px");
    	campoPrecioProducto.setWidth("260px");
    	campoCosteFabProducto.setWidth("260px");
    	campoIngreso.setWidth("260px");
    	campoAniadirProducto.setWidth("260px");
    	campoRestarProducto.setWidth("260px");
    	botonCerrarP.setWidth("260px");
        botonCerrar.setWidth("260px");
        botonAniadirCantidad.setWidth("260px");
        botonRestarCantidad.setWidth("260px");
    	botonMasOpcionesProducto.setWidth("260px");
    	botonEliminarProducto.setWidth("260px");
    	botonModificarProducto.setWidth("260px");
    	botonAniadirProducto.setWidth("260px");
    	botonAniadirIngreso.setWidth("260px");
    	botonCambiarDivisa.setWidth("260px");
    	campoIdentificarTransaccion.setWidth("260px");
    	campoCosteTransaccion.setWidth("260px");
    	campoDivisa.setWidth("260px");
    	campoTipoTransacciones.setWidth("260px");
    	botonDeseleccionar.setWidth("260px");
    	
    	// Indicamos que en este campo es solo de lectura
    	campoDivisa.setReadOnly(true);
    	campoDivisa.setValue(divisa);

    	// Creamos unos checkbox para poder crear los productos
    	CheckBoxGroup<String> opcionesComponentes = new CheckBoxGroup<>("Selección de los componentes:");
    	Iterator<Productos> recorrerLista1 = Almacen.getInstance().getProductosAlmacen().iterator();
    	ArrayList<String> nombresComponentes = new ArrayList<String>() ;
    	
		while (recorrerLista1.hasNext()) {
			nombresComponentes.add(recorrerLista1.next().getNombreProducto());
		}
    	opcionesComponentes.setItems(nombresComponentes);
    	
    	// Creamos el formulario de datos
    	organizacion.addComponents(campoNombreProducto, campoCantidadProducto,campoPrecioProducto, campoCosteFabProducto, opcionesComponentes,
    			campoDivisa, botonAniadirProducto, botonEliminarProducto, botonModificarProducto, botonMasOpcionesProducto, botonDeseleccionar,
    			botonCambiarDivisa);
    	organizacion2.addComponents(tablaDatos);
    	
    	// Creamos el formulario de transacciones
    	organizacion5.addComponents(campoIngreso, campoIdentificarTransaccion, campoCosteTransaccion, campoTipoTransacciones, botonAniadirIngreso);
    	organizacion6.addComponents(tablaTransacciones);
    	
    	// Visualizamos los productos mediante la tablaDatos
    	tablaDatos.addColumn(Productos::getNombreProducto).setCaption("Nombre del producto");
    	tablaDatos.addColumn(Productos::getCantidadProducto).setCaption("Cantidad existente");
    	tablaDatos.addColumn(Productos::getPrecioProducto).setCaption("Precio del producto");
    	tablaDatos.addColumn(Productos::getPrecioFabricacionProducto).setCaption("Coste de fabricación");

    	// Indicamos que solo se puede seleccionar un producto a la vez
    	tablaDatos.setSelectionMode(SelectionMode.SINGLE);
    	
    	// Indicamos que los datos de la tabla se encuentra en el array Productos
    	tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
    	
    	// Establecemos el tamaño de la tabla
    	tablaDatos.setWidth("760px");
    	tablaDatos.setHeight("623px");
    
    	// Visualizamos los datos de las transacciones
    	tablaTransacciones.addColumn(Transacciones::getIdentificacionTransaccion).setCaption("Identificación");
    	tablaTransacciones.addColumn(Transacciones::getTipoTransaccion).setCaption("Tipo");
    	tablaTransacciones.addColumn(Transacciones::getCantidadTransaccion).setCaption("Efectivo traspasado");
    	tablaTransacciones.addColumn(Transacciones::getFechaTransaccion).setCaption("Fecha");
    	tablaTransacciones.addColumn(Transacciones::getCosteTransaccion).setCaption("Coste");
    	tablaTransacciones.setItems(Historico.getInstance().getHistoricoTransacciones());;
    	
    	// Indicamos el tamaño de la tabla
    	tablaTransacciones.setWidth("760px");
    	
    	// Añadimos el formulario a horizontalLayout
    	horizontalLayout.addComponents(labelIndicacionDatos,organizacion, organizacion2);
    	horizontalLayout1.addComponents(labelIndicacionTransacciones, organizacion5,organizacion6);
    	organizacion1.addComponents(horizontalLayout, horizontalLayout1);
    	setContent(organizacion1);

    	
    	/*******************************************************************************/
    	/******************** FUNCIONALIDAD DE LOS PRODUCTOS ***************************/
    	/*******************************************************************************/
    	
    	// Añadimos funcionalidad al botón de deseleccionar el producto
    	botonDeseleccionar.addClickListener(e -> {
    		// Si no hay producto seleccionado, lo indicamos con una pestaña
    		if (productoSeleccionado == null) {
    			avisoError.center();
    			verticalLayout10.addComponents(labelDeseleccion2, botonCerrarP);
    			avisoError.setContent(verticalLayout10);
        		addWindow(avisoError);
        		
        		// Limpiamos los campos rellenados y las opciones de los componentes
				campoNombreProducto.clear();
				campoPrecioProducto.clear();
				campoCantidadProducto.clear();
				campoCosteFabProducto.clear();
				
				// Deseleccionamos los componentes
				opcionesComponentes.deselectAll();
				
        		// Indicamos que no se puede deseleccionar el producto
        		System.out.println("No se puede deseleccionar el producto...");
    		} else {
    			// Deseleccionamos todos los datos de la tabla
        		tablaDatos.deselectAll();
        		
        		// Indicamos que no se selecciona ningún producto
        		productoSeleccionado = null;
        		avisoError.center();
    			verticalLayout9.addComponents(labelDeseleccion, botonCerrarP);
    			avisoError.setContent(verticalLayout9);
        		addWindow(avisoError);
        		
        		// Limpiamos los campos rellenados y las opciones de los componentes
				campoNombreProducto.clear();
				campoPrecioProducto.clear();
				campoCantidadProducto.clear();
				campoCosteFabProducto.clear();
				
				// Deseleccionamos los componentes
				opcionesComponentes.deselectAll();
				
        		// Indicamos que se deselecciona el producto
        		System.out.println("Se ha deseleccionado el producto...");
    		}
    	});
    	
    	// Añadimos funcionalidad al botón de añadir el producto
    	botonAniadirProducto.addClickListener(e -> {
    		// Comprobamos si se ha seleccionado un producto en la tabla
    		if(productoSeleccionado == null) {
    			Boolean existe = null;
    			
    			// Creamos un iterador para recorrer la lista
    			Iterator<Productos> recorrerLista2 = Almacen.getInstance().getProductosAlmacen().iterator();
    			
    			// Comprobamos si existe el producto en la lista
    			while (recorrerLista2.hasNext()) {
    				if(recorrerLista2.next().getNombreProducto().equals(campoNombreProducto.getValue())) {
    					existe = true;

    					// Creamos la pestaña indicando el error
			    		avisoError.center();
			    		verticalLayout12.addComponents(labelProductoExistente, botonCerrarP);
			    		avisoError.setContent(verticalLayout12);
			    		addWindow(avisoError);
    				} 
    			}
    			
				// Si no existe, lo añadimos a la lista
				if (existe == null) {
					// Cogemos los componentes
					Set <String> eleccionComponentes = opcionesComponentes.getValue();
						
					// Indicmaos el precio inicial
					double precioEleccion = 0.0;
						
					// Creamos el array necesario
					ArrayList<Productos> componentesProducto = new ArrayList<Productos>();
					Iterator<Productos> recorrerLista3 = Almacen.getInstance().getProductosAlmacen().iterator();
					
					// Recorremos la lista con los datos
					while(recorrerLista3.hasNext()) {
						Productos siguienteComponente = recorrerLista3.next();
						if(eleccionComponentes.contains(siguienteComponente.getNombreProducto())) {
							componentesProducto.add(siguienteComponente);
							precioEleccion = siguienteComponente.getPrecioFabricacionProducto();
						}
					}
						
					// Comprobamos si los textfield estan vacios
					if (campoNombreProducto.getValue().isEmpty() || campoCantidadProducto.getValue().isEmpty() ||
						campoPrecioProducto.getValue().isEmpty() || campoCosteFabProducto.getValue().isEmpty()){
						
						// Indicamos que faltan datos por rellenar
						System.out.println("No se puede añadir el producto, ya que faltan datos...");
						
						// Creamos la pestaña indicando el error
			    		avisoError.center();
			    		verticalLayout11.addComponents(labelFaltaDatos, botonCerrarP);
			    		avisoError.setContent(verticalLayout11);
			    		addWindow(avisoError);
						
					} else {
						// Damos valores a algunas variables
						Double precioIntroducido = Double.parseDouble(campoCosteFabProducto.getValue());
						Double precioFinalProducto = precioEleccion + precioIntroducido;
												
						if(Double.parseDouble(campoPrecioProducto.getValue()) < 0 || Integer.parseInt(campoCantidadProducto.getValue()) < 0 ||
								Double.parseDouble(campoCosteFabProducto.getValue()) < 0) {
							
							// Creamos la pestaña indicando el error
				    		avisoError.center();
				    		verticalLayout11.addComponents(labelNumerosNegativos, botonCerrarP);
				    		avisoError.setContent(verticalLayout11);
				    		addWindow(avisoError);
						} else {
							// Añadimos el producto a la tabla
							Productos productoNuevo = new Productos(campoNombreProducto.getValue(),Integer.parseInt(campoCantidadProducto.getValue()),
									Double.parseDouble(campoPrecioProducto.getValue()), precioFinalProducto, componentesProducto);
							Almacen.getInstance().getProductosAlmacen().add(productoNuevo);
							
							// Limpiamos los campos rellenados y las opciones de los componentes
							campoNombreProducto.clear();
							campoPrecioProducto.clear();
							campoCantidadProducto.clear();
							campoCosteFabProducto.clear();
							
							// Deseleccionamos los componentes
							opcionesComponentes.deselectAll();
							
							// Recargamos la prágina
							Page.getCurrent().reload();
							
							// Actualizamos las tablas
		        			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
		        			tablaTransacciones.setItems(Historico.getInstance().getHistoricoTransacciones());
		        			
							// Indicamos el producto que se ha añadido
							System.out.println("El producto " + productoNuevo.getNombreProducto() + " se ha añadido al almacen");
						}
					}
				}
	    	} else if (productoSeleccionado != null) {
				// Creamos la pestaña indicando el error
	    		avisoError.center();
	    		verticalLayout3.addComponents(labelerrorConProducto, botonCerrarP);
	    		avisoError.setContent(verticalLayout3);
	    		addWindow(avisoError);
			}
		});

    	//Selecion de producto por pantalla de la tabla datos
    	tablaDatos.addItemClickListener(event -> {
    		productoSeleccionado = event.getItem();
        	labelNombreProducto.setValue(productoSeleccionado.getNombreProducto());
        	labelCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        	labelPrecioProducto.setValue(Double.toString(productoSeleccionado.getPrecioProducto()));
        	labelFabricacionProducto.setValue(Double.toString(productoSeleccionado.getPrecioFabricacionProducto()));
        	
        	// Ponemos los datos en los campos
        	campoNombreProducto.setValue(productoSeleccionado.getNombreProducto());
    		campoCantidadProducto.setValue(productoSeleccionado.getCantidadProducto().toString());
    		campoPrecioProducto.setValue(productoSeleccionado.getPrecioProducto().toString());
    		campoCosteFabProducto.setValue(productoSeleccionado.getPrecioFabricacionProducto().toString());
    	});

    	// Añadimos funcionalidad al botón de eliminar
    	botonEliminarProducto.addClickListener(e ->  {
    		// Comprobamos si se ha escogido un producto
    		if (productoSeleccionado != null) {
    			// Se elimina el producto seleccionado
    			Almacen.getInstance().getProductosAlmacen().remove(productoSeleccionado);
    			
    			// Recargamos la página
    			Page.getCurrent().reload();
    			
    			// Actualizamos las tablas
    			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
    			tablaTransacciones.setItems(Historico.getInstance().getHistoricoTransacciones());
    			
    			// Indicamos que se ha eliminado el producto
    			System.out.println("El producto se ha eliminado del almacen");
    			
    			// Deseleccionamos el producto
    			productoSeleccionado = null;
    			
    			
    		} else {
    			// Creamos una pestaña indicando el error
        		avisoError.center();
        		verticalLayout4.addComponents(labelerrorSinProducto, botonCerrarP);
        		avisoError.setContent(verticalLayout4);
        		addWindow(avisoError);
    		}
    		
    		// Si la divisa es euros, mantenemos el precio
    		if (divisa == "Euros") {
				campoDivisa.setValue(divisa);
				for (Productos productos : almacen.getProductosAlmacen()) {
					productos.setPrecioProducto(productos.getPrecioProducto());
					productos.setPrecioFabricacionProducto(productos.getPrecioFabricacionProducto());
				}
    		} else {
    			// Si es dólares, realizamos el cambio
    			divisa = "Dólares";
    			campoDivisa.setValue(divisa);
				for (Productos productos : almacen.getProductosAlmacen()) {
					productos.setPrecioProducto(productos.getPrecioProducto()/precioDolares);
					productos.setPrecioFabricacionProducto(productos.getPrecioFabricacionProducto()/precioDolares);
				}
    		}
    	});

    	// Añadimos funcionalidad al botón de modificar
    	botonModificarProducto.addClickListener(e -> {
    		// Comprobamos si se ha escogido un producto
    		if (productoSeleccionado != null) {
	    		Set <String> eleccionComponentes1 = opcionesComponentes.getValue();
				double precioEleccion1 = 0.0;
				
				// Creamos el array necesario
				ArrayList<Productos> componentesProducto1 = new ArrayList<Productos>();
				Iterator<Productos> recorrerLista4 = Almacen.getInstance().getProductosAlmacen().iterator();
				
				while(recorrerLista4.hasNext()) {
					Productos siguienteComponente1 = recorrerLista4.next();
					if(eleccionComponentes1.contains(siguienteComponente1.getNombreProducto())) {
						componentesProducto1.add(siguienteComponente1);
						precioEleccion1 = siguienteComponente1.getPrecioFabricacionProducto();
					}
				}

				// Comprobamos si los textfield estan vacios
				if (campoNombreProducto.getValue().isEmpty()|| campoCantidadProducto.getValue().isEmpty() ||
					campoPrecioProducto.getValue().isEmpty() || campoCosteFabProducto.getValue().isEmpty()){
					
					// Indicamos que faltan datos por rellenar
					System.out.println("No se puede añadir el producto, ya que faltan datos...");
					
					// Creamos la pestaña indicando el error
		    		avisoError.center();
		    		verticalLayout11.addComponents(labelFaltaDatos, botonCerrarP);
		    		avisoError.setContent(verticalLayout11);
		    		addWindow(avisoError);
		    		
				} else {
		    		// Declaramos algunas variables
					Double precioIntroducido1 = Double.parseDouble(campoCosteFabProducto.getValue());
					Double precioFinalProducto1 = precioEleccion1 + precioIntroducido1;

					// Añadimos el producto
					Almacen.getInstance().getProductosAlmacen().remove(productoSeleccionado);
					Productos productoNuevo1 = new Productos(campoNombreProducto.getValue(), Integer.parseInt(campoCantidadProducto.getValue()),
							Double.parseDouble(campoPrecioProducto.getValue()), precioFinalProducto1, componentesProducto1);
					Almacen.getInstance().getProductosAlmacen().add(productoNuevo1);
					
					// Limpiamos los campos rellenados e indicamos que no se ha escogido ningún producto
					productoSeleccionado = null;
					campoNombreProducto.clear();
					campoPrecioProducto.clear();
					campoCantidadProducto.clear();
					campoCosteFabProducto.clear();
					
					// Recargamos la página
					Page.getCurrent().reload();
					
					// Actualizamos las tablas
        			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
        			
					// Indicamos que se ha modificado el producto
					System.out.println("Se ha modificado el producto...");
		    	}
    		} else {
    			// Creamos una pestaña indicando el error
        		avisoError.center();
        		verticalLayout5.addComponents(labelerrorSinProducto, botonCerrarP);
        		avisoError.setContent(verticalLayout5);
        		addWindow(avisoError);
    		}
    	});

        // Colocamos los elementos en la pestaña
        verticalLayout.addComponents(campoAniadirProducto,botonAniadirCantidad, campoRestarProducto, botonRestarCantidad, botonCerrar);
    	verticalLayout2.addComponents(labelnombreProducto, labelverNombreProducto, labelcantidadProducto,labelverCantidadProducto,
    			labelprecioProducto, labelverPrecioProducto, labelprecioFabProducto, labelverPrecioFabProducto);
    	organizacion3.addComponents(verticalLayout2);
    	organizacion4.addComponents(verticalLayout);
    	
    	horizontalLayout2.addComponents(organizacion3, organizacion4);
    	pestanaMasOpciones.setContent(horizontalLayout2);
    	
        // Indicamos que la pestaña salga en el centro
        pestanaMasOpciones.center();
        
    	// Añadimos funcionalidad al botón de más opciones
        botonMasOpcionesProducto.addClickListener(e -> {
        	if(productoSeleccionado != null) {
        		// Hacemos que aparezca la nueva pestaña
        		addWindow(pestanaMasOpciones);
        		
        		labelverNombreProducto.setValue(productoSeleccionado.getNombreProducto());
        		labelverCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        		labelverPrecioProducto.setValue(Double.toString(productoSeleccionado.getPrecioProducto()));
        		labelverPrecioFabProducto.setValue(Double.toString(productoSeleccionado.getPrecioFabricacionProducto()));
        		
        		// Indicamos que se ha modificado el producto
        		System.out.println("Se ha modificado el producto...");
        	} else {
        		// Creamos una pestaña indicando el error
        		avisoError.center();
        		verticalLayout6.addComponents(labelerrorSinProducto, botonCerrarP);
        		avisoError.setContent(verticalLayout6);
        		addWindow(avisoError);
        	}
    	});
        
        // Añadimos funcionalidad al boton de cerrar la pestaña
        botonCerrar.addClickListener(e -> {
        	pestanaMasOpciones.close();
        	
        	// Ponemos los datos en los campos
        	campoNombreProducto.setValue(productoSeleccionado.getNombreProducto());
    		campoCantidadProducto.setValue(productoSeleccionado.getCantidadProducto().toString());
    		campoPrecioProducto.setValue(productoSeleccionado.getPrecioProducto().toString());
    		campoCosteFabProducto.setValue(productoSeleccionado.getPrecioFabricacionProducto().toString());
    		
        	// Indicamos que se ha cerrado la pestaña
    		System.out.println("Se ha cerrado la pestaña...");
        });
        
        // Añadimos funcionalidad al boton de sumar cantidades
        botonAniadirCantidad.addClickListener(e -> {
        	// Comprobamos si el textfield esta vacío
        	if (campoAniadirProducto.getValue().isEmpty()) {
        		System.out.println("No se puede realizar la operación ya que faltan datos...");
				
				// Creamos la pestaña indicando el error
	    		avisoError.center();
	    		verticalLayout11.addComponents(labelFaltaDatos, botonCerrarP);
	    		avisoError.setContent(verticalLayout11);
	    		addWindow(avisoError);
	    		
        	} else {
        		// Declaramos algunas variables
	        	Integer cantidadTotal = 0;
	        	Integer cantidadSumada = 0;
	        	
	        	// Obtenemos la cantidad que vamos a sumar
	        	cantidadSumada = Integer.parseInt(campoAniadirProducto.getValue());
	
	        	// Si el valor que vamos a añadir es 0 o mayor
	        	if (cantidadSumada >= 0) {
	        		// Calculamos la cantidad total del producto seleccionado
	        		cantidadTotal = productoSeleccionado.getCantidadProducto() + cantidadSumada;
	        		
	        		// Establecemos la nueva cantidad
	        		productoSeleccionado.setCantidadProducto(cantidadTotal);
	        		
	        		// Actualizamos el valor en la visualización de los datos
	        		labelverCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
	        		campoAniadirProducto.clear();
	        		
	        		// Actualizamos las tablas
        			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
        			tablaTransacciones.setItems(Historico.getInstance().getHistoricoTransacciones());
	
	        	} else {
	        		// Creamos una pestaña indicando el error
	        		avisoError.center();
	        		verticalLayout7.addComponents(labelerrorSuma, botonCerrarP);
	        		avisoError.setContent(verticalLayout7);
	        		addWindow(avisoError);
	        	}
        	}
        });
        
        // Añadimos funcionalidad al boton de restar cantidad
        botonRestarCantidad.addClickListener(e -> {
        	// Comprobamos si el textfield esta vacío
        	if (campoRestarProducto.getValue().isEmpty()) {
        		System.out.println("No se puede realizar la operación ya que faltan datos...");
				
				// Creamos la pestaña indicando el error
	    		avisoError.center();
	    		verticalLayout11.addComponents(labelFaltaDatos, botonCerrarP);
	    		avisoError.setContent(verticalLayout11);
	    		addWindow(avisoError);
	    		
        	} else {
	        	// Declaramos algunas variables
	        	Integer cantidadTotal = 0;
	        	Integer cantidadRestada = 0;
	        	
	        	// Obtenemos lo que vamos a restar
	        	cantidadRestada = Integer.parseInt(campoRestarProducto.getValue());
	        	
	        	// Si el valor es mayor o igual que 0
	        	if (cantidadRestada >= 0) {
	        		
	        		// Calculamos el resultado
	        		cantidadTotal = productoSeleccionado.getCantidadProducto() - cantidadRestada;
	        		
	        		// Si el resultado es mayor que 0
	        		if (cantidadTotal > 0) {
	        			// Establecemos la nueva cantidad del producto
	        			productoSeleccionado.setCantidadProducto(cantidadTotal);
	        			
	        			// Actualizamos el valor donde se ve los datos
	        			labelverCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
	        			campoRestarProducto.clear();
	        			
	        			// Actualizamos las tablas
	        			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
	        			tablaTransacciones.setItems(Historico.getInstance().getHistoricoTransacciones());
	        			
	        		// Si el resultado es menor o igual que 0
	        		} else if (cantidadTotal <= 0){
	        			
	        			// Como no existen cantidades negativas, lo dejamos en 0
	        			cantidadTotal = 0;
	        			
	        			// Establecemos la cantidad a 0
	        			productoSeleccionado.setCantidadProducto(cantidadTotal);
	        			
	        			// Actualizamos el valor para visualizar los datos
	        			labelverCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
	        			
	        			// Eliminados el producto de la lista al no existir cantidades suficientes
	        			Almacen.getInstance().getProductosAlmacen().remove(productoSeleccionado);
	        			campoRestarProducto.clear();
	        			
	        			// Recargamos la página actual
	        			Page.getCurrent().reload();
	        			
	        			// Actualizamos las tablas
	        			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
	        			tablaTransacciones.setItems(Historico.getInstance().getHistoricoTransacciones());
	        			
	        			// Actualizamos la página
	        			pestanaMasOpciones.close();
	        		}
	        	} else {    		
	        		// Creamos una pestaña indicando el error
	        		avisoError.center();
	        		verticalLayout8.addComponents(labelerrorResta, botonCerrarP);
	        		avisoError.setContent(verticalLayout8);
	        		addWindow(avisoError);
	        	}
        	}
        });

        // Añadimos funcionalidad al boton de cambiar divisa
        botonCambiarDivisa.addClickListener(e -> {
        	
        	// Si la divisa actual es euros
        	if (divisa == "Euros") {
        		
        		// Cambiamos la divisa a dólares, indicándolo por pantalla
        		divisa = "Dólares";
        		campoDivisa.setValue(divisa);
        		
        		// Recorremos la tabla cambiando los precios
        		for (Productos prod : almacen.getProductosAlmacen()) {
        			prod.setPrecioProducto(prod.getPrecioProducto()*precioDolares);
        			prod.setPrecioFabricacionProducto(prod.getPrecioFabricacionProducto()*precioDolares);
        		}
        		
        		// Si se ha seleccionado el producto, se aprecia en los campos el cambio
        		if (productoSeleccionado != null) {
        			// Ponemos los datos en los campos
	            	campoNombreProducto.setValue(productoSeleccionado.getNombreProducto());
	        		campoCantidadProducto.setValue(productoSeleccionado.getCantidadProducto().toString());
	        		campoPrecioProducto.setValue(productoSeleccionado.getPrecioProducto().toString());
	        		campoCosteFabProducto.setValue(productoSeleccionado.getPrecioFabricacionProducto().toString());
        		}
        		
        		// Indicamos que se ha cambiado a dólares
        		System.out.println("Se ha cambiado la divisa a dólares");
        		
        	// Si son dólares, cambiamos a euros y hacemos lo mismo
        	} else {
        		divisa = "Euros";
        		campoDivisa.setValue(divisa);
        		for (Productos prod : almacen.getProductosAlmacen()) {
        			prod.setPrecioProducto(prod.getPrecioProducto()/precioDolares);
        			prod.setPrecioFabricacionProducto(prod.getPrecioFabricacionProducto()/precioDolares);
        		}

        		// Si se ha seleccionado el producto, se aprecia en los campos el cambio
        		if (productoSeleccionado != null) {
            		// Ponemos los datos en los campos
                	campoNombreProducto.setValue(productoSeleccionado.getNombreProducto());
            		campoCantidadProducto.setValue(productoSeleccionado.getCantidadProducto().toString());
            		campoPrecioProducto.setValue(productoSeleccionado.getPrecioProducto().toString());
            		campoCosteFabProducto.setValue(productoSeleccionado.getPrecioFabricacionProducto().toString());
            		}
        		
        		// Indicamos que se ha cambiado a dólares
        		System.out.println("Se ha cambiado la divisa a euros");
        	}
        	
        	// Actualizamos la lista para ver los precios cambiados
        	tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
        	tablaTransacciones.setItems(Historico.getInstance().getHistoricoTransacciones());
        });
        
        // Añadimos funcionalidad al botón de cerrar la pestaña
    	botonCerrarP.addClickListener(e -> {
    		avisoError.close();
    		
    		// Indicamos que se ha cerrado la pestaña
    		System.out.println("Se ha cerrado la pestaña...");
    	});
    	
        
        /***********************************************************************************/
    	/******************** FUNCIONALIDAD DE LOS TRANSACCIONES ***************************/
    	/***********************************************************************************/
        
    	
        // Añadimos funcionalidad al boton de introducir la transacción
        botonAniadirIngreso.addClickListener(e -> {
        	
        	// Inicializamos algunas variables
        	Double cantidadIngreso = 0.0;
        	Double costeTransaccion = 0.0;
        	String identificacion;
        	String tipo;
        	Boolean existe1 = null;
        	
        	// Obtenemos la fecha
        	java.util.Date fechaTrans = new Date();
        	
        	// Creamos un iterador para recorrer la lista
			Iterator<Transacciones> recorrerLista5 = Historico.getInstance().getHistoricoTransacciones().iterator();
			
			// Comprobamos si existe el producto en la lista
			while (recorrerLista5.hasNext()) {
				if(recorrerLista5.next().getIdentificacionTransaccion().equals(campoIdentificarTransaccion.getValue())) {
					existe1 = true;

					// Creamos la pestaña indicando el error
		    		avisoError.center();
		    		verticalLayout13.addComponents(labelTransaccionExistente, botonCerrarP);
		    		avisoError.setContent(verticalLayout13);
		    		addWindow(avisoError);
				} 
			}
			
			if (existe1 == null) {
	        	// Comprobamos si los textfield estan vacios
				if (campoIngreso.getValue().isEmpty()|| campoCosteTransaccion.getValue().isEmpty() ||
						campoIdentificarTransaccion.getValue().isEmpty() || campoTipoTransacciones.getValue().isEmpty()){
					
					System.out.println("No se puede añadir la transacción, ya que faltan datos...");
					
					// Creamos la pestaña indicando el error
		    		avisoError.center();
		    		verticalLayout11.addComponents(labelFaltaDatos, botonCerrarP);
		    		avisoError.setContent(verticalLayout11);
		    		addWindow(avisoError);
		    		
				} else {
					// Damos valores a las variables creadas
		        	cantidadIngreso = Double.parseDouble(campoIngreso.getValue());
		        	costeTransaccion = Double.parseDouble(campoCosteTransaccion.getValue());
		        	identificacion = campoIdentificarTransaccion.getValue();
		        	tipo = campoTipoTransacciones.getValue();
	
		        	if(Double.parseDouble(campoIngreso.getValue()) < 0 || Double.parseDouble(campoCosteTransaccion.getValue()) < 0){
						
						// Creamos la pestaña indicando el error
			    		avisoError.center();
			    		verticalLayout11.addComponents(labelNumerosNegativos, botonCerrarP);
			    		avisoError.setContent(verticalLayout11);
			    		addWindow(avisoError);
		        	} else {
				    	// Insertamos la nueva transacción
				        Transacciones nuevaTransaccion = new Transacciones(fechaTrans, identificacion, cantidadIngreso, costeTransaccion, tipo);
				
				        // Añadimos la transacción al histórico y actualizamos la tabla
				        Historico.getInstance().getHistoricoTransacciones().add(nuevaTransaccion);
				        tablaTransacciones.setItems(Historico.getInstance().getHistoricoTransacciones());
				        	
				        // Vaciamos los campos
				        campoIngreso.clear();
				        campoCosteTransaccion.clear();
				        campoIdentificarTransaccion.clear();
				        campoTipoTransacciones.clear();
				        
				        // Indicamos que se ha insertado la transacción
				        System.out.println("La transacción " + nuevaTransaccion.getIdentificacionTransaccion() + " se ha añadido al histórico");
		        	}
				}
			}
        });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
