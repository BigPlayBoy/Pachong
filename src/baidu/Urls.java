package baidu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
根据指定的规则，通过构造正则表达式获取网址
*/
public class Urls {
	private String startUrl; // 开始采集网址
	String urlContent;
	String ContentArea;
	private String strAreaBegin, strAreaEnd; // 采集区域开始采集字符串和结束采集字符串
	String strContent;// 获得的采集内容
	String[] allUrls; // 采集到的所有网址
	private String regex; // 采集规则
	UrlAndTitle urlAndTitle = new UrlAndTitle(); // 存储网址和标题

	 // static File fp=new File("a.txt");
	//static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("a.txt")));
	public static void main(String[] args) {
		Urls myurl = new Urls("<body", "/body>");
		myurl.getStartUrl("http://www.qdaily.com/");
		myurl.getUrlContent();
		myurl.getContentArea();
		myurl.getStartUrl("http://www.qdaily.com/");
		myurl.getStringNotInUrl("google");
		myurl.Urls();

		// System.out.println("startUrl:"+myurl.startUrl);
		// System.out.println("urlcontent:"+myurl.urlContent);
		// System.out.println("ContentArea:"+myurl.ContentArea);
	}

	// 初始化构造函数 strAreaBegin 和strAreaEnd
	public Urls(String strAreaBegin, String strAreaEnd) {
		this.strAreaBegin = strAreaBegin;
		this.strAreaEnd = strAreaEnd;
	}

	//
	public void Urls() {
		int i = 0;
		// String regex ="<a
		// href="?'?http://[a-zA-Z0-9]+/.[a-zA-Z0-9]+/.[a-zA-Z]+/?[/.?[/S|/s]]+[a>]$";
		String regex = "<a.*?/a>";
		// 我只想获得文章
		String regextitle = "<a href=\"/articles/.*?html";
		String regexnew = "<!--传.*?-->.*?><div.*?><.*?>";
		// String regex ="http://.*?>";
		Pattern pt = Pattern.compile(regexnew);
		Matcher mt = pt.matcher(ContentArea);
		while (mt.find()) {
			//System.out.println(mt.group());
			i++;
			// 获取标题
			// Matcher title = Pattern.compile(">.*?</a>").matcher(mt.group());
			Matcher title = Pattern.compile("alt=\".*?\"").matcher(mt.group());
			while (title.find()) {
				String a = title.group().replaceAll("alt=|\"", "");
				System.out.println("标题:" + a);
				
			}

			// 获取网址
			Matcher myurl = Pattern.compile("ahref=.*?html").matcher(mt.group());
			while (myurl.find()) {
				String b = myurl.group().replaceAll("ahref=", "http://www.qdaily.com").replace("\"", "");
				System.out.println("网址:" + b);
				try {
					SaveTU(b);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// System.out.println();
		}
		System.out.println("共有" + i + "个符合结果");
	}

	/**
	 * 以下代码是用来保存获得的标题和网址的
	 * 满满的泪和血
	 * @param startUrl
	 * @throws IOException
	 */
	static boolean SaveTU(String txt) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("a.txt",true)));
		bufferedWriter.write(txt);
		bufferedWriter.write("\n");
		bufferedWriter.close();
		return false;
	}

	// 获得开始采集网址
	public void getStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

	// 获得网址所在内容;
	public void getUrlContent() {

		StringBuffer is = new StringBuffer();
		try {
			URL myUrl = new URL(startUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(myUrl.openStream(), "utf-8"));

			String s;
			while ((s = br.readLine()) != null) {
				is.append(s);
			}
			urlContent = is.toString();
		} catch (Exception e)

		{
			System.out.println("网址文件未能输出");
			e.printStackTrace();
		}

	}

	// 获得网址所在的匹配区域部分
	public void getContentArea() {
		int pos1 = 0, pos2 = 0;
		pos1 = urlContent.indexOf(strAreaBegin) + strAreaBegin.length();
		pos2 = urlContent.indexOf(strAreaEnd, pos1);
		ContentArea = urlContent.substring(pos1, pos2).replaceAll("\t|\n", "").replaceAll(" ", "");
		System.out.println(ContentArea);
	}

	// 以下两个函数获得网址应该要包含的关键字及不能包含的关键字
	// 这里只做初步的实验。后期，保护的关键字及不能包含的关键字应该是不只一个的。
	public void getStringInUrl(String stringInUrl) {

	}

	public void getStringNotInUrl(String stringNotInUrl) {
	}

	// 获取采集规则

	// 获取url网址
	public void getUrl() {

	}

	public String getRegex() {
		return regex;

	}

	class UrlAndTitle {
		String myURL;
		String title;
	}
}