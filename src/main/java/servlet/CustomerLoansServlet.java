package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/customerLoans")
public class CustomerLoansServlet extends HttpServlet {
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {

			  response.setContentType("text/html");

			  PrintWriter out = response.getWriter();

			  try {

			   int customerId = Integer.parseInt(request.getParameter("customer_id"));

			   HttpSession session = request.getSession();
			   int adminId = (int) session.getAttribute("admin_id");

			   Class.forName("com.mysql.cj.jdbc.Driver");

			   Connection con = DriverManager.getConnection(
			    "jdbc:mysql://localhost:3306/finance_manager",
			    "root",
			    "Sunil@#1234097");

			   Statement stmt = con.createStatement();

			   // Show loans only for this admin
			   ResultSet rs = stmt.executeQuery(
			    "SELECT l.* FROM loans l JOIN customers c ON l.customer_id=c.customer_id " +
			    "WHERE l.customer_id=" + customerId + " AND c.admin_id=" + adminId);

			   out.println("<h2>Loan Details</h2>");

			   out.println("<table border='1'>");

			   out.println("<tr>");
			   out.println("<th>Loan ID</th>");
			   out.println("<th>Loan Amount</th>");
			   out.println("<th>EMI</th>");
			   out.println("<th>Total Paid</th>");
			   out.println("<th>Remaining Balance</th>");
			   out.println("</tr>");

			   while (rs.next()) {

			    int loanId = rs.getInt("loan_id");
			    double loanAmount = rs.getDouble("loan_amount");
			    double emi = rs.getDouble("emi");

			    Statement stmt2 = con.createStatement();

			    ResultSet pay = stmt2.executeQuery(
			     "SELECT SUM(amount) FROM payments WHERE loan_id=" + loanId);

			    double paid = 0;

			    if (pay.next()) {
			     paid = pay.getDouble(1);
			    }

			    double remaining = loanAmount - paid;

			    out.println("<tr>");

			    out.println("<td>" + loanId + "</td>");
			    out.println("<td>" + loanAmount + "</td>");
			    out.println("<td>" + emi + "</td>");
			    out.println("<td>" + paid + "</td>");
			    out.println("<td>" + remaining + "</td>");

			    out.println("</tr>");
			   }

			   out.println("</table>");

			   // Payment history for this admin's customer
			   out.println("<h2>Payment History</h2>");

			   ResultSet payHistory = stmt.executeQuery(
			    "SELECT p.* FROM payments p " +
			    "JOIN loans l ON p.loan_id=l.loan_id " +
			    "JOIN customers c ON l.customer_id=c.customer_id " +
			    "WHERE c.admin_id=" + adminId + " AND c.customer_id=" + customerId);

			   out.println("<table border='1'>");

			   out.println("<tr>");
			   out.println("<th>Payment ID</th>");
			   out.println("<th>Loan ID</th>");
			   out.println("<th>Amount</th>");
			   out.println("<th>Date</th>");
			   out.println("</tr>");

			   while (payHistory.next()) {

			    out.println("<tr>");

			    out.println("<td>" + payHistory.getInt("payment_id") + "</td>");
			    out.println("<td>" + payHistory.getInt("loan_id") + "</td>");
			    out.println("<td>" + payHistory.getDouble("amount") + "</td>");
			    out.println("<td>" + payHistory.getDate("payment_date") + "</td>");

			    out.println("</tr>");
			   }

			   out.println("</table>");

			  } catch (Exception e) {
			   e.printStackTrace();
			  }
			 }

}
