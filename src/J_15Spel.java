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

    public void updateGamingBoard(){
        for (String i : g.getGameNrList()){
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
            g.setTestGameFrameValue(test1);
        }
        System.out.println(g.toString());

        buttonList = new ArrayList<>();
        startNewGame = new JButton("Start new game");
        youWin = new JLabel("You win!!!");
        p = new JPanel(new BorderLayout());
        gamePanel = new JPanel(new GridLayout(4,4));

        updateGamingBoard();

        add(p);
        p.add(gamePanel,BorderLayout.NORTH);
        p.add(startNewGame,BorderLayout.SOUTH);
        for (JButton i : buttonList){
            i.addActionListener(this);
        }
        startNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.add(gamePanel);
                p.remove(youWin);
                p.repaint();
                GameSetUp newGame = new GameSetUp();
                if (isTest){
                    newGame.setTestGameFrameValue(test2);
                }
                System.out.println(newGame.toString());
                for (int i = 0; i < 16; i++) {
                    g.setGameNrList(i,newGame.getGameNrList().get(i));
                }
                g.setGameFrameValue();
                int count = 0;
                for (JButton i : buttonList){
                    i.setText(g.getGameNrList().get(count));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        int indexOfSelectedNr;
        int indexOfEmpty;
        for (JButton i : buttonList){
            if (e.getSource()==i){
                if (g.checkIfSelectedNrNextToEmpty(i.getText())){
                    g.goSelectNr(i.getText());
                    indexOfEmpty = g.getIndex(" ");
                    indexOfSelectedNr = g.getIndex(i.getText());
                    buttonList.get(indexOfEmpty).setText(" ");
                    buttonList.get(indexOfSelectedNr).setText(g.getGameNrList().get(indexOfSelectedNr));
                }
            }
        }
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
