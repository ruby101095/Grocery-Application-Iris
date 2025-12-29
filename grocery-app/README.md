# 🛒 Grocery Store Checkout System (Spring Boot)

## Overview
Production-ready Grocery Checkout System built using **Spring Boot** and **Clean Architecture** principles.

The application supports:
- Dynamic pricing (JSON / DB ready)
- Versioned pricing
- Configurable discounts
- Inventory validation
- Refund handling
- Easily extensible to production (only DB layer swap)

---

## Architecture (Clean Architecture)

Controller  
→ Service  
→ Ports (Interfaces)  
→ Adapters (JSON / DB / External)

Business logic is isolated from infrastructure.

---

## Key Components

### Controller
- `CheckoutController`
- Exposes REST endpoint `/checkout`

### Services
- `CheckoutService`
- `InventoryService`
- `RefundService`

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
- JUnit 5
- Mockito
- No Spring context for unit tests

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

---

## Production Readiness

To move to production:
- Replace JSON adapters with DB adapters
- No changes required in:
  - Entities
  - Services
  - Controllers

---

## Interview Highlights

- Clean Architecture
- Open/Closed Principle
- Versioned pricing
- Dynamic discounts
- Refund correctness
- Concurrency-safe design ready

---

## Author
Built for Java / Spring Boot backend interview demonstrations.
