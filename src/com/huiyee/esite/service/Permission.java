package com.huiyee.esite.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//����Permissionע�Ᵽ���ڵĽ׶�
@Target(ElementType.METHOD)//��ע�ڷ�������
public @interface Permission {

	/** ģ�� */
	String module();
	/** Ȩ��ֵ */
	int privilege();
	
}