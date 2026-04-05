package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.CustomerDAO;

@WebServlet("/SearchCustomerServlet")
public class SearchCustomerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		/* LOAD HEADER + NAVBAR */

		request.getRequestDispatcher("components/header.jsp").include(request, response);
		request.getRequestDispatcher("components/navbar.jsp").include(request, response);

		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");

		/* SESSION CHECK */

		HttpSession session = request.getSession(false);

		if(session == null || session.getAttribute("admin_id") == null){

			response.sendRedirect("login.jsp");
			return;

		}

		int adminId = (int) session.getAttribute("admin_id");

		try{

			/* DAO CALL */

			ResultSet rs = CustomerDAO.searchCustomer(name, adminId);

			out.println("<div class='container mt-4'>");

			out.println("<h2 class='mb-4'>Search Results</h2>");

			out.println("<table class='table table-bordered table-striped table-hover'>");

			out.println("<thead class='table-dark'>");

			out.println("<tr>");
			out.println("<th>ID</th>");
			out.println("<th>Name</th>");
			out.println("<th>Phone</th>");
			out.println("<th>Address</th>");
			out.println("</tr>");

			out.println("</thead>");

			out.println("<tbody>");

			while(rs.next()){

				out.println("<tr>");

				out.println("<td>"+rs.getInt("customer_id")+"</td>");

				out.println("<td>");
				out.println("<a class='text-decoration-none' href='CustomerDetailsServlet?customer_id="+rs.getInt("customer_id")+"'>");
				out.println(rs.getString("name"));
				out.println("</a>");
				out.println("</td>");

				out.println("<td>"+rs.getString("phone")+"</td>");

				out.println("<td>"+rs.getString("address")+"</td>");

				out.println("</tr>");

			}

			out.println("</tbody>");
			out.println("</table>");

			out.println("</div>");

		}catch(Exception e){
			e.printStackTrace();
		}

		/* LOAD FOOTER */

		request.getRequestDispatcher("components/footer.jsp").include(request, response);

	}

}
