package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import util.DBConnection;


@WebServlet("/TotalLoanServlet")
public class TotalLoanServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			response.setContentType("text/html");

			request.getRequestDispatcher("components/header.jsp").include(request,response);
			request.getRequestDispatcher("components/navbar.jsp").include(request,response);

			PrintWriter out = response.getWriter();

			HttpSession session = request.getSession(false);
			int adminId = (int) session.getAttribute("admin_id");

			try{

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(
			"SELECT c.customer_id, c.name, l.loan_id, l.loan_amount, l.interest_rate, l.duration " +
			"FROM loans l JOIN customers c ON l.customer_id=c.customer_id WHERE c.admin_id=?"
			);

			ps.setInt(1, adminId);

			ResultSet rs = ps.executeQuery();

			out.println("<div class='container mt-4'>");
			out.println("<h2>Total Loans Given</h2>");

			out.println("<table class='table table-bordered'>");
			out.println("<tr><th>Name</th><th>Customer ID</th><th>Loan ID</th><th>Amount</th></tr>");

			while(rs.next()){

			out.println("<tr>");

			out.println("<td>");
			out.println("<a href='CustomerDetailsServlet?customer_id="+rs.getInt("customer_id")+"'>");
			out.println(rs.getString("name"));
			out.println("</a>");
			out.println("</td>");

			out.println("<td>"+rs.getInt("customer_id")+"</td>");
			out.println("<td>"+rs.getInt("loan_id")+"</td>");
			out.println("<td>₹"+rs.getDouble("loan_amount")+"</td>");

			out.println("</tr>");
			}

			out.println("</table></div>");

			}catch(Exception e){
			e.printStackTrace();
			}

			request.getRequestDispatcher("components/footer.jsp").include(request,response);
			}
}
