/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import walkin.service.servMain;
import walkin.service.iServCountry;
import walkin.service.iServUser;

/**
 */
public class Main extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        /* initialize service layer. */
        Main.serviceLayer = new servMain();

        /* initialize main form and show it. */
        show(new formMain(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of Main
     */
    public static Main getApplication() {
        return Application.getInstance(Main.class);
    }

    /**
     * Application entry point.
     * @param args The command lne arguments.
     */
    public static void main(String[] args) {
        launch(Main.class, args);
    }

    /**
     * Get the Service layer for application.
     * @return The service layer.
     */
    public static walkin.service.iServMain getServiceLayer() {
        return serviceLayer;
    }

    /* service layer. */
    private static walkin.service.iServMain serviceLayer;
}
