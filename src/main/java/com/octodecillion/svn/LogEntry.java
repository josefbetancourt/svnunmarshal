package com.octodecillion.svn;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Joiner;

/**
 * @author j.betancourt
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class LogEntry {
	@XmlAttribute
	String revision;
	String author;
	String date;
	@XmlElementWrapper(name="paths")
	@XmlElement(name="path")
	List<Path>paths;
	String msg;

	/**
	 * @return the revision
	 */
	public String getRevision() {
		return this.revision;
	}

	/**
	 * @param revision the revision to set
	 */
	public void setRevision(String revision) {
		this.revision = revision;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return this.date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the paths
	 */
	public List<Path> getPaths() {
		return this.paths;
	}

	/**
	 * @param paths the paths to set
	 */
	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return this.msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder("[");		
		bld.append(Joiner.on(",").join(this.paths)).append("]");		
		return String.format("{revision:%s,author:%s,date:%s,paths:%s,msg:%s}", this.revision,this.author,this.date,bld.toString(),this.msg);
	}
}