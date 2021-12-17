package hello.itemService.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    // 멀티 쓰레드 환경에서는 동시에 스토어에 접근하게 되면 싱글톤으로 생성되어야 하기 때문에 HashMap으로 사용하면 안된다.
    // 대신에 ConcurrentHashMap 사용해야된다.
    private static final Map<Long, Item> store = new HashMap<>(); // static 주의 - 실제로는 map사용 금지

    // 이것도 동시에 접근하면 automicLong 등을 사용해야된다.
    private static long sequence = 0L; //static 주의


    // ### 메서드 목록 ###
    // 아이템 저장
    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // 아이템 찾기 메서드
    public Item findById(Long id) {
        return store.get(id);
    }

    // 아이템 목록 보기 메서드
        //arrayLIst에 감싸서 반환하면 arraylist 에 값을 추가로 넣어도 더이상 실제 스토어에는 변함이 없기 때문에 안정성을 보장한다.
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    // 아이템 업데이트
    public void update(Long itemId, Item updateParam){ // 아이템 업데이트 파람을 넣으면 업데이트 되도록하기
        // 아이템을 찾기
        Item findItem = findById(itemId);
        // 아이템 파라미터로 이름 정보 수정하기
        findItem.setItemName(updateParam.getItemName());
        // 아이템 파라미터로 가격 정보 수정하기
        findItem.setPrice(updateParam.getPrice());
        // 아이템 파라미터로 수량 정보 수정하기
        findItem.setQuantity(updateParam.getQuantity());
    }

    // 상품들 모두 없애기
    public void clearStore(){
        store.clear();
    }


}
