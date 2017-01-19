package com.huiyee.esite.util;

public class Base62Util
{
	private static String[] str62key =
	{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };

	public static void main(String[] args)
	{
		String mid = UrlToMid("8s07mHQu");// 3647036354004279
		System.out.println(mid);
		System.out.println(MidToUrl("20142149703711"));
		// System.out.println( "AjL0A6Moq".substring(3, 4));
		// System.out.println(MidToUrl("3590521559673267"));
	}

	/**
	 * urlת����mid��ֵ
	 * 
	 * @param url
	 * @return
	 */
	public static String UrlToMid(String url)
	{
		String mid = "";
		for (int i = url.length() - 4; i > -4; i = i - 4)
		{// �ֱ����ĸ�Ϊһ��
			int offset1 = i < 0 ? 0 : i;
			int offset2 = i + 4;
			String str = url.substring(offset1, offset2);
			str = str62to10(str);// String���͵�ת����ʮ���Ƶ���
			// �����ǵ�һ�飬����7λ��0
			if (offset1 > 0)
			{
				while (str.length() < 7)
				{
					str = '0' + str;
				}
			}
			mid = str + mid;
		}
		return mid;
	}

	/**
	 * midת����url�����Ժ��ֵ
	 * 
	 * @param mid
	 * @return
	 */
	public static String MidToUrl(String mid)
	{
		String url = "";
		try
		{
			for (int j = mid.length() - 7; j > -7; j = j - 7)
			{// ��7������Ϊһ����λ����ת��
				int offset3 = j < 0 ? 0 : j;
				int offset4 = j + 7;
				// String l = mid.substring(mid.Length - 14, mid.Length - 13);
				if ((j > 0 && j < 6) && mid.length() == 19 && (mid.substring(mid.length() - 14, mid.length() - 13).equals("0") ))
				{
					String num = mid.substring(offset3 + 1, offset4);
					num = int10to62(Integer.parseInt(num));// ʮ����ת����62����
					url = 0 + num + url;
					if (url.length() == 9)
					{
						url = url.substring(1, url.length());
					}
				} else
				{
					String num = mid.substring(offset3, offset4);
					num = int10to62(Integer.parseInt(num));
					url = num + url;
				}
			}
		} catch (Exception e)
		{

		}
		return url;
	}

	/**
	 * 62����ת����10����
	 * 
	 * @param str
	 * @return
	 */
	private static String str62to10(String str)
	{
		String i10 = "0";
		int c = 0;
		for (int i = 0; i < str.length(); i++)
		{
			int n = str.length() - i - 1;
			String s = str.substring(i, i + 1);
			for (int k = 0; k < str62key.length; k++)
			{
				if (s.equals(str62key[k]))
				{
					int h = k;
					c += (int) (h * Math.pow(62, n));
					break;
				}
			}
			i10 = String.valueOf(c);
		}
		return i10;
	}

	/**
	 * 10����ת����62����
	 * 
	 * @param int10
	 * @return
	 */
	private static String int10to62(double int10)
	{
		String s62 = "";
		int w = (int) int10;
		int r = 0;
		int a = 0;
		while (w != 0)
		{
			r = (int) (w % 62);
			s62 = str62key[r] + s62;
			a = (int) (w / 62);
			w = (int) Math.floor((double) a);
		}
		return s62;
	}

}
