package Source;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("request") != null) {
            switch (request.getParameter("request")) {
                case "insert":
                    Person insertPerson = new Person();
                    insertPerson.setName(request.getParameter("name"));
                    insertPerson.setJob(request.getParameter("job"));
                    try {
                        insertPerson.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    insertPerson.setSalary(Integer.parseInt(request.getParameter("sal")));
                    insertPerson.setDept(request.getParameter("dept"));
                    insertPerson.setLocation(request.getParameter("loc"));
                    SqlConn.insertPerson(insertPerson);
                    break;
                case "delete":
                    SqlConn.deletePersonById(Integer.parseInt(request.getParameter("id")));
                    break;
                case "update":
                    Person updateperson = new Person();
                    updateperson.setName(request.getParameter("name"));
                    updateperson.setJob(request.getParameter("job"));
                    try {
                        updateperson.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    updateperson.setSalary(Integer.parseInt(request.getParameter("sal")));
                    updateperson.setDept(request.getParameter("dept"));
                    updateperson.setLocation(request.getParameter("loc"));
                    updateperson.setId(Integer.parseInt(request.getParameter("id")));
                    SqlConn.updatePerson(updateperson);
                    break;
            }
        }
        ArrayList<Person> persons = SqlConn.getPersonList();
        System.out.println(persons.size());
        request.setAttribute("persons", persons);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
