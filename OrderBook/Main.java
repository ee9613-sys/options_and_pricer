package OrderBook;

import static OrderBook.Order.OrderType.*;

public class Main {
    public static void main(String[] args){
        OrderBook book = new OrderBook();

        // --- Build a resting book, no crossing ---
        book.orderIn(SELL, 105.0, 10);
        book.orderIn(SELL, 103.0, 5);
        book.orderIn(SELL, 102.0, 8);
        book.orderIn(BUY,  98.0, 10);
        book.orderIn(BUY, 100.0, 5);
        book.orderIn(BUY, 100.0, 3);   // second order at 100 -> queue of 2 at this level
        System.out.println("=== Initial book ===");
        book.displayBook();
        // Sells: 102(8) 103(5) 105(10) | Buys: 98(10) 100(8). Spread, no trades yet.

        // --- Limit buy crossing one level partially, at the RESTING price ---
        book.orderIn(BUY, 102.0, 3);
        System.out.println("=== After BUY 102 x3 ===");
        book.displayBook();
        book.displayTrades();
        // Trade: 3 @ 102 (maker price). Sell 102 now 5. Nothing rests.

        // --- Limit buy sweeping multiple levels ---
        book.orderIn(BUY, 104.0, 10);
        System.out.println("=== After BUY 104 x10 ===");
        book.displayBook();
        book.displayTrades();
        // Eats 102(5) then 103(5) = 10. Both levels gone. Trades: 5@102, 5@103. Sells: 105 only.

        // --- Limit sell that crosses the buy side, price improvement to taker ---
        book.orderIn(SELL, 99.0, 20);
        System.out.println("=== After SELL 99 x20 ===");
        book.displayBook();
        book.displayTrades();
        // Best buy is 100(8): fills 8 @ 100, then 98(10): fills 10 @ 98 -> wait, sell limit is 99,
        // 98 < 99 so it will NOT cross 98. So 8 filled @100, 12 remain, rest at sell 99.
        // Trades add: 8@100. Buys: 98(10). Sells: 99(12), 105(10).

        // --- Market buy: ignores price, sweeps cheapest sells until filled ---
        book.orderIn(MARKETBUY, 15);
        System.out.println("=== After MARKETBUY x15 ===");
        book.displayBook();
        book.displayTrades();
        // Sweeps sell 99(12) fully @99, then sell 105 fills 3 @105. 15 done.
        // Trades add: 12@99, 3@105. Sells: 105(7). Buys: 98(10).

        // --- Market sell larger than the book: partial fill + cancel message ---
        book.orderIn(MARKETSELL, 50);
        System.out.println("=== After MARKETSELL x50 ===");
        book.displayBook();
        book.displayTrades();
        // Only buy is 98(10): fills 10 @98, then book empty -> prints "Market cleared..." with 40 canceled.
        // Buys empty. Sells: 105(7).

        // --- Edge: market order with no matching side ---
        book.orderIn(MARKETBUY, 5);
        // Sells still has 105(7), so this fills 5 @105 -> sell 105 now 2. (Adjust expectation if you ran differently.)
        System.out.println("=== Final ===");
        book.displayBook();
        book.displayTrades();
    }
}