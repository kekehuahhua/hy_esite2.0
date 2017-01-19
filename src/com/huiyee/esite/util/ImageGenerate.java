package com.huiyee.esite.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageGenerate {
 
    public static BufferedImage generate(String text,Font font,File imagefile,Color color) throws IOException {
		
    	//��ȡfont����ʽӦ����str�ϵ���������

		  Rectangle2D r=font.getStringBounds(text, new FontRenderContext(AffineTransform.getScaleInstance(1, 1),false,false));

		  int unitHeight=(int)Math.floor(r.getHeight());//��ȡ�����ַ��ĸ߶�

		  //��ȡ����str����font��ʽ�Ŀ�����������������+1��֤��Ⱦ�������������ַ�����ΪͼƬ�Ŀ��

		  int width=(int)Math.round(r.getWidth())+1;

		  int height=unitHeight+3;//�ѵ����ַ��ĸ߶�+3��֤�߶Ⱦ����������ַ�����ΪͼƬ�ĸ߶�
    	// ����BufferedImage����
    	BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
    	// ��ȡGraphics2D
    	Graphics2D g2d = image.createGraphics();
    	// ----------  ��������Ĵ���ʹ�ñ���͸��  -----------------
    	image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    	g2d.dispose();
    	g2d = image.createGraphics();
    	// ----------  ����͸���������  -----------------
    	g2d.setColor(color);
    	g2d.setFont(font);
    	// ��ͼ
    	g2d.setStroke(new BasicStroke(1));
    	g2d.drawString(text, 0, font.getSize());
    	//�ͷŶ���
    	g2d.dispose();
    	// �����ļ�
    	ImageIO.write(image, "png", imagefile);
    	return image;
    }
    
    public static BufferedImage generate2() throws IOException {
		
		int width=1;
		int height=1;//�ѵ����ַ��ĸ߶�+3��֤�߶Ⱦ����������ַ�����ΪͼƬ�ĸ߶�
    	// ����BufferedImage����
    	BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
    	// ��ȡGraphics2D
    	Graphics2D g2d = image.createGraphics();
    	// ----------  ��������Ĵ���ʹ�ñ���͸��  -----------------
    	image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    	g2d.dispose();
    	g2d = image.createGraphics();
    	// ----------  ����͸���������  -----------------
    	//�ͷŶ���
    	g2d.dispose();
    	// �����ļ�
//    	ImageIO.write(image, "png", new File("D:/3.png"));
    	return image;
    }
    
    public static void main(String[] args)
	{

	}
    
}