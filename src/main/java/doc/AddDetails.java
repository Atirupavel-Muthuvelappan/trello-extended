package doc;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import service.Database;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AddDetails extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession=request.getSession(false);
      String email=  httpSession.getAttribute("email").toString();
        String data=request.getParameter("details");
        String people=request.getParameter("people");
       String person[]= people.split(",");
       ArrayList<String> members=new ArrayList<>(Arrays.asList(person));
       String date= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        new Database(email).addDetails(data,email,members,date);
   for(String mailId:members){
       Queue queue = QueueFactory.getDefaultQueue();
       queue.add(TaskOptions.Builder.withUrl("/change").method(TaskOptions.Method.POST).param("type","feed").param("email", mailId));
   }
response.setStatus(201);
    }
}
