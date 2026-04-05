package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.CustomerDAO;
import model.Customer;

@WebServlet("/ViewCustomersServlet")
public class ViewCustomersServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		/* include layout */

		request.getRequestDispatcher("components/header.jsp").include(request,response);
		request.getRequestDispatcher("components/navbar.jsp").include(request,response);

		PrintWriter out = response.getWriter();

		/* session validation */

		HttpSession session = request.getSession(false);

		if(session == null || session.getAttribute("admin_id") == null){

			response.sendRedirect("login.jsp");
			return;

		}

		int adminId = (int) session.getAttribute("admin_id");

		try{

			/* DAO call */

			List<Customer> customers = CustomerDAO.getCustomers(adminId);

			out.println("<div class='container mt-4'>");

			out.println("<h2 class='mb-4'>Customer List</h2>");

			out.println("<table class='table table-bordered table-striped'>");

			out.println("<thead class='table-dark'>");

			out.println("<tr>");
			out.println("<th>ID</th>");
			out.println("<th>Name</th>");
			out.println("<th>Phone</th>");
			out.println("<th>Address</th>");
			out.println("<th>Action</th>");
			out.println("</tr>");

			out.println("</thead>");
			out.println("<tbody>");

			for(Customer c : customers){

				int id = c.getCustomerId();

				out.println("<tr>");

				out.println("<td>"+id+"</td>");

				out.println("<td>");
				out.println("<a href='CustomerDetailsServlet?customer_id="+id+"'>"+c.getName()+"</a>");
				out.println("</td>");

				out.println("<td>"+c.getPhone()+"</td>");

				out.println("<td>"+c.getAddress()+"</td>");

				out.println("<td>");

				out.println("<a class='btn btn-danger btn-sm' href='DeleteCustomerServlet?id="+id+"' ");
				out.println("onclick=\"return confirm('Delete this customer?')\">Delete</a>");

				out.println("</td>");

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
