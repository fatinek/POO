package com.fredericboisguerin.insa.postage.model.packages;

import com.fredericboisguerin.insa.postage.model.Weight;

public class MediumPackage extends Package {
    private static final double COST_PER_KILOGRAM = 17.59;
    private static final double EXTRA_COST = 2.86;

    private final Weight weight;

    public MediumPackage(Weight weight) {
        this.weight = weight;
    }

    @Override
    public double calculateLocalShippingCost() {
        return weight.asKg() * COST_PER_KILOGRAM + EXTRA_COST;
    }
}
