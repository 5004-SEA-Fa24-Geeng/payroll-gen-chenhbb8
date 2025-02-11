package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest {

    private SalaryEmployee nami;
    private SalaryEmployee misaAmane;

    @BeforeEach
    void setUp() {
        nami = new SalaryEmployee("Nami", "s193", 200000.0, 17017.0, 4983.0, 1000.0);
        misaAmane = new SalaryEmployee("Misa Amane", "x102", 150000.0, 12000.0, 3500.0, 500.0);
    }

    @Test
    void getName() {
        assertEquals("Nami", nami.getName());
        assertEquals("Misa Amane", misaAmane.getName());
    }

    @Test
    void getID() {
        assertEquals("s193", nami.getID());
        assertEquals("x102", misaAmane.getID());
    }

    @Test
    void getPayRate() {
        assertEquals(200000.0, nami.getPayRate(), 0.01);
        assertEquals(150000.0, misaAmane.getPayRate(), 0.01);
    }

    @Test
    void getEmployeeType() {
        assertEquals("SALARY", nami.getEmployeeType());
        assertEquals("SALARY", misaAmane.getEmployeeType());
    }

    @Test
    void getYTDEarnings() {
        assertEquals(17017.0, nami.getYTDEarnings(), 0.01);
        assertEquals(12000.0, misaAmane.getYTDEarnings(), 0.01);
    }

    @Test
    void getYTDTaxesPaid() {
        assertEquals(4983.0, nami.getYTDTaxesPaid(), 0.01);
        assertEquals(3500.0, misaAmane.getYTDTaxesPaid(), 0.01);
    }

    @Test
    void getPretaxDeductions() {
        assertEquals(1000.0, nami.getPretaxDeductions(), 0.01);
        assertEquals(500.0, misaAmane.getPretaxDeductions(), 0.01);
    }

    @Test
    void runPayroll() {
        IPayStub namiPayStub = nami.runPayroll(0);
        assertNotNull(namiPayStub);
        assertEquals(5672.33, namiPayStub.getPay(), 0.01);  // Corrected for Nami
        assertEquals(1661.0, namiPayStub.getTaxesPaid(), 0.01);  // Corrected taxes for Nami

        IPayStub misaPayStub = misaAmane.runPayroll(0);
        assertNotNull(misaPayStub);
        assertEquals(4447.63, misaPayStub.getPay(), 0.01);  // Corrected for Misa Amane
        assertEquals(1302.38, misaPayStub.getTaxesPaid(), 0.01);  // Corrected taxes for Misa Amane
    }


    @Test
    void toCSV() {
        String expectedNamiCSV = "SALARY,Nami,s193,200000.00,1000.00,17017.00,4983.00";
        String expectedMisaCSV = "SALARY,Misa Amane,x102,150000.00,500.00,12000.00,3500.00";

        assertEquals(expectedNamiCSV, nami.toCSV());
        assertEquals(expectedMisaCSV, misaAmane.toCSV());
    }
}
