import javax.swing.*;
import java.awt.*;

public class ResultWindow {
    JFrame frame = new JFrame();
    public ImageIcon image = new ImageIcon("X:\\Applications\\IntelliJ IDEA Community Edition 2023.3.5\\Images\\p_5dfc3382-d5f7-11ee-ba1f-00163e012526_wm.png");


    ResultWindow(){
        frame.setLayout(null);
        frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setTitle("Result Window");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(203, 203, 203));




    }
}
