package br.com.cursoudemy.sbmongodb.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Date convertDate(String date, Date defaultDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(date);
		}
		catch(ParseException e) {
			return defaultDate;
		}
	}
}
