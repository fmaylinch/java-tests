package com.plazi.javatests.intro;

import org.junit.Test;

import static com.plazi.javatests.intro.PasswordUtil.SecurityLevel.*;
import static com.plazi.javatests.intro.PasswordUtil.assessSecurity;
import static org.assertj.core.api.Assertions.*;

public class PasswordUtilAssessSecurityTest {

    @Test
    public void weak_when_password_is_less_than_8_chars() {
        assertThat(assessSecurity("12345ab")).isEqualTo(WEAK);
    }

    @Test
    public void weak_when_password_only_has_letters() {
        assertThat(assessSecurity("abcdefghijk")).isEqualTo(WEAK);
    }

    @Test
    public void medium_when_password_has_letters_and_numbers() {
        assertThat(assessSecurity("1234abcd")).isEqualTo(MEDIUM);
    }

    @Test
    public void strong_when_password_has_letters_numbers_and_symbols() {
        assertThat(assessSecurity("12!4abcd")).isEqualTo(STRONG);
    }
}