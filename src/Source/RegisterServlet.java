package Source;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("username");
        String p = request.getParameter("password");
        request.setAttribute("register",false);
        if (s.matches("^[a-zA-Z]\\w{5,14}$") && p.matches("\\w{6,15}$")) {
            if (!SqlConn.checkUser(s, p)) {
                SqlConn.addUser(s, p);
                request.setAttribute("register", true);
                System.out.println("username : "+s+" password : "+p);
            }
        }
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }
}
