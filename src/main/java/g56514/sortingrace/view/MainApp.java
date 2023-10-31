package g56514.sortingrace.view;

import g56514.sortingrace.controller.Controller;
import g56514.sortingrace.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author yohan
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("/fxml/sort.fxml"));
        Model model = new Model();
        Controller controller = new Controller(model);
        SortViewController sortViewController = new SortViewController(controller);
        loader.setController(sortViewController);
        model.addObserver(sortViewController);
        VBox root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
