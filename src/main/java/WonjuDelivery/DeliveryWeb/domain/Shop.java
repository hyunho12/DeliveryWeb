package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Shop {
    @Id @GeneratedValue
    @Column(name = "shop_id")
    private Long id;

    @Column(name = "shop_name")
    public String shopName;

    @Column(name = "shop_info")
    public String shopInfo;

    public String shopTel;
    public String ceo;
    public String shopNo;

    @OneToOne(fetch = FetchType.LAZY)
    public Category category;
}
