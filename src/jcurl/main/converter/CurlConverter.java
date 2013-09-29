package jcurl.main.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jcurl.main.converter.syntaxtree.Curl;
import jcurl.main.converter.tokens.EOFToken;
import jcurl.main.converter.tokens.Token;
import jcurl.main.converter.visitors.BasicVisitor;

/**
 * 
 * @author joebh
 *
 */
public class CurlConverter {

	private static final Logger log = Logger.getLogger(CurlConverter.class.getName());

	public static CurlObject convertCurl(String curlString) {
		CurlObject curlObject = new CurlObject();
		CurlLexer lex = new CurlLexer(curlString);
		CurlParser parser = new CurlParser(lex);
		
		//generate root of syntax tree
		Curl curl = parser.parse();
		
		log.info(curl.toString());
		
		BasicVisitor visitor = new BasicVisitor();
		curl.accept(visitor);
		

		return curlObject;
	}

}
