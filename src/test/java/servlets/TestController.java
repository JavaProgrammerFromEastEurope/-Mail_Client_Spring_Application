package servlets;

import classes.TestModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TestController", urlPatterns = "/TestController")
public class TestController extends HttpServlet {
    private TestModel myModel = new TestModel();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("Mail", myModel.getMail());

        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        req.setAttribute("list",list);
        Map<Integer, String> nameMap = new HashMap<>();
        nameMap.put(0,"Mike");
        nameMap.put(1,"Gregory");
        nameMap.put(2,"Pole");
        req.setAttribute("NameMap",nameMap);
        System.out.println(req.getAttribute("Mail"));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/example.jsp");
        requestDispatcher.forward(req, resp);
    }
}
