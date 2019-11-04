package com.rezk.orderit.mc.resources.utils;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static List<Integer> decodeIntList(String encoded) {
		return Arrays.asList(encoded.split(",")).stream().map(id -> Integer.parseInt(id)).collect(Collectors.toList());
	}
	
	public static String decodeParam(String param) {
		try{
			return URLDecoder.decode(param, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

}
