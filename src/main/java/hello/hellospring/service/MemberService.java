package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 메소드들의 이름을 보면 알겠지만 Service class는 좀 더 비지니에 가까운 네이밍이 필요.
// Repository쪽은 findAll, save 이런식이라면 Service는 join, findMembers 이런식.
public class MemberService {

  private final MemberRepository memberRepository;
  // memberRepository 를 service class 안에서 생성하는 것이 아닌, 외부에서 받아서 설정하는 걸로 처리
  // 그래야 테스트를 할 때도 또 다른 repository 의 객체를 생성해서 정합성의 문제가 생기지 않는다. 다른쪽에서도 그렇고
  // 이런식으로 외부에서 넣어주는 것을 DI(Dependency Injection: 의존성주입) 라고 한다.
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * 회원가입
   * @return member.id
   */
  public Long join(Member member) {
    // No name duplication (just for prac)
//    Optional<Member> result = memberRepository.findByName(member.getName());
//    result.ifPresent(m -> {
//      throw new IllegalStateException("이미 존재하는 값입니다");
//    });

    validateDuplicateMember(member);  // 중복 회원 검증
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
            .ifPresent(m -> {
              throw new IllegalStateException("이미 존재하는 값입니다");
            });
  }

  /**
   * 전체 회원 조회
   * @return List<Member>
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
