package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

String username = request.getParameter("username");
String password = request.getParameter("password");

try{

ResultSet rs = UserDAO.login(username, password);

if(rs.next()){

int adminId = rs.getInt("user_id");

HttpSession session = request.getSession();

session.setAttribute("admin_id", adminId);

response.sendRedirect("DashboardServlet");

}else{

PrintWriter out = response.getWriter();
out.println("<h3>Invalid Username or Password</h3>");

}

}catch(Exception e){
e.printStackTrace();
}

}

}