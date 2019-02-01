package TAP.Practica_3.Web;

import java.util.ArrayList;
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
		
		// Creamos los elementos el formulario que tiene que rellenar
		// el usuario
		FormLayout organizacion1 = new FormLayout();
		FormLayout organizacion2 = new FormLayout();
		TextField campoNombreProducto = new TextField("Nombre del producto:");
		TextField campoCantidadProducto = new TextField("Cantidad del producto:");
		TextField campoPrecioProducto = new TextField("Precio del producto:");
		TextField campoCosteFabProducto = new TextField("Coste de fabricación del producto:");
		Label indicacionDatos = new Label("Datos de los productos");
		Label labelNombreProducto = new Label();
        Label labelCantidadProducto = new Label();
        Label labelPrecioProducto = new Label();
        Label labelFabricacionProducto = new Label();

		// Creamos las tablas qué usaremos para visualizar datos
    	Grid<Productos> tablaDatos = new Grid<Productos>();
    	Grid<Transacciones> tablaTransacciones = new Grid<Transacciones>();
    	
    	// Usaremos una organización en horizontal
    	HorizontalLayout horizontalLayout = new HorizontalLayout();	
    	
    	// Creamos el botón para añadir nuevos productos
    	Button botonAniadirProducto = new Button("Añadir el producto");
    	botonAniadirProducto.setWidth("260px");
    	
    	// Creamos el botón para modificar un producto
    	Button botonModificarProducto = new Button("Modificar el producto");
    	botonModificarProducto.setWidth("260px");
    	
    	// Creamos el botón para eliminar un producto
    	Button botonEliminarProducto = new Button("Eliminar el producto");
    	botonEliminarProducto.setWidth("260px");
    	
    	// Creamos el botón para ver más opciones de un producto
    	Button botonMasOpcionesProducto = new Button("Más opciones del producto");
    	botonMasOpcionesProducto.setWidth("260px");
    	
    	// Creamos el boton para cerrar una pestaña
    	Button cerrarE = new Button("Cerrar pestaña");
        cerrarE.setWidth("260px");
        
        // Creamos el boton para cerrar una pestaña
        Button cerrarM = new Button("Cerrar pestaña");
        cerrarM.setWidth("260px");
        
        // Creamos el boton para cerrar una pestaña
        Button cerrarA = new Button("Cerrar pestaña");
        cerrarA.setWidth("260px");
        
        // Creamos el boton para cerrar una pestaña
        Button cerrarS = new Button("Cerrar pestaña");
        cerrarS.setWidth("260px");
        
        // Creamos el boton para ceerar una pestaña
        Button cerrarR = new Button("Cerrar pestaña");
        cerrarR.setWidth("260px");

    	// Damos un formato a los distintos campos
    	campoNombreProducto.setWidth("260px");
    	campoCantidadProducto.setWidth("260px");
    	campoPrecioProducto.setWidth("260px");
    	campoCosteFabProducto.setWidth("260px");
    	
    	// Creamos unos checkbox para poder crear los productos
    	CheckBoxGroup<String> opcionesComponentes = new CheckBoxGroup<>("Selección de los componentes:");
    	Iterator<Productos> recorrerLista1 = Almacen.getInstance().getProductosAlmacen().iterator();
    	ArrayList<String> nombresComponentes = new ArrayList<String>() ;
		while (recorrerLista1.hasNext()) {
				nombresComponentes.add(recorrerLista1.next().getNombreProducto());
			}
    	opcionesComponentes.setItems(nombresComponentes);
    	
    	// Creamos el formulario
    	organizacion1.addComponents(campoNombreProducto, 
    			campoCantidadProducto,
    			campoPrecioProducto,
    			campoCosteFabProducto,
    			opcionesComponentes,
    			botonAniadirProducto,
    			botonEliminarProducto,
    			botonModificarProducto,
    			botonMasOpcionesProducto);
    	organizacion2.addComponents(tablaDatos);
    	
    	// Visualizamos los productos mediante la tabla
    	tablaDatos.addColumn(Productos::getNombreProducto).setCaption("Nombre del producto");
    	tablaDatos.addColumn(Productos::getCantidadProducto).setCaption("Cantidad existente");
    	tablaDatos.addColumn(Productos::getPrecioProducto).setCaption("Precio del producto");
    	tablaDatos.addColumn(Productos::getPrecioFabricacionProducto).setCaption("Coste de fabricación");
    	tablaDatos.setSelectionMode(SelectionMode.SINGLE);
    	tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
    	tablaDatos.setWidth("655px");
    	
    	// Añadimos el formulario a horizontalLayout
    	horizontalLayout.addComponents(indicacionDatos,organizacion1, organizacion2);
    	setContent(horizontalLayout);
    	
    	// Añadimos funcionalidad al botón de añadir el producto
    	botonAniadirProducto.addClickListener(e -> {
    		
    		// Comprobamos si existe el producto
    		if(productoSeleccionado == null) {
    			Boolean existe = null;
    			
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
    			VerticalLayout verticalLayout4 = new VerticalLayout();
        		Label error3 = new Label("Error, no debe debe seleccionar un producto");
        		
        		avisoError.center();
        		verticalLayout4.addComponents(error3, cerrarA);
        		avisoError.setContent(verticalLayout4);
        		addWindow(avisoError);
    		}
    	});
    	
    	// Añadimos funcionalidad al boton de eliminar la pestaña
    	cerrarA.addClickListener(e -> {
    		avisoError.close();
    	});

    	//Selecion de producto por pantalla
    	tablaDatos.addItemClickListener(event -> {
    		productoSeleccionado = event.getItem();
        	labelNombreProducto.setValue(productoSeleccionado.getNombreProducto());
        	labelCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        	labelPrecioProducto.setValue(Double.toString(productoSeleccionado.getPrecioProducto()));
        	labelFabricacionProducto.setValue(Double.toString(productoSeleccionado.getPrecioFabricacionProducto()));
    	});
    	
    	// Añadimos funcionalidad al botón de eliminar
    	botonEliminarProducto.addClickListener(e ->  {
    		if (productoSeleccionado != null) {
    			Almacen.getInstance().getProductosAlmacen().remove(productoSeleccionado);
    			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
    			Page.getCurrent().reload();
    		} else {
    			VerticalLayout verticalLayout2 = new VerticalLayout();
        		Label error1 = new Label("Error, debe seleccionar un producto");
        		
        		avisoError.center();
        		verticalLayout2.addComponents(error1, cerrarE);
        		avisoError.setContent(verticalLayout2);
        		addWindow(avisoError);
    		}
    	});
    	
    	// Añadimos funcionalidad al boton de eliminar la pestaña
    	cerrarE.addClickListener(e -> {
    		avisoError.close();
    	});
    	
    	// Añadimos funcionalidad al botón de modificar
    	botonModificarProducto.addClickListener(e -> {
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
    			VerticalLayout verticalLayout3 = new VerticalLayout();
        		Label error2 = new Label("Error, debe seleccionar un producto");
        		
        		avisoError.center();
        		verticalLayout3.addComponents(error2, cerrarM);
        		avisoError.setContent(verticalLayout3);
        		addWindow(avisoError);
    		}
    	});
    	
    	// Añadimos funcionalidad al boton de eliminar la pestaña
    	cerrarM.addClickListener(e -> {
    		avisoError.close();
    	});
    	
    	// Creamos algunas pestañas nuevas
    	Window pestanaMasOpciones = new Window("Opciones disponibles");
    	
    	// Creamos los layout para la nueva pestaña
    	HorizontalLayout horizontalLayout2 = new HorizontalLayout();
    	FormLayout organizacion3 = new FormLayout();
    	FormLayout organizacion4 = new FormLayout();
    	VerticalLayout verticalLayout = new VerticalLayout();
    	VerticalLayout verticalLayout2 = new VerticalLayout();
    	
    	// Creamos los elementos de la pestaña
    	TextField aniadirProducto = new TextField("Sumar cantidad al producto: ");
    	aniadirProducto.setWidth("260px");
    	
    	TextField restarProducto = new TextField("Restar cantidad al producto: ");
    	restarProducto.setWidth("260px");
    	
    	Label nombreProducto = new Label("Nombre del producto: ");
    	Label verNombreProducto = new Label("");
    	Label cantidadProducto = new Label("Cantidad del producto: ");
    	Label verCantidadProducto = new Label("");
    	Label precioProducto = new Label("Precio del producto: ");
    	Label verPrecioProducto = new Label("");
    	Label precioFabProducto = new Label("Precio de fabricación del producto: ");
    	Label verPrecioFabProducto = new Label("");
    	
      	// Creamos el boton para sumar cantidades
    	Button aniadirCantidad = new Button("Sumar cantidad");
    	aniadirCantidad.setWidth("260px");
    	
    	// Creamos el boton para restar cantidades
    	Button restarCantidad = new Button("Restar cantidad");
    	restarCantidad.setWidth("260px");
    	
    	// Creamos unos botones para cerrar las pestañas
        Button cerrar = new Button("Cerrar pestaña");
        cerrar.setWidth("260px");
        
        Button cerrarO = new Button("Cerrar pestaña");
        cerrarO.setWidth("260px");
        
        // Colocamos los elementos en la pestaña
        verticalLayout.addComponents(aniadirProducto,aniadirCantidad, restarProducto, restarCantidad, cerrar);
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
        		
        		VerticalLayout verticalLayout3 = new VerticalLayout();
        		Label error = new Label("Error, debe seleccionar un producto");
        		
        		avisoError.center();
        		verticalLayout3.addComponents(error, cerrarO);
        		avisoError.setContent(verticalLayout3);
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
        	Integer cantidadTotal = 0;
        	Integer cantidadSumada = 0;
        	
        	cantidadSumada = Integer.parseInt(aniadirProducto.getValue());
        	

        	if (cantidadSumada >= 0) {
        		cantidadTotal = productoSeleccionado.getCantidadProducto() + cantidadSumada;
        		
        		productoSeleccionado.setCantidadProducto(cantidadTotal);
        		verCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        		aniadirProducto.clear();
        		tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
        	} else {
        		VerticalLayout verticalLayout5 = new VerticalLayout();
        		Label error4 = new Label("Error, la cantidad a sumar debe ser mayor o igual que cero");
        		
        		avisoError.center();
        		verticalLayout5.addComponents(error4, cerrarS);
        		avisoError.setContent(verticalLayout5);
        		addWindow(avisoError);
        	}
        });
        
        // Añadimos funcionalidad al boton de cerrar
        cerrarS.addClickListener(e -> {
        	avisoError.close();
        });
        
        // Añadimos funcionalidad al boton de restar cantidad
        restarCantidad.addClickListener(e -> {
        	Integer cantidadTotal = 0;
        	Integer cantidadRestada = 0;
        	
        	cantidadRestada = Integer.parseInt(restarProducto.getValue());
        	
        	if (cantidadRestada >= 0) {
        		cantidadTotal = productoSeleccionado.getCantidadProducto() - cantidadRestada;
        		if (cantidadTotal > 0) {
        		
        			productoSeleccionado.setCantidadProducto(cantidadTotal);
        			verCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        			restarProducto.clear();
        			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
        		} else if (cantidadTotal <= 0){
        			cantidadTotal = 0;
        			productoSeleccionado.setCantidadProducto(cantidadTotal);
        			verCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        			Almacen.getInstance().getProductosAlmacen().remove(productoSeleccionado);
        			restarProducto.clear();
        			tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
        			pestanaMasOpciones.close();
        		}
        	} else {
        		VerticalLayout verticalLayout6 = new VerticalLayout();
        		Label error5 = new Label("Error, la cantidad a restar debe ser mayor o igual que cero");
        		
        		avisoError.center();
        		verticalLayout6.addComponents(error5, cerrarR);
        		avisoError.setContent(verticalLayout6);
        		addWindow(avisoError);
        	}
        });
        
        // Añadimos funcionalidad al boton de cerrar
        cerrarR.addClickListener(e -> {
        	avisoError.close();
        });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
