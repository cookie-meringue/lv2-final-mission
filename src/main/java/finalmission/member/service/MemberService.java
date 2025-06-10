package finalmission.member.service;

import finalmission.member.domain.Member;
import finalmission.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findByEmailAndPassword(
            final String email,
            final String password
    ) {
        return memberRepository.findByEmailAndPassword(email, password);
    }

    public void createWithRandomName(String email, String password) {
        // 추후 랜덤 이름 생성 기능 구현 후 변경 예정
        String name = "test";
        Member member = new Member(name, email, password);
        memberRepository.save(member);
    }
}
