package sample;

import javafx.geometry.Point2D;
import java.util.Stack;


/**
 * @author jrenta
 * @version 1.0
 */
public class PathFinder{
    public Stack pathStack;

    //Constructor get the gridHandler
    public PathFinder(GridHandler gH){
        pathStack = new Stack();
        setMainPathStack(gH.getRowLength(),gH.getColumnLength(),pathStack);
    }

    /* This method traverses through a grid in a snake-like path
     * Each element in the grid is collected & pushed into a stack
     * The bottom of the stack will contain the top left node of the grid (Row 1, Col 1)
     */
    private void setMainPathStack(int numRows, int numCols, Stack mainPathStack){
        int direction = 1; //The variable direction is "-1" or "+1"
        boolean endOfColFlag = false;
        boolean startOfColFlag = true;
        boolean endOfRowFlag = false;
        boolean initialMoveMade = false;
        boolean lastNodeReached = false;
        Point2D tempNode;

        for(int row = 1; row < numRows; row++)
            for(int col = 1; col < numCols + 1; col += direction){
                //Flag Setup
                if(row == numRows || row > numRows)
                    endOfRowFlag = true;
                if(col == numCols)
                    endOfColFlag = true;
                if(col == 1 && initialMoveMade && row != numRows)
                    startOfColFlag = true;
                if(col == 1 && initialMoveMade && row == numRows){
                    lastNodeReached = true;
                    startOfColFlag = true;
                }

                //Collection and Direction Algorithm
                if(endOfColFlag && endOfRowFlag){
                    tempNode = new Point2D(row, col);
                    mainPathStack.push(tempNode);
                    col = numCols; //exits inner loop
                }
                else if(endOfColFlag){
                    endOfColFlag = false;
                    tempNode = new Point2D(row, col);
                    mainPathStack.push(tempNode);
                    row++;
                    tempNode = new Point2D(row, col);
                    mainPathStack.push(tempNode);
                    direction *= -1;
                }
                else if(startOfColFlag && initialMoveMade && !lastNodeReached){
                    startOfColFlag = false;
                    tempNode = new Point2D(row, col);
                    mainPathStack.push(tempNode);
                    row++;
                    tempNode = new Point2D(row, col);
                    mainPathStack.push(tempNode);
                    direction *= -1; //might not go reverse here
                }
                else if(startOfColFlag && initialMoveMade && lastNodeReached) {
                    startOfColFlag = false;
                    tempNode = new Point2D(row, col);
                    mainPathStack.push(tempNode);
                    direction *= -1;//bringing back to normal
                    col = numCols;
                    row = numRows;
                }
                else{
                    initialMoveMade = true;
                    tempNode = new Point2D(row, col);
                    mainPathStack.push(tempNode);
                    startOfColFlag = false;
                }
            }
    }
}
