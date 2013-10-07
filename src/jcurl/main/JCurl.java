package jcurl.main;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.logging.Logger;

import jcurl.main.session.CurlResponse;
import jcurl.main.session.JCurlSession;
import jcurl.main.session.JCurlSession.KeyValuePair;

public class JCurl {

	private static final Logger log = Logger.getLogger(JCurl.class.getName());

	public static void main(String[] args) {
		JCurlSession session = createSession();

		try {
			session.callCurl(new File("resources/login"), 
					new KeyValuePair("username", "JoebhJoebh@gmail.com"),
					new KeyValuePair("password", "zxcvasdf12")
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
