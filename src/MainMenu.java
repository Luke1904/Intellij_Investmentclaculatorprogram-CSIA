import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;



public class MainMenu implements ActionListener{
    JFrame frame = new JFrame("Investment calculator");
    JButton button1 = new JButton("Go to investment calculator");
    JButton button2 = new JButton("Go to saves");
    JPanel panel = new JPanel();
    public ImageIcon image = new ImageIcon("X:\\Images\\p_5dfc3382-d5f7-11ee-ba1f-00163e012526_wm.png");
    JLabel label = new JLabel("Main Menu");




    MainMenu() {

        FolderManager.createSaveFolder();
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setIconImage(image.getImage());
        frame.setLayout(null);
        frame.setVisible(true);



        button1.setBounds((ss.width / 2) - (200 / 2), (ss.height / 2) - (30 / 2) - 30, 200, 30);
        button2.setBounds((ss.width / 2) - (200 / 2), (ss.height / 2) - (30 / 2) + (30 / 2), 200, 30);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        button2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        button1.setFocusable(false);
        button2.setFocusable(false);
        frame.add(button1);
        frame.add(button2);

        label.setBounds(ss.width / 2 - 63, ss.height / 2 - ((panel.getY() + button1.getY()) / 2) + 30, 300, 40);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label);


        frame.add(panel);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setBounds(ss.width / 2 - 250, ss.height / 2 - 250, 500, 500);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            frame.dispose();
            CalculatorWindow calculate = new CalculatorWindow();
        } else if (e.getSource() == button2) {
            frame.dispose();
            SavesMenu saves = new SavesMenu();
        }
    }
}

