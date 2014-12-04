/**
 * 
 */
package com.octodecillion.svn;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author j.betancourt
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Path {
	@XmlValue
	String value;
	@XmlAttribute(name = "text-mods")
	String textmods;
	@XmlAttribute(name="kind")
	String kind;
	
	/*
	 Actions are one of:
		A 	The item was added.
		D 	The item was deleted.
		M 	Properties or textual contents on the item were changed.
		R 	The item was replaced by a different one at the same location. 
			Item has been replaced in your working copy. This means the file 
			was scheduled for deletion, and then a new file with the same 
			name was scheduled for addition	in its place.
	 */	
	@XmlAttribute(name="action")
	String action;
	@XmlAttribute(name="prop-mods")
	String propmods;

	/**
	 * @return the textmods
	 */
	public String getTextmods() {
		return this.textmods;
	}

	/**
	 * @param textmods the textmods to set
	 */
	public void setTextmods(String textmods) {
		this.textmods = textmods;
	}

	/**
	 * @return the kind
	 */
	public String getKind() {
		return this.kind;
	}

	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the propmods
	 */
	public String getPropmods() {
		return this.propmods;
	}

	/**
	 * @param propmods the propmods to set
	 */
	public void setPropmods(String propmods) {
		this.propmods = propmods;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("{value:%s,kind:%s,action:%s,textmods:%s,propmods:%s}", value.replaceAll("\\n+",""),kind,action,textmods,propmods);
	}
	
}
