package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProgressServlet", urlPatterns = "/ProgressServlet", asyncSupported = false)
public class ProgressServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><head><title>Progress Servlet</title></head><body>");
        printWriter.println("entering doGet() ==> thread name: " + Thread.currentThread().getName() + "<br>");
        printWriter.println("<progress id='progress' max='100' style='display: block'></progress>");
        ProgressAsyncServlet.threadProgressBar(printWriter);
    }
}
