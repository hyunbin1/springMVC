package hello.itemService.web;


import hello.itemService.domain.item.Item;
import hello.itemService.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.ExitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    //@RequiredArgsConstructor은 final이 붙은 것에 아래와 같은 코드를 저절로 붙여준다.
//    @Autowired
//    public BasicItemController(ItemRepository itemRepository){
//        this.itemRepository = itemRepository;

    // 아이템 목록 조회
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    //상품 상세 whghl
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";

    }

    // 테스트 용 초기 데이터 추가
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("TestA", 10000, 10));
        itemRepository.save(new Item("TestB", 20000, 20));
    }
}
