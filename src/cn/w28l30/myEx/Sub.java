package cn.w28l30.myEx;

public class Sub {

	public static String sub(String s) {
		if (s.length() > 10) {
			return s.substring(0, 10) + "...";
		}
		return s;
	}

}
