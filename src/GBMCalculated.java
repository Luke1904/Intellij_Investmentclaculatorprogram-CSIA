

/*import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GBMCalculated {
    public int startingAmount;
    public int contributionAmount;
    public String selectedOption3; // Stock// Commodity
    public String selectedOption1; // Contribution frequency (monthly/annually)
    public String selectedOption4;

    // Constructor
    public GBMCalculated(int startingAmount,
                         int contributionAmount,
                         String selectedOption3,
                         String selectedOption1,
                         String selectedOption4) {
        this.startingAmount = startingAmount;
        this.contributionAmount = contributionAmount;
        this.selectedOption3 = selectedOption3;
        this.selectedOption1 = selectedOption1;
        this.selectedOption4 = selectedOption4;

        scheduleDailyUpdates();
    }

    // GBM calculation
    public double calculateFinalInvestment(double drift, double volatility) {
        int years = 10;
        int periods = this.selectedOption1.equalsIgnoreCase("monthly") ? years * 12 : years;
        double dt = 1.0 / periods;
        double investment = this.startingAmount;

        for (int i = 0; i < periods; i++) {
            double randomShock = Math.random() * volatility * Math.sqrt(dt);
            investment *= Math.exp((drift - 0.5 * volatility * volatility) * dt + randomShock);

            if (this.selectedOption4.equalsIgnoreCase("beginning") || this.selectedOption4.equalsIgnoreCase("end")) {
                investment += this.contributionAmount;
            }
        }

        return investment;
    }

    // Calculate ROI
    public double calculateROI(double finalValue) {
        double totalContributions = this.contributionAmount * (this.selectedOption1.equalsIgnoreCase("monthly") ? 12 : 1) * 10;
        return ((finalValue - this.startingAmount - totalContributions) / (this.startingAmount + totalContributions)) * 100;
    }

    // Total dividends (dummy calculation, modify as needed)
    public double calculateDividends(double finalValue) {
        return finalValue * 0.02; // Assume 2% of the final value is dividends
    }

    // Fetch stock price using Yahoo Finance API
    public BigDecimal fetchStockPrice(String ticker) {
        try {
            Stock stock = YahooFinance.get(ticker);
            return stock.getQuote().getPrice();
        } catch (Exception e) {
            System.err.println("Error fetching stock price for " + ticker);
            return BigDecimal.ZERO;
        }

    }

    // Update calculations every 24 hours
    private void scheduleDailyUpdates() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Updating investment data...");
            BigDecimal stockPrice = fetchStockPrice(this.selectedOption3); // Example: update based on selected stock
            System.out.println("Current price of " + this.selectedOption3 + ": " + stockPrice);

            double drift = 0.05; // Assume 5% average annual return
            double volatility = 0.2; // Assume 20% volatility

            double finalInvestment = calculateFinalInvestment(drift, volatility);
            System.out.println("Updated final investment value: " + finalInvestment);
        }, 0, 24, TimeUnit.HOURS);
    }
}
*/