import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private FXMLLoader fxmlLoader;
    private Parent rootNode;


    @Override
    public void start(Stage primaryStage) throws Exception {
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("server.fxml"));
        rootNode = fxmlLoader.load();

        main.java.Controller controller = fxmlLoader.getController();
//        controller.setTicTacToeService(this.ticTacToeService);
//        controller.setScene(rootNode.getScene());
//        controller.connect();

        primaryStage.setTitle("Client_1");
        Scene scene = new Scene(rootNode, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        controller.setScene(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
