package finalmission.member.service;

import finalmission.auth.infrastructure.methodargument.MemberPrincipal;
import finalmission.member.domain.Member;
import finalmission.member.infrastructure.namegenerator.NameGenerator;
import finalmission.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final NameGenerator nameGenerator;

    public Optional<Member> findByPrincipal(final MemberPrincipal principal) {
        return memberRepository.findByEmail(principal.email());
    }

    public Optional<Member> findByEmailAndPassword(
            final String email,
            final String password
    ) {
        return memberRepository.findByEmailAndPassword(email, password);
    }

    public void createWithRandomName(String email, String password) {
        String name = nameGenerator.generate();
        Member member = new Member(name, email, password);
        memberRepository.save(member);
    }
}
