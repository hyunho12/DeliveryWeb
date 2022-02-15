package WonjuDelivery.DeliveryWeb.service;

import WonjuDelivery.DeliveryWeb.domain.*;
import WonjuDelivery.DeliveryWeb.exception.NotEnoughStockException;
import WonjuDelivery.DeliveryWeb.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();
        Item item = createItem();

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER,getOrder.getOrderStatus());
        Assert.assertEquals("주문한 상품 종류 수가 정확해야한다",1,getOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격",10000*2,getOrder.getTotalPrice());
        Assert.assertEquals(8,item.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        Member member = createMember();
        Item item = createItem();
        int orderCount = 11;

        orderService.order(member.getId(), item.getId(), orderCount);
        fail("재고수량부족");
        //Assert.assertEquals(orderCount,item.getStockQuantity());
    }

    @Test
    public void 주문취소() throws Exception{
        Member member = createMember();
        Item item = createItem();

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("주문취소시 상태는 cancel",OrderStatus.CANCEL,getOrder.getOrderStatus());
        Assert.assertEquals("주문이 취소된 상품은 재고가 다시 증가",10,item.getStockQuantity());
    }

    private Member createMember(){
        Member member = new Member();
        member.setName("pooky");
        member.setAddress(new Address("서울","강가","123-435"));
        em.persist(member);
        return member;
    }

    private Item createItem(){
        Item item = new Item();
        item.setName("chicken");
        item.setPrice(10000);
        item.setStockQuantity(10);
        em.persist(item);
        return item;
    }

}