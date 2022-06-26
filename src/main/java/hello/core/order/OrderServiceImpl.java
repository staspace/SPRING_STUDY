package hello.core.order;

import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{



    //스프링 2가지 Life Cycle
    //빈 생성
    //의존관계 주입

    //필드주입(권장하지 않음)
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;


//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
    //setter 주입
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    //DIP 지키고 있음:인터페이스에만 의존함,구체적인 클래스에 대해선 전혀 모름
    //값이 꼭 있어야 된다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자주입(주로 사용됨)
    //불변
    //생성자가 지금처럼 딱 하나만 있을 때는 @Autowired 생략가능(자동으로 의존관계 주입이 일어난다.)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
