package com.huiyee.interact.research.mgr;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.research.dao.IResearchQuestionDao;
import com.huiyee.interact.research.dto.OrdData;
import com.huiyee.interact.research.dto.ResearchOptionDto;
import com.huiyee.interact.research.dto.ResearchQuestionDataDto;
import com.huiyee.interact.research.model.OptionIdx;
import com.huiyee.interact.research.model.ResearchQuestionListModel;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOption;
import com.huiyee.interact.research.model.ResearchQuestionOptionModel;
import com.huiyee.interact.research.model.ResearchQuestionOptionVO;
import com.huiyee.interact.research.model.ResearchRecordAnswer;
import com.huiyee.interact.research.util.FileUploadService;
public class ResearchQuestionMgrImpl implements IResearchQuestionMgr
{

	private IResearchQuestionDao researchQuestionDao;
	
	public IResearchQuestionDao getResearchQuestionDao()
	{
		return researchQuestionDao;
	}

	public void setResearchQuestionDao(IResearchQuestionDao researchQuestionDao)
	{
		this.researchQuestionDao = researchQuestionDao;
	}
	@Override
	public List<ResearchQuestionModel> findResearchQuestionList(long searchid,int start,int size)
	{
		return researchQuestionDao.findResearchQuestionList(searchid,start,size);
	}

	 private String createFileName(String fileName,String type) {
			int index = fileName.lastIndexOf('.');
			// �ж��ϴ��ļ����Ƿ�����չ��
			if (index != -1) {
				return FileUploadService.getNow()+type+ fileName.substring(index);
			}
			return FileUploadService.getNow()+type;
	}
	 
