/**
 * 
 */
package com.octodecillion.svn;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * @author j.betancourt
 */
public class UnMarshallTest {

	/**
	 * 
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public final void test() throws Exception {
		Log theLog = new Unmarshal().path("src/test/resources/log.xml");
		String actual = toSingleLine(theLog.toString());
		String expected1 = "{revision:20950,author:jbetancourt,date:2014-11-10T20:12:11.910891Z,paths:[{value:/2014/Acme/branches/rabbit-trap/www/images/beep.png,kind:file,action:D,textmods:false,propmods:false}],msg:initialcommit}{revision:20948,author:jbetancourt,date:2014-11-10T19:55:58.629641Z,paths:[{value:/2014/Acme/branches/rabbit-trap/www/images/desert.png,kind:file,action:D,textmods:false,propmods:false}],msg:changedicontint}{revision:20942,author:jbetancourt,date:2014-11-10T15:30:08.770266Z,paths:[{value:/2014/Acme/branches/rabbit-trap/www/scripts/acme/traps/rocket.js,kind:file,action:M,textmods:true,propmods:false},{value:/2014/Acme/branches/rabbit-trap/www/scripts/acme/traps/sled.js,kind:file,action:M,textmods:true,propmods:false}],msg:Added'usestrict'.}{revision:20941,author:rsmith,date:2014-11-10T15:20:41.707766Z,paths:[{value:/2014/Acme/branches/rabbit-trap/www/ads/umbrella/promo.html,kind:file,action:M,textmods:true,propmods:false},{value:/2014/Acme/branches/rabbit-trap/www/ads/images/umbrella.jpg,kind:file,action:A,textmods:true,propmods:true}],msg:promotionMerge}";
		String expected = toSingleLine(expected1);
		Assert.assertEquals("Created wrong object structure", expected, actual);

		System.out.println("passed test");
	}

	/**  */
	String toSingleLine(String s) {
		String s1 = s.replaceAll("\\n+", "");
		return s1.replaceAll("\\s+", "");
	}

}
