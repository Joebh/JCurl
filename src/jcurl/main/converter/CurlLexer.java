package jcurl.main.converter;

import java.util.logging.Logger;

import jcurl.main.converter.tokens.*;

public class CurlLexer {

	private char[] curlString;
	private int index;
	private static final States fsm[][];
	private Logger log = Logger.getLogger(CurlLexer.class.getName());

	/**
	 * Build the fsm used in lexing
	 */
	static {
		fsm = new States[States.values().length][Transitions.values().length];
		for (int i = 0; i < fsm.length; i++) {
			for (int j = 0; j < fsm[i].length; j++) {
				fsm[i][j] = States.ERROR;
			}
		}

		fsm[States.START.i][Transitions.C.i] = States.AFTER_C;
		fsm[States.START.i][Transitions.DASH.i] = States.AFTER_DASH;
		fsm[States.START.i][Transitions.QUOTE.i] = States.AFTER_QUOTE;
		fsm[States.START.i][Transitions.SPACE.i] = States.START;

		fsm[States.AFTER_C.i][Transitions.U.i] = States.AFTER_CU;

		fsm[States.AFTER_CU.i][Transitions.R.i] = States.AFTER_CUR;

		fsm[States.AFTER_CUR.i][Transitions.L.i] = States.AFTER_CURL;

		fsm[States.AFTER_CUR.i][Transitions.SPACE.i] = States.START;

		fsm[States.AFTER_DASH.i][Transitions.DASH.i] = States.AFTER_DASHDASH;
		fsm[States.AFTER_DASH.i][Transitions.ALPHA.i] = States.AFTER_DASHES;

		fsm[States.AFTER_DASHDASH.i][Transitions.ALPHA.i] = States.AFTER_DASHES;

		fsm[States.AFTER_DASHES.i][Transitions.ALPHA.i] = States.AFTER_DASHES;
		fsm[States.AFTER_DASHES.i][Transitions.SPACE.i] = States.START;

		fsm[States.AFTER_QUOTE.i][Transitions.NOTQUOTE.i] = States.IN_STRING;
		fsm[States.AFTER_QUOTE.i][Transitions.QUOTE.i] = States.DONE_STRING;
		
		fsm[States.IN_STRING.i][Transitions.NOTQUOTE.i] = States.IN_STRING;
		fsm[States.IN_STRING.i][Transitions.QUOTE.i] = States.DONE_STRING;

		fsm[States.DONE_STRING.i][Transitions.SPACE.i] = States.START;
	}

	CurlLexer(String curlString) {
		this.curlString = curlString.toCharArray();
		this.index = 0;
	}

	public static void main(String[] args) {
		CurlLexer lex = new CurlLexer(
				"curl");
		
		for (int i = 0; i < fsm.length; i++) {
			System.out.print(i + "\t");
			for (int j = 0; j < fsm[i].length; j++) {
				System.out.print(fsm[i][j] + " ");
			}
			System.out.print("\n");
		}
		

		Token token = lex.nextToken();
		while (token != null) {
			System.out.println(token);
			token = lex.nextToken();
		}
	}

	public Token nextToken() {
		States state = States.START;
		StringBuilder value = new StringBuilder();
		States previousState = States.START;
		do {
			// get next char
			if(index == curlString.length){
				break;
			}
			String nextChar = curlString[index++] + "";
			
			//append char to value
			value.append(nextChar);
			
			//save previous state
			previousState = state;
			
			//use regex in transition enum to see what state to goto next
			Transitions[] transitions = state.transitions;
			for(Transitions transition : transitions){
//				log.info("Trying to match transition " + transition);
				if(nextChar.matches(transition.regex)){
					log.info("Matched " + transition);
					state = fsm[state.i][transition.i];
				}
			}
			
			if(state == States.ERROR){
				return new ErrorToken();
			}
		} while (state != States.START && previousState != States.START);
		
		if(previousState == States.AFTER_CURL){
			return new CurlToken();
		}
		if(previousState == States.DONE_STRING){
			return new StringToken(value.toString());
		}
		if(previousState == States.AFTER_DASHES){
			return new FlagToken(value.toString());
		}

		return new ErrorToken();
	}
}
