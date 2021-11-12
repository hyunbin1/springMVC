package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyView {
     private String viewPath;

     public MyView(String viewPath){
         this.viewPath = viewPath;
     }

     public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
         // forward = 서버의 다른 리소스(서블릿, JSP 파일 또는 HTML 파일)로 요청을 전달합니다.
        dispatcher.forward(request, response);
     }
}
