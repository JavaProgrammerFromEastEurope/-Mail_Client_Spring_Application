package servlets;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import static java.lang.System.out;

@WebServlet(name = "SessionServlet", urlPatterns = "/SessionServlet", asyncSupported = true )
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getHeader("Accept-Encoding").contains("gzip")) {
            PrintWriter printWriter = new PrintWriter(new GZIPOutputStream(resp.getOutputStream()));
            printWriter.write("hello world");
        }

        out.println("redirect: http://localhost:8080/Mail_Client_Web_Application_war_exploded/MailClient");
        resp.sendRedirect("http://localhost:8080/Mail_Client_Web_Application_war_exploded/MailClient");
        resp.setHeader("Refresh", "1");
        resp.setHeader("Refresh", "5;https://google.com");
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "You're not allowed here! Please, close this page!");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            out.println(cookie.getName());
            out.println(cookie.getValue());
        }
        Cookie cookie = new Cookie("testCookie", "25277E09FCD88A9B1B696C26050FF2A9");
        cookie.setMaxAge(10);
        cookie.setPath("/SessionServlet");
        resp.addCookie(cookie);
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long startTime = System.nanoTime();
        final AsyncContext asyncContext = req.startAsync(req, resp);

        ServletResponse response = asyncContext.getResponse();
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("exiting doGet() ==> thread name: " + Thread.currentThread().getName() + "<br>");
        new Thread(() -> {
            try {
                Thread.sleep(100);
                out.println("entering doGet() ==> thread name: " + Thread.currentThread().getName() + "<br>");
                out.print("Work completed. Time elapsed: " + (System.nanoTime() - startTime));
                out.flush();
                asyncContext.complete();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
