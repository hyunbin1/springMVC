package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name="frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    // 1. 매핑정보 - url을 넣으면 컨트롤러를 꺼내서 호출이 된다.
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    // 2. 저장되어 있는 컨트롤러 주소값 설정
    public FrontControllerServletV4(){
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 4. 컨트롤러 실행 및 존재 유무 확인
        String requestURI = request.getRequestURI(); // 클라이언트가 입력한 url

        ControllerV4 controller = controllerMap.get(requestURI); // 입력된 url에 해당하는 컨트롤러 가져오기
        // 오류처리 - 존재하지 않는 컨트롤러를 호출할 시
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // page 404 출력
            return;
        }

        // 4. 이전 버전 v3: 요청받은 모든 파라미터들 -> Map(paramMap) -> mv
        // v4: 컨트롤러에서는 모두 Map으로 model 데이터를 처리하기 때문에, 컨트롤러에서 model을 쉽게 사용하고 쉽게 받기 위해서
        //      model을 HashMap으로 새롭게 생성해주어야한다.
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        // 5. 내부 파일 논리 이름 + 물리적 이름 결합 하기 - 내부 파일 경로 편하게 쓰기 위해서
        //    1) 컨트롤러에서는 여기 바로 위에서 보내준 파라미터 Map과 객체 Map을 필요에 따라 비지니스 로직을 수행한 후 다시 돌려주는 것이다.
        //    2) 리턴 값으로 주소값을 주었기 때문에 로직은 컨트롤러에서 실행 되고 여기에서는 주소 값만 받는 것이다. 여기서 컨트롤러 로직 수행 x
        //    3) 컨트롤러에서 수행된 model의 값은 바로 위에 작성된 model = new HashMap() 코드를 통해 담겼기 때문에 아래애서 그냥 model을
        //       사용하면 된다.
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);
        // 이전 버전 v3: View단에 model에 저장되어 있는 내용들 보내주기
        // v4: 프론트 컨트롤러에서 model을 제공하기 때문에 직접 위에 있는 model을 사용하면 된다.
        view.render(model, request, response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    // paramMap에 모든 요청 파라미터 정보들을 집어 넣어야한다 -> 이후 이것이 각각의 컨트롤러(MemberControllerV4)로 이동하게 되는 것이다.
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
