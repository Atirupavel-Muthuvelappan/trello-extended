package doc;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PushQueue  extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        String email = request.getParameter("email");

        Queue queue = QueueFactory.getDefaultQueue();
        queue.add(TaskOptions.Builder.withUrl("/change").method(TaskOptions.Method.POST).param("type","change").param("email", email));
        response.sendRedirect("login.html");
    }
}
