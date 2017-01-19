package com.huiyee.pay.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.huiyee.esite.util.HyConfig;
import com.huiyee.pay.config.AlipayConfig4Phone;
import com.huiyee.pay.sign.MD5;
import com.huiyee.pay.sign.RSA;

/* *
 *������AlipayNotify4phone
 *���ܣ�֧����֪ͨ������
 *��ϸ������֧�������ӿ�֪ͨ����
 *�汾��3.3
 *���ڣ�2012-08-17
 *˵����
 *���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 *�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο�

 *************************ע��*************************
 *����֪ͨ����ʱ���ɲ鿴���дlog��־��д��TXT������ݣ������֪ͨ�����Ƿ�����
 */
public class AlipayNotify4phone {

    /**
     * ֧������Ϣ��֤��ַ
     */
    private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
    
    /**
     * ��֤��Ϣ�Ƿ���֧���������ĺϷ���Ϣ����֤callback
     * @param params ֪ͨ�������Ĳ�������
     * @return ��֤���
     */
    public static boolean verifyReturn(Map<String, String> params) {
	    String sign = "";
	    //��ȡ����ʱ��ǩ����֤���
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    //��֤ǩ��
	    boolean isSign = getSignVeryfy(params, sign, true);

        //д��־��¼����Ҫ���ԣ���ȡ����������ע�ͣ�
        //String sWord = "isSign=" + isSign + "\n ���ػ����Ĳ�����" + AlipayCore4phone.createLinkString(params);
	    //AlipayCore4phone.logResult(sWord);

        //�ж�isSign�Ƿ�Ϊtrue
        //isSign����true���밲ȫУ���롢����ʱ�Ĳ�����ʽ���磺���Զ�������ȣ��������ʽ�й�
        if (isSign) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ��֤��Ϣ�Ƿ���֧���������ĺϷ���Ϣ����֤�������첽֪ͨ
     * @param params ֪ͨ�������Ĳ�������
     * @return ��֤���
     */
    public static boolean verifyNotify(Map<String, String> params) throws Exception {
    	
    	//��ȡ�Ƿ���֧�����������������������֤���
    	String responseTxt = "true";
    	try {
        	//XML����notify_data���ݣ���ȡnotify_id
	    	Document document = DocumentHelper.parseText(params.get("notify_data"));
	    	String notify_id = document.selectSingleNode( "//notify/notify_id" ).getText();
			responseTxt = verifyResponse(notify_id);
    	} catch(Exception e) {
    		responseTxt = e.toString();
    	}
    	
    	//��ȡ����ʱ��ǩ����֤���
	    String sign = "";
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    boolean isSign = getSignVeryfy(params, sign,false);

        //д��־��¼����Ҫ���ԣ���ȡ����������ע�ͣ�
        //String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign + "\n ���ػ����Ĳ�����" + AlipayCore4phone.createLinkString(params);
	    //AlipayCore4phone.logResult(sWord);

        //�ж�responsetTxt�Ƿ�Ϊtrue��isSign�Ƿ�Ϊtrue
        //responsetTxt�Ľ������true����������������⡢���������ID��notify_idһ����ʧЧ�й�
        //isSign����true���밲ȫУ���롢����ʱ�Ĳ�����ʽ���磺���Զ�������ȣ��������ʽ�й�
        if (isSign && responseTxt.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * ����
     * @param inputPara Ҫ��������
     * @return ���ܺ���
     */
    public static Map<String, String> decrypt(Map<String, String> inputPara) throws Exception {
    	inputPara.put("notify_data", RSA.decrypt(inputPara.get("notify_data"), AlipayConfig4Phone.private_key, AlipayConfig4Phone.input_charset));
    	return inputPara;
    }

    /**
     * ���ݷ�����������Ϣ������ǩ�����
     * @param Params ֪ͨ�������Ĳ�������
     * @param sign �ȶԵ�ǩ�����
     * @param isSort �Ƿ�����
     * @return ���ɵ�ǩ�����
     */
	private static boolean getSignVeryfy(Map<String, String> Params, String sign, boolean isSort) {
    	//���˿�ֵ��sign��sign_type����
    	Map<String, String> sParaNew = AlipayCore4phone.paraFilter(Params);
        //��ȡ��ǩ���ַ���
    	String preSignStr = "";
    	if(isSort) {
    		preSignStr = AlipayCore4phone.createLinkString(sParaNew);
    	} else {
    		preSignStr = AlipayCore4phone.createLinkStringNoSort(sParaNew);
    	}
        //���ǩ����֤���
        boolean isSign = false;
        if(AlipayConfig4Phone.sign_type.equals("MD5") ) {
        	isSign = MD5.verify(preSignStr, sign, HyConfig.alikey, AlipayConfig4Phone.input_charset);
        }
        if(AlipayConfig4Phone.sign_type.equals("0001")){
        	isSign = RSA.verify(preSignStr, sign, AlipayConfig4Phone.ali_public_key, AlipayConfig4Phone.input_charset);
        }
        return isSign;
    }

    /**
    * ��ȡԶ�̷�����ATN���,��֤����URL
    * @param notify_id ֪ͨУ��ID
    * @return ������ATN���
    * ��֤�������
    * invalid����������� ��������������ⷵ�ش�����partner��key�Ƿ�Ϊ�� 
    * true ������ȷ��Ϣ
    * false �������ǽ�����Ƿ�������ֹ�˿������Լ���֤ʱ���Ƿ񳬹�һ����
    */
    private static String verifyResponse(String notify_id) {
        //��ȡԶ�̷�����ATN�������֤�Ƿ���֧��������������������

        String partner = HyConfig.alipartner;
        String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;

        return checkUrl(veryfy_url);
    }

    /**
    * ��ȡԶ�̷�����ATN���
    * @param urlvalue ָ��URL·����ַ
    * @return ������ATN���
    * ��֤�������
    * invalid����������� ��������������ⷵ�ش�����partner��key�Ƿ�Ϊ�� 
    * true ������ȷ��Ϣ
    * false �������ǽ�����Ƿ�������ֹ�˿������Լ���֤ʱ���Ƿ񳬹�һ����
    */
    private static String checkUrl(String urlvalue) {
        String inputLine = "";

        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
                .getInputStream()));
            inputLine = in.readLine().toString();
        } catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }

        return inputLine;
    }
}
