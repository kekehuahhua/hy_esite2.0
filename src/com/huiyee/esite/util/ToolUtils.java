package com.huiyee.esite.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.net.BCodec;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

public class ToolUtils {

	private static Log logger = LogFactory.getLog(ToolUtils.class);

	private static BCodec bCodec = new BCodec();
	private static URLCodec uCodec = new URLCodec();
	public static final String sdateformat1="yyyy-MM-dd HH:mm:ss";
    public static final String sdateformat2="yyyy-MM-dd";

	private static String regex = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";

	private static Pattern pattern = Pattern.compile(regex);

	public static String encodeString(String s) throws Exception {
		return bCodec.encode(s);
	}

	public static String decodeString(String s) throws Exception {
		try {
			return bCodec.decode(s.trim());
		} catch (Exception x) {
			logger.warn("could not base64 decode:" + s + ":");
		}
		return null;
	}

	public static String encodeURLString(String s) throws Exception {
		return uCodec.encode(s);
	}

	public static String decodeURLString(String s) throws Exception {
		return uCodec.decode(s);
	}

	public static boolean isEmailValid(String e) {
		Matcher m = pattern.matcher(e);
		return m.find();

	}

	public static String trimAllWhiteSpace(String s) {
		String t = StringUtils.trimLeadingWhitespace(s);
		return StringUtils.trimTrailingWhitespace(t);
	}

	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	public static String dateToStrTime(Date res) {
		String strDate = "";
		strDate = res.toString();
		strDate = strDate.substring(0, (strDate.length() - 2));
		return strDate;
	}

	public static long getDayNums(long tempTimeMillon) {
		long nowTimeMillon = System.currentTimeMillis();
		long days = (((tempTimeMillon - nowTimeMillon) / 1000) / 3600) / 24 + 1;
		return days;
	}

