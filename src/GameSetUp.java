import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameSetUp {
    String[][] gameFrame;
    ArrayList<String> randomGameNrList;

    //create a shuffled list of numbers for 2D gaming frame
    public void createRandomNrList(){
        ArrayList<String> temp = new ArrayList<>
                (Arrays.asList(" ","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"));
        Collections.shuffle(temp);
        this.randomGameNrList = new ArrayList<>(temp);
    }

    public void setup2DGameBoard(){
        int counter = 0;
        for (int i = 0; i < gameFrame.length; i++) {
            for (int j = 0; j < gameFrame[0].length; j++) {
                gameFrame[i][j] = randomGameNrList.get(counter);
                counter++;
            }
        }
    }

    //Constructor
    public GameSetUp(){
        gameFrame = new String[4][4];
        randomGameNrList = new ArrayList<>();
        createRandomNrList();
        setup2DGameBoard();
    }

    //Test mode: update 2D gaming frame with test data
    public void setup2DGameBoardOnTestMode(String[][] testGame){
        int counter = 0;
        for (int i = 0; i < testGame.length; i++) {
            for (int j = 0; j < testGame[0].length; j++) {
                randomGameNrList.set(counter,testGame[i][j]);
                counter++;
            }
        }
        setup2DGameBoard();
    }

    //print the 2D gaming frame
    public String toString(){
        String temp = "";
        for (int i = 0; i < gameFrame.length; i++) {
            for (int j = 0; j < gameFrame[0].length; j++) {
                temp += String.format("%2s",gameFrame[i][j]) + " ";
            }
            temp += "\n";
        }
        return temp;
    }

    //get index of the selected number in 2D gaming frame
    public int[] getIndexOfSelectedNr(String selectedNr){
        int row = -1;
        int column = -1;
        for (int i = 0; i < gameFrame.length; i++) {
            for (int j = 0; j < gameFrame[0].length; j++) {
                if (gameFrame[i][j].equals(selectedNr)){
                    row = i;
                    column = j;
                }
            }
        }
        return new int[]{row,column};
    }

    //start the game and pick a selected number
    public void enterSelectedNr(String selectedNr){
        if (checkIfSelectedNrNextToEmpty(selectedNr)){
            int indexOfEmpty = getIndex(" ");
            int indexOfSelectedNr = getIndex(selectedNr);
            //exchange locations of the selected number and empty space
            randomGameNrList.set(indexOfSelectedNr," ");
            randomGameNrList.set(indexOfEmpty,selectedNr);
            setup2DGameBoard();

            //print msg if the game is finished.
            if (isGameNrInOrder()){
                System.out.println("You win!");
            }
        }
    }

    //check in overall if there's empty ABOVE,BELOW,on LEFT,on RIGHT side of the selected number
    public boolean checkIfSelectedNrNextToEmpty(String selectedNr){
        int[] temp = getIndexOfSelectedNr(selectedNr);
        return ifSelectedNrAboveIsEmpty(temp) || ifSelectedNrBelowIsEmpty(temp)
                || ifSelectedNrLeftIsEmpty(temp) || ifSelectedNrRightIsEmpty(temp);
    }

    //check if there's an empty space ABOVE the selected number
    public boolean ifSelectedNrAboveIsEmpty(int[] indexOfselectedNr){
        int row = indexOfselectedNr[0];
        int column = indexOfselectedNr[1];
        if ((row-1)>=0){
            if (gameFrame[row-1][column].equals(" ")){
                return true;
            }
        }
        return false;
    }

    //check if there's an empty space BELOW the selected number
    public boolean ifSelectedNrBelowIsEmpty(int[] indexOfselectedNr){
        int row = indexOfselectedNr[0];
        int column = indexOfselectedNr[1];
        if ((row+1)<4){
            if (gameFrame[row+1][column].equals(" ")){
                return true;
            }
        }
        return false;
    }

    //check if there's an empty space on the LEFT hand side of the selected number
    public boolean ifSelectedNrLeftIsEmpty(int[] indexOfselectedNr){
        int row = indexOfselectedNr[0];
        int column = indexOfselectedNr[1];
        if ((column-1)>=0){
            if (gameFrame[row][column-1].equals(" ")){
                return true;
            }
        }
        return false;
    }

    //check if there's an empty space on the RIGHT hand side of the selected number
    public boolean ifSelectedNrRightIsEmpty(int[] indexOfselectedNr){
        int row = indexOfselectedNr[0];
        int column = indexOfselectedNr[1];
        if ((column+1)<4){
            if (gameFrame[row][column+1].equals(" ")){
                return true;
            }
        }
        return false;
    }

    //get index of desired numbers in the arraylist of game numbers
    public int getIndex(String nr){
        for (int i = 0; i < randomGameNrList.size(); i++) {
            if (randomGameNrList.get(i).equals(nr)){
                return i;
            }
        }
        throw new NullPointerException("No such number");
    }

    //check if the numbers are in order
    public boolean isGameNrInOrder(){
        int correctCount = 0;
        for (int i = 1; i < 16; i++) {
            if (randomGameNrList.get(0).equals(" ")){
                if (randomGameNrList.get(i).equals(String.valueOf(i))){
                    correctCount++;
                }
            }  else if (randomGameNrList.get(i-1).equals(String.valueOf(i))){
                correctCount++;
            }
        }
        return correctCount == 15 ? true : false;
    }

    public ArrayList<String> getRandomGameNrList() {
        return randomGameNrList;
    }

    public void setGameNrList(int index, String value) {
        this.randomGameNrList.set(index,value);
    }
}
