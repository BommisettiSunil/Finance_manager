package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoanDAO;
import model.Loan;


@WebServlet("/AddLoanServlet")
public class AddLoanServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int customerId = Integer.parseInt(request.getParameter("customer_id"));
		double loanAmount = Double.parseDouble(request.getParameter("loan_amount"));
		double interestRate = Double.parseDouble(request.getParameter("interest_rate"));
		int duration = Integer.parseInt(request.getParameter("duration"));
		String startDate = request.getParameter("start_date");
		String interestType = request.getParameter("interest_type");

		/* EMI calculation */

		double emi = 0;

		if("MONTHLY_FLAT".equals(interestType)){

		    // Flat interest per month
		    double monthlyInterest = (loanAmount * interestRate / 100) / duration;

		    emi = (loanAmount / duration) + monthlyInterest;

		}else{

		    // Normal EMI (reducing balance)
		    double monthlyRate = interestRate / 12 / 100;

		    emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, duration)) /
		          (Math.pow(1 + monthlyRate, duration) - 1);
		}

		/* create loan object */

		Loan loan = new Loan();

		loan.setCustomerId(customerId);
		loan.setLoanAmount(loanAmount);
		loan.setInterestRate(interestRate);
		loan.setDuration(duration);
		loan.setEmi(emi);
		loan.setStartDate(java.sql.Date.valueOf(startDate));
		loan.setInterestType(interestType);

		/* DAO call */

		LoanDAO.addLoan(loan);

		response.sendRedirect("addLoan.jsp?msg=success");
}
}
