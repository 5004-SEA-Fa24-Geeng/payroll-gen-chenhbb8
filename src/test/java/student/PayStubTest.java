package student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for verifying the functionality of the PayStub class.
 */
class PayStubTest {

    private PayStub payStub;

    /**
     * Sets up a PayStub instance before each test.
     */
    @BeforeEach
    void setUp() {
        payStub = new PayStub("Luffy", "s192", "HOURLY",
                1102.24, 322.76, 21102.24, 4852.76);
    }

    /**
     * Tests the getPay method to ensure it returns the correct net pay.
     */
    @Test
    void getPay() {
        double expectedNetPay = 1102.24;
        assertEquals(expectedNetPay, payStub.getPay(),
                "Net pay does not match the expected value.");
    }

    /**
     * Tests the getTaxesPaid method to ensure it returns the correct taxes paid.
     */
    @Test
    void getTaxesPaid() {
        double expectedTaxesPaid = 322.76;
        assertEquals(expectedTaxesPaid, payStub.getTaxesPaid(),
                "Taxes paid do not match the expected value.");
    }

    /**
     * Tests the toCSV method to ensure it returns the correct CSV format, including the employee type.
     */
    @Test
    void toCSV() {
        String expectedCSV = "HOURLY,Luffy,s192,1102.24,322.76,21102.24,4852.76";
        String actualCSV = payStub.toCSV();

        assertNotNull(actualCSV, "CSV output should not be null.");
        assertEquals(expectedCSV, actualCSV,
                "CSV output does not match the expected format.");
    }
}
