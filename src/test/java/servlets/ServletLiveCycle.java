package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.System.*;

@WebServlet(name = "ServletLiveCycle", urlPatterns = "/LiveCycle")
public class ServletLiveCycle extends HttpServlet {
    @Override
    public void init() throws ServletException {
        out.println("initialization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out.println("do get method");
        String s = "<html>"
                + "<header><title>this is title</title></header>"
                + "<body>"
                + "Hello World"
                + "</body>"
                + "</html>";
        resp.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out.println("service method");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        out.println("destroy");
    }
}
