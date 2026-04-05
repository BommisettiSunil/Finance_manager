package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import util.DBConnection;

@WebServlet("/TodayCollectionServlet")
public class TodayCollectionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

			PrintWriter out = response.getWriter();

			HttpSession session = request.getSession(false);
			int adminId = (int) session.getAttribute("admin_id");

			try{

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(
			"SELECT c.customer_id,c.name,p.loan_id,p.amount,l.loan_amount,l.emi FROM payments p JOIN loans l ON p.loan_id=l.loan_id JOIN customers c ON l.customer_id=c.customer_id WHERE DATE(p.payment_date)=CURDATE() AND c.admin_id=? AND p.type='EMI'"
			);

			ps.setInt(1, adminId);

			ResultSet rs = ps.executeQuery();

			out.println("<h4>Today's Collection</h4>");
			out.println("<table class='table table-bordered'>");
			out.println("<tr><th>Name</th><th>Loan</th><th>Paid</th><th>Installment</th><th>Remaining</th></tr>");

			while(rs.next()){

			int loanId = rs.getInt("loan_id");

			PreparedStatement ps2 = con.prepareStatement(
			"SELECT COALESCE(SUM(amount),0) FROM payments WHERE loan_id=?"
			);

			ps2.setInt(1, loanId);
			ResultSet r2 = ps2.executeQuery();
			r2.next();

			double totalPaid = r2.getDouble(1);
			double remaining = rs.getDouble("loan_amount") - totalPaid;
			int installment = (int)(totalPaid / rs.getDouble("emi"));

			out.println("<tr>");

			out.println("<td><a href='CustomerDetailsServlet?customer_id="+rs.getInt("customer_id")+"'>"+rs.getString("name")+"</a></td>");
			out.println("<td>"+loanId+"</td>");
			out.println("<td>₹"+rs.getDouble("amount")+"</td>");
			out.println("<td>"+installment+"</td>");
			out.println("<td>₹"+remaining+"</td>");

			out.println("</tr>");
			}

			out.println("</table>");

			}catch(Exception e){e.printStackTrace();}
			}
}
