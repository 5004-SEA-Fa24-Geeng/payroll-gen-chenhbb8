package student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedList;

/**
 * Main driver for generating payroll and pay stubs.
 */
public final class PayrollGenerator {

    /** Default file name for employee data. */
    private static final String DEFAULT_EMPLOYEE_FILE = "resources/employees.csv";

    /** Default file name for payroll output. */
    private static final String DEFAULT_PAYROLL_FILE = "resources/pay_stubs.csv";

    /** Default file name for time card data. */
    private static final String DEFAULT_TIME_CARD_FILE = "resources/time_cards.csv";

    /**
     * Private constructor to prevent instantiation.
     */
    private PayrollGenerator() {
    }

    /**
     * Main method to execute the payroll process.
     *
     * @param args Command-line arguments for file inputs and outputs
     */
    public static void main(String[] args) {
        Arguments arguments = Arguments.process(args);

        List<String> employeeLines = FileUtil.readFileToList(arguments.getEmployeeFile());
        List<String> timeCards = FileUtil.readFileToList(arguments.getTimeCards());

        List<IEmployee> employees = employeeLines.stream()
                .map(Builder::buildEmployeeFromCSV)
                .filter(e -> e != null)
                .collect(Collectors.toList());

        List<ITimeCard> timeCardList = timeCards.stream()
                .map(Builder::buildTimeCardFromCSV)
                .filter(tc -> tc != null)
                .collect(Collectors.toList());

        List<IPayStub> payStubs = new LinkedList<>();

        for (ITimeCard timeCard : timeCardList) {
            IEmployee employee = employees.stream()
                    .filter(e -> e.getID().equals(timeCard.getEmployeeID()))
                    .findFirst()
                    .orElse(null);

            if (employee == null) {
                continue;
            }

            double hoursWorked = timeCard.getHoursWorked();
            if (hoursWorked < 0) {
                continue;
            }

            IPayStub payStub = employee.runPayroll(hoursWorked);
            if (payStub != null) {
                payStubs.add(payStub);
            }
        }

        List<String> employeeCSV = employees.stream()
                .map(IEmployee::toCSV)
                .collect(Collectors.toList());

        employeeCSV.add(0, "employee_type,employee_name,employee_id,pay_rate,pretax_deductions,ytd_earnings,ytd_taxes_paid");
        FileUtil.writeFile(arguments.getEmployeeFile(), employeeCSV);

        List<String> payStubLines = payStubs.stream()
                .filter(ps -> ps != null)
                .map(IPayStub::toCSV)
                .collect(Collectors.toList());

        payStubLines.add(0, "employee_name,employee_id,net_pay,taxes_paid,ytd_earnings,ytd_taxes_paid");
        FileUtil.writeFile(arguments.getPayrollFile(), payStubLines);
    }

    /**
     * Handles command-line arguments for file paths.
     */
    private static final class Arguments {

        /** File containing employee data. */
        private String employeeFile = DEFAULT_EMPLOYEE_FILE;

        /** File for payroll output. */
        private String payrollFile = DEFAULT_PAYROLL_FILE;

        /** File containing time card data. */
        private String timeCards = DEFAULT_TIME_CARD_FILE;

        /**
         * Private constructor for internal use.
         */
        private Arguments() {
        }

        /**
         * Gets the employee file path.
         *
         * @return Path to the employee file
         */
        public String getEmployeeFile() {
            return employeeFile;
        }

        /**
         * Gets the payroll output file path.
         *
         * @return Path to the payroll file
         */
        public String getPayrollFile() {
            return payrollFile;
        }

        /**
         * Gets the time card file path.
         *
         * @return Path to the time cards file
         */
        public String getTimeCards() {
            return timeCards;
        }

        /**
         * Prints help message for using the program.
         */
        public void printHelp() {
            System.out.println("Usage: java student.PayrollGenerator "
                    + "[-e employee_file] [-t time_cards_file] [-o payroll_file]");
            System.out.println("Options:");
            System.out.println("  -e employee_file   Input file containing employee information.");
            System.out.println("  -t time_cards_file Input file containing time card information.");
            System.out.println("  -o payroll_file    Output file containing payroll information.");
            System.out.println("  -h                 Print this help message.");
        }

        /**
         * Processes command-line arguments.
         *
         * @param args Command-line arguments
         * @return Processed Arguments object
         */
        public static Arguments process(String[] args) {
            Arguments arguments = new Arguments();
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-e")) {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        arguments.employeeFile = args[++i];
                    }
                } else if (args[i].equals("-t")) {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        arguments.timeCards = args[++i];
                    }
                } else if (args[i].equals("-o")) {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        arguments.payrollFile = args[++i];
                    }
                } else if (args[i].equals("-h")) {
                    arguments.printHelp();
                    System.exit(0);
                }
            }
            return arguments;
        }
    }
}
