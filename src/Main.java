import com.sun.deploy.util.BlackList;
import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.AccountService;
import model.DatabaseConnection;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DatabaseConnection database = new DatabaseConnection("Coursework.db");

        AccountService.selectById(3, database);

        Pane root = new Pane();
        root.setStyle("-fx-background: #222222;");
        Scene scene = new Scene(root, 1024, 768);
        stage.setTitle("Elixr");
        stage.setScene(scene);

        Button myButton1 = new Button("Play");
        myButton1.setPrefSize(100, 35);
        myButton1.setLayoutX(462);
        myButton1.setLayoutY(680);
        root.getChildren().add(myButton1);

        Button myButton2 = new Button("Previous");
        myButton2.setPrefSize(100, 35);
        myButton2.setLayoutX(362);
        myButton2.setLayoutY(680);
        root.getChildren().add(myButton2);

        Button myButton3 = new Button("Next");
        myButton3.setPrefSize(100, 35);
        myButton3.setLayoutX(562);
        myButton3.setLayoutY(680);
        root.getChildren().add(myButton3);



        //add components
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    }

