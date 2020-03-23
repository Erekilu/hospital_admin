package top.lsyweb.hosadm.domain;

import java.sql.Timestamp;

public class Log {

  private long logId;
  private long logMaster;
  private long logBelong;
  private String logContent;
  private java.sql.Timestamp logCreateTime;

  @Override
  public String toString()
  {
    return "Log{" + "logId=" + logId + ", logMaster=" + logMaster + ", logBelong=" + logBelong + ", logContent='"
            + logContent + '\'' + ", logCreateTime=" + logCreateTime + '}';
  }

  public long getLogId()
  {
    return logId;
  }

  public void setLogId(long logId)
  {
    this.logId = logId;
  }

  public long getLogMaster()
  {
    return logMaster;
  }

  public void setLogMaster(long logMaster)
  {
    this.logMaster = logMaster;
  }

  public long getLogBelong()
  {
    return logBelong;
  }

  public void setLogBelong(long logBelong)
  {
    this.logBelong = logBelong;
  }

  public String getLogContent()
  {
    return logContent;
  }

  public void setLogContent(String logContent)
  {
    this.logContent = logContent;
  }

  public Timestamp getLogCreateTime()
  {
    return logCreateTime;
  }

  public void setLogCreateTime(Timestamp logCreateTime)
  {
    this.logCreateTime = logCreateTime;
  }
}
