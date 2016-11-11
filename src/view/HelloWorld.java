package view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jerry on 2016-11-01.
 */
public class HelloWorld extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)throws IOException,ServletException{
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.println("<HTML>");
        out.println("<BODY>");
        out.println("Hello World!!");
        out.println("</BODY>");
        out.println("</HTML>");
        out.close();
    }
}
