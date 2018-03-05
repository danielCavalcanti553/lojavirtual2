package com.tcc.lojavirtual.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UtilURL {
	
	public static List<Integer> intToList(String s){
		return Arrays.asList(s.split(",")).stream().map(x->Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	public static String decodeUrlParam(String p) {
		try {
			return URLDecoder.decode(p,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		
	}
	
}
