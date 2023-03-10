package com.example.mysqlpractice.domain.member.service;

import com.example.mysqlpractice.domain.member.dto.RegisterMemberCommand;
import com.example.mysqlpractice.domain.member.entity.Member;
import com.example.mysqlpractice.domain.member.entity.MemberNicknameHistory;
import com.example.mysqlpractice.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.mysqlpractice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
@Service
public class MemberWriteService {

    final private MemberRepository memberRepository;

    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Transactional
    public Member create(RegisterMemberCommand command) {
        /*
         * 목표    - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
         *        - 닉네임은 10자를 넘길수 없다.
         * 파라미터 - memberRegisterCommand
         * val member = Member.of(memberRegisterCommand)
         * memberRepository.save(member)
         * */
        var member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();

        // TransactionTemplate 을 사용할 수도 있다.

        var savedMember = memberRepository.save(member);
        saveMemberNicknameHistory(savedMember);
        return savedMember;
    }

    public void changeNickname(Long memberId, String nickname) {

        var member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);

        memberRepository.save(member);

        saveMemberNicknameHistory(member);

    }

    private void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameHistoryRepository.save(history);
    }


}
