package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;
import lombok.Setter;

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
    @OneToOne(fetch = FetchType.LAZY)
    public Delivery delivery;

    @OneToMany(mappedBy = "order")
    public List<OrderItem> orderItems = new ArrayList<>();

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

        return order;
    }

}
