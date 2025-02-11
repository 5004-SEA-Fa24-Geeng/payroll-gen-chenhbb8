package student;

public class PayStub implements IPayStub {
    private final String employeeName;
    private final String employeeId;
    private final double netPay;
    private final double taxesPaid;
    private final double ytdEarnings;
    private final double ytdTaxesPaid;

    public PayStub(String employeeName, String employeeId, double netPay, double taxesPaid, double ytdEarnings, double ytdTaxesPaid) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
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

    @Override
    public String toCSV() {
        return String.format("%s,%s,%.2f,%.2f,%.2f,%.2f",
                employeeName, employeeId, netPay, taxesPaid, ytdEarnings, ytdTaxesPaid);
    }
}
