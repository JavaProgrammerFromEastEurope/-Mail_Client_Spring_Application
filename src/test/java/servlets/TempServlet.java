package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import static java.lang.System.out;

@WebServlet(name = "TempServlet", urlPatterns = "/temp")
public class TempServlet extends HttpServlet {

    private int i = 0;

    private String[] sender = new String[0];
    private String[] receiver = new String[0];
    private String[] mailPass = new String[0];
    private String[] subjectMessage = new String[0];
    private String[] message = new String[0];

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        synchronized (this) {
            for (int j = 0; j < 1_000_000; j++) {
                i++;
            }
            out.println(i);
//            out.println(Thread.currentThread().getName());


            String first = req.getParameter("first");
            String second = req.getParameter("second");
            String third = req.getParameter("third");
            out.println(first);
            String[] oneValues = req.getParameterValues("one");
            for (String s : oneValues) {
                out.println(s);
            }
            Enumeration<String> parametersNames = req.getParameterNames();
            while (parametersNames.hasMoreElements()) {
                String element = parametersNames.nextElement();
                out.println(MessageFormat.format("{0} = {1}", element, req.getParameter(element)));
            }
            Map<String, String[]> parameterMap = req.getParameterMap();
            out.println(req.getRequestURL());
            out.println(req.getServletPath());
            out.println(req.getRemoteHost());
            out.println(req.getRequestURI());
            out.println(req.getLocalPort());
            out.println(req.getQueryString());
            out.println("run method get");
            doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out.println("run method post");


        String s = "<html>"
                + "<header><title>this is title</title></header>"
                + "<body>"
//                + " one = " + one + "<br>"
//                + " two = " + two + "<br>"
//                + " textarea = " + textarea + "<br>"
                + "<form action='temp' method='post'>"      + "<br>"
                + "<input type = 'text' name='one'>"        + "<br>"
                + "<input type = 'text' name='two'>"        + "<br>"
                + "<textarea name='textarea'></textarea>"   + "<br>"
                + "<input type = 'submit' name='submit'>"   + "<br>"
                + "</form>"
                + "</body>"
                + "</html>";

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        initializeVariables(req, resp);
        String sender = req.getParameter("sender");
        String receiver = req.getParameter("receiver");
        String sendPass = req.getParameter("sendPass");
        String subjMes = req.getParameter("subjMes");
        String message = req.getParameter("message");

        resp.setStatus(HttpServletResponse.SC_OK);
        HttpSession session = req.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        session.setAttribute("one", "two");
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            out.println(attributeName + " = " + session.getAttribute(attributeName));
            out.println(session.getMaxInactiveInterval());
        }
        session.removeAttribute("one");


        subjMes = subjMes == null
                ? "" : subjMes.replaceAll("<", "&lt;").replace(">", "&gt;");


//        out.println(Arrays.toString(sender));
//        out.println(Arrays.toString(receiver));
//        out.println(Arrays.toString(mailPass));
//        out.println(Arrays.toString(subjectMessage));
//        out.println(Arrays.toString(message));
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            out.println(header + " = " + req.getHeader(header));
        }
        resp.setHeader("Content-Length", "100");
    }

    //        @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        synchronized (this) {
//            for (int j = 0; j < 1_000_000; j++) {
//                i++;
//            }
//            out.println(i);
//        }
//    }
    private void initializeVariables(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> parameterMap = req.getParameterMap();

        parameterMap.forEach((key, value) -> {
            if (key.equals("sender")) {
                sender = value;
            }
            if (key.equals("receiver")) {
                receiver = value;
            }
            if (key.equals("sendPass")) {
                mailPass = value;
            }
            if (key.equals("subjMes")) {
                subjectMessage = value;
            }
            if (key.equals("message")) {
                message = value;
            }
        });
        for (String entryMessage : message) {
            entryMessage = entryMessage == null ? ""
                    : entryMessage.replaceAll("<", "&lt;").replace(">", "&gt;");
        }
    }

    public static void main(String[] args) {
        for (int j = 0; j < 2; j++) {
            new Thread(() -> {
                try {
                    URLConnection urlConnection =
                            new URL("http://localhost:8080/Mail_Client_Web_Application_war_exploded//MailClient")
                                    .openConnection();
                    urlConnection.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
