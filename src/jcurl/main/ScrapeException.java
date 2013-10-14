package jcurl.main;

import java.io.IOException;

public class ScrapeException extends Exception{

	public ScrapeException(String message){
		super(message);
	}

	public ScrapeException(IOException e) {
		super(e);
	}

}
