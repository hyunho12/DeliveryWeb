package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    public String name;
    public String price;
    public String detail;
    public int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    public Shop shop;
}
