import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CalculatorWindow implements ActionListener {
    JFrame frame = new JFrame("Investment Calculator");
    JButton calc_Button = new JButton("Calculate");
    JButton returnButton = new JButton();
    JLabel label1 = new JLabel("Investment Calculator");
    JLabel label2 = new JLabel("Starting Amount");
    JLabel label3 = new JLabel("Contribution Amount");
    JLabel label4 = new JLabel("Choose Contribution Interval");
    JLabel label5 = new JLabel("Choose an Asset");
    JPanel panel1 = new JPanel();
    public ImageIcon image = new ImageIcon("X:\\Images\\p_5dfc3382-d5f7-11ee-ba1f-00163e012526_wm.png");
    JTextField textField = new JTextField();
    JTextField textField1 = new JTextField();
    JLabel outputLabel = new JLabel();
    JLabel outputLabel1 = new JLabel();
    JComboBox<String> dropdown1;
    JComboBox<String> dropdown2;
    public boolean isValid = false;
    public boolean isValid1 = false;
    JButton resetButton = new JButton("Reset");
    JRadioButton option1 = new JRadioButton("beginning");
    JRadioButton option2 = new JRadioButton("end");
    ButtonGroup group = new ButtonGroup();

    CalculatorWindow(){
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(203, 203, 203));

        option1.setBounds(490, 450, 100, 20);
        option1.setFocusable(false);
        option2.setBounds(600, 450, 100, 20);
        option2.setFocusable(false);

        group.add(option1);
        group.add(option2);

        frame.add(option1);
        frame.add(option2);

        option1.setVisible(false);
        option2.setVisible(false);

        resetButton.setBounds(700, 400, 70, 30);
        resetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(resetButton);
        resetButton.setFocusable(false);
        resetButton.setVisible(true);


        textField.setBounds(490, 265, 100, 20);
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField);

        textField1.setBounds(490, 330, 100, 20);
        textField1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField1);

        String[] timeInterval = {"Monthly", "Annually"};
        dropdown1 = new JComboBox<>(timeInterval);
        dropdown1.setToolTipText("Contribution Interval");
        dropdown1.setBounds(489, 400, 200, 30);
        dropdown1.setBackground(new Color(203, 203, 203));
        dropdown1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dropdown1.setFocusable(false);
        frame.add(dropdown1);
        dropdown1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) dropdown1.getSelectedItem();
                if(Objects.equals(selectedOption, "Monthly") || Objects.equals(selectedOption, "Annually")){
                    option1.setVisible(true);
                    option2.setVisible(true);
                }
            }
        });

        String[] investmentOptions = {"Stocks", "Bonds", "Real Estate", "Cryptocurrency", "Commodities"};
        dropdown2 = new JComboBox<>(investmentOptions);
        dropdown2.setToolTipText("Choose asset");
        dropdown2.setBounds(489, 514, 200, 30);
        dropdown2.setBackground(new Color(203, 203, 203));
        dropdown2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dropdown2.setFocusable(false);
        frame.add(dropdown2);
        dropdown2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) dropdown2.getSelectedItem();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    option1.setVisible(false);
                    option2.setVisible(false);
            }
        });

        label1.setBounds(ss.width / 2 - 125, 125, 300, 40);
        label1.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label1);

        label2.setBounds(489, 226, 200, 30);
        frame.add(label2);

        label3.setBounds(489, 296, 200, 30);
        frame.add(label3);

        label4.setBounds(489, 366, 200, 30);
        frame.add(label4);

        label5.setBounds(489, 480, 200, 30);
        frame.add(label5);

        calc_Button.setBounds(489, 704, 100, 30);
        calc_Button.addActionListener(this);
        calc_Button.setFocusable(false);
        calc_Button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(calc_Button);

        returnButton.setBounds(441, 118, 20, 15);
        returnButton.addActionListener(this);
        returnButton.setText("←");
        returnButton.setFocusable(false);
        returnButton.setHorizontalTextPosition(SwingConstants.CENTER);
        returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(returnButton);

        outputLabel.setBounds(604, 264, 500, 20);
        outputLabel.setForeground(Color.red);
        frame.add(outputLabel);

        outputLabel1.setBounds(604, 330, 500, 20);
        outputLabel1.setForeground(Color.red);
        frame.add(outputLabel1);


        panel1.setBounds(ss.width / 2 - 350, ss.height / 2 - 350, 700, 700);
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(panel1);

        frame.setVisible(true);

        calc_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isInputValid = true;

                String input = textField.getText();
                if (input.isEmpty()) {
                    isValid = false;
                    textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    outputLabel.setText("Input cannot be empty.");
                    isInputValid = false;
                } else if (!input.matches("\\d+")) {
                    isValid = false;
                    textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    outputLabel.setText("Please enter a valid number.");
                    isInputValid = false;
                } else {
                    isValid = true;
                    textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
                    outputLabel.setText("");
                }

                String input1 = textField1.getText();
                if (input1.isEmpty()) {
                    isValid1 = false;
                    textField1.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    outputLabel1.setText("Input cannot be empty.");
                    isInputValid = false;
                } else if (!input1.matches("\\d+")) {
                    isValid1 = false;
                    textField1.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    outputLabel1.setText("Please enter a valid number.");
                    isInputValid = false;
                } else {
                    isValid1 = true;
                    textField1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
                    outputLabel1.setText("");
                }


                if (isValid && isValid1 && isInputValid) {
                    frame.dispose();
                    ResultWindow resultWindow = new ResultWindow();
                }
            }
        });


    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == returnButton) {
            frame.dispose();
            MainMenu menu = new MainMenu();
        }
    }
}






