package hello.springmvc.basicTheory.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j // log 쉽게 전송
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    // 위 코드를 더 쉽게 사용하는 방법
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    // 위 코드를 더 쉽게 사용하는 방법
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 위 코드를 더 쉽게 사용하는 방법 - 하지만 팀 단위에서 애노테이션을 잘쓰지 못하는 팀이라면 넣는 것이 좋다.
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //필수 파라미터 요청
    @ResponseBody
    @RequestMapping("/request-param-requierd")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // required = true면 꼭 들어와야한다.
            // int는 null값이 들어올 수 없지만 Integer은 객체형이라 null이 가능하여 이렇게 사용해야한다.
            @RequestParam(required = true) Integer age // required = false면 꼭 들어오지 않아도 된다.
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //필수 파라미터 요청 - default 값 추가 - defaultValue는 빈문자의 경우에도 guest로 처리해준다.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 요청파람을 Map으로 조회 가능하다.
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap( @RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

}
