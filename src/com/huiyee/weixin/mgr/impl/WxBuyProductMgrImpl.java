
package com.huiyee.weixin.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.huiyee.esite.dao.IContentProductDao;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.PayApt;
import com.huiyee.esite.model.PayRecord;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.Arith;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.weixin.dao.IPayShopOwnerDao;
import com.huiyee.weixin.dao.IWxBuyProductDao;
import com.huiyee.weixin.dto.OrderInfoDto;
import com.huiyee.weixin.dto.WkqCmp;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;
import com.huiyee.weixin.model.PayAddress;
import com.huiyee.weixin.model.PayOrder;
import com.huiyee.weixin.model.PayOrderTemplate;
import com.huiyee.weixin.model.ProductLevel;
import com.huiyee.weixin.model.ShoppingCartRecord;
import com.huiyee.weixin.model.Wkq;
import com.huiyee.weixin.model.template.DDZFCG;
import com.huiyee.weixin.model.template.GRXXTZ;
import com.huiyee.weixin.model.template.JFDHLPTZ;

public class WxBuyProductMgrImpl extends AbstractMgr implements IWxBuyProductMgr
{
	private IWxBuyProductDao wxBuyProductDao;
	private IContentProductDao contentProductDao;
	private IPayShopOwnerDao payShopOwnerDao;
	
	public void setPayShopOwnerDao(IPayShopOwnerDao payShopOwnerDao)
	{
		this.payShopOwnerDao = payShopOwnerDao;
	}

	public void setContentProductDao(IContentProductDao contentProductDao)
	{
		this.contentProductDao = contentProductDao;
	}

	public void setWxBuyProductDao(IWxBuyProductDao wxBuyProductDao)
	{
		this.wxBuyProductDao = wxBuyProductDao;
	}

	@Override
	public OrderInfoDto findProductAndAddressInfo(long[] id,long aid, long hyuid,long ownerid,long levelid)
	{
		OrderInfoDto dto = new OrderInfoDto();
		List<ShoppingCartRecord> list = new ArrayList<ShoppingCartRecord>();
		int needAddress = 0;//�����ж��Ƿ���Ҫ��д��ַ
		for (int i = 0; i < id.length; i++){//�Ҳ�Ʒ
			ShoppingCartRecord cr = wxBuyProductDao.findShoppingProductById(id[i],hyuid);
			if(cr == null)
				return null;
			if(cr.getPaid() >0){
				PayApt payApt = wxBuyProductDao.findPayAptById(cr.getPaid());
				cr.setPayApt(payApt);
			}
			if("K".equals(cr.getProduct().getSubtype())){
				needAddress = 1;
			}
			if(levelid > 0){
				ProductLevel pl = wxBuyProductDao.findProductLevel(cr.getProductid(), levelid);//���û�Ա��
				if(pl != null){
					if("J".equals(cr.getProduct().getType())){
						cr.getProduct().setSalesprice(pl.getPrice());
					}else{
						cr.getProduct().setSalesprice(Arith.div(pl.getPrice(), 100, 2));
					}
				}
				
			}
			
			list.add(cr);
		}
		dto.setNeedAddress(needAddress);
		if(list.size()==0)
			//����Ϊ0  ��Ϊ�ǹ���
			return null;
		PayAddress address = null;
		if(aid >0 ){
			address = wxBuyProductDao.findPayAddressById(aid);//ʹ�õ�ַ
		}else{
			address = wxBuyProductDao.findDefaultPayAddress(hyuid);//����Ĭ�ϵ�ַ
		}
		if(address == null){
			//�������е�ַ
			dto.setPayAddressList(wxBuyProductDao.findPayAddressListByHyUid(hyuid));
		}else{
			dto.setAddress(address);
		}
		dto.setShopCartList(list);
		dto.setWkq(payShopOwnerDao.findWkq(ownerid,list.get(0).getProduct().getType().equals("J")?0:1));
		dto.setTotalJf(this.findJFen(hyuid));//���ݿ���Ҹ��˻���
		return dto;
	}
	
