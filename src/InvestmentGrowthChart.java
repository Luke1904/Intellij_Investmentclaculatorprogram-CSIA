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


public class InvestmentGrowthChart{

    static ArrayList<double[]> data = new ArrayList<>();

    public InvestmentGrowthChart() {

    }

    private static JFreeChart createChart() {

        DefaultCategoryDataset dataset = inputDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createStackedBarChart(
                null,           // chart title (set to null, we'll add a subtitle for legend)
                "Year",         // domain axis label
                "Amount",       // range axis label
                dataset,        // data
                PlotOrientation.VERTICAL,  // orientation
                false,          // include legend (we'll create custom legend)
                true,           // tooltips
                false           // URLs
        );

        // Add subtitle as legend
        TextTitle subTitle = new TextTitle("  Starting Amount     Contributions     Interest  ");
        subTitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subTitle.setPadding(new RectangleInsets(5, 0, 15, 0));
        chart.addSubtitle(subTitle);

        // Configure plot
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(UIManager.getColor("Panel.background"));
        plot.setOutlinePaint(null);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(new Color(230, 230, 230));

        // Configure renderer
        StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter()); // Remove glossy effect
        renderer.setShadowVisible(false);
        renderer.setDrawBarOutline(false);

        // Set colors matching the image
        renderer.setSeriesPaint(0, new Color(55, 125, 255));  // Blue for Starting Amount
        renderer.setSeriesPaint(1, new Color(170, 0, 0));   // Green for Contributions
        renderer.setSeriesPaint(2, new Color(85, 170, 85));     // Dark red for Interest

        // Configure domain axis (X axis)
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.05);
        domainAxis.setUpperMargin(0.05);
        domainAxis.setCategoryMargin(0.25);

        // Configure range axis (Y axis)
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 11));
        rangeAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
        rangeAxis.setRangeWithMargins(0, 2 * CalculatorWindow.getTotalInvestment());
        rangeAxis.setTickUnit(new org.jfree.chart.axis.NumberTickUnit(50000));
        rangeAxis.setNumberFormatOverride(new java.text.DecimalFormat("$#,###"));

        return chart;
    }

    public static void addFirstTerm(double startingAmount){
        if(!data.isEmpty()){
            data.clear();
        }
        data.add( new double[]{data.size(), startingAmount, 0, 0});
    }
    public static void createDataset(double starting, double interest, double contribution) {
        data.add( new double[]{data.size(), starting, interest, contribution});
    }
    private static DefaultCategoryDataset inputDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (double[] row : data) {
            int year = (int) row[0];
            dataset.addValue((Number) row[1], "Starting Amount", Integer.toString(year));
            dataset.addValue((Number) row[2], "Interest", Integer.toString(year));
            dataset.addValue((Number) row[3], "Contributions", Integer.toString(year));
        }

        return dataset;
    }
    public static ChartPanel getChartPanel() {
        JFreeChart chart = InvestmentGrowthChart.createChart();
        chart.setBackgroundPaint(UIManager.getColor("Panel.background"));
        return new ChartPanel(chart);
    }
}
