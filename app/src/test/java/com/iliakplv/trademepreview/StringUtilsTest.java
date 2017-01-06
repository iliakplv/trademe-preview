package com.iliakplv.trademepreview;

import org.junit.Test;

import static com.iliakplv.trademepreview.common.StringUtils.getLastDigitGroup;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StringUtilsTest {

    @Test
    public void test_getLastDigitGroup() throws Exception {

        assertNull(getLastDigitGroup(null, null));
        assertNull(getLastDigitGroup(null, ""));
        assertNull(getLastDigitGroup(null, "-"));

        assertEquals("", getLastDigitGroup("", null));
        assertEquals("", getLastDigitGroup("", ""));
        assertEquals("", getLastDigitGroup("", "-"));

        assertEquals("1234", getLastDigitGroup("1234", "-"));
        assertEquals("1234", getLastDigitGroup("1234-", "-"));
        assertEquals("1234", getLastDigitGroup("0001-1234", "-"));
        assertEquals("1234", getLastDigitGroup("0001-1234-", "-"));
        assertEquals("1234", getLastDigitGroup("0001-0002-1234", "-"));
        assertEquals("1234", getLastDigitGroup("0001-0002-1234-", "-"));

    }

}