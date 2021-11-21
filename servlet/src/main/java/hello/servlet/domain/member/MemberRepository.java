package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    /*
    * 동시성 문제가 고려되어 있지 않다. 실무에서는 ConcurrentHashMap, AtomicLong을 사용해야 한다.
    * */

    // key는 아이디, 값은 멤버
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // id 순서 - 0부터 시작

    private static final MemberRepository instance = new MemberRepository();

    // 외부에서 이 생성자 조회시 사용할 메서드
    public static MemberRepository getInstance(){
        return instance;
    }

    // 싱글톤 생성시 private로 외부 막기
    public MemberRepository(){
    }

    // ## 메서드 시작 ##

    // 1. 저장 메서드
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }


    // 2. 멤버 찾기
    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        // store의 값들을 모두 새로운 arraylist에 담아준다. - store 데이터 보존
        // 이는 외부에서 arraylist를 건들여도 store에 있는 values 리스트는 유지/보존 하고 싶기 때문이다.
        return new ArrayList<>(store.values());
    }

    // 3. 데이터 지우기 - 주로 테스트에서 사용
    public void clearStore(){
        store.clear();
    }

}
