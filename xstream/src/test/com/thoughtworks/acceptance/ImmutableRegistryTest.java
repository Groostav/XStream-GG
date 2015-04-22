package com.thoughtworks.acceptance;

import com.thoughtworks.acceptance.objects.StandardObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Geoff on 2015-04-22.
 */
public class ImmutableRegistryTest extends AbstractAcceptanceTest{

    /**
     * see {@link com.thoughtworks.xstream.XStream#setupImmutableTypes()} for the list
     * of built-in immutables, note that URL is one of them.
     */
    public static class URLPair extends StandardObject{
        URL source;
        URL destination;

        public URLPair(URL source, URL destination){
            this.source = source;
            this.destination = destination;
        }

        public URLPair(URL both){
            this.source = both;
            this.destination = both;
        }
    }

    public void testDocumentWithImmutableMembersDontUseXPathByDefault() throws MalformedURLException{
        xstream.alias("URLPair", URLPair.class);

        URL empower = new URL("http://www.empoweroperations.com");
        URLPair pair = new URLPair(empower);

        String expectedXml = "" //
                + "<URLPair>\n" //
                + "  <source>http://www.empoweroperations.com</source>\n" //
                + "  <destination>http://www.empoweroperations.com</destination>\n" //
                + "</URLPair>";

        assertBothWays(pair, expectedXml);
    }

}
