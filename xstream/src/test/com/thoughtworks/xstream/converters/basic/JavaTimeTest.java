package com.thoughtworks.xstream.converters.basic;

import com.thoughtworks.acceptance.AbstractAcceptanceTest;
import com.thoughtworks.xstream.XStream;

import java.net.URI;

public class JavaTimeTest extends AbstractAcceptanceTest {
    public JavaTimeTest() {
    }

    public void setUp() throws Exception{

        xstream = new XStream();
        xstream.registerConverter(JavaTime.InstantConverter);
    }

    /**
     * Test of canConvert method, of class URIConverter.
     */
    public void testCanConvert() {
        assertBothWays()
        final Class type = URI.class;
        final URIConverter instance = new URIConverter();
        final boolean expResult = true;
        final boolean result = instance.canConvert(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of fromString method, of class URIConverter.
     */
    public void testFromString() throws Exception{
        final URIConverter instance = new URIConverter();
        final Object expResult = new URI(TEST_URI_STRING);
        final Object result = instance.fromString(TEST_URI_STRING);
        assertEquals(expResult, result);
    }
}