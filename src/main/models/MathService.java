package main.models;

public class MathService implements Math {

    @Override
    public double multiply(float a, float b) {
        return a * b;
    }
}
