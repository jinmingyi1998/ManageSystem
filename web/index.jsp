<%@ page import="java.util.ArrayList,Source.SqlConn,Source.Person,Source.SqlUtils,Source.DBCPUtils" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>Manage System</title>
    <meta charset="utf-8"/>
    <script>
        $(function () {
            $(".updateform").hide();
            $(".updatespan").click(function () {
                $(".updatespan").show();
                $(".updateform").hide();
                $(this).siblings(".updateform").show();
                $(this).parent().find("span").hide();
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Manage System</h1>
    </div>
    <%
        if (request.getParameter("request") != null) {
            switch (request.getParameter("request")) {
                case "insert":
                    Person insertPerson = new Person();
                    insertPerson.setName(request.getParameter("name"));
                    insertPerson.setJob(request.getParameter("job"));
                    insertPerson.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")));
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
                    updateperson.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")));
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
    %>
    <div class="row">
        <div class="col-3">
            <form action="index.jsp?request=insert" method="post">
                <div class="form-group">
                    <label for="name">Name</label><input type="text" id="name" name="name" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="job">Job</label><input type="text" id="job" name="job" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="hiredate">Hiredate</label><input type="date" id="hiredate" name="date" class="form-control"
                                                                 required>
                </div>
                <div class="form-group">
                    <label for="sal">Salary</label><input type="number" pattern="^$[0-9]{0,8}$" id="sal" name="sal" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="dept">Department</label><input type="text" id="dept" name="dept" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="loc">Location</label><input type="text" id="loc" name="loc" class="form-control" required>
                </div>
                <div class="form-group">
                    <input type="submit" name="submit" value="Submit!" class="btn btn-primary">
                </div>
            </form>
        </div>
        <div class="col-9">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>job</th>
                    <th>hiredate</th>
                    <th>salary</th>
                    <th>department</th>
                    <th>location</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${persons}" var="person">
                    <tr>
                        <form action="index.jsp?request=update" method="post">
                            <td><span id="s${person.id}" class="updatespan">${person.id}</span>
                                <input type="text" id="${person.id}" value="${person.id}" name="id"
                                       class="form-control updateform" readonly></td>
                            <td><span id="s${person.id}" class="updatespan">${person.name}</span>
                                <input type="text" name="name" value="${person.name}" id="${person.id}"
                                       class="form-control updateform"></td>
                            <td><span id="s${person.id}" class="updatespan">${person.job}</span>
                                <input type="text" id="${person.id}" value="${person.job}" name="job"
                                       class="form-control updateform"></td>
                            <td><span id="s${person.id}" class="updatespan">${person.hiredate}</span>
                                <input type="date" id="${person.id}" name="date" value="${person.hiredate}"
                                       class="form-control updateform"></td>
                            <td><span id="s${person.id}" class="updatespan">${person.salary}</span>
                                <input type="number" id="${person.id}" name="sal" value="${person.salary}"
                                       class="form-control updateform" pattern="^[0-9]{0,8}$"></td>
                            <td><span id="s${person.id}" class="updatespan">${person.dept}</span>
                                <input type="text" id="${person.id}" name="dept" value="${person.dept}"
                                       class="form-control updateform"></td>
                            <td><span id="s${person.id}" class="updatespan">${person.location}</span>
                                <input type="text" id="${person.id}" name="loc" value="${person.location}"
                                       class="form-control updateform">
                                <input type="submit" style="display: none;">
                            </td>
                            <td><a href="index.jsp?request=delete&id=${person.id}" class="btn btn-primary">Delete</a></td>
                        </form>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>