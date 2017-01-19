package com.huiyee.weixin.model;

public class WxPayOrder
{

	private String appid;// ΢�ŷ���Ĺ����˺�ID Y
	private String mch_id;// ΢��֧��������̻��� Y
	private String device_info;// �ն��豸��(�ŵ�Ż������豸ID)��ע�⣺PC��ҳ���ں���֧���봫"WEB" N
	private String nonce_str;// ����ַ�����������32λ�� Y
	private String sign;// ǩ�� Y
	private String body;// ��Ʒ��֧������Ҫ���� Y
	private String detail;// ��Ʒ������ϸ�б�N
	private String attach;// �������ݣ��ڲ�ѯAPI��֧��֪ͨ��ԭ�����أ����ֶ���Ҫ�����̻�Я���������Զ�������N
	private String out_trade_no;// �̻�ϵͳ�ڲ��Ķ�����,32���ַ��ڡ��ɰ�����ĸY
	private String fee_type;// ����ISO 4217��׼����λ��ĸ���룬Ĭ������ң�CNY��N
	private Integer total_fee;// �����ܽ�ֻ��Ϊ��������Y
	private String spbill_create_ip;// APP����ҳ֧���ύ�û���ip��Native֧�������΢��֧��API�Ļ���IP��Y
	private String time_start;// ��������ʱ�䣬��ʽΪyyyyMMddHHmmss---N
	private String time_expire;// ����ʧЧʱ�䣬��ʽΪyyyyMMddHHmmssע�⣺���ʧЧʱ�����������5����--N
	private String goods_tag;// ��Ʒ��ǣ�����ȯ�������Żݹ��ܵĲ���N
	private String notify_url;// ����΢��֧���첽֪ͨ�ص���ַY
	private String trade_type;// ȡֵ���£�JSAPI��NATIVE��APP��WAP---Y
	private String product_id;// rade_type=NATIVE���˲����ش�����idΪ��ά���а�������ƷID---N
	private String openid;// rade_type=JSAPI���˲����ش����û����̻�appid�µ�Ψһ��ʶ---N

	public String getAppid()
	{
		return appid;
	}

	public void setAppid(String appid)
	{
		this.appid = appid;
	}

	public String getMch_id()
	{
		return mch_id;
	}

	public void setMch_id(String mch_id)
	{
		this.mch_id = mch_id;
	}

	public String getDevice_info()
	{
		return device_info;
	}

	public void setDevice_info(String device_info)
	{
		this.device_info = device_info;
	}

	public String getNonce_str()
	{
		return nonce_str;
	}

	public void setNonce_str(String nonce_str)
	{
		this.nonce_str = nonce_str;
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public String getAttach()
	{
		return attach;
	}

	public void setAttach(String attach)
	{
		this.attach = attach;
	}

	public String getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type()
	{
		return fee_type;
	}

	public void setFee_type(String fee_type)
	{
		this.fee_type = fee_type;
	}

	public Integer getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee)
	{
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip()
	{
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip)
	{
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start()
	{
		return time_start;
	}

	public void setTime_start(String time_start)
	{
		this.time_start = time_start;
	}

	public String getTime_expire()
	{
		return time_expire;
	}

	public void setTime_expire(String time_expire)
	{
		this.time_expire = time_expire;
	}

	public String getGoods_tag()
	{
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag)
	{
		this.goods_tag = goods_tag;
	}

	public String getNotify_url()
	{
		return notify_url;
	}

	public void setNotify_url(String notify_url)
	{
		this.notify_url = notify_url;
	}

	public String getTrade_type()
	{
		return trade_type;
	}

	public void setTrade_type(String trade_type)
	{
		this.trade_type = trade_type;
	}

	public String getProduct_id()
	{
		return product_id;
	}

	public void setProduct_id(String product_id)
	{
		this.product_id = product_id;
	}

	public String getOpenid()
	{
		return openid;
	}

	public void setOpenid(String openid)
	{
		this.openid = openid;
	}

}