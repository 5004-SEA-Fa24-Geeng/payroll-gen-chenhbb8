package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an hourly employee with pay and tax details.
 */
public class HourlyEmployee implements IEmployee {

    /** The employee's name. */
    private String name;

    /** The employee's ID. */
    private String id;

    /** The hourly pay rate. */
    private double payRate;

    /** Year-to-date earnings of the employee. */
    private double ytdEarnings;

    /** Year-to-date taxes paid by the employee. */
    private double ytdTaxesPaid;

    /** Pretax deductions for the employee. */
    private double pretaxDeductions;

    /**
     * Constructs an HourlyEmployee with the given details.
     *
     * @param name              The employee's name
     * @param id                The employee's ID
     * @param payRate           The employee's hourly pay rate
     * @param ytdEarnings       Year-to-date earnings
     * @param ytdTaxesPaid      Year-to-date taxes paid
     * @param pretaxDeductions  Pretax deductions
     */
    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings,
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
        return "HOURLY";
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
     * Calculates the payroll based on hours worked.
     *
     * @param hoursWorked Number of hours worked
     * @return The generated pay stub
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;  // Skip invalid entries
        }

        double grossPay;
        if (hoursWorked > 40) {
            grossPay = (40 * payRate) + ((hoursWorked - 40) * payRate * 1.5);  // Overtime calculation
        } else {
            grossPay = hoursWorked * payRate;
        }

        double taxableIncome = Math.max(0, grossPay - pretaxDeductions);  // Prevent negative taxable income
        double taxes = taxableIncome * 0.2265;  // 22.65% tax rate
        double netPay = taxableIncome - taxes;

        // Round to 2 decimal places
        BigDecimal netPayRounded = BigDecimal.valueOf(netPay).setScale(2, RoundingMode.HALF_UP);
        BigDecimal taxesRounded = BigDecimal.valueOf(taxes).setScale(2, RoundingMode.HALF_UP);

        // Update YTD earnings and taxes
        ytdEarnings += netPayRounded.doubleValue();
        ytdTaxesPaid += taxesRounded.doubleValue();

        return new PayStub(name, id, netPayRounded.doubleValue(),
                taxesRounded.doubleValue(), ytdEarnings, ytdTaxesPaid);
    }

    /**
     * Converts employee details to a CSV format.
     *
     * @return A CSV string representing the employee
     */
    @Override
    public String toCSV() {
        return String.format("HOURLY,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
