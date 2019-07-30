package doc;

import service.LoginService;

import javax.servlet.ServletConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String email = request.getParameter("femail");
        String password = request.getParameter("fpassword");
        boolean valid [] = new LoginService(email, password).validateUser();
        if (valid[0] == true ){
       if( valid[1]==true){
           HttpSession session = request.getSession();
            session.setAttribute("email", email);
          out.print("correct");
    // response.sendRedirect("view.html");
  //         request.getRequestDispatcher("view.html").forward(request,response);
       } else {  out.print("wrong");
       }} else {
            out.print("signup");

        }
    }
}

