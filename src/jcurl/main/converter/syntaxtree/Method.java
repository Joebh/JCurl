package jcurl.main.converter.syntaxtree;

import jcurl.main.converter.visitors.Visitor;

public class Method implements Acceptor{
	
	private String type;

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.accept(this);
	}

}
