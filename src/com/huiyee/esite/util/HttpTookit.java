package com.huiyee.esite.util;
import org.apache.commons.httpclient.HttpClient; 
import org.apache.commons.httpclient.HttpMethod; 
import org.apache.commons.httpclient.HttpStatus; 
import org.apache.commons.httpclient.URIException; 
import org.apache.commons.httpclient.methods.GetMethod; 
import org.apache.commons.httpclient.methods.PostMethod; 
import org.apache.commons.httpclient.util.URIUtil; 
import org.apache.commons.lang.StringUtils; 
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.HashMap;
import java.util.Map; 

/** 
* HTTP������ 
*/ 

public final class HttpTookit { 
        private static Log log = LogFactory.getLog(HttpTookit.class); 

        /** 
         * ִ��һ��HTTP GET���󣬷���������Ӧ��HTML 
         * 
         * @param url                 �����URL��ַ 
         * @param queryString ����Ĳ�ѯ����,����Ϊnull 
         * @param charset         �ַ��� 
         * @param pretty            �Ƿ����� 
         * @return ����������Ӧ��HTML 
         */ 
        public static String doGet(String url, String queryString, String charset, boolean pretty) { 
                StringBuffer response = new StringBuffer(); 
                HttpClient client = new HttpClient(); 
                HttpMethod method = new GetMethod(url); 
                try { 
                        if (StringUtils.isNotBlank(queryString)) 
                                //��get�����������http����Ĭ�ϱ��룬����û���κ����⣬���ֱ���󣬾ͳ�Ϊ%ʽ�����ַ��� 
                                method.setQueryString(URIUtil.encodeQuery(queryString)); 
                        client.executeMethod(method); 
                        if (method.getStatusCode() == HttpStatus.SC_OK) { 
                                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset)); 
                                String line; 
                                while ((line = reader.readLine()) != null) { 
                                        if (pretty) 
                                                response.append(line).append(System.getProperty("line.separator"));
                                        else 
                                                response.append(line); 
                                } 
                                reader.close(); 
                        } 
                } catch (URIException e) { 
                        log.error("ִ��HTTP Get����ʱ�������ѯ�ַ�����" + queryString + "�������쳣��", e); 
                } catch (IOException e) { 
                        log.error("ִ��HTTP Get����" + url + "ʱ�������쳣��", e); 
                } finally { 
                        method.releaseConnection(); 
                } 
                return response.toString(); 
        } 

        /** 
         * ִ��һ��HTTP POST���󣬷���������Ӧ��HTML 
         * 
         * @param url         �����URL��ַ 
         * @param params    ����Ĳ�ѯ����,����Ϊnull 
         * @param charset �ַ��� 
         * @param pretty    �Ƿ����� 
         * @return ����������Ӧ��HTML 
         */ 
        public static String doPost(String url, Map<String, String> params, String charset, boolean pretty) { 
                StringBuffer response = new StringBuffer(); 
                HttpClient client = new HttpClient(); 
                PostMethod method = new UTF8PostMethod(url); 
              
                //����Http Post���� 
                if (params != null) { 
                        for (Map.Entry<String, String> entry : params.entrySet()) { 
									method.addParameter(entry.getKey(), entry.getValue());
                        } 
                } 
                try { 
                        client.executeMethod(method); 
                        if (method.getStatusCode() == HttpStatus.SC_OK) { 
                                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset)); 
                                String line; 
                                while ((line = reader.readLine()) != null) { 
                                        if (pretty) 
                                                response.append(line).append(System.getProperty("line.separator"));
                                        else 
                                                response.append(line); 
                                } 
                                reader.close(); 
                        } 
                } catch (IOException e) { 
                        log.error("ִ��HTTP Post����" + url + "ʱ�������쳣��", e); 
                } finally { 
                        method.releaseConnection(); 
                } 
                return response.toString(); 
        }
        public static  class UTF8PostMethod extends PostMethod{     
            public UTF8PostMethod(String url){     
                super(url);     
            }     
            @Override    
            public String getRequestCharSet() {     
                return "utf-8";     
            }     
        }  
       
        
    	public static String convert(String utfString){
    		StringBuilder sb = new StringBuilder();
    		int i = -1;
    		int pos = 0;
    		
    		while((i=utfString.indexOf("\\u", pos)) != -1){
    			sb.append(utfString.substring(pos, i));
    			if(i+5 < utfString.length()){
    				pos = i+6;
    				sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));
    			}
    		}
    		
    		return sb.toString();
    	}

        public static void main(String[] args) { 
              //  String y = doGet("http://api.map.baidu.com/place/v2/search", "q=�����������԰&region=����&output=json&ak=025j9m7SviBpzAzFtN4pczG0", "utf-8", false); 
               // System.out.println(y); 
                Map<String, String> ms=new HashMap<String, String>();
                ms.put("ddd", "1111");
                ms.put("out_trade_no", "5555");
            System.out.println(    doPost("http://127.0.0.1:8080/user/alcb.action", ms, "utf-8", false));
                
                
        } 
}