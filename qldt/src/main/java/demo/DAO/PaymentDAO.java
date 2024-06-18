package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import demo.Entity.Payment;
import demo.Data.DataBase;

public class PaymentDAO {

    public void addPayment(Payment payment) {
        try (Connection conn = DataBase.connecDb()) {
            String sql = "INSERT INTO payments (paymentId, studentId, amount, date) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, payment.getPaymentId());
            pstmt.setString(2, payment.getStudentId());
            pstmt.setDouble(3, payment.getAmount());
            pstmt.setString(4, payment.getDate());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getPaymentsByStudentId(String studentId) {
        List<Payment> payments = new ArrayList<>();
        try (Connection conn = DataBase.connecDb()) {
            String sql = "SELECT * FROM payments WHERE studentId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getString("paymentId"));
                payment.setStudentId(rs.getString("studentId"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setDate(rs.getString("date"));
                payments.add(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }
}
