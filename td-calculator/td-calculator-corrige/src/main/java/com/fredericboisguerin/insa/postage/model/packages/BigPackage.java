package com.fredericboisguerin.insa.postage.model.packages;

import static java.lang.Math.max;

import com.fredericboisguerin.insa.postage.model.Dimensions;
import com.fredericboisguerin.insa.postage.model.Weight;

public class BigPackage extends Package {
    private static final double COST_PER_KILOGRAM = 21.62;
    private static final double COST_PER_CUBIC_DECIMETER = 1.43;

    private final Dimensions dimensions;
    private final Weight weight;

    public BigPackage(Dimensions dimensions, Weight weight) {
        this.dimensions = dimensions;
        this.weight = weight;
    }

    @Override
    public double calculateLocalShippingCost() {
        double weightCost = weight.asKg() * COST_PER_KILOGRAM;
        double dimensionsCost = dimensions.asCubicDecimeters() * COST_PER_CUBIC_DECIMETER;
        return max(weightCost, dimensionsCost);
    }
}
