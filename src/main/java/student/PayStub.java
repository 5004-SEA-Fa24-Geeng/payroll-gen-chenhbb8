package student;

/**
 * Represents a pay stub for an employee, containing payment details.
 */
public class PayStub implements IPayStub {

    private final String employeeName;
    private final String employeeId;
    private final String employeeType; // Added employeeType
    private final double netPay;
    private final double taxesPaid;
    private final double ytdEarnings;
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
        this.employeeType = employeeType;  // Initialize employeeType
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
     * Converts pay stub details to CSV format, including the employee type.
     *
     * @return A CSV string representing the pay stub
     */
    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                employeeType, employeeName, employeeId, netPay, taxesPaid, ytdEarnings, ytdTaxesPaid);
    }
}
