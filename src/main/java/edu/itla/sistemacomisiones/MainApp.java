package edu.itla.sistemacomisiones;

import com.jfoenix.controls.JFXDecorator;
import edu.itla.sistemacomisiones.database.Conexion;
import edu.itla.sistemacomisiones.ui.VentanaPrincipalController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    private ViewFlowContext flowContext;

    @Override
    public void start(Stage stage) throws Exception {
        
       
        Conexion.getInstancia();
        Flow flow = new Flow(VentanaPrincipalController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flow.createHandler(flowContext).start(container);

        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);
        Scene scene = new Scene(decorator, 800, 850);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(MainApp.class.getResource("/styles/Styles.css").toExternalForm());
        stage.setMinWidth(700);
        stage.setMinHeight(800);
        stage.setScene(scene);
        stage.show();
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Test.fxml"));
//        
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//        
//        stage.setTitle("JavaFX and Maven");
//        stage.setScene(scene);
//        stage.show();
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
