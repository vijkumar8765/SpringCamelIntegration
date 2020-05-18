package com.eai.example.imintegration.functionalTests.bdd;

public class Bdd<T extends Bdd> {

    public T and() {
        return (T) this;
    }

    public T but() {
        return (T) this;
    }

    public T forA() {
        return (T) this;
    }

    public T after() {
        return (T) this;
    }
}
