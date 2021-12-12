package hello.springmvc.basicTheory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


 /*
 * @RestController: 원래 사용하던 @Controller에서 문자를 리턴하면 viewName이 반환된다.
 * 하지만 @RestController을 사용하면 문자가 그대로 반환된다. - viewName을 건들지 않는다.
 */
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());// Logger - org.slf4j꺼 사용하기

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "String";

        //로그 출력
        System.out.println("name = "+ name + " === System.out.println으로 출력한 것"); // 이전까지 사용한 로글 출력
        log.info(" info log={} == info.log로 출력한 것", name); // 실무에 적합한 로그 출력

        // 로그의 장점: 로그를 찍을 때,로그 레벨에 따라 출력할 수 있다.
         log.trace("trace log={}", name); //
         log.debug("debug log={}", name);
         log.info("info log={}", name); // 중요한 비지니스 등에서 사용되는 정보야
         log.warn("warn log={}", name); // 위험한 경보
         log.error("error log={}", name); //

        return "ok";
    }
}
