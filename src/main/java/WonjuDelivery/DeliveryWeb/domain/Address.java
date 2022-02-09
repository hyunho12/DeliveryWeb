package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Getter
@Embeddable
public class Address {
    public String city;
    public String zipcode;
    public String street;


}
