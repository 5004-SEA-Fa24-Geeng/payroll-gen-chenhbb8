package student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Test class for verifying PayrollGenerator functionality.
 */
public class TestPayrollGenerator {

    @TempDir
    static Path tempDir;

    /**
     * Tests the final pay stub generation from employee and time card CSV files.
     *
     * @throws IOException If file operations fail
     */
    @Test
    public void testFinalPayStub() throws IOException {
        Path employees = tempDir.resolve("employees.csv");
        Path timeCards = tempDir.resolve("time_cards.csv");

        // Copy test data into the temporary directory
        Files.copy(Paths.get("resources/employees.csv"), employees);
        Files.copy(Paths.get("resources/time_cards.csv"), timeCards);

        Path payStubs = tempDir.resolve("paystubs.csv");

        String[] args = {
                "-e", employees.toString(),
                "-t", timeCards.toString(),
                "-o", payStubs.toString()
        };

        // Run the payroll generator
        PayrollGenerator.main(args);

        // Expected pay stub output
        String expectedPayStubs = """
                employee_type,employee_name,employee_id,net_pay,taxes_paid,ytd_earnings,ytd_taxes_paid
                HOURLY,Luffy,s192,1044.22,305.78,29750.94,7027.18
                SALARY,Nami,s193,5672.33,1661.00,73039.64,18271.00
                HOURLY,Light Yagami,x101,773.50,226.50,17094.00,4077.00
                SALARY,Misa Amane,x102,4447.63,1302.38,54790.52,13919.04
                SALARY,Edward Elric,f103,3029.54,887.13,39784.84,9430.04
                HOURLY,Eren Yeager,a105,541.45,158.55,10965.80,2627.40
                SALARY,Mikasa Ackerman,a106,3635.45,1064.55,44541.80,10849.40
                """;

        String actualPayStubs = Files.readString(payStubs);

        // Normalize line endings and trim whitespace
        expectedPayStubs = expectedPayStubs.replaceAll("\\r\\n?", "\n").trim();
        actualPayStubs = actualPayStubs.replaceAll("\\r\\n?", "\n").trim();

        // Ensure the file is generated correctly
        assertNotNull(actualPayStubs, "Generated pay stubs file is null.");

        // Compare expected vs. actual pay stub output
        assertEquals(expectedPayStubs, actualPayStubs,
                "Mismatch found in pay stubs:\nExpected:\n" + expectedPayStubs +
                        "\nActual:\n" + actualPayStubs);
    }
}
