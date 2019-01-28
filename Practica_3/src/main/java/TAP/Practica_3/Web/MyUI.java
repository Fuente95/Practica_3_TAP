package TAP.Practica_3.Web;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
		FormLayout formularioDatos = new FormLayout();
		FormLayout formularioTransacciones = new FormLayout();
		TextField campoNombreProducto = new TextField("Nombre del producto:");
		TextField campoCantidadProducto = new TextField("Cantidad del producto:");
		TextField campoPrecioProducto = new TextField("Precio del producto:");
		TextField campoCosteFabProducto = new TextField("Coste de fabricación del producto:");
		
		final VerticalLayout layout = new VerticalLayout();
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
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
