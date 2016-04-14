package baidu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1.�����վ��ҳ 2.ƥ������ �ͱ��� ������ 3.������ȱ����������� ���������� 4.�ظ�2 3 ֱ���������Ӷ���� 5
 * 
 * @author ����
 *
 */

/**
 * ƥ����ͨ���¸��� <div class="packery-item article">\n\t\t(.*?)< ƥ����ַ ƥ�����±���
 * <a href="(.*?)" class="com-grid-article" >\n\t
 * <div class="grid-article-hd">\n\t\t<img class="pic" src="(.*?)" alt="(.*?)">
 * 
 * @author ����
 *
 */
public class Qdaliy {
	private static final String String = null;
	static String Regexarticle = "<a href=\"/articles/.{5}.html";
	// <a href="/articles/23209.html" class="com-grid-key-article">
	static Map<Integer, String> map = new HashMap<>();
	static HashSet<String> hashset = new HashSet<String>();
	static Stack<String> stack = new Stack<String>();
	static int i = 0;

	/**
	 * ��Ҫ����һ��List������ַ�ͱ���
	 * 
	 * @param targetStr
	 * @param patternStr
	 * @return
	 */
	static boolean RegexString(String targetStr, String patternStr) {
		// ����һ����ʽģ�壬����ʹ��������ʽ����������Ҫץ������
		// �൱�����������ƥ��ĵط��ͻ����ȥ
		Pattern pattern = Pattern.compile(patternStr);
		// ����һ��matcher������ƥ��
		Matcher matcher = pattern.matcher(targetStr);
		// ����ҵ���
		// System.out.println(matcher.groupCount());
		while (matcher.find()) {
			// ��ӡ�����
			System.out.println(matcher.group());
			stack.push(matcher.group().replace("<a href=\"", "http://www.qdaily.com").replace("\"", ""));
			i++;
		}

		System.out.println("����" + i + "�����Ͻ��");
		return true;
	}

	/**
	 * ƥ��url
	 * 
	 * @param targetStr
	 * @param patternStr
	 * @return
	 */
	static boolean Regexurl(String targetStr, String patternStr) {
		// ����һ����ʽģ�壬����ʹ��������ʽ����������Ҫץ������
		// �൱�����������ƥ��ĵط��ͻ����ȥ
		int i = 0;
		Pattern pattern = Pattern.compile(patternStr);
		// ����һ��matcher������ƥ��
		Matcher matcher = pattern.matcher(targetStr);
		// ����ҵ���
		while (matcher.find()) {
			// ��ӡ�����
			System.out.println(matcher.group());
			// ��ȡ��ַ
			Matcher myurl = Pattern.compile("href=.*? ").matcher(matcher.group());
			while (myurl.find()) {
				System.out.println("��ַ:" + myurl.group().replaceAll("href=|>", ""));
			}
			i++;
		}
		System.out.println("����" + i + "�����Ͻ��");
		return true;
	}

	public static void main(String[] args) {
		String urlqdaliy = "http://www.qdaily.com/";// �������ձ�
		// �������Ӳ���ȡҳ������
		String result = SendGet(urlqdaliy);
		// ʹ������ƥ�����
		// ƥ����ַ(https?://)?(\w+\.)+(com|cn)
		// RegexString(result, "<a href=\"(.*?)\"");
		RegexString(result, Regexarticle);
		// <a href="(.*?)" class="com-grid-article" >\n\t<div
		// class="grid-article-hd">\n\t\t<img class="pic" src="(.*?)"
		// alt="(.*?)">
		// ƥ�䳬����<a href="/categories/3.html">����</a>
		// RegexString(result, "<a href=\"(.+?)\"[> ]");
		while (!stack.isEmpty()) {
			String url = stack.pop();
		}
		//不好意思下面的函数误删了20160317
//		String url = Tools.GetStack(stack);
//		System.out.println(url);
		// ʹ�õ������õ����е�����
	}

	static String SendGet(String url) {
		// ����һ���ַ��������洢��ҳ����
		String result = "";
		// ����һ�������ַ�������
		BufferedReader in = null;
		try {
			// ��stringת��url����
			URL realUrl = new URL(url);
			// ��ʼ��һ�����ӵ��Ǹ�url������
			URLConnection connection = realUrl.openConnection();
			// ��ʼʵ�ʵ�����
			connection.connect();
			// ��ʼ�� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			// ������ʱ�洢ץȡ����ÿһ�е�����
			String line;
			while ((line = in.readLine()) != null) {
				// ����ץȡ����ÿһ�в�����洢��result����
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally���ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
/**
 * ��ץ������ҳ��������  ��ҳ��Ϊ������
 * @param targetStr
 * @param patternStr
 * @return
 */
	static Stack RegexUrlSave(String targetStr, String patternStr) {
		Stack<String> stack = new Stack();
		
		// ����һ����ʽģ�壬����ʹ��������ʽ����������Ҫץ������
		// �൱�����������ƥ��ĵط��ͻ����ȥ
		Pattern pattern = Pattern.compile(patternStr);
		// ����һ��matcher������ƥ��
		Matcher matcher = pattern.matcher(targetStr);
		// ����ҵ���
		// System.out.println(matcher.groupCount());
		while (matcher.find()) {
			// ��ӡ�����
			System.out.println(matcher.group());
			stack.push(matcher.group().replace("<a href=\"", "http://www.qdaily.com").replace("\"", ""));
			i++;
		}
		return stack;

	}
}
