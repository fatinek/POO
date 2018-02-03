package com.fredericboisguerin.insa.postage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.fredericboisguerin.insa.postage.service.ShippingCostsCalculator;

@RunWith(Parameterized.class)
public class GoldenMaster {

    private static final Object[][] TEST_PARAMETERS = new Object[][] {
            { 191, 123, 18,  2.354d, "FR",      "12,00" },
            { 253, 215, 164, 1.565d, "FR",      "30,39" },
            { 653, 133, 271, 2.132d, "FR",      "46,09" },
            { 653, 331, 271, 3.650d, "FR",      "83,76" },
            { 123, 191, 18,  2.354d, "MC",      "13,04" },
            { 253, 215, 164, 1.565d, "MC",      "33,03" },
            { 653, 133, 271, 2.132d, "MC",      "50,10" },
            { 653, 331, 271, 3.650d, "MC",      "91,05" },
            { 191, 123, 18,  2.354d, "DOM/TOM", "13,91" },
            { 253, 215, 164, 1.565d, "DOM/TOM", "33,29" },
            { 653, 133, 271, 2.132d, "DOM/TOM", "49,84" },
            { 653, 331, 271, 3.650d, "DOM/TOM", "89,55" }
    };

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(TEST_PARAMETERS);
    }

    private final int dimension1;
    private final int dimension2;
    private final int dimension3;
    private final double weight;
    private final String destination;
    private final String expectedPriceAsText;

    public GoldenMaster(int dimension1, int dimension2, int dimension3, double weight, String destination,
            String expectedPriceAsText) {
        this.dimension1 = dimension1;
        this.dimension2 = dimension2;
        this.dimension3 = dimension3;
        this.weight = weight;
        this.destination = destination;
        this.expectedPriceAsText = expectedPriceAsText;
    }

    @Test
    public void should_return_expected_price() {
        ShippingCostsCalculator shippingCostsCalculator = new ShippingCostsCalculator();

        String priceAsText = shippingCostsCalculator.calculatePriceFor(dimension1, dimension2, dimension3, weight, destination);

        assertThat(priceAsText).isEqualTo(expectedPriceAsText);
    }
}
