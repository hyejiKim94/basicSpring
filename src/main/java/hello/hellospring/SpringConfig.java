package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/*
Service, Repository class 위에 각각  @Service @Repository 어노테이션을 붙여주는 방법도 있지만
이렇게 Java code 로 빈을 설정해주는 방법도 있다.
물론 어노테이션이 편하지만 각각의 장점이 있다
예를 들어 현재 상황이 어떤 DB를 연결할 지 정해져있지 않은 상황에서 Repository 를 만들어서
메모리 DB를 사용해서 구현하고 있는데 이것이 나중에 DB가 정해져서 사용할 Repository 를 바꿔야 할 경우
코드를 바꿀 필요 없이 그냥 밑에 MemoryMemberRepository 를 DB를 연결해서 구현한 그 클래스를
가져다가 bean 설정을 해주면 되므로 어노테이션의 삭제, 혹은 구현한 클래스 안에 코드를
일일히 바꿀 필요가 없는 장점이 있다.
 */
@Configuration
public class SpringConfig {

//  private EntityManager em;

//  @Autowired
//  public SpringConfig(EntityManager em) {
//    this.em = em;
//  }
  //  private DataSource dataSource;
//
//  @Autowired
//  public SpringConfig(DataSource dataSource) {
//    this.dataSource = dataSource;
//  }

  // spring data jpa setting
  private final MemberRepository memberRepository;

  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository);
  }

  // 이러한 기능을 지금 쓰고있구나 라는 것을 볼 수 있으므로 Bean 으로 등록해 주는 것이 좋다.
  // 하지만 TimeTraceAop class 위에 @Component 어노테이션을 추가해 주는 방법으로도 가능하다
  @Bean
  public TimeTraceAop timeTraceAop() {
    return new TimeTraceAop();
  }

//  @Bean
//  public MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//    return new JdbcMemberRepository(dataSource);
//    return new JdbcTemplateMemberRepository(dataSource);
//    return new JpaMemberRepository(em);
//  }
}
