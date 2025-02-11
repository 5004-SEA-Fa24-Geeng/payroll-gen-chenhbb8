package student;

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

    /**
     * Runs payroll for the employee based on hours worked.
     *
     * @param hoursWorked The number of hours worked
     * @return The generated pay stub
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        double grossPay = hoursWorked * payRate;
        double taxableIncome = Math.max(0, grossPay - pretaxDeductions);
        double taxes = taxableIncome * 0.2265;
        double netPay = taxableIncome - taxes;

        // Round net pay and taxes to 2 decimal places
        netPay = Math.round(netPay * 100.0) / 100.0;
        taxes = Math.round(taxes * 100.0) / 100.0;

        ytdEarnings += netPay;
        ytdTaxesPaid += taxes;

        return new PayStub(name, id, getEmployeeType(), netPay, taxes, ytdEarnings, ytdTaxesPaid);
    }

    /**
     * Converts employee details to a CSV string.
     *
     * @return The CSV representation of the employee
     */
    @Override
    public String toCSV() {
        return String.format("HOURLY,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
