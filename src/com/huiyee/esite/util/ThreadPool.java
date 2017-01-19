package com.huiyee.esite.util;

/**
 * �̳߳�,ά���߳�
 * autor :hzm
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.huiyee.esite.handler.Handler;
import com.huiyee.esite.handler.HandlerRunner;



public class ThreadPool
{
	private int corePoolSize = 100;// �̳߳�ά���̵߳���������
	private int maximumPoolSize = 1000;// �̳߳�ά���̵߳��������
	private long keepAliveTime = 0;// �̳߳�ά���߳�������Ŀ���ʱ��
	private TimeUnit unit = TimeUnit.SECONDS;// �̳߳�ά���߳�������Ŀ���ʱ��ĵ�λ
	private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);// �̳߳���ʹ�õĻ������
	private RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();// �̳߳ضԾܾ�����Ĵ������
	private int sleeptime = 5000;// ���߳��ύ����ʧ�ܵ�ʱ���ظ��ύ�����ʱ����
	private ThreadPoolExecutor pool = null;

	public synchronized void dowork(Handler whandler, Object taskData)
	{
		HandlerRunner hr = new HandlerRunner();
		hr.setData(whandler, taskData);
		if (pool == null)
		{
			pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);// �����̳߳�
		}
		try
		{
			pool.execute(hr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				Thread.sleep(sleeptime);
			}
			catch (InterruptedException e1)
			{

				e1.printStackTrace();
			}
			dowork(whandler, taskData);
		}
	}

	public int getCorePoolSize()
	{
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize)
	{
		this.corePoolSize = corePoolSize;
	}

	public int getMaximumPoolSize()
	{
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize)
	{
		this.maximumPoolSize = maximumPoolSize;
	}

	public long getKeepAliveTime()
	{
		return keepAliveTime;
	}

	public void setKeepAliveTime(long keepAliveTime)
	{
		this.keepAliveTime = keepAliveTime;
	}

	public TimeUnit getUnit()
	{
		return unit;
	}

	public void setUnit(TimeUnit unit)
	{
		this.unit = unit;
	}

	public BlockingQueue<Runnable> getWorkQueue()
	{
		return workQueue;
	}

	public void setWorkQueue(BlockingQueue<Runnable> workQueue)
	{
		this.workQueue = workQueue;
	}

	public RejectedExecutionHandler getHandler()
	{
		return handler;
	}

	public void setHandler(RejectedExecutionHandler handler)
	{
		this.handler = handler;
	}

	public int getSleeptime()
	{
		return sleeptime;
	}

	public void setSleeptime(int sleeptime)
	{
		this.sleeptime = sleeptime;
	}

	public ThreadPoolExecutor getPool()
	{
		return pool;
	}

	public void setPool(ThreadPoolExecutor pool)
	{
		this.pool = pool;
	}

}
