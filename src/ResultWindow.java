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

        // Set up the main label for the results
        label.setBounds(711, 146, 300, 40);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label);

        // Set up label and text field for total contributions
        label1.setBounds(483, 514, 200, 30);
        frame.add(label1);

        textField.setBounds(483, 540, 100, 20);
        textField.setCaretColor(new Color(0, 0, 0, 0));
        textField.setText(String.format("%.2f", CalculatorWindow.getTotalContribution()) + "$");
        textField.setEditable(false);
        frame.add(textField);

        // Set up info button for total contributions
        infoButton1.setBounds(613, 540, buttonDimx, buttonDimY);
        infoButton1.setToolTipText("<html>"
                + "This is the value of the total contributions <br>"
                + "that you had over the investment period."
                + "<html>");
        infoButton1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton1.setFocusable(false);
        frame.add(infoButton1);

        // Set up label and text field for total interest
        label2.setBounds(483, 564, 100, 30);
        frame.add(label2);

        textField1.setBounds(483, 590, 100, 20);
        textField1.setCaretColor(new Color(0, 0, 0, 0));
        textField1.setText(String.format("%.2f", CalculatorWindow.getTotalInterest()) + "$");
        textField1.setEditable(false);
        frame.add(textField1);

        // Set up info button for total interest
        infoButton2.setBounds(613, 590, buttonDimx, buttonDimY);
        infoButton2.setToolTipText("<html>"
                + "This is the value of the total interest <br>"
                + "which was accumulated throughout the compound periods ."
                + "<html>");
        infoButton2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton2.setFocusable(false);
        frame.add(infoButton2);

        // Set up label and text field for total investment
        label3.setBounds(483, 614, 100, 30);
        frame.add(label3);

        textField2.setBounds(483, 640, 100, 20);
        textField2.setCaretColor(new Color(0, 0, 0, 0));
        textField2.setText(String.format("%.2f", CalculatorWindow.getTotalInvestment()) + "$");
        textField2.setEditable(false);
        frame.add(textField2);

        // Set up info button for total investment
        infoButton3.setBounds(613, 640, buttonDimx, buttonDimY);
        infoButton3.setToolTipText("<html>"
                + "This is the value of the total investment <br>"
                + "which is composed from the starting amount, <br>"
                + "accumulated interest, and contribution amount."
                + "<html>");
        infoButton3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton3.setFocusable(false);
        frame.add(infoButton3);

        // Set up label and text field for ROI
        label4.setBounds(483, 664, 300, 30);
        frame.add(label4);

        textField3.setBounds(483, 690, 100, 20);
        textField3.setCaretColor(new Color(0, 0, 0, 0));
        textField3.setText(String.format("%.2f", CalculatorWindow.getROI()) + "%");
        textField3.setEditable(false);
        frame.add(textField3);

        // Set up info button for ROI
        infoButton4.setBounds(613, 690, buttonDimx, buttonDimY);
        infoButton4.setToolTipText("<html>"
                + "This is the value of the return on investment <br>"
                + "which is the final value of the final investment divided <br>."
                + "by the starting amount and accumulated interest"
                + "<html>");
        infoButton4.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(Color.gray)));
        infoButton4.setFocusable(false);
        frame.add(infoButton4);

        // Set up button to go back to the calculator
        goBackToCalculator.setBounds(907, 564, 150, 30);
        goBackToCalculator.setFocusable(false);
        goBackToCalculator.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(goBackToCalculator);

        // Set up button to create a save file
        createSave.setBounds(917, 514, 130, 30);
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
        s.setBounds(450, 247, 250, 200);
        frame.add(s);

        // Add the investment pie chart to the frame
        ChartPanel p = InvestmentPieChart.getChartPanel();
        p.setBackground(UIManager.getColor("Panel.background"));
        p.setBounds(820, 247, 240, 180);
        frame.add(p);

        // Action listener for the create save button
        createSave.addActionListener(e -> {
            String directory = "X:\\Saves\\";
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
        panel.setBounds(ss.width / 2 - 350, ss.height / 2 - 350, 700, 700);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(panel);
    }

    // ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}