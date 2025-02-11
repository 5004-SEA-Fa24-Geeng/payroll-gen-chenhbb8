package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryEmployee implements IEmployee {
    private String name;
    private String id;
    private double payRate;
    private double ytdEarnings;
    private double ytdTaxesPaid;
    private double pretaxDeductions;

    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public double getPayRate() {
        return this.payRate;
    }

    @Override
    public String getEmployeeType() {
        return "SALARY";
    }

    @Override
    public double getYTDEarnings() {
        return this.ytdEarnings;
    }

    @Override
    public double getYTDTaxesPaid() {
        return this.ytdTaxesPaid;
    }

    @Override
    public double getPretaxDeductions() {
        return this.pretaxDeductions;
    }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) return null;  // Skip invalid entries

        double grossPay = payRate / 24;  // Bi-monthly salary

        double taxableIncome = Math.max(0, grossPay - pretaxDeductions);
        double taxes = taxableIncome * 0.2265;  // 22.65% tax rate
        double netPay = taxableIncome - taxes;

        // Round to 2 decimal places
        BigDecimal netPayRounded = BigDecimal.valueOf(netPay).setScale(2, RoundingMode.HALF_UP);
        BigDecimal taxesRounded = BigDecimal.valueOf(taxes).setScale(2, RoundingMode.HALF_UP);

        // Update YTD earnings and taxes
        ytdEarnings += netPayRounded.doubleValue();
        ytdTaxesPaid += taxesRounded.doubleValue();

        return new PayStub(name, id, netPayRounded.doubleValue(), taxesRounded.doubleValue(), ytdEarnings, ytdTaxesPaid);
    }

    @Override
    public String toCSV() {
        return String.format("SALARY,%s,%s,%.2f,%.2f,%.2f,%.2f", name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
