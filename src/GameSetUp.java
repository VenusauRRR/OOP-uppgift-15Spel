import java.util.ArrayList;
import java.util.Collections;

public class GameSetUp {
    String[][] gameFrame;
    ArrayList<String> gameNrList;

    public void setShuffleGameNrList(){
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            if (i == 0){
                temp.add(" ");
            } else {
                temp.add(String.valueOf(i));
            }
        }
        Collections.shuffle(temp);
        this.gameNrList = new ArrayList<>(temp);
    }

    public void setGameFrameValue(){
        int counter = 0;
        for (int i = 0; i < gameFrame.length; i++) {
            for (int j = 0; j < gameFrame[0].length; j++) {
                gameFrame[i][j] = gameNrList.get(counter);
                counter++;
            }
        }
    }

    public void setTestGameFrameValue(String[][] inputFrame){
        int counter = 0;
        for (int i = 0; i < inputFrame.length; i++) {
            for (int j = 0; j < inputFrame[0].length; j++) {
                gameNrList.set(counter,inputFrame[i][j]);
                counter++;
            }
        }
        setGameFrameValue();
    }

    public GameSetUp(){
        gameFrame = new String[4][4];
        gameNrList = new ArrayList<>();
        setShuffleGameNrList();
        setGameFrameValue();
    }

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

    public void goSelectNr(String selectedNr){
        if (checkIfSelectedNrNextToEmpty(selectedNr)){
            int indexOfEmpty = getIndex(" ");
            int indexOfSelectedNr = getIndex(selectedNr);
            gameNrList.set(indexOfSelectedNr," ");
            gameNrList.set(indexOfEmpty,selectedNr);
            setGameFrameValue();
            if (isGameNrInOrder()){
                System.out.println("You win!");
            }
        }
    }

    public boolean checkIfSelectedNrNextToEmpty(String selectedNr){
        int[] temp = getIndexOfSelectedNr(selectedNr);
        return ifSelectedNrAboveIsEmpty(temp) || ifSelectedNrBelowIsEmpty(temp)
                || ifSelectedNrLeftIsEmpty(temp) || ifSelectedNrRightIsEmpty(temp);
    }

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

    public int getIndex(String nr){
        for (int i = 0; i < gameNrList.size(); i++) {
            if (gameNrList.get(i).equals(nr)){
                return i;
            }
        }
        throw new NullPointerException("No such number");
    }

    public boolean isGameNrInOrder(){
        int correctCount = 0;
        for (int i = 1; i < 16; i++) {
            if (gameNrList.get(0).equals(" ")){
                if (gameNrList.get(i).equals(String.valueOf(i))){
                    correctCount++;
                }
            }  else if (gameNrList.get(i-1).equals(String.valueOf(i))){
                correctCount++;
            }
        }
        return correctCount == 15 ? true : false;
    }

}
