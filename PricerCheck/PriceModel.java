package PricerCheck;

public abstract class PriceModel extends Pricer{

    public PriceModel(double spotPrice, double riskFreeRate, double volatility, double timeToExpiry){
        super(spotPrice, riskFreeRate, volatility, timeToExpiry);
    }

    public abstract double simulatePrice();

    public double getRiskFreeRate(){
        return riskFreeRate;
    }

    public double getTimeToExpiry(){
        return timeToExpiry;
    }

}
