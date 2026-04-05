package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.LoanDAO;
import dao.PaymentDAO;

@WebServlet("/ViewLoansServlet")
public class ViewLoansServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		request.getRequestDispatcher("components/header.jsp").include(request,response);
		request.getRequestDispatcher("components/navbar.jsp").include(request,response);

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		if(session == null || session.getAttribute("admin_id") == null){

			response.sendRedirect("login.jsp");
			return;

		}

		int adminId = (int) session.getAttribute("admin_id");

		try{

			ResultSet rs = LoanDAO.getAllLoans(adminId);

			out.println("<div class='container mt-4'>");

			out.println("<h2 class='mb-4'>Loan List</h2>");

			out.println("<table class='table table-bordered table-striped'>");

			out.println("<thead class='table-dark'>");

			out.println("<tr>");
			out.println("<th>Loan ID</th>");
			out.println("<th>Customer ID</th>");
			out.println("<th>Loan Amount</th>");
			out.println("<th>Interest</th>");
			out.println("<th>Duration</th>");
			out.println("<th>EMI</th>");
			out.println("<th>Total Paid</th>");
			out.println("<th>Remaining</th>");
			out.println("</tr>");

			out.println("</thead>");
			out.println("<tbody>");

			while(rs.next()){

				int loanId = rs.getInt("loan_id");
				int customerId = rs.getInt("customer_id");

				double loanAmount = rs.getDouble("loan_amount");
				double interest = rs.getDouble("interest_rate");
				int duration = rs.getInt("duration");
				double emi = rs.getDouble("emi");

				double paid = PaymentDAO.getTotalPaid(loanId);

				double remaining = loanAmount - paid;

				out.println("<tr>");

				out.println("<td>"+loanId+"</td>");
				out.println("<td>"+customerId+"</td>");
				out.println("<td>₹"+loanAmount+"</td>");
				out.println("<td>"+interest+"%</td>");
				out.println("<td>"+duration+"</td>");
				out.println("<td>₹"+emi+"</td>");
				out.println("<td class='text-success'>₹"+paid+"</td>");
				out.println("<td class='text-danger'>₹"+remaining+"</td>");

				out.println("</tr>");

			}

			out.println("</tbody>");
			out.println("</table>");
			out.println("</div>");

		}catch(Exception e){
			e.printStackTrace();
		}

		request.getRequestDispatcher("components/footer.jsp").include(request,response);

	}

}
