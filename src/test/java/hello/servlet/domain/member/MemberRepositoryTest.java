package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// junit 5부터는 public 필요 없다.
class MemberRepositoryTest {
    // 싱글톤이기 때문에 new 사용하면 안됨
    MemberRepository memberRepository = MemberRepository.getInstance();

    // 테스트 돌때마다 데이터 초기화
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    //1. 저장 테스트 작성
    @Test
    void save(){
//      given
        Member member = new Member("hello", 20);

//      when
        Member savedMember = memberRepository.save(member);

//      then
        Member findMember = memberRepository.findById(savedMember.getId());// 저장한 맴버 아이디 찾은 후에
        assertThat(findMember).isEqualTo(savedMember); // 현재 저장한 findMember과 실제 저장된 맴버와 내용이 같아야 한다.

    }


    // 2. 모든 조회 테스트 작성
    void findALl(){
//      given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 14);

        memberRepository.save(member1);
        memberRepository.save(member2);
//      when
        List<Member> result = memberRepository.findAll();

//      then
        assertThat(result.size()).isEqualTo(2); // 저장 개수 2개
        assertThat(result).contains(member1, member2);
    }



}
