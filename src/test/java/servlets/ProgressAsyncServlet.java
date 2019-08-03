package servlets;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProgressAsyncServlet", urlPatterns = "/ProgressAsyncServlet", asyncSupported = true)
public class ProgressAsyncServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><head><title>Progress Async Servlet</title></head><body>");
        printWriter.println("entering doGet() ==> thread name: " + Thread.currentThread().getName() + "<br>");
        printWriter.println("<progress id='progress' max='100' style='display: block'></progress>");
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            printWriter.println("thread name within asyncContext.start((): " + Thread.currentThread().getName() + "<br>");
            threadProgressBar(printWriter);
            asyncContext.complete();
        });
    }

    static void threadProgressBar(PrintWriter printWriter) {

        for (int i = 0; i <= 100; i++) {
            printWriter.println("<script>document.getElementById('progress').value=\"" + i + "</script>");
            printWriter.flush();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printWriter.println("<script>document.getElementById('progress').style.display='none';</script>");
        printWriter.println("[time consuming generating and returned result]<br>");
        printWriter.println("exiting doGet() ==> thread name: " + Thread.currentThread().getName() + "<br>");
        printWriter.println("</body></html>");
    }
}
