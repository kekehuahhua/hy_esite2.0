package com.huiyee.esite.service;

import org.aspectj.lang.ProceedingJoinPoint;

import com.huiyee.esite.util.HyConfig;


public class MemCachedAround {

	public Object memcached(ProceedingJoinPoint call) {
		Object target = call.getTarget();
		if( !(target instanceof IMemCacheAble) || HyConfig.isRun()) {
			try {
				return call.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		IMemCacheAble memCacheAble = (IMemCacheAble) target;
		int cacheTime = memCacheAble.getCacheTime();
		// cacheTime==0����ȫ���߻���
		if (cacheTime<=0) {
			try {
				return call.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		String info = memCacheAble.getClass().getName() + "."
				+ call.getSignature().getName() + ":";
		Object[] args = call.getArgs();
		String code = "";
		for (Object arg : args) {
			if (arg == null) {
				code = code + "~" + "null";
			} else {
				code = code + "~" + arg.toString();
			}
		}
		String key = info + code +":"+cacheTime;
		// System.out.println(key);
		MemCacheInvoke invoke = MemCacheInvoke.getInstance();
		Object result = invoke.get(key);
		if (result != null) {
			return result;
		}
		try {
			result = call.proceed();
			if (result != null) {
				invoke.set(key, result, cacheTime);// �ݶ���Ч��1���롣���ڸ����������
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
}