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

    JFrame frame = new JFrame();
    JPanel panel = new LinePanel();
    JLabel label = new JLabel("Results"), label1 = new JLabel("Total contributions"),
            label2 = new JLabel("Total interest"),
            label3 = new JLabel("Total investment"),
            label4 = new JLabel("Total Return on Investment");
    JTextField textField = new JTextField(),
            textField1 = new JTextField(),
            textField2 = new JTextField(),
            textField3 = new JTextField();
    JButton createSave = new JButton("Create save"), goBackToCalculator = new JButton("Go back to calculator");
    public double startingAmount, contributionAmount, returnRate;
    public int investmentInterval;
    public String selectedOption1, selectedOption2, selectedOption3;
    public double[] values;

    ResultWindow(double startingAmount, double contributionAmount, double returnRate, int investmentInterval, String selectedOption1, String selectedOption2, String selectedOption3, double[] values){
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        this.startingAmount = startingAmount;
        this.contributionAmount = contributionAmount;
        this.returnRate = returnRate;
        this.investmentInterval = investmentInterval;
        this.selectedOption1 = selectedOption1;
        this.selectedOption2 = selectedOption2;
        this.selectedOption3 = selectedOption3;
        this.values = values;

        label.setBounds(711, 146, 300, 40);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label);

        label1.setBounds(483, 514, 100, 30);
        frame.add(label1);

        textField.setBounds(483, 540, 100, 20);
        textField.setText(String.format("%.2f",CalculatorWindow.getTotalContribution()));
        textField.setEditable(false);
        frame.add(textField);

        label2.setBounds(483, 564, 100, 30);
        frame.add(label2);

        textField1.setBounds(483, 590, 100, 20);
        textField1.setText(String.format("%.2f",CalculatorWindow.getTotalInterest()));
        textField1.setEditable(false);
        frame.add(textField1);

        label3.setBounds(483, 614, 100, 30);
        frame.add(label3);

        textField2.setBounds(483, 640, 100, 20);
        textField2.setText(String.format("%.2f",CalculatorWindow.getTotalInvestment()));
        textField2.setEditable(false);
        frame.add(textField2);

        label4.setBounds(483, 664, 300, 30);
        frame.add(label4);

        textField3.setBounds(483, 690, 100, 20);
        textField3.setText(String.format("%.2f",CalculatorWindow.getROI()));
        textField3.setEditable(false);
        frame.add(textField3);

        goBackToCalculator.setBounds(907, 564, 150, 30);
        goBackToCalculator.setFocusable(false);
        goBackToCalculator.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(goBackToCalculator);

        createSave.setBounds(917, 514, 130, 30);
        createSave.setFocusable(false);
        createSave.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        frame.add(createSave);

        frame.setLayout(null);
        frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setTitle("Result Window");
        frame.getContentPane().setBackground(new Color(203, 203, 203));

        ChartPanel s = InvestmentGrowthChart.getChartPanel();
        s.setBackground(UIManager.getColor("Panel.background"));
        s.setBounds(450, 247, 250, 200);
        frame.add(s);

        ChartPanel p = InvestmentPieChart.getChartPanel();
        p.setBackground(UIManager.getColor("Panel.background"));
        p.setBounds(820, 247, 240, 180);
        frame.add(p);


        createSave.addActionListener(e -> {
            String directory = "X:\\Saves\\";
            String baseFileName = "investment_data";
            String extension = ".txt";
            File file;
            int counter = 0;

            do {
                String fileName = baseFileName + (counter == 0 ? "" : counter) + extension;
                file = new File(directory + fileName);
                counter++;
            } while (file.exists());


            try {
                FileWriter writer = new FileWriter(file);
                writer.write("Starting Amount: " + String.format("%.2f", this.startingAmount) + "\n");
                writer.write("Contribution Amount: " + String.format("%.2f", this.contributionAmount) + "\n");
                writer.write("Return rate: " + String.format("%.2f", this.returnRate) + "\n");
                writer.write("Investment interval: " + this.investmentInterval + "\n");
                writer.write("Compounding rate: " + this.selectedOption1 + "\n");
                writer.write("For the first option, you have chosen: " + this.selectedOption2 + "\n");
                writer.write("For the second option, you have chosen: " + this.selectedOption3 + "\n");
                writer.write("Total contribution: " + String.format("%.2f", CalculatorWindow.getTotalContribution()) + "\n");
                writer.write("Total interest: " + String.format("%.2f", CalculatorWindow.getTotalInterest()) + "\n");
                writer.write("Total investment: " + String.format("%.2f", CalculatorWindow.getTotalInvestment()) + "\n");
                writer.write("Total Return on Investment: " + String.format("%.2f", CalculatorWindow.getROI()) + "\n");
                writer.close();
            } catch (IOException a) {
                a.printStackTrace();
            }

        });

        goBackToCalculator.addActionListener(e ->{
            frame.dispose();
            CalculatorWindow calculatorWindow = new CalculatorWindow();
        });



        panel.setBounds(ss.width / 2 - 350, ss.height / 2 - 350, 700, 700);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
