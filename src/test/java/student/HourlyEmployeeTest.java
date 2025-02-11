package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HourlyEmployeeTest {

    private HourlyEmployee luffy;
    private HourlyEmployee lightYagami;

    @BeforeEach
    void setUp() {
        luffy = new HourlyEmployee("Luffy", "s192", 30.0, 20000.0, 4530.0, 0.0);
        lightYagami = new HourlyEmployee("Light Yagami", "x101", 25.0, 10000.0, 2265.0, 0.0);
    }

    @Test
    void getName() {
        assertEquals("Luffy", luffy.getName());
        assertEquals("Light Yagami", lightYagami.getName());
    }

    @Test
    void getID() {
        assertEquals("s192", luffy.getID());
        assertEquals("x101", lightYagami.getID());
    }

    @Test
    void getPayRate() {
        assertEquals(30.0, luffy.getPayRate(), 0.01);
        assertEquals(25.0, lightYagami.getPayRate(), 0.01);
    }

    @Test
    void getEmployeeType() {
        assertEquals("HOURLY", luffy.getEmployeeType());
        assertEquals("HOURLY", lightYagami.getEmployeeType());
    }

    @Test
    void getYTDEarnings() {
        assertEquals(20000.0, luffy.getYTDEarnings(), 0.01);
        assertEquals(10000.0, lightYagami.getYTDEarnings(), 0.01);
    }

    @Test
    void getYTDTaxesPaid() {
        assertEquals(4530.0, luffy.getYTDTaxesPaid(), 0.01);
        assertEquals(2265.0, lightYagami.getYTDTaxesPaid(), 0.01);
    }

    @Test
    void getPretaxDeductions() {
        assertEquals(0.0, luffy.getPretaxDeductions(), 0.01);
        assertEquals(0.0, lightYagami.getPretaxDeductions(), 0.01);
    }

    @Test
    void runPayroll() {
        IPayStub luffyPayStub = luffy.runPayroll(45);
        assertNotNull(luffyPayStub);
        assertEquals(1102.24, luffyPayStub.getPay(), 0.01);
        assertEquals(322.76, luffyPayStub.getTaxesPaid(), 0.01);

        IPayStub lightPayStub = lightYagami.runPayroll(40);
        assertNotNull(lightPayStub);
        assertEquals(773.5, lightPayStub.getPay(), 0.01);
        assertEquals(226.5, lightPayStub.getTaxesPaid(), 0.01);
    }

    @Test
    void toCSV() {
        String expectedLuffyCSV = "HOURLY,Luffy,s192,30.00,0.00,20000.00,4530.00";
        String expectedLightCSV = "HOURLY,Light Yagami,x101,25.00,0.00,10000.00,2265.00";

        assertEquals(expectedLuffyCSV, luffy.toCSV());
        assertEquals(expectedLightCSV, lightYagami.toCSV());
    }
}
