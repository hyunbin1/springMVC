# springMVC1

### 이 레파지토리는 HTTP를 요청, 응답하는 방법부터 MVC 패턴을 도입하기까지 일련의 발전 과정을 서술하고 있습니다. 만일 코드를 보고 싶다면 src-main을 보시면 됩니다.

- basicTheory: 초기의 기초 개념과 코드를 작성해 놓은 파일
- servletMVC: MVC 공부를 시작하기 전에, MVC가 생기기 이전까지 우리가 얼마나 WEB을 불편하게 만들었는지 직접 격어보는 servlet을 만들어 보는 파일입니다.
-

## MVC V5(버전 5)

### ADD - 어댑터 패턴

![image-20211117100522500](C:\Users\kimgu\AppData\Roaming\Typora\typora-user-images\image-20211117100522500.png)

지금까지 우리는 v1~v4까지 여러 개의 컨트롤러 인터페이스를 작성했다. 그리고 우리가 개발한 **프론트 컨트롤러는 단 한가지 방식의 컨트롤러 인터페이스만을 사용할 수 있다.** 만약, 여러 명과 협업을 하고 있을 때, 각자의 취향 존중을 위해 여러 개의 컨트롤러 인터페이스를 사용할 수 있도록 만들면 얼마나 좋을까?

**핸들러 어댑터**: 이는 프로그램이 돌아가는 프로세스 중간에 어댑터 역할을 하는 어댑터이다. 이 핸들러 어댑터 덕분에 우리는 다양한 종류의 컨트롤러를 호출 할 수 있다.

**핸들러**: 컨트롤러의 이름을 더 넓은 범위인 "핸들러"로 변경하여 표현하기로 하자. 이전과 달리 이제는 어댑터가 존재하기 때문에 컨트롤러의 개념뿐만 아니라 어떠한 것이든 해당하는 종류의 어댑터만 있으면 다 처리할 수 있기 때문이다.

## SpringMVC1

![image-20211117100605775](C:\Users\kimgu\AppData\Roaming\Typora\typora-user-images\image-20211117100605775.png)

@Controller:

- 스프링이 자동으로 스프링 빈으로 등록한다. (내부에 `@Component` 애노테이션이 있어서 컨포넌트 스캔의 대상이 됨)

- 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다.

@RequestMapping:

- 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다. 애노테이션을 기반으로 동작하기 때문에, 메서드의 이름은 임의로 지으면 된다.

@ModelAndView:

- 모델과 뷰 정보를 담아서 반환하면 된다.

'RequestMappingHandlerMapping' 은 스프링 빈 중에서 '@RequestMapping' 또는 '@Controller'가 클래스 레벨에 붙어있는 경우 매핑 정보로 인식한다.

---

## 로깅 알아보기

지금까지 우리는 코드에 System.out.println()을 사용해서 콘솔에 필요한 정보를 출력하였다. 하지만 실무에서는 이 방법을 사용하지 않는다. 그렇다면 어떻게 실무에서는 로깅 처리를 할까?

실무에 투입된 개발자들은 주로 **별도의 로깅 라이브러리를 사용해서 로그를 출력한다.** 지금 서술하는 것은 최소한의 사용 방법으로, 이를 통해 기반 지식을 얻었다면 좀 더 깊은 내용의 학습을 추가적으로 해야한다.

1. **로깅 라이브러리**: 스프링 부트 라이브러리를 사용하면 스프링 부트 로깅 라이브러리('spring-boot-starter-loggin')가 함께 포함된다. 스프링 부트의 로깅 라이브러리는 SLF4J, LogBack을 주로 사용한다.

   로그 라이브러리는 LogBack, Log4J, Log4J2 등 수많은 라이브러리가 있는데, SLF4J는 이들을 모두 통합하여 이를 인터페이스로 제공한다.

   즉, **<u>인터페이스로는 SLF4J를</u>**, 이를 구현하는 **<u>구현체는 LogBack</u>** 같은 로그 라이브러리를 선택하면 된다. 주로 실무에서는 스프링 부트가 기본으로 제공하는 LogBack을 대부분 사용한다.

   cf)

   - SLF4J = http://www.slf4j.org
   - Logback = http://logback.qos.ch

2. **로그 선언**:

   ```java
   private Logge log = LoggerFactory.getLogger(getClass();
   private static final Logger log = LoggerFactory.getLogger(Xxx.class)
   @Slf4j // 롬복 사용 가능
   ```

3. **로그 호출:**

   ```
   log.info("hello");
   System.out.println("Hello");
   ```
