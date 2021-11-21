package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberFormControllerV1{

    // @RequestMapping: URI 매핑
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        // jsp 파일 불러오기
        return new ModelAndView("new-form");
    }
}
