package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();
        Parent root = borderPane;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);
        //gridHandler takes the size of the matrix (row, col)
        GridHandler gHandler = new GridHandler(gridPane, 3,3);
        PathFinder pathFinder = new PathFinder(gHandler);
    }


    public static void main(String[] args) {launch(args);}

    public void setGrid(){

    }
}
