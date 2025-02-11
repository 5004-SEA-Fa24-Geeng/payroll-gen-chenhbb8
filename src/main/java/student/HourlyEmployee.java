package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an hourly employee with payroll and tax details.
 */
public class HourlyEmployee implements IEmployee {

    /** The employee's name. */
    private String name;

    /** The employee's ID. */
    private String id;

    /** The employee's hourly pay rate. */
    private double payRate;

    /** The employee's year-to-date earnings. */
    private double ytdEarnings;

    /** The employee's year-to-date taxes paid. */
    private double ytdTaxesPaid;

    /** The employee's pretax deductions. */
    private double pretaxDeductions;

    /**
     * Constructs an HourlyEmployee with the provided details.
     *
     * @param name              The employee's name
     * @param id                The employee's ID
     * @param payRate           The hourly pay rate
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

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        double grossPay = hoursWorked * payRate;
        double taxableIncome = Math.max(0, grossPay - pretaxDeductions);
        double taxes = taxableIncome * 0.2265;
        double netPay = taxableIncome - taxes;

        BigDecimal netPayRounded = BigDecimal.valueOf(netPay).setScale(2, RoundingMode.HALF_UP);
        BigDecimal taxesRounded = BigDecimal.valueOf(taxes).setScale(2, RoundingMode.HALF_UP);

        ytdEarnings += grossPay;
        ytdTaxesPaid += taxesRounded.doubleValue();

        return new PayStub(name, id, getEmployeeType(), netPayRounded.doubleValue(),
                taxesRounded.doubleValue(), ytdEarnings, ytdTaxesPaid);
    }

    @Override
    public String toCSV() {
        return String.format("HOURLY,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
