
package project.caidan.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import project.caidan.dto.DailiCpz;
import project.caidan.dto.ProductRMB;
import project.caidan.mgr.IKqMgr;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.weixin.dto.WkqRs;
import com.huiyee.weixin.mgr.IPayShopOwnerMgr;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;
import com.huiyee.weixin.model.Wkq;
import com.opensymphony.xwork2.ActionContext;

public class CaiDanKqAction extends AbstractAction
{

	private static final long serialVersionUID = 1L;
	private IPayShopOwnerMgr payShopOwnerMgr;
	private IWxBuyProductMgr wxBuyProductMgr;
	private IKqMgr kqMgr;
	
	
	public void setKqMgr(IKqMgr kqMgr)
	{
		this.kqMgr = kqMgr;
	}

	public void setWxBuyProductMgr(IWxBuyProductMgr wxBuyProductMgr)
	{
		this.wxBuyProductMgr = wxBuyProductMgr;
	}

	public void setPayShopOwnerMgr(IPayShopOwnerMgr payShopOwnerMgr)
	{
		this.payShopOwnerMgr = payShopOwnerMgr;
	}

	public String asub() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		WkqRs rs = new WkqRs();
		if (vu != null && vu.getKv() != null)
		{
			String[] strs = vu.getKv().split("-");
			if (strs.length == 2)
			{
				OrderGoods po=wxBuyProductMgr.findPayOrderGoodsById(Long.valueOf(strs[1]));
				if(po==null){
					rs.setStatus(-20);
					rs.setHydesc("���������ڣ�");
					Gson gson = new Gson();
					out.print(gson.toJson(rs));
					out.flush();
					out.close();
					return null;
				}
				Wkq wk = payShopOwnerMgr.findWkq(this.getOwner(),po.getType());
				if(wk==null){
					rs.setStatus(-10);
					rs.setHydesc("û������Ӧ��վ�㣡");
					Gson gson = new Gson();
					out.print(gson.toJson(rs));
					out.flush();
					out.close();
					return null;
				}
				rs.setUrl(HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + wk.getFpage() + "/" + vu.getSource() + ".html");
				DailiCpz dc=kqMgr.findChannelByWxuid(vu.getWxUser().getId());
				if(dc==null)
				{
					rs.setStatus(-4);
					rs.setHydesc("���ǵ�����ݣ�");
					Gson gson = new Gson();
					out.print(gson.toJson(rs));
					out.flush();
					out.close();
					return null;
				}
				int ss=kqMgr.findProductChannel(po.getPid(), dc.getClid());
				if(ss==0)
				{
						rs.setStatus(-6);
						rs.setHydesc("����������ƥ�䣡");
						Gson gson = new Gson();
						out.print(gson.toJson(rs));
						out.flush();
						out.close();
						return null;
				}
				 ProductRMB pr=kqMgr.findPRMB(po.getPid());
				 if(pr.getEndtime().getTime()<System.currentTimeMillis())
				 {
					    rs.setStatus(-7);
						rs.setHydesc("��ȯ�Ѿ����ڣ�");
						Gson gson = new Gson();
						out.print(gson.toJson(rs));
						out.flush();
						out.close();
						return null; 
				 }
				
				String st = wxBuyProductMgr.findWkqOrder(strs[0], Long.valueOf(strs[1]));
					if (st == null)
					{
						rs.setStatus(-2);
						rs.setHydesc("��ȯ�����ڣ�");
					} else if (st.equals("Y"))
					{
						rs.setStatus(-3);
						rs.setHydesc("��ȯ�Ѿ���֤����");
					} else
					{
						//wxBuyProductMgr.updateWkqYzStatus(vu.getWxuid(),strs[0], Long.valueOf(strs[1]),po.getOrderid(), "Y",this.getOnameUrl(),po.getType(),this.getOwner());
						//��������  ����һ�����ִ�зŵ�kqmgr��
						kqMgr.updateRMB(vu, dc, po.getOrderid(), Long.valueOf(strs[1]), po.getProductname(),pr,strs[0],this.getOnameUrl(),po.getType(),this.getOwner());
						rs.setStatus(1);
						rs.setHydesc("��֤�ɹ���");
						rs.setUrl(HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" + wk.getSpage() + "/" + vu.getSource() + ".html");
					}
			} else
			{
				rs.setStatus(-5);
				rs.setHydesc("�벻Ҫ�۸Ķ�ά���ַ��");
			}
		} else
		{
			rs.setStatus(-1);
			rs.setHydesc("�û������ڣ�");
		}
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}

}
