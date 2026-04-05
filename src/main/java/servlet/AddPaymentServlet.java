package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.PaymentDAO;

@WebServlet("/AddPaymentServlet")
public class AddPaymentServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

int loanId = Integer.parseInt(request.getParameter("loan_id"));

double amount = Double.parseDouble(request.getParameter("amount"));

String paymentDate = request.getParameter("payment_date");

/* DAO CALL */

PaymentDAO.addPayment(loanId, amount, paymentDate,"EMI");

/* redirect with success message */

response.sendRedirect("addPayment.jsp?msg=success&loan_id=" + loanId);

}

}
