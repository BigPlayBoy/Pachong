package baidu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1 浠庢枃浠堕噷璇诲彇鏁版嵁 2 鎵惧埌閲岄潰鐨勭綉鍧� 3 鑾峰彇缃戝潃鍐呭 4 淇濆瓨鏍囬鍜屽唴瀹�
 * 
 * @author 鏄庤緣
 *
 */

public class DownloadTitle {
	static Stack stack = null;

	public static void main(String[] args) throws IOException {
		stack = ReadUrl("a.txt");
		String url = null;
		String regextitle = "<title>(.*?)</title>";
		String regexcontenth2 = "<h2class=\"excerpt\">.*?</h2>";
		String regexcontentp = "<divclass=\"detail\">.*?</div>";
		int i = 1;
		while (!stack.empty()) {
			url = SendGet(GetUrl(stack)).replaceAll("\t\n", "").replaceAll(" ", "");
			String title = RegexString(url, regextitle);
			String h2 = RegexString(url, regexcontenth2);
			String detail = RegexString(url, regexcontentp);
			System.out.println(title + "    \n" + h2 + "\n" + detail);
//			SaveTU(title, i + ".txt");
//			SaveTU(h2, i + ".txt");
//			SaveTU(detail, i + ".txt");
			SaveHtml(title, h2+detail, i+".html");
			i++;
		}
	}

	static String RegexString(String targetStr, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		// System.out.println(matcher.groupCount());
		while (matcher.find()) {
			// System.out.println(matcher.group());
			return matcher.group();
		}
		return null;
	}

	static Stack ReadUrl(String string2) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(string2)));
		Stack<String> stack = new Stack<>();
		String string = null;
		while ((string = in.readLine()) != null) {
			stack.push(string);
		}
		return stack;
	}

	/**
	 * 淇濈暀缃戦〉 鍙渶瑕佷繚瀛樻爣棰樺拰鏂囨湰鍗冲彲 闇�瑕佷笁涓彉閲� 鏍囬 鏂囨湰 鏂囦欢鍚� 闄や簡杩欎笁鏍� 杩樻湁html鐨勫熀鏈牸寮�
	 * 
	 * @param txt
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	static boolean SaveHtml(String title, String content, String filename) throws IOException {
		Savehtml1(filename);
		SaveTU(title, filename);
		Savehtml2(filename);
		SaveTU(content, filename);
		Savehtml3(filename);
		return false;

	}

	/**
	 * 淇濆瓨缃戦〉寮�澶�
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	static boolean Savehtml1(String filename) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filename, true)));
		bufferedWriter.write("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		bufferedWriter.close();
		return true;
	}

	/**
	 * 淇濆瓨head鍜宐ody
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */

	static boolean Savehtml2(String filename) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filename, true)));
		bufferedWriter.write("</head><body> ");
		bufferedWriter.close();
		return true;
	}

	/**
	 * 淇濆瓨bodyh鍜宧tml
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */

	static boolean Savehtml3(String filename) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filename, true)));
		bufferedWriter.write("</body></html> ");
		bufferedWriter.close();
		return true;
	}

	/**
	 * 浠ヤ笅浠ｇ爜鏄敤鏉ヤ繚瀛樿幏寰楃殑鏍囬鍜岀綉鍧�鐨� 婊℃弧鐨勬唱鍜岃
	 * 
	 * @param startUrl
	 * @throws IOException
	 */
	static boolean SaveTU(String txt, String filename) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filename, true)));
		bufferedWriter.write(txt);
		// bufferedWriter.write("\n");
		bufferedWriter.close();
		return false;
	}

	/**
	 * 鑾峰彇鏍堥《鐨勬暟鎹�
	 * 
	 * @param stack
	 * @return
	 */
	static String GetUrl(Stack<String> stack) {
		return stack.pop();
	}

	static String SendGet(String url) {
		// 锟斤拷锟斤拷一锟斤拷锟街凤拷锟斤拷锟斤拷锟斤拷锟芥储锟斤拷页锟斤拷锟斤拷
		String result = "";
		// 锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟街凤拷锟斤拷锟斤拷锟斤拷
		BufferedReader in = null;
		try {
			// 锟斤拷string转锟斤拷url锟斤拷锟斤拷
			URL realUrl = new URL(url);
			// 锟斤拷始锟斤拷一锟斤拷锟斤拷锟接碉拷锟角革拷url锟斤拷锟斤拷锟斤拷
			URLConnection connection = realUrl.openConnection();
			// 锟斤拷始实锟绞碉拷锟斤拷锟斤拷
			connection.connect();
			// 锟斤拷始锟斤拷 BufferedReader锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷取URL锟斤拷锟斤拷应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			// 锟斤拷锟斤拷锟斤拷时锟芥储抓取锟斤拷锟斤拷每一锟叫碉拷锟斤拷锟斤拷
			String line;
			while ((line = in.readLine()) != null) {
				// 锟斤拷锟斤拷抓取锟斤拷锟斤拷每一锟叫诧拷锟斤拷锟斤拷娲拷锟絩esult锟斤拷锟斤拷
				result += line;
			}
		} catch (Exception e) {
			System.out.println("锟斤拷锟斤拷GET锟斤拷锟斤拷锟斤拷锟斤拷斐ｏ拷锟�" + e);
			e.printStackTrace();
		}
		// 使锟斤拷finally锟斤拷锟截憋拷锟斤拷锟斤拷锟斤拷
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
}
