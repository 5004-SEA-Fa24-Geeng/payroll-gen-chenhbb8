package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a salaried employee with payroll and tax details.
 */
public class SalaryEmployee implements IEmployee {

    /** The employee's name. */
    private String name;

    /** The employee's ID. */
    private String id;

    /** The employee's annual salary. */
    private double payRate;

    /** The employee's year-to-date earnings. */
    private double ytdEarnings;

    /** The employee's year-to-date taxes paid. */
    private double ytdTaxesPaid;

    /** The employee's pretax deductions. */
    private double pretaxDeductions;

    /**
     * Constructs a SalaryEmployee with provided details.
     *
     * @param name              The employee's name
     * @param id                The employee's ID
     * @param payRate           The annual salary
     * @param ytdEarnings       Year-to-date earnings
     * @param ytdTaxesPaid      Year-to-date taxes paid
     * @param pretaxDeductions  Pretax deductions
     */
    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings,
                          double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public double getPayRate() {
        return this.payRate;
    }

    @Override
    public String getEmployeeType() {
        return "SALARY";
    }

    @Override
    public double getYTDEarnings() {
        return this.ytdEarnings;
    }

    @Override
    public double getYTDTaxesPaid() {
        return this.ytdTaxesPaid;
    }

    @Override
    public double getPretaxDeductions() {
        return this.pretaxDeductions;
    }

    /**
     * Runs payroll calculations for a salaried employee.
     *
     * @param hoursWorked Number of hours worked (ignored for salaried employees)
     * @return The generated pay stub
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        double grossPay = payRate / 24;  // Bi-monthly salary
        double taxableIncome = Math.max(0, grossPay - pretaxDeductions);
        double taxes = taxableIncome * 0.2265;  // Correct tax rate
        double netPay = taxableIncome - taxes;

        // Ensure accurate rounding
        netPay = Math.round(netPay * 100.0) / 100.0;
        taxes = Math.round(taxes * 100.0) / 100.0;

        // Correct YTD calculation: add grossPay instead of netPay
        ytdEarnings += grossPay;
        ytdTaxesPaid += taxes;

        return new PayStub(name, id, netPay, taxes, ytdEarnings, ytdTaxesPaid);
    }


    /**
     * Converts employee details to CSV format.
     *
     * @return A CSV string representing the employee
     */
    @Override
    public String toCSV() {
        return String.format("SALARY,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
