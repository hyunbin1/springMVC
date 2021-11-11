package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveController implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age")); // getParameter은 항상 string만 입력받기 때문에 숫자로 변환시켜주어야한다.

        // 멤버 생성자에 이름, 나이를 입력한 후
        Member member = new Member(username, age);
        // 생성된 멤버를 레퍼지토리에 저장한다.
        memberRepository.save(member);

        //Model에 데이터 보관한다.
        request.setAttribute("member", member); // http request 내부에 있는 저장소를 사용한다.

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
