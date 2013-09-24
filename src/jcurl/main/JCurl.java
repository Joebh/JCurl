package jcurl.main;


import jcurl.main.session.JCurlSession;

public class JCurl {

	public static void main(String[] args){
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
