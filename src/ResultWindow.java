import org.jfree.chart.ChartPanel;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResultWindow implements ActionListener {

    // Declare GUI components
    JFrame frame = new JFrame();
    JPanel panel = new LinePanel();
    JLabel label = new JLabel("Results"), label1 = new JLabel("Total contributions"),
            label2 = new JLabel("Total interest"),
            label3 = new JLabel("Total investment"),
            label4 = new JLabel("ROI");
    JTextField textField = new JTextField(),
            textField1 = new JTextField(),
            textField2 = new JTextField(),
            textField3 = new JTextField();
    JButton createSave = new JButton("Create save"), goBackToCalculator = new JButton("Go back to calculator"),
            infoButton1 = new JButton("i"),
            infoButton2 = new JButton("i"),
            infoButton3 = new JButton("i"),
            infoButton4 = new JButton("i");
    public double startingAmount, contributionAmount, returnRate;
    public int investmentInterval, buttonDimx = 21, buttonDimY = 21;
    public String selectedOption1, selectedOption2, selectedOption3;
    public double[] values;

    // Constructor to initialize the result window
    ResultWindow(double startingAmount, double contributionAmount, double returnRate, int investmentInterval, String selectedOption1, String selectedOption2, String selectedOption3, double[] values) {
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        this.startingAmount = startingAmount;
        this.contributionAmount = contributionAmount;
        this.returnRate = returnRate;
        this.investmentInterval = investmentInterval;
        this.selectedOption1 = selectedOption1;
        this.selectedOption2 = selectedOption2;
        this.selectedOption3 = selectedOption3;
        this.values = values;

        // Panel positioning and sizing
        int xPanel = ss.width / 2 - ss.width / 4 + ss.width / 100; // X-coordinate of the panel: centered horizontally with a slight offset
        int yPanel = ss.height / 10; // Y-coordinate of the panel: 1/10 of the screen height from the top
        int wPanel = ss.width / 2; // Width of the panel: half of the screen width
        int hPanel = ss.height - ss.height / 6; // Height of the panel: 5/6 of the screen height

// Label positioning and sizing
        int xLabel = ss.width / 2 - wPanel / 24; // X-coordinate of the label: centered horizontally with a slight offset
        int yLabel = ss.height / 2 - 2 * (ss.height / 6); // Y-coordinate of the label: vertically centered with an offset
        int wLabel = wPanel / 3; // Width of the label: 1/3 of the panel's width
        int hLabel = hPanel / 14; // Height of the label: 1/14 of the panel's height

// Investment Growth Chart positioning and sizing
        int xInvestmentGrowthChart = xPanel + wPanel / 16; // X-coordinate of the Investment Growth Chart: offset from the panel's left edge
        int yInvestmentGrowthChart = yLabel + 2 * hLabel; // Y-coordinate of the Investment Growth Chart: below the label
        int wInvestmentCharts = wPanel / 3; // Width of the investment charts: 1/3 of the panel's width
        int hInvestmentCharts = hPanel / 4; // Height of the investment charts: 1/4 of the panel's height

// Investment Pie Chart positioning
        int xInvestmentPieChart = xInvestmentGrowthChart + 3 * wInvestmentCharts / 2; // X-coordinate of the Investment Pie Chart: offset from the Investment Growth Chart
        int yInvestmentPieChart = yInvestmentGrowthChart; // Y-coordinate of the Investment Pie Chart: same as the Investment Growth Chart

// Labels and Text Fields positioning and sizing
        int xLabelsAndTextFields = xInvestmentGrowthChart; // X-coordinate of labels and text fields: same as the Investment Growth Chart
        int wLabelsAndTextFields = wPanel / 4; // Width of labels and text fields: 1/4 of the panel's width
        int hLabelsAndTextFields = hPanel / 35; // Height of labels and text fields: 1/35 of the panel's height

// Label and Text Field vertical positions
        int yLabel1 = ss.height / 2 + hPanel / 10; // Y-coordinate of Label 1: below the vertical center of the screen
        int yTextField = yLabel1 + hLabelsAndTextFields; // Y-coordinate of the first text field: below Label 1
        int yLabel2 = yTextField + 3 * hLabelsAndTextFields / 2; // Y-coordinate of Label 2: below the first text field
        int yTextField1 = yLabel2 + hLabelsAndTextFields; // Y-coordinate of the second text field: below Label 2
        int yLabel3 = yTextField1 + 3 * hLabelsAndTextFields / 2; // Y-coordinate of Label 3: below the second text field
        int yTextField2 = yLabel3 + hLabelsAndTextFields; // Y-coordinate of the third text field: below Label 3
        int yLabel4 = yTextField2 + 3 * hLabelsAndTextFields / 2; // Y-coordinate of Label 4: below the third text field
        int yTextField3 = yLabel4 + hLabelsAndTextFields; // Y-coordinate of the fourth text field: below Label 4

// Information Buttons positioning
        int xInformationButtons = xLabelsAndTextFields + 3 * wLabelsAndTextFields / 5; // X-coordinate of Information Buttons: offset from labels and text fields
        int yInfoButton1 = yTextField; // Y-coordinate of Info Button 1: same as the first text field
        int yInfoButton2 = yTextField1; // Y-coordinate of Info Button 2: same as the second text field
        int yInfoButton3 = yTextField2; // Y-coordinate of Info Button 3: same as the third text field
        int yInfoButton4 = yTextField3; // Y-coordinate of Info Button 4: same as the fourth text field

// Go Back Button positioning
        int xGoBackButton = ss.width / 2 + wPanel / 6; // X-coordinate of the Go Back Button: offset from the center of the screen
        int yGoBackButton = ss.height / 2 + hPanel / 5; // Y-coordinate of the Go Back Button: below the vertical center of the screen

// Save Button positioning
        int xSaveButton = xGoBackButton + wLabelsAndTextFields / 4; // X-coordinate of the Save Button: offset from the Go Back Button
        int ySaveButton = ss.height / 2 + hPanel / 8; // Y-coordinate of the Save Button: below the vertical center of the screen


        // Set up the main label for the results
        label.setBounds(xLabel, yLabel, wLabel, hLabel);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label);

        // Set up label and text field for total contributions
        label1.setBounds(xLabelsAndTextFields, yLabel1, wLabelsAndTextFields, hLabelsAndTextFields);
        frame.add(label1);

        textField.setBounds(xLabelsAndTextFields, yTextField, wLabelsAndTextFields / 2, hLabelsAndTextFields);
        textField.setCaretColor(new Color(0, 0, 0, 0));
        textField.setText(String.format("%.2f", CalculatorWindow.getTotalContribution()) + "$");
        textField.setEditable(false);
        frame.add(textField);

        // Set up info button for total contributions
        infoButton1.setBounds(xInformationButtons, yInfoButton1, buttonDimx, buttonDimY);
        infoButton1.setToolTipText("<html>"
                + "This is the value of the total contributions <br>"
                + "that you had over the investment period."
                + "<html>");
        infoButton1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton1.setFocusable(false);
        frame.add(infoButton1);

        // Set up label and text field for total interest
        label2.setBounds(xLabelsAndTextFields, yLabel2, wLabelsAndTextFields, hLabelsAndTextFields);
        frame.add(label2);

        textField1.setBounds(xLabelsAndTextFields, yTextField1, wLabelsAndTextFields / 2, hLabelsAndTextFields);
        textField1.setCaretColor(new Color(0, 0, 0, 0));
        textField1.setText(String.format("%.2f", CalculatorWindow.getTotalInterest()) + "$");
        textField1.setEditable(false);
        frame.add(textField1);

        // Set up info button for total interest
        infoButton2.setBounds(xInformationButtons, yInfoButton2, buttonDimx, buttonDimY);
        infoButton2.setToolTipText("<html>"
                + "This is the value of the total interest <br>"
                + "which was accumulated throughout the compound periods ."
                + "<html>");
        infoButton2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton2.setFocusable(false);
        frame.add(infoButton2);

        // Set up label and text field for total investment
        label3.setBounds(xLabelsAndTextFields, yLabel3, wLabelsAndTextFields, hLabelsAndTextFields);
        frame.add(label3);

        textField2.setBounds(xLabelsAndTextFields, yTextField2, wLabelsAndTextFields / 2, hLabelsAndTextFields);
        textField2.setCaretColor(new Color(0, 0, 0, 0));
        textField2.setText(String.format("%.2f", CalculatorWindow.getTotalInvestment()) + "$");
        textField2.setEditable(false);
        frame.add(textField2);

        // Set up info button for total investment
        infoButton3.setBounds(xInformationButtons, yInfoButton3, buttonDimx, buttonDimY);
        infoButton3.setToolTipText("<html>"
                + "This is the value of the total investment <br>"
                + "which is composed from the starting amount, <br>"
                + "accumulated interest, and contribution amount."
                + "<html>");
        infoButton3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton3.setFocusable(false);
        frame.add(infoButton3);

        // Set up label and text field for ROI
        label4.setBounds(xLabelsAndTextFields, yLabel4, wLabelsAndTextFields, hLabelsAndTextFields);
        frame.add(label4);

        textField3.setBounds(xLabelsAndTextFields, yTextField3, wLabelsAndTextFields / 2, hLabelsAndTextFields);
        textField3.setCaretColor(new Color(0, 0, 0, 0));
        textField3.setText(String.format("%.2f", CalculatorWindow.getROI()) + "%");
        textField3.setEditable(false);
        frame.add(textField3);

        // Set up info button for ROI
        infoButton4.setBounds(xInformationButtons, yInfoButton4, buttonDimx, buttonDimY);
        infoButton4.setToolTipText("<html>"
                + "This is the value of the return on investment <br>"
                + "which is the final value of the final investment divided <br>."
                + "by the starting amount and accumulated interest"
                + "<html>");
        infoButton4.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton4.setFocusable(false);
        frame.add(infoButton4);

        // Set up button to go back to the calculator
        goBackToCalculator.setBounds(xGoBackButton, yGoBackButton, wLabelsAndTextFields, 3 * hLabelsAndTextFields / 2);
        goBackToCalculator.setFocusable(false);
        goBackToCalculator.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(goBackToCalculator);

        // Set up button to create a save file
        createSave.setBounds(xSaveButton, ySaveButton, wLabelsAndTextFields / 2, 3 * hLabelsAndTextFields / 2);
        createSave.setFocusable(false);
        createSave.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(createSave);

        // Set up the main frame
        frame.setLayout(null);
        frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setTitle("Result Window");
        frame.getContentPane().setBackground(new Color(203, 203, 203));

        // Add the investment growth chart to the frame
        ChartPanel s = InvestmentGrowthChart.getChartPanel();
        s.setBackground(UIManager.getColor("Panel.background"));
        s.setBounds(xInvestmentGrowthChart, yInvestmentGrowthChart, wInvestmentCharts, hInvestmentCharts);
        frame.add(s);

        // Add the investment pie chart to the frame
        ChartPanel p = InvestmentPieChart.getChartPanel();
        p.setBackground(UIManager.getColor("Panel.background"));
        p.setBounds(xInvestmentPieChart, yInvestmentPieChart, wInvestmentCharts, hInvestmentCharts);
        frame.add(p);

        // Action listener for the create save button
        createSave.addActionListener(e -> {
            // Get the user's home directory
            String homeDirectory = System.getProperty("user.home");
            String directory = homeDirectory + "\\Desktop\\Saves\\";
            String baseFileName = "investment_data";
            String extension = ".txt";
            File file;
            int counter = 0;

            // Generate a unique file name
            do {
                String fileName = baseFileName + (counter == 0 ? "" : counter) + extension;
                file = new File(directory + fileName);
                counter++;
            } while (file.exists());

            // Write the investment data to the file
            try {
                FileWriter writer = new FileWriter(file);
                writer.write("Starting Amount: " + String.format("%.2f", this.startingAmount) + "$" + "\n");
                writer.write("Contribution Amount: " + String.format("%.2f", this.contributionAmount) + "$" + "\n");
                writer.write("Return rate: " + String.format("%.2f", this.returnRate) + "%" + "\n");
                writer.write("Investment interval: " + this.investmentInterval + " years" + "\n");
                writer.write("Compounding rate: " + this.selectedOption1 + "\n");
                writer.write("For the first option, you have chosen: " + this.selectedOption2 + "\n");
                writer.write("For the second option, you have chosen: " + this.selectedOption3 + "\n");
                writer.write("Total contribution: " + String.format("%.2f", CalculatorWindow.getTotalContribution()) + "$" + "\n");
                writer.write("Total interest: " + String.format("%.2f", CalculatorWindow.getTotalInterest()) + "$" + "\n");
                writer.write("Total investment: " + String.format("%.2f", CalculatorWindow.getTotalInvestment()) + "$" + "\n");
                writer.write("Total Return on Investment: " + String.format("%.2f", CalculatorWindow.getROI()) + "%" + "\n");
                writer.close();
            } catch (IOException a) {
                a.printStackTrace();
            }
        });

        // Action listener for the go back to calculator button
        goBackToCalculator.addActionListener(e -> {
            frame.dispose();
            CalculatorWindow calculatorWindow = new CalculatorWindow();
        });

        // Set up the main panel
        panel.setBounds(xPanel, yPanel, wPanel, hPanel);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(panel);
    }

    // ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}