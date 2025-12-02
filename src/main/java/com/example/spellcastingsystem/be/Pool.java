package com.example.spellcastingsystem.be;

public abstract class Pool {
    int currentPoints;
    void add(int amount){
        this.currentPoints += amount;
    }
    void consume(int amount){
        this.currentPoints -= amount;
    }

}
