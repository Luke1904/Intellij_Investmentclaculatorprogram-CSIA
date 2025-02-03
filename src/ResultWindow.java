import javax.swing.*;
import java.awt.*;

public class ResultWindow {

    JFrame frame = new JFrame();

    ResultWindow(){


        frame.setLayout(null);
        frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setTitle("Result Window");
        frame.getContentPane().setBackground(new Color(203, 203, 203));


    }



}