	@Override
	public void updateConfirmFreeOrder(long productid, String ip,VisitUser vu,String desc,long owner,String url)
	{
		ContentProduct product = contentProductDao.findProductById(productid);
		if (product == null)
		{
			return ;
		}
		if (product.getTotal() <= product.getUsed())
		{
			return ;
		}
		int type = -1;
		String status = "CMP";
		String uuid = null;
		if (product.getType().equals("W"))
		{
			type = 1;
			List<WxTemplate> wts = this.findWxTemplate(owner,"WDS",0);	
			if (wts != null&&wts.size()>0&&vu!=null&&vu.getWxUser()!=null)
			{
				for(WxTemplate wt:wts)
				{
				DDZFCG dd = (DDZFCG) JsonStringUtil.String2Obj(wt.getData(), DDZFCG.class);
				dd.setOrderMoneySum("0Ԫ");
				dd.setOrderProductName(product.getName());
				WxTemplateJob wj = new WxTemplateJob();
				wj.setMpid(wt.getMpid());
				wj.setType("WDS");
				wj.setEntityid(wt.getEntityid());
				wj.setRemark(wt.getRemark());
				wj.setTemplate_id(wt.getTemplate_id());
				wj.setTouser(vu.getWxUser().getOpenid());
				wj.setData(dd.toData());
				wj.setUrl(url+ "/user/showOrderList.action");
				this.addTmplMsg(wj);
				}
			}
		} else if (product.getType().equals("J"))
		{
			type = 0;
			List<WxTemplate> wts = this.findWxTemplate(owner,"JFS",0);	
			if (wts != null&&wts.size()>0&&vu!=null&&vu.getWxUser()!=null)
			{
				for(WxTemplate wt:wts)
				{
				JFDHLPTZ dd = (JFDHLPTZ) JsonStringUtil.String2Obj(wt.getData(), JFDHLPTZ.class);
				dd.setKeyword1(product.getName());
				dd.setKeyword2("0����");
				dd.setKeyword3(this.findJFen(vu.getHyUserId())+"����");
				dd.setKeyword4(DateUtil.getDateTimeString(new Date()));
				WxTemplateJob wj = new WxTemplateJob();
				wj.setMpid(wt.getMpid());
				wj.setType("JFS");
				wj.setEntityid(wt.getEntityid());
				wj.setRemark(wt.getRemark());
				wj.setTemplate_id(wt.getTemplate_id());
				wj.setTouser(vu.getWxUser().getOpenid());
				wj.setData(dd.toData());
				wj.setUrl(url+ "/user/showJfOrderList.action");
				this.addTmplMsg(wj);
				}
			}
		}
		
		long mainOrderid = wxBuyProductDao.savePayOrder(vu.getHyUserId(),vu.getWxUser().getId(),owner,type,ip, status);
//		int price = type==0?product.getSalesjifen():(int)(product.getSalesprice()*100);//��Ʒ����  ����*1��ʵ��RMB*100
		int price = 0;
		if(product.getSubtype().equals("K")){
			//����п�ȯ ���ֶ���
			long childOrderid = wxBuyProductDao.saveChildOrder(vu.getHyUserId(), vu.getWxUser().getId(), owner, type, 0, ip,mainOrderid,status);//�Ӷ���
			wxBuyProductDao.savePayOrderGoods(childOrderid, product.getId(),product.getName(),product.getSubtype(),product.getSimgurl(), price, type, getRandomString(10), 1, 0,0);
		}else{
			//ʵ�� ����������
			wxBuyProductDao.savePayOrderGoods(mainOrderid, product.getId(),product.getName(),product.getSubtype(),product.getSimgurl(), price, type, null, 1, 0,0);
		}
		PayRecord payRecord = new PayRecord(); 
		payRecord.setIp(ip);
		payRecord.setMediaorder(desc);
		payRecord.setHyuid(vu.getHyUserId());
		payRecord.setOrderid(mainOrderid);
		wxBuyProductDao.savePayRecord(payRecord);
		contentProductDao.updateUsed(productid,1);

	}

	@Override
	public PayOrder payOrderInfo(long payOrderid)
	{
		PayOrder payOrder = wxBuyProductDao.findPayOrderById(payOrderid);
		if(payOrder != null && "DFK".equals(payOrder.getStatus())){
			List<OrderGoods> productList;
			String subject = "";
			if(payOrder.getGoodscount() > 0){
				 productList = wxBuyProductDao.findPayOrderGoods(payOrderid);
			}else{
				//����ȯ���� ���Ӷ����µ���Ʒ
				long id = wxBuyProductDao.findChildOrder(payOrderid);
				productList = wxBuyProductDao.findPayOrderGoods(id);
			}
			if(productList != null && productList.size()>0){
				if(productList.size() > 1){
					subject = productList.get(0).getProductname() + "��";
				}else{
					subject = productList.get(0).getProductname();
				}
			}
			payOrder.setSubject(subject);
			payOrder.setGoods(productList);
		}
		return payOrder;
	}

