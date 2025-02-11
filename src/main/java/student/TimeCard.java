package student;

/**
 * Represents a time card with employee ID and hours worked.
 */
public class TimeCard implements ITimeCard {

    /** The employee's ID. */
    private final String employeeId;

    /** The number of hours worked. */
    private final double hoursWorked;

    /**
     * Constructs a TimeCard object.
     *
     * @param employeeId   The ID of the employee
     * @param hoursWorked  The number of hours worked
     */
    public TimeCard(String employeeId, double hoursWorked) {
        this.employeeId = employeeId;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the employee ID associated with this time card.
     *
     * @return The employee ID
     */
    @Override
    public String getEmployeeID() {
        return employeeId;
    }

    /**
     * Gets the total hours worked for this time card.
     *
     * @return The number of hours worked
     */
    @Override
    public double getHoursWorked() {
        return hoursWorked;
    }
}
