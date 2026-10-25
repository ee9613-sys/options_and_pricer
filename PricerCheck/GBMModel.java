package PricerCheck;


import java.util.Random;

import static java.lang.Math.max;

public class GBMModel extends PriceModel {
    Random rng;

    public GBMModel(double spotPrice, double riskFreeRate, double volatility, double timeToExpiry) {
        super(spotPrice, riskFreeRate, volatility, timeToExpiry);
        this.rng=new Random();
    }

    @Override
    public double simulatePrice(){
        double z=rng.nextGaussian();
        double S_T=spotPrice*Math.exp(((riskFreeRate-(0.5*(Math.pow(volatility,2))))*timeToExpiry)+(volatility*Math.sqrt(timeToExpiry)*z));
        return S_T;
    }

}

