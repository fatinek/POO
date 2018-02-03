package com.fredericboisguerin.insa.postage.model;

public class Weight {
    private final double weightInKg;

    public Weight(double weightInKg) {
        this.weightInKg = weightInKg;
    }

    public static Weight fromKilograms(double weightInKg) {
        return new Weight(weightInKg);
    }

    public boolean isUnder(Weight other) {
        return weightInKg < other.weightInKg;
    }

    public double asKg() {
        return weightInKg;
    }
}
