package com.huiyee.interact.appointment.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.appointment.model.AptMapping;
import com.huiyee.interact.appointment.model.OrderMappingModel;

public class ToolUtil {
	
	//����ĸתСд
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    //����ĸת��д
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    public static List<String> testReflect(Object model,Object model1) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	List<String> list = new ArrayList<String>();
        Field[] field = model1.getClass().getDeclaredFields();        //��ȡʵ������������ԣ�����Field����  
            for(int j=0 ; j<field.length ; j++){     //������������
                String name =toUpperCaseFirstOne( field[j].getName());    //��ȡ���Ե�����
                String type = field[j].getGenericType().toString();    //��ȡ���Ե�����
                if(type.equals("class java.lang.String")){   //���type�������ͣ���ǰ�����"class "�����������
                    Method m = model.getClass().getMethod("get"+name);
                    String value = (String) m.invoke(model);    //����getter������ȡ����ֵ
                    if(value != null){
                        list.add(value+"");
                    }
                }
                if(type.equals("class java.lang.Integer")|| type.equals("int")){    
                    Method m = model.getClass().getMethod("get"+name);
                    Integer value = (Integer) m.invoke(model);
                    if(value != null && value!=0){
                        list.add(value+"");
                    }
                }
                if(type.equals("class java.lang.Long")|| type.equals("long")){    
                    Method m = model.getClass().getMethod("get"+name);
                    Long value = (Long) m.invoke(model);
                    if(value != null && value!=0){
                        list.add(value+"");
                    }
                }
                if(type.equals("class java.lang.Short")|| type.equals("short")){    
                    Method m = model.getClass().getMethod("get"+name);
                    Short value = (Short) m.invoke(model);
                    if(value != null){
                        list.add(value+"");
                    }
                }      
                if(type.equals("class java.lang.Double")|| type.equals("double")){    
                    Method m = model.getClass().getMethod("get"+name);
                    Double value = (Double) m.invoke(model);
                    if(value != null){                    
                    	list.add(value+"");
                    }
                }                 
                if(type.equals("class java.lang.Boolean")|| type.equals("boolean")){
                    Method m = model.getClass().getMethod("get"+name);   
                    Boolean value = (Boolean) m.invoke(model);
                    if(value != null){                      
                        list.add(value+"");
                    }
                }
                if(type.equals("class java.util.Date")|| type.equals("date")){
                    Method m = model.getClass().getMethod("get"+name);                   
                    Date value = (Date) m.invoke(model);
                    if(value != null){
                        list.add(value+"");
                    }
                }
            }
            return list;
    }
    
    
    public static List<String> testReflect(AppointmentDataModel model,Object model1) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	List<String> list = new ArrayList<String>();
    	if(model!=null){
    		List<AptMapping> maps=model.getMaps();        //��ȡʵ������������ԣ�����Field����  
            for(int j=0 ; j<maps.size() ; j++){     //������������
                String name =toUpperCaseFirstOne(maps.get(j).getMapping());    //��ȡ���Ե�����
                Method m = model.getClass().getMethod("get" + name);
				Object value = m.invoke(model); // ����getter������ȡ����ֵ
				if (value != null)
				{
					list.add(value + "");
				}else{
					list.add("");	
				}
            }
    	}
        return list;
    }
}
