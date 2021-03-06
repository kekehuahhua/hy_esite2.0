package com.huiyee.esite.mgr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.ActivityLog;
import com.huiyee.esite.model.ActivityReportAnalyse;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Source;
import com.huiyee.esite.model.Terminal;
import com.huiyee.esite.model.UserInfo;
import com.huiyee.esite.model.VisitLog;
import com.huiyee.weixin.model.WxPageShow;

public interface IActivityManager {
	
	public int findActivityCounts(long id);
	
	public long saveActivityAndModule(String name,List<Long> modules,long siteid, long ownerid);
	
	public List<Activity> findActivityList(long siteid);
	
	public List<Activity> findActivityId(long siteid,String name);
	
	public List<Module> findModuleList(long activityid);
	
	public List<Feature> findActivityFeature(long activityid);
	
	public Activity findActivityNameById(long activityid);
	
	public String delteActivity(long activityid);
	
	public String updateActivity(long activityid,ArrayList<Long> moduleList, String activityname);
	
	public List<Long> findModuleIdByActivityid(long activityid);
	
	public List<Activity> findActivityId(long siteid);
	
	public VisitLog findUserCount(long activityid);
	
	public VisitLog findUnkonwnUserCount(long activityid);
	
	public List<Activity> findActivityListBysitegrouid(long activityid);
	
	public List<Terminal> findVisitCountByTerminal(long activityid);
	
	public int findAnonVisitCountByTerminal(String name,long activityid);
	
	public List<Terminal> findAnonVisitCountByTerminal(long activityid);
	
	public List<Source> findVistitCountBySource(long activityid);
	
	public int findAnonVisitCountBySource(String name,long activityid);
	
	public List<Terminal> findParticipateCountByTerminal(long activityid);
	
	public int findAnonParticipateCountByTerminal(String name,long activityid);
	
	public Activity findActivityByActivityid(long activityid);
	
	public List<ActivityLog> findActivityParticipateDetails(long activityid,String name,int start,int pageSize);
	
	public List<ActivityLog> findActivityParticipateDetails(long activityid,String terminalName, String visitTime1, String visitTime2,String source, int start,int pageSize); 
	
	public UserInfo findNickNames(long siteid,long wbuid);
 	
	public List<Source> findParticipateCountBySource(long activityid);
	
	public int findAnonParticipateCountBySource(String name,long activityid);
	
	public List<Terminal> findParticipateSuccessCountByTerminal(long activityid);
	
	public int findAnonParticipateSuccessCountByTerminal(String name,long activityid);
	
	public List<Source> findParticipateSuccessCountBySource(long activityid);
	
	public int findAnonParticipateSuccessCountBySource(String name,long activityid);
	
	public int findParticipateCount(long activityid);
 	
 	public int findParticipateSuccessCount(long activityid);
 	
 	public List<VisitLog> findAnonVisitData(long activityid,int start,int pageSize);
 	
 	public int findAnonVisitDataCount(long activityid);
 	
 	public List<VisitLog> findnonAnonVisitData(long activityid,int start,int pageSize);
 	
 	public int findnonAnonVisitCount(long activityid);
 	
 	public List<VisitLog> findnonAnonVisitDataDetails(long activityid,String visitTime1,String visitTime2,String terminal,String source,int page,int pageSize);
 	
 	public int findnonAnonVisitDataDetailsCount(long activityid,String visitTime1,String visitTime2,String terminal,String source);
 	
 	public int findnonAnonVisitDataDetailsCount(long activityid,String nickname);
 	
 	public List<VisitLog> findnonAnonVisitDataDetails(long activityid,String nickname,int start,int pageSize );
 	
 	public List<Terminal> findTerminalNames(long activityid);
 	
 	public List<Source> findSourceNames(long activityid);
 	
 	public int findActivityParticipateDetailsCount(String terminalName,long activityid,String visitTime1,String visitTime2,String source);
 	
 	public List<Source> findActivityParticipateSourceNames(String terminalName,long activityid);
 	
 	public List<Source> findActivityParticipateSuccessSourceNames(String terminalName,long activityid);
 	
 	public List<UserInfo> findWbuid(long siteid, String nickname);
 	
 	public int findActivityParticipateDetailsByName(long activityid,String terminalName,long wbuid);
 	
 	public List<ActivityLog> findActivityParticipateDetailsByName(long activityid,String terminalName,long wbuid,int start,int pageSize);
 	
 	public List<ActivityLog> findActivityParticipateSuccessDetails(long activityid,String name,int start,int pageSize);
 	
 	public int findActivityParticipateSuccessDetailsCount(String terminalName,long activityid,String visitTime1,String visitTime2,String source);
 	
 	public List<ActivityLog> findActivityParticipateSuccessDetails(long activityid,String terminalName, String visitTime1, String visitTime2,String source, int start,int pageSize);
 	
 	public int findActivityParticipateSuccessDetailsByName(long activityid,String terminalName,long wbuid);
 	
 	public List<ActivityLog> findActivityParticipateSuccessDetailsByName(long activityid,String terminalName,long wbuid,int start,int pageSize);
 	
 	public int findParticipateFailCountByTerminal(String name,long activityid);
 	
 	public List<ActivityLog> findActivityParticipateFailDetails(long activityid,String name,int start,int pageSize);
 	
 	public List<Source> findActivityParticipateFailSourceNames(String terminalName,long activityid);
 	
 	public int findActivityParticipateFailDetailsCount(String terminalName,long activityid,String visitTime1,String visitTime2,String source);
 	
 	public List<ActivityLog> findActivityParticipateFailDetails(long activityid,String terminalName, String visitTime1, String visitTime2,String source, int start,int pageSize);
 	
 	public int findActivityParticipateFailDetailsByName(long activityid,String terminalName,long wbuid);
 	
 	public List<ActivityLog> findActivityParticipateFailDetailsByName(long activityid,String terminalName,long wbuid,int start,int pageSize);
 	
 	public List<Terminal> findParticipateFailCountByTerminal(long activityid);
 	
 	public List<Source> findParticipateFailCountBySource(long activityid);
 	
 	public UserInfo findwbuidByUid(long uid);
 	
 	public long saveActivityLog(long activityid, long wbuid, String type,String ip, String terminal, String source);

	public List<ContentCategory> findContentCategory(long ownerid);

	public WxPageShow findWxPageShowById(long wxshareid,long aid);

 }
