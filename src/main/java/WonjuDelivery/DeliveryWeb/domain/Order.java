package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    public LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    public Member member;

    @OneToOne(fetch = FetchType.LAZY)
    public Delivery delivery;
}
