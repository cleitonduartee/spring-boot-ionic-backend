package com.cursomc.resource.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Integer> decodeIntList(String s){
		try {			
			return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());	
			
		} catch (NumberFormatException e) {
			 List<Integer>list = new ArrayList<>();
			 return list;		
		}		
	}
	public static String decodeParam(String nome)   {
		try {
			
			return URLDecoder.decode(nome, "UTF-8");			
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
