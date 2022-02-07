package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    public Address address;

    @Enumerated(EnumType.STRING)
    public DeliveryStatus deliveryStatus;
}
