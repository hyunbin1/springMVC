package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/* [목표 기능]
* 1. 파라미터 전송 기능
* http://localhost:8080/request-param?username=hello&age=20&username=hello22
*
*
* */



@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. request(요청) 파라미터 모두 조회하는 방법
        System.out.println("[전체 파라미터 조회] -  start");
        // paramName = 파라미터 이름 꺼내기 Ex. age, name
        // request.getParameter == 파라미터 이름에 해당하는 내용을 꺼내기 Ex. 김현빈, 23 등
        // getParamter은 하나의 파라미터 이름에 대해서 첫 번째 단 하나의 값만 출력해준다.
        // 따라서 중복일 경우에는 request.getParameterValues()를 사용해주어야한다.
        // 하지만 주로 단일 파라미터를 사용한다.


        request.getParameterNames().asIterator().forEachRemaining(
                (paramName -> System.out.println(paramName+"="+request.getParameter(paramName))));
        System.out.println("[전체 파라미터 조회] -  end");
        System.out.println();

        // 2. 단일 요청 파라미터 조회하는 방법
        System.out.println("[단일 파라미터 조회] -  start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = "+username + "age = "+ age);
        System.out.println("[단일 파라미터 조회] -  end");
        System.out.println();

        // 3. 같은 파라미터 이름에 인풋 값이 복수인 경우 조회하기
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for (String name:usernames){
            System.out.println("username = " + name);
        }

        response.getWriter().write("ok");


    }
}
