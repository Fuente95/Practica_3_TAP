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
    	Button botonMasOpcionesProducto = new Button("Más opciones");
    	botonMasOpcionesProducto.setWidth("260px");
    	
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
    	tablaDatos.addColumn(Productos::getPrecioFabricacionProducto).setCaption("Coste de fabricación");
    	tablaDatos.setSelectionMode(SelectionMode.SINGLE);
    	tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
    	tablaDatos.setWidth("550px");
    	
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
    								Integer.parseInt(campoPrecioProducto.getValue()),
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
    		}
    	});

    	//Selecion de producto por pantalla
    	tablaDatos.addItemClickListener(event -> {
    		productoSeleccionado = event.getItem();
        	labelNombreProducto.setValue(productoSeleccionado.getNombreProducto());
        	labelCantidadProducto.setValue(Integer.toString(productoSeleccionado.getCantidadProducto()));
        	labelFabricacionProducto.setValue(Double.toString(productoSeleccionado.getPrecioFabricacionProducto()));
    	});
    	
    	// Añadimos funcionalidad al botón de eliminar
    	botonEliminarProducto.addClickListener(e ->  {
    		Almacen.getInstance().getProductosAlmacen().remove(productoSeleccionado);
    		tablaDatos.setItems(Almacen.getInstance().getProductosAlmacen());
    		Page.getCurrent().reload();
    	});
    	
    	// Añadimos funcionalidad al botón de modificar
    	botonModificarProducto.addClickListener(e -> {
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
					Integer.parseInt(campoPrecioProducto.getValue()),
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
    	});
    	
    	//Ventana que se abre al clicar con los campos correspondientes
    	Window pestanaMasOpciones = new Window("Opciones disponibles");
    
    	HorizontalLayout horizontalLayout3 = new HorizontalLayout();
    	
    	Label prueba = new Label("Probando el funcionamiento de la nueva pestaña");
    	
    	// Añadimos funcionalidad al botón de más opciones
        botonMasOpcionesProducto.addClickListener(e -> {
            
            pestanaMasOpciones.setContent(horizontalLayout3);
    		
            horizontalLayout3.addComponent(prueba);
            
            // Indicamos que la pestaña salga en el centro
            pestanaMasOpciones.center();
            
            // Hacemos que aparezca la nueva pestaña
            addWindow(pestanaMasOpciones);
    	});
        	
		/*final VerticalLayout layout = new VerticalLayout();
        final Panel loginPanel = new Panel("Gestión de Inventario");
        layout.addComponent(loginPanel);
        loginPanel.setSizeFull();

        final FormLayout loginForm = new FormLayout();
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue()));
            loginForm.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });

        layout.addComponents(name, button);
        loginForm.addComponent(name);
        loginForm.addComponent(button);

        loginPanel.setContent(loginForm);
        layout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        layout.setSizeFull();
        setContent(layout);*/
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
