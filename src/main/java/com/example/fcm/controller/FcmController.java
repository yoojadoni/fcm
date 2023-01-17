package com.example.fcm.controller;

import com.example.fcm.domain.Member;
import com.example.fcm.dto.NotificationDTO;
import com.example.fcm.service.fcm.FCMService;
import com.example.fcm.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("fcm")
public class FcmController {

    @Autowired
    private FCMService fcmService;

    @Autowired
    private MemberService memberService;

    /**
     * fcm token 정보 조회 후 발송요청처리
     * @param email
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/{email}")
    public ResponseEntity sendFcmMessage(@PathVariable(value = "email") String email) throws Exception
    {

        Member member = memberService.findByEmail(email).get();
        String fcmId = member.getFcm();
        String title = "메시지가 도착했습니다.";
        String message = member.getName() + "님 내용 확인 바랍니다.";
        NotificationDTO notificationDTO = new NotificationDTO(title, message, fcmId);

        // 비동기방식
        fcmService.sendAsync(notificationDTO);

        return ResponseEntity.ok().build();
    }

    /**
     * fcm token 정보 업데이트처리.
     * @param email
     * @param fcm
     * @return
     * @throws Exception
     */
    @PutMapping("/{email}/{fcm}")
    public ResponseEntity updateFcmToken(@PathVariable(value="email") String email, @PathVariable(value = "fcm")  String fcm) throws Exception
    {
        Member member = Member.builder()
                .email(email)
                .fcm(fcm)
                .build();

        memberService.updateMemberById(member);

        return ResponseEntity.ok().build();
    }
}
