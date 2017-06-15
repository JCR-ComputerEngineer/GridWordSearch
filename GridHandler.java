package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.Random;
import java.util.Stack;

/**
 * Created by jrenta on 3/17/2017.
 */
public class GridHandler {

    private int row;
    private int col;
    private GridPane grid = new GridPane();
    private Random random = new Random();
    private char ch;
    private Stack stackOfLetters = new Stack();
    private Button button;
    private Insets buttonInsets = new Insets(10);

    //Constructor
    public GridHandler(GridPane gP, int rows, int cols){
        row = rows;
        col = cols;
        grid = gP;

        generateButtons();
    }

    private void generateButtons(){
        int j, k, stackLength = 0;
        //stackLength starts at zero
        for(j = 0; j < row; j++){
            for(k = 0; k < col; k++) {
                ch = (char)(random.nextInt(26)+65);
                System.out.print(ch + " ");
                stackOfLetters.push(ch);

                //peek in stack and place letter in button
                button = new Button(stackOfLetters.elementAt(stackLength).toString());
                //button = new Button(stackOfLetters.pop().toString());
                //Style the button here
                //button.setPrefWidth(50);
                button.setMinWidth(40);
                button.setMinHeight(button.getMinWidth());
                //Space the buttons here
                button.setPadding(buttonInsets);
                //Add button to the grid
                grid.add(button,k,j);
                stackLength++;
            }
            System.out.println(" ");
        }
        System.out.println();
        System.out.println("Size of Stack: " + stackOfLetters.size());
    }

    public GridPane getGrid(){
        return grid;
    }
    private Stack getStack(){
        return stackOfLetters;
    }
    public int getRowLength(){
        return row;
    }
    public int getColumnLength(){
        return col;
    }
}
