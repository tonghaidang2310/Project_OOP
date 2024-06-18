package demo.Entity;

public class Payment {
    private String paymentId;
    private String studentId;
    private double amount;
    private String date;

    public Payment(String paymentId, String studentId, double amount, String date) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.amount = amount;
        this.date = date;
    }

    public Payment() {
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
