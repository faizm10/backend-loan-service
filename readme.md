# Loan Service — Spring Boot REST API Learning Project

## How to Run

```bash
cd demo
JAVA_HOME=$(/usr/libexec/java_home -v 23) ./mvnw spring-boot:run
```

Server starts at `http://localhost:8080`

---

## Project Structure

```
demo/src/main/java/com/example/demo/
├── DemoApplication.java     # Entry point — boots the app
├── Loan.java                # Entity — maps to a database table
├── LoanRepository.java      # Repository — free CRUD from JpaRepository
└── LoanController.java      # Controller — defines all REST endpoints
```

---

## What is a RESTful API?

REST (Representational State Transfer) is how systems communicate over HTTP.
You send a request with an HTTP method + URL, and get back JSON.

| HTTP Method | Action         | SQL equivalent |
|-------------|----------------|----------------|
| GET         | Read data      | SELECT         |
| POST        | Create data    | INSERT         |
| PUT         | Update data    | UPDATE         |
| DELETE      | Remove data    | DELETE         |

---

## Endpoints

Base URL: `http://localhost:8080/loans`

### GET /loans
Returns all loans.
```
GET http://localhost:8080/loans
```
Response `200 OK`:
```json
[
  { "id": 1, "applicantName": "Alice", "amount": 5000.0, "status": "PENDING" }
]
```

---

### GET /loans/{id}
Returns a single loan by ID.
```
GET http://localhost:8080/loans/1
```
Response `200 OK`:
```json
{ "id": 1, "applicantName": "Alice", "amount": 5000.0, "status": "PENDING" }
```
Response `404 Not Found` if ID doesn't exist.

---

### POST /loans
Creates a new loan. Send JSON in the request body.
```
POST http://localhost:8080/loans
Content-Type: application/json

{
  "applicantName": "Alice",
  "amount": 5000,
  "status": "PENDING"
}
```
Response `201 Created`:
```json
{ "id": 1, "applicantName": "Alice", "amount": 5000.0, "status": "PENDING" }
```

---

### PUT /loans/{id}
Updates an existing loan.
```
PUT http://localhost:8080/loans/1
Content-Type: application/json

{
  "applicantName": "Alice",
  "amount": 7500,
  "status": "APPROVED"
}
```
Response `200 OK` with updated loan, or `404 Not Found`.

---

### DELETE /loans/{id}
Deletes a loan by ID.
```
DELETE http://localhost:8080/loans/1
```
Response `204 No Content` on success, or `404 Not Found`.

---

## Key Annotations

| Annotation          | Where used       | What it does                                              |
|---------------------|------------------|-----------------------------------------------------------|
| `@SpringBootApplication` | DemoApplication | Boots the app, enables component scanning          |
| `@Entity`           | Loan.java        | Maps the class to a DB table                              |
| `@Id`               | Loan.java        | Marks the primary key field                               |
| `@GeneratedValue`   | Loan.java        | Auto-increments the ID                                    |
| `@RestController`   | LoanController   | Marks class as a REST handler, returns JSON automatically |
| `@RequestMapping`   | LoanController   | Sets the base URL path (`/loans`)                         |
| `@GetMapping`       | LoanController   | Handles GET requests                                      |
| `@PostMapping`      | LoanController   | Handles POST requests                                     |
| `@PutMapping`       | LoanController   | Handles PUT requests                                      |
| `@DeleteMapping`    | LoanController   | Handles DELETE requests                                   |
| `@PathVariable`     | method param     | Pulls `{id}` out of the URL                               |
| `@RequestBody`      | method param     | Converts incoming JSON body to a Java object              |

---

## How to Test the API

Use any of these tools to send requests:

- **curl** (terminal):
  ```bash
  curl -X POST http://localhost:8080/loans \
    -H "Content-Type: application/json" \
    -d '{"applicantName":"Alice","amount":5000,"status":"PENDING"}'

  curl http://localhost:8080/loans
  ```

- **Postman** — GUI tool, great for learning
- **H2 Console** — view the database at `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:loandb`
  - Username: `sa`, Password: *(leave blank)*
