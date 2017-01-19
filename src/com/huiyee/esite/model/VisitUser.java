
package com.huiyee.esite.model;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import com.huiyee.interact.bbs.model.BBSUser;

public class VisitUser implements Serializable
{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private long cid;// ��ǰ���ʵĹ�΢��id
	private String oname;// owner �Ļ�Ա��
	private long wbuid;// ΢��uid
	private long wxuid;// ΢��openid
	private String source;// ������Դ
	private long pageid;// ���õ�pageidÿ�μ��ض�������set
	private String oldSource;// �ϴη��ʵ�source
	private long oldPageid;// �ϴη��ʵ�pageid
	private WxUser wxUser;
	private BBSUser bbsUser;
	private int cd = -1;// ����ʶ��ǰ������;-1-Ĭ�ϻ���cookie,0-΢��,1-΢��,2-�û�������
	private int uif;// ����ʶ���ǲ�����Ҫ��ȡ΢���û���Ϣ;0-������Ȩ,1-������Ȩ
	private HyUser hyUser;
	private CookieUser cookieUser;
	private String skey = "";// ct-��ƷĿ¼,cp-ͼƬĿ¼��cv-��ƵĿ¼��cn-����Ŀ¼��n-��������.(����ct-hy-123,��������)
	private String svalue = "0";// skey��ֵ
	private String kv = "";

	public String getKv()
	{
		return kv;
	}

	public void setKv(String kv)
	{
		if(kv==null)
		{
			this.kv ="kv";
			this.skey=kv;
			this.svalue=null;
			return;
		}
		if (kv.contains("-hy-"))
		{
			String[] strs = kv.split("-hy-");
			this.skey = strs[0];
			this.svalue = strs[strs.length-1];
		} else if (kv.startsWith("n-"))
		{
			String[] strs = kv.split("-");
			this.skey = strs[0];
			this.svalue = strs[strs.length-1];
		}
		this.kv = kv;
	}

	public String getOname()
	{
		return oname;
	}

	public void setOname(String oname)
	{
		this.oname = oname;
	}

	public String getSkey()
	{
		return skey;
	}

	public void setSkey(String skey)
	{
		this.skey = skey;
	}

	public String getSvalue()
	{
		return svalue;
	}

	public void setSvalue(String svalue)
	{
		this.svalue = svalue;
	}

	public long getHyUserId()
	{
		if (hyUser != null)
		{
			return hyUser.getId();
		}
		return 0;
	}

	public long getUid()
	{
		if (cd == -1)
		{
			if (cookieUser != null)
			{
				return cookieUser.getId();
			}
		} else if (cd == 0)
		{
			return wbuid;
		} else if (cd == 1)
		{
			return wxuid;
		} else if (cd == 2)
		{
			if (hyUser != null)
			{
				return hyUser.getId();
			}
		}
		return 0;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public long getWxuid()
	{
		return wxuid;
	}

	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	public String getSource()
	{
		if(StringUtils.isBlank(source)){
			return "pcn";
		}
		return source;
	}

	public void setSource(String source, long pageid)
	{
		// ֻ��source����������֮��old���û���pageidֻҪ����ͻỻ������pageid����Ҫ�û�old��
		if (StringUtils.isNotBlank(source))
		{
			if (this.source != null && !this.source.equals(source))
			{
				this.oldSource = this.source;
				if (pageid > 0)
				{
					this.oldPageid = this.pageid;
				}
			} 
			if (this.source == null || source.contains("-p-") || source.startsWith("t-"))
			{
				this.source = source;
			}
		}
		if (pageid > 0)
		{
			this.pageid = pageid;
		}
	}

	public long getCid()
	{
		return cid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	public long getPageid()
	{
		return pageid;
	}

	public WxUser getWxUser()
	{
		return wxUser;
	}

	public void setWxUser(WxUser wxUser)
	{
		this.wxUser = wxUser;
	}

	public int getCd()
	{
		return cd;
	}

	public void setCd(int cd)
	{
		this.cd = cd;
	}

	public int getUif()
	{
		return uif;
	}

	public void setUif(int uif)
	{
		this.uif = uif;
	}

	public BBSUser getBbsUser()
	{
		return bbsUser;
	}

	public void setBbsUser(BBSUser bbsUser)
	{
		this.bbsUser = bbsUser;
	}

	public HyUser getHyUser()
	{
		return hyUser;
	}

	public void setHyUser(HyUser hyUser)
	{
		this.hyUser = hyUser;
	}

	public CookieUser getCookieUser()
	{
		return cookieUser;
	}

	public void setCookieUser(CookieUser cookieUser)
	{
		this.cookieUser = cookieUser;
	}

	public String getOldSource()
	{
		return oldSource;
	}

	public void setOldSource(String oldSource)
	{
		this.oldSource = oldSource;
	}

	public long getOldPageid()
	{
		return oldPageid;
	}

	public void setOldPageid(long oldPageid)
	{
		this.oldPageid = oldPageid;
	}

}
