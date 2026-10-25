package OrderBook;


public class Order {

    public enum OrderType {
        BUY,
        SELL,
        MARKETBUY,
        MARKETSELL
    }

    public OrderType orderType;
    public double price;
    public int quantity;

    public Order(OrderType orderType, double price, int quantity) {
        this.orderType = orderType;
        this.price = price;
        this.quantity = quantity;
    }

    public Order(OrderType orderType, int quantity){
        this.orderType = orderType;
        this.quantity = quantity;
    }

    public void updateQuantity(int change){
        quantity-=change;
    }
}
