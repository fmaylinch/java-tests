package com.plazi.javatests.tdd;

import org.junit.Test;

import static com.plazi.javatests.tdd.DateUtil.isLeapYear;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class DateUtilLeapYearShould {

    /*
      All years divisible by 400 ARE leap years (1600, 2000, 2400),
      All years divisible by 100 but not by 400 are NOT leap years (1700, 1800, 1900),
      All years divisible by 4 but not by 100 ARE leap years (1996, 2004, 2008),
      All years not divisible by 4 are NOT leap years (2017, 2018, 2019).
     */

    @Test
    public void be_true_when_year_is_divisible_by_400() {

        assertThat( isLeapYear(1600), is(true) );
        assertThat( isLeapYear(2000), is(true) );
        assertThat( isLeapYear(2400), is(true) );
    }

    @Test
    public void be_false_when_year_is_divisible_by_100_and_not_by_400() {

        assertThat( isLeapYear(1700), is(false) );
        assertThat( isLeapYear(1800), is(false) );
        assertThat( isLeapYear(1900), is(false) );
    }

    @Test
    public void be_true_when_year_is_divisible_by_4_and_not_by_100() {

        assertThat( isLeapYear(1996), is(true) );
        assertThat( isLeapYear(2004), is(true) );
        assertThat( isLeapYear(2008), is(true) );
    }

    @Test
    public void be_false_when_year_is_not_divisible_by_4() {

        assertThat( isLeapYear(2017), is(false) );
        assertThat( isLeapYear(2018), is(false) );
        assertThat( isLeapYear(2019), is(false) );
    }

}