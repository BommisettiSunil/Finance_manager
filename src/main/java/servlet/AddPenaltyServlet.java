package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.PaymentDAO;

@WebServlet("/AddPenaltyServlet")
public class AddPenaltyServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

int loanId = Integer.parseInt(request.getParameter("loan_id"));

double penaltyAmount = 200;   // example fixed penalty

String today = java.time.LocalDate.now().toString();

PaymentDAO.addPayment(loanId, penaltyAmount, today, "PENALTY");

response.sendRedirect("InstallmentServlet?loan_id=" + loanId);

}

}