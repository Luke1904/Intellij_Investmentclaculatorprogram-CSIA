import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CalculatorWindow implements ActionListener {

    // GUI components
    public JFrame frame = new JFrame("Investment Calculator"); // Main window
    public JButton resetButton = new JButton("reset"), // Button to reset inputs
            calc_Button = new JButton("Calculate"), // Button to calculate results
            returnButton = new JButton(), // Button to return to the main menu
            infoButton1 = new JButton("i"), // Info button for starting amount
            infoButton2 = new JButton("i"), // Info button for contribution amount
            infoButton3 = new JButton("i"), // Info button for return rate
            infoButton4 = new JButton("i"), // Info button for investment period
            infoButton5 = new JButton("i"), // Info button for compound rate
            infoButton6 = new JButton("i"), // Info button for contribution timing
            infoButton7 = new JButton("i"); // Info button for contribution frequency

    // Radio buttons for contribution timing and frequency
    public JRadioButton option1 = new JRadioButton("beginning"), option2 = new JRadioButton("end"),
            option3 = new JRadioButton("month"), option4 = new JRadioButton("year");
    public ButtonGroup group = new ButtonGroup(), group1 = new ButtonGroup(); // Button groups for radio buttons

    // Labels for UI elements
    public JLabel label1 = new JLabel("Investment Calculator"),
            label2 = new JLabel("Starting Amount ($)"),
            label3 = new JLabel("Contribution Amount ($)"),
            label4 = new JLabel("Choose Compound Rate (%)"),
            label5 = new JLabel("Choose Return Rate (%)"),
            label6 = new JLabel("Choose when to contribute"),
            label7 = new JLabel("Choose Investment Period (years)"),
            outputLabel = new JLabel(), // Label for error messages
            outputLabel1 = new JLabel(),
            outputLabel2 = new JLabel(),
            outputLabel3 = new JLabel(),
            outputLabel4 = new JLabel(),
            outputLabel5 = new JLabel(),
            outputLabel6 = new JLabel();

    public JPanel panel1 = new JPanel(); // Main panel for the UI
    public JTextField textField = new JTextField(), // Text field for starting amount
            textField1 = new JTextField(), // Text field for contribution amount
            textField2 = new JTextField(), // Text field for return rate
            textField3 = new JTextField(); // Text field for investment period
    public JComboBox<String> dropdown1; // Dropdown for compound rate interval

    // Flags and variables for calculations
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

    // Constructor
    public CalculatorWindow() {
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize(); // Get screen size for dynamic positioning

        // Panel 1 positioning and sizing
        int xPanel1 = ss.width / 2 - ss.width / 4 + ss.width / 100; // X-coordinate of Panel 1: centered horizontally with a slight offset
        int yPanel1 = ss.height / 10; // Y-coordinate of Panel 1: 1/10 of the screen height from the top
        int wPanel1 = ss.width / 2; // Width of Panel 1: half of the screen width
        int hPanel1 = ss.height - ss.height / 6; // Height of Panel 1: 5/6 of the screen height

// Label 1 positioning and sizing
        int xLabel1 = ss.width / 2 - wPanel1 / 8; // X-coordinate of Label 1: centered horizontally with an offset
        int yLabel1 = ss.height / 2 - 2 * (ss.height / 6); // Y-coordinate of Label 1: vertically centered with an offset
        int wLabel1 = wPanel1 / 3; // Width of Label 1: 1/3 of Panel 1's width
        int hLabel1 = hPanel1 / 14; // Height of Label 1: 1/14 of Panel 1's height

// Return button positioning
        int xReturnButton = xPanel1 + wPanel1 / 16; // X-coordinate of the Return button: offset from Panel 1's left edge
        int yReturnButton = yPanel1 + hPanel1 / 16; // Y-coordinate of the Return button: offset from Panel 1's top edge

// Text fields positioning and sizing
        int xTextFields = ss.width / 2 - wPanel1 / 3; // X-coordinate of text fields: centered horizontally with an offset
        int yTextField = ss.height / 2 - hPanel1 / 4; // Y-coordinate of the first text field: vertically centered with an offset
        int wTextFields = wPanel1 / 8; // Width of text fields: 1/8 of Panel 1's width
        int hTextFields = hPanel1 / 35; // Height of text fields: 1/35 of Panel 1's height

// Labels for text fields positioning and sizing
        int xLabelsTxt = xTextFields; // X-coordinate of labels for text fields: same as text fields
        int yLabel2 = yTextField - 5 * (hTextFields / 3); // Y-coordinate of Label 2: above the first text field
        int yLabel3 = yTextField + 3 * hTextFields / 2; // Y-coordinate of Label 3: below the first text field
        int wLabelsTxt = 2 * wTextFields; // Width of labels for text fields: twice the width of text fields
        int hLabelsTxt = hTextFields; // Height of labels for text fields: same as text fields

// Info buttons positioning
        int xInfoButtons = xTextFields + wTextFields + wTextFields / 4; // X-coordinate of Info buttons: offset from text fields
        int yInfoButton1 = yTextField; // Y-coordinate of Info Button 1: same as the first text field
        int yTextField1 = yLabel3 + 3 * hLabelsTxt / 2; // Y-coordinate of the second text field: below Label 3
        int yInfoButton2 = yTextField1; // Y-coordinate of Info Button 2: same as the second text field
        int yLabel5 = yTextField1 + 3 * hTextFields / 2; // Y-coordinate of Label 5: below the second text field
        int yTextField2 = yLabel5 + 3 * hLabelsTxt / 2; // Y-coordinate of the third text field: below Label 5
        int yInfoButton3 = yTextField2; // Y-coordinate of Info Button 3: same as the third text field
        int yLabel7 = yTextField2 + 3 * hTextFields / 2; // Y-coordinate of Label 7: below the third text field
        int yTextField3 = yLabel7 + 3 * hLabelsTxt / 2; // Y-coordinate of the fourth text field: below Label 7
        int yInfoButton4 = yTextField3; // Y-coordinate of Info Button 4: same as the fourth text field
        int yLabel4 = yTextField3 + 3 * hTextFields / 2; // Y-coordinate of Label 4: below the fourth text field

// Dropdown positioning and sizing
        int wDropdown1 = 2 * wTextFields; // Width of Dropdown 1: twice the width of text fields
        int hDropdown1 = 3 * hTextFields / 2; // Height of Dropdown 1: 1.5 times the height of text fields
        int yDropdown1 = yLabel4 + 3 * hLabelsTxt / 2; // Y-coordinate of Dropdown 1: below Label 4

// Reset button positioning and sizing
        int xResetButton = xTextFields + wDropdown1 + wDropdown1 / 16; // X-coordinate of Reset button: offset from Dropdown 1
        int yResetButton = yDropdown1; // Y-coordinate of Reset button: same as Dropdown 1
        int wResetButton = wDropdown1 / 4; // Width of Reset button: 1/4 of Dropdown 1's width
        int hResetButton = hDropdown1; // Height of Reset button: same as Dropdown 1's height

// Info Button 5 positioning
        int xInfoButton5 = xResetButton + 3 * wResetButton / 2; // X-coordinate of Info Button 5: offset from Reset button
        int yInfoButton5 = yResetButton + hResetButton / 4; // Y-coordinate of Info Button 5: offset from Reset button

// Label 6 positioning
        int xLabel6 = xTextFields; // X-coordinate of Label 6: same as text fields
        int yLabel6 = yDropdown1 + 3 * hDropdown1 / 2; // Y-coordinate of Label 6: below Dropdown 1

// Options positioning and sizing
        int wOptions = wLabelsTxt / 2; // Width of options: half the width of labels for text fields
        int xOption1 = xLabel6; // X-coordinate of Option 1: same as Label 6
        int yOption1 = yLabel6 + 2 * hLabelsTxt; // Y-coordinate of Option 1: below Label 6
        int xOption2 = xLabel6 + wLabelsTxt / 2; // X-coordinate of Option 2: offset from Option 1
        int yOption2 = yOption1; // Y-coordinate of Option 2: same as Option 1
        int xInfoButton6 = xOption2 + wLabelsTxt / 2; // X-coordinate of Info Button 6: offset from Option 2
        int yInfoButton6 = yOption1; // Y-coordinate of Info Button 6: same as Option 1
        int xOption3 = xOption1; // X-coordinate of Option 3: same as Option 1
        int yOption3 = yOption2 + 5 * hLabelsTxt / 3; // Y-coordinate of Option 3: below Option 2
        int xOption4 = xOption3 + wOptions; // X-coordinate of Option 4: offset from Option 3
        int yOption4 = yOption3; // Y-coordinate of Option 4: same as Option 3
        int xInfoButton7 = xInfoButton6; // X-coordinate of Info Button 7: same as Info Button 6
        int yInfoButton7 = yInfoButton6 + 3 * hLabelsTxt / 2; // Y-coordinate of Info Button 7: below Info Button 6

// Calculate button positioning and sizing
        int xCalcButton = xOption3; // X-coordinate of Calculate button: same as Option 3
        int yCalcButton = yOption3 + 3 * hLabelsTxt; // Y-coordinate of Calculate button: below Option 3
        int wCalcButton = wTextFields; // Width of Calculate button: same as text fields
        int hCalcButton = 3 * hTextFields / 2; // Height of Calculate button: 1.5 times the height of text fields

// Output labels positioning
        int xOutputLabel = xInfoButtons - buttonDimx / 2; // X-coordinate of Output Label: offset from Info buttons
        int yOutputLabel = yTextField; // Y-coordinate of Output Label: same as the first text field
        int xOutputLabel1 = xOutputLabel; // X-coordinate of Output Label 1: same as Output Label
        int yOutputLabel1 = yTextField1; // Y-coordinate of Output Label 1: same as the second text field
        int xOutputLabel4 = xOutputLabel; // X-coordinate of Output Label 4: same as Output Label
        int yOutputLabel4 = yTextField2; // Y-coordinate of Output Label 4: same as the third text field
        int xOutputLabel6 = xOutputLabel; // X-coordinate of Output Label 6: same as Output Label
        int yOutputLabel6 = yTextField3; // Y-coordinate of Output Label 6: same as the fourth text field
        int xOutputLabel2 = xResetButton + 5 * wResetButton / 4; // X-coordinate of Output Label 2: offset from Reset button
        int yOutputLabel2 = yResetButton + hResetButton / 8; // Y-coordinate of Output Label 2: offset from Reset button
        int xOutputLabel3 = xOption1 + wDropdown1; // X-coordinate of Output Label 3: offset from Option 1
        int yOutputLabel3 = yOption1; // Y-coordinate of Output Label 3: same as Option 1
        int xOutputLabel5 = xOption3 + wDropdown1; // X-coordinate of Output Label 5: offset from Option 3
        int yOutputLabel5 = yOption3; // Y-coordinate of Output Label 5: same as Option 3


        // Set up the main frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed
        frame.setLayout(null); // Use absolute positioning
        frame.getContentPane().setBackground(new Color(203, 203, 203)); // Set background color

        // Set up radio buttons for contribution timing
        option1.setBounds(xOption1, yOption1, wOptions, hLabelsTxt);
        option1.setFocusable(false);
        option2.setBounds(xOption2, yOption2, wOptions, hLabelsTxt);
        option2.setFocusable(false);
        group.add(option1);
        group.add(option2);
        frame.add(option1);
        frame.add(option2);
        option1.setVisible(false);
        option2.setVisible(false);

        // Set up info button for contribution timing
        infoButton6.setBounds(xInfoButton6, yInfoButton6, buttonDimx, buttonDimY);
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

        // Set up radio buttons for contribution frequency
        option3.setBounds(xOption3, yOption3, wOptions, hLabelsTxt);
        option3.setFocusable(false);
        option4.setBounds(xOption4, yOption4, wOptions, hLabelsTxt);
        option4.setFocusable(false);
        group1.add(option3);
        group1.add(option4);
        frame.add(option3);
        frame.add(option4);
        option3.setVisible(false);
        option4.setVisible(false);

        // Set up info button for contribution frequency
        infoButton7.setBounds(xInfoButton7, yInfoButton7, buttonDimx, buttonDimY);
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
        label6.setBounds(xLabel6, yLabel6, wLabelsTxt, hLabelsTxt);
        label6.setVisible(false);
        frame.add(label6);

        // Set up reset button
        resetButton.setBounds(xResetButton, yResetButton, wResetButton, hResetButton);
        resetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(resetButton);
        resetButton.setFocusable(false);
        resetButton.setVisible(true);

        // Set up info button for compound rate
        infoButton5.setBounds(xInfoButton5, yInfoButton5, buttonDimx, buttonDimY);
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
        textField.setBounds(xTextFields, yTextField, wTextFields, hTextFields);
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField);

        // Set up info button for starting amount
        infoButton1.setBounds(xInfoButtons, yInfoButton1, buttonDimx, buttonDimY);
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
        textField1.setBounds(xTextFields, yTextField1, wTextFields, hTextFields);
        textField1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField1);

        // Set up info button for contribution amount
        infoButton2.setBounds(xInfoButtons, yInfoButton2, buttonDimx, buttonDimY);
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
        textField2.setBounds(xTextFields, yTextField2, wTextFields, hTextFields);
        textField2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField2);

        // Set up info button for return rate
        infoButton3.setBounds(xInfoButtons, yInfoButton3, buttonDimx, buttonDimY);
        infoButton3.setToolTipText("<html>"
                + "This is the return rate which shows what percentage <br>"
                + "of the investment at a specific time will be added <br>"
                + "to the total value of the investment.<br>"
                + "Please add only natural smaller then 50 (12) and decimal numbers under 7 digits (1.323)"
                + "<html>");
        infoButton3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton3.setFocusable(false);
        frame.add(infoButton3);

        // Set up text field for investment period
        textField3.setBounds(xTextFields, yTextField3, wTextFields, hTextFields);
        textField3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        frame.add(textField3);

        // Set up info button for investment period
        infoButton4.setBounds(xInfoButtons, yInfoButton4, buttonDimx, buttonDimY);
        infoButton4.setToolTipText("<html>"
                + "This is the period in which the investment evolves. <br>"
                + "The more time you allocate for the investment, the <br>"
                + "more it will compound over time. Please add only natural <br>"
                + "numbers under 3 digits (12)."
                + "<html>");
        infoButton4.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton4.setFocusable(false);
        frame.add(infoButton4);

        // Set up dropdown for compound rate interval
        String[] timeInterval = {"monthly", "quarterly", "semi-annually", "annually"};
        dropdown1 = new JComboBox<>(timeInterval);
        dropdown1.setBounds(xLabelsTxt, yDropdown1, wDropdown1, hDropdown1);
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
                if (Objects.equals(outputLabel3.getText(), "") && Objects.equals(outputLabel5.getText(), " ")){
                    infoButton6.setVisible(true);
                    infoButton7.setVisible(true);
                }
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
        label1.setBounds(xLabel1, yLabel1, wLabel1, hLabel1);
        label1.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label1);

        // Set up labels for input fields
        label2.setBounds(xLabelsTxt, yLabel2, wLabelsTxt, hLabelsTxt);
        frame.add(label2);

        label3.setBounds(xLabelsTxt, yLabel3, wLabelsTxt, hLabelsTxt);
        frame.add(label3);

        label4.setBounds(xLabelsTxt, yLabel4, wLabelsTxt, hLabelsTxt);
        frame.add(label4);

        label5.setBounds(xLabelsTxt, yLabel5, wLabelsTxt, hLabelsTxt);
        frame.add(label5);

        label7.setBounds(xLabelsTxt, yLabel7, wLabelsTxt * 2, hLabelsTxt);
        frame.add(label7);

        // Set up calculate button
        calc_Button.setBounds(xCalcButton, yCalcButton, wCalcButton, hCalcButton);
        calc_Button.addActionListener(this);
        calc_Button.setFocusable(false);
        calc_Button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(calc_Button);

        // Set up return button
        returnButton.setBounds(xReturnButton, yReturnButton, buttonDimx, buttonDimY);
        returnButton.addActionListener(this);
        returnButton.setText("←");
        returnButton.setFocusable(false);
        returnButton.setHorizontalTextPosition(SwingConstants.CENTER);
        returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(returnButton);

        // Set up output labels for error messages
        outputLabel.setBounds(xOutputLabel, yOutputLabel, wLabelsTxt, hLabelsTxt);
        outputLabel.setForeground(Color.red);
        frame.add(outputLabel);

        outputLabel1.setBounds(xOutputLabel1, yOutputLabel1, wLabelsTxt, hLabelsTxt);
        outputLabel1.setForeground(Color.red);
        frame.add(outputLabel1);

        outputLabel2.setBounds(xOutputLabel2, yOutputLabel2, wLabelsTxt, hLabelsTxt);
        outputLabel2.setForeground(Color.red);
        frame.add(outputLabel2);

        outputLabel3.setBounds(xOutputLabel3, yOutputLabel3, wLabelsTxt, hLabelsTxt);
        outputLabel3.setForeground(Color.red);
        frame.add(outputLabel3);

        outputLabel4.setBounds(xOutputLabel4, yOutputLabel4, wLabelsTxt, hLabelsTxt);
        outputLabel4.setForeground(Color.red);
        frame.add(outputLabel4);

        outputLabel5.setBounds(xOutputLabel5, yOutputLabel5, wLabelsTxt, hLabelsTxt);
        outputLabel5.setForeground(Color.red);
        frame.add(outputLabel5);

        outputLabel6.setBounds(xOutputLabel6, yOutputLabel6, wLabelsTxt, hLabelsTxt);
        outputLabel6.setForeground(Color.red);
        frame.add(outputLabel6);

        // Set up main panel
        panel1.setBounds(xPanel1, yPanel1, wPanel1, hPanel1);
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

    // Method to validate input fields and dropdowns
    public boolean validateTextFieldsAndDropDowns (){
        boolean isInputValid = true, isValid = false, isValid1 = false, isValid2 = false, isValid3 = false, isValidForTextFields = false;
        String input = textField.getText();

        // Validate starting amount
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
        } else if (input.charAt(0) == '-') {
            infoButton1.setVisible(false);
            textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel.setText("Please enter a valid number.");
            isInputValid = false;
        }
        else {
            infoButton1.setVisible(true);
            startingAmount = Double.parseDouble(input);
            isValid = true;
            textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            outputLabel.setText("");
        }

        // Validate contribution amount
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
        } else if (input.length() > 8) {
            infoButton1.setVisible(false);
            textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (input1.charAt(0) == '-') {
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

        // Validate return rate
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
        } else if (Double.parseDouble(input2) > 50 || input2.length() > 6) {
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
        } else if (input2.charAt(0) == '-') {
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

        // Validate investment period
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
        } else if (input3.length() > 2) {
            infoButton4.setVisible(false);
            textField3.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel6.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (input3.charAt(0) == '0' && input3.length() > 1) {
            infoButton4.setVisible(false);
            textField3.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            outputLabel6.setText("Please enter a valid number.");
            isInputValid = false;
        } else if (input3.charAt(0) == '-') {
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
            infoButton6.setVisible(true);
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

    // Method to get the compound rate
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
                values[i] = (contributionAmount * ((Math.pow(1 + (returnRate / 100), i + 1) - 1)
                        / (returnRate / 100)) * (1 + (returnRate / 100))) + startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                interestforGrowthChart = values[i] - startingAmount;
                contributionforGrowthChart = values[i] - startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                InvestmentGrowthChart.createDataset(startingAmount, interestforGrowthChart, contributionforGrowthChart);
            } else if (Objects.equals(selectedOption2, "end") && Objects.equals(selectedOption3, "year")) {
                values[i] = (contributionAmount * ((Math.pow(1 + (returnRate / 100), i + 1) - 1)
                        / (returnRate / 100))) + startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                interestforGrowthChart = values[i] - startingAmount;
                contributionforGrowthChart = values[i] - startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                InvestmentGrowthChart.createDataset(startingAmount, interestforGrowthChart, contributionforGrowthChart);
            } else if (Objects.equals(selectedOption2, "beginning") && Objects.equals(selectedOption3, "month")) {
                values[i] = (contributionAmount * ((Math.pow(1 + (returnRate / 1200), 12 * (i + 1)) - 1)
                        / (returnRate / 1200)) * (1 + (returnRate / 1200))) + startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                interestforGrowthChart = values[i] - startingAmount;
                contributionforGrowthChart = values[i] - startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
                InvestmentGrowthChart.createDataset(startingAmount, interestforGrowthChart, contributionforGrowthChart);
            } else if (Objects.equals(selectedOption2, "end") && Objects.equals(selectedOption3, "month")) {
                values[i] = (contributionAmount * ((Math.pow(1 + (returnRate / 1200), 12 * (i + 1)) - 1)
                        / (returnRate / 1200))) + startingAmount * (Math.pow(1 + (returnRate / (compoundRate * 100)), i + 1));
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