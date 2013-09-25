package jcurl.main.converter.syntaxtree;

import jcurl.main.converter.visitors.Visitor;

public abstract class Flag implements Acceptor{

	private String value;

	protected Flag() {

	}
	
	public abstract void accept(Visitor visitor);

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
