package hello.servlet.web.frontcontroller.v1;
/*
* 서블릿과 비슷한 모양의 컨트롤러 v1을 인터페이스로 만드는 이유:
* 1. 이 프론트 컨트롤러를 통해 여러 개의 구현을 하기 위해서이다. == 다형성을 활용하는 것이다.
* 2. 프론트 컨트롤러는 인터페이스를 호출해서 구현과 관계 없이 로직의 일관성을 가져간다.
* 3. 이 인터페이스를 구현한 컨트롤러를 만들어보자.
*
*
* */


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {
    // servlet이랑 같은 모양의 인터페이스 생성
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
