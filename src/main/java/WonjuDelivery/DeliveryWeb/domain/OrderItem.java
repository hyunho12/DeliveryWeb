package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Getter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    public int count;
    public int price;

    @ManyToOne(fetch= FetchType.LAZY)
    public Order orderItem;
}
