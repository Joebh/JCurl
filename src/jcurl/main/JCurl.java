package jcurl.main;

import java.util.logging.Logger;

import jcurl.main.session.CurlResponse;
import jcurl.main.session.JCurlSession;

public class JCurl {
	
	private static final Logger log = Logger.getLogger(JCurl.class.getName());

	public static void main(String[] args){
		JCurlSession session = createSession();
		
		session.callCurl(
				"curl 'http://www.foodlion.com/' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8' -H 'Connection: keep-alive' -H 'Accept-Encoding: gzip,deflate,sdch' -H 'Host: www.foodlion.com' -H 'Accept-Language: en-US,en;q=0.8' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36' --compressed"
				);
		
		log.info(session.toString());
		
		session.callCurl(
				"curl 'http://www.foodlion.com/' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8' -H 'Connection: keep-alive' -H 'Accept-Encoding: gzip,deflate,sdch' -H 'Host: www.foodlion.com' -H 'Accept-Language: en-US,en;q=0.8' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36' --compressed"
				);
		log.info(session.toString());
	}

	/**
	 * Create a new JCurlSession for managing cookies
	 * of subsequent curl calls
	 * 
	 * @return JCurlSession
	 */
	public static JCurlSession createSession(){
		return new JCurlSession();
	}
	
}