	/**
	 * �����������ڵĲ��
	 * 
	 * @param diffType
	 *            �����ʲô��Ϊ��λ,����Calendar.DATE,СʱCalendar.HOUR��
	 */
	public static long dateDiff(Date date1, Date date2, int diffType) {
		long rtn = 0;
		long diff = 0;
		if (date1 != null && date2 != null) {
			diff = date2.getTime() - date1.getTime();
			switch (diffType) {
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

	public static long DoubleConvertLong(double numDouble) {
		long numLong = 0;
		String str = String.valueOf(numDouble);
		str = str.substring(0, str.length() - 2);
		numLong = Long.parseLong(str);
		return numLong;
	}

	public static String dateToString(Date resDate) {
		String strDate = "";
		strDate = resDate.toString();
		strDate = strDate.substring(0, 10);
		return strDate;
	}

	public static String createTimeFormateToChinese(Date createTime) {
		long nowTimes = new Date().getTime();
		long oldTimes = createTime.getTime();
		long result = (nowTimes - oldTimes) / (60 * 1000);
		if (result <= 15) {
			return "�ո�...";
		} else if (result > 15 && result <= 60) {
			return "һСʱ֮ǰ...";
		} else if (result > 60 && result <= 24 * 60) {
			return "����...";
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm");
			return dateFormat.format(createTime);
		}
	}

	private static DecimalFormat moneyFormat = new DecimalFormat("######0.00");

	public static String getMoneyFormatString(double d) {
		return moneyFormat.format(d);
	}

	private static DecimalFormat discountFormat = new DecimalFormat("######0.0");

	public static String getDiscountFormatString(double d) {
		return discountFormat.format(d);
	}

	/**
	 * ͨ�û��򷽷�����List�еĶ������˳��ÿ�������һ��
	 * 
	 * @param src
	 *            ԭʼlist
	 * @return ��list��Ԫ�ش���˳����list
	 */
	public static List mix(List src) {
		Collections.shuffle(src);
		return src;
	}

	/**
	 * ���ı��еĸ����������ַ�������<a>�ڵ㣬����ҳ����ʾ���û�ֱ�ӵ��
	 * 
	 * @param text
	 * @return ����A�ڵ���ַ���
	 */
	public static String textAddLink(String text) {
		if (text == null || "".equals(text)) {
			return "";
		}
		int index = text.indexOf("http://www.geili360.com/f2/");
		if (index == -1) {
			return text;
		}
		int index2 = text.indexOf("html", index) + 4;// ��λ��.html�Ľ�β��
		String href = text.substring(index, index2);
		String a = "<a target=\"_blank\" href=\"" + href + "\">" + href
				+ "</a>";
		String result = text.substring(0, index) + a + text.substring(index2);
		return result;
	}
	public static Integer createRadomNum() {
		Random random = new Random();
		return random.nextInt(99999999);
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * ���url�ĳ���
	 * 
	 * @param oldtext
	 * @return
	 */
	public static String addurlLink(String text) {
		String regex = "(http:|https:)//[^[A-Za-z0-9\\._\\?%&+\\-=/#]]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		StringBuffer result = new StringBuffer();
		while (matcher.find()) {
			StringBuffer replace = new StringBuffer();
			replace.append("<a href=\"").append(matcher.group());
			replace.append("\" target=\"_blank\">" + matcher.group() + "</a>");
			matcher.appendReplacement(result, replace.toString());
		}
		matcher.appendTail(result);
		return result.toString();
	}
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	public static List<String> getUrls(String text)
	{
		String regex = "(http:|https:)//[^[A-Za-z0-9\\._\\?%&+\\-=/#]]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		List<String> resutl=new ArrayList<String>();
		while (matcher.find()) {
			resutl.add(matcher.group());
		}
		return resutl;
	}

	/**
	 * ���@ĳ�˳���
	 * 
	 * @param oldtext
	 * @return
	 */
	public static String addATLink(String text) {
		String regex = "@[^\\.^\\,^:^;^!^(^)^\\?^\\s^#^@^��^��^��^��^��^��^��^��]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		StringBuffer result = new StringBuffer();
		while (matcher.find()) {
			StringBuffer replace = new StringBuffer();
			String username = matcher.group().substring(1,
					matcher.group().length());
			replace
					.append(
							"<a wb-nickName='"
									+ username
									+ "' class='nickNameClass'  href=\"http://weibo.com/n/")
					.append(username);
			replace.append("\" target=\"_blank\">" + matcher.group() + "</a>");
			matcher.appendReplacement(result, replace.toString());
		}
		matcher.appendTail(result);
		return result.toString();
	}

	/**
	 * ���#ĳ����#����
	 * 
	 * @param oldtext
	 * @return
	 */
	public  static String addTrendsLink(String text) {
			String regex = "#[^#]+#";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(text);
			StringBuffer result = new StringBuffer();
			while (matcher.find()) {
				if(!matcher.group().contains("href")){
				StringBuffer replace = new StringBuffer();
				replace.append("<a href=\"http://weibo.com/k/").append(
						matcher.group().substring(1,
								matcher.group().length() - 1));
				replace.append("\" target=\"_blank\">" + matcher.group()
						+ "</a>");
				matcher.appendReplacement(result, replace.toString());
				}
			}
			matcher.appendTail(result);
			return result.toString();
	}
	
	/**
	 * ����gsidֵ���ʲ�����ҳ�棬��ȡstֵ
	 * 
	 * @param gsid
	 * @return null��ʾ��ȡʧ�ܡ��������Ӧ����һ��4���ַ����ַ���
	 */
	public static String getSt(String gsid) {
		URL url = null;
		InputStream in = null;
		BufferedReader br = null;
		String resultStr = null;
		try {
			url = new URL("http://weibo.cn/?gsid=" + gsid);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn
					.setRequestProperty(
							"User-agent",
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");
			conn.connect();
			in = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			resultStr = sb.toString().trim();
			int index = resultStr.indexOf("st=");
			int index2 = resultStr.indexOf("\"", index);
			if ((index2 - index) != 7) {
				return null;// �����ˡ�st=0d4fֵ
			}
			return resultStr.substring(index + 3, index2);
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static final String getSysGroupNameByType(String type){
		if("REG".equals(type)){
			return "��ͨ�û�";
		}else if("DIS".equals(type)){
			return	"�����û�";
		}else if("ACT".equals(type)){
			return "��Ծ�û�";
		}else if("LOY".equals(type)){
			return "�ҳ��û�";
		}else if("NTS".equals(type)){
			return "δ��������";
		}else if("SNC".equals(type)){
			return "δ��������";
		}else if("CLK".equals(type)){
			return "���ŵ������";
		}else if("ONC".equals(type)){
			return "����δ�������";
		}else if("SUB".equals(type)){
			return "��������";
		}else if("USB".equals(type)){
			return "�˶�����";
		}else if("CSB".equals(type)){
			return "������";
		}else if("CHB".equals(type)){
			return "Ӳ������";
		}else if("BLK".equals(type)){
			return "����������";
		}else if("NVL".equals(type)){
			return "��Ч����";
		}
		return null;
	}
	
	public static long getIpByStr(String ip)
	{
		try {
            String[] els = StringUtils.delimitedListToStringArray(ip, ".");
            if (els == null || els.length != 4)
            {// maybe it is IPV6 ignore
            	return 0;
            }
            long b0 = ((long) (new Integer(els[3]).byteValue() & 0xff));
            long b1 = ((long) (new Integer(els[2]).byteValue() & 0xff)) << 8;
            long b2 = ((long) (new Integer(els[1]).byteValue() & 0xff)) << 16;
            long b3 = ((long) (new Integer(els[0]).byteValue() & 0xff)) << 24;
            return (long) (b0 | b1 | b2 | b3);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(ip);
            e.printStackTrace();
        }
        return 0;
	}
	
	 /** ������ */
    private static final Object lockObj = new Object();

    /** ��Ų�ͬ������ģ���ʽ��sdf��Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    /**
     * ����һ��ThreadLocal��sdf,ÿ���߳�ֻ��newһ��sdf
     * 
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

        // �˴���˫���жϺ�ͬ����Ϊ�˷�ֹsdfMap������������put�ظ���sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // ֻ��Map�л�û�����pattern��sdf�Ż������µ�sdf������map
                    // �����ǹؼ�,ʹ��ThreadLocal<SimpleDateFormat>���ԭ��ֱ��new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }
        return tl.get();
    }

    /**
     * ����ThreadLocal<SimpleDateFormat>����ȡSimpleDateFormat,����ÿ���߳�ֻ����һ��SimpleDateFormat
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }
    
    public static Map<String, Object> parseJSON2Map(String jsonStr){  
        Map<String, Object> map = new HashMap<String, Object>();  
        //��������  
        JSONObject json = JSONObject.fromObject(jsonStr);  
        for(Object k : json.keySet()){  
            Object v = json.get(k);   
            //����ڲ㻹������Ļ�����������  
            if(v instanceof JSONArray){  
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
                Iterator<JSONObject> it = ((JSONArray)v).iterator();  
                while(it.hasNext()){  
                    JSONObject json2 = it.next();  
                    list.add(parseJSON2Map(json2.toString()));  
                }  
                map.put(k.toString(), list);  
            } else {  
                map.put(k.toString(), v);  
            }  
        }  
        return map;  
    }  
 
    private static int counter = 0; 
    public static int countStr(int num,String str1, String str2) {
    	counter = num;
        if (str1.indexOf(str2) == -1) {  
            return 0;  
        } else if (str1.indexOf(str2) != -1) {  
            counter++;  
            countStr(counter,str1.substring(str1.indexOf(str2) +  
                   str2.length()), str2);  
               return counter;  
        }  
        return 0;
    }
    
  //����ĸתСд
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    //����ĸת��д
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    
    public static String cvsStr(String str)
	{
		if (str != null&&!"null".equalsIgnoreCase(str))
		{
			if (str.indexOf(",") != -1)
			{
				return "\"" + str + "\"";
			} else
			{
				return str;
			}
		} else
		{
			return "";
		}
	}
    
    public static String listToString(List<String> list,String st)
	{
    	StringBuilder sb=new StringBuilder();
		if(list!=null&&list.size()>0){
			for (String str : list)
			{
				str=cvsStr(str);
				if(sb.length()>0){
					sb.append(st+str);
				}else{
					sb.append(str);
				}
			}
			return sb.toString();
		}
		return null;
    	
	}
}
