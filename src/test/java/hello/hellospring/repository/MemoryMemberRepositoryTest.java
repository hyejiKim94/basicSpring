package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// publicは削除、このテストクラスをどこかで使う訳ではないので
class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  // 테스트가 끝날때 마다 실행해 줄 메소드
  // 클리어 해주지 않은 상태에서 테스트를 이어가게되면 객체를 만드는 쪽에 있어서
  // 무언가를 달리 해주지 않으면, 겹쳐버리거나, 예상대로 테스트가 흘러가지 않는 경우가 발생
  @AfterEach
  public void afterEach() {
    repository.clearStore();
  }


  @Test
  public void save() {
    Member member = new Member();
    member.setName("hi");

    repository.save(member);
    // 実装側ではすぐget()を使うのはよくない。これはテストだからOK
    Member result = repository.findById(member.getId()).get();
//    Assertions.assertEquals(result, member);
    assertThat(member).isEqualTo(result);
  }

  @Test
  public void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member result =  repository.findByName("spring1").get();

    assertThat(result).isEqualTo(member1);
  }

  @Test
  public void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    List<Member> result = repository.findAll();

    assertThat(result.size()).isEqualTo(2);
  }
}
