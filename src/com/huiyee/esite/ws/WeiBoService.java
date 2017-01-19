package com.huiyee.esite.ws;

import java.util.List;

import javax.jws.WebService;

import com.huiyee.tfmodel.HyWbComment;
import com.huiyee.tfmodel.HyWbLogin;
import com.huiyee.tfmodel.HyWbSrc;
import com.huiyee.tfmodel.HyWbUser;
import com.huiyee.tfmodel.HyWbUserInfo;
import com.huiyee.tfmodel.param.TfKeyWordDto;
import com.huiyee.tfmodel.param.TfPageJoinDto;
import com.huiyee.tfmodel.param.TfVpsCommentDto;
import com.huiyee.tfmodel.param.TfWeiBoMonitorDto;
import com.huiyee.tfmodel.result.TfPageForKeyWord;
import com.huiyee.tfmodel.result.TfPageForWbid;

@WebService
public interface WeiBoService
{
	/**
	 * ��ӹؼ�����������,���������û���ӹؼ���ʱ���
	 * @param keywords
	 * @return 0:ʧ��;1:�ɹ�
	 */
	public int addKeyWordsJob(String keywords);
	
	/**
	 * �����û��Ļ�Ծ��˿
	 * @param token
	 * @param wbuid
	 * @param count ���صĸ���
	 * @return
	 */
	public List<String> findWbuidActiveFans(String token,long wbuid,int count);
	

	/**
	 * at����ʱ��Ҫ�õ���,ͨ������ģ������
	 * @param token
	 * @param key
	 * @param count
	 * @return
	 */
	public List<String> findFansBySchName(String token,String key,int count);
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
	 * �ظ����˵�����
	 * @param cid
	 * @param token
	 * @param content
	 * @return
	 */
	public HyWbComment replycomment(String cid,String wbid, String token, String content);


	/**
	 * ͨ��ˮ�����͵����� ���ܳɹ��������ͳɹ� ��֤�ɹ���Ҫ�ٴε��������Ƿ��ͳɹ��ӿ�
	 * 
	 * @param cms
	 *            list,����Ԫ��������wbuid,wbid,content,key(���ɵ�Ψһkey,�����Ժ��ѯ�������)
	 * @return 0:����ʧ��;1:���ܳɹ�
	 */
	public int vpscomments(List<TfVpsCommentDto> cms);

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
	 * �鿴����״̬
	 * 
	 * @param type
	 *            VC-vpscommet;CM-comment;WB-weibo;RP-repost
	 * @param typekey
	 * @return 0:δ����;1:�ɹ�;2:ʧ��
	 */
	public int findStatus(String type, String typekey);
	
	/**
	 * ͨ��wbuid��user,ͨ���ӿڻ��
	 * @param wbuid
	 * @return
	 */
			
	public HyWbUser findUserByid(String token,long wbuid);
	
	/**
	 * �ӹ�ע
	 * @param token ��½��
	 * @param wbuid ����ע����,һ��Ϊ��΢
	 * @return
	 */
	public HyWbUser guanzhu(String token,long wbuid);
	
	/**
	 * ͨ��wbuid��user,ͨ��hbase���
	 * @param wbuid
	 * @return
	 */
			
	public HyWbUserInfo findHUserByid(long wbuid);
	
	/**
	 * ͨ��wbuid��user,ͨ��hbase���
	 * �Լ�֪����token
	 * @param wbuid
	 * @return
	 */
			
	public HyWbUserInfo findHTUserByid(String token,long wbuid);
	
	/**
	 * ͨ�� �ǳ���user
	 * @param token
	 * @param nickname
	 * @return
	 */
    public HyWbUser findUserByNickname(String token,String nickname);
    
	
	/**
	 * ͨ��΢�������ҵ�΢��,ͬʱ������
	 * 
	 * @param token
	 *            ΢��token
	 * @param url
	 *            ΢������
	 * @return ����΢��
	 */
	public HyWbSrc findWeiBoByWbUrl(String token, String url);
	
	/**
	 * ͨ��΢��id�ҵ�΢��,ͬʱ������
	 * 
	 * @param token
	 *            ΢��token
	 * @param url
	 *            ΢������
	 * @return ����΢��
	 */
	public HyWbSrc findWeiBoByMWbid(String token, String url);
	
	
	/**
	 * ͨ��΢��id�ҵ�΢��
	 * 
	 * @param token
	 *            ΢��token
	 * @param url
	 *            ΢������
	 * @return ����΢��
	 */
	public HyWbSrc findWeiBoByWbid(String token, String wbid,boolean fast);
	
	
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
	 * 
	 * @param keyWordDto
	 * @return Tfpage����objectΪlist<hywbsrc>
	 */
	public TfPageForKeyWord findWeibosByKeyword(TfKeyWordDto keyWordDto);
	
   /**
    * 
    * @param weiBoMonitorDto
    * @return Tfpage����objectΪlist<hywbcomment>����list<hywbsrc>
    */
	public TfPageForWbid findWeibosByWbid(TfWeiBoMonitorDto weiBoMonitorDto);
	
	/**
	 * ��վ����������־
	 * 
	 * @param joinDto
	 * @return 0:����ʧ��,1:����ɹ�
	 */
	public int addUserJoinLog(List<TfPageJoinDto> joinDto);

}
