package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository gives you free CRUD methods with zero code:
//   .findAll()       → SELECT * FROM loan
//   .findById(id)    → SELECT * FROM loan WHERE id = ?
//   .save(loan)      → INSERT or UPDATE
//   .deleteById(id)  → DELETE FROM loan WHERE id = ?
//
// First generic type = the Entity class
// Second generic type = the type of the primary key
public interface SoccerRepository extends JpaRepository<Soccer, String> {
}
