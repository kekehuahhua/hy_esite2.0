package com.huiyee.esite.dto.showdto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.RenQiRecord;
import com.huiyee.interact.renqi.model.RenQi;

public class Show144Dto implements IDto, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long source; //������
	private long visit;	//������
	private RenQi rq;
	private List<RenQiRecord> record;//��¼
	private int isSend;//�������Ƿ����
	private int isJoin;//�������Ƿ�����
	private int total;//�ܷ�

	public long getSource() {
		return source;
	}

	public void setSource(long source) {
		this.source = source;
	}

	public long getVisit() {
		return visit;
	}

	public void setVisit(long visit) {
		this.visit = visit;
	}

	public List<RenQiRecord> getRecord() {
		return record;
	}

	public void setRecord(List<RenQiRecord> record) {
		this.record = record;
	}

	public int getIsSend() {
		return isSend;
	}

	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

	public int getIsJoin() {
		return isJoin;
	}

	public void setIsJoin(int isJoin) {
		this.isJoin = isJoin;
	}

	public RenQi getRq() {
		return rq;
	}

	public void setRq(RenQi rq) {
		this.rq = rq;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
}
