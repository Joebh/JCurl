package jcurl.main;

import java.util.logging.Logger;

import jcurl.main.session.CurlResponse;
import jcurl.main.session.JCurlSession;

public class JCurl {
	
	private static final Logger log = Logger.getLogger(JCurl.class.getName());

	public static void main(String[] args){
		JCurlSession session = createSession();
		
		session.callCurl(
				"curl 'https://www.foodlion.com/Account/Logon' -H 'Cookie: FoodLionAuthP2=90B71DCAECA24E461AE1958B50E3BB90FD8174AB10D10CDAA04091E2C24C8C9982FB04A48FA7D60B8C5A5DD2AD33DBBD3E8E413BAE669B81AE20A4EFE59C6342115FF3B2D2F11DCE124A5F18BE3C31C16621584EEEE048B46A00BA0E591D0CFCD576150E8B5EE874E7C0A39C31A9FC51470F2B69A132ADCE143E2B892B98A92A0DAF7055FB37A0140E89A9F2C70EF8BF05B65DE9538A31B22369EEFA93C5D6560704880A4A5BA5631F6274F4141C22589521C6B1; ASP.NET_SessionId=j4wnxvt3vo342voj3ly54zxs; __utma=111076882.1954587904.1381168178.1381168178.1381171674.2; __utmb=111076882.3.10.1381171674; __utmc=111076882; __utmz=111076882.1381168178.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=1.503346723.1381168178.1381168178.1381171674.2; __utmb=1.3.10.1381171674; __utmc=1; __utmz=1.1381168178.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)' -H 'Origin: https://www.foodlion.com' -H 'Accept-Encoding: gzip,deflate,sdch' -H 'Host: www.foodlion.com' -H 'Accept-Language: en-US,en;q=0.8' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36' -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8' -H 'Accept: */*' -H 'Referer: https://www.foodlion.com/?popup=Login' -H 'X-Requested-With: XMLHttpRequest, XMLHttpRequest' -H 'Connection: keep-alive' --data 'ReturnUrl=%2F&Email=joebhjoebh%40gmail.com&Password=zxcvasdf12&Persistent=false&Logon=LOG%20IN&X-Requested-With=XMLHttpRequest' --compressed"
				);
		
		log.info(session.toString());
		
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
