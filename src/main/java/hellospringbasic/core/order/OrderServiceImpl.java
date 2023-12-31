package hellospringbasic.core.order;

import hellospringbasic.core.annotation.MainDiscountPolicy;
import hellospringbasic.core.discount.DiscountPolicy;
import hellospringbasic.core.member.Member;
import hellospringbasic.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memId, String itemName, int itemPrice) {
        Member mem = memberRepository.findById(memId);
        int discountPrice = discountPolicy.discount(mem, itemPrice);

        return new Order(memId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
