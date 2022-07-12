package net.benfro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.scene.control.window.Window;
import net.benfro.commons.controls.knob.Knob;
import net.benfro.commons.midi.MIDIConstants;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.tbee.javafx.scene.layout.MigPane;

/**
 * First VFXWindows tutorial.
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) {

        // create the canvas where the windows will be added to
        Pane canvas = new Pane();
        canvas.getStyleClass().add("panel-primary");

        // create a scene that displays the canvas (resolution 600,600)
        Scene scene = new Scene(canvas, 600, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        // create a window with title "My Window"
        Window pluginWindow = new Window("Plugin");
        //pluginWindow.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        pluginWindow.getContentPane().getStyleClass().addAll("panel","panel-primary");
        //pluginWindow.setTitleBarStyleClass("h3");
        //pluginWindow.getStyleClass().addAll("panel-title", "default");

        // set the window position to 10,10 (coordinates inside canvas)
        pluginWindow.setLayoutX(10);
        pluginWindow.setLayoutY(10);

        final MigPane contentPane = new MigPane();
        pluginWindow.setContentPane(contentPane);
        final Button btn = new Button("Hej!");
        btn.getStyleClass().setAll("btn","btn-primary", "btn-xs");
        contentPane.add(btn);
        contentPane.add(new Knob());

        // define the initial window size
        pluginWindow.setPrefSize(300, 200);

        // add the window to the canvas
        canvas.getChildren().add(pluginWindow);

        // init and show the stage
        primaryStage.setTitle("MIDI Editor");
        primaryStage.setScene(scene);
        primaryStage.show();

        MIDIConstants.getMidiDeviceInfo().forEach(i -> System.out.println(i.getDescription() + " " + i.getVersion()));

        MIDIConstants.getMidiDevices().forEach(i -> System.out.println(i));
        MIDIConstants.getSynths().forEach(i -> System.out.println(i));
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
