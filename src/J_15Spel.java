import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class J_15Spel extends JFrame implements ActionListener{
    JPanel p;
    ArrayList<JButton> buttonList;
    String[] list;
    GameSetUp g;
    public void updateGamingBoard(){
        for (String i : g.getGameNrList()){
            buttonList.add(new JButton(i));
        }
        int count = 0;

        for (JButton i : buttonList){
            p.add(i);
            count++;
        }
    }

    public void printGamingBoard(){
        g = new GameSetUp();
        System.out.println(g.toString());
        buttonList = new ArrayList<>();
        p = new JPanel(new GridLayout(4,4));
        updateGamingBoard();
    }

    public J_15Spel(){
        printGamingBoard();
        add(p);
        for (JButton i : buttonList){
            i.addActionListener(this);
        }
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        J_15Spel s = new J_15Spel();
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
                    buttonList.get(indexOfEmpty).setText(g.getGameNrList().get(indexOfEmpty));
                    buttonList.get(indexOfSelectedNr).setText(g.getGameNrList().get(indexOfSelectedNr));
                }
            }
        }
    }
}
