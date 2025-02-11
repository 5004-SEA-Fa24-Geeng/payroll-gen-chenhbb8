package student;

public class HourlyEmployee implements IEmployee {
    private String name;
    private String id;
    private double payRate;
    private double ytdEarnings;
    private double ytdTaxesPaid;
    private double pretaxDeductions;

    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
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
        return payRate;
    }

    @Override
    public String getEmployeeType() {
        return "HOURLY";
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
        if (hoursWorked < 0) return null;

        double regularHours = Math.min(40, hoursWorked);
        double overtimeHours = Math.max(0, hoursWorked - 40);

        double regularPay = regularHours * payRate;
        double overtimePay = overtimeHours * payRate * 1.5;

        double grossPay = regularPay + overtimePay;
        double taxesPaid = grossPay * 0.2265;
        double netPay = grossPay - taxesPaid;

        // Update YTD values
        ytdEarnings += grossPay;
        ytdTaxesPaid += taxesPaid;

        return new PayStub(name, netPay, taxesPaid, ytdEarnings, ytdTaxesPaid);
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getEmployeeType(), name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
