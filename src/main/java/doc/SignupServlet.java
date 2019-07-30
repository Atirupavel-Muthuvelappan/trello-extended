package doc;
import service.Database;
import service.LoginService;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class SignupServlet extends HttpServlet {
    String username;
    String password ;
    String email;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
         username = request.getParameter("fname");
         password = request.getParameter("fpassword");
         email=request.getParameter("femail");
        boolean check[]=new LoginService(email,password).validateUser();
     if(check[0]==true)
        {out.print("exist");}
     else{
         if(!this.username.equals("none") && !this.password.equals("none")){
    HttpSession session =request.getSession();
    session.setAttribute("email",email);
        new Database(email,password,username).addUser();}
        out.print("new");

}}}

