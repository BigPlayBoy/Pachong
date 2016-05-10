package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Scanner;

import sina.bean.info.LoginInfo;
import sina.httpclient.LoginSina;
import sina.httpclient.SpiderSina;
import sina.utils.EncodeUtils;
import sina.utils.Utils;

/**
 * @author zc0604
 * 
 */
public class SpiderTest {
	private static LoginSina lg;
	private static SpiderSina spider;
	static{
		lg=new LoginSina(LoginInfo.username, LoginInfo.password);
		lg.dologinSina();
		lg.redirect();
		spider=new SpiderSina(lg);
	}
	
	public static void main(String[] args) {
//		getGroupTopicMessage();
		LoginSina ls=new LoginSina(LoginInfo.username, LoginInfo.password);
		ls.dologinSina();
		ls.redirect();
		
		SpiderSina ss=new SpiderSina(ls);
//		String rsp=ss.addFollowing("1791346245");
//		System.out.println(rsp);
//		Scanner s=new Scanner(System.in);
//		s.next();
//		rsp=ss.cancelFollowing("1791346245");
//		System.out.println(rsp);
		//ss.releaseTopic("今天是阴天啊，呵呵啊和");		
		
	}
	
	public static void getGroupTopicMessage(){
		String groupID="271226";
		Integer count=50;
		String htmlcode=spider.getGroupTopic(1, count, groupID);
		EncodeUtils.unicodeTogb2312(htmlcode);
		Utils.writeFileFromString("a.txt", htmlcode);
		htmlcode=spider.getGroupTopic(2, count, groupID);
		EncodeUtils.unicodeTogb2312(htmlcode);
		Utils.writeFileFromString("b.txt", htmlcode);
	}
}
