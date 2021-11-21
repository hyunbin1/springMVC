package hello.servlet.basicTheory;
/*
* json 형식의 파일은 그대로 사용하지 않고 객체를 하나 생성한 후 사용한다(파싱).
* 따라서 이 파일은 json의 객체로 바꾸는 파일이다.
*/

/*
* 목표 Json 형식:
* 1. POST http://localhost:8080/request-body-json
* 2. content-type: application/jsion
* 3. message body: {"username : "hello", "age" : 20 }
* 4. 결과: messageBody = {"username : "hello", "age" : 20 }
* */

import lombok.Getter;
import lombok.Setter;

@Getter @Setter // lombok의 자동 생성 코드
public class HelloData {

    private String username;
    private int age;

    }
