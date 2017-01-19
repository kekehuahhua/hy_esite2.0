package com.huiyee.esite.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.util.concurrent.ConcurrentHashMap;

import com.huiyee.esite.dto.BbsUserLoginTime;
import com.huiyee.esite.dto.CardDto;
import com.huiyee.esite.dto.VisitLogDto;
import com.huiyee.esite.dto.VisitPageTime;
import com.huiyee.esite.model.OwnerSetting;
import com.huiyee.weixin.model.WxMp;

public class CacheUtil
{
  public static Map<String, CardDto> psds=new ConcurrentHashMap<String, CardDto>();//��Ƭģ��Ļ���,keyΪpcid
  
  public static Map<Long, CardDto> pgds=new ConcurrentHashMap<Long, CardDto>();//ҳ�濨Ƭ�Ļ���,keyΪpageid
  
  public static Map<Long, String> htmls=new ConcurrentHashMap<Long, String>();//�հ�ҳ��html�Ļ���,keyΪpageid
  
  public static List<VisitLogDto> vlogs=new ArrayList<VisitLogDto>();//���ʼ�¼����־
  
  public static List<VisitPageTime> vpt=new ArrayList<VisitPageTime>();//ҳ�����ʱ��
  
  public static List<BbsUserLoginTime> bbs=new ArrayList<BbsUserLoginTime>();//��̳�û���¼ʱ��
  
//  public static Map<Long, OwnerSetting> oss=new ConcurrentHashMap<Long, OwnerSetting>();//osetting����
  
  public static Map<Long, WxMp> wxmps = new HashMap<Long, WxMp>();//���ںŵĻ���keyΪmpid
  public static Map<Long, WxMp> wxmpso = new HashMap<Long, WxMp>();//���ںŵĻ���keyΪowner
  
  
  public static synchronized void addvpt(VisitPageTime vl)
  {
	  vpt.add(vl);
  }

  public static synchronized List<VisitPageTime> findvpt()
  {
	  List<VisitPageTime> vl=vpt;
	  vpt=new ArrayList<VisitPageTime>();
	  return vl;
  }
  
 //================================================
  
  public static synchronized void addVisitLog(VisitLogDto vl)
  {
	  vlogs.add(vl);
  }

  public static synchronized List<VisitLogDto> findVisitLog()
  {
	  List<VisitLogDto> vl=vlogs;
	  vlogs=new ArrayList<VisitLogDto>();
	  return vl;
  }
  
  //================================================
  
  public static synchronized void addBbs(BbsUserLoginTime vl)
  {
	  bbs.add(vl);
  }

  public static synchronized List<BbsUserLoginTime> findBbs()
  {
	  int size=1000;
	  if(bbs.size()<1000)
	  {
		  size=bbs.size();
	  }
	  List<BbsUserLoginTime> vl= bbs.subList(0, size);
	  return vl;
  }

  public static synchronized void removeBbs(List<BbsUserLoginTime> vl)
  {
	  bbs.removeAll(vl);
  }
  
   //================================================
  
}
