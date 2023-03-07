package com.kenny.designpattern.templatemethod;

// ConcreteClass
public class CharDisplay extends AbstractDisplay {

    private char ch;

    public CharDisplay(final char ch) {
        this.ch = ch;
    }

    @Override
    public void open() {
        System.out.print("<<");
    }

    @Override
    public void print() {
        System.out.print(ch);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}
