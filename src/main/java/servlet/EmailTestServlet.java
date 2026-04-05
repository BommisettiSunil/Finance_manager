package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EmailTestServlet")
public class EmailTestServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EmailSender.sendEmail(
                "customer@gmail.com",
                "EMI Reminder",
                "Your EMI payment is due. Please pay on time."
        );

        response.getWriter().println("Email Sent");
    }
}
