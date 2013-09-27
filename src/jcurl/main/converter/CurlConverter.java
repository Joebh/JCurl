package jcurl.main.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jcurl.main.converter.tokens.Token;

public class CurlConverter {


	public static CurlObject convertCurl(String curlString) {
		CurlObject curlObject = new CurlObject();
		CurlLexer lex = new CurlLexer(curlString);
		
		Token token = lex.nextToken();
		while (token != null) {
			System.out.println(token);
			token = lex.nextToken();
		}

		return curlObject;
	}

}
