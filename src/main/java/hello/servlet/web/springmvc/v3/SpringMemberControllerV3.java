package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping(value = "new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    // ModelAndView를 string으로 대신 받아도 된다.
    public String newForm() {
        return "new-form";
    }

//    @RequestMapping(value ="save", method = RequestMethod.POST)
    @PostMapping("/save")
    // 요청 파라미터 내용을 servlet 없이 직접 받을 수 있다.
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }


}


