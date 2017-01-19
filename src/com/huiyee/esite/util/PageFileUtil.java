package com.huiyee.esite.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class PageFileUtil {
	
	public static final String ROOT_PATH="";
	
	public static String readFile(String filePath){
		BufferedReader br = null;
		String str = null;
		try {
			File file = new File(filePath);// ָ��Ҫ��ȡ���ļ�  
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));// ��ȡ���ļ���������  
			char[] bb = new char[1024];// ��������ÿ�ζ�ȡ�����ַ�  
			str = "";
			int n;// ÿ�ζ�ȡ�����ַ�����  
			while ((n = br.read(bb)) != -1) {  
			    str += new String(bb, 0, n);  
			}  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return str;
	}
	
	public static void SaveFile(String filePath,String source){
		BufferedWriter bw = null;
		try {
			File file = new File(filePath);// Ҫд����ı��ļ�
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));// ��ȡ���ļ��������  
			bw.write(source);// д����  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally{
			if(bw != null){
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * �����ļ���
	 * 
	 * @param oldPath
	 *            ��String �ļ�·��
	 * @param newPath
	 *            ��String �ļ�·��
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs(); // ����ļ��в����� �������ļ���
			File oldfiles = new File(oldPath);
			String[] file = oldfiles.list();// ѭ��ʱ���ڴ����ʱ���ļ��б�
			if(file != null){
				File tempfile = null;// �����ʱ�ļ�
				for (int i = 0; i < file.length; i++) {
					// ѭ���õ��ļ����µ�ÿ���ļ�
					if (oldPath.endsWith(File.separator)) {
						tempfile = new File(oldPath + file[i]);
					} else {
						tempfile = new File(oldPath + File.separator + file[i]);
					}
		
					// ���ļ�����ֱ�ӿ��ļ�
					if (tempfile.isFile()) {
						copyFile(tempfile, newPath + "/"
								+ (tempfile.getName()).toString());
					}
		
					// ���ļ��A������ѭ�����ļ���
					if (tempfile.isDirectory()) {
						copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("�����ļ��С�" + oldPath + "��ʱ����");
			e.printStackTrace();

		}

	}
	
	/**
	 * ���Ƶ����ļ�
	 * 
	 * @param oldFile
	 *            ��File
	 * @param newPath
	 *            :String �ļ�·��
	 */
	public static void copyFile(File oldFile, String newPath) {

		Long starttime = System.currentTimeMillis();
		InputStream inStream = null;
		FileOutputStream fout = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			if (oldFile.exists()) { // �ļ�����ʱ
				inStream = new FileInputStream(oldFile); // ����ԭ�ļ�
				fout = new FileOutputStream(newPath);

				byte[] buffer = new byte[1024];

				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // �ֽ��� �ļ���С
					fout.write(buffer, 0, byteread);
				}
				fout.flush();
			}
		} catch (Exception e) {
			System.out.println("�����ļ���" + oldFile.getAbsolutePath() + "��ʱ����");
			e.printStackTrace();
		} finally {
			try {
				// �ر�������
				if (inStream != null) {
					inStream.close();
				}
				// �ر������
				if (fout != null) {
					fout.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
