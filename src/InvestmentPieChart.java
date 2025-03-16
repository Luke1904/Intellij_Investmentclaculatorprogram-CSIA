import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class InvestmentPieChart {

    // Constructor
    public InvestmentPieChart() {
    }

    // Method to create the pie chart
    public static JFreeChart createChart() {
        // Create a dataset for the pie chart
        DefaultPieDataset dataset = createDataset();

        // Create the pie chart using the dataset
        JFreeChart chart = ChartFactory.createPieChart(
                "Investment Breakdown", // Chart title
                dataset, // Dataset
                true, // Include legend
                true, // Include tooltips
                false // Include URLs
        );

        // Customize the plot
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(UIManager.getColor("Panel.background")); // Set background color

        // Set colors for the pie chart sections
        plot.setSectionPaint("Starting Amount", new Color(55, 125, 255)); // Starting Amount
        plot.setSectionPaint("Contributions", new Color(85, 170, 85)); // Contributions
        plot.setSectionPaint("Interest", new Color(170, 0, 0)); // Interest

        // Use simple labels for the pie chart
        plot.setSimpleLabels(true);

        // Set a tooltip generator to display values and percentages
        plot.setToolTipGenerator(new StandardPieToolTipGenerator(
                "{0}: {2}", // Tooltip format (label: value)
                NumberFormat.getNumberInstance(), // Number format
                NumberFormat.getPercentInstance() // Percentage format
        ));

        return chart;
    }

    // Method to create the dataset for the pie chart
    public static DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Add values to the dataset
        dataset.setValue("Starting Amount", CalculatorWindow.getStartingAmount());
        dataset.setValue("Contributions", CalculatorWindow.getTotalContribution());
        dataset.setValue("Interest", CalculatorWindow.getTotalInterest());

        return dataset;
    }

    // Method to get the chart panel for display
    public  static ChartPanel getChartPanel() {
        JFreeChart chart = InvestmentPieChart.createChart(); // Create the chart
        chart.setBackgroundPaint(UIManager.getColor("Panel.background")); // Set background color
        return new ChartPanel(chart); // Return the chart panel
    }
}