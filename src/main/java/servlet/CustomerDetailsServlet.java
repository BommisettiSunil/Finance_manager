package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.LoanDAO;
import dao.PaymentDAO;

@WebServlet("/CustomerDetailsServlet")
public class CustomerDetailsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		/* Load Header + Navbar */

		request.getRequestDispatcher("components/header.jsp").include(request,response);
		request.getRequestDispatcher("components/navbar.jsp").include(request,response);

		PrintWriter out=response.getWriter();

		int customerId=Integer.parseInt(request.getParameter("customer_id"));

		try{

			ResultSet rs=LoanDAO.getCustomerLoans(customerId);

			out.println("<div class='container mt-4'>");

			out.println("<h2 class='mb-4'>Customer Loan Details</h2>");

			while(rs.next()){

				int loanId=rs.getInt("loan_id");
				double loanAmount=rs.getDouble("loan_amount");
				String startDate=rs.getString("start_date");

				/* DAO Call for Payment */

				double paid=PaymentDAO.getTotalPaid(loanId);

				double remaining=loanAmount-paid;

				out.println("<div class='card shadow mb-4'>");

				out.println("<div class='card-body'>");

				out.println("<h5 class='card-title'>Loan ID : "+loanId+"</h5>");

				out.println("<p>Amount : ₹"+loanAmount+"</p>");

				out.println("<p>Disbursed On : "+startDate+"</p>");

				out.println("<p class='text-success'>Total Paid : ₹"+paid+"</p>");

				out.println("<p class='text-danger'>Remaining Balance : ₹"+remaining+"</p>");

				out.println("<a class='btn btn-primary me-2' href='InstallmentServlet?loan_id="+loanId+"'>View Details</a>");

				out.println("<a class='btn btn-success' href='addPayment.jsp?loan_id="+loanId+"'>Collect EMI</a>");

				out.println("</div>");

				out.println("</div>");

			}

			out.println("</div>");

		}catch(Exception e){
			e.printStackTrace();
		}

		/* Footer */

		request.getRequestDispatcher("components/footer.jsp").include(request,response);

	}

}
