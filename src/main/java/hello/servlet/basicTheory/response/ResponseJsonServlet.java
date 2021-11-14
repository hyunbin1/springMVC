package hello.servlet.basicTheory.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basicTheory.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kimn");
        helloData.setAge(20);

        // 들어온 데이터를 json 형식:  {"username":"kim", "age":20} 으로 바꿀것이다.
        //이를 위해 우리는 이전에 사용한 object Mapper을 사용하여 객체 형태의 json 데이터를 읽어야한다.
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);

    }
}
