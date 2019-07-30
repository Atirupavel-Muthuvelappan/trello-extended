package doc;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionCheck extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
response.setContentType("text/html");
      PrintWriter out=response.getWriter();
      if(request.getSession(false)!=null){
         out.print("yes");
      }else
  out.print("no");
  }
}
