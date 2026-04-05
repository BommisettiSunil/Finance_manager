package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.PaymentDAO;

@WebServlet("/ViewPaymentsServlet")
public class ViewPaymentsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		/* header + navbar */

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

			ResultSet rs = PaymentDAO.getPayments(adminId);

			out.println("<div class='container mt-4'>");

			out.println("<h2 class='mb-4'>Payment History</h2>");

			out.println("<table class='table table-bordered table-striped'>");

			out.println("<thead class='table-dark'>");

			out.println("<tr>");
			out.println("<th>Payment ID</th>");
			out.println("<th>Loan ID</th>");
			out.println("<th>Amount</th>");
			out.println("<th>Payment Date</th>");
			out.println("</tr>");

			out.println("</thead>");

			out.println("<tbody>");

			while(rs.next()){

				out.println("<tr>");

				out.println("<td>"+rs.getInt("payment_id")+"</td>");

				out.println("<td>"+rs.getInt("loan_id")+"</td>");

				out.println("<td class='text-success'>₹"+rs.getDouble("amount")+"</td>");

				out.println("<td>"+rs.getDate("payment_date")+"</td>");

				out.println("</tr>");

			}

			out.println("</tbody>");
			out.println("</table>");

			out.println("</div>");

		}catch(Exception e){
			e.printStackTrace();
		}

		/* footer */

		request.getRequestDispatcher("components/footer.jsp").include(request,response);

	}

}
