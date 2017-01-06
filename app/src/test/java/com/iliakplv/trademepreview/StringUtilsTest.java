package com.iliakplv.trademepreview;

import org.junit.Test;

import static com.iliakplv.trademepreview.common.StringUtils.getLastDigitGroup;
import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void test_getLastDigitGroup() throws Exception {

        assertEquals("", getLastDigitGroup(null, null));
        assertEquals("", getLastDigitGroup("", null));
        assertEquals("", getLastDigitGroup(null, ""));
        assertEquals("", getLastDigitGroup("", ""));

        assertEquals("", getLastDigitGroup("", "-"));
        assertEquals("", getLastDigitGroup(null, "-"));

        assertEquals("", getLastDigitGroup("-", "-"));
        assertEquals("", getLastDigitGroup("--", "-"));
        assertEquals("", getLastDigitGroup("---", "-"));

        assertEquals("1234", getLastDigitGroup("1234", null));
        assertEquals("1234", getLastDigitGroup("1234", ""));

        assertEquals("1234", getLastDigitGroup("1234", "-"));
        assertEquals("1234", getLastDigitGroup("1234-", "-"));
        assertEquals("1234", getLastDigitGroup("0001-1234", "-"));
        assertEquals("1234", getLastDigitGroup("0001-1234-", "-"));
        assertEquals("1234", getLastDigitGroup("0001-0002-1234", "-"));
        assertEquals("1234", getLastDigitGroup("0001-0002-1234-", "-"));

        assertEquals("1234", getLastDigitGroup("1234--", "-"));
        assertEquals("1234", getLastDigitGroup("1234---", "-"));
        assertEquals("1234", getLastDigitGroup("-1234-", "-"));
        assertEquals("1234", getLastDigitGroup("-1234--", "-"));
        assertEquals("1234", getLastDigitGroup("--1234--", "-"));
        assertEquals("1234", getLastDigitGroup("0000-1234--", "-"));
        assertEquals("1234", getLastDigitGroup("-0000-1234--", "-"));
        assertEquals("1234", getLastDigitGroup("---0000---1234---", "-"));

    }

}