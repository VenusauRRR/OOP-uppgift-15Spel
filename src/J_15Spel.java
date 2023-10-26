import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class J_15Spel extends JFrame {
    JPanel p;
//    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    ArrayList<JButton> buttonList;
    String[] list;

    public void DefineVariable(){
        p = new JPanel(new GridLayout(4,4));

        for (JButton i : buttonList){
            p.add(i);
        }
    }

    public J_15Spel(){
        DefineVariable();
        add(p);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        J_15Spel s = new J_15Spel();
    }
}
