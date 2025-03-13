import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CalculatorWindow implements ActionListener {

    // Declare GUI components
    public JFrame frame = new JFrame("Investment Calculator");
    public JButton resetButton = new JButton("reset"),
            calc_Button = new JButton("Calculate"),
            returnButton = new JButton(),
            infoButton1 = new JButton("i"),
            infoButton2 = new JButton("i"),
            infoButton3 = new JButton("i"),
            infoButton4 = new JButton("i"),
            infoButton5 = new JButton("i"),
            infoButton6 = new JButton("i"),
            infoButton7 = new JButton("i");
    public JRadioButton option1 = new JRadioButton("beginning"), option2 = new JRadioButton("end"), option3 = new JRadioButton("month"), option4 = new JRadioButton("year");
    public ButtonGroup group = new ButtonGroup(), group1 = new ButtonGroup();
    public JLabel label1 = new JLabel("Investment Calculator"),
            label2 = new JLabel("Starting Amount ($)"),
            label3 = new JLabel("Contribution Amount ($)"),
            label4 = new JLabel("Choose Compound Rate (%)"),
            label5 = new JLabel("Choose Return Rate (%)"),
            label6 = new JLabel("Choose when to contribute"),
            label7 = new JLabel("Choose Investment Period (years)"),
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
    public boolean option1IsSelected = false, pass = true;
    public static double startingAmount;
    public double contributionAmount;
    public double returnRate;
    public static double ROI;
    public static double totalContribution;
    public static double totalInterest;
    public static double totalInvestment;
    public int investmentInterval, buttonDimx = 21, buttonDimY = 21;
    public String selectedOption1, selectedOption2, selectedOption3;
    public double[] values;

    // Constructor to initialize the calculator window
    public CalculatorWindow() {
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

        // Set up the main frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(203, 203, 203));

        // Set up radio buttons for contribution timing (beginning or end)
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

        // Set up info button for contribution timing
        infoButton6.setBounds(730, 590, buttonDimx, buttonDimY);
        infoButton6.setToolTipText("<html>"
                + "This tells you the time you will want to contribute <br>"
                + "between the beginning or end of a time period. <br>"
                + "By contributing at the beginning of the time period, <br>"
                + "more money will be added compared to contributing at the end. <br>"
                + "Choose only one of the 2"
                + "<html>");
        infoButton6.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton6.setFocusable(false);
        infoButton6.setVisible(false);
        frame.add(infoButton6);

        // Set up radio buttons for contribution frequency (monthly or yearly)
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

        // Set up info button for contribution frequency
        infoButton7.setBounds(730, 630, buttonDimx, buttonDimY);
        infoButton7.setToolTipText("<html>"
                + "This tells you the time you <br>"
                + "will want to contribute <br>"
                + "between monthly or yearly. <br>"
                + "By contributing each month you will add more value to the overall <br>"
                + "investment, then if you contributed each year <br>"
                + "Choose only one of the 2"
                + "<html>");
        infoButton7.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton7.setFocusable(false);
        infoButton7.setVisible(false);
        frame.add(infoButton7);

        // Set up label for contribution timing
        label6.setBounds(490, 550, 200, 30);
        label6.setVisible(false);
        frame.add(label6);

        // Set up reset button
        resetButton.setBounds(700, 514, 70, 30);
        resetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(resetButton);
        resetButton.setFocusable(false);
        resetButton.setVisible(true);

        // Set up info button for compound rate
        infoButton5.setBounds(800, 520, buttonDimx, buttonDimY);
        infoButton5.setToolTipText("<html>"
                + "This is the compound rate which shows how <br>"
                + "often the return rate will be applied. <br>"
                + "A more frequent period will result <br>"
                + "in more money being added to the final investment. <br>"
                + "Monthly means the return rate will be applied every month, <br>"
                + "quarterly every 4 months, semi-annually every 6 months, <br>"
                + "and yearly every 12 months"
                + "<html>");
        infoButton5.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton5.setFocusable(false);
        frame.add(infoButton5);

        // Set up text field for starting amount
        textField.setBounds(490, 240, 100, 20);
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField);

        // Set up info button for starting amount
        infoButton1.setBounds(620, 240, buttonDimx, buttonDimY);
        infoButton1.setFocusable(false);
        infoButton1.setToolTipText("<html>"
                + "This is the initial amount that you invest. <br>"
                + "Any other personal additions to the investment <br>"
                + "will be counted as contributions. <br>"
                + "Please add only natural (120000) and <br>"
                + "decimal numbers (120.32)."
                + "<html>");
        infoButton1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(infoButton1);

        // Set up text field for contribution amount
        textField1.setBounds(490, 310, 100, 20);
        textField1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField1);

        // Set up info button for contribution amount
        infoButton2.setBounds(620, 310, buttonDimx, buttonDimY);
        infoButton2.setFocusable(false);
        infoButton2.setToolTipText("<html>"
                + "This is the contribution amount or the money that you add <br>"
                + "to the investment after the initial amount. The more money <br>"
                + "you will add throughout the investment's lifespan, the higher <br>"
                + "the value of the total investment. Please add only natural (120000) <br>"
                + "Please add only natural (120000) and decimal numbers (120.32)."
                + "</html>");
        infoButton2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(infoButton2);

        // Set up text field for return rate
        textField2.setBounds(490, 380, 100, 20);
        textField2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField2);

        // Set up info button for return rate
        infoButton3.setBounds(620, 380, buttonDimx, buttonDimY);
        infoButton3.setToolTipText("<html>"
                + "This is the return rate which shows what percentage <br>"
                + "of the investment at a specific time will be added <br>"
                + "to the total value of the investment.<br>"
                + "Please add only natural (12) and decimal numbers (1.3)"
                + "<html>");
        infoButton3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton3.setFocusable(false);
        frame.add(infoButton3);

        // Set up text field for investment period
        textField3.setBounds(490, 450, 100, 20);
        textField3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField3);

        // Set up info button for investment period
        infoButton4.setBounds(620, 450, buttonDimx, buttonDimY);
        infoButton4.setToolTipText("<html>"
                + "This is the period in which the investment evolves. <br>"
                + "The more time you allocate for the investment, the <br>"
                + "more it will compound over time. Please add only natural <br>"
                + "numbers (12)."
                + "<html>");
        infoButton4.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton4.setFocusable(false);
        frame.add(infoButton4);

        // Set up dropdown for compound rate interval
        String[] timeInterval = {"monthly", "quarterly", "semi-annually", "annually"};
        dropdown1 = new JComboBox<>(timeInterval);
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
                infoButton6.setVisible(true);
                infoButton7.setVisible(true);
                option1.setVisible(true);
                option2.setVisible(true);
                option3.setVisible(true);
                option4.setVisible(true);
            }
        });

        // Set up reset button action listener
        resetButton.addActionListener(e -> {
            infoButton6.setVisible(false);
            infoButton7.setVisible(false);
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

        // Set up main label for the calculator
        label1.setBounds(ss.width / 2 - 125, 125, 300, 40);
        label1.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label1);

        // Set up labels for input fields
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

        // Set up calculate button
        calc_Button.setBounds(489, 704, 100, 30);
        calc_Button.addActionListener(this);
        calc_Button.setFocusable(false);
        calc_Button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(calc_Button);

        // Set up return button
        returnButton.setBounds(441, 118, 20, 15);
        returnButton.addActionListener(this);
        returnButton.setText("←");
        returnButton.setFocusable(false);
        returnButton.setHorizontalTextPosition(SwingConstants.CENTER);
        returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(returnButton);

        // Set up output labels for error messages
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

        // Set up main panel
        panel1.setBounds(ss.width / 2 - 350, ss.height / 2 - 350, 700, 700);
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(panel1);

        // Make the frame visible
        frame.setVisible(true);

        // Set up return button action listener
        returnButton.addActionListener(e -> {
            frame.dispose();
            MainMenu menu = new MainMenu();
        });

        // Set up calculate button action listener
        calc_Button.addActionListener(e -> {
            if (validateTextFieldsAndDropDowns()) {
                frame.dispose();
                calculateResults();
                if(pass) {
                    ResultWindow resultWindow = new ResultWindow(startingAmount, contributionAmount, returnRate, investmentInterval, selectedOption1, selectedOption2, selectedOption3, values);
                }
            }
        });
    }

    // Method to validate input fields and dropdown selections
    public boolean validateTextFieldsAndDropDowns (){
        boolean isInputValid = true, isValid = false, isValid1 = false, isValid2 = false, isValid3 = false, isValidForTextFields = false;
        String input = textField.getText();

        // Validate starting amount input
        if (input.isEmpty()) {
            infoButton1.setVisible(false);
            textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel.setText("Input cannot be empty.");
            isInputValid = false;
        } else if (!input.matches("-?\\d+(\\.\\d+)?")) {
            infoButton1.setVisible(false);
            textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (input.length() > 8) {
            infoButton1.setVisible(false);
            textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (input.charAt(0) == '0' && input.length() > 1 && input.charAt(1) != '.') {
            infoButton1.setVisible(false);
            textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (isAllZero(input)) {
            infoButton1.setVisible(false);
            textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel.setText("Please enter a valid number.");
            isInputValid = false;
        } else {
            infoButton1.setVisible(true);
            startingAmount = Double.parseDouble(input);
            isValid = true;
            textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            outputLabel.setText("");
        }

        // Validate contribution amount input
        String input1 = textField1.getText();
        if (input1.isEmpty()) {
            infoButton2.setVisible(false);
            textField1.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel1.setText("Input cannot be empty.");
            isInputValid = false;
        } else if (!input1.matches("-?\\d+(\\.\\d+)?")) {
            infoButton2.setVisible(false);
            textField1.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel1.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (input1.charAt(0) == '0' && input1.length() > 1) {
            infoButton2.setVisible(false);
            textField1.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel1.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (isAllZero(input1)) {
            infoButton2.setVisible(false);
            textField1.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel1.setText("Please enter a valid number.");
            isInputValid = false;
        } else {
            infoButton2.setVisible(true);
            contributionAmount = Double.parseDouble(input1);
            isValid1 = true;
            textField1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            outputLabel1.setText("");
        }

        // Validate return rate input
        String input2 = textField2.getText();
        if (input2.isEmpty()) {
            infoButton3.setVisible(false);
            textField2.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel4.setText("Input cannot be empty.");
            isInputValid = false;
        } else if (!input2.matches("-?\\d+(\\.\\d+)?")) {
            infoButton3.setVisible(false);
            textField2.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel4.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (input2.length() > 8) {
            infoButton3.setVisible(false);
            textField2.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel4.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (input2.charAt(0) == '0' && input2.length() > 1) {
            infoButton3.setVisible(false);
            textField2.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel4.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (isAllZero(input2)) {
            infoButton3.setVisible(false);
            textField2.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel4.setText("Please enter a valid number.");
            isInputValid = false;
        } else {
            infoButton3.setVisible(true);
            returnRate = Double.parseDouble(input2);
            isValid2 = true;
            textField2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            outputLabel4.setText("");
        }

        // Validate investment period input
        String input3 = textField3.getText();
        if (input3.isEmpty()) {
            infoButton4.setVisible(false);
            textField3.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel6.setText("Input cannot be empty.");
            isInputValid = false;
        } else if (!input3.matches("\\d+")) {
            infoButton4.setVisible(false);
            textField3.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel6.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (input3.length() > 8) {
            infoButton4.setVisible(false);
            textField3.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel6.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (input3.charAt(0) == '0' && input3.length() > 1) {
            infoButton4.setVisible(false);
            textField3.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel6.setText("Please enter a valid number.");
            isInputValid = false;
        } else {
            infoButton4.setVisible(true);
            investmentInterval = Integer.parseInt(input3);
            isValid3 = true;
            textField3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            outputLabel6.setText("");
        }

        // Check if all text fields are valid
        if(isValid && isValid1 && isValid2 && isValid3 && isInputValid){
            isValidForTextFields = true;
        }
        boolean valid1 = false, valid2 = false, valid3 = false, isValidForDropDown = false;
        if(option1IsSelected){
            infoButton5.setVisible(true);
            valid1 = true;
            outputLabel2.setText("");
        } else {
            infoButton5.setVisible(false);
            outputLabel2.setText("Select an interval");
        }
        if(valid1 && group.getSelection() == null){
            infoButton6.setVisible(false);
            outputLabel3.setText("Choose beginning or end");
        } else if (valid1 && group.getSelection() != null) {
            infoButton5.setVisible(true);
            valid2 = true;
            outputLabel3.setText("");
            if(option1.isSelected()){
                selectedOption2 = option1.getText();
            } else if (option2.isSelected()) {
                selectedOption2 = option2.getText();
            }
        }
        if(valid1 && group1.getSelection() == null){
            infoButton7.setVisible(false);
            outputLabel5.setText("Choose month or year");
        } else if (valid1 && group1.getSelection() != null){
            infoButton7.setVisible(true);
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

    // Method to get the compound rate based on the selected interval
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

    // Method to calculate the investment results
    public void calculateResults(){
        double contributionforGrowthChart, interestforGrowthChart;
        values = new double[(int) investmentInterval];
        InvestmentGrowthChart.addFirstTerm(startingAmount);
        int compoundRate = getCompoundRate();
        for(int i = 0; i < investmentInterval; i++){
            if(Objects.equals(selectedOption2, "beginning") && Objects.equals(selectedOption3, "year")){
                values[i] = (contributionAmount * ((Math.pow(1 + (returnRate / 100), i + 1) - 1) / (returnRate / 100)) * (1 + (returnRate / 100))) + startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                interestforGrowthChart = values[i] - startingAmount;
                contributionforGrowthChart = values[i] - startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                InvestmentGrowthChart.createDataset(startingAmount, interestforGrowthChart, contributionforGrowthChart);
            } else if (Objects.equals(selectedOption2, "end") && Objects.equals(selectedOption3, "year")) {
                values[i] = (contributionAmount * ((Math.pow(1 + (returnRate / 100), i + 1) - 1) / (returnRate / 100))) + startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                interestforGrowthChart = values[i] - startingAmount;
                contributionforGrowthChart = values[i] - startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                InvestmentGrowthChart.createDataset(startingAmount, interestforGrowthChart, contributionforGrowthChart);
            } else if (Objects.equals(selectedOption2, "beginning") && Objects.equals(selectedOption3, "month")) {
                values[i] = (contributionAmount * ((Math.pow(1 + (returnRate / 1200), 12 * (i + 1)) - 1) / (returnRate / 1200)) * (1 + (returnRate / 1200))) + startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                interestforGrowthChart = values[i] - startingAmount;
                contributionforGrowthChart = values[i] - startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                InvestmentGrowthChart.createDataset(startingAmount, interestforGrowthChart, contributionforGrowthChart);
            } else if (Objects.equals(selectedOption2, "end") && Objects.equals(selectedOption3, "month")) {
                values[i] = (contributionAmount * ((Math.pow(1 + (returnRate / 1200), 12 * (i + 1)) - 1) / (returnRate / 1200))) + startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                interestforGrowthChart = values[i] - startingAmount;
                contributionforGrowthChart = values[i] - startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                InvestmentGrowthChart.createDataset(startingAmount, interestforGrowthChart, contributionforGrowthChart);
            }
        }
        if(values[investmentInterval - 1] > 100000000){
            pass = false;
        }
        if(pass) {
            totalInterest = values[investmentInterval - 1] - startingAmount;
            totalContribution = values[investmentInterval - 1] - startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), investmentInterval));
            totalInvestment = values[investmentInterval - 1];
            ROI = (values[investmentInterval - 1] / startingAmount) * 100;
        }
    }

    // Getters for investment results
    public static double getStartingAmount(){
        return startingAmount;
    }
    public static double getTotalContribution(){
        return totalContribution;
    }
    public static double getTotalInterest(){
        return totalInterest;
    }
    public static double getTotalInvestment(){
        return totalInvestment;
    }
    public static double getROI(){
        return ROI;
    }

    // Method to check if the input string consists of all zeros
    public static boolean isAllZero(String input) {
        input.replace(".", "").replace("-", "");
        return input.chars().allMatch(ch -> ch == '0');
    }

    // ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}