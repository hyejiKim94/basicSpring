package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void join() {
    // Test Process 테스트가 길어질 때에는 이렇게 given, when, then 으로 나누어 주는 것도 좋다
    // given
    Member member = new Member();
    member.setName("spring");

    // when
    Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  // Test 는 사실 그 목적의 동작이 잘 처리되는 것도 중요하지만 예외처리가 잘 작동하는지의 체크도 중요!
  @Test
  public void 중복_회원_예외() {
    // given
    Member member1 = new Member();
    member1.setName("spring");
    Member member2 = new Member();
    member2.setName("spring");

    // when
    memberService.join(member1);
    // assertThrows([얘가 나와야해], [() -> {} 이 처리를 실행했을때]) 라고 해석하면 된
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    assertThat(e.getMessage()).isEqualTo("이미 존재하는 값입니다");



    // 하나의 방법 하지만 try catch 를 써야하므로 추천하지는 않는 방법
//    try {
//      memberService.join(member2);
//      fail();
//    } catch (IllegalStateException e) {
//      assertThat(e.getMessage()).isEqualTo("이미 존재하는 값입니다");
//    }

  }

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}