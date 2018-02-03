package com.fredericboisguerin.insa.postage.model.packages;

public class SmallPackage extends Package {

    private static final int CONSTANT_COST = 12;

    @Override
    public double calculateLocalShippingCost() {
        return CONSTANT_COST;
    }
}
