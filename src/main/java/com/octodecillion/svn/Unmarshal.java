package com.octodecillion.svn;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

import com.google.common.base.Preconditions;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;

/**
 * @author jbetancourt
 */
public class Unmarshal {
	/** */
	public Log path(String path) throws JAXBException, SAXException,
			IOException {
		Preconditions.checkNotNull(path, "'path' param is null");
		return url(new File(path).toURI().toURL());
	}

	/**  */
	public Log url(String url) throws JAXBException, SAXException, IOException {
		Preconditions.checkNotNull(url, "'url' param is null");
		CharSource charSrc = Resources.asCharSource(new URL(url),
				Charset.defaultCharset());
		return unmarshall(charSrc);
	}

	/**  */
	public Log url(URL url) throws JAXBException, SAXException, IOException {
		Preconditions.checkNotNull(url, "'url' param is null");
		CharSource charSrc = Resources.asCharSource(url,
				Charset.defaultCharset());
		return unmarshall(charSrc);
	}

	/** */
	public Log string(String xml) throws JAXBException, SAXException,
			IOException {
		Preconditions.checkNotNull(xml, "'xml' param is null");
		return unmarshall(CharSource.wrap(xml));
	}

	/** */
	public Log unmarshall(CharSource in) throws JAXBException, SAXException,
			IOException {
		Preconditions.checkNotNull(in, "'in' param is null");
		JAXBContext jaxbContext = JAXBContext.newInstance(Log.class);
		Log theLog = null;
		
		try(Reader reader = in.openStream()){
			StreamSource source = new StreamSource(reader);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<Log> jxbElement = unmarshaller.unmarshal(source, Log.class);
			theLog = jxbElement.getValue();
		}
		
		return theLog;
	}

}
