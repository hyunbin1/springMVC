package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         // 모델에 있는 데이터를 forEach로 다 꺼내기 -> 요청 파라미터의 값을 모두 담아논다.
        modelToRequestAttribute(model, request); // 모델 내용을 jsp에 맞는 형식으로 변환해주기
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
