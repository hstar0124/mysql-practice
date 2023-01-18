package com.example.mysqlpractice.domain.member;


import com.example.mysqlpractice.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.LongStream;


public class MemberTest {

    @DisplayName("회원 닉네임을 변경할 수 있다")
    @Test
    public void testChangeName() {
        var member = MemberFixtureFactory.create();
        var expected = "hstar";

        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }

    @DisplayName("회원 닉네임은 10자를 초과할 수 없다")
    @Test
    public void testNicknameMaxLength() {
        var member = MemberFixtureFactory.create();
        var overMaxLengthName = "hstaraksdjfasdfsadf";

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> member.changeNickname(overMaxLengthName)
        );


    }
}
