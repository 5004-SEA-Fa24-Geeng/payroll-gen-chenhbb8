package student;
import student.TimeCard;

/**
 * A utility class to build employee and time card objects from CSV strings.
 */
public final class Builder {

    /**
     * Private constructor to prevent instantiation.
     */
    private Builder() {
    }

    /**
     * Builds an employee object from a CSV string.
     *
     * CSV Format: employee_type,employee_name,employee_id,pay_rate,pretax_deductions,ytd_earnings,ytd_taxes_paid
     *
     * @param csv The CSV string containing employee details
     * @return An IEmployee object (either HourlyEmployee or SalaryEmployee), or null if invalid
     */
    public static IEmployee buildEmployeeFromCSV(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            return null;
        }

        String[] fields = csv.split(",");

        if (fields.length != 7) {
            return null;
        }

        try {
            String employeeType = fields[0].trim();
            String name = fields[1].trim();
            String id = fields[2].trim();
            double payRate = Double.parseDouble(fields[3].trim());
            double pretaxDeductions = Double.parseDouble(fields[4].trim());
            double ytdEarnings = Double.parseDouble(fields[5].trim());
            double ytdTaxesPaid = Double.parseDouble(fields[6].trim());

            if ("HOURLY".equalsIgnoreCase(employeeType)) {
                return new HourlyEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
            } else if ("SALARY".equalsIgnoreCase(employeeType)) {
                return new SalaryEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
            }
        } catch (NumberFormatException e) {
            return null; // Invalid number format in CSV
        }

        return null; // Unsupported employee type
    }

    /**
     * Converts a CSV string to a TimeCard object.
     *
     * CSV Format: employee_id,hours_worked
     *
     * @param csv The CSV string containing time card details
     * @return An ITimeCard object, or null if invalid
     */
    public static ITimeCard buildTimeCardFromCSV(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            return null;
        }

        String[] fields = csv.split(",");

        if (fields.length != 2) {
            return null;
        }

        try {
            String employeeId = fields[0].trim();
            double hoursWorked = Double.parseDouble(fields[1].trim());

            return new TimeCard(employeeId, hoursWorked);
        } catch (NumberFormatException e) {
            return null; // Invalid number format in CSV
        }
    }
}
