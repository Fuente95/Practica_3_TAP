package TAP.Practica_3.Interfaz;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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

    // Creación de las variables necesarias
	private Productos productoEscogido;
	private Transacciones transaccionRealizada;
	private Double preciofabProducto = 0.0;
	
	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		
		CambiarDolares eurosAdolares = new CambiarDolares(); 
		CambiarEuros dolaresAeuros = new CambiarEuros();
		
        final VerticalLayout layout = new VerticalLayout();
        final Panel loginPanel = new Panel("Panel de prueba");
        layout.addComponent(loginPanel);
        loginPanel.setSizeFull();
        
        final FormLayout loginForm = new FormLayout();
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            loginForm.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
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
