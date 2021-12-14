package hello.springmvc.basicTheory.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory .getLogger(getClass());


//  1. URL 요청 매핑 - 여러개 한번에 가능
//  @RequestMapping({"/hello-basic", "/hello-go"})
    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    // 위 코드를 애노테이션 방식을 사용하여 작성하기
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mapping-get-v2");
        return "ok";
    }
    //2. PathVariable - 경로 변수
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId){
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId){
        log.info("mappingPath userId={}, orderId={}", userId, orderId); // mappingPath userId=userA, orderId=100
        return "ok";
    }

    //3. 파라미터 - 잘 사용하지는 않음 - 특정 모드 파라미터가 있으면 요청이 되지만 없으면 오류를 띄운다.
    @GetMapping(value="/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    //4. 특정 헤더 조건 매핑 - 헤더 값이 없으면 404에러/ 있으면 리턴
    @GetMapping(value="/mapping-header", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    //5. 미디어 타입 조건 매핑 - HTTP 요청 ContentType -> consume을 사용하자.
        // 헤더에 컨텐트 타입이 application/json이여야지 호출한다.
    /*
    Content-Type 헤더 기반 추가 매핑 Media Type
    consumes="application/json"
    consumes="!application/json"
    consumes="*\/*"
    MediaType.APPLICATION_JSON_VALUE
     */

//    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }

    //미디어 타입 조건 매핑 - HTTP 요청 Accept 헤더 필요함, accept 헤더에 produces의 파일형식과 동일해야한다.
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }

}
