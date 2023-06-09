## Clearpoint Software Dev Assessment

### Question 1 - Data Model

Following is a minimal ER diagram based on the given information. 

```mermaid
erDiagram
    DRIVER ||--o{ DELIVERY : has
    DELIVERY ||--o{ DELIVERYPRODUCT : contains
    PRODUCT ||--o{ DELIVERYPRODUCT : part_of

    DRIVER {
        int driver_id PK
        string driver_email
    }

    DELIVERY {
        int delivery_id PK
        int driver_id FK
        string delivery_type
        date delivery_date
    }

    PRODUCT {
        int product_id PK
        string product_code
    }

    DELIVERYPRODUCT {
        int delivery_id FK
        int product_id FK
        int quantity
    }
```

#### Database Query
```sql
SELECT d.driver_email, de.delivery_type, SUM(dp.quantity) AS quantity_of_all_products
FROM Driver d
        INNER JOIN Delivery de ON d.driver_id = de.driver_id
        INNER JOIN DeliveryProduct dp ON de.delivery_id = dp.delivery_id
WHERE
   de.delivery_date = '2021-09-21'
GROUP BY d.driver_email, de.delivery_type
ORDER BY d.driver_email, de.delivery_type;
```

### Question 2 - Please refer to the Clocke class in this repository.

Clocke is a simple app that calculates degree of angle between hour and minute hands of a clock.

#### Prerequisites

Ensure you have the Java Development Kit (JDK) 17 installed on your system. If you don't have it installed, download it from [AdoptOpenJDK](https://adoptopenjdk.net/).

#### Build and Run the Application

1. Clone the repository or download the source code to your local machine.

2. Open a terminal or command prompt and navigate to the project's root directory (where the `build.gradle` file is located).

3. Test and build the application using the Gradle Wrapper by running the following command:

    - On Linux or macOS:

      ```
      ./gradlew test build
      ```

    - On Windows:

      ```
      gradlew.bat test build
      ```

4. After the build is successful, run the JAR file by running the following command:

    ```
   java -jar build/libs/clearpoint-test-1.0-SNAPSHOT.jar
   ```

The application will start, and you can interact with it through the terminal or command prompt.

### Question 3 

Architectural remedies should be applied carefully, taking into account their impact on the business. There may be multiple constraints to consider, such as the business impact of the features, cloud cost, available developer resources, time, and consistency with past architectural decisions. The remedies should be applied based on the priority of these constraints.

The following are a few remedies that can be applied to the given architecture:

1. **Fix any performance issues in the batch process itself.**
2. **Use a readonly replica of the database for the batch job, depending on the nature of the job.**
3. **Introduce a new endpoint in the usage storage component specifically for component A's consumption that doesn't talk to the database.** *Note: If this endpoint that checks HTTP status is a health check, be careful since the health check still needs to make a simple query to the database to check if it is running.*
4. **Break component B's application logic into microservices with their own database, separated from the data being used by the batch job.**
5. **Increase the database capacity (vertical scaling).** 
