import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class FinancialGraph {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the dataset
            XYSeries stockPriceSeries = new XYSeries("Stock Price");
            stockPriceSeries.add(1, 100.0);
            stockPriceSeries.add(2, 105.5);
            stockPriceSeries.add(3, 103.2);
            stockPriceSeries.add(4, 110.7);
            stockPriceSeries.add(5, 115.3);

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(stockPriceSeries);

            // Create the chart
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Stock Price Trend",  // Chart title
                    "Time Period",        // X-Axis Label
                    "Price ($)",          // Y-Axis Label
                    dataset,               // Dataset
                    PlotOrientation.VERTICAL, // Plot Orientation
                    true,                  // Show Legend
                    true,                  // Use tooltips
                    false                  // Configure chart to generate URLs?
            );

            // Customize the chart's appearance
            XYPlot plot = chart.getXYPlot();

            // Customize line rendering
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setSeriesPaint(0, new Color(33, 105, 185));  // Deep blue color
            renderer.setSeriesStroke(0, new BasicStroke(2.5f));   // Thicker line

            // Add data points
            renderer.setSeriesLinesVisible(0, true);
            renderer.setSeriesShapesVisible(0, true);

            plot.setRenderer(renderer);

            // Set background and grid colors for aesthetic appeal
            plot.setBackgroundPaint(Color.WHITE);
            plot.setDomainGridlinePaint(new Color(220, 220, 220));
            plot.setRangeGridlinePaint(new Color(220, 220, 220));

            // Create the panel
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(560, 370));

            // Create a frame and add the chart
            JFrame frame = new JFrame("Aesthetic Financial Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(chartPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
