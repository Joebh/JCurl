package jcurl.main;

import java.util.Map.Entry;
import java.util.logging.Logger;

import jcurl.main.session.CurlResponse;
import jcurl.main.session.JCurlSession;
import jcurl.main.session.JCurlSession.KeyValuePair;

public class JCurl {

	private static final Logger log = Logger.getLogger(JCurl.class.getName());

	public static void main(String[] args) {
		JCurlSession session = createSession();

		session.callCurl("curl 'http://dox.redplum.com/FoodLion/Account/LogOn' " +
				"-H 'Origin: http://dox.redplum.com' " +
				"-H 'Accept-Encoding: gzip,deflate,sdch' " +
				"-H 'Host: dox.redplum.com' " +
				"-H 'Accept-Language: en-US,en;q=0.8' " +
				"-H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36' " +
				"-H 'Content-Type: application/x-www-form-urlencoded' " +
				"-H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8' " +
				"-H 'Cache-Control: max-age=0' " +
				"-H 'Referer: http://dox.redplum.com/FoodLion/Account/LogOn' " +
				"-H 'Connection: keep-alive' " +
				"--data 'UserName=${username}&Password=${password}&RememberMe=false&KeepMeLoggedIn=false&ReturnUrl=%2FFoodLion' " +
				"--compressed", 
				new KeyValuePair("username", "JoebhJoebh@gmail.com"),
				new KeyValuePair("password", "zxcvasdf12")
				);

		log.info(session.toString());

	}

	/**
	 * Create a new JCurlSession for managing cookies of subsequent curl calls
	 * 
	 * @return JCurlSession
	 */
	public static JCurlSession createSession() {
		return new JCurlSession();
	}

}
