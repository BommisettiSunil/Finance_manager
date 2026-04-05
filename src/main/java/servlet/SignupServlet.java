package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UserDAO;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

String name = request.getParameter("name");
String username = request.getParameter("username");
String password = request.getParameter("password");

try{

UserDAO.signup(name, username, password);

response.setContentType("text/html");

PrintWriter out = response.getWriter();

out.println("<h3>Registration Successful</h3>");
out.println("<a href='login.jsp'>Go to Login</a>");

}catch(Exception e){
e.printStackTrace();
}

}

}
