package WonjuDelivery.DeliveryWeb.domain;

import javax.persistence.Entity;

@Entity
public class Member {
    private Long id;
    public String name;

    public Address address;
}
