package PricerCheck;

public class BlackScholesPricer extends Pricer{
    private double d1;
    private double d2;
    public BlackScholesPricer(double spotPrice, double riskFreeRate, double volatility, double timeToExpiry) {
        super(spotPrice, riskFreeRate, volatility, timeToExpiry);
    }
    // Standard normal CDF, N(x) — Zelen & Severo approximation, ~7 digit accuracy
    public double normalCDF(double x) {
        double t = 1.0 / (1.0 + 0.2316419 * Math.abs(x));
        double d = 0.3989422804014327 * Math.exp(-x * x / 2.0);
        double prob = d * t * (0.319381530+ t * (-0.356563782+ t * (1.781477937+ t * (-1.821255978+ t * 1.330274429))));
        prob = 1.0 - prob;
        if (x < 0) prob = 1.0 - prob;
        return prob;
    }
    public void price(double strike){
        d1=(Math.log(spotPrice/strike)+(riskFreeRate+0.5*volatility*volatility)*timeToExpiry)/(volatility*Math.sqrt(timeToExpiry));
        d2=d1-volatility*Math.sqrt(timeToExpiry);
            }

    public double priceCall(double strike){
        price(strike);
        double price=spotPrice*normalCDF(d1)-strike*Math.exp(-riskFreeRate*timeToExpiry)*normalCDF(d2);
        return price;
    }

    public double pricePut(double strike){
        price(strike);
        double price=strike*Math.exp(-riskFreeRate*timeToExpiry)*normalCDF(-d2)-spotPrice*normalCDF(-d1);
        return price;
    }
}
