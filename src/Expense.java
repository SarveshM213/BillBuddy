public class Expense {
    private String title;
    private double amount;
    private String paidBy;

    // Constructor
    public Expense(String title, double amount, String paidBy) {
        this.title = title;
        this.amount = amount;
        this.paidBy = paidBy;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaidBy() {
        return paidBy;
    }
}
