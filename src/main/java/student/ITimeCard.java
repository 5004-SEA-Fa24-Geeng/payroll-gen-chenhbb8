package student;

/**
 * An interface for the concept of the time card.
 *
 * This interface is used in the Builder to help convert the CSV file to a list of time cards.
 */
public interface ITimeCard {

    /**
     * Gets the employee ID.
     *
     * @return the employee ID
     */
    String getEmployeeID();

    /**
     * Gets the hours worked by the employee.
     *
     * @return the hours worked by the employee
     */
    double getHoursWorked();

    /**
     * Static method to build a time card from a CSV string.
     *
     * @param csv the CSV string (format: employeeID,hoursWorked)
     * @return an ITimeCard object or null if the format is invalid
     */
    static ITimeCard fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 2) {
            return null; // Invalid format
        }

        String employeeID = parts[0];
        double hoursWorked;

        try {
            hoursWorked = Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
            return null; // Invalid hours worked format
        }

        return new ITimeCard() {
            @Override
            public String getEmployeeID() {
                return employeeID;
            }

            @Override
            public double getHoursWorked() {
                return hoursWorked;
            }
        };
    }

    /**
     * Default method to convert a time card to a CSV string.
     *
     * @return CSV representation of the time card
     */
    default String toCSV() {
        return String.format("%s,%.2f", getEmployeeID(), getHoursWorked());
    }
}
