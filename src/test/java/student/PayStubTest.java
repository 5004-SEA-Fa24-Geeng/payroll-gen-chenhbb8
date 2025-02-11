package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayStubTest {

    private PayStub luffyPayStub;
    private PayStub namiPayStub;

    @BeforeEach
    void setUp() {
        luffyPayStub = new PayStub("Luffy", "s192", 1031.31, 301.69, 21031.31, 4831.69);
        namiPayStub = new PayStub("Nami", "s193", 1500.00, 400.00, 18517.00, 5383.00);
    }

    @Test
    void getPay() {
        assertEquals(1031.31, luffyPayStub.getPay(), 0.01);
        assertEquals(1500.00, namiPayStub.getPay(), 0.01);
    }

    @Test
    void getTaxesPaid() {
        assertEquals(301.69, luffyPayStub.getTaxesPaid(), 0.01);
        assertEquals(400.00, namiPayStub.getTaxesPaid(), 0.01);
    }

    @Test
    void toCSV() {
        String expectedLuffyCSV = "Luffy,s192,1031.31,301.69,21031.31,4831.69";
        String expectedNamiCSV = "Nami,s193,1500.00,400.00,18517.00,5383.00";

        assertEquals(expectedLuffyCSV, luffyPayStub.toCSV());
        assertEquals(expectedNamiCSV, namiPayStub.toCSV());
    }
}
