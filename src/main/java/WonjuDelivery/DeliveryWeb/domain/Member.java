package WonjuDelivery.DeliveryWeb.domain;

import javax.persistence.Entity;

@Entity
public class Member {
    public Long id;
    public String name;

    public Address address;
}
