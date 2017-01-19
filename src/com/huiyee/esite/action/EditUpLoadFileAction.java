package com.huiyee.esite.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class EditUpLoadFileAction extends AbstractAction
{

	private long pageid;
	private String readContent;
	private String writeContent;
	private String fileUrl;
	private String type;//�����ж�jsp����
	
	public String readStyleFromFile()throws Exception{
		File file = new File(fileUrl);// ָ��Ҫ��ȡ���ļ�  
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));// ��ȡ���ļ���������  
        char[] bb = new char[1024];// ��������ÿ�ζ�ȡ�����ַ�  
        String str = "";// ������ÿ�ζ�ȡ�����ַ�ƴ��
        int n;// ÿ�ζ�ȡ�����ַ�����  
        while ((n = br.read(bb)) != -1) {  
            str += new String(bb, 0, n);  
        }  
        br.close();// �ر����������ͷ�����  
        readContent=str;
        return SUCCESS;
	}
	
	public String writeStyleToFile()throws Exception{
		int i=0;
        HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		File file = new File(fileUrl);// Ҫд����ı��ļ�  
	        if (!file.exists()) {// ����ļ������ڣ��򴴽����ļ�  
	            file.createNewFile();  
	        }  
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));// ��ȡ���ļ��������  
	    bw.write(writeContent);// д����  
	    bw.flush();// ��ջ�������������������������д���ļ���  
	    bw.close();// �ر��������ʩ����Դ  
	    int result=i++;
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
//	public static void main(String[] args)throws Exception
//	{
//		readCssFromFile();
//		writeCssToFile();
//		readCssFromFile();
//	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getReadContent()
	{
		return readContent;
	}

	public void setReadContent(String readContent)
	{
		this.readContent = readContent;
	}

	public String getWriteContent()
	{
		return writeContent;
	}

	public void setWriteContent(String writeContent)
	{
		this.writeContent = writeContent;
	}

	public String getFileUrl()
	{
		return fileUrl;
	}

	public void setFileUrl(String fileUrl)
	{
		this.fileUrl = fileUrl;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
