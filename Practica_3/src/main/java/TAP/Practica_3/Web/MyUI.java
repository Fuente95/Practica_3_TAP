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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import TAP.Practica_3.Inventario.Almacen;
import TAP.Practica_3.Inventario.Historico;
import TAP.Practica_3.Inventario.Predeterminado;
import TAP.Practica_3.Inventario.Productos;
import TAP.Practica_3.Inventario.Transacciones;
import TAP.Practica_3.Logica.CambiarDolares;
import TAP.Practica_3.Logica.CambiarEuros;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add  component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	// Creamos algunas variables 
	private Productos productoSeleccionado;
	private Transacciones transaccionSeleccionada;
	private Double costesfabProducto = 0.0;
	
	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		
		// Cargamos los productos que se encuentran por defecto 
		Predeterminado productosPre = new Predeterminado();
		productosPre.Predeterminado();
		
		// Creamos una nueva pestaña
		Window avisoError = new Window("Error");
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
    	
		// Creamos los labels que usaremos para mostrar datos o información
		Label indicacionDatos = new Label("Datos de los productos:");
		Label indicacionTransacciones = new Label("Datos de las transacciones:");
		Label labelNombreProducto = new Label();
        Label labelCantidadProducto = new Label();
        Label labelPrecioProducto = new Label();
        Label labelFabricacionProducto = new Label();
        Label errorSinProducto = new Label("Error, debe seleccionar un producto");
        Label errorConProducto = new Label("Error, no debe seleccionar un producto");
        Label errorSuma = new Label("Error, la cantidad a sumar debe ser mayor o igual que cero");
        Label errorResta = new Label("Error, la cantidad a restar debe ser mayor o igual que cero");
    	Label nombreProducto = new Label("Nombre del producto: ");
    	Label verNombreProducto = new Label("");
    	Label cantidadProducto = new Label("Cantidad del producto: ");
    	Label verCantidadProducto = new Label("");
    	Label precioProducto = new Label("Precio del producto: ");
    	Label verPrecioProducto = new Label("");
    	Label precioFabProducto = new Label("Precio de fabricación del producto: ");
    	Label verPrecioFabProducto = new Label("");
    	
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
    
    	// Creamos las tablas qué usaremos para visualizar datos
    	Grid<Productos> tablaDatos = new Grid<Productos>();
    	Grid<Transacciones> tablaTransacciones = new Grid<Transacciones>();
    	
      	// Creamos los botones necesarios para usar las funcionalidades
    	Button aniadirCantidad = new Button("Sumar cantidad");
    	Button restarCantidad = new Button("Restar cantidad");
    	Button botonAniadirProducto = new Button("Añadir el producto");
    	Button botonModificarProducto = new Button("Modificar el producto");
    	Button botonEliminarProducto = new Button("Eliminar el producto");
    	Button botonMasOpcionesProducto = new Button("Más opciones del producto");
    	Button aniadirIngreso = new Button("Ingresar cantidad en efectivo");
    	
    	// Creamos lo botones para cerrar las pestañas
        Button cerrar = new Button("Cerrar pestaña");
        Button cerrarO = new Button("Cerrar pestaña");	
    	Button cerrarE = new Button("Cerrar pestaña");
        Button cerrarM = new Button("Cerrar pestaña");
        Button cerrarA = new Button("Cerrar pestaña");
        Button cerrarS = new Button("Cerrar pestaña");
        Button cerrarR = new Button("Cerrar pestaña");
        
    	// Damos un formato a los distintos campos, botones, labels
    	campoNombreProducto.setWidth("260px");
    	campoCantidadProducto.setWidth("260px");
    	campoPrecioProducto.setWidth("260px");
    	campoCosteFabProducto.setWidth("260px");
    	campoIngreso.setWidth("260px");
    	campoAniadirProducto.setWidth("260px");
    	campoRestarProducto.setWidth("260px");
    	cerrarR.setWidth("420px");
    	cerrarS.setWidth("420px");
    	cerrarA.setWidth("260px");
    	cerrarM.setWidth("260px");
    	cerrarE.setWidth("260px");
        cerrarO.setWidth("260px");
        cerrar.setWidth("260px");
        aniadirCantidad.setWidth("260px");
        restarCantidad.setWidth("260px");
    	botonMasOpcionesProducto.setWidth("260px");
    	botonEliminarProducto.setWidth("260px");
    	botonModificarProducto.setWidth("260px");
    	botonAniadirProducto.setWidth("260px");
    	aniadirIngreso.setWidth("260px");
    	campoIdentificarTransaccion.setWidth("260px");
    	campoCosteTransaccion.setWidth("260px");
    	
    	// Creamos unos checkbox para poder crear los productos
    	CheckBoxGroup<String> opcionesComponentes = new CheckBoxGroup<>("Selección de los componentes:");
    	Iterator<Productos> recorrerLista1 = Almacen.getInstance().getProductosAlmacen().iterator();
    	ArrayList<String> nombresComponentes = new ArrayList<String>() ;
		while (recorrerLista1.hasNext()) {
				nombresComponentes.add(recorrerLista1.next().getNombreProducto());
			}
    	opcionesComponentes.setItems(nombresComponentes);
    	
    	// Creamos el formulario de datos
    	organizacion.addComponents(campoNombreProducto, 
    			campoCantidadProducto,
    			campoPrecioProducto,
    			campoCosteFabProducto,
    			opcionesComponentes,
    			botonAniadirProducto,
    			botonEliminarProducto,
    			botonModificarProducto,
    			botonMasOpcionesProducto);
    	organizacion2.addComponents(tablaDatos);
    	
    	// Creamos el formulario de transacciones
    	organizacion5.addComponents(campoIngreso, campoIdentificarTransaccion, 
    			campoCosteTransaccion, aniadirIngreso);
    	organizacion6.addComponents(tablaTransacciones);
    	
    	// Visualizamos los productos mediante la tablaDatos
    	tablaDatos.addColumn(Productos::getNombreProducto).setCaption("Nombre del producto");
    	tablaDatos.addColumn(Productos::getCantidadProducto).setCaption("Cantidad existente");
    	tablaDatos.addColumn(Productos::getPrecioProducto).setCaption("Precio del producto");
    	tablaDatos.addColumn(Productos::getPrecioFabricacionProducto).setCaption("Coste de fabricación");
    	tablaDatos.setSelectionMode(SelectionMode.SINGLE);
    	tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
    	tablaDatos.setWidth("755px");
    	tablaDatos.setHeight("460px");
    	
    	// Visualizamos los datos de las transacciones
    	tablaTransacciones.addColumn(Transacciones::getIdentificacionTransaccion).setCaption("Identificación de la transacción");
    	tablaTransacciones.addColumn(Transacciones::getCantidadTransaccion).setCaption("Efectivo traspasado");
    	tablaTransacciones.addColumn(Transacciones::getFechaTransaccion).setCaption("Fecha de la transacción");
    	tablaTransacciones.addColumn(Transacciones::getCosteTransaccion).setCaption("Coste de la transaccion");
    	tablaTransacciones.setWidth("760px");
    	
    	// Añadimos el formulario a horizontalLayout
    	horizontalLayout.addComponents(indicacionDatos,organizacion, organizacion2);
    	horizontalLayout1.addComponents(indicacionTransacciones,
    			organizacion5,
    			organizacion6);
    	organizacion1.addComponents(horizontalLayout, horizontalLayout1);
    	setContent(organizacion1);
    	
    	
    	/******* FUNCIONALIDAD DE LOS PRODUCTOS *********/
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
    				} else {
    					// Si no existe, lo añadimos a la lista
    					if (existe == null) {
    						// Cogemos los componentes
    						Set <String> eleccionComponentes = opcionesComponentes.getValue();
    						double precioEleccion = 0.0;
    						
    						// Creamos el array necesario
    						ArrayList<Productos> componentesProducto = new ArrayList<Productos>();
    						Iterator<Productos> recorrerLista3 = Almacen.getInstance().getProductosAlmacen().iterator();
    						
    						while(recorrerLista3.hasNext()) {
    							Productos siguienteComponente = recorrerLista3.next();
    							if(eleccionComponentes.contains(siguienteComponente.getNombreProducto())) {
    								componentesProducto.add(siguienteComponente);
    								precioEleccion = siguienteComponente.getPrecioFabricacionProducto();
    							}
    						}
    						
    						Double precioIntroducido = Double.parseDouble(campoCosteFabProducto.getValue());
    						Double precioFinalProducto = precioEleccion + precioIntroducido;
    						
    						// Añadimos el producto
    						Productos productoNuevo = new Productos(campoNombreProducto.getValue(),
    								Integer.parseInt(campoCantidadProducto.getValue()),
    								Double.parseDouble(campoPrecioProducto.getValue()),
    								precioFinalProducto,
    								componentesProducto);
    						Almacen.getInstance().getProductosAlmacen().add(productoNuevo);
    						
    						// Limpiamos los campos rellenados
    						campoNombreProducto.clear();
    						campoPrecioProducto.clear();
    						campoCantidadProducto.clear();
    						campoCosteFabProducto.clear();
    						tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
    						Page.getCurrent().reload();
    					}
    				}
    			} 
    		} else if (productoSeleccionado != null){
    			// Creamos la pestaña indicando el error
        		avisoError.center();
        		verticalLayout3.addComponents(errorConProducto, cerrarA);
        		avisoError.setContent(verticalLayout3);
        		addWindow(avisoError);
    		}
    	});
    	
    	// Añadimos funcionalidad al boton de eliminar la pestaña
    	cerrarA.addClickListener(e -> {
    		avisoError.close();
    	});

    	//Selecion de producto por pantalla de la tabla datos
    	tablaDatos.addItemClickListener(event -> {
    		productoSeleccionado = event.getItem();
        	labelNombreProducto.setValue(productoSeleccionado.getNombreProducto());
        	labelCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        	labelPrecioProducto.setValue(Double.toString(productoSeleccionado.getPrecioProducto()));
        	labelFabricacionProducto.setValue(Double.toString(productoSeleccionado.getPrecioFabricacionProducto()));
    	});
    	
    	// Añadimos funcionalidad al botón de eliminar
    	botonEliminarProducto.addClickListener(e ->  {
    		// Comprobamos si se ha escogido un producto
    		if (productoSeleccionado != null) {
    			// Se elimina el producto seleccionado
    			Almacen.getInstance().getProductosAlmacen().remove(productoSeleccionado);
    			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
    			Page.getCurrent().reload();
    		} else {
    			// Creamos una pestaña indicando el error
        		avisoError.center();
        		verticalLayout4.addComponents(errorSinProducto, cerrarE);
        		avisoError.setContent(verticalLayout4);
        		addWindow(avisoError);
    		}
    	});
    	
    	// Añadimos funcionalidad al boton de eliminar la pestaña
    	cerrarE.addClickListener(e -> {
    		avisoError.close();
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
				
				Double precioIntroducido1 = Double.parseDouble(campoCosteFabProducto.getValue());
				Double precioFinalProducto1 = precioEleccion1 + precioIntroducido1;
				
				// Añadimos el producto
				Almacen.getInstance().getProductosAlmacen().remove(productoSeleccionado);
				Productos productoNuevo1 = new Productos(campoNombreProducto.getValue(),
						Integer.parseInt(campoCantidadProducto.getValue()),
						Double.parseDouble(campoPrecioProducto.getValue()),
						precioFinalProducto1,
						componentesProducto1);
				Almacen.getInstance().getProductosAlmacen().add(productoNuevo1);
				
				// Limpiamos los campos rellenados
				productoSeleccionado = null;
				campoNombreProducto.clear();
				campoPrecioProducto.clear();
				campoCantidadProducto.clear();
				campoCosteFabProducto.clear();
				tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
				Page.getCurrent().reload();
    		} else {
    			// Creamos una pestaña indicando el error
        		avisoError.center();
        		verticalLayout5.addComponents(errorSinProducto, cerrarM);
        		avisoError.setContent(verticalLayout5);
        		addWindow(avisoError);
    		}
    	});
    	
    	// Añadimos funcionalidad al boton de eliminar la pestaña
    	cerrarM.addClickListener(e -> {
    		avisoError.close();
    	});

        // Colocamos los elementos en la pestaña
        verticalLayout.addComponents(campoAniadirProducto,aniadirCantidad, 
        		campoRestarProducto, restarCantidad, cerrar);
    	verticalLayout2.addComponents(nombreProducto, verNombreProducto,
    			cantidadProducto,verCantidadProducto,
    			precioProducto, verPrecioProducto,
    			precioFabProducto, verPrecioFabProducto);
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
        		
        		verNombreProducto.setValue(productoSeleccionado.getNombreProducto());
        		verCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        		verPrecioProducto.setValue(Double.toString(productoSeleccionado.getPrecioProducto()));
        		verPrecioFabProducto.setValue(Double.toString(productoSeleccionado.getPrecioFabricacionProducto()));
        	} else {
        		// Creamos una pestaña indicando el error
        		avisoError.center();
        		verticalLayout6.addComponents(errorSinProducto, cerrarO);
        		avisoError.setContent(verticalLayout6);
        		addWindow(avisoError);
        	}
    	});
        
        // Añador funcionalidad al boton de cerrar la pestaña
        cerrarO.addClickListener(e -> {
        	avisoError.close();
        });
        
        // Añadimos funcionalidad al boton de cerrar la pestaña
        cerrar.addClickListener(e -> {
        	pestanaMasOpciones.close();
        	Page.getCurrent().reload();
        });
        
        // Añadimos funcionalidad al boton de sumar cantidades
        aniadirCantidad.addClickListener(e -> {
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
        		verCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        		campoAniadirProducto.clear();
        		
        		// Actualizamos los datos en la tabla
        		tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
        	} else {
        		// Creamos una pestaña indicando el error
        		avisoError.center();
        		verticalLayout7.addComponents(errorSuma, cerrarS);
        		avisoError.setContent(verticalLayout7);
        		addWindow(avisoError);
        	}
        });
        
        // Añadimos funcionalidad al boton de cerrar
        cerrarS.addClickListener(e -> {
        	avisoError.close();
        });
        
        // Añadimos funcionalidad al boton de restar cantidad
        restarCantidad.addClickListener(e -> {
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
        			verCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        			campoRestarProducto.clear();
        			
        			// Actualizamos la tabla de datos
        			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
        			
        		// Si el resultado es menor o igual que 0
        		} else if (cantidadTotal <= 0){
        			
        			// Como no existen cantidades negativas, lo dejamos en 0
        			cantidadTotal = 0;
        			
        			// Establecemos la cantidad a 0
        			productoSeleccionado.setCantidadProducto(cantidadTotal);
        			
        			// Actualizamos el valor para visualizar los datos
        			verCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        			
        			// Eliminados el producto de la lista al no existir cantidades suficientes
        			Almacen.getInstance().getProductosAlmacen().remove(productoSeleccionado);
        			campoRestarProducto.clear();
        			
        			// Actualizamos la tabla de los datos
        			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
        			
        			// Actualizamos la página
        			pestanaMasOpciones.close();
        		}
        	} else {    		
        		// Creamos una pestaña indicando el error
        		avisoError.center();
        		verticalLayout8.addComponents(errorResta, cerrarR);
        		avisoError.setContent(verticalLayout8);
        		addWindow(avisoError);
        	}
        });
        
        // Añadimos funcionalidad al boton de cerrar
        cerrarR.addClickListener(e -> {
        	avisoError.close();
        });
        
        /******** FUNIONCALIDAD DE LAS TRANSACCIONES ********/
        aniadirIngreso.addClickListener(e -> {
        	
        	Double cantidadIngreso = 0.0;
        	Double costeTransaccion = 0.0;
        	String identificacion;
        	java.util.Date fechaTrans = new Date();
        	
        	cantidadIngreso = Double.parseDouble(campoIngreso.getValue());
        	costeTransaccion = Double.parseDouble(campoCosteTransaccion.getValue());
        	identificacion = campoIdentificarTransaccion.getValue();

	        Transacciones nuevaTransaccion = new Transacciones(fechaTrans,
	        		identificacion, 
	        		cantidadIngreso, 
	        		costeTransaccion);
	        	
	        Historico.getInstance().getHistoricoTransacciones().add(nuevaTransaccion);
	        tablaTransacciones.setItems(Historico.getInstance().getHistoricoTransacciones());
	        	
	        campoIngreso.clear();
	        campoCosteTransaccion.clear();
	        campoIdentificarTransaccion.clear();
        	
        });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
