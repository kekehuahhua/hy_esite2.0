package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.OwnerSetting;

public interface IHyUtilMgr
{
	 public long findOwnerByName(String oname);
	 
	 /**
	  * ���������� �ͻ��Լ�����������ƽ̨��Ȼ��������Լ������������ڲ����ˡ�
	  * @return
	  */
	 public List<OwnerSetting> findOwnerSetting();
}
