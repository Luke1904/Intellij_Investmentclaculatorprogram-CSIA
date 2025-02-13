import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CalculatorWindow implements ActionListener {


    public JFrame frame = new JFrame("Investment Calculator");
    public JButton resetButton = new JButton("reset"),
            calc_Button = new JButton("Calculate"),
            returnButton = new JButton();
    public JRadioButton option1 = new JRadioButton("beginning"), option2 = new JRadioButton("end"), option3 = new JRadioButton("month"), option4 = new JRadioButton("year");
    public ButtonGroup group = new ButtonGroup(), group1 = new ButtonGroup();
    public JLabel label1 = new JLabel("Investment Calculator"),
            label2 = new JLabel("Starting Amount"),
            label3 = new JLabel("Contribution Amount"),
            label4 = new JLabel("Choose Compound Rate"),
            label5 = new JLabel("Choose Return Rate"),
            label6 = new JLabel("Choose when to contribute"),
            label7 = new JLabel("Choose Investment Period"),
            outputLabel = new JLabel(),
            outputLabel1 = new JLabel(),
            outputLabel2 = new JLabel(),
            outputLabel3 = new JLabel(),
            outputLabel4 = new JLabel(),
            outputLabel5 = new JLabel(),
            outputLabel6 = new JLabel();
    public JPanel panel1 = new JPanel();
    public JTextField textField = new JTextField(),
            textField1 = new JTextField(),
            textField2 = new JTextField(),
            textField3 = new JTextField();
    public JComboBox<String> dropdown1;
    public boolean option1IsSelected = false;
    public double startingAmount, contributionAmount, returnRate, investmentInterval;
    public String selectedOption1, selectedOption2, selectedOption3;
    public double[] values;

    public CalculatorWindow() {
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(203, 203, 203));

        option1.setBounds(490, 590, 100, 20);
        option1.setFocusable(false);
        option2.setBounds(600, 590, 100, 20);
        option2.setFocusable(false);
        group.add(option1);
        group.add(option2);
        frame.add(option1);
        frame.add(option2);
        option1.setVisible(false);
        option2.setVisible(false);

        option3.setBounds(490, 630, 100, 20);
        option3.setFocusable(false);
        option4.setBounds(600, 630, 100, 20);
        option4.setFocusable(false);
        group1.add(option3);
        group1.add(option4);
        frame.add(option3);
        frame.add(option4);
        option3.setVisible(false);
        option4.setVisible(false);

        label6.setBounds(490, 550, 200, 30);
        label6.setVisible(false);
        frame.add(label6);

        resetButton.setBounds(700, 514, 70, 30);
        resetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(resetButton);
        resetButton.setFocusable(false);
        resetButton.setVisible(true);

        textField.setBounds(490, 240, 100, 20);
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField);

        textField1.setBounds(490, 310, 100, 20);
        textField1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField1);

        textField2.setBounds(490, 380, 100, 20);
        textField2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField2);

        textField3.setBounds(490, 450, 100, 20);
        textField3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField3);

        String[] timeInterval = {"monthly", "quarterly", "semi-annually", "annually"};
        dropdown1 = new JComboBox<>(timeInterval);
        dropdown1.setToolTipText("Compound Rate");
        dropdown1.setBounds(489, 514, 200, 30);
        dropdown1.setBackground(new Color(203, 203, 203));
        dropdown1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dropdown1.setFocusable(false);
        frame.add(dropdown1);
        dropdown1.addActionListener(e -> {
            option1IsSelected = true;
            selectedOption1 = (String) dropdown1.getSelectedItem();
            if (Objects.equals(selectedOption1, "monthly") || Objects.equals(selectedOption1, "quarterly") || Objects.equals(selectedOption1, "semi-annually") || Objects.equals(selectedOption1, "annually")) {
                label6.setVisible(true);
                option1.setVisible(true);
                option2.setVisible(true);
                option3.setVisible(true);
                option4.setVisible(true);
            }
        });

        resetButton.addActionListener(e -> {
            outputLabel3.setText("");
            outputLabel5.setText("");
            option1IsSelected = false;
            label6.setVisible(false);
            group.clearSelection();
            option1.setVisible(false);
            option2.setVisible(false);
            group1.clearSelection();
            option3.setVisible(false);
            option4.setVisible(false);
        });

        label1.setBounds(ss.width / 2 - 125, 125, 300, 40);
        label1.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label1);

        label2.setBounds(489, 200, 200, 30);
        frame.add(label2);

        label3.setBounds(489, 270, 200, 30);
        frame.add(label3);

        label4.setBounds(489, 480, 200, 30);
        frame.add(label4);

        label5.setBounds(489, 340, 200, 30);
        frame.add(label5);

        label7.setBounds(489, 410, 200, 30);
        frame.add(label7);

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

        outputLabel.setBounds(604, 240, 500, 20);
        outputLabel.setForeground(Color.red);
        frame.add(outputLabel);

        outputLabel1.setBounds(604, 310, 500, 20);
        outputLabel1.setForeground(Color.red);
        frame.add(outputLabel1);

        outputLabel2.setBounds(780, 519, 500, 20);
        outputLabel2.setForeground(Color.red);
        frame.add(outputLabel2);

        outputLabel3.setBounds(700, 590, 500, 20);
        outputLabel3.setForeground(Color.red);
        frame.add(outputLabel3);

        outputLabel4.setBounds(604, 380, 500, 20);
        outputLabel4.setForeground(Color.red);
        frame.add(outputLabel4);

        outputLabel5.setBounds(700, 630, 500, 20);
        outputLabel5.setForeground(Color.red);
        frame.add(outputLabel5);

        outputLabel6.setBounds(604, 450, 500, 20);
        outputLabel6.setForeground(Color.red);
        frame.add(outputLabel6);

        panel1.setBounds(ss.width / 2 - 350, ss.height / 2 - 350, 700, 700);
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(panel1);

        frame.setVisible(true);

        returnButton.addActionListener(e -> {
            frame.dispose();
            MainMenu menu = new MainMenu();
        });

        calc_Button.addActionListener(e -> {
            if (validateTextFieldsAndDropDowns()) {
                frame.dispose();
                calculateResults();
                //ResultWindow resultWindow = new ResultWindow();
            }
        });
    }

    public boolean validateTextFieldsAndDropDowns (){
        boolean isInputValid = true, isValid = false, isValid1 = false, isValid2 = false, isValid3 = false, isValidForTextFields = false;
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
            startingAmount = Double.parseDouble(input);
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
            contributionAmount = Double.parseDouble(input1);
            isValid1 = true;
            textField1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            outputLabel1.setText("");
        }

        String input2 = textField2.getText();
        if (input2.isEmpty()) {
            textField2.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel4.setText("Input cannot be empty.");
            isInputValid = false;
        } else if (!input2.matches("\\d+")) {
            textField2.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel4.setText("Please enter a valid number.");
            isInputValid = false;
        } else {
            returnRate = Double.parseDouble(input2);
            isValid2 = true;
            textField2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            outputLabel4.setText("");
        }

        String input3 = textField3.getText();
        if (input3.isEmpty()) {
            textField3.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel6.setText("Input cannot be empty.");
            isInputValid = false;
        } else if (!input3.matches("\\d+")) {
            textField3.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel6.setText("Please enter a valid number.");
            isInputValid = false;
        } else {
            investmentInterval = Double.parseDouble(input3);
            isValid3 = true;
            textField3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            outputLabel6.setText("");
        }

        if(isValid && isValid1 && isValid2 && isValid3 && isInputValid){
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
                selectedOption2 = option1.getText();
            } else if (option2.isSelected()) {
                selectedOption2 = option2.getText();
            }
        }
        if(valid1 && group1.getSelection() == null){
            outputLabel5.setText("Choose month or year");
        } else if (valid1 && group1.getSelection() != null){
            valid3 = true;
            outputLabel5.setText("");
            if(option3.isSelected()){
                selectedOption3 = option3.getText();
            } else if (option4.isSelected()) {
                selectedOption3 = option4.getText();
            }
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



    public int getCompoundRate(){
        if(Objects.equals(selectedOption1, "monthly")){
            return 12;
        } else if (Objects.equals(selectedOption1, "quarterly")) {
            return 4;
        } else if (Objects.equals(selectedOption1, "semi-annually")) {
            return 2;
        } else if (Objects.equals(selectedOption1, "annually")) {
            return 1;
        }
        return -1;
    }
    public void calculateResults(){
        values = new double[(int) investmentInterval];
        int compoundRate = getCompoundRate();
        for(int i = 0; i < investmentInterval; i++){
            if(Objects.equals(selectedOption2, "beginning")){
                values[i] = contributionAmount * ((Math.pow(1 + (returnRate / compoundRate * 100), i + 1) - 1) / (returnRate / compoundRate * 100)) * (1 + (returnRate / compoundRate * 100)) + startingAmount * (Math.pow(1 + (returnRate / compoundRate * 100), i + 1));
            } else if (Objects.equals(selectedOption2, "end")) {
                values[i] = contributionAmount * ((Math.pow(1 + (returnRate / compoundRate * 100), i + 1) - 1) / (returnRate / compoundRate * 100)) + startingAmount * (Math.pow(1 + (returnRate / compoundRate * 100), i + 1));
            }
        }

        for(int i = 0; i < investmentInterval; i++){
            System.out.print(values[i] + " ");

        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
    }
}






