package hello.springmvc.basicTheory.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {
    @GetMapping
    public String user(){ // user 목록 조회
        return "get users";
    }
    @PostMapping
    public String addUser(){ // user 추가
        return "post users";
    }
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){ // 특정 회원 조회
        return "get userId= " + userId;
    }
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){ // 특정 회원 조회
        return "update userId= " + userId;
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){ // 특정 회원 조회
        return "delete userId= " + userId;
    }
}
