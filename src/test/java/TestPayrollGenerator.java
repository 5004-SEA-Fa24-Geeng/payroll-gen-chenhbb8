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
                HOURLY,Luffy,s192,1044.23,305.78,26006.72,6109.84
                SALARY,Nami,s193,5672.33,1661.00,50700.65,13288.00
                HOURLY,Light Yagami,x101,773.50,226.50,14320.50,3397.50
                SALARY,Misa Amane,x102,4447.63,1302.38,37842.89,10011.90
                SALARY,Edward Elric,f103,3029.54,887.13,28421.96,6768.65
                HOURLY,Eren Yeager,a105,541.45,158.55,9024.35,2151.75
                SALARY,Mikasa Ackerman,a106,3635.45,1064.55,30906.35,7655.75
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
