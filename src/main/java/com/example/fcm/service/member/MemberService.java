package com.example.fcm.service.member;

import com.example.fcm.domain.Member;
import com.example.fcm.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public Optional<Member> findByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    public Member saveMember(Member member){
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateMemberById(Member member) throws Exception{
        try{
            System.out.println("--------------");
            Member findMemeber = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new Exception("NOT FOUND"));
            findMemeber.changeFcm(member.getFcm());
        }catch (Exception e){
            throw new Exception();
        }
        return member;
    }

}
