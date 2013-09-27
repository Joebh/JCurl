package jcurl.main.converter.tokens;

public class FlagToken extends Token{

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public FlagToken(String type) {
		super();
		this.type = type;
	}
	
}
