package baidu;

import java.util.Calendar;

/**
 * 闇�瑕佷竴涓瘮杈冧袱涓椂闂寸殑鍑芥暟锛屼竴涓椂闂存槸绯荤粺鏃堕棿 鍙︿竴涓椂闂存槸鎴戠粰鐨勬椂闂�
 * 
 * @author 鏄庤緣
 *
 */
public class DateTools {
	public static void main(String[] args) {
//		Calendar c = Calendar.getInstance();
//		int Hour = c.get(Calendar.HOUR_OF_DAY);
//		int minute = c.get(Calendar.MINUTE);
//		System.out.println("褰撳墠鐨勬椂闂翠负" + Hour + ":" + minute);
//
//		Calendar b = Calendar.getInstance();
//		b.set(2012, 12, 24);
//		if (c.after(b)) {
//			System.out.println("true");
//		} else
//			System.out.println("false");
//		System.out.println();
//		
		
		isInTime(8,00);
		// System.out.println(calendar);
		// System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		// System.out.println(calendar.get(Calendar.HOUR));
		// SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println(df.format(calendar));
		// System.out.println(calendar.MONTH);
	}

	public static boolean isInTime(int hour, int minute) {
		Calendar c = Calendar.getInstance();
		Calendar before = (Calendar) c.clone();
		Calendar after = (Calendar) c.clone();
		before.set(before.get(Calendar.YEAR), before.get(Calendar.MONTH), before.get(Calendar.DAY_OF_MONTH), hour, minute);
		after.set(after.get(Calendar.YEAR), after.get(Calendar.MONTH),after.get(Calendar.DAY_OF_MONTH), hour+1,minute);
		System.out.println(c.toString());
		System.out.println(before.toString());
		System.out.println(after.toString());
		if (c.after(before)&&c.before(after)) {
			System.out.println("true");
			return true;
		} else {
			System.out.println("false");

			return false;
		}
	}
}
