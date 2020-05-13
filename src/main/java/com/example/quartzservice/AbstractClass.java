package com.example.quartzservice;

public abstract class AbstractClass {

    protected abstract void helper1();
    protected abstract void helper2();

    public void templateMethod() {
        helper1();
        helper2();
    }
}
