package com.fredericboisguerin.insa.postage.model.packages.factory;

import com.fredericboisguerin.insa.postage.model.packages.BigPackage;
import com.fredericboisguerin.insa.postage.model.Dimensions;
import com.fredericboisguerin.insa.postage.model.packages.MediumPackage;
import com.fredericboisguerin.insa.postage.model.packages.Package;
import com.fredericboisguerin.insa.postage.model.packages.SmallPackage;
import com.fredericboisguerin.insa.postage.model.Weight;

public class PackageFactory {

    private static final Dimensions CALIBRE_FOR_SMALL_PACKAGE = Dimensions.fromMillimeters(229, 162, 25);
    private static final Weight MAXIMUM_WEIGHT_FOR_MEDIUM_PACKAGE = Weight.fromKilograms(1.8);

    public Package buildPackageAndReturnCost(Dimensions dimensions, Weight weight) {
        if (dimensions.fitsCalibre(CALIBRE_FOR_SMALL_PACKAGE)) {
            return new SmallPackage();
        } else if (weight.isUnder(MAXIMUM_WEIGHT_FOR_MEDIUM_PACKAGE)) {
            return new MediumPackage(weight);
        }
        return new BigPackage(dimensions, weight);
    }
}
