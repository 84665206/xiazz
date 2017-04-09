package com.eshop.webapp.m.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class XStreamWrapper {

	private static final XStreamWrapper xStreamWrapper = new XStreamWrapper();

	private static XStream xStream = new XStream();

	private XStreamWrapper() {

	}

	public static XStreamWrapper getInstance() {
		return xStreamWrapper;
	}

	public static XStream getXStream() {
		return XStreamWrapper.xStream;
	}

	public static String toXML(Object obj) {
		return XStreamWrapper.xStream.toXML(obj);
	}

	public static String toJsonByJsonHierarchicalStreamDriver(Object obj){
		XStream xStream = new  XStream(new JsonHierarchicalStreamDriver());
		return xStream.toXML(obj);
	}

	public static String toJsonByJettisonMappedXmlDriver(Object obj){
		XStream xStream = new  XStream(new JettisonMappedXmlDriver());
		xStream.setMode(XStream.NO_REFERENCES);
		return xStream.toXML(obj);
	}

}
