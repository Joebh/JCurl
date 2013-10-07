package jcurl.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
			session.callCurl(new File("resources/login"), new KeyValuePair(
					"username", "JoebhJoebh@gmail.com"), new KeyValuePair(
					"password", "zxcvasdf12"));
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

	/**
	 * Create a new session with a different starting timeout
	 * 
	 * @param timeout
	 * @return
	 */
	public static JCurlSession createSession(int timeout) {
		JCurlSession session = new JCurlSession();
		session.setTimeout(timeout);
		return session;
	}

	/**
	 * Create a new session with a timeout and a front regex
	 * 
	 * @param timeout
	 *            0 for negative, in milliseconds
	 * @param frontRegex
	 *            the front of templating regex, encapsulates variable name
	 * @param backRegex
	 *            the back of templating regex, encapsulates variable name
	 * @return
	 */
	public static JCurlSession createSession(int timeout, String frontRegex,
			String backRegex) {
		JCurlSession session = new JCurlSession();
		session.setTimeout(timeout);

		session.setFrontParamDetect(frontRegex);
		session.setBackParamDetect(backRegex);
		return session;
	}

	/**
	 * Create a new session with a timeout and a front regex
	 * 
	 * @param frontRegex
	 *            the front of templating regex, encapsulates variable name
	 * @param backRegex
	 *            the back of templating regex, encapsulates variable name
	 * @return
	 */
	public static JCurlSession createSession(String frontRegex, String backRegex) {
		JCurlSession session = new JCurlSession();
		session.setFrontParamDetect(frontRegex);
		session.setBackParamDetect(backRegex);
		return session;
	}

}
