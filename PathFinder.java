package sample;

import javafx.geometry.Point2D;
import javafx.scene.layout.GridPane;
import java.util.Stack;


/**
 * Created by jrenta on 5/23/2017.
 */
public class PathFinder{

    //Variables
    private Stack mainPathStack;
    private Stack pathStack;
    private Stack branchStack = new Stack();
    private GridHandler gridHandler;
    private GridPane grid;
    private Point2D root;
    private Point2D currentNode;
    private Point2D tempNode;

    //Constructor get the gridHandler
    public PathFinder(GridHandler gH){
        gridHandler = gH;
        root = new Point2D(1,1);
        currentNode = root;
        setMainPathStack(gridHandler.getRowLength(),gridHandler.getColumnLength());
        findBranches();
    }

    private void findBranches(){
        if(!isMainPathStackEmpty())
            loadBranch();
    }

    /* This method goes through a grid in a snake-like path
     * Each element in the grid is collected & pushed into a stack
     * The bottom of the stack will contain the top left node of the grid (Row 1, Col 1)
    */
    private void setMainPathStack(int numRows, int numCols){
        int direction = 1; //The variable direction is "-1" or "+1"
        boolean endOfColFlag = false;
        boolean startOfColFlag = true;
        boolean endOfRowFlag = false;
        boolean initialMoveMade = false;
        boolean lastNodeReached = false;
        mainPathStack = new Stack();

        for(int row = 1; row < numRows; row++)
            for(int col = 1; col < numCols + 1; col += direction){
                //Flag Setup
                if(row == numRows || row > numRows)
                    endOfRowFlag = true;
                if(col == numCols)
                    endOfColFlag = true;
                if(col == 1 && initialMoveMade && row != numRows)
                    startOfColFlag = true;
                if(col == 1 && initialMoveMade && row == numRows) {
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

    public boolean isMainPathStackEmpty(){
        return mainPathStack.isEmpty();
    }
    public void moveToNextNode(){
        if(root != currentNode){
            root = (Point2D)mainPathStack.firstElement();
            mainPathStack.removeElementAt(0); //Check to see if "0" is top or bottom of stack
        }
        else{
            mainPathStack.removeElementAt(0);
            root = (Point2D)mainPathStack.firstElement();
            mainPathStack.removeElementAt(0);
        }
    }


    public void loadBranch(){
        if(doesLeftNeighborExist()){
            tempNode = new Point2D(currentNode.getX()-1,currentNode.getY());
            if(!isTempNodeInBranchStack())
            branchStack.push(tempNode);
        }
        if(doesDownNeighborExist()){
            tempNode = new Point2D(currentNode.getX(),currentNode.getY()+1);
            if(!isTempNodeInBranchStack())
                branchStack.push(tempNode);
        }
        if(doesRightNieghborExist()){
            tempNode = new Point2D(currentNode.getX()+1,currentNode.getY());
            if(!isTempNodeInBranchStack())
                branchStack.push(tempNode);
        }
        if(doesUpNeighborExist()){
            tempNode = new Point2D(currentNode.getX(),currentNode.getY()-1);
            //check if tempNode is already in branchStack
            if(!isTempNodeInBranchStack())
                branchStack.push(tempNode);
        }
    }

    public boolean isTempNodeInBranchStack(){
        boolean state = false;
        for(int i = 0; i < branchStack.size() - 1; i++){
            if(branchStack.elementAt(i)==tempNode)
                state = true;
        }
        return state;
    }

    //Check for neighbors around currentNode
    private boolean doesUpNeighborExist(){
        if(currentNode.getY() - 1 < 0)
            return false;
        else return true;
    }
    private boolean doesRightNieghborExist(){
        if(currentNode.getX() + 1 > gridHandler.getColumnLength())
            return false;
        else return true;
    }
    private boolean doesDownNeighborExist(){
        if(currentNode.getY() + 1 > gridHandler.getRowLength())
            return false;
        else return true;
    }
    private boolean doesLeftNeighborExist(){
        if(currentNode.getX() - 1 < 0)
            return false;
        else return true;
    }

}
