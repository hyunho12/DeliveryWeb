package WonjuDelivery.DeliveryWeb.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    public String name;

    @Embedded
    public Address address;
}