	@Override
	public long saveResearchQuestion(ResearchQuestionListModel rqlmodel,ResearchQuestionOptionModel rqomodel)
	{
		long resultid=0;
		int result=0;
		long mid=10006;
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		long searchid=rqlmodel.getSearchid();
		String type=rqlmodel.getType();
		String isreq=rqlmodel.getIsreq();
		for(int i=0;i<rqlmodel.getTitle().size();i++){
			String title=rqlmodel.getTitle().get(i);
			if(rqlmodel.getImg()!=null){
				String name=createFileName(rqlmodel.getImgFileName().get(i),"_m");
				String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
				String saveResult = FileUploadService.saveFile(rqlmodel.getImg().get(i),path,name);
				resultid=researchQuestionDao.saveResearchQuestion(searchid, type, title, saveResult,isreq);
			}else{
				String saveResult="";
				resultid=researchQuestionDao.saveResearchQuestion(searchid, type, title, saveResult,isreq);
			}
		}
		if(rqomodel!=null){
		  for(int i=0;i<rqomodel.getContent().size();i++){
			String content=rqomodel.getContent().get(i);
			if(rqomodel.getImg()!=null){
				String name=createFileName(rqomodel.getImgFileName().get(i),"_m");
				String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
				String saveResult = FileUploadService.saveFile(rqomodel.getImg().get(i),path,name);
				result=researchQuestionDao.saveQuestionContent(content, saveResult, resultid);
			}else{
				String saveResult ="";
				result=researchQuestionDao.saveQuestionContent(content, saveResult, resultid);
			}
		  }
		}
		if(resultid>0){
			return resultid;
		}else if(result>0){
			result=1;
		}
		return result;
		
	}
	@Override
	public int delResearchQuestion(long id)
	{
		int len=researchQuestionDao.delResearchQuestion(id);
		if(len>0){
			return len;
		}
		return 0;
	}
	  public List<ResearchQuestionDataDto> findResearchData(long researchid)
	{
		List<ResearchQuestionDataDto> questionlist = researchQuestionDao.findResearchQuestionListOrderByIdex(researchid);//����searchid �õ�question������
		for (ResearchQuestionDataDto researchQuestionDataDto : questionlist)  //���������
		{
			ResearchOptionDto dto = new ResearchOptionDto();
			List<List<Object>> optionobj = new ArrayList<List<Object>>();
			long questionid = researchQuestionDataDto.getId();
			List<ResearchQuestionOption> optionlist = researchQuestionDao.findQuestionOptionsByQuestionid(questionid);//���е�ѡ�� ����questionid �õ�option������
			int total = researchQuestionDao.findResearchOptionTotalByQuestionid(questionid);  
			DecimalFormat df2 = new DecimalFormat("##.##%"); //������ʽ������
			Gson gson = new Gson();
			if("ORD".equals(researchQuestionDataDto.getType())){
				List<Map<Integer,Integer>> lists = new ArrayList<Map<Integer,Integer>>();
				List<OrdData> l =new ArrayList<OrdData>();
				for(int i=0;i<optionlist.size();i++){
					Map<Integer,Integer> map = new HashMap<Integer, Integer>();
					List<OptionIdx> list1 = researchQuestionDao.countIdx(optionlist.get(i).getId());
					for(OptionIdx o : list1){
						map.putAll(o.getMaps());
					}
					for(int j=0;j<optionlist.size();j++){
						if(map.get(j+1)==null){
							map.put(j+1, 0);
						}
					}
					lists.add(map);
				}
				for(int i= 0 ;i<optionlist.size();i++){
					OrdData od = new OrdData();
					od.setName("��"+(i+1)+"λ");
					for(int j= 0 ;j<optionlist.size();j++){
						Map<Integer,Integer>  m = lists.get(j);
						od.getData().add(m.get(i+1));						
					}
					l.add(od);
				}
				dto.setOrdLists(l);
				dto.setOrdStr(gson.toJson(l));
				dto.setLists(lists);
			}
			//String type = researchQuestionDataDto.getType();
			if (optionlist != null && optionlist.size() > 0)
			{
				for (ResearchQuestionOption option : optionlist)
				{
					int num = option.getCount();
					String content = option.getContent();
					List<Object> votecontents = new ArrayList<Object>();
					if (total != 0)
					{
						double optionnum = (num * 1.00) / (total * 1.00);
						String percent = optionnum != 0 ? df2.format(optionnum) : "0%";
						votecontents.add(content + "=" + percent);
						option.setPercent(percent);
					}
					else
					{
						votecontents.add(content + "=0%");
						option.setPercent("0%");
					}
					votecontents.add(num);
					optionobj.add(votecontents);
				}
			}
			dto.setResearchdata(optionobj);
			dto.setTotal(total);
			dto.setList(optionlist);
			researchQuestionDataDto.setOptionDto(dto);
		}
		return questionlist;
	}
	  
