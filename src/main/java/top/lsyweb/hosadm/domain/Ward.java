package top.lsyweb.hosadm.domain;

import java.sql.Timestamp;
import java.util.List;

public class Ward {

  private Long wardId;
  private Long wardBelong;
  private String wardStatus;
  private String wardName;
  private java.sql.Timestamp wardCreateTime;
  private java.sql.Timestamp wardLastEditTime;

  private List<Bed> wardBeds;

  @Override
  public String toString()
  {
    return "Ward{" + "wardId=" + wardId + ", wardBelong=" + wardBelong + ", wardStatus='" + wardStatus + '\''
            + ", wardName='" + wardName + '\'' + ", wardCreateTime=" + wardCreateTime + ", wardLastEditTime="
            + wardLastEditTime + ", wardBeds=" + wardBeds + '}';
  }

  public Long getWardId()
  {
    return wardId;
  }

  public void setWardId(Long wardId)
  {
    this.wardId = wardId;
  }

  public Long getWardBelong()
  {
    return wardBelong;
  }

  public void setWardBelong(Long wardBelong)
  {
    this.wardBelong = wardBelong;
  }

  public String getWardStatus()
  {
    return wardStatus;
  }

  public void setWardStatus(String wardStatus)
  {
    this.wardStatus = wardStatus;
  }

  public String getWardName()
  {
    return wardName;
  }

  public void setWardName(String wardName)
  {
    this.wardName = wardName;
  }

  public Timestamp getWardCreateTime()
  {
    return wardCreateTime;
  }

  public void setWardCreateTime(Timestamp wardCreateTime)
  {
    this.wardCreateTime = wardCreateTime;
  }

  public Timestamp getWardLastEditTime()
  {
    return wardLastEditTime;
  }

  public void setWardLastEditTime(Timestamp wardLastEditTime)
  {
    this.wardLastEditTime = wardLastEditTime;
  }

  public List<Bed> getWardBeds()
  {
    return wardBeds;
  }

  public void setWardBeds(List<Bed> wardBeds)
  {
    this.wardBeds = wardBeds;
  }
}
