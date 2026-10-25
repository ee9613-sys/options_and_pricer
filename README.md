# Order Book & Option Pricing (Java)

Two Java projects: an exchange-style order-matching engine and an option-pricing
cross-check.

## Order Book & Matching Engine (`OrderBook/`)
An exchange-style limit order book with price–time priority matching.
- Maintains bid and ask price levels, matching each incoming order against the
  best available price and queueing the remainder.
- Supports limit and market orders, partial fills and multi-level sweeps, and
  logs the resulting trades.
- **Key classes:** `Order`, `OrderBook`, `Trade`, `Main`.

## Option Pricers (`PricerCheck/`)
Prices European options two independent ways and cross-checks them.
- A from-scratch **Black–Scholes** analytic pricer.
- A **Monte Carlo** pricer that simulates geometric Brownian motion price paths
  under the risk-neutral measure, then compares its estimate against the
  Black–Scholes value.
- **Key classes:** `BlackScholesPricer`, `GBMModel`, `MonteCarloPriceCalc`,
  `PriceModel`, `Pricer`, `Main`.

## Running
Each project has its own `Main`. Open the folder in IntelliJ IDEA and run the
`Main` class in the project you want to try.

## Tech
Java · IntelliJ IDEA