package com.example.fcm;

import com.example.fcm.domain.Member;
import com.example.fcm.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class FcmWebApplication {

	@Autowired
	MemberService memberService;
	public static void main(String[] args) {
		SpringApplication.run(FcmWebApplication.class, args);
	}

	@PostConstruct
	public void initCreateMember(){

		Member member = Member.builder()
				.email("test@test.com")
				.name("test유저")
				.fcm("eCJUebCTAr4:APA91bFVc3ilFst_BW4tf6rkJC3-4CHMHAETSD1VeWfxDzrO1mB7Z2foqzzrrI2T6zxAAN-JtI1g7Ef-evMbXasyIcY-DizBXQwzeFnZ6ocgOz9ogm9sATSNaUq-3WB3LVYNVMN0V8jW")
				.build();

		memberService.saveMember(member);

	}
}
