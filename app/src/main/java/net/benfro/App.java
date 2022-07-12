package net.benfro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.scene.control.window.Window;
import net.benfro.commons.controls.knob.Knob;
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

        // create a scene that displays the canvas (resolution 600,600)
        Scene scene = new Scene(canvas, 600, 600);

        // create a window with title "My Window"
        Window pluginWindow = new Window("Plugin");

        // set the window position to 10,10 (coordinates inside canvas)
        pluginWindow.setLayoutX(10);
        pluginWindow.setLayoutY(10);

        final MigPane contentPane = new MigPane();
        pluginWindow.setContentPane(contentPane);
        contentPane.add(new Button("Hej!"));
        contentPane.add(new Knob());

        // define the initial window size
        pluginWindow.setPrefSize(300, 200);

        // add the window to the canvas
        canvas.getChildren().add(pluginWindow);

        // init and show the stage
        primaryStage.setTitle("MIDI Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
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
