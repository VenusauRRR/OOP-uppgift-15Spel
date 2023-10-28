import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class J_15Spel extends JFrame implements ActionListener{
    JPanel p;
    JPanel gamePanel;
    ArrayList<JButton> buttonList;
    JLabel youWin;
    JButton startNewGame;
    GameSetUp g;
    static boolean isTest = false;
    String[][] test1 = new String[][]{
            {"1","5","2","3" },
            {"4"," ","6","7" },
            {"8","9","10","11" },
            {"12","13","14","15" },
    };

    String[][] test2 = new String[][]{
            {"1","2","3","4" },
            {"5","6","7","8" },
            {"9","10"," ","12" },
            {"13","14","11","15" },
    };

    //read GameSetUp.randomGameNrList to Swing and update the gaming board
    public void updateGamingBoard(){
        for (String i : g.getRandomGameNrList()){
            buttonList.add(new JButton(i));
        }
        int count = 0;

        for (JButton i : buttonList){
            gamePanel.add(i);
            count++;
        }
    }


    public J_15Spel(){
        g = new GameSetUp();
        if (isTest){
            g.setup2DGameBoardOnTestMode(test1);
        }
        System.out.println(g.toString());

        //intitiate variables
        buttonList = new ArrayList<>();
        startNewGame = new JButton("Start new game");
        youWin = new JLabel("You win!!!");
        p = new JPanel(new BorderLayout());
        gamePanel = new JPanel(new GridLayout(4,4));

        updateGamingBoard();

        add(p);
        p.add(gamePanel,BorderLayout.NORTH);
        p.add(startNewGame,BorderLayout.SOUTH);

        //add action listener to each button
        for (JButton i : buttonList){
            i.addActionListener(this);
        }

        //add action listener to "Start New Game" button
        startNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset panel components before staring new game
                p.add(gamePanel);
                p.remove(youWin);
                p.repaint();

                //create a new game object
                GameSetUp newGame = new GameSetUp();
                if (isTest){
                    newGame.setup2DGameBoardOnTestMode(test2);
                }
                //update the old game board with new game data.
                for (int i = 0; i < 16; i++) {
                    g.setGameNrList(i,newGame.getRandomGameNrList().get(i));
                }
                g.setup2DGameBoard();
                int count = 0;
                //update the Swing game board
                for (JButton i : buttonList){
                    i.setText(g.getRandomGameNrList().get(count));
                    count++;
                }
                pack();
            }
        });

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //action listener for button
    @Override
    public void actionPerformed(ActionEvent e) {
        int indexOfSelectedNr;
        int indexOfEmpty;
        for (JButton i : buttonList){
            if (e.getSource()==i){
                if (g.checkIfSelectedNrNextToEmpty(i.getText())){
                    g.enterSelectedNr(i.getText());
                    indexOfEmpty = g.getIndex(" ");
                    indexOfSelectedNr = g.getIndex(i.getText());
                    buttonList.get(indexOfEmpty).setText(" ");
                    buttonList.get(indexOfSelectedNr).setText(g.getRandomGameNrList().get(indexOfSelectedNr));
                }
            }
        }

        //add "you win" message when the game ends
        if (g.isGameNrInOrder()){
            p.add(youWin,BorderLayout.CENTER);
            p.remove(gamePanel);
        }
        pack();
    }

    public static void main(String[] args) {
        isTest = true;
        J_15Spel s = new J_15Spel();
    }
}
