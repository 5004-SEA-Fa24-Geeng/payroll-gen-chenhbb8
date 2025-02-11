import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import student.PayrollGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestPayrollGenerator {

    @TempDir
    static Path tempDir;

    @Test
    public void testFinalPayStub() throws IOException {
        Path employees = tempDir.resolve("employees.csv");
        Path timeCards = tempDir.resolve("time_cards.csv");
        Files.copy(Paths.get("resources/employees.csv"), employees);
        Files.copy(Paths.get("resources/time_cards.csv"), timeCards);

        Path payStubs = tempDir.resolve("paystubs.csv");

        String[] args = {
                "-e", employees.toString(),
                "-t", timeCards.toString(),
                "-o", payStubs.toString()
        };

        PayrollGenerator.main(args);

        String expectedPayStubs = """
                employee_name,employee_id,net_pay,taxes,ytd_earnings,ytd_taxes_paid
                Luffy,s192,1102.24,322.76,21102.24,4852.76
                Nami,s193,5672.33,1661.00,22689.33,6644.00
                Light Yagami,x101,773.50,226.50,10773.50,2491.50
                Misa Amane,x102,4447.63,1302.38,16447.63,4802.38
                Edward Elric,f103,3029.54,887.13,14029.54,3220.13
                Eren Yeager,a105,541.45,158.55,6541.45,1517.55
                Mikasa Ackerman,a106,3635.45,1064.55,13635.45,3397.55
                """;

        String actualPayStubs = Files.readString(payStubs);

        // ✅ Normalize line endings and trim spaces
        expectedPayStubs = expectedPayStubs.replaceAll("\\r\\n?", "\n").trim();
        actualPayStubs = actualPayStubs.replaceAll("\\r\\n?", "\n").trim();

        assertNotNull(actualPayStubs, "Generated pay stubs file is null.");
        assertEquals(expectedPayStubs, actualPayStubs, "Mismatch between expected and actual pay stubs.");
    }
}
