package hello.servlet.web.frontcontroller.v5;


import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import org.springframework.web.servlet.HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 모든 종류의 컨트롤러가 들어가야되기 때문에 특정 컨트롤러가 아닌 Object를 Map 파라미터에 넣어준다.
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    // 어댑터(컨트롤러)가 여러개이기 때문에 만들어진 컨트롤러들의 리스트를 만들어 주어야한다.
    private final List<MyHandlerAdapter> handlerAdaptersList = new ArrayList<>();

    // 2. 컨트롤러 주소값(URI) 설정
    public FrontControllerServletV5(){
        initHandlerMappingMap();
        // 어댑터(컨트롤러)에 해당하는 URIfmf 리스트에 추가해준다.
        initHandlerAdapters();
    }
    private void initHandlerAdapters(){
        handlerAdaptersList.add(new ControllerV3HandlerAdapter());
        handlerAdaptersList.add(new ControllerV4HandlerAdapter());
    }
    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //V4 컨트롤러 추가
       handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 3. 핸들러 찾아와
        //    요청 정보를 통해 핸들러를 찾아온다.
        Object handler = getHandler(request); // 반환: MemberFormControllerV3
        // 오류처리 - 존재하지 않는 컨트롤러를 호출할 시
        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // page 404 출력
            return;
        }

        // 4. 어댑터 찾아와
        //    위 코드에서 만들어둔 핸들러 어댑터 목록에서 해당 어댑터를 찾아와야한다.
        //    MyHandlerAdapter에 매핑 정보를 추가해주었다. 그렇기 때문에 우리는 a에 찾으려는 어댑터를 넣으면 어댑터가 작동하게 된다.

        MyHandlerAdapter adapter = getHandlerAdapter(handler);  // handler = ControlV3HandlerAdapter

        // 5. 요청받은 모든 파라미터들 -> Map(paramMap) -> mv
        ModelView mv = adapter.handle(request, response, handler);

        // 6. 내부 파일 논리 이름 + 물리적 이름 결합 하기 - 내부 파일 경로 편하게 쓰기 위해서
        String viewName = mv.getViewName(); // 논리 이름: Ex. new-form
        // 실제 물리적 이름 완성하기
        MyView view = viewResolver(viewName);
        // View단에 model에 저장되어 있는 내용들 보내주기
        view.render(mv.getModel(), request, response);
    }

    // 3. 핸들러 찾아와 메서드
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI(); // 클라이언트가 입력한 url
        return handlerMappingMap.get(requestURI); // 입력된 url에 해당하는 컨트롤러 가져오기
    }

    // 4. 어댑터 찾아와 메서드
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        // Object handler = MemberFormControlV3
        // handlerAdaptersList = new ControllerV3HandlerAdapter() - initHandlerAdapters
        for (MyHandlerAdapter adapter : handlerAdaptersList) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        // 해당 어댑터가 없을 시 오류 출력하기
        throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다. handler=" + handler);
    }

    // 뷰 리졸버 메서드 - 파일 이름 생략 가능하도록 만들어줌
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }


}
