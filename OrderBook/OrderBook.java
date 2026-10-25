package OrderBook;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import OrderBook.Order.OrderType;

import static OrderBook.Order.OrderType.*;

public class OrderBook {
    TreeMap<Double, Queue<Order>> buys=new TreeMap<>();
    TreeMap<Double, Queue<Order>> sells=new TreeMap<>();
    LinkedList<Trade> tradeLog=new LinkedList<>();
    boolean addOrder;

    public OrderBook() {
    }

    public void makeTrade(TreeMap<Double, Queue<Order>> record, Queue<Order> priceLevel, Order order){
        if (order.quantity >= priceLevel.peek().quantity) {
            Trade trade= new Trade(priceLevel.peek().price, priceLevel.peek().quantity);
            add(tradeLog, trade);
            order.updateQuantity(priceLevel.poll().quantity);
            if (priceLevel.size() == 0) record.remove(trade.price);
            if (order.quantity==0)      addOrder=false;
        }
        else {
            priceLevel.peek().updateQuantity(order.quantity);
            Trade trade= new Trade(priceLevel.peek().price, order.quantity);
            add(tradeLog, trade);
            order.quantity=0;
            addOrder=false;
        }
    }

    public void add(TreeMap<Double, Queue<Order>> record, Order order){
        if (record.containsKey(order.price)){
            record.get(order.price).add(order);
        }
        else {
            Queue<Order> price_Level = new LinkedList<>();
            price_Level.add(order);
            record.put(order.price, price_Level);
        }
    }

    public void add(LinkedList<Trade> record, Trade trade){
        record.addLast(trade);
    }

    public void orderIn(OrderType type, double price, int quantity){
        Order order=new Order(type, price, quantity);
        addOrder=true;
        if (order.orderType==BUY){
            while (!sells.isEmpty() && order.price>=sells.firstKey() && order.quantity>0) {
                makeTrade(sells, sells.firstEntry().getValue(), order);
            }
            if (addOrder) {
                add(buys, order);
            }
        }
        else {
            while (!buys.isEmpty() && order.price<=buys.lastKey() && order.quantity!=0){
                makeTrade(buys, buys.lastEntry().getValue(), order);
            }
            if (addOrder) {
                add(sells, order);
            }
        }
    }

    public void orderIn(OrderType type, int quantity){
        Order order=new Order(type, quantity);
        if (order.orderType==MARKETBUY) {
            while (!sells.isEmpty() && order.orderType == MARKETBUY && order.quantity != 0) {
                makeTrade(sells, sells.firstEntry().getValue(), order);
            }
            if (sells.isEmpty() && order.quantity!=0) {
                System.out.println("Market cleared, order not filled completely.\n"+ order.quantity+" remaining and canceled.");
            }
        }
        else if (order.orderType==MARKETSELL) {
            while (!buys.isEmpty() && order.orderType==MARKETSELL && order.quantity!=0){
                makeTrade(buys, buys.lastEntry().getValue(), order);
            }
            if (buys.isEmpty() && order.quantity!=0) {
                System.out.println("Market cleared, order not filled completely.\n"+ order.quantity+" remaining and canceled.");
            }
        }
        else    System.out.println("Price not defined, order cancelled");
    }


    public void displayBook(){
        System.out.println("Sells:");
        displayOrders(sells);
        System.out.println("Buys:" );
        displayOrders(buys);
    }

    public void displayTrades(){
        System.out.println("Trades: ");
        displayTrades(tradeLog);
    }

    public void displayOrders(Map<Double, Queue<Order>> orders){
        int quant;
        for (Queue<Order> priceLevel:orders.values()){
            quant=0;
            for (Order order:priceLevel) {
                quant+=order.quantity;
            }
            System.out.println("Price: "+priceLevel.peek().price+"\nQuantity: "+quant);
        }
    }

    public void displayTrades(LinkedList<Trade> orders){
        for (Trade trade:orders){
            System.out.println("Price: "+trade.price+"\nQuantity: "+trade.quantity+"\nTime: "+trade.tradeTime);
        }
    }

}
