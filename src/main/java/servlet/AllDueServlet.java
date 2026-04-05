package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;


@WebServlet("/AllDueServlet")
public class AllDueServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

			PrintWriter out = response.getWriter();

			HttpSession session = request.getSession(false);
			int adminId = (int) session.getAttribute("admin_id");

			try{

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(
			"SELECT c.customer_id,c.name,l.loan_id,l.loan_amount,l.emi,l.start_date " +
			"FROM loans l JOIN customers c ON l.customer_id=c.customer_id WHERE c.admin_id=?"
			);

			ps.setInt(1, adminId);

			ResultSet rs = ps.executeQuery();

			out.println("<h4>Overdue Loans</h4>");
			out.println("<table class='table table-bordered'>");
			out.println("<tr><th>Name</th><th>Loan</th><th>Overdue Amount</th></tr>");

			while(rs.next()){

			int loanId = rs.getInt("loan_id");
			double emi = rs.getDouble("emi");

			// 👉 start date
			java.sql.Date startDate = rs.getDate("start_date");

			if(startDate == null) continue;

			java.time.LocalDate start = startDate.toLocalDate();
			java.time.LocalDate today = java.time.LocalDate.now();

			// 👉 months passed
			long months = java.time.temporal.ChronoUnit.MONTHS.between(start, today);

			double expectedPaid = emi * months;

			// 👉 actual paid
			PreparedStatement ps2 = con.prepareStatement(
					"SELECT COALESCE(SUM(amount),0) FROM payments WHERE loan_id=? AND type='EMI'"
			);

			ps2.setInt(1, loanId);

			ResultSet r2 = ps2.executeQuery();
			r2.next();

			double actualPaid = r2.getDouble(1);

			// 👉 overdue calculation
			double overdue = expectedPaid - actualPaid;

			if(overdue > 0){

			out.println("<tr>");

			out.println("<td>");
			out.println("<a href='CustomerDetailsServlet?customer_id="+rs.getInt("customer_id")+"'>");
			out.println(rs.getString("name"));
			out.println("</a>");
			out.println("</td>");

			out.println("<td>"+loanId+"</td>");
			out.println("<td class='text-danger'>₹"+String.format("%.2f", overdue)+"</td>");

			out.println("</tr>");
			}

			}

			out.println("</table>");

			}catch(Exception e){
			e.printStackTrace();
			}
			}
}
