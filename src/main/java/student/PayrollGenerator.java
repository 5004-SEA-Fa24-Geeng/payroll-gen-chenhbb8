package student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedList;

public final class PayrollGenerator {
    private static final String DEFAULT_EMPLOYEE_FILE = "resources/employees.csv";
    private static final String DEFAULT_PAYROLL_FILE = "resources/pay_stubs.csv";
    private static final String DEFAULT_TIME_CARD_FILE = "resources/time_cards.csv";

    private PayrollGenerator() {
    }

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

        employeeLines = employees.stream()
                .map(IEmployee::toCSV)
                .collect(Collectors.toList());
        employeeLines.add(0, FileUtil.EMPLOYEE_HEADER);
        FileUtil.writeFile(arguments.getEmployeeFile(), employeeLines);

        List<String> payStubLines = payStubs.stream()
                .filter(x -> x != null)
                .map(IPayStub::toCSV)
                .collect(Collectors.toList());


        payStubLines.add(0, "employee_name,employee_id,net_pay,taxes,ytd_earnings,ytd_taxes_paid");

        FileUtil.writeFile(arguments.getPayrollFile(), payStubLines);
    }

    private static final class Arguments {
        private String employeeFile = DEFAULT_EMPLOYEE_FILE;
        private String payrollFile = DEFAULT_PAYROLL_FILE;
        private String timeCards = DEFAULT_TIME_CARD_FILE;

        private Arguments() {
        }

        public String getEmployeeFile() {
            return employeeFile;
        }

        public String getPayrollFile() {
            return payrollFile;
        }

        public String getTimeCards() {
            return timeCards;
        }

        public void printHelp() {
            System.out.println("Usage: java student.PayrollGenerator [-e employee_file] [-t time_cards_file] [-o payroll_file]");
            System.out.println("Options:");
            System.out.println("  -e employee_file  Input file containing employee information.");
            System.out.println("  -t time_cards_file  Input file containing time card information.");
            System.out.println("  -o payroll_file   Output file containing payroll information.");
            System.out.println("  -h                Print this help message");
        }

        public static Arguments process(String[] args) {
            Arguments arguments = new Arguments();
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-e")) {
                    arguments.employeeFile = args[++i];
                } else if (args[i].equals("-t")) {
                    arguments.timeCards = args[++i];
                } else if (args[i].equals("-o")) {
                    arguments.payrollFile = args[++i];
                } else if (args[i].equals("-h")) {
                    arguments.printHelp();
                    System.exit(0);
                }
            }
            return arguments;
        }
    }
}
