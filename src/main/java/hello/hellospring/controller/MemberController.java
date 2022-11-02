package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {


  // field 주입 - 이거는 권장되지 않는 사용 방법
  // @Autowired private MemberService memberService;
  private final MemberService memberService;

  // Setter 주입 - 얘도 권장되지 않음 Service 는 한번 설정하고 나서는 건드릴 일이 없는데
  // Service setter 자체가 public 되어버리므로
//  @Autowired
//  public void setMemberService(MemberService memberService) {
//    this.memberService = memberService
//  }

  // 생성자 주입 - 가장 권장되고 많이 쓰이는 사용 방법
  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/members/new")
  public String createForm() {
    return "members/createMemberForm";
  }

  @PostMapping("/members/new")
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    memberService.join(member);

    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
  }
}