	@Override
	public int updateResearchQuestion(long id, ResearchQuestionListModel rqlmodel,long questionid, ResearchQuestionOptionModel rqomodel)
	{
		int result=0;
		long mid=10006;
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		String isreq=rqlmodel.getIsreq();
		//��Ŀ����
		for(int i=0;i<rqlmodel.getTitle().size();i++){
			String title=rqlmodel.getTitle().get(i);
			//���жϱ���ͼƬ��Ĭ�ϵ�Ϊ""
			if("".equals(rqlmodel.getPic().get(i))){
				//���ж��û��ϴ���ͼƬ
				if(rqlmodel.getImg()!=null){
					String name=createFileName(rqlmodel.getImgFileName().get(i),"_m");
					String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
					String saveResult = FileUploadService.saveFile(rqlmodel.getImg().get(i),path,name);
					result=researchQuestionDao.updateResearchQuestion(id, title, saveResult,isreq);
				}else{
					String Tpic=rqlmodel.getPic().get(i);
					result=researchQuestionDao.updateResearchQuestion(id, title, Tpic,isreq);
				}
				//����û���һ���ϴ���ͼƬ�ٴα༭û���ϴ�ͼƬ��������
			}else{
				String Tpic=rqlmodel.getPic().get(i);
				result=researchQuestionDao.updateResearchQuestion(id, title, Tpic,isreq);
			}
		}
		//ѡ��
		if(rqomodel!=null){
			for(int i=0;i<rqomodel.getContent().size();i++){
				String content=rqomodel.getContent().get(i);
				//�༭��ʱ���û�����ѡ����������
				if(rqomodel.getId().get(i)==null){
					//���жϱ���ͼƬ��Ĭ�ϵ�Ϊ""
					if("".equals(rqomodel.getPic().get(i))){
						//���ж��û��ϴ���ͼƬ
						if(rqomodel.getImg()!=null){
							for(int a=0;a<rqomodel.getImg().size();a++){
								String name=createFileName(rqomodel.getImgFileName().get(a),"_m");
								String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
								String saveResult = FileUploadService.saveFile(rqomodel.getImg().get(a),path,name);
								result=researchQuestionDao.saveQuestionContent(content, saveResult, questionid);
								break;
							}
						}else{
							String Tpic=rqomodel.getPic().get(i);
							result=researchQuestionDao.saveQuestionContent(content, Tpic, questionid);
						}
						//����û���һ���ϴ���ͼƬ�ٴα༭û���ϴ�ͼƬ��������
					}else{
						String Tpic=rqomodel.getPic().get(i);
						result=researchQuestionDao.saveQuestionContent(content, Tpic, questionid);
					}
					//�༭��ʱ���û�û������ѡ����������
				}else{
					long optionsid=rqomodel.getId().get(i);
					//���жϱ���ͼƬ��Ĭ�ϵ�Ϊ""
					if("".equals(rqomodel.getPic().get(i))){
						//���ж��û��ϴ���ͼƬ
						if(rqomodel.getImg()!=null){
							for(int a=0;a<rqomodel.getImg().size();a++){
								String name=createFileName(rqomodel.getImgFileName().get(a),"_m");
								String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
								String saveResult = FileUploadService.saveFile(rqomodel.getImg().get(a),path,name);
								result=researchQuestionDao.updateResearchQuestionOption(optionsid,content, saveResult);
								break;
							}
						}else{
							String Tpic=rqomodel.getPic().get(i);
							result=researchQuestionDao.updateResearchQuestionOption(optionsid, content, Tpic);
						}
						//����û���һ���ϴ���ͼƬ�ٴα༭û���ϴ�ͼƬ��������
					}else{
						String Tpic=rqomodel.getPic().get(i);
						result=researchQuestionDao.updateResearchQuestionOption(optionsid, content, Tpic);
					}
				}
			}
		}
		if(result>0){
			result=1;
		}
		return result;
	}
	

	@Override
	public List<ResearchQuestionModel> findOneResearchQuestion(long questionid)
	{
		return researchQuestionDao.findOneResearchQuestion(questionid);
	}

	@Override
	public ResearchQuestionModel findTypeIds(long id)
	{
		return researchQuestionDao.findTypeIds(id);
	}

	@Override
	public int findResearchQuestionTotal(long id)
	{
		return researchQuestionDao.findResearchQuestionTotal(id);
	}

	@Override
	public int updateDownResearchQuestion(long id)
	{
		return researchQuestionDao.updateDownResearchQuestion(id);
	}

	@Override
	public int updateUpResearchQuestion(long id)
	{
		return researchQuestionDao.updateUpResearchQuestion(id);
	}

	@Override
	public int deleteResearchOptions(long id)
	{
		return researchQuestionDao.deleteResearchOptions(id);
	}

	@Override
	public List<ResearchQuestionOption> findoptionbyqid(long questionid)
	{
		return researchQuestionDao.findoptionbyqid(questionid);
	}

	@Override
	public List<ResearchQuestionModel> findquestionbyqid(long questionid,long searchid,int idx)
	{
		return researchQuestionDao.findquestionbyqid(questionid,searchid,idx);
	}

	@Override
	public int updateinTarget(long id, long target)
	{
		return researchQuestionDao.updateinTarget(id,target);
	}

	@Override
	public ResearchQuestionOptionVO selisTar(long id)
	{
		return researchQuestionDao.findselisTar(id);
	}

}
