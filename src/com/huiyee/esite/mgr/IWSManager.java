package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.tfmodel.HyWbComment;
import com.huiyee.tfmodel.HyWbLogin;
import com.huiyee.tfmodel.HyWbSrc;
import com.huiyee.tfmodel.HyWbUser;

public interface IWSManager {
	
	/**
	 * ͨ��΢�������ҵ�΢��
	 * 
	 * @param token
	 *            ΢��token
	 * @param url
	 *            ΢������
	 * @return ����΢��
	 */
	public HyWbSrc findWeiBoByWbUrl(String token,
			String url);

	/**
	 * ͨ��wbid����id
	 * @param token
	 * @param wbid
	 * @return
	 */
	public HyWbSrc findWeiBoByWbid(String token, String wbid,boolean fast);
	
	/**
	 * AT �����ϵ��
	 * @param token
	 * @param key
	 * @return
	 */
	public List<String> findAtUsers(String token,String key,int size,long wbuid);
	
	/**
	 * 
	 * @param token
	 *            ����΢���ߵ�token
	 * @param content
	 *            ����΢��������
	 * @return ����wbid
	 */
	public HyWbSrc weibo(String token, String content);

	/**
	 * 
	 * @param token
	 *            �����ߵ�token
	 * @param content
	 *            ����΢��������
	 * @param img
	 *            img��url��ַ
	 * @return ����wbid
	 */
	public HyWbSrc weiboByPic(String token, String content, String img);
	
	/**
	 * ת��һ��΢��
	 * 
	 * @param id
	 *            Ҫת����΢��ID
	 * @param content
	 *            ��ӵ�ת���ı���������URLencode�����ݲ�����140�����֣�������Ĭ��Ϊ��ת��΢����
	 * @param is_comment
	 *            �Ƿ���ת����ͬʱ�������ۣ�0����1�����۸���ǰ΢����2�����۸�ԭ΢����3�������ۣ�Ĭ��Ϊ0
	 * @return ����wbid
	 */
	public HyWbSrc repost(String id, String content, String token, Integer is_comment);
	
	/**
	 * 
	 * @param wbid
	 *            ���۵�΢��id
	 * @param token
	 *            �����ߵ�token
	 * @param content
	 *            ���۵�����
	 * @return 0:ʧ��;1:�ɹ�
	 */
	public HyWbComment wbcomment(String wbid, String token, String content);
	
	/**
	 * �ӹ�ע
	 * @param token ��½��
	 * @param wbuid ����ע����,һ��Ϊ��΢
	 * @return
	 */
	public HyWbUser guanzhu(String token,long wbuid);
	
	/**
	 * 
	 * @param cmd_type
	 *            1.�����½��Ȩ
	 * @param redirect_uri
	 *            �ص���ַ
	 * @param state
	 *            ����Ĳ���,���ᴫ���ص�����
	 * @param appkey �����appid
	 * @return
	 */

	public String loginForLink(int cmd_type, String redirect_uri, String state,String appkey);
	
	/**
	 * 
	 * @param code
	 *            sina��������code������ȡtokenֵ
	 * @param appkey �����appid
	 * @param appsecret �����app����Կ
	 * @redirect_URI ���ص�ַ,���Բ���д
	 * @return ����LoginWeiBoDto����,�������token��Ϣ,ʧЧʱ��(������),wbuid
	 */

	public HyWbLogin loginForWeiBo(String code,String appkey,String appsecret,String redirect_URI);
	
	/**
	 * ͨ��wbuid��user,ͨ���ӿڻ��
	 * @param wbuid
	 * @return
	 */
			
	public HyWbUser findUserByid(String token,long wbuid);
}
