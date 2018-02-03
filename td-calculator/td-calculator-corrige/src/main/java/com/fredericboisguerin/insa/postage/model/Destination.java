package com.fredericboisguerin.insa.postage.model;

import java.util.stream.Stream;

public enum Destination {
    FRANCE("FR", 1, 0),
    MONACO("MC", 1.087, 0),
    DOM_TOM("DOM/TOM", 1.054, 1.26);

    private final String code;
    private final double coefficient;
    private final double offset;

    Destination(String code, double coefficient, double offset) {
        this.code = code;
        this.coefficient = coefficient;
        this.offset = offset;
    }

    public double getCostForBaseShippingCost(double baseShippingCost) {
        return offset + coefficient * baseShippingCost;
    }

    public static Destination fromCode(String destinationCode) {
        return Stream.of(values())
                .filter(destination -> destination.code.equals(destinationCode))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown destination code: " + destinationCode));
    }
}

