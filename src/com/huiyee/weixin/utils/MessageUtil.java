package com.huiyee.weixin.utils;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.huiyee.weixin.model.message.Item;
import com.huiyee.weixin.model.message.NewsMessage;
import com.huiyee.weixin.model.message.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * ��Ϣ������
 * 
 */
public class MessageUtil {

	/**
	 * ��Ϣ���ͣ��ı�
	 */
	public static final String MESSAGE_TYPE_TEXT = "text";

	/**
	 * ��Ϣ���ͣ�ͼƬ
	 */
	public static final String MESSAGE_TYPE_IMAGE = "image";

	/**
	 * ��Ϣ���ͣ�����
	 */
	public static final String MESSAGE_TYPE_MUSIC = "music";

	/**
	 * ��Ϣ���ͣ�ͼ��
	 */
	public static final String MESSAGE_TYPE_NEWS = "news";

	/**
	 * ��Ϣ���ͣ���Ƶ
	 */
	public static final String MESSAGE_TYPE_VOICE = "voice";

	/**
	 * ��Ϣ���ͣ�����
	 */
	public static final String MESSAGE_TYPE_LINK = "link";

	/**
	 * ��Ϣ���ͣ�����λ��
	 */
	public static final String MESSAGE_TYPE_LOCATION = "location";

	/**
	 * ��Ϣ���ͣ��¼�����
	 */
	public static final String MESSAGE_TYPE_EVENT = "event";

	/**
	 * �¼����ͣ�subscribe(����)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	/**
	 * �¼����ͣ�CLICK(�Զ���˵�����¼�)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	/**
	 * �¼����ͣ�VIEW(�Զ���˵�����¼�)
	 */
	public static final String EVENT_TYPE_VIEW = "VIEW";

	/**
	 * �¼����ͣ��ϱ�����λ��
	 */
	public static final String EVENT_TYPE_LOCATION = "LOCATION";

	/**
	 * ����΢�ŷ���������XML��
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(InputStream inputStream) throws Exception {
		// ����������洢��HashMap��
		Map<String, String> map = new HashMap<String, String>();

		// ��ȡ������
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// �õ�xml��Ԫ��
		Element root = document.getRootElement();
		// �õ���Ԫ�ص������ӽڵ�

		List<Element> elementList = root.elements();

		// ���������ӽڵ�
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// �ͷ���Դ
		inputStream.close();
		inputStream = null;

		return map;
	}

	/**
	 * ����΢�ŷ���������XML��
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		return parseXml(request.getInputStream());
	}

	/**
	 * �ı���Ϣ����ת����xml
	 * 
	 * @param textMessage
	 *            �ı���Ϣ����
	 * @return xml
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * ͼ����Ϣ����ת����xml
	 * 
	 * @param newsMessage
	 *            ͼ����Ϣ����
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Item().getClass());
		return xstream.toXML(newsMessage);
	}

	/**
	 * ��չxstream��ʹ��֧��CDATA��
	 * 
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// ������xml�ڵ��ת��������CDATA���
				boolean cdata = true;

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

}