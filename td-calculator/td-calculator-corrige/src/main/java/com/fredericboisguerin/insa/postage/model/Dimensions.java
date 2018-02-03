package com.fredericboisguerin.insa.postage.model;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.List;

public class Dimensions {

    private static final double ONE_CUBIC_DECIMETER_PER_CUBIC_MILLIMETER = 1e-6;

    private final int small;
    private final int medium;
    private final int big;

    public Dimensions(int dimension1, int dimension2, int dimension3) {
        List<Integer> dimensions = getSortedDimensions(dimension1, dimension2, dimension3);
        this.small = dimensions.get(0);
        this.medium = dimensions.get(1);
        this.big = dimensions.get(2);
    }

    public boolean fitsCalibre(Dimensions calibre) {
        return this.big < calibre.big && this.medium < calibre.medium && this.small < calibre.small;
    }

    public static Dimensions fromMillimeters(int dimension1, int dimension2, int dimension3) {
        return new Dimensions(dimension1, dimension2, dimension3);
    }

    private static List<Integer> getSortedDimensions(int dimension1, int dimension2, int dimension3) {
        List<Integer> dimensions = asList(dimension1, dimension2, dimension3);
        Collections.sort(dimensions);
        return dimensions;
    }

    public double asCubicDecimeters() {
        return (big * medium * small) * ONE_CUBIC_DECIMETER_PER_CUBIC_MILLIMETER;
    }
}
