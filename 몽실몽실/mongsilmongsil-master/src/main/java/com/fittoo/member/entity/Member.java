package com.fittoo.member.entity;

import com.fittoo.common.entity.UserBaseEntity;
import com.fittoo.member.model.MemberInput;
import com.fittoo.member.model.LoginType;
import com.fittoo.reservation.Reservation;
import com.fittoo.review.entity.Review;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Member extends UserBaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String regPurpose;

    private long point;


    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        this.reservationList.add(reservation);
        reservation.setMember(this);
    }

    public void addReview(Review review) {
        this.reviewList.add(review);
        review.setMember(this);
    }

    public static String setRegPurpose(List<String> regPurposeList) {

        StringBuilder purposeList = new StringBuilder();
        int length = regPurposeList.size();
        for (String regPurpose : regPurposeList) {
            if (--length == 0) {
                purposeList.append(regPurpose);
            } else {
                purposeList.append(regPurpose).append(",");
            }
        }
        return purposeList.toString();
    }

    public static Member of(MemberInput memberInput, String encPassword) {
        return Member.builder()
            .userId(memberInput.getUserId())
            .password(encPassword)
            .gender(setGender(memberInput.getGender()))
            .loginType(LoginType.NORMAL)
            .phoneNumber(memberInput.getPhoneNumber())
            .exercisePeriod(memberInput.getExercisePeriod())
            .regPurpose(setRegPurpose(memberInput.getRegPurposeList()))
            .userName(memberInput.getUserName())
            .address(memberInput.getAddress())
            .regDt(LocalDateTime.now())
            .build();
    }

    public static String setGender(int num) {
        switch (num) {
            case 1:
                return "??????";
            case 2:
                return "??????";
        }
        return null;
    }


}
