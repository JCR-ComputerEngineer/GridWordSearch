package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {
    private Stack stack;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();
        Parent root = borderPane;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);
        GridHandler gHandler = new GridHandler(gridPane, 3,3); //Need to ask the user for the matrix size
        PathFinder pathFinder = new PathFinder(gHandler.getRowLength(), gHandler.getColumnLength(), stack);
    }


    public static void main(String[] args) {launch(args);}

    public void setGrid(){

    }
}
