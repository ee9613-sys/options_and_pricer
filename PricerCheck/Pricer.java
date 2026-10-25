package PricerCheck;

public abstract class Pricer {
    protected double spotPrice;
    protected double riskFreeRate;
    protected double volatility;
    protected double timeToExpiry;

    public Pricer(double spotPrice, double riskFreeRate, double volatility, double timeToExpiry) {
        this.spotPrice = spotPrice;
        this.riskFreeRate = riskFreeRate;
        this.volatility = volatility;
        this.timeToExpiry = timeToExpiry;
    }


}
