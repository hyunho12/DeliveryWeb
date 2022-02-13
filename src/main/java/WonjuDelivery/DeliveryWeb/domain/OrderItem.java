package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private int count;
    private int orderprice;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static OrderItem createOrderItem(Item item, int orderprice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderprice(orderprice);
        orderItem.setCount(count);

        item.removeStock(count);

        return orderItem;
    }

    public void cancel(){
        getItem().addStock(count);
    }

    public int getTotalPrice(){
        return orderprice * count;
    }
}
