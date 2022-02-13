package WonjuDelivery.DeliveryWeb.service;

import WonjuDelivery.DeliveryWeb.domain.Item;
import WonjuDelivery.DeliveryWeb.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired ItemRepository itemRepository;
    @Autowired ItemService itemService;

    @Test
    @Rollback(false)
    public void 상품등록(){
        Item item = new Item();
        item.setName("chicken");
        item.addStock(10);

        Long saveId = itemService.saveItem(item);

        Assert.assertEquals(item, itemRepository.findOne(saveId));
    }
}