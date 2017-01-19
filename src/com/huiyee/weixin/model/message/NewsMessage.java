package com.huiyee.weixin.model.message;

import java.util.List;

/**
 * ��ͼ����Ϣ�� ��ͼ�ĵ�ʱ�� Articles ֻ��һ��������
 * 
 * @author Caspar.chen
 */
public class NewsMessage extends BaseMessage {
	/**
	 * ͼ����Ϣ����������Ϊ10������
	 */
	private int ArticleCount;
	/**
	 * ����ͼ����Ϣ��Ϣ��Ĭ�ϵ�һ��itemΪ��ͼ
	 */
	private List<Item> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Item> getArticles() {
		return Articles;
	}

	public void setArticles(List<Item> articles) {
		Articles = articles;
	}
}