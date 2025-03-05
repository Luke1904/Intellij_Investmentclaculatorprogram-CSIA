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
    public InvestmentPieChart() {
    }

    public static JFreeChart createChart() {
        DefaultPieDataset dataset = createDataset();


        JFreeChart chart = ChartFactory.createPieChart(
                "Investment Breakdown",
                dataset,
                true,
                true,
                false
        );


        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(UIManager.getColor("Panel.background"));
        plot.setSectionPaint("Starting Amount", new Color(55, 125, 255));
        plot.setSectionPaint("Contributions", new Color(85, 170, 85));
        plot.setSectionPaint("Interest", new Color(170, 0, 0));
        plot.setSimpleLabels(true);

        plot.setToolTipGenerator(new StandardPieToolTipGenerator("{0}: {2}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));

        return chart;
    }

    public static DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();


        double startingAmount = CalculatorWindow.getStartingAmount();
        double contributions = CalculatorWindow.getTotalContribution();
        double interest = CalculatorWindow.getTotalInterest();

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
