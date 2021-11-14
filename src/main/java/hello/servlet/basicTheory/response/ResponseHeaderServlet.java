package hello.servlet.basicTheory.response;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // [status-line] - 응답코드에서 http 스팩에서 가장 첫 라인
        response.setStatus(HttpServletResponse.SC_OK); // 직접 200을 파라미터에 적어주는 것보다 이것이 더 좋은 방법이다.

//        //[response-headers]
//        response.setHeader("Content-Type", "test/plain;charset=utf-8");
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시 완전히 무효화하겠다.
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("my-header", "hello");

        //편의 메서드 실행
        content(response);
        cookie(response);
        redirect(response);

        //[message Body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }


    //편의 메서드들 작성
    private void content(HttpServletResponse response){
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    private void cookie(HttpServletResponse response){
        // Set-Cookie: myCookie=good, Max-Age=600
        // response.setHeader{"Set-Cookie", "good"};
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/Basic/hello-form.html");
    }
}
