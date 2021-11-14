package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // frontController에서 미리 처리한 요청 파라미터 정보들을 Map에 다 넣어서 넘겨줄것이다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 비지니스 로직 실행
        Member member = new Member(username, age);
        memberRepository.save(member);

        //모델 파라미터에 값 넣어주기 - 기존에 모델을 직접 다루었다면 이제는 프론트 컨트롤러가 대신 다룰 것이다.
        model.put("member", member);

        // 논리 주소만 리턴해주면 된다.
        return "save-result";
    }
}
