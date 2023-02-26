package com.kenny.designpattern.cor;

public class Trouble {
    private int number;

    public Trouble(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Trouble{" +
                "number=" + number +
                '}';
    }
}
