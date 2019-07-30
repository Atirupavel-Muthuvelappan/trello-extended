package doc;

import service.Database;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ChangePassword extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String password=request.getParameter("fpassword");
        String name=request.getParameter("fname");
        String email=request.getParameter("femail");
        new Database(email,password,name).addUser();
        response.setStatus(201);
    }

}
