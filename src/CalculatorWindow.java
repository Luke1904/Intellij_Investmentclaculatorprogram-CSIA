import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CalculatorWindow implements ActionListener {

    public JFrame frame = new JFrame("Investment Calculator");
    public JButton resetButton = new JButton("reset"),
            resetButton1 = new JButton("reset"),
            calc_Button = new JButton("Calculate"),
            returnButton = new JButton();
    public JRadioButton option1 = new JRadioButton("beginning"), option2 = new JRadioButton("end");
    public ButtonGroup group = new ButtonGroup();
    public JLabel label1 = new JLabel("Investment Calculator"),
            label2 = new JLabel("Starting Amount"),
            label3 = new JLabel("Contribution Amount"),
            label4 = new JLabel("Choose Contribution Interval"),
            label5 = new JLabel("Choose an Asset"),
            outputLabel = new JLabel(),
            outputLabel1 = new JLabel(),
            outputLabel2 = new JLabel(),
            outputLabel3 = new JLabel(),
            outputLabel4 = new JLabel();
    public JPanel panel1 = new JPanel();
    public JTextField textField = new JTextField(), textField1 = new JTextField();
    public JComboBox<String> dropdown1,
            dropdown2,
            dropdown3,
            dropdown4,
            dropdown5,
            dropdown6,
            dropdown7;
    public boolean option1IsSelected = false, option2IsSelected = false;
    public int startingAmount, contributionAmount;
    public String selectedOption1,
            selectedOption2,
            selectedOption3,
            selectedOption4,
            selectedOption5,
            selectedOption6,
            selectedOption7,
            selectedOption8,
            selectedOption9;

    public CalculatorWindow(){

        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
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
        frame.add(resetButton);
        resetButton.setFocusable(false);
        resetButton.setVisible(true);

        resetButton1.setBounds(700, 514, 70, 30);
        frame.add(resetButton1);
        resetButton1.setFocusable(false);
        resetButton1.setVisible(true);

        textField.setBounds(490, 265, 100, 20);
        frame.add(textField);

        textField1.setBounds(490, 330, 100, 20);
        frame.add(textField1);

        String[] timeInterval = {"monthly", "annually"};
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
                option1IsSelected = true;
                selectedOption1 = (String) dropdown1.getSelectedItem();
                if(Objects.equals(selectedOption1, "monthly") || Objects.equals(selectedOption1, "annually")){
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
                option2IsSelected = true;
                selectedOption2 = (String) dropdown2.getSelectedItem();
                DropdownManager.setVisibility(selectedOption2,
                        dropdown2,
                        dropdown3,
                        dropdown4,
                        dropdown5,
                        dropdown6,
                        dropdown7);
            }
        });

        String[] stockOptions = {"NASDAQ100", "S&P500", "DJ30", "Nikkei225"};
        dropdown3 = new JComboBox<>(stockOptions);
        dropdown3.setToolTipText("Select stock");
        dropdown3.setVisible(false);
        dropdown3.setBounds(489, 514, 200, 30);
        dropdown3.setBackground(new Color(203, 203, 203));
        dropdown3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dropdown3.setFocusable(false);
        frame.add(dropdown3);
        dropdown3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption3 = (String) dropdown3.getSelectedItem();
            }
        });

        String[] bondOptions = {"Vanguard total bond market ETF", "J.P. Morgan limited duration bond ETF", "Vanguard Short-term Bond Etf", "SPDR Portfolio Short Term Treasury ETF"};
        dropdown4 = new JComboBox<>(bondOptions);
        dropdown4.setToolTipText("Select bond");
        dropdown4.setVisible(false);
        dropdown4.setBounds(489, 514, 200, 30);
        dropdown4.setBackground(new Color(203, 203, 203));
        dropdown4.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dropdown4.setFocusable(false);
        frame.add(dropdown4);
        dropdown4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption4 = (String) dropdown4.getSelectedItem();
            }
        });

        String[] realEstateOptions = {"Prologis Inc.", "American Tower Corp.", "Digital Realty Trust Inc.", "AvalonBay Communities Inc."};
        dropdown5 = new JComboBox<>(realEstateOptions);
        dropdown5.setToolTipText("Select real estate");
        dropdown5.setVisible(false);
        dropdown5.setBounds(489, 514, 200, 30);
        dropdown5.setBackground(new Color(203, 203, 203));
        dropdown5.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dropdown5.setFocusable(false);
        frame.add(dropdown5);
        dropdown5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption5 = (String) dropdown5.getSelectedItem();
            }
        });

        String[] cryptoCurrencyOptions = {"Bitcoin", "Ethereum", "Tether", "Solana"};
        dropdown6 = new JComboBox<>(cryptoCurrencyOptions);
        dropdown6.setToolTipText("Select cryptocurrency");
        dropdown6.setVisible(false);
        dropdown6.setBounds(489, 514, 200, 30);
        dropdown6.setBackground(new Color(203, 203, 203));
        dropdown6.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dropdown6.setFocusable(false);
        frame.add(dropdown6);
        dropdown6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption6 = (String) dropdown6.getSelectedItem();
            }
        });

        String[] commoditiesOptions = {"Gold", "Natural Gas", "Copper", "Silver"};
        dropdown7 = new JComboBox<>(commoditiesOptions);
        dropdown7.setToolTipText("Select commodity");
        dropdown7.setVisible(false);
        dropdown7.setBounds(489, 514, 200, 30);
        dropdown7.setBackground(new Color(203, 203, 203));
        dropdown7.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dropdown7.setFocusable(false);
        frame.add(dropdown7);
        dropdown7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption7 = (String) dropdown7.getSelectedItem();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    outputLabel3.setText("");
                    option1IsSelected = false;
                    group.clearSelection();
                    option1.setVisible(false);
                    option2.setVisible(false);
            }
        });

        resetButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option2IsSelected = false;
                dropdown3.setVisible(false);
                dropdown4.setVisible(false);
                dropdown5.setVisible(false);
                dropdown6.setVisible(false);
                dropdown7.setVisible(false);
                dropdown2.setVisible(true);
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

        outputLabel1.setBounds(604, 329, 500, 20);
        outputLabel1.setForeground(Color.red);
        frame.add(outputLabel1);

        outputLabel2.setBounds(780, 401, 500, 20);
        outputLabel2.setForeground(Color.RED);
        frame.add(outputLabel2);

        outputLabel3.setBounds(700, 450, 500, 20);
        outputLabel3.setForeground(Color.red);
        frame.add(outputLabel3);

        outputLabel4.setBounds(780, 516, 500, 20);
        outputLabel4.setForeground(Color.RED);
        frame.add(outputLabel4);


        panel1.setBounds(ss.width / 2 - 350, ss.height / 2 - 350, 700, 700);
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(panel1);

        frame.setVisible(true);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MainMenu menu = new MainMenu();
            }
        });

        calc_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateTextFieldsAndDropDowns()){
                    frame.dispose();
                    ResultWindow resultWindow = new ResultWindow();
                }
            }
        });


    }


    public boolean validateTextFieldsAndDropDowns (){
        boolean isInputValid = true, isValid = false, isValid1 = false, isValidForTextFields = false;
        String input = textField.getText();
        if (input.isEmpty()) {
            textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel.setText("Input cannot be empty.");
            isInputValid = false;
        } else if (!input.matches("\\d+")) {
            textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel.setText("Please enter a valid number.");
            isInputValid = false;
        } else {
            startingAmount = Integer.parseInt(input);
            isValid = true;
            textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            outputLabel.setText("");
        }

        String input1 = textField1.getText();
        if (input1.isEmpty()) {
            textField1.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel1.setText("Input cannot be empty.");
            isInputValid = false;
        } else if (!input1.matches("\\d+")) {
            textField1.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel1.setText("Please enter a valid number.");
            isInputValid = false;
        } else {
            contributionAmount = Integer.parseInt(input1);
            isValid1 = true;
            textField1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            outputLabel1.setText("");
        }
        if(isValid && isValid1 && isInputValid){
            isValidForTextFields = true;
        }
        boolean valid1 = false, valid2 = false, valid3 = false, isValidForDropDown = false;
        if(option1IsSelected){
            valid1 = true;
            outputLabel2.setText("");
        } else {
            outputLabel2.setText("Select an interval");
        }
        if(valid1 && group.getSelection() == null){
            outputLabel3.setText("Choose beginning or end");
        } else if (valid1 && group.getSelection() != null) {
            valid2 = true;
            outputLabel3.setText("");
            if(option1.isSelected()){
                selectedOption8 = option1.getText();
            } else if (option2.isSelected()) {
                selectedOption9 = option2.getText();
            }
        }
        if(option2IsSelected){
            valid3 = true;
            outputLabel4.setText("");
        } else {
            outputLabel4.setText("Select an asset");
        }
        if(valid1 && valid2 && valid3){
            isValidForDropDown = true;
        }
        if(isValidForDropDown && isValidForTextFields){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}






