package com.huiyee.esite.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
 
public class HyFont
{
	
    public static Font loadFont(String fontFileName, float fontSize)  //��һ���������ⲿ���������ڶ����������С
    {
        try
        {
        	 File file = new File(fontFileName);
             FileInputStream aixing = new FileInputStream(file);
             Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
             Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
             aixing.close();
             return dynamicFontPt;
        }
        catch(Exception e)//�쳣����
        {
            e.printStackTrace();
            return new java.awt.Font("����", Font.PLAIN, 14);
        }
    }
    
    public static java.awt.Font Font1(){
        Font font = HyFont.loadFont("D:/apache-tomcat-8.0.23/me-webapps/hy_esite2.0/font/bb3175/2.ttf",20f);//����
        return font;//��������
    }
    
    public static void main(String[] args)
	{
		Font f = Font1();
		System.out.println(f.getSize());
		f = f.deriveFont(60f);
		System.out.println(f.getSize());
		File fl = new File("D:/2.png");
		try
		{
			ImageGenerate.generate("������", f, fl, new Color(255,255,0));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
    
}