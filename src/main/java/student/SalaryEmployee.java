package student;

public class SalaryEmployee implements IEmployee {
    private String name;
    private String id;
    private double annualSalary;
    private double ytdEarnings;
    private double ytdTaxesPaid;
    private double pretaxDeductions;

    public SalaryEmployee(String name, String id, double annualSalary, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.annualSalary = annualSalary;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public double getPayRate() {
        return annualSalary;
    }

    @Override
    public String getEmployeeType() {
        return "SALARY";
    }

    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        double biMonthlySalary = annualSalary / 24;
        double taxableIncome = biMonthlySalary - pretaxDeductions;

        // Ensure taxable income is not negative
        if (taxableIncome < 0) {
            taxableIncome = 0;
        }

        double taxesPaid = taxableIncome * 0.2265;
        double netPay = Math.max(0, taxableIncome - taxesPaid); // Ensure netPay is non-negative

        // Update YTD values
        ytdEarnings += biMonthlySalary;
        ytdTaxesPaid += taxesPaid;

        return new PayStub(name, netPay, taxesPaid, ytdEarnings, ytdTaxesPaid);
    }


    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getEmployeeType(), name, id, annualSalary, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
