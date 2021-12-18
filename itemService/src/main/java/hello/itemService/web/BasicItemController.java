package hello.itemService.web;

import hello.itemService.domain.item.Item;
import hello.itemService.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.ExitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.cglib.reflect.MethodDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //상품 상세 조회
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // 상품 등록 페이지 조회
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //상품 등록
//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model)
    {
        Item item =new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);


        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item){

        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동 추가되기 때문에 생략 가능
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model){
        itemRepository.save(item);

        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item){
        itemRepository.save(item);

        return "basic/item";
    }

    //상품 수정 페이지 조회
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    // 상품 수정 저장
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    // 상품 수정 취소 기능은 추가하지 않아도 된다. - 처리되는 데이터가 없기 때문


    
    
//    @PostMapping("/{itemId}/edit")
//    public String edit(Item item){
//
//    }



    // 테스트 용 초기 데이터 추가
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("TestA", 10000, 10));
        itemRepository.save(new Item("TestB", 20000, 20));
    }
}
