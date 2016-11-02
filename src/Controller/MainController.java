package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jerry on 2016-11-01.
 */
public class MainController extends HttpServlet {
    public MainController() {
        System.out.println("만들어졌다!!!");
    }

    public void destroy() {
    super.destroy();
        System.out.println("부셔졌다!!!");
    }

    public void init() throws ServletException {
        super.destroy();
        System.out.println("init!!!");
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)throws IOException,ServletException {

        httpServletResponse.setContentType("text/html");
        httpServletRequest.setAttribute("todo", "10");
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/Mainboard.jsp");
        System.out.println(requestDispatcher.toString());
        System.out.println(httpServletRequest.getClass());
        System.out.println(requestDispatcher.getClass());
        requestDispatcher.forward(httpServletRequest, httpServletResponse);

        }
}
