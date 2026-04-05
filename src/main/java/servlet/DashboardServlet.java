package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import util.DBConnection;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			HttpSession session = request.getSession(false);

			// 🔐 Session check
			if(session == null || session.getAttribute("admin_id") == null){
			response.sendRedirect("login.jsp");
			return;
			}

			int adminId = (int) session.getAttribute("admin_id");

			double totalLoan = 0;
			double totalPaid = 0;
			double totalInterest = 0;
			double todayCollection = 0;

			try{

			Connection con = DBConnection.getConnection();


			// 🔹 TOTAL LOAN GIVEN
			PreparedStatement ps1 = con.prepareStatement(
			"SELECT COALESCE(SUM(l.loan_amount),0) FROM loans l " +
			"JOIN customers c ON l.customer_id=c.customer_id WHERE c.admin_id=?"
			);

			ps1.setInt(1, adminId);
			ResultSet rs1 = ps1.executeQuery();

			if(rs1.next()){
			totalLoan = rs1.getDouble(1);
			}


			// 🔹 TOTAL PAID
			PreparedStatement ps2 = con.prepareStatement(
			"SELECT COALESCE(SUM(p.amount),0) FROM payments p " +
			"JOIN loans l ON p.loan_id=l.loan_id " +
			"JOIN customers c ON l.customer_id=c.customer_id WHERE c.admin_id=?"
			);

			ps2.setInt(1, adminId);
			ResultSet rs2 = ps2.executeQuery();

			if(rs2.next()){
			totalPaid = rs2.getDouble(1);
			}


			// 🔹 TOTAL INTEREST (PROFIT)
			PreparedStatement ps4 = con.prepareStatement(
					"SELECT l.loan_amount, COALESCE(SUM(p.amount),0) as paid " +
					"FROM loans l " +
					"LEFT JOIN payments p ON l.loan_id=p.loan_id AND p.type='EMI' " +
					"JOIN customers c ON l.customer_id=c.customer_id " +
					"WHERE c.admin_id=? GROUP BY l.loan_id"
					);

					ps4.setInt(1, adminId);

					ResultSet rs4 = ps4.executeQuery();

					while(rs4.next()){

					    double loan = rs4.getDouble("loan_amount");
					    double paid = rs4.getDouble("paid");

					    if(paid > loan){
					        totalInterest += (paid - loan);
					    }
					}


			// 🔹 TODAY COLLECTION
			PreparedStatement ps3 = con.prepareStatement(
					"SELECT COALESCE(SUM(p.amount),0) FROM payments p " +
					"JOIN loans l ON p.loan_id=l.loan_id " +
					"JOIN customers c ON l.customer_id=c.customer_id " +
					"WHERE p.payment_date = CURDATE() AND c.admin_id=? AND p.type='EMI'"
					);
			ps3.setInt(1, adminId);
			ResultSet rs3 = ps3.executeQuery();

			if(rs3.next()){
			todayCollection = rs3.getDouble(1);
			}


			// 🔹 SET ONLY TOTAL VALUES (NO TABLE DATA)
			request.setAttribute("totalLoan", totalLoan);
			request.setAttribute("interestCollected", totalInterest);
			request.setAttribute("todayCollection", todayCollection);
			request.setAttribute("allDue", 0); // handled by AJAX


			// 🔹 FORWARD
			request.getRequestDispatcher("index.jsp").forward(request, response);

			}catch(Exception e){
			e.printStackTrace();
			}
			}
}