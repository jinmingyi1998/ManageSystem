package Source;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usr = request.getParameter("username");
        String pwd = request.getParameter("password");
        System.out.println("login:"+usr+"---"+pwd);
        if (usr != null && !usr.equals("") && pwd != null && !pwd.equals("")) {
            if (SqlConn.checkUser(usr, pwd)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", usr);
                session.setAttribute("password",pwd);
                System.out.println(session.toString());
                System.out.println(session.getAttribute("username"));
            }
        }
        request.getRequestDispatcher("dorequest").forward(request, response);
    }
}
