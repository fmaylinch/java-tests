package com.plazi.javatests;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void repeatMultipleTimes() {
        assertThat(StringUtil.repeat(3, "hey"), is("heyheyhey"));
    }

    @Test
    public void repeatOnce() {
        assertThat(StringUtil.repeat(1, "hello"), is("hello"));
    }

    @Test
    public void repeatZeroTimes() {
        assertThat(StringUtil.repeat(0, "bye"), is(""));
    }
}