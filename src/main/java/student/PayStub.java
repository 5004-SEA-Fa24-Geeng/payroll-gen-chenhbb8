package student;

/**
 * Represents a pay stub for an employee, containing payment details.
 */
public class PayStub implements IPayStub {

    /** The employee's name. */
    private final String employeeName;

    /** The employee's ID. */
    private final String employeeId;

    /** The employee's net pay after deductions. */
    private final double netPay;

    /** The amount of taxes paid by the employee. */
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
     * @param netPay        The employee's net pay
     * @param taxesPaid     The amount of taxes paid
     * @param ytdEarnings   The year-to-date earnings
     * @param ytdTaxesPaid  The year-to-date taxes paid
     */
    public PayStub(String employeeName, String employeeId, double netPay, double taxesPaid,
                   double ytdEarnings, double ytdTaxesPaid) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.netPay = netPay;
        this.taxesPaid = taxesPaid;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    /**
     * Gets the employee's net pay.
     *
     * @return The net pay amount
     */
    @Override
    public double getPay() {
        return netPay;
    }

    /**
     * Gets the amount of taxes paid by the employee.
     *
     * @return The taxes paid
     */
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
        return String.format("%s,%s,%.2f,%.2f,%.2f,%.2f",
                employeeName, employeeId, netPay, taxesPaid, ytdEarnings, ytdTaxesPaid);
    }
}
