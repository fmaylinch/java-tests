package com.plazi.javatests.intro;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class StringUtilRepeatShould {

    // TODO: what should happen when text is null?

    @Test
    public void return_text_repeated_several_times() {
        assertThat(StringUtil.repeat(3, "hey"), is("heyheyhey"));
    }

    @Test
    public void return_same_text_when_repeating_once() {
        assertThat(StringUtil.repeat(1, "hello"), is("hello"));
    }

    @Test
    public void return_empty_string_when_repeating_zero_times() {
        assertThat(StringUtil.repeat(0, "bye"), is(""));
    }

    @Test
    public void throw_exception_when_repeating_negative_times() {
        try {
            StringUtil.repeat(-1, "oops");
            fail();
        } catch (Exception expected) { }
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_repeating_negative_times_2() {
        StringUtil.repeat(-1, "oops");
    }
}