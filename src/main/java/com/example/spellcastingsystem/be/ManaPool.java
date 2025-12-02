package com.example.spellcastingsystem.be;

public class ManaPool extends Pool{
    public boolean hasEnough(int amount){
        return this.currentPoints >= amount;
    }
}
