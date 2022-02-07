package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class shop {
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
}
