package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;


@WebServlet("/InterestServlet")
public class InterestServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

			PrintWriter out = response.getWriter();

			HttpSession session = request.getSession(false);
			int adminId = (int) session.getAttribute("admin_id");

			try{

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(
			"SELECT c.customer_id,c.name,l.loan_id,l.loan_amount,COALESCE(SUM(p.amount),0) as paid FROM loans l JOIN customers c ON l.customer_id=c.customer_id LEFT JOIN payments p ON l.loan_id=p.loan_id WHERE c.admin_id=? GROUP BY l.loan_id"
			);

			ps.setInt(1, adminId);

			ResultSet rs = ps.executeQuery();

			out.println("<h4>Interest Details</h4>");
			out.println("<table class='table table-bordered'>");
			out.println("<tr><th>Name</th><th>Loan</th><th>Principal</th><th>Paid</th><th>Interest</th></tr>");

			while(rs.next()){

			double loan = rs.getDouble("loan_amount");
			double paid = rs.getDouble("paid");
			double interest = paid - loan;
			if(interest < 0) interest = 0;

			out.println("<tr>");

			out.println("<td><a href='CustomerDetailsServlet?customer_id="+rs.getInt("customer_id")+"'>"+rs.getString("name")+"</a></td>");
			out.println("<td>"+rs.getInt("loan_id")+"</td>");
			out.println("<td>₹"+loan+"</td>");
			out.println("<td>₹"+paid+"</td>");
			out.println("<td class='text-success'>₹"+interest+"</td>");

			out.println("</tr>");
			}

			out.println("</table>");

			}catch(Exception e){e.printStackTrace();}
			}

}
