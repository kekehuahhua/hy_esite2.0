package com.huiyee.esite.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static DateFormat dateFormat4 = new SimpleDateFormat("yyyy��MM��dd��");
	static SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
	static SimpleDateFormat dateFormat3 = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
	static SimpleDateFormat dateFormat5 = new SimpleDateFormat("yyyyMMdd");
	static SimpleDateFormat dateFormat6 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	static SimpleDateFormat dateFormat7 = new SimpleDateFormat("HH:mm");
	/**
	 * ����yyyyMMddHHmmss�� �ַ�����ʽ��������
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date getDate2(String dateStr)
	{
		try
		{
			return dateFormat2.parse(dateStr);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * �����ڻ��ɡ�yyyyMMddHHmmss�� �ַ�����ʽ
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getDate2String(Date date)
	{
		return dateFormat2.format(date);
	}
	
	public static String getDate5String(Date date)
	{
		return dateFormat5.format(date);
	}

	/**
	 * ��"yyyy-MM-dd"�ַ�����ʽ��������
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date getDate(String dateStr)
	{
		try
		{
			return dateFormat.parse(dateStr);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����ڸ�ʽ����"yyyy-MM-dd"�ַ���
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date)
	{
		String dateString = null;
		if (date != null)
		{
			dateString = dateFormat.format(date);
		}
		return dateString;
	}
	
	
	/**
	 * �����ڸ�ʽ����"HH:mm"�ַ���
	 * 
	 * @param date
	 * @return
	 */
	public static String gerDateFormat7(Date date)
	{
		String dateString = null;
		if (date != null)
		{
			dateString = dateFormat7.format(date);
		}
		return dateString;
	}
	/**
	 * �����ڸ�ʽ����"yyyy��MM��dd��"�ַ���
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateStringM(Date date)
	{
		String dateString = null;
		if (date != null)
		{
			dateString = dateFormat4.format(date);
		}
		return dateString;
	}

	/**
	 * �����ڸ�ʽ����"MM/dd"�ַ���
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString2(Date date)
	{
		String dateString = null;
		if (date != null)
		{
			dateString = dateFormat3.format(date).substring(3, 8);
		}
		return dateString;
	}

	/**
	 * ��"yyyy-MM-dd HH:mm:ss"�ַ�����ʽ��������
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date getDateTime(String dateStr)
	{
		try
		{
			return dateFormatTime.parse(dateStr);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����ڸ�ʽ����"yyyy-MM-dd HH:mm:ss"�ַ���
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTimeString(Date date)
	{
		if (date == null)
			return "";
		return dateFormatTime.format(date);
	}
	
	/**
	 * �����ڸ�ʽ����"yyyy-MM-dd HH:mm"�ַ���
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString6(Date date)
	{
		if (date == null)
			return "";
		return dateFormat6.format(date);
	}
	
	
	/**
	 * ָ�����ڽ��м��·�
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date reduceMonth(Date date, int offset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - offset);
		return calendar.getTime();
	}

	/**
	 * ָ�����ڽ��м�����
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date reduceDays(Date date, int offset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - offset);// �����ڼ�λ����

		return calendar.getTime();
	}

	/**
	 * ָ�����ڽ��м�Сʱ
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date reduceHours(Date date, int offset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - offset);

		return calendar.getTime();
	}

	/**
	 * ָ�����ڽ��м�����
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date reduceMinutes(Date date, int offset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - offset);
		return calendar.getTime();
	}

	/**
	 * ��ָ�����ڽ��мӼ�����
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date addDays(Date date, int offset)
	{
		Date result = null;
		if (date != null)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + offset);// �����ڼ�λ����
			result = calendar.getTime();
		}
		return result;
	}

	/**
	 * ָ�����ڽ��мӼ�Сʱ
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date addHours(Date date, int offset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + offset);

		return calendar.getTime();
	}

	/**
	 * �ƶ����ڼӼ�����
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date addMinutes(Date date, int offset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + offset);

		return calendar.getTime();
	}

	/**
	 * �ƶ����ڼӼ���
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date addSeconds(Date date, int offset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + offset);

		return calendar.getTime();
	}

	/**
	 * �õ����ܵ���һ
	 * 
	 * @param day
	 * @return
	 */
	public static Date getMondayByCurrentWeek()
	{
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		System.out.println(calendar.getTime());
		int idx = calendar.get(Calendar.DAY_OF_WEEK);
		Date result = null;
		if (idx == 1)
		{
			result = reduceDays(date, 6);
		} else
		{
			result = reduceDays(date, idx - 2);
		}
		return result;
	}

	/**
	 * �õ����ܵ�����
	 * 
	 * @param day
	 * @return
	 */
	public static Date getSundayByCurrentWeek()
	{
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		System.out.println(calendar.getTime());
		int idx = calendar.get(Calendar.DAY_OF_WEEK);
		Date result = null;
		if (idx == 1)
		{
			result = date;
		} else
		{
			result = addDays(date, 7 - idx + 1);
		}
		return result;
	}

	/**
	 * @Title: formatTimetoDate
	 * @Description: ��ʽ�����ڸ�ʽ��YYYY-MM-DD hh24:mi:ss תΪ YYYY-MM-DD
	 * @param
	 * @param date
	 * @param
	 * @return
	 * @return Date
	 * @throws
	 */
	public static Date formatTimetoDate(Date date)
	{
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = dateFm.format(date);
		return DateUtil.getDate(dateTime);

	}

	/**
	 * @Title: round
	 * @Description: С���������뱣��ָ��λС��
	 * @param
	 * @param v
	 * @param
	 * @param scale
	 * @param
	 * @return
	 * @return double
	 * @throws
	 */
	public static double round(double v, int scale)
	{

		if (scale < 0)
		{

			throw new IllegalArgumentException("The scale must be a positive integer or zero");

		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * �����������ڵĲ��
	 * 
	 * @param diffType
	 *            �����ʲô��Ϊ��λ,����Calendar.DATE,СʱCalendar.HOUR��
	 */
	public static long dateDiff(Date date1, Date date2, int diffType)
	{
		long rtn = 0;
		long diff = 0;
		if (date1 != null && date2 != null)
		{
			diff = date2.getTime() - date1.getTime();
			switch (diffType)
			{
			case Calendar.YEAR:
				rtn = diff / 1000 / 3600 / 24 / 365;
				break;
			case Calendar.MONTH:
				rtn = diff / 1000 / 3600 / 24 / 12;
				break;
			case Calendar.WEEK_OF_MONTH:
				rtn = diff / 1000 / 3600 / 24 / 7;
				break;
			case Calendar.DATE:
				rtn = diff / 1000 / 3600 / 24;
				break;
			case Calendar.HOUR:
				rtn = diff / 1000 / 3600;
				break;
			case Calendar.MINUTE:
				rtn = diff / 1000 / 60;
				break;
			case Calendar.SECOND:
				rtn = diff / 1000;
				break;
			case Calendar.MILLISECOND:
				rtn = diff;
				break;
			default:
				rtn = 0;
			}
		}
		return rtn;
	}

	public static void main(String[] args) {
		Date d = DateUtil.reduceDays(new Date(), 1);
		System.out.println(d);
	}

}
