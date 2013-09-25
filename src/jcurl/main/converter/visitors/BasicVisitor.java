package jcurl.main.converter.visitors;

import jcurl.main.converter.syntaxtree.Curl;
import jcurl.main.converter.syntaxtree.Flag;
import jcurl.main.converter.syntaxtree.Method;
import jcurl.main.converter.syntaxtree.URL;
import jcurl.main.converter.syntaxtree.flags.Compressed;
import jcurl.main.converter.syntaxtree.flags.H;

public class BasicVisitor implements Visitor{

	@Override
	public void accept(Curl curl) {
		System.out.println("curl");
		for(Flag flag : curl.getFlags()){
			flag.accept(this);
		}
		curl.getMethod().accept(this);
		curl.getUrl().accept(this);
	}

	@Override
	public void accept(URL url) {
		// TODO Auto-generated method stub
		System.out.println("url");
	}

	@Override
	public void accept(Method method) {
		// TODO Auto-generated method stub
		System.out.println("method");
	}

	@Override
	public void accept(Flag flag) {
		// TODO Auto-generated method stub
		System.out.println("flag");
	}

	@Override
	public void accept(H h) {
		// TODO Auto-generated method stub
		System.out.println("h");
	}

	@Override
	public void accept(Compressed compressed) {
		// TODO Auto-generated method stub
		System.out.println("compressed");
	}

}
