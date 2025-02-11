package student;

/**
 * Represents a pay stub for an employee, containing payment details.
 */
public class PayStub implements IPayStub {

    /** The employee's name. */
    private final String employeeName;

    /** The employee's ID. */
    private final String employeeId;

    /** The employee's type (HOURLY or SALARY). */
    private final String employeeType;

    /** The employee's net pay. */
    private final double netPay;

    /** The amount of taxes paid. */
    private final double taxesPaid;

    /** The employee's year-to-date earnings. */
    private final double ytdEarnings;

    /** The employee's year-to-date taxes paid. */
    private final double ytdTaxesPaid;

    /**
     * Constructs a PayStub with the provided details.
     *
     * @param employeeName  The employee's name
     * @param employeeId    The employee's ID
     * @param employeeType  The employee's type (e.g., HOURLY, SALARY)
     * @param netPay        The net pay for the employee
     * @param taxesPaid     The amount of taxes paid
     * @param ytdEarnings   Year-to-date earnings
     * @param ytdTaxesPaid  Year-to-date taxes paid
     */
    public PayStub(String employeeName, String employeeId, String employeeType,
                   double netPay, double taxesPaid, double ytdEarnings, double ytdTaxesPaid) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.employeeType = employeeType;
        this.netPay = netPay;
        this.taxesPaid = taxesPaid;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    @Override
    public double getPay() {
        return netPay;
    }

    @Override
    public double getTaxesPaid() {
        return taxesPaid;
    }

    /**
     * Converts pay stub details to CSV format.
     *
     * @return A CSV string representing the pay stub
     */
    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                employeeType, employeeName, employeeId, netPay, taxesPaid, ytdEarnings, ytdTaxesPaid);
    }
}
