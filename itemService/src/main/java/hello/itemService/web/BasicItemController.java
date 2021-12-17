package hello.itemService.web;


import hello.itemService.domain.item.Item;
import hello.itemService.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.ExitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("item", items);
        return "basic/items";
    }

    // 테스트 용 초기 데이터 추가
    @PostMapping
    public void init(){
        itemRepository.save(new Item("ItemA", 10000, 10));
        itemRepository.save(new Item("ItemB", 20000, 20));
    }
}
