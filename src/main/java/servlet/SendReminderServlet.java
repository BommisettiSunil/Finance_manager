package servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.LoanDAO;

@WebServlet("/SendReminderServlet")
public class SendReminderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int loanId = Integer.parseInt(request.getParameter("loan_id"));

		HttpSession session = request.getSession();

		try{

			ResultSet rs = LoanDAO.getReminderDetails(loanId);

			if(rs.next()){

				String name = rs.getString("name");
				String email = rs.getString("email");
				double emi = rs.getDouble("emi");

				EmailSender.sendEmail(
						email,
						"EMI Reminder",
						"Dear "+name+", your EMI of ₹"+emi+" is due. Please pay on time."
						);

				/* store message in session */

				session.setAttribute("msg","Reminder Sent Successfully");

			}

		}catch(Exception e){
			e.printStackTrace();
		}

		/* redirect back to installment page */

		response.sendRedirect("InstallmentServlet?loan_id="+loanId);

	}

}