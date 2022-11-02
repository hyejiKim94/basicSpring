package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
public class TimeTraceAop {

  //                   package명.클래스명.파라미터 타입 등등해서 설정가능
  // 밑에는 hello.hellospring 하위애들은 전부 대상이 되지만 SpringConfig 파일만 제외한다는 뜻
  @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    System.out.println("START: " + joinPoint.toString());

    try {
      return joinPoint.proceed();
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
    }
  }
}
