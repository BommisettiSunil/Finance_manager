package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.CustomerDAO;
import model.Customer;

@WebServlet("/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("admin_id");

        Customer c = new Customer();

        c.setName(name);
        c.setPhone(phone);
        c.setAddress(address);
        c.setEmail(email);
        c.setAdminId(adminId);

        CustomerDAO.addCustomer(c);

        response.sendRedirect("addCustomer.jsp?msg=success");
    }
}
