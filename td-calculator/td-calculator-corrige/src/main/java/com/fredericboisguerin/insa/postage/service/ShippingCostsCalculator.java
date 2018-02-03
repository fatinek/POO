package com.fredericboisguerin.insa.postage.service;

import com.fredericboisguerin.insa.postage.model.Destination;
import com.fredericboisguerin.insa.postage.model.Dimensions;
import com.fredericboisguerin.insa.postage.model.Weight;
import com.fredericboisguerin.insa.postage.model.packages.Package;
import com.fredericboisguerin.insa.postage.model.packages.factory.PackageFactory;

public class ShippingCostsCalculator {

    private PackageFactory packageFactory = new PackageFactory();

    public String calculatePriceFor(int dimension1, int dimension2, int dimension3, double weightInKg, String destinationCode) {
        Dimensions dimensions = Dimensions.fromMillimeters(dimension1, dimension2, dimension3);
        Weight weight = Weight.fromKilograms(weightInKg);
        Package aPackage = packageFactory.buildPackageAndReturnCost(dimensions, weight);
        Destination destination = Destination.fromCode(destinationCode);
        double baseShippingCost = aPackage.calculateLocalShippingCost();
        double priceAsDouble = destination.getCostForBaseShippingCost(baseShippingCost);
        return String.format("%.2f", priceAsDouble);
    }
}
