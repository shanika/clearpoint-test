## Clearpoint Software Dev Assessment

### Question 1 - Data Model

Following is a minimal ER diagram based on the given information. 

DRIVER_EMAIL and PRODUCT_CODE are used as primary keys instead of using separate IDs for the DRIVER and PRODUCT tables as a design decision to keep it simple and efficient at this stage.

```mermaid
erDiagram
    DRIVER ||--o{ DELIVERY : has
    DELIVERY ||--o{ DELIVERYPRODUCT : contains
    PRODUCT ||--o{ DELIVERYPRODUCT : part_of

    DRIVER {
        string driver_email PK
    }

    DELIVERY {
        int delivery_id PK
        string driver_email FK
        string delivery_type
        date delivery_date
    }

    PRODUCT {
        string product_code PK
    }

    DELIVERYPRODUCT {
        int delivery_id FK
        string product_code FK
        int quantity
    }
```

#### Database Query
```sql
SELECT d.driver_email, de.delivery_type, SUM(dp.quantity) AS quantity_of_all_products
FROM Driver d
INNER JOIN Delivery de ON d.driver_email = de.driver_email
INNER JOIN DeliveryProduct dp ON de.delivery_id = dp.delivery_id
WHERE 
    de.delivery_date = '2021-09-21'
GROUP BY d.driver_email, de.delivery_type
ORDER BY d.driver_email, de.delivery_type;

```

#### Simplified and optimized query
Remove the inner join between Driver and Delivery tables since following query will produce the same result as above.
```sql
SELECT de.driver_email, de.delivery_type, SUM(dp.quantity) AS total_quantity
FROM Delivery de
JOIN DeliveryProduct dp ON de.delivery_id = dp.delivery_id
WHERE 
    de.delivery_date = '2021-09-21'
GROUP BY de.driver_email, de.delivery_type
ORDER BY de.driver_email, de.delivery_type;
```

### Question 2 - Please refer to the Clocke class in this repository.
