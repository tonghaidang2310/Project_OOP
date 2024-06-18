package demo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import demo.DAO.PaymentDAO;
import demo.Entity.Payment;

public class PaymentController {

    private PaymentDAO paymentDAO;

    public PaymentController() {
        this.paymentDAO = new PaymentDAO();
    }

    public void addPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        String studentId = request.getParameter("studentId");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String date = request.getParameter("date");

        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setStudentId(studentId);
        payment.setAmount(amount);
        payment.setDate(date);

        paymentDAO.addPayment(payment);

        response.sendRedirect("paymentSuccess.jsp");
    }

    public void getPaymentsByStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        List<Payment> payments = paymentDAO.getPaymentsByStudentId(studentId);

        request.setAttribute("payments", payments);
        request.getRequestDispatcher("/view/payments.jsp").forward(request, response);
    }
}
