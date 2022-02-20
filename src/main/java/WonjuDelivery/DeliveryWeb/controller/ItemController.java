package WonjuDelivery.DeliveryWeb.controller;

import WonjuDelivery.DeliveryWeb.domain.Item;
import WonjuDelivery.DeliveryWeb.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new ItemForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(ItemForm itemForm){
        Item item = new Item();
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        item.setStockQuantity(itemForm.getStockQuantity());
        item.setDetail(itemForm.getDetail());

        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Item item = itemService.findOne(itemId);

        ItemForm itemForm = new ItemForm();
        itemForm.setId(item.getId());
        itemForm.setName(item.getName());
        itemForm.setPrice(item.getPrice());
        itemForm.setStockQuantity(item.getStockQuantity());

        model.addAttribute("form",itemForm);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(ItemForm Form){
        itemService.updateItem(Form.getId(),Form.getName(), Form.getPrice(), Form.getStockQuantity(), Form.getDetail());

        /*
        Item item = new Item();
        item.setId(itemForm.getId());
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        item.setStockQuantity(itemForm.getStockQuantity());
        item.setDetail(itemForm.getDetail());

        itemService.saveItem(item); */
        return "redirect:/items";
    }
}
