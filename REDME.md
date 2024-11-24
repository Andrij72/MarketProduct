# MarketProduct 
### This is a project of a grocery store with customers and sales reports.
Main functionality:

various sales reports, formation of complex JSON summary based on HQL/SQL queries with groupings, functions.
***
The application works in two modes:

- web (call via browser or Postman)
- command line (call via console)

Example of launching java -jar program.jar search input.json output.json

Used stack: Java, PostgreSQL, Maven, SpringBoot, Jupiter, Mokito.

#### Data structure (tables) and columns
• Buyers (name, surname)
• Products (name, price)
• Purchases (buyer, product, purchase date)

#### Types and Description of operations
 _The application will have 2 main actions:_
   - Search for buyers by criteria (search)
   - Statistics for a date period (stat)