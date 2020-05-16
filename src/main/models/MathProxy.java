package main.models;

import main.Client;

public class MathProxy implements Math {

    private Client client;

    public MathProxy() {
        client = new Client();
    }

    @Override
    public double multiply(float a, float b) {
        String result = client.send(a, b);
        if (result != null) return Double.parseDouble(result);
        return Double.NaN;
    }
}
