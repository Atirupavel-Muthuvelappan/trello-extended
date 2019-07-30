package doc;
import com.google.gson.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class View extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);

        String  email=session.getAttribute("email").toString();

        JsonArray jsonArray =new ViewJson(email).fetch();
       if(jsonArray.size()<1){
           out.print(new Gson().toJson("nothing"));
       }
       else
        out.print(new Gson().toJson(jsonArray));
    }
}
