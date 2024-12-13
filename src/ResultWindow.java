import javax.swing.*;
import java.awt.*;

public class ResultWindow {
    JFrame frame = new JFrame("Result Window");
    public ImageIcon image = new ImageIcon("X:\\Images\\p_5dfc3382-d5f7-11ee-ba1f-00163e012526_wm.png");
    JPanel panel2 = new JPanel();
    XOYGraphic graphic = new XOYGraphic();


    ResultWindow(){
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLayout(null);
        frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setTitle("Result Window");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(203, 203, 203));

        graphic.setBounds(458, 149, 200, 200);
        frame.add(graphic);






        panel2.setBounds(ss.width / 2 - 350, ss.height / 2 - 350, 700, 700);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(panel2);








    }
}
