# MarketProduct

### This is a project of a grocery store with customers and sales reports.

## Main Functionality

- Various sales reports.
- Formation of complex JSON summary based on HQL/SQL queries with groupings, functions.

---

The application works in two modes:

1. **Web** (call via browser or Postman)
2. **Command line** (call via console)

### Example of launching Java:

```bash
java -jar program.jar search input.json output.json
```
---
## 🛠 Used Stack:
- Java
- PostgreSQL
- Maven
- SpringBoot
- JUnit (Jupiter)
- Mockito
---
## 📊 Data structure (tables) and columns
- Buyers (Buyers): name, surname
- Products (Products): name, price
- Purchases (Purchases): buyer, product, purchase date

## 🔧 Types and Description of operations
  The application will have 2 main actions:
- Search for buyers by criteria (search)
- Statistics for a date period (stat)
Files for testing the application
---
## 🗂 Test files are located in the root directory of the project:

- For web: ***postman_collection.json
- For command line: input***.json
---
---
## _Example of JSON Files:_
Input JSON (Example for search):
```json lines
{
  "criterias": [
    {
      "lastName": "Smith"
    },
    {
      "productName": "Meat",
      "minTimes": 2
    },
    {
      "minExpenses": 30,
      "maxExpenses": 100
    }
  ]
}
```
Output JSON(Example for statistics):
```json lines
{
  "type": "stat",
  "totalDays": 365,
  "customers": [
    {
      "name": "Doe John",
      "purchases": [
        {
          "productName": "Bread",
          "expenses": 24
        }
      ],
      "totalExpenses": 36
    }
  ],
  "summaryTotalExpenses": 1736,
  "avgTotalExpenses": 144.67
}
```