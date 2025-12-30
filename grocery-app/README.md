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

### Future Scope
- Inventory Service checking for items existence
- Discount scope that can be applied on both item as well as cart
- Discount expiry can be checked if discount is still valid till today date

---

## Architecture
- Controller Layer
- Service Layer (Pricing, Discount, Receipt, Checkout)
- Port/Adapter pattern for pricing & discounts
- Config-driven rules

---

## Key Components

### Controller
- `CheckoutController`
- Exposes REST endpoint `/checkout`

### Services
- `CheckoutService`
- `InventoryService`
- `PricingService`
- `ReceiptService`
- `DiscountService`

### Domain Entities
- Product
- CartItem
- OrderItem
- PriceSnapshot
- Discount
- Receipt

### Ports
- `PricingPort`
- `DiscountPort`

### Adapters
- `JsonPricingAdapter`
- `JsonDiscountAdapter`

---

## Versioned Pricing

Prices are stored with versions and effective dates.

### prices.json
```json
{
  "products": [
    {
      "productCode": "BANANA",
      "priceVersions": [
        { "version": "v1", "price": 0.50, "effectiveFrom": "2024-01-01T00:00:00" },
        { "version": "v2", "price": 0.60, "effectiveFrom": "2025-01-01T00:00:00" }
      ]
    }
  ]
}
```

✔ Ensures correct refunds even if price changes later

---

## Dynamic Discounts

Discounts are fully configurable using SpEL expressions.

### discounts.json
```json
{
  "discounts": [
    {
      "productCode": "BANANA",
      "expression": "(quantity / 3) * price",
      "params": {}
    },
    {
      "productCode": "ORANGE",
      "expression": "(quantity / 3) * 0.15",
      "params": {}
    }
  ]
}
```

✔ No strategy explosion  
✔ No code change for new discounts  

---

## API Usage

### Checkout API
```
POST /checkout
```

#### Request
```json
[
  { "productCode": "BANANA", "quantity": 3 },
  { "productCode": "ORANGE", "quantity": 4 }
]
```

#### Response
```json
{
  "subtotal": 3.00,
  "discount": 0.60,
  "total": 2.40
}
```

---

## Refund Handling

Refund uses price-at-purchase:
```java
priceAtPurchase × quantity
```

✔ Accurate  
✔ Auditable  
✔ Production-safe  

---

## Testing
- JUnit 
- Mockito

---

## How to Run

```bash
mvn clean install
mvn spring-boot:run
```

Server runs on:
```
http://localhost:8080
```

## Design Principles
- SOLID
- Open/Closed Principle
- Clean Architecture