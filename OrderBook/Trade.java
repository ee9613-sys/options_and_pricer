package OrderBook;

import java.time.Instant;
import java.util.Map;
import java.util.Queue;

public class Trade {
    public double price;
    public int quantity;
    public Instant tradeTime;

    public Trade(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
        this.tradeTime = Instant.now();
    }


}
