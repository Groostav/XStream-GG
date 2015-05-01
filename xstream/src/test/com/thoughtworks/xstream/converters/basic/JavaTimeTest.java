package com.thoughtworks.xstream.converters.basic;

import junit.framework.TestCase;

import java.time.Instant;

/**
 * Created by Geoff on 2015-04-29.
 */
public class JavaTimeTest extends TestCase {

    public void testConvertingInstantsDoesSoAccordingToISO8601(){
        Instant instant = Instant.EPOCH;

        String expected = "1970-01-01T00:00:00Z";
        String actual = JavaTime.InstantConverter.toString(instant);

        assertEquals(expected, actual);
    }
}
