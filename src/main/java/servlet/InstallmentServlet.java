package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.LoanDAO;
import dao.PaymentDAO;
import model.Loan;

@WebServlet("/InstallmentServlet")
public class InstallmentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		/* Load header + navbar */

		request.getRequestDispatcher("components/header.jsp").include(request,response);
		request.getRequestDispatcher("components/navbar.jsp").include(request,response);

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String msg = (String) session.getAttribute("msg");

		if(msg != null){

		out.println("<div class='container mt-3'>");
		out.println("<div class='alert alert-success'>"+msg+"</div>");
		out.println("</div>");

		session.removeAttribute("msg");

		}

		int loanId = Integer.parseInt(request.getParameter("loan_id"));

		try{

			/* DAO call */

			Loan loan = LoanDAO.getLoanById(loanId);

			if(loan != null){

			    double emi = loan.getEmi();
			    int duration = loan.getDuration();
			    LocalDate start = loan.getStartDate().toLocalDate();

			

				/* DAO call for payments */

				int paidInstallments = PaymentDAO.getPaidInstallments(loanId);
				out.println("<div class='container mt-4'>");

				out.println("<h2 class='mb-4'>Installment Details</h2>");

				for(int i=1;i<=duration;i++){

					LocalDate dueDate = start.plusDays(i*30);

					out.println("<div class='card mb-3 shadow'>");

					out.println("<div class='card-body'>");

					if(i <= paidInstallments){

						out.println("<h5>"+i+" Installment ✔ Paid</h5>");
						out.println("<p>Amount : ₹"+emi+"</p>");
						out.println("<p>Due On : "+dueDate+"</p>");

					}else{

						out.println("<h5>"+i+" Installment</h5>");
						out.println("<p>Amount : ₹"+emi+"</p>");
						out.println("<p>Due On : "+dueDate+"</p>");

						out.println("<a class='btn btn-warning btn-sm me-2' href='SendReminderServlet?loan_id="+loanId+"'>Send Reminder</a>");

						out.println("<a class='btn btn-danger btn-sm me-2' href='AddPenaltyServlet?loan_id="+loanId+"'>Add Penalty</a>");

						out.println("<a class='btn btn-success btn-sm' href='addPayment.jsp?loan_id="+loanId+"'>Collect EMI</a>");

					}

					out.println("</div>");
					out.println("</div>");

				}

				out.println("</div>");

			}

		}catch(Exception e){
			e.printStackTrace();
		}

		/* footer */

		request.getRequestDispatcher("components/footer.jsp").include(request,response);

	}

}