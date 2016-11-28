package Controller;

import Model.Manage.BoardManager;
import view.HelloWorld;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jerry on 2016-11-11.
 */
public class BoardController extends HttpServlet {

    BoardManager boardManager = BoardManager.getOurInstance();

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)throws IOException, ServletException{
        //// TODO: 2016-11-11 web.xml로 관리하는 경로와 아래 조건절의 조건과 맞아야 한다. 따라서 이부분 메소드는 xml에 종속적이다. 따라서 web.xml만 사용을 하던가,
        // 자동으로 파싱하여 경로를 가져와서 아래 조건과 맞는 방안이 필요. 그런데 컨트롤러를 자바 단에서 수행 할 때 장점이 있는가?
        // - 장점 1. Web.xml보다 조금 더 세밀하게 자원의 흐름을 조정 할 수 있다.
        if(httpServletRequest.getRequestURI().equals("/writing")){
            write(httpServletRequest,httpServletResponse);
        }else {
            httpServletRequest.getRequestDispatcher("HelloWorld").forward(httpServletRequest,httpServletResponse) ;
        }
    }

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)throws IOException, ServletException{
        //// TODO: 2016-11-11 web.xml로 관리하는 경로와 아래 조건절의 조건과 맞아야 한다. 따라서 이부분 메소드는 xml에 종속적이다. 따라서 web.xml만 사용을 하던가,
        // 자동으로 파싱하여 경로를 가져와서 아래 조건과 맞는 방안이 필요. 그런데 컨트롤러를 자바 단에서 수행 할 때 장점이 있는가?
        // - 장점 1. Web.xml보다 조금 더 세밀하게 자원의 흐름을 조정 할 수 있다.
        if(httpServletRequest.getRequestURI().equals("/writing")){
            String title = httpServletRequest.getParameter("title");
            String body = httpServletRequest.getParameter("body");
            String email = httpServletRequest.getParameter("email");
            String password = httpServletRequest.getParameter("password");
            System.out.println(title+body+email+password);
            boardManager.insertArticle(title,body,email,password);
        }else {
            httpServletRequest.getRequestDispatcher("HelloWorld").forward(httpServletRequest,httpServletResponse) ;
        }
    }

    public void write(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)throws IOException, ServletException{
        httpServletRequest.getRequestDispatcher("/View/Insert.jsp").forward(httpServletRequest,httpServletResponse) ;
    }

}
