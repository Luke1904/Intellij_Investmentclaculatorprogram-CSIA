import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ui.RectangleInsets;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InvestmentGrowthChart {

    // ArrayList to store data for the chart
    static ArrayList<double[]> data = new ArrayList<>();

    // Constructor
    public InvestmentGrowthChart() {
    }

    // Method to create the chart
    private static JFreeChart createChart() {

        // Create a dataset from the stored data
        DefaultCategoryDataset dataset = inputDataset();

        // Create a stacked bar chart
        JFreeChart chart = ChartFactory.createStackedBarChart(
                null, // Chart title (set to null for no title)
                "Year", // X-axis label
                "Amount", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation
                false, // Include legend
                true, // Include tooltips
                false // Include URLs
        );

        // Add a subtitle to explain the stacked bars
        TextTitle subTitle = new TextTitle("  Starting Amount     Contributions     Interest  ");
        subTitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subTitle.setPadding(new RectangleInsets(5, 0, 15, 0));
        chart.addSubtitle(subTitle);

        // Customize the plot
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(UIManager.getColor("Panel.background")); // Set background color
        plot.setOutlinePaint(null); // Remove outline
        plot.setRangeGridlinesVisible(true); // Show gridlines
        plot.setRangeGridlinePaint(new Color(230, 230, 230)); // Set gridline color

        // Customize the renderer
        StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter()); // Use standard bar painter
        renderer.setShadowVisible(false); // Disable shadows
        renderer.setDrawBarOutline(false); // Disable bar outlines

        // Set colors for the stacked bars
        renderer.setSeriesPaint(0, new Color(55, 125, 255)); // Starting Amount
        renderer.setSeriesPaint(1, new Color(170, 0, 0)); // Contributions
        renderer.setSeriesPaint(2, new Color(85, 170, 85)); // Interest

        // Customize the domain axis (X-axis)
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.05); // Set lower margin
        domainAxis.setUpperMargin(0.05); // Set upper margin
        domainAxis.setCategoryMargin(0.25); // Set category margin

        // Customize the range axis (Y-axis)
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); // Use integer tick units
        rangeAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 11)); // Set tick label font
        rangeAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 12)); // Set axis label font
        rangeAxis.setRangeWithMargins(0, 2 * CalculatorWindow.getTotalInvestment()); // Set range with margins
        rangeAxis.setTickUnit(new org.jfree.chart.axis.NumberTickUnit(CalculatorWindow.getTotalInvestment() / 2)); // Set tick unit
        rangeAxis.setNumberFormatOverride(new java.text.DecimalFormat("$#,###")); // Format numbers as currency

        return chart;
    }

    // Method to add the first term (starting amount) to the data
    public static void addFirstTerm(double startingAmount) {
        if (!data.isEmpty()) {
            data.clear(); // Clear existing data if any
        }
        data.add(new double[]{data.size(), startingAmount, 0, 0}); // Add starting amount
    }

    // Method to add a new dataset entry (starting amount, interest, and contributions)
    public static void createDataset(double starting, double interest, double contribution) {
        data.add(new double[]{data.size(), starting, interest, contribution}); // Add new data
    }

    // Method to create a dataset for the chart
    private static DefaultCategoryDataset inputDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Populate the dataset with data from the ArrayList
        for (double[] row : data) {
            int year = (int) row[0]; // Year
            dataset.addValue((Number) row[1], "Starting Amount", Integer.toString(year)); // Starting Amount
            dataset.addValue((Number) row[2], "Interest", Integer.toString(year)); // Interest
            dataset.addValue((Number) row[3], "Contributions", Integer.toString(year)); // Contributions
        }

        return dataset;
    }

    // Method to get the chart panel for display
    public static ChartPanel getChartPanel() {
        JFreeChart chart = InvestmentGrowthChart.createChart(); // Create the chart
        chart.setBackgroundPaint(UIManager.getColor("Panel.background")); // Set background color
        return new ChartPanel(chart); // Return the chart panel
    }
}