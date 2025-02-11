package student;

public class PayStub implements IPayStub {
    private String employeeName;
    private String employeeID;
    private double netPay;
    private double taxes;
    private double ytdEarnings;
    private double ytdTaxesPaid;

    public PayStub(String employeeName, String employeeID, double netPay, double taxes, double ytdEarnings, double ytdTaxesPaid) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.netPay = netPay;
        this.taxes = taxes;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    @Override
    public double getPay() {
        return netPay;
    }

    @Override
    public double getTaxesPaid() {
        return taxes;
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%.2f,%.2f,%.2f,%.2f", employeeName, employeeID, netPay, taxesPaid, ytdEarnings, ytdTaxesPaid);
    }
}
