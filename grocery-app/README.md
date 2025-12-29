# Grocery Store Checkout System

## Overview
This is a Spring Boot based Grocery Checkout application designed with Clean Architecture principles.
It supports versioned pricing, dynamic discounts, refunds using price snapshots, and is easily extensible for production use.

---

## Assumptions

### Functional Assumptions
- Each product is uniquely identified by `productCode`
- Cart contains one entry per product
- Quantity is always a positive integer
- Inventory check is synchronous and simplified
- Discounts are non-overlapping and additive
- Item-level discounts apply per product
- Cart-level discounts apply after item-level discounts
- Only one active discount per product

### Pricing Assumptions
- Prices are versioned and selected using latest effective date
- Price snapshot is captured at checkout
- Refunds use price-at-purchase
- Currency is GBP (£)

### Discount Assumptions
- Discounts are config-driven (`discounts.json`)
- SpEL is used for discount expressions
- Supported variables: quantity, price, custom params
- If no discount exists, `NO_DISC` is applied
- Cart-level discount uses special key `CART`

### Technical Assumptions
- JSON configs stored under `src/main/resources`
- Single Spring Boot instance
- Persistence layer can be added without service/entity changes
- Uses JUnit 5, Mockito, Spring Boot Test

### Out of Scope
- Payments
- Authentication
- Taxes
- Distributed inventory

---

## Architecture
- Controller Layer
- Service Layer (Pricing, Discount, Receipt, Checkout)
- Port/Adapter pattern for pricing & discounts
- Config-driven rules

---

## How to Run
```bash
mvn clean spring-boot:run
```

---

## API
POST `/checkout`

---

## Testing
- Unit Tests: JUnit + Mockito
- Integration Tests: SpringBootTest + MockMvc

---

## Design Principles
- SOLID
- Open/Closed Principle
- Clean Architecture
