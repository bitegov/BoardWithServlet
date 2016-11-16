package view;

import Model.DAO.DaoToMSSQL;
import Model.Manage.BoardManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
        try {
            BoardManager.getOurInstance().insertWriter();
            out.println(BoardManager.getOurInstance().SelectAllOfWriter().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println("</BODY>");
        out.println("</HTML>");
        out.close();

    }
}
