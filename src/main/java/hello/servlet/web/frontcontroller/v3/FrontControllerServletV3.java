package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    // 1. 매핑정보 - url을 넣으면 컨트롤러를 꺼내서 호출이 된다.
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    // 2. 저장되어 있는 컨트롤러 주소값 설정
    public FrontControllerServletV3(){
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 3. 컨트롤러 실행 및 존재 유무 확인
        String requestURI = request.getRequestURI(); // 클라이언트가 입력한 url

        ControllerV3 controller = controllerMap.get(requestURI); // 입력된 url에 해당하는 컨트롤러 가져오기
        // 오류처리 - 존재하지 않는 컨트롤러를 호출할 시
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // page 404 출력
            return;
        }

        // 4. 요청받은 모든 파라미터들 -> Map(paramMap) -> mv
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        // 5. 내부 파일 논리 이름 + 물리적 이름 결합 하기 - 내부 파일 경로 편하게 쓰기 위해서 
        String viewName = mv.getViewName(); // 논리 이름: Ex. new-form
        // 실제 물리적 이름 완성하기
        MyView view = viewResolver(viewName);
        // View단에 model에 저장되어 있는 내용들 보내주기
        view.render(mv.getModel(), request, response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    // paramMap에 모든 요청 파라미터 정보들을 집어 넣어야한다 -> 이후 이것이 MemberControllerV3으로 이동하게 되는 것이다.
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
