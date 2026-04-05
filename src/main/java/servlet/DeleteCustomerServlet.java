package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.CustomerDAO;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

int id = Integer.parseInt(request.getParameter("id"));

HttpSession session = request.getSession(false);

if(session == null || session.getAttribute("admin_id") == null){

response.sendRedirect("login.jsp");
return;

}

int adminId = (int) session.getAttribute("admin_id");

/* DAO CALL */

CustomerDAO.deleteCustomer(id, adminId);

response.sendRedirect("ViewCustomersServlet");

}

}
