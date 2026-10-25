# Java Projects

A set of Java projects exploring object-oriented design, an exchange-style
order-matching engine, and option pricing.

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

## Bank System (`BankSystem/`)
A small banking model built to practise core object-oriented design.
- An abstract `Account` base type with `CurrentAccount` and `SavingsAccount`
  subclasses (inheritance and polymorphism), managed through a `Bank` class.
- **Key classes:** `Account`, `CurrentAccount`, `SavingsAccount`, `Bank`, `Main`.

## Running
Each project has its own `Main`. Open the repository in IntelliJ IDEA and run
the `Main` class in the package you want to try.

## Tech
Java · IntelliJ IDEA
