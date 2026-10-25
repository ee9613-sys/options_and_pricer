package PricerCheck;

public class Main {
    public static void main(String[] args){
        GBMModel mcp = new GBMModel(100, 0.05, 0.2, 1);
        BlackScholesPricer bsp=new BlackScholesPricer(100, 0.05, 0.2, 1);
        MonteCarloPriceCalc payoff=new MonteCarloPriceCalc();
        System.out.println("Price for call: "+payoff.priceCall(100, 1000000, mcp));
        System.out.println("Price for put: "+payoff.pricePut(100, 1000000, mcp));
        System.out.println("CDF 0: "+bsp.normalCDF(0)+"\tCDF 1.96: "+bsp.normalCDF(1.96)+"\tCDF -1.96: "+bsp.normalCDF(-1.96)+"\n");
        System.out.println("Black-Scoles Call: "+bsp.priceCall(100));
        System.out.println("Black-Scoles Put: "+bsp.pricePut(100));
    }
}