	public static String getRandomString(int length)
	{ // length��ʾ�����ַ����ĳ���
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++)
		{
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	@Override
	public int updatepayOrderStatus(long out_trade_no,String prestatus, String status,long owner,String url,int fee)
	{
		if (owner > 0)
		{
			List<WxTemplate> wts = this.findWxTemplate(owner,"WDS",0);
			if (wts != null&&wts.size()>0)
			{
				PayOrder payOrder = wxBuyProductDao.findPayOrderById(out_trade_no);
				 PayOrderTemplate p=null;
				if(payOrder.getGoodscount() > 0){
					 p = wxBuyProductDao.findPayOrderNameById(out_trade_no);
				}else{
					p = wxBuyProductDao.findPayOrderNameByFid(out_trade_no);
				}
				
				for(WxTemplate wt:wts)
				{
				DDZFCG dd = (DDZFCG) JsonStringUtil.String2Obj(wt.getData(), DDZFCG.class);
				double fe=fee;
				dd.setOrderMoneySum(fe / 100 + "Ԫ");
				dd.setOrderProductName(p.getName());
				WxTemplateJob wj = new WxTemplateJob();
				wj.setMpid(wt.getMpid());
				wj.setType("WDS");
				wj.setEntityid(wt.getEntityid());
				wj.setRemark(wt.getRemark());
				wj.setTemplate_id(wt.getTemplate_id());
				wj.setTouser(p.getOpenid());
				wj.setData(dd.toData());
				wj.setUrl(url);
				this.addTmplMsg(wj);
				}
			}
		}
		return wxBuyProductDao.updatepayOrderStatus(out_trade_no,prestatus, status);
	}
	@Override
	public int updatepayOrderStatus(long out_trade_no,String prestatus,String status,PayRecord payRecord){
		wxBuyProductDao.savePayRecord(payRecord);//����֧����¼
		return wxBuyProductDao.updatepayOrderStatus(out_trade_no,prestatus, status);
	}

	@Override
	public long savePayRecord(String mediaorder, String ip)
	{
		return wxBuyProductDao.savePayRecord(mediaorder, ip);
	}

	@Override
	public int savePayAddress(long hyuid, PayAddress address)
	{	
		if(address==null){
			return 0;
		}else{
			List<PayAddress> list=wxBuyProductDao.findPayAddressListByHyUid(hyuid);
			if(list.size()==0){
				address.setIsdefault("Y");
			}
		}
		if("Y".equalsIgnoreCase(address.getIsdefault())){
			wxBuyProductDao.removeOtherAddressDefault(hyuid);
		}
		return wxBuyProductDao.savePayAddress(hyuid, address);
	}

	@Override
	public PayAddress findPayAddressById(long aid)
	{
		return wxBuyProductDao.findPayAddressById(aid);
	}

	@Override
	public int updatePayAddress(long aid, long hyuid, PayAddress address)
	{
		if(address!=null&&"Y".equalsIgnoreCase(address.getIsdefault())){
			wxBuyProductDao.removeOtherAddressDefault(hyuid);
		}
		return wxBuyProductDao.updatePayAddress(aid, address);
	}

	@Override
	public int delPayAddress(long aid)
	{
		return wxBuyProductDao.delPayAddress(aid);
	}

	@Override
	public int updateWkqYzStatus(long wxuid,String uuid, long pogid,long poid,String status,String onameurl,int ptype,long owner)
	{
		String type="WDC";
		if(ptype==0)
		{
			type="JFC";
		}
		List<WxTemplate> wts = this.findWxTemplate(owner,type,0);
		if (wts != null&&wts.size()>0)
		{
			WkqCmp wc=wxBuyProductDao.findWkqCmp(poid);
			String date=DateUtil.getDateTimeString(wc.getTime());
			String nickname=wc.getNickname();
			if(nickname==null){
				nickname="";
			}
			for(WxTemplate wt:wts)
			{
			GRXXTZ dd = (GRXXTZ) JsonStringUtil.String2Obj(wt.getData(), GRXXTZ.class);
			dd.setFirst(dd.getFirst().replace("${nickname}", nickname).replace("${time}", date));
			dd.setKeyword1(dd.getKeyword1().replace("${nickname}", nickname).replace("${time}", date));
			dd.setKeyword2(date);
			dd.setKeyword3(dd.getKeyword3().replace("${nickname}", nickname).replace("${time}", date));
			dd.setRemark(dd.getRemark().replace("${nickname}",nickname).replace("${time}", date));
			WxTemplateJob wj = new WxTemplateJob();
			wj.setMpid(wt.getMpid());
			wj.setType(wt.getType());
			wj.setEntityid(wt.getEntityid());
			wj.setRemark(wt.getRemark());
			wj.setTemplate_id(wt.getTemplate_id());
			wj.setTouser(wc.getOpenid());
			wj.setData(dd.toData());
			wj.setUrl(wt.getUrl());
			this.addTmplMsg(wj);
			}
		}
		wxBuyProductDao.updateWkqRecord(wxuid, pogid);
		return wxBuyProductDao.updateWkqYzStatus(uuid, pogid,status);
	}

	@Override
	public String findWkqOrder(String uuid, long id)
	{
		return wxBuyProductDao.findWkqOrder(uuid, id);
	}

	@Override
	public PayOrder findPayOrderById(long payOrderid)
	{
		return wxBuyProductDao.findPayOrderById(payOrderid);
	}

	@Override
	public ShoppingCartRecord addShoppingCart(long productid, long hyuid,long paid,String status,long owner)
	{
		ContentProduct product = contentProductDao.findEasyProductById(productid,owner);
		ShoppingCartRecord p = null;
		if(product != null){
			int remain = product.getTotal() - product.getUsed();
			if(remain < 1 ){
				ShoppingCartRecord r = new ShoppingCartRecord();
				r.setId(-1);
				return r;
			}
			p = wxBuyProductDao.findShoppingCartProduct(productid,hyuid,paid,status);
			long result = 0;
			if(p != null){
				int num = p.getNum();
				if("RTN".equals(status)){
					num = 0;
				}
				if(!findPersonLimitNum(product, hyuid,num)){
					ShoppingCartRecord r = new ShoppingCartRecord();
					r.setId(-2);
					return r;
				}
				if("RTN".equals(status)){//��������
					result = p.getId();
				}else{
					result = wxBuyProductDao.updatePayShoppingCartNum(p.getId(),hyuid,paid);
					if(result > 0){
						result = p.getId();//��Ҫ���ع��ﳵ��id
					}
				}
			}else{
				if(!findPersonLimitNum(product, hyuid,0)){
					ShoppingCartRecord r = new ShoppingCartRecord();
					r.setId(-2);
					return r;
				}
				p = new ShoppingCartRecord();
				result = wxBuyProductDao.savePayShoppingCart(productid,hyuid,paid,status);
			}
			p.setId(result);
			p.setType(product.getType());
			return p;
		}else{
			return null;
		}
	}
	
	@Override
	public int delShopCartProduct(long shopCartid,long hyuid){
		return wxBuyProductDao.updateShoppingCartStatus(shopCartid,hyuid);
	}

	@Override
	public OrderInfoDto findShoppingCartProductByHyuid(long hyuid,long aid,long owner,String type,long levelid)
	{
		OrderInfoDto dto = new OrderInfoDto();
		List<ShoppingCartRecord> shopCartList = wxBuyProductDao.findShoppingCartProductByHyuid(hyuid,owner,type);
		if(shopCartList != null && shopCartList.size()>0){
			for(int i=0;i<shopCartList.size();i++){
				if(levelid > 0){
					ProductLevel pl = wxBuyProductDao.findProductLevel(shopCartList.get(i).getProductid(), levelid);//���û�Ա��
					if(pl != null){
						if("J".equals(type)){
							shopCartList.get(i).getProduct().setSalesprice(pl.getPrice());
						}else{
							shopCartList.get(i).getProduct().setSalesprice(Arith.div(pl.getPrice(), 100, 2));
						}
					}
				}
				if(shopCartList.get(i).getPaid() > 0){
					PayApt payApt = wxBuyProductDao.findPayAptById(shopCartList.get(i).getPaid());
					shopCartList.get(i).setPayApt(payApt);
				}
			}
			dto.setShopCartList(shopCartList);
		}
		return dto;
	}

	@Override
	public int updateQuantity(long shopCartid, long hyuid, int num,long productid)
	{
		ContentProduct product = contentProductDao.findProductById(productid);
		if(!findPersonLimitNum(product, hyuid,num-1)){
			return -1;
		}
		return wxBuyProductDao.updateShoppingCartProductNum(shopCartid,hyuid,num);
	}

	@Override
	public int updatePayRecord(long payrecordid, String mediaorder, int status,int price)
	{
		return wxBuyProductDao.updatePayRecord(payrecordid,mediaorder,status,price);
	}

	@Override
	public OrderInfoDto findOrderList(long hyuid, String status,int pageId,int type)
	{
		OrderInfoDto dto = new OrderInfoDto();
		int total = wxBuyProductDao.findTotalOrderList(hyuid,status,type);
		int dfkCount = wxBuyProductDao.findTotalOrderList(hyuid,"DFK",type);//�����������
		int kqCount = wxBuyProductDao.finfTotalUnusedKQOrderList(hyuid,"CMP",type);//�Ѹ����δʹ�õĿ�ȯ����
		dto.setDfkCount(dfkCount);dto.setKqCount(kqCount);
		int start = (pageId - 1) * 10;
		if(total > 0){
//			��ѯ������������������ȯ��
			List<PayOrder> payOrderList = wxBuyProductDao.findOrderList(hyuid,status,type,start,10);
		
			if(payOrderList != null && payOrderList.size()>0){
				for(int i=0;i<payOrderList.size();i++){
					List<OrderGoods> list = new ArrayList<OrderGoods>();
					int goodscount = payOrderList.get(i).getGoodscount();//������ ��Ʒ����
					PayOrder mainOrder = payOrderList.get(i);
					if(mainOrder.getGoodscount()>0){
						List<OrderGoods> productList = wxBuyProductDao.findPayOrderGoods(mainOrder.getId());//��������Ʒ
//						mainOrder.setGoods(productList);
						list.addAll(productList);
						//���Ի���Ʒ��Ϣ����������Ʒ�� 
						if(productList != null && productList.size() > 0){
							for(int j=0;j< productList.size();j++){
								if(productList.get(j).getPaid() > 0){
									PayApt payApt = wxBuyProductDao.findPayAptById(productList.get(j).getPaid());
									productList.get(j).setPayApt(payApt);
								}
							}
						}
					}
					//��ѯ�Ӷ����Լ�����Ʒ
					PayOrder childOrder = wxBuyProductDao.findChildOrderById(mainOrder.getId());//�Ӷ���
					if(childOrder != null){
						List<OrderGoods> goods = wxBuyProductDao.findPayOrderGoods(childOrder.getId());//�Ӷ�����Ʒ
						childOrder.setGoods(goods);
						mainOrder.setChildOrder(childOrder);//�����Ӷ���
						mainOrder.setGoodscount(childOrder.getGoodscount() + goodscount);
						list.addAll(goods);
					}
					mainOrder.setGoods(list);
					String subject = "";
					if(list.size() > 1){
						 subject = list.get(0).getProductname() + "��";
					}else{
						 subject = list.get(0).getProductname();
					}
					mainOrder.setSubject(subject);
				}
				dto.setPayOrderList(payOrderList);
			}
		}
		dto.setPager(new Pager(pageId, total, 10));
		return dto;
	}

	@Override
	public int updateUseJf(long productid, long hyuid, int usejf)
	{
		return wxBuyProductDao.updateShoppingCartJf(productid,hyuid,usejf);
	}

	@Override
	public long findPayHome(long owner)
	{
		return wxBuyProductDao.findPayHome(owner);
	}

	@Override
	public long findPayJfHome(long owner)
	{
		return wxBuyProductDao.findPayJfHome(owner);
	}

	@Override
	public int findShoppingCartProductNumByHyuid(long hyuid)
	{
		return wxBuyProductDao.findShoppingCartProductNumByHyuid(hyuid);
	}

	@Override
	public long findPayJfUserPage(long owner)
	{
		return wxBuyProductDao.findPayJfUserPage(owner);
	}

	@Override
	public PayRecord findPayRecordById(long id)
	{
		return wxBuyProductDao.findPayRecordById(id);
	}

	@Override
	public int delPayOrder(long payOrderid)
	{
		wxBuyProductDao.delPayOrder(payOrderid);
		return wxBuyProductDao.updatePayorderByFid(payOrderid);
	}

	@Override
	public OrderInfoDto findAddressList(long aid,long hyuid)
	{
		OrderInfoDto dto = new OrderInfoDto();
		dto.setPayAddressList(wxBuyProductDao.findPayAddressListByHyUid(hyuid));
		return dto;
	}

	@Override
	public long saveOrder(VisitUser vu, long[] id, long aid, int usejf,String ip,long owner)
	{
		//��һ�� �Ҷ�����Ʒ
		List<ShoppingCartRecord> list = new ArrayList<ShoppingCartRecord>();
		for (int i = 0; i < id.length; i++){
			ShoppingCartRecord cr = wxBuyProductDao.findShoppingProductById(id[i],vu.getHyUserId());
			if(cr == null){
				return -1;
			}
			if(vu.getHyUser().getLevelid() > 0){
				ProductLevel pl = wxBuyProductDao.findProductLevel(cr.getProductid(), vu.getHyUser().getLevelid());//���û�Ա��
				if(pl != null){
					if("J".equals(cr.getProduct().getType())){
						cr.getProduct().setSalesprice(pl.getPrice());
					}else{
						cr.getProduct().setSalesprice(Arith.div(pl.getPrice(), 100, 2));
					}
				}
			}
			
			list.add(cr);
		}
		int totalprice = 0;//��Ʒ�ܼ� �Է�Ϊ��λ
		int maxjf =0;
		//�ڶ��� ���ɶ���+��Ʒ���붩����������ֶ�����
		long wxuid = vu.getWxUser()==null?0:vu.getWxUser().getId();
		int subtype = list.get(0).getProduct().getType().equals("J")?0:1;//�����̳ǣ�0�� ΢�̳ǣ�1
		long mainOrderid = wxBuyProductDao.saveNewOrder(vu.getHyUserId(), wxuid, owner, subtype, aid, ip);//������
		long childOrderid = 0;
		for(ShoppingCartRecord sc : list ){
			ContentProduct cp = sc.getProduct();
			int price = subtype==0?cp.getSalesjifen():(int)(Arith.mul(cp.getSalesprice(), 100));//��Ʒ����  ����*1��ʵ��RMB*100
			if(cp.getSubtype().equals("K")){
				//����п�ȯ ���ֶ���
				if(childOrderid == 0){
					childOrderid = wxBuyProductDao.saveChildOrder(vu.getHyUserId(), wxuid, owner, subtype, aid, ip,mainOrderid,"DFK");//�Ӷ���
				}
				for (int i = 0; i < sc.getNum(); i++)
				{
					//������Ʒ���� ����(��Ʒ����)������¼
					wxBuyProductDao.savePayOrderGoods(childOrderid, cp.getId(),cp.getName(),cp.getSubtype(),cp.getSimgurl(), price, subtype, getRandomString(10), 1, sc.getPaid(),sc.getId());
				}
			}else if(cp.getSubtype().equals("C")){
				//ȯ��-�������͵���Ʒ
				long rs=wxBuyProductDao.savePayOrderGoods(mainOrderid, cp.getId(),cp.getName(),cp.getSubtype(),cp.getSimgurl(), price, subtype, getRandomString(10), 1, sc.getPaid(),sc.getId());
				ProductCode pcode=contentProductDao.findCodeByPid(cp.getId());
				if(pcode!=null){
					wxBuyProductDao.updateOrderPcode(rs,pcode.getId());
					contentProductDao.updateProductCodeUsed(pcode);
				}else{
					System.out.println("����ȯ����Ʒ�쳣=====ȯ�벻��productid:"+cp.getId());
				}
			}else{
				//ʵ�� ����������
				wxBuyProductDao.savePayOrderGoods(mainOrderid, cp.getId(),cp.getName(),cp.getSubtype(),cp.getSimgurl(), price, subtype, null, sc.getNum(), sc.getPaid(),sc.getId());
			}
			totalprice += price * sc.getNum();//��Ʒ����*��Ʒ����
			maxjf += cp.getMaxjf()*sc.getNum();//����ֿܵ۶��ٻ���
			//ɾ���ﳵ����
			wxBuyProductDao.deleteShoppingCart(sc.getId(), vu.getHyUserId());
			//������Ʒ���
			contentProductDao.updateUsed(sc.getProductid(),sc.getNum());
		}
		//�ۿۼ۸�
		int discountprice = 0;
		if(subtype == 1){//΢�̳�
			int[] res = zhekou(owner, vu.getHyUserId(), usejf , maxjf,totalprice);
			discountprice = res[0];
			usejf = res[1];
		}
		//���¼۸񶩵��۸�
		wxBuyProductDao.updateOrderPriceById(mainOrderid, totalprice, discountprice, (totalprice-discountprice));
		if(usejf > 0){
			//΢�̳ǲ��л��ֵֿ�
			//����usejf
			wxBuyProductDao.updateUseJf(mainOrderid, vu.getHyUserId(), usejf);
			//���Ի���
			this.updateBalance(vu.getHyUserId(), -usejf, "���ֵֿ��ֽ�", "JFD", "DKP", mainOrderid);//���»���
		}
		wxBuyProductDao.updateAddPersonBuyProduct(mainOrderid);
		return mainOrderid;
	}
	
	private int[] zhekou(long owner,long hyuid, int usejf,int maxjf,int totalprice){
		int totaljifen = this.findJFen(hyuid);//ӵ���ܻ���
		Wkq wkq = payShopOwnerDao.findWkq(owner, 1);
		double bili = wkq!=null&&wkq.getBili()>0?wkq.getBili():100; //Ĭ�ϻ��ֱ�100
		if( usejf >= totaljifen){
			//ʹ�û��� ������ڵ��� ���л���  �Ժ���Ϊ׼
			usejf = totaljifen;
		}
		if( usejf >= maxjf){
			//��ʹ�û��ִ��� ��Ʒ����ʹ�û��� �Ժ���Ϊ׼
			usejf = maxjf;
		}
		int discountprice = (int)(1/bili * 100 * usejf);//�ֿ�
		discountprice = discountprice > (int)(totalprice*0.5) ? (int)(totalprice*0.5):discountprice;
		return new int[]{discountprice,usejf};
	}

	@Override
	public long savePayRecord(PayRecord payRecord)
	{
		return wxBuyProductDao.savePayRecord(payRecord);
	}

	@Override
	public int updatePayorderStatusByFid(long out_trade_no,String status)
	{
		return wxBuyProductDao.updatePayorderStatusByFid(out_trade_no,status);
	}

	@Override
	public OrderInfoDto savePayMoney(long payOrderid,long hyuid,long owner,VisitUser vu,String url,String ip)
	{
		OrderInfoDto dto = new OrderInfoDto();
		PayOrder payOrder = wxBuyProductDao.findPayOrderById(payOrderid);
		List<OrderGoods> productList;
		String subject = "";
		if(payOrder.getGoodscount() > 0){
			 productList = wxBuyProductDao.findPayOrderGoods(payOrderid);
		}else{
			//����ȯ���� ���Ӷ����µ���Ʒ
			long id = wxBuyProductDao.findChildOrder(payOrderid);
			productList = wxBuyProductDao.findPayOrderGoods(id);
		}
		if(productList != null && productList.size() > 0){
			if(productList.size() > 1){
				subject = productList.get(0).getProductname() + "��";
			}else{
				subject = productList.get(0).getProductname();
			}
		}
		int it=this.findJFen(hyuid);
		int totalPrice = payOrder.getRealprice();
		if(it<totalPrice)
		{
			dto.setStatus(-1);
			dto.setHydesc("���ֲ���");
			return dto;
		}
		if (owner > 0)
		{
			List<WxTemplate> wts = this.findWxTemplate(owner,"JFS",0);	
			if (wts != null&&wts.size()>0&&vu!=null&&vu.getWxUser()!=null)
			{
				for(WxTemplate wt:wts)
				{
				JFDHLPTZ dd = (JFDHLPTZ) JsonStringUtil.String2Obj(wt.getData(), JFDHLPTZ.class);
				dd.setKeyword1(subject);
				dd.setKeyword2(totalPrice+"����");
				int ttt=it-totalPrice;
				dd.setKeyword3(ttt+"����");
				dd.setKeyword4(DateUtil.getDateTimeString(new Date()));
				WxTemplateJob wj = new WxTemplateJob();
				wj.setMpid(wt.getMpid());
				wj.setType("JFS");
				wj.setEntityid(wt.getEntityid());
				wj.setRemark(wt.getRemark());
				wj.setTemplate_id(wt.getTemplate_id());
				wj.setTouser(vu.getWxUser().getOpenid());
				wj.setData(dd.toData());
				wj.setUrl(url);
				this.addTmplMsg(wj);
				}
			}
		}
		this.updateBalance(hyuid, -totalPrice, "������Ʒ�һ�", "JFS", "DHS", payOrderid);
		
		wxBuyProductDao.updatepayOrderStatus(payOrderid,null, "CMP");
		wxBuyProductDao.updatePayorderStatusByFid(payOrderid,"CMP");//�����Ӷ���
		PayRecord payRecord = new PayRecord(); 
		payRecord.setIp(ip);
		payRecord.setMediaorder("����֧��");
		payRecord.setHyuid(hyuid);
		payRecord.setOrderid(payOrderid);
		payRecord.setPrice(totalPrice);
		wxBuyProductDao.savePayRecord(payRecord);//����֧����¼
		dto.setStatus(1);
		dto.setHydesc("֧���ɹ�");
		return dto;
	}

	@Override
	public OrderInfoDto findKqOrderList(long hyuid, String status, int pageId, int type)
	{
		OrderInfoDto dto = new OrderInfoDto();
		int total = wxBuyProductDao.findTotalKQOrderList(hyuid,status,type);
		int dfkCount = wxBuyProductDao.findTotalOrderList(hyuid,"DFK",type);//�����������
		int kqCount = wxBuyProductDao.finfTotalUnusedKQOrderList(hyuid,"CMP",type);//�Ѹ����δʹ�õĿ�ȯ����
		dto.setDfkCount(dfkCount);dto.setKqCount(kqCount);
		int start = (pageId - 1) * 10;
		if(total > 0){
			List<PayOrder> payOrderList = wxBuyProductDao.findKQOrderList(hyuid,status,type,start,10);
			if(payOrderList != null){
				for(int i=0;i<payOrderList.size();i++){
					List<OrderGoods> productList = wxBuyProductDao.findPayOrderGoods(payOrderList.get(i).getId());
					
					if(productList != null && productList.size() > 0){
						for(int j=0;j< productList.size();j++){
							if(productList.get(j).getPaid() > 0){
								PayApt payApt = wxBuyProductDao.findPayAptById(productList.get(j).getPaid());
								productList.get(j).setPayApt(payApt);
							}
						}
					}
					
					payOrderList.get(i).setGoods(productList);
					
					String subject = "";
					if(productList.size() > 1){
						 subject = productList.get(0).getProductname() + "��";
					}else{
						 subject = productList.get(0).getProductname();
					}
					payOrderList.get(i).setSubject(subject);
					
				}
				dto.setPayOrderList(payOrderList);
			}
		}
		dto.setPager(new Pager(pageId, total, 10));
		return dto;
	}

	@Override
	public OrderInfoDto findKqOrderList(long hyuid,String status,int pageId){
		OrderInfoDto dto = new OrderInfoDto();
		int start = (pageId - 1) * 10;
		List<PayOrder> payOrderList = wxBuyProductDao.findKQOrderList(hyuid,status,start,10);
		if(payOrderList != null){
			for(int i=0;i<payOrderList.size();i++){
				List<OrderGoods> productList = wxBuyProductDao.findPayOrderGoods(payOrderList.get(i).getId());
				
				if(productList != null && productList.size() > 0){
					for(int j=0;j< productList.size();j++){
						if(productList.get(j).getPaid() > 0){
							PayApt payApt = wxBuyProductDao.findPayAptById(productList.get(j).getPaid());
							productList.get(j).setPayApt(payApt);
						}
					}
				}
				
				payOrderList.get(i).setGoods(productList);
				
				String subject = "";
				if(productList.size() > 1){
					 subject = productList.get(0).getProductname() + "��";
				}else{
					 subject = productList.get(0).getProductname();
				}
				payOrderList.get(i).setSubject(subject);
				
			}
			dto.setPayOrderList(payOrderList);
		}
		return dto;
	}
	
	
	public int updatePayOrder(long payOrderid,long hyuid)
	{
		PayOrder payOrder = wxBuyProductDao.findPayOrderById(payOrderid);
		if(payOrder != null){
			wxBuyProductDao.updatePayorderStatusById(payOrderid,"YQX");
			wxBuyProductDao.updatePayorderStatusByFid(payOrderid, "YQX");
			
			if(payOrder.getUsejf() > 0){
				this.updateBalance(hyuid, payOrder.getUsejf(), "ȡ���������ַ���", "JFD", "DKP", payOrderid);
			}
			List<OrderGoods> goods = wxBuyProductDao.findPayOrderGoods(payOrderid);
			if(goods != null && goods.size()>0){
				for(int i=0;i< goods.size();i++){
					contentProductDao.updateUsed(goods.get(i).getPid(),-goods.get(i).getNum());
				}
			}
			wxBuyProductDao.updateCancelPersonBuyProduct(payOrderid);
		}
		return 1; 
	}

	@Override
	public OrderGoods findPayOrderGoodsById(long id)
	{
		return wxBuyProductDao.findPayOrderGoodsById(id);
	}

	@Override
	public long addToShoppingCart(long productid, long hyuid, String status, PayApt apt)
	{
		long paid = wxBuyProductDao.savePayApt(apt);
		return wxBuyProductDao.savePayShoppingCart(productid, hyuid, paid, status);
	}
	
	@Override
	public MarketingSet findPayShop(long owner)
	{
		return wxBuyProductDao.findPayShopByOwner(owner);
	}
	
	@Override
	public MarketingSet findJfPayShop(long owner)
	{
		return wxBuyProductDao.findPayJfShopByOwner(owner);
	}

	@Override
	public OrderInfoDto findPayOrderInfoById(long payOrderid)
	{
		OrderInfoDto dto = new OrderInfoDto();
		PayOrder payOrder = wxBuyProductDao.findPayOrderById(payOrderid);
		PayRecord record = wxBuyProductDao.findPayRecordByPoid(payOrderid);
		if(record != null){
			dto.setRecord(record);
		}
		if(payOrder != null){
			long aid = payOrder.getAddressid();
			if(aid > 0 ){
				PayAddress address = wxBuyProductDao.findPayAddressById(aid);//ʹ�õ�ַ
				dto.setAddress(address);
			}
			List<OrderGoods> list = new ArrayList<OrderGoods>();
			if(payOrder.getGoodscount() > 0){
				List<OrderGoods> productList = wxBuyProductDao.findPayOrderGoods(payOrderid);//��������Ʒ
				//���Ի���Ʒ��Ϣ����������Ʒ�� 
				if(productList != null && productList.size() > 0){
					for(int j=0;j< productList.size();j++){
						if(productList.get(j).getPaid() > 0){
							PayApt payApt = wxBuyProductDao.findPayAptById(productList.get(j).getPaid());
							productList.get(j).setPayApt(payApt);
						}
					}
				}
				list.addAll(productList);
			}
			
			PayOrder childOrder = wxBuyProductDao.findChildOrderById(payOrderid);//�Ӷ���
			if(childOrder != null){
				List<OrderGoods> goods = wxBuyProductDao.findPayOrderGoods(childOrder.getId());//�Ӷ�����Ʒ
				childOrder.setGoods(goods);
				payOrder.setChildOrder(childOrder);//�����Ӷ���
				payOrder.setGoodscount(childOrder.getGoodscount() + payOrder.getGoodscount());
				list.addAll(goods);
			}
			payOrder.setGoods(list);
			dto.setPayOrder(payOrder);
		}
		return dto;
	}

	@Override
	public boolean findPersonLimitNum(ContentProduct product,long hyuid,int num) {
		if(product != null && product.getPersonlimit() > 0){
			int count = wxBuyProductDao.findBuyProductNumByProductid(product.getId(),hyuid);//��ѯ�����¼����
			count = count + num;
			if(count >= product.getPersonlimit()){//����������������������ù���
				return false;
			} 
		}
		return true;
	}
	
}
