# Java + Spring Boot Concepts

## 1. Java OOP Basics

```mermaid
classDiagram
    class Class {
        Fields (data)
        Methods (behavior)
        Constructor()
    }

    class Object {
        Instance of a Class
        Has its own field values
    }

    class Inheritance {
        Child extends Parent
        Child gets parent methods for free
    }

    class Interface {
        Defines what methods must exist
        No implementation
    }

    Class --> Object : instantiates
    Class --> Inheritance : supports
    Class --> Interface : implements
```

---

## 2. How a Spring Boot Request Flows

```mermaid
flowchart TD
    A[HTTP Request\ne.g. POST /loans] --> B[@RestController\nLoanController]
    B --> C[@Service / Business Logic\noptional layer]
    C --> D[@Repository\nLoanRepository]
    D --> E[(Database\nH2 / Postgres)]
    E --> D
    D --> C
    C --> B
    B --> F[HTTP Response\nJSON]
```

---

## 3. Spring Boot Layer Architecture

```mermaid
flowchart LR
    subgraph Controller Layer
        A[@RestController\nHandles HTTP\nReturns JSON]
    end

    subgraph Repository Layer
        B[@Repository / JpaRepository\nCRUD methods\nNo SQL needed]
    end

    subgraph Database Layer
        C[(H2 / Postgres\nActual data storage)]
    end

    subgraph Entity Layer
        D[@Entity\nMaps Java class\nto DB table]
    end

    A -->|calls| B
    B -->|reads/writes| C
    D -->|describes shape of| C
```

---

## 4. JPA — How @Entity Maps to a Table

```mermaid
flowchart LR
    subgraph Java Class
        A["@Entity
        class Loan {
          @Id Long id
          String applicantName
          double amount
          String status
        }"]
    end

    subgraph Database Table
        B["TABLE: loan
        id  | applicant_name | amount | status
        1   | Alice          | 5000   | PENDING
        2   | Bob            | 12000  | APPROVED"]
    end

    A -->|JPA maps automatically| B
```

---

## 5. JpaRepository — Free Methods

```mermaid
flowchart TD
    A[LoanRepository\nextends JpaRepository] --> B[findAll\nSELECT * FROM loan]
    A --> C[findById id\nSELECT * WHERE id=?]
    A --> D[save loan\nINSERT or UPDATE]
    A --> E[deleteById id\nDELETE WHERE id=?]
    A --> F[existsById id\nSELECT count WHERE id=?]
```

---

## 6. REST HTTP Methods

```mermaid
flowchart LR
    subgraph HTTP Methods
        G[GET] -->|Read| DB
        P[POST] -->|Create| DB
        U[PUT] -->|Update| DB
        D[DELETE] -->|Remove| DB
    end

    DB[(Database)]
```

---

## 7. Annotations Cheat Sheet

```mermaid
mindmap
  root((Spring Boot\nAnnotations))
    Entity Layer
      @Entity\nmarks class as DB table
      @Id\nprimary key
      @GeneratedValue\nauto-increment ID
    Controller Layer
      @RestController\nhandles HTTP + returns JSON
      @RequestMapping\nbase URL path
      @GetMapping\nGET route
      @PostMapping\nPOST route
      @PutMapping\nPUT route
      @DeleteMapping\nDELETE route
    Method Parameters
      @PathVariable\npulls id from URL
      @RequestBody\nconverts JSON body to object
    Repository Layer
      @Repository\nmarks as data layer
      JpaRepository\nfree CRUD methods
```

---

## 8. OOP in This Project

```mermaid
classDiagram
    class Loan {
        -Long id
        -String applicantName
        -double amount
        -String status
        +getId() Long
        +getName() String
        +getAmount() double
        +getStatus() String
        +setName(String)
        +setAmount(double)
        +setStatus(String)
    }

    class LoanRepository {
        <<interface>>
        +findAll() List~Loan~
        +findById(Long) Optional~Loan~
        +save(Loan) Loan
        +deleteById(Long)
        +existsById(Long) boolean
    }

    class LoanController {
        -LoanRepository loanRepository
        +getAllLoans() List~Loan~
        +getLoanById(Long) ResponseEntity
        +createLoan(Loan) ResponseEntity
        +updateLoan(Long, Loan) ResponseEntity
        +deleteLoan(Long) ResponseEntity
    }

    LoanController --> LoanRepository : uses
    LoanRepository --> Loan : manages
```
