package com.huiyee.esite.dto;

public class HdRsDto {
	/**
	 * $$$$����״̬$$$$
	 * -10000��������id�����ڣ�
	 * -10001���ύ����Ƶ����
	 * -10002��������ʱ��δ��ʼ��ҳ����ʾֱ����hydesc��ʾ��
	 * -10003�������Ѿ�������ҳ����ʾֱ����hydesc��ʾ
	 * -10004�������û������ڣ�
	 * -10005�������ܴ����ﵽ���ޣ�
	 * -10006������ÿ��Ĵ����ﵽ���ޣ�
	 * -11000������ʧ��,�������⣻
	 *  10000���м�״̬��ҳ�治��Ҫ����
	 *  1�������ɹ���
	 * $$$$���ױ��ύ$$$$
	 * -1����֤�벻��ȷ
	 * $$$$���׾���$$$$
	 * -1�����ֲ�����
	 * -2:���ļ۸�ͣ�
	 * -3:����ʱ�������
	 * $$$$���׳齱$$$$
	 * -4��û��΢�ֳ�ǩ����
	 * -5������΢�Ź�ע�ߣ�
	 * -7�����ֲ����齱��
	 * -8��ָ���û����������ڣ�
	 * -9���û��н��ﵽ���ޣ�
	 *  0��δ�н���
	 *  1�����˻��֣�
	 *  2�������Ż݄���
	 *  3������ʵ�
	 *  4��΢�ź����
	 *  5������һ��룻
	 *  6: ������Ʒ��
	 *  7:����ʶ�Ľ�Ʒ����;
	 *  8:��ȡ�ɹ��û���ʣ��齱����
	 *  $$$$����ͶƱ$$$$
	 *  -1:û��ѡ��ѡ�
	 *  -2:ÿ��ÿ�����һ��ѡ��ֻ��һ�λ��᣻
	 */
	private int status;
	private String hydesc;
	/**
	 * һ��Ϊ�����ɹ�ʧ�ܺ���Ҫ��ת��pageid��
	 */
	private long id;
	/**
	 * �ɹ�����ʧ�ܣ�������ȼ��ߣ�
	 */
	private String url;
	/**
	 * ��ת��url��ַ.
	 */
	private String furl;
	
	private int tnum;//����ʣ�����
	private int num;//�ܹ�ʣ�����
	
	private int shownum;//����ǰ̨��ʾ��ʣ�����
	
	public int getShownum()
	{
		return shownum;
	}

	
	public void setShownum(int shownum)
	{
		this.shownum = shownum;
	}

	public int getTnum()
	{
		return tnum;
	}
	
	public int getNum()
	{
		return num;
	}
	
	public void setTnum(int tnum)
	{
		this.tnum = tnum;
	}
	
	public void setNum(int num)
	{
		this.num = num;
	}

	public String getFurl()
	{
		return furl;
	}
	
	public void setFurl(String furl)
	{
		this.furl = furl;
	}

	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHydesc() {
		return hydesc;
	}

	public void setHydesc(String hydesc) {
		this.hydesc = hydesc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
