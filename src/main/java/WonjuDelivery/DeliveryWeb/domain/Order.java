package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    public LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    public Member member;

    @JoinColumn(name = "delivery_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }

    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems){ //정적 팩토리 메서드
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    public void cancel(){
        if(delivery.getDeliveryStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소 불가능합니다.");
        }

        this.setOrderStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return  totalPrice;
    }

}
