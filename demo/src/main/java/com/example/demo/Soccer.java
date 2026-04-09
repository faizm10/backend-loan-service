package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Soccer {

    // generates id, increments by one
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //basic information
    private String name;
    private int goals;

    // JPA requires a no-arg constructor to instantiate objects from the DB
    public Soccer() {
    }

    public Soccer(String name, int goals) {
        this.name = name;
        this.goals = goals;
    }

    // getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoals() { //how many goals
        return this.goals;
    }

    public void setGoals(int goals) { //set goals
        this.goals = goals;
    }
}
