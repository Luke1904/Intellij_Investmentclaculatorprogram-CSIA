import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResultWindow implements ActionListener {

    JFrame frame = new JFrame();
    JButton createSave = new JButton("Create save");
    public double startingAmount, contributionAmount, returnRate;
    public int investmentInterval;
    public String selectedOption1, selectedOption2, selectedOption3;
    public double[] values;

    ResultWindow(double startingAmount, double contributionAmount, double returnRate, int investmentInterval, String selectedOption1, String selectedOption2, String selectedOption3, double[] values){
        this.startingAmount = startingAmount;
        this.contributionAmount = contributionAmount;
        this.returnRate = returnRate;
        this.investmentInterval = investmentInterval;
        this.selectedOption1 = selectedOption1;
        this.selectedOption2 = selectedOption2;
        this.selectedOption3 = selectedOption3;
        this.values = values;

        createSave.setBounds(500, 500, 100, 20);
        createSave.setFocusable(false);
        frame.add(createSave);

        frame.setLayout(null);
        frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setTitle("Result Window");
        frame.getContentPane().setBackground(new Color(203, 203, 203));

        InvestmentGrowthChart chart = new InvestmentGrowthChart("Graph1");
        ChartPanel p = chart.getChartPanel();
        p.setBounds(100, 100, 500, 200);
        p.setVisible(true);
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
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
