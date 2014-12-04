/**
 * 
 */
package com.octodecillion.svn;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author j.betancourt
 */
@XmlRootElement
public class Log {
	@XmlElement(name = "logentry")
	List<LogEntry> entries;
	
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		for(LogEntry entry : entries){
			bld.append(entry.toString());
		}
		
		return bld.toString();
	}
}
