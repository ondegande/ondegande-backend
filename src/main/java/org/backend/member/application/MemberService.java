package org.backend.member.application;

import org.backend.member.domain.Member;
import org.backend.member.domain.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));
    }

    public Member findByRefreshToken(String refreshToken) {
        return memberRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new IllegalArgumentException("회원 정보가 존재하지 않습니다."));
    }

    public Member register(Member member) {
        Member regitserMember = memberRepository.findByEmail(member.getEmail()).orElse(null);
        if(regitserMember == null) {
            return memberRepository.save(member);
        }
        return regitserMember;
    }
}
