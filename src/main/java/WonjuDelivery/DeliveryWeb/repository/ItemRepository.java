package WonjuDelivery.DeliveryWeb.repository;

import WonjuDelivery.DeliveryWeb.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class ItemRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Item item){
        em.persist(item);
        return item.getId();
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
}
