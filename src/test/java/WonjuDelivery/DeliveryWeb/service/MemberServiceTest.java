package WonjuDelivery.DeliveryWeb.service;

import WonjuDelivery.DeliveryWeb.domain.Member;
import WonjuDelivery.DeliveryWeb.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("pooky");

        Long saveId = memberService.join(member);

        Assert.assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test
    public void 중복회원예외(){
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        try{
            memberService.join(member2);
        }catch (IllegalStateException e){
            return;
        }

        fail("예외가 발생해야한다");
    }
}