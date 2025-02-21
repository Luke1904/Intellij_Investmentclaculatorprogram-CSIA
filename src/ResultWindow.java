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
    JLabel label = new JLabel("Results");
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
                writer.write("Starting Amount: " + this.startingAmount + "\n");
                writer.write("Contribution Amount: " + this.contributionAmount + "\n");
                writer.write("Return rate: " + this.returnRate + "\n");
                writer.write("Investment interval: " + this.investmentInterval + "\n");
                writer.write("Compounding rate: " + this.selectedOption1 + "\n");
                writer.write("For the first option, you have chosen: " + this.selectedOption2 + "\n");
                writer.write("For the second option, you have chosen: " + this.selectedOption3 + "\n");
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
