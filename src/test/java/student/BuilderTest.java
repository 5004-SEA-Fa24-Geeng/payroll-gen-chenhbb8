package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuilderTest {

    private String validHourlyEmployeeCSV;
    private String validSalaryEmployeeCSV;
    private String validTimeCardCSV;

    @BeforeEach
    void setUp() {
        validHourlyEmployeeCSV = "HOURLY,Luffy,s192,30.0,0.0,20000.0,4530.0";
        validSalaryEmployeeCSV = "SALARY,Nami,s193,200000.0,1000.0,17017.0,4983.0";
        validTimeCardCSV = "s192,45";
    }

    @Test
    void buildEmployeeFromCSV() {
        IEmployee hourlyEmployee = Builder.buildEmployeeFromCSV(validHourlyEmployeeCSV);
        assertNotNull(hourlyEmployee, "Failed to build HOURLY employee from CSV");
        assertTrue(hourlyEmployee instanceof HourlyEmployee);
        assertEquals("Luffy", hourlyEmployee.getName());
        assertEquals("s192", hourlyEmployee.getID());
        assertEquals(30.0, hourlyEmployee.getPayRate(), 0.01);
        assertEquals(20000.0, hourlyEmployee.getYTDEarnings(), 0.01);
        assertEquals(4530.0, hourlyEmployee.getYTDTaxesPaid(), 0.01);

        IEmployee salaryEmployee = Builder.buildEmployeeFromCSV(validSalaryEmployeeCSV);
        assertNotNull(salaryEmployee, "Failed to build SALARY employee from CSV");
        assertTrue(salaryEmployee instanceof SalaryEmployee);
        assertEquals("Nami", salaryEmployee.getName());
        assertEquals("s193", salaryEmployee.getID());
        assertEquals(200000.0, salaryEmployee.getPayRate(), 0.01);
        assertEquals(17017.0, salaryEmployee.getYTDEarnings(), 0.01);
        assertEquals(4983.0, salaryEmployee.getYTDTaxesPaid(), 0.01);
    }

    @Test
    void buildTimeCardFromCSV() {
        ITimeCard timeCard = Builder.buildTimeCardFromCSV(validTimeCardCSV);
        assertNotNull(timeCard, "Failed to build TimeCard from CSV using ITimeCard.fromCSV()");
        assertEquals("s192", timeCard.getEmployeeID());
        assertEquals(45, timeCard.getHoursWorked(), 0.01);
    }
}
