package top.lsyweb.hosadm.dto;

import top.lsyweb.hosadm.domain.Log;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-16
 */
public class LogExecution
{
	private Log log;
	private List<Log> logList;
	private int logCount;

	@Override
	public String toString()
	{
		return "LogExecution{" + "log=" + log + ", logList=" + logList + ", logCount=" + logCount + '}';
	}

	public Log getLog()
	{
		return log;
	}

	public void setLog(Log log)
	{
		this.log = log;
	}

	public List<Log> getLogList()
	{
		return logList;
	}

	public void setLogList(List<Log> logList)
	{
		this.logList = logList;
	}

	public int getLogCount()
	{
		return logCount;
	}

	public void setLogCount(int logCount)
	{
		this.logCount = logCount;
	}
}
