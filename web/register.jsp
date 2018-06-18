<%--
  Created by IntelliJ IDEA.
  User: JINMY
  Date: 2018/6/18
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>Sign Up</title>
    <script>
        function checksame() {
            if ($("#password").val() != $("#password2").val()) {
                $('[data-toggle="popover"]').popover();
            }
        }

        <%
        if (request.getAttribute("register")!=null)
         {   if ((boolean)request.getAttribute("register")==true)
            {
        %>
        $(function () {
            $("#successModal").modal('show');
            setTimeout(2000,"window.location='dorequest'")
        })
        <%
            }else
                {
            %>
        $(function () {
            $("#failModal").modal('show');
        })
        <%
        }
         }
    %>
    </script>
</head>
<body>
<div class="modal fade" id="successModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                Success
            </div>
            <div class="modal-footer">
                <a href="dorequest" type="button" class="btn btn-secondary" data-dismiss="modal">关闭</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="failModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                Faild
            </div>
            <div class="modal-footer">
                <a href="dorequest" type="button" class="btn btn-secondary" data-dismiss="modal">关闭</a>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <form action="register">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" class="form-control" name="username" required pattern="^[a-zA-Z]\w{5,14}$">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" class="form-control" name="password" required pattern="^\w{6-15}$">
        </div>
        <div class="form-group">
            <label for="password2">Password Again</label>
            <input type="password" id="password2" class="form-control" required pattern="^\w{6-15}$"
                   onkeyup="checksame()" data-toggle="popover" data-placement="right"
                   data-content="Password should be same">
        </div>
        <input type="submit" class="btn btn-primary" value="Sign in">
    </form>
</div>
</body>
</html>
