package jcurl.main;

import jcurl.main.session.CurlResponse;
import jcurl.main.session.JCurlSession;

public class JCurl {

	public static void main(String[] args){
		JCurlSession session = createSession();
		
		CurlResponse response = session.callCurl(
				"curl -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8' -H 'Connection: keep-alive' -H 'Accept-Encoding: gzip,deflate,sdch' 'http://www.lowesfoods.com/'  -H 'Cookie: CFID=bc799c5e-5177-4ca2-886b-08d09716c637; CFTOKEN=0; USERID=\"\"; USERHASH=\"\"; ORIGINALURLTOKEN=92AB687D-C0C7-4101-9D8F061193F1F8AE; __utma=257673494.766763064.1380051303.1380051303.1380051303.1; __utmb=257673494.2.10.1380051303; __utmc=257673494; __utmz=257673494.1380051303.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __unam=bedb7f4-1415177bea0-6638ab58-2' -H 'Host: www.lowesfoods.com' -H 'Accept-Language: en-US,en;q=0.8' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36' --compressed"
				);
		
		System.out.println(response);
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
