package WonjuDelivery.DeliveryWeb.domain;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Order {
    private Long id;
    public LocalDateTime orderDate;
}
