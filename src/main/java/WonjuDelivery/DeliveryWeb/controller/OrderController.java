package WonjuDelivery.DeliveryWeb.controller;

import WonjuDelivery.DeliveryWeb.domain.Item;
import WonjuDelivery.DeliveryWeb.domain.Member;
import WonjuDelivery.DeliveryWeb.domain.Order;
import WonjuDelivery.DeliveryWeb.domain.OrderSearch;
import WonjuDelivery.DeliveryWeb.service.ItemService;
import WonjuDelivery.DeliveryWeb.service.MemberService;
import WonjuDelivery.DeliveryWeb.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderService orderService;

    @GetMapping("/order")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order( Long memberId,
                         Long itemId,
                         int count){
        orderService.order(memberId,itemId,count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }
}
