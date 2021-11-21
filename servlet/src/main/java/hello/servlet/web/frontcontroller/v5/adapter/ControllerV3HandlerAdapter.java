package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// V3 컨트롤러를 지원하는 어댑터
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        // handler = MemberFormControllerV3 - 부모가 ControllerV3이다.
        // 받은 객체가 v3 컨트롤러인지 여부 확인 - 동일하다면 handle 메서드로 이동, 아니라면 다시 어댑터로 돌려보냄
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // 객체가 최상위 부모인 Object이기 때문에 할 수 있는 것이 별로 없다. 따라서 ControllerV3로 다운 캐스팅을 해야된다.
        ControllerV3 controller = (ControllerV3) handler;

        // v3에서는 원래 컨트롤러에서 modelview를 반환해주었기 때문에 여기에서도 modelView로 반환해준다.
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        return mv;
    }


    // 요청 받은 파라미터 리스트화 시키기
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}