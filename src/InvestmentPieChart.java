import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class InvestmentPieChart {
    public InvestmentPieChart() {
    }

    private static JFreeChart createChart() {
        DefaultPieDataset dataset = createDataset();

        // Create the pie chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Investment Breakdown",  // Chart title
                dataset,                  // Dataset
                true,                      // Include legend
                true,                      // Tooltips
                false                      // URLs
        );

        // Customize plot
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(UIManager.getColor("Panel.background"));
        plot.setSectionPaint("Starting Amount", new Color(55, 125, 255));
        plot.setSectionPaint("Contributions", new Color(85, 170, 85));
        plot.setSectionPaint("Interest", new Color(170, 0, 0));
        plot.setSimpleLabels(true);

        return chart;
    }

    private static DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Sample values (Modify as needed)
        double startingAmount = 20000;
        double contributions = 100000;
        double interest = 75000;

        dataset.setValue("Starting Amount", startingAmount);
        dataset.setValue("Contributions", contributions);
        dataset.setValue("Interest", interest);

        return dataset;
    }

    public static ChartPanel getChartPanel() {
        JFreeChart chart = InvestmentPieChart.createChart();
        chart.setBackgroundPaint(UIManager.getColor("Panel.background"));
        return new ChartPanel(chart);
    }
}
