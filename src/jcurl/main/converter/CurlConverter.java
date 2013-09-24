package jcurl.main.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurlConverter {

	public static CurlObject convertCurl(String curlString) {
		CurlObject curlObject = new CurlObject();

		// parse out all headers
		curlObject.setHeaders(parseHeaders(curlString));

		return curlObject;
	}

	private static Map<String, String> parseHeaders(String curlString) {
		Map<String, String> headers = new HashMap<String, String>();
		Pattern pattern = Pattern.compile("-H +(['\"])");

		Matcher matcher = pattern.matcher(curlString);
		while (matcher.find()) {
			System.out.println(matcher.group(1));
		}

		System.out.println(matcher.replaceAll(""));
		System.exit(1);
		return null;
	}

}
