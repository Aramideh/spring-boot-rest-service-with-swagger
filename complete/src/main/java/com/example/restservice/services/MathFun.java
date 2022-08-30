package com.example.restservice.services;

public class MathFun {

    private final long id;

    public MathFun( long id ) {
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public double multiply( MathPair mathPair){
        return mathPair.getX() * mathPair.getY();
    }

    public double multiply(Double x, Double y) {
        return x*y;
    }
}
