package PricerCheck;

import static java.lang.Math.max;

public class MonteCarloPriceCalc {


    public double priceCall(double strike, int numSimulations, PriceModel pricer){
        double payoff=0;
        for (int i=0; i<numSimulations; i++){
            payoff+=max(0, pricer.simulatePrice()-strike);
        }
        payoff/=numSimulations;
        payoff*=Math.exp(-pricer.getRiskFreeRate()*pricer.getTimeToExpiry());
        return payoff;
    }

    public double pricePut(double strike, int numSimulations, PriceModel pricer){
        double payoff=0;
        for (int i=0; i<numSimulations; i++){
            payoff+=max(0, strike-pricer.simulatePrice());
        }
        payoff/=numSimulations;
        payoff*=Math.exp(-pricer.getRiskFreeRate()*pricer.getTimeToExpiry());
        return payoff;
    }

}
